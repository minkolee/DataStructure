package datastructure.chapter07.project;


import datastructure.chapter5.LinkedListStack;
import datastructure.chapter5.Stack;

import java.util.Arrays;
import java.util.Random;

/**
 * 先写一个迭代的方法吧, 思路还是比较明确的
 * 这也是一个排序的方法啦, 双栈排序
 */
public class P03 {

    public static Stack<Integer> sort(Stack<Integer> stack) {

        Stack<Integer> s2 = new LinkedListStack<>();
        Stack<Integer> s3 = new LinkedListStack<>();

        //栈不空, 就对其中的每一个元素, 先弹出, 然后放入合理的s2的位置, 再把原来的s3的东西压进去
        while (!stack.isEmpty()) {
            int element = stack.pop();

            boolean isPushed = false;

            //只要还没有把这个东西压入s2中, 就不断循环
            while (!isPushed) {
                //如果s2为空, 压入, 然后改变变量
                if (s2.isEmpty()) {
                    s2.push(element);
                    isPushed = true;
                //如果s2不为空, 由于s2栈顶最小, 因此如果小于栈顶才能放入,否则一直需要弹出栈项
                }else{
                    //如果元素小于s2顶, 则直接压入即可
                    if (element < s2.peek()) {
                        s2.push(element);
                        isPushed = true;
                    //如果元素不小于s2顶, 需要弹到s3中, 然后继续循环看是否为空或者比较下一项
                    } else {
                        s3.push(s2.pop());
                    }
                }
            }

            //一个元素被压入之后, 还需要将s3的全部元素再压回来
            while (!s3.isEmpty()) {
                s2.push(s3.pop());
            }

        }

        //执行到这里说明s1已经为空, 循环结束之后返回s2. s3已经被弹空
        return s2;
    }

    //想了一下思路, 递归的思路是, 每次从s1排一个元素到s2中, 只要函数能够完成使用三个栈把s1中一个元素放到s2合理的位置, 那么可以继续反复递归.
    public static void insertOneElementFromStartToTarget(Stack<Integer> start, Stack<Integer> target, Stack<Integer> transit) {
        if (!start.isEmpty()) {
            int element = start.pop();
            boolean isPushed = false;
            //只要还没有把这个东西压入s2中, 就不断循环
            while (!isPushed) {
                //如果s2为空, 压入, 然后改变变量
                if (target.isEmpty()) {
                    target.push(element);
                    isPushed = true;
                    //如果s2不为空, 由于s2栈顶最小, 因此如果小于栈顶才能放入,否则一直需要弹出栈项
                }else{
                    //如果元素小于s2顶, 则直接压入即可
                    if (element < target.peek()) {
                        target.push(element);
                        isPushed = true;
                        //如果元素不小于s2顶, 需要弹到s3中, 然后继续循环看是否为空或者比较下一项
                    } else {
                        transit.push(target.pop());
                    }
                }
            }

            //一个元素被压入之后, 还需要将s3的全部元素再压回来
            while (!transit.isEmpty()) {
                target.push(transit.pop());
            }

            //然后继续对此时的栈重复这个操作
            insertOneElementFromStartToTarget(start, target, transit);
        }
    }



    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedListStack<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            stack.push(random.nextInt(200));
        }

        Stack<Integer> target = new LinkedListStack<>();
        Stack<Integer> transit = new LinkedListStack<>();

        System.out.println(Arrays.toString(stack.toArray()));

        insertOneElementFromStartToTarget(stack, target, transit);

        System.out.println(Arrays.toString(target.toArray()));

    }
}
