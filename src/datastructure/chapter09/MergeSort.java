package datastructure.chapter09;

public class MergeSort {


    private static <T extends Comparable<? super T>> T[] getTempArray() {
        return null;
    }

    private static <T extends Comparable<? super T>> T[] merge(T[] array1, int array1StartIndex, int array1EndIndex, T[] array2, int array2StartIndex, int array2EndIndex, T[] tempArray) {

        return null;
    }

    private static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex, boolean reverse) {
        checkArguments(array, startIndex, endIndex);

        if (startIndex == endIndex) {
            return;
        } else {
            sort(array, startIndex, (startIndex + endIndex) / 2, reverse);
            sort(array, startIndex, (startIndex + endIndex) / 2, reverse);
            merge()
        }

    }


    /**
     * 检测参数合理性的方法
     *
     * @param array      要排序的数组
     * @param startIndex 要排序部分的开始索引
     * @param endIndex   要排序部分的结束索引
     * @param <T>        泛型参数, 实现Comparable接口
     */
    private static <T> void checkArguments(T[] array, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex < 0 || startIndex > array.length - 1 || endIndex > array.length - 1 || startIndex > endIndex) {
            throw new IllegalArgumentException("索引超出范围. startIndex=" + startIndex + " endIndex=" + endIndex);
        }
    }
}
