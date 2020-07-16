package multithread.designpatternsformultithread;

public class Test {


    public static void main(String[] args) throws InterruptedException {

        int[] array = new int[10000000];

        for (int i = 0; i < 10000000; i++) {
            array[i] = i;
        }


        MultiSearch searcher = new MultiSearch(array);


        int index = searcher.search(9320420);

        System.out.println("找到的索引是: "+index);

    }
}
