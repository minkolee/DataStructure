package datastructure.chapter21;

import datastructure.chapter19.LinearDictionary;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        ZipDictionary<String, String> dictionary1 = new ZipDictionary<>();

        Random random = new Random();

        System.out.println("各装填10000个数据");
        System.out.println("-------------------准备拉链法字典--------------------");
        for (int i = 0; i < 10000; i++) {
            dictionary1.add(String.valueOf(i), String.valueOf(i * 3));
        }

        System.out.println("-------------------准备线性表字典--------------------");

        LinearDictionary<String, String> dictionary2 = new LinearDictionary<>();

        for (int i = 0; i < 10000; i++) {
            dictionary2.add(String.valueOf(i), String.valueOf(i * 3));
        }

        String[] indexNumbers = new String[1024*1024];

        for (int i = 0; i < indexNumbers.length; i++) {
            indexNumbers[i] = String.valueOf(random.nextInt(10000));
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < indexNumbers.length; i++) {
            dictionary1.getValue(indexNumbers[i]);
        }
        long end = System.currentTimeMillis();

        System.out.println("拉链法字典查找时间是: " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < indexNumbers.length; i++) {
            dictionary2.getValue(indexNumbers[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("线性表字典查找时间是: " + (end - start));

    }

}
