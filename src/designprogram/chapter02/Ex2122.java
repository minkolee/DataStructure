package designprogram.chapter02;


import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Ex2122 {

//  2.1.1  查明具备平方, 计算正弦和确定两个数的最大值的运算

    public static void print(Object a) {
        System.out.print(a);
    }

    public static void println(Object a) {
        System.out.println(a);
    }

    public static void main(String[] args) throws IOException {

        println(areaOfTriangle(3, 5));

        println(convert3(3, 4, 5));

        println(func1(2));
        println(func1(9));
        println(func2(2));
        println(func2(9));
        println(func3(2));
        println(func3(9));
    }

// 2.1.2 计算平方

    public static void ex212() {
        println(Math.sqrt(4));
        println(Math.sqrt(2));
        println(Math.sqrt(-1));
    }


    // 2.2.1 定义华氏温度转换摄氏温度的函数, 从文件中读入然后打印结果
    //华氏度转换成摄氏度时，可以使用C = 5/9(F - 32)公式。 在将摄氏度转换成华氏度时，使用公式9/5C = F - 32
    public static double fahrenheitToCelsius(double f) {
        return 5.0 / 9.0 * (f - 32);
    }

//    返回scanner对象的打开文件的通用函数
    public static Scanner openFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        return new Scanner(reader);
    }

    //向文件中写入读出的全部数据经过计算后组成的新文件
    public static void writeResult(Scanner scanner, String targetFileName) throws IOException {
        File file = new File(targetFileName);

        DecimalFormat format = new DecimalFormat("#.00");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        while (scanner.hasNextDouble()) {
            bufferedWriter.write(format.format(fahrenheitToCelsius(scanner.nextDouble())));

            bufferedWriter.write("\n");
        }

        bufferedWriter.close();
    }

    //2.2.2 定义汇率转换
    public static double dollarToEuro(double dollar) {
        return dollar * 0.90;
    }

    //2.2.3 通过底和高计算三角形面积
    public static double areaOfTriangle(double bottom, double height) {
        return bottom * height / 2;
    }

    //2.2.4 输入为三个数, 分别代表一个数的个位, 十位, 百位上的数
    public static int convert3(int a, int b, int c) {
        if (a > 9 || a < 0 || b > 9 || b < 0 || c > 9 || c < 0) {
            throw new IllegalArgumentException("参数只能是0-9的整数");
        }

        return a + b * 10 + c * 100;
    }

    //2.2.5 组合函数

    public static double func1(double n) {
        return Math.pow(n, 2) + 10;
    }

    public static double func2(double n) {
        return 0.5 * Math.pow(n, 2) + 20;
    }

    public static double func3(double n) {
        return 2 - 1 / n;
    }

}
