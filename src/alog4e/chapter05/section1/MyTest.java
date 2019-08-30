package alog4e.chapter05.section1;

import java.util.Arrays;

public class MyTest {
    public static void main(String[] args) {
        String s = "fdsafdsakjllkj";

        char[] s1 = new char[]{'s', 'v', '2'};
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i));
        }

        System.out.println(Arrays.toString(s1));
    }
}
