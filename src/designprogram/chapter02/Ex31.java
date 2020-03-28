package designprogram.chapter02;

public class Ex31 {

    public static void main(String[] args) {

        Ex2122.println(pipeArea(1, 1, 1));

    }


    // 5美元的基础上, 每调低0.1美元售价, 观众增加15位

    //最后的目标: 利润与票价之间的关系

    //1 明确目的, 需要通过票价计算出利润 创建票价 -> 利润的函数

    //2 例子, 先来几个例子计算一下人数, 先创建人数与票价的关系
    public static double count(double price) {
        return (120 + (5 - price) / 0.1 * 15);
    }


    //2 例子续, 计算给定票价时候的收入, 收入 = 人数*票价

    public static double income(double price) {
        return price * count(price);
    }

    //2 例子续, 计算给定票价时候的成本
    public static double cost(double price) {
        //成本 = 固定成本180+ 每位观众0.04元开销
//        return 180 + count(price) * 0.04;

        //成本等于每个观众场内成本4美分和支付给供片商1.5美元
        return count(price) * 1.54;


    }

    //将上述函数组合起来, 得到price->profit

    public static double profit(double price) {
        return income(price) - cost(price);
    }

    //测试函数在 5 - 1 美元上的所有组合
    //最后可以计算得出, 票价为2.9的时候利润最高


    //习题3.1.4 取消固定成本, 由于我们合理的使用了辅助函数, 因此只需要修改计算成本的过程, 而不需修改程序的其他部分.
    //重新测试可以发现票价为3.7的时候利润最高


    //3.3.2 计算圆柱体的半径和高度
    public static double volume(double radius, double height) {
        double PI = Math.PI;
        return Math.pow(radius, 2) * PI * height;
    }

    //3.3.4 计算圆柱体的表面积
    public static double area(double radius, double height) {
        double PI = Math.PI;
        return Math.pow(radius, 2) * PI * 2 + 2 * PI * radius * height;
    }

    //3.3.5 计算管道的表面积
    //设计为多个函数

    //给定圆柱体的侧面积 不含上下圆形部分面积
    public static double sideArea(double radius, double height) {
        return 2 * Math.PI * radius * height;
    }

    //计算一个半径的圆的面积
    public static double circleArea(double radius) {
        return Math.pow(radius, 2) * Math.PI;
    }

    //计算圆环的面积, 输入是内半径, 厚度
    public static double ringArea(double radius, double thickness) {
        return circleArea(radius + thickness) - circleArea(radius);
    }

    //最后计算管道的面积, 管道的面积等于两个侧面积加上管道的上下两个圆环的面积
    public static double pipeArea(double radius, double thickness, double height) {
        return sideArea(radius, height) + sideArea(radius + thickness, height) + 2 * ringArea(radius, thickness);
    }


    //3.3.5 分拆成若干个函数

    public static double G = 9.8;

    //给定时刻的速度
    public static double speed(double t) {
        return G * t;
    }

    //给定时刻的高度
    public static double height(double t) {
        return 0.5 * speed(t) * t;
    }



}
