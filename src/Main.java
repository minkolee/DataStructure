import alog4e.libs.In;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);

        int i = Integer.parseInt(scanner.nextLine());

        String s1 = null;
        String s2 = null;

        int j = 1;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            s1 = line.split(" ")[0];
            s2 = line.split(" ")[1];


            System.out.println("Case " + j + ":");
            System.out.println(s1 + " + " + s2 + " = " + answer(s1, s2));
            if (j != i) {
                System.out.println();
            }
            j++;
        }

    }


    private static String answer(String s1, String s2) {

        //进位标志一开始是false
        boolean exceed10 = false;

        //两个字符串当前索引
        int index1 = s1.length() - 1;
        int index2 = s2.length() - 1;

        //最大索引, 控制总循环次数
        int maxIndex = Math.max(index1, index2);

        //用栈保存字符
        Stack<Character> stack = new Stack<>();

        for (int i = maxIndex; i >= 0; i--) {

            int n1 = 0;
            int n2 = 0;

            if (index1 >= 0) {
                n1 = (s1.charAt(index1) - 48);
            }

            if (index2 >= 0) {
                n2 =  (s2.charAt(index2) - 48);
            }

            //如果上一位产生了进位, 则当前计算结果+1, 再根据结果设置进位
            if (exceed10) {
                int result = n1 + n2 + 1;
                if (result >= 10) {
                    exceed10 = true;
                    stack.push((char) (result - 10 + 48));
                } else {
                    exceed10 = false;
                    stack.push((char) (result + 48));
                }
            //上一位没产生进位, 不用+1
            } else {
                int result = n1 + n2;
                if (result >= 10) {
                    exceed10 = true;
                    stack.push((char) (result - 10 + 48));
                } else {
                    exceed10 = false;
                    stack.push((char) (result + 48));
                }
            }

            index1 = index1 - 1;
            index2 = index2 - 1;
        }

        //完成之后再最后检测一次进位标记, 如果是true就再放一个1
        if (exceed10) {
            stack.push('1');
        }


        StringBuilder stringBuilder = new StringBuilder();

        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }


        return stringBuilder.toString();
    }



}
