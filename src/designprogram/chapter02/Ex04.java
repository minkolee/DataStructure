package designprogram.chapter02;

import java.util.function.Function;

public class Ex04 {

    public static void main(String[] args) {

        Ex2122.println(netPay(25));
        Ex2122.println(255);
    }

    public boolean judge1(double i) {
        return i > 3 && i < 7;
    }

    public boolean judge2(double i) {
        return i >= 3 && i <= 7;
    }

    public boolean judge3(double i) {
        return i >= 3 && i < 7;
    }

    //第四个, 好的方法是编写复核函数

    public boolean judge41(double i) {
        return i > 1 && i < 3;
    }

    public boolean judge42(double i) {
        return i > 9 && i < 11;
    }

    public boolean judge4(double i) {
        return judge41(i) || judge42(i);
    }

    public boolean judge5(double i) {
        return i < 1 || i > 3;
    }


    //4.2.3 验证一个数是不是某个方程的解 ,只需要将其代入然后进行判断即可
    //这里使用函数式编程, 传入一个解和一个函数, 判断是不是等于0即可.
    public static boolean isSolution(double x, Function<Double, Double> function) {
        return function.apply(x) == 0;
    }

    //4.4.1 返回收益的函数
    public static double rate(double deposit) {
        if (deposit <= 1000) {
            return 0.04;
        } else if (deposit <= 5000) {
            return 0.045;
        } else return 0.05;
    }

    public static double interest(double deposit) {
        return deposit * rate(deposit);
    }


    // 4.4.2 工作时间 计算净收入
    public static double WAGEPERHOUR = 12;

    public static double grossIncome(int hour) {
        return hour * WAGEPERHOUR;
    }

    public static double taxRate(double grossIncome) {
        if (grossIncome <= 240) {
            return 0;
        } else if (grossIncome <= 480) {
            return 0.15;
        } else {
            return 0.28;
        }
    }

    public static double tax(double grossIncome) {
        return grossIncome * taxRate(grossIncome);
    }

    public static double netPay(int hour) {
        double grossIncome = grossIncome(hour);

        return grossIncome - tax(grossIncome);

    }



}
