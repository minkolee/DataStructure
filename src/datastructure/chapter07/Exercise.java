package datastructure.chapter07;

import alog4e.libs.Counter;
import datastructure.chapter5.LinkedListStack;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise {

    //EX01
    public static void EX01DisplayRowOfCharacters(char c, int count) {
        if (count != 0) {
            System.out.print(c);
            EX01DisplayRowOfCharacters(c, count - 1);
        }

    }


    //EX03
    public static void EX03InputNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个数字: ");
        int i = scanner.nextInt();
        if (i < 1 || i > 10) {
            System.out.println("输入错误");
            EX03InputNumber();
        } else {
            System.out.println("输入正确");
        }
    }

    //EX04
    //不是尾递归模式, 但实际调用次数也是10
    public static long EX04Factorial(int n, Counter counter) {
        counter.increment();

        if (n == 0) {
            return 1;
        } else return n * EX04Factorial(n - 1, counter);
    }

    //思路是, 最后一个变量乘以前边的结果, 最后返回这个变量乘以前边的结果即可.
    public static long EX04Factorial02(int n, int start, Counter counter) {
        counter.increment();

        if (n == 0) {
            return start;
        } else {
            start = start * n;
            return EX04Factorial02(n - 1, start, counter);
        }

    }

    //EX05
    //这个方法的思路就是, 先显示最后一位, 再处理前边更短的数组,直到数组长度为0
    //这里复制了数组, 空间占用比较大,  如果还是想使用原数组, 可以额外传一个当前的索引.
    public static void reverseShowArray(int[] array) {
        if (array.length > 0) {
            System.out.println(array[array.length - 1]);
            int[] newArray = new int[array.length - 1];
            System.arraycopy(array, 0, newArray, 0, array.length - 1);
            reverseShowArray(newArray);
        }
    }

    //传索引的方法占用空间比较小, 但是额外有一个参数,这里外层封装一下, 传入数组的长度
    private static void reverseShowArrayWithIndex(int[] array, int length) {
        if (length >= 1) {
            System.out.println(array[length - 1]);
            reverseShowArrayWithIndex(array, length - 1);
        }
    }

    //这个公开函数调用私有静态方法, 直接传入数组的长度
    public static void reverseShowArray2(int[] array) {
        reverseShowArrayWithIndex(array, array.length);
    }

    //EX06
    //先考虑第一个字符, 意思就是先倒过来显示剩下的部分, 最后显示第一个字符, 可以先进递归再显示
    public static void EX06reverseShow(int[] array) {
        if (array.length > 1) {
            int[] newArray = new int[array.length - 1];
            //将老数组从索引1开始的部分, 复制到新数组从0开始的部分, 也就是给下一个次递归调用传入原来数组去掉第一个元素之后的数组
            System.arraycopy(array, 1, newArray, 0, array.length - 1);
            EX06reverseShow(newArray);
        }

        System.out.println(array[0]);
    }

    //EX06的传索引方式
    //index 初始为0, 一直增加到数组尾部结束
    private static void EX06reverseShow(int[] array, int index) {
        //如果索引还在范围内
        if (index <= array.length - 1) {
            //先处理索引到数组末尾的部分, 再处理第一个字符
            EX06reverseShow(array, index + 1);
            System.out.println(array[index]);
        }
    }

    //包装一下
    public static void EX06reverseShow2(int[] array) {
        EX06reverseShow(array, 0);
    }

    //EX07 处理字符串和数组类似, 采取索引方式, 不每次复制字符串了. 思路是先显示最后一个字符, 再处理之前所有的.
    private static void EX07reverseShowString(String s, int length) {
        if (length > 0) {
            System.out.print(s.charAt(length - 1));
            EX07reverseShowString(s, length - 1);
        }
    }

    //EX07 内部包装那个索引函数
    public static void reverseShowString(String s) {
        EX07reverseShowString(s, s.length());
        System.out.println();
        EX07reverseShowString2(s, 0);
    }

    //先处理第一个字符的方法, 即先倒序显示剩下所有的, 再显示第一个字符
    private static void EX07reverseShowString2(String s, int length) {
        if (length <= s.length() - 1) {
            EX07reverseShowString2(s, length + 1);
            System.out.print(s.charAt(length));
        }
    }

    //EX08 思路是编写一个比较字符串两端是否相同的函数, 如果相同, 返回继续比较去掉两端字符串的字符串的结果即可.
    public static boolean isPalindromic(String s) {
        //如果长度为0, 视为true
        if (s.length() == 0) {
            return true;
        //如果字符串长度为1, 视为true
        } else if (s.length() == 1) {
            return true;
        //如果字符串至少长度为2, 比较第一个与最后一个字符串, 如果相同, 返回继续比较去头去尾的字符串的结果
        } else if (s.charAt(0) == s.charAt(s.length() - 1)) {
            System.out.println(s.charAt(0) + " | " + s.charAt(s.length() - 1));
            String s1 = s.substring(1, s.length() - 1);
            System.out.println(s1);
            return isPalindromic(s1);
        //比较第一个与最后一个字符串失败, 就返回false
        } else {
            System.out.println(s.charAt(0) + " | " + s.charAt(s.length() - 1));
            String s1 = s.substring(1, s.length() - 1);
            return false;
        }
    }

    //EX09 练习要求写迭代方法, 更简单了
    public static int fib(int n) {
        //数组长度至少3项, 也就是 n=2 的时候, 再去创建数组. 可见数字的长度为n+1项目
        if (n < 0) {
            throw new RuntimeException("n必须大于0");
        }

        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;

        } else {
            int[] array = new int[n + 1];
            array[0] = 1;
            array[1] = 1;
            for (int i = 2; i < n + 1; i++) {
                array[i] = array[i - 1] + array[i = 2];
            }
            return array[n];
        }
    }

    //EX11 统计节点的思想: 如果链表为null 返回0, 如果不为空, 返回1+ 统计剩下的节点即可, 这个不是尾递归, 是真要有链表那么多的栈空间了. 这就不如迭代好用.
    //这个方法添加到LinkedListStack中


    //EX12 显示一个十进制的整数n, 使用递归, 递归的思路是, 先处理最右边一位数之前的部分, 然后显示最右边一位数. 思路可以直接翻译成代码
    public static void printNumber(int i, int base) {
        //停机条件是i=0,由于i始终是最左边的数字, 所以等于0的时候不做任何操作.
        if (i != 0) {
            //先处理不包含最后一位数的部分
            printNumber(i / base, base);
            //然后显示最后一位数
            System.out.print(i % base);
        }
    }

    //EX13 递归方法是, 在第一个结点中寻找, 然后把结果加上在之后链表中寻找的结果, 思路很简单, 就不写了

    //EX16 用递归返回整数数组中的最小值. 思路是, 递归思想是, 将数组一分为二, 返回这两部分中比较小的值. 直到数组长度为1, 就返回当前数字
    private static int recursionFindMin(int[] array, int first, int last) {
        if (array.length == 0) {
            throw new RuntimeException("数组不能为空");
        }
        //如果数组长度是1, 那就是最小值, 返回即可.
        if (first == last) {
            return array[first];
        } else {
            //分半, 由于我们方法的功能就是找最小值, 所以只需要对两半各调用方法, 然后返回比较小的内容
            int firstHalf = recursionFindMin(array, first, (first + last) / 2);
            int lastHalf = recursionFindMin(array, (first + last) / 2 + 1, last);
            return Math.min(firstHalf, lastHalf);
        }
    }

    //EX16的外部暴露方法
    public static int recursionFindMin(int[] array) {
        return recursionFindMin(array, 0, array.length - 1);
    }

    //EX17 改造一下传入一个栈
    public static int f(int n, LinkedListStack<String> stack) {
        int result = 0;
        if (n < 4) {
            //这里计算完毕一个, 相当于弹出
            stack.pop();
            result = 1;
        } else {
            //这里没有计算完毕, 相当于压两个栈进去
            stack.push("f(" + n / 2 + ")");
            stack.push("f(" + n / 4 + ")");
            result = f(n / 2, stack) + f(n / 4, stack);
        }
        System.out.println(Arrays.toString(stack.toArray()));
        return result;
    }


    //EX18 寻找Comparable数组中第二小的对象. 思路是初始化两个数值, 一个是最小值, 一个是第二小的值, 然后遍历, 如果下一个值比最小的小, 就更新最小和次小. 如果比第二个小但比第一个大, 就更新次小, 如果大于等于第二小, 就不更新
    //迭代的方法会了, 如何改成递归呢. 我们的方法可以在一个数组中找到最小的两个数字, 只要变成先找到最开始的两个数较大的, 然后与后边找到的较大的进行运算.
    //写不出来, 只能想到迭代或者先排序再写, 用递归咋写呢.



    public static void main(String[] args) {
//        EX01DisplayRowOfCharacters('*', 5);
//
//        EX03InputNumber();

//        Counter counter = new Counter("counter");
//        System.out.println(EX04Factorial02(10, 1, counter));
//        System.out.println(counter);

//        int[] array = new int[]{3, 482, 489, 490, 102, 49, 58, 18, 1098};
//        reverseShowArray(array);

//        EX06reverseShow(array);
//        EX06reverseShow2(array);

//        String s = "123456";
//        reverseShowString(s);

//        String s = "a3a3a2a3a";
//        System.out.println(isPalindromic(s));

//        System.out.println(fib(10));

//        printNumber(120984,8);

    }
}
