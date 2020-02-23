package datastructure.chapter1.exercise;

import datastructure.chapter1.ResizableArrayBag;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Exerscise01 {

    public static void main(String[] args) {

        int n;
        int m;

        Scanner in = new Scanner(System.in);

        System.out.print("请输入整数的数量n: ");
        n = Integer.parseInt(in.nextLine());

        System.out.print("请输入整数的范围1-m 的 m: ");
        m = Integer.parseInt(in.nextLine());

        ResizableArrayBag<Integer> bag = new ResizableArrayBag<>(n);

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            bag.add(random.nextInt(m) + 1);
        }

        System.out.println("生成的随机数字是: " + Arrays.toString(bag.toArray()));

        boolean game =true;

        while (game) {
            System.out.println("输入你猜测的1-" + m + "之间选中的" + n + "个整数: ");
            String input = in.nextLine();
            String[] arrays = input.split("\\s+");
            int[] numbers = new int[arrays.length];
            for (int i = 0; i < arrays.length; i++) {
                numbers[i] = Integer.parseInt(arrays[i]);
            }

            System.out.println("输入的数组是: " + Arrays.toString(numbers));
            ResizableArrayBag<Integer> inputNumberBag = new ResizableArrayBag<>(arrays.length);
            for (Integer i : numbers) {
                inputNumberBag.add(i);
            }

            System.out.println("输入的数字组成的包是 " + Arrays.toString(inputNumberBag.toArray()));

            if (inputNumberBag.equals(bag)) {
                System.out.print("正确! 再玩一次? y/n");
                if (in.nextLine().equals("y")) {
                }else{
                    System.out.println("再见");
                    game = false;
                }

            } else{
                //判断用户输入包中的每个数据是否正确
                for (int i : numbers) {
                    if (bag.getFrequencyOf(i) == inputNumberBag.getFrequencyOf(i)) {
                        System.out.println("你输入的"+i+"是正确的. 再猜");
                    }
                }

            }

        }
    }

}
