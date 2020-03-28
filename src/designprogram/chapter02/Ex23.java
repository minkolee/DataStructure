package designprogram.chapter02;

public class Ex23 {

    public static void main(String[] args) {
        Ex2122.println(income(50));
        Ex2122.println(netIncome(income(50)));

        Ex2122.println(sumCoins(1, 3, 4, 5));
        Ex2122.println(totalProfit(3));
        Ex2122.println(totalProfit(4));
        Ex2122.println(totalProfit(5));
    }

    //2.3.1 计算税前收入, 税, 税后收入的函数, 可以组合完成功能

    public static double income(double hour) {
        return hour * 12;
    }

    public static double tax(double income) {
        return 0.15 * income;
    }

    public static double netIncome(double income) {
        return income - tax(income);
    }

    //2.3.2 计算硬币价值
    public static double sumCoins(int cent, int nickel, int dime, int quarter) {
        return cent + 5 * nickel + 10 * dime + 25 * quarter;
    }

    //2.3.3 计算电影院的净利润
    public static double totalProfit(int count) {
        return 5 * count - 20 - 0.5 * count;
    }





}
