package datastructure.chapter07.project;

import alog4e.libs.Counter;

public class P02 {

    //汉诺塔的套路就是: 三个柱子是起始柱, 中转柱, 目标柱, 对应三个字符串参数, 这三个参数的意义是固定的
    //将n个盘子从起始柱移动到目标柱的方法是:
    // 1 将n-1个盘子从起始柱移动到中转柱(此时起到中转的作用的柱子是目标住,而目标柱是原来的中转柱)
    // 2 将1个盘子也就是最大的盘子从起始柱移动到目标柱
    // 3 将n-1个盘子从中转柱移动到目标柱(此时起始柱是原来的中转柱, 目标柱不变, 起到中转作用的柱子是原来的起始柱)
    public static void Hanoi(int n, String start, String temp, String target) {
        if (n == 1) {
            System.out.println(start + " => " + target);
        } else {
            //对应步骤1 三个参数的顺序是固定的起始, 中转, 目标, 移动n-1的时候三个柱子的作用发生了变化
            Hanoi(n - 1, start, target, temp);
            // 对应步骤2, 从第一个参数柱子移动到第三个参数柱子
            System.out.println(start + " => " + target);
            //对应步骤3, 此时三个柱子的作用又发生了变化, 因此参数改变了.
            Hanoi(n - 1, temp, start, target);
        }
    }

    //带计数的版本
    public static void Hanoi(int n, String start, String temp, String target, Counter counter) {
        counter.increment();
        if (n == 1) {
            System.out.println(start + " => " + target);
        } else {
            //对应步骤1 三个参数的顺序是固定的起始, 中转, 目标, 移动n-1的时候三个柱子的作用发生了变化
            Hanoi(n - 1, start, target, temp, counter);
            // 对应步骤2, 从第一个参数柱子移动到第三个参数柱子
            System.out.println(start + " => " + target);
            //对应步骤3, 此时三个柱子的作用又发生了变化, 因此参数改变了.
            Hanoi(n - 1, temp, start, target, counter);
        }
    }


    public static void main(String[] args) {

        Counter counter = new Counter("counter");

        Hanoi(5, "A", "B", "C");

    }
}
