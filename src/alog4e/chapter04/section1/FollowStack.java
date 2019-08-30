package alog4e.chapter04.section1;

import java.util.ArrayList;

public class FollowStack {

    private static ArrayList<Integer> arrayList = new ArrayList<>();

    private static int count = 0;

    private static int fib(int n) {

        if (!arrayList.isEmpty()) {
            arrayList.remove(arrayList.size() - 1);
        }
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }
        count++;
        arrayList.add(n);
        System.out.println(count + "" + arrayList);
        return fib(n - 1) + fib(n - 2);

    }

    public static void main(String[] args) {
        System.out.println(fib(10));

    }
}
