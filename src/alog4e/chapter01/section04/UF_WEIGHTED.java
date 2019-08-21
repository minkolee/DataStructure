package alog4e.chapter01.section04;

import alog4e.libs.StdOut;

import java.util.Arrays;

public class UF_WEIGHTED {
    private int N;
    private int[] a;
    private int[] size;


    UF_WEIGHTED(int numbers) {
        this.N = numbers;
        a = new int[numbers];
        size = new int[numbers];
        //初始化数组之外, 再初始化一个数组记录各个元素作为根节点的长度.
        for (int i = 0; i < numbers; i++) {
            a[i] = i;
            size[i] = 1;
        }
    }

    //find函数无需修改, 因为按我们的设计, 这个函数的查找非常快, 是2的对数级别
    int find(int p) {
        while (p != a[p]) {
            p = a[p];
        }
        return p;
    }

    //新的Union算法在连通的时候, 判断两颗树的大小, 然后去把小数的长度加到大树上

    void union(int p, int q) {
        //找到p和q对应的根节点
        int p_number = find(p);
        int q_number = find(q);

        if (p_number == q_number) {
            return;
        }

        //如果p_number 是大树的根节点, q_number是小树的根节点
        if (size[p_number] > size[q_number]) {
            //小树连到大树上
            a[q_number] = p_number;
            //小树的长度需要添加到大树上
            size[p_number] += size[q_number];
        //p_number是小树, q_number是大树
        } else {
            //依然是小树连到大树上
            a[p_number] = q_number;
            //小树长度添加到大树上
            size[q_number] += size[p_number];
        }
        N--;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int count() {
        return N;
    }

    @Override
    public String toString() {
        return "UF_WEIGHTED{" +
                "N=" + N +
                ", a=" + Arrays.toString(a) +
                ", size=" + Arrays.toString(size) +
                '}';
    }

    public static void main(String[] args) {
        UF_WEIGHTED uf = new UF_WEIGHTED(10);
        uf.union(0, 1);
        StdOut.println(uf);

        uf.union(2, 3);
        StdOut.println(uf);

        uf.union(3, 4);
        StdOut.println(uf);

        uf.union(3, 0);
        StdOut.println(uf);

        uf.union(5, 8);
        StdOut.println(uf);
        StdOut.println(uf.count());

    }
}
