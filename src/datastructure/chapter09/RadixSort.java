package datastructure.chapter09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {

    public static void sort(String[] array) {
        RadixSort.sort(array, false);
    }

    public static void sortDesc(String[] array) {
        RadixSort.sort(array, true);
    }

    /**
     * 内部类Buckets, 内部是一个ArrayList数组, 长度为10, 对应0-9的桶
     */
    private static class Buckets {

        private static int count = 10;
        private ArrayList<String>[] lists;

        /**
         * 构造器, 创建内部的桶数组
         */
        @SuppressWarnings("unchecked")
        public Buckets() {
            lists = new ArrayList[count];
            for (int i = 0; i < count; i++) {
                lists[i] = new ArrayList<String>();
            }
        }

        /**
         * 清除所有的桶, 方便调用.
         */
        public void clearAll() {
            for (int i = 0; i < count; i++) {
                lists[i].clear();
            }
        }

        /**
         * 向编号为index的桶中放入target元素
         *
         * @param index  桶的编号
         * @param target 要放入桶中的元素
         */
        public void putIntoBucket(int index, String target) {
            lists[index].add(target);
        }

        /**
         * 显示桶中所有元素的内容
         */
        public void showBuckets() {
            for (int i = 0; i < count; i++) {
                System.out.println(i + "号桶的内容是:\t" + lists[i]);
            }
        }

        /**
         * 将桶中的元素按桶的顺序全部取出到数组中的方法
         *
         * @param array 要将桶中的元素按顺序装到的数组
         */
        public void getAllToArray(String[] array) {
            int startIndex = 0;


            for (int i = 0; i < count; i++) {
                if (lists[i].size() > 0) {
                    for (String s : lists[i]) {
                        array[startIndex] = s;
                        startIndex++;
                    }
                }
            }

            //装完之后清空桶
            this.clearAll();
            //打印语句
//            System.out.println("成功将元素装入数组中, 总数为: " + (startIndex));
        }

    }

    /**
     * 基数排序的核心方法, 这里就不使用泛型了, 针对的是字符串形式的整数的数组
     *
     * @param array 要排序的数组
     */
    private static void sort(String[] array, boolean reverse) {

        //这里实际上应该检查一下字符串数组是否合法, 不过就不检查了, 直接来进行使用.

        if (array.length == 0 || array.length == 1) {
            System.out.println("数组无需排序");
            return;
        }

        Buckets buckets = new Buckets();

        //首先应该找到这个数组中最长的字符串
        int max = array[0].length();

        for (String s : array) {
            if (s.length() > max) {
                max = s.length();
            }
        }

        //此时的max就是总循环的次数, 有几位就排几次.
        for (int sortTimes = 0; sortTimes < max; sortTimes++) {
            //打印语句
//            System.out.println("-------------------------------------第" + (sortTimes + 1) + "次排序-------------------------------------");


            //将数组中的每个字符串元素放入对应的桶中.

            //这里首先需要判断一下, 如果字符串的长度小于当前的sortTimes+1, 比如当前排到右边起第二位, 但是字符串只长1, 则将该位当做0.
            for (int i = 0; i < array.length; i++) {
                if (array[i].length() < sortTimes + 1) {

                    if (!reverse) {
                        buckets.putIntoBucket(0, array[i]);
                    } else {
                        buckets.putIntoBucket(9, array[i]);
                    }

                    //打印语句
//                    System.out.println("第" + (sortTimes + 1) + "次排序的第" + (i + 1) + "个元素: " + array[i] + " 被放入0号桶.");
                }
                //如果长度足够, 则对右侧起的sortTimes+1位进行排序
                else {
                    //注意, 这里取到的是字符0-9对应的char, 还需要减去48才行
                    int index = array[i].charAt(array[i].length() - 1 - sortTimes) - 48;
//                    System.out.println("当前" + array[i] + "的第" + (sortTimes + 1) + "位是: " + index);
                    //将元素放进对应index编号的桶里

                    if (!reverse) {
                        buckets.putIntoBucket(index, array[i]);
                    } else {
                        buckets.putIntoBucket(9 - index, array[i]);
                    }

                    //打印语句
//                    System.out.println("第" + (sortTimes + 1) + "次排序的第" + (i + 1) + "个元素: " + array[i] + " 被放入" + index + "号桶.");
                }

            }

            //打印语句
//            buckets.showBuckets();

            //将元素重新放回到数组中
            buckets.getAllToArray(array);

            //打印语句
//            System.out.println("本次排序后的数组是: " + Arrays.toString(array));
//            System.out.println("-------------------------------------第" + (sortTimes + 1) + "次排序-------------------------------------");
//            System.out.println();
        }

        System.out.println("排序完成: " + Arrays.toString(array));

    }

    public static void main(String[] args) {
        String[] array = new String[]{
                "4123", "2398", "2610", "3019", "528", "7003", "53", "3129", "543", "294"
        };

        RadixSort.sort(array, false);
        RadixSort.sort(array, true);

    }


}
