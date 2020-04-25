package datastructure.chapter21;

import datastructure.chapter12.MyLinkedList;
import datastructure.chapter19.Dictionary;

import java.util.Arrays;
import java.util.Iterator;

public class ZipDictionary<K, V> implements Dictionary<K, V> {

    private MyLinkedList<Entry<K, V>>[] dictionary;

    private int capacity = 17;

    private static int[] primeNumbers = new int[]{37, 79, 151, 307, 617, 1237, 2477, 4957, 10007};

    @SuppressWarnings("unchecked")
    public ZipDictionary() {
        dictionary = new MyLinkedList[capacity];
    }


    private class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }


    @Override
    public void add(K key, V value) {
        if (key == null || value == null) {
            throw new RuntimeException("不允许添加null.");
        }

        checkSize();

        Entry<K, V> newEntry = new Entry<>(key, value);

        //关键步骤, 计算散列码并压缩到下标中.
        int index = Math.abs(newEntry.hashCode() % dictionary.length);
        //如果为空就先创建线性表, 然后添加
        if (dictionary[index] == null) {
            dictionary[index] = new MyLinkedList<>();
            dictionary[index].add(newEntry);
        } else {
            //如果不为空, 需要先探查有没有这个东西

            int result = dictionary[index].getPosition(newEntry);
            //如果没找到, 就直接添加, 可以考虑添加在头部, 这样下一次查找会比较快
            if (result == -1) {
                dictionary[index].add(0, newEntry);
            } else {
                //如果找到了, 就替换
                dictionary[index].replace(result, newEntry);
            }
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(K key) {
        int index = Math.abs(key.hashCode() % dictionary.length);

        Entry<K, V> target = new Entry<>(key, (V) new Object());

        if (dictionary[index] == null) {
            return null;
        } else {
            int result = dictionary[index].getPosition(target);
            if (result == -1) {
                return null;
            } else {
                V value = dictionary[index].getEntry(result).value;
                dictionary[index].remove(result);
                return value;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public V getValue(K key) {
        int index = Math.abs(key.hashCode() % dictionary.length);
        Entry<K, V> target = new Entry<>(key, (V) new Object());

        if (dictionary[index] == null) {
            return null;
        } else {
            int result = dictionary[index].getPosition(target);
            if (result == -1) {
                return null;
            } else {
                return dictionary[index].getEntry(result).value;
            }
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(K key) {
        int index = Math.abs(key.hashCode() % dictionary.length);
        Entry<K, V> target = new Entry<>(key, (V) new Object());

        if (dictionary[index] == null) {
            return false;
        } else {
            return dictionary[index].getPosition(target) != -1;
        }

    }


    @Override
    public boolean isEmpty() {
        int sum = 0;

        for (MyLinkedList<Entry<K, V>> entries : dictionary) {
            if (entries != null) {
                sum = sum + entries.getLength();
            }
        }
        return sum == 0;
    }

    @Override
    public int getSize() {
        int sum = 0;

        for (MyLinkedList<Entry<K, V>> entries : dictionary) {
            if (entries != null) {
                sum = sum + entries.getLength();
            }
        }
        return sum;
    }

    @Override
    public void clear() {
        Arrays.fill(dictionary, null);
    }

    public void showAll() {
        for (int i = 0; i < dictionary.length; i++) {
            if (dictionary[i] != null) {

                System.out.println(i + "|" + Arrays.toString(dictionary[i].toArray()));
            } else {
                System.out.println(i + "|");
            }
        }
    }

    @Override
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    @Override
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    private class KeyIterator implements Iterator<K> {

        private K[] array;

        private int index;

        private int number;

        @SuppressWarnings("unchecked")
        KeyIterator() {
            this.index = 0;
            this.number = getSize();
            array = (K[]) new Object[number];

            int i = 0;

            for (MyLinkedList<Entry<K, V>> list : dictionary) {
                if (list != null) {
                    for (Entry<K, V> entry : list) {
                        array[i] = entry.key;
                        i++;
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return index != number;
        }

        @Override
        public K next() {
            K result = array[index];
            index++;
            return result;
        }
    }

    private class ValueIterator implements Iterator<V> {

        private V[] array;

        private int index;

        private int number;

        @SuppressWarnings("unchecked")
        ValueIterator() {
            this.index = 0;
            this.number = getSize();
            array = (V[]) new Object[number];

            int i = 0;

            for (MyLinkedList<Entry<K, V>> list : dictionary) {
                if (list != null) {
                    for (Entry<K, V> entry : list) {
                        array[i] = entry.value;
                        i++;
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return index != number;
        }

        @Override
        public V next() {
            V result = array[index];
            index++;
            return result;
        }
    }

    @SuppressWarnings("unchecked")
    private void checkSize() {
        double loadFactor = (double) getSize() / dictionary.length;
        if (loadFactor > 0.7) {
            for (int primeNumber : primeNumbers) {
                if (primeNumber > capacity) {
                    capacity = primeNumber;
                    //用一个临时引用保存原来的内部数据结构
                    MyLinkedList<Entry<K, V>>[] temp = dictionary;

                    //然后创建一个新的数据结构作为字典
                    dictionary = new MyLinkedList[capacity];

                    //由于是散列, 不能直接复制, 所以只好一个一个把原来的散列表的内容装载到新的散列表中.
                    loadDataToNewDictionary(temp);

                    break;
                }
            }
        }
    }

    private void loadDataToNewDictionary(MyLinkedList<Entry<K, V>>[] array) {
        for (MyLinkedList<Entry<K, V>> list : array) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    add(entry.key, entry.value);
                }
            }
        }
    }
}
