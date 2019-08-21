package alog4e.chapter01.section04;

import alog4e.libs.StdOut;

import java.util.Arrays;

public class UF_QUICK_UNION {
    private int N;
    private int[] a;


    UF_QUICK_UNION(int numbers) {
        this.N = numbers;
        a = new int[numbers];
        //初始化数组依然不变, 保持一开始每个节点都彼此独立
        for (int i = 0; i < numbers; i++) {
            a[i] = i;
        }
    }

    //find函数进行了重大修改, 如果索引与值不同, 则将值取出当成索引, 去判断下一个索引位置的值
    //当索引和值相同, 就说明找到了根节点, 返回根节点的值
    int find(int p) {
        while (p != a[p]) {
            p = a[p];
        }
        return p;
    }

    //新的Union算法不再遍历数组, 而是根据p 和 q的根节点来判断

    void union(int p, int q) {
        //找到p和q对应的根节点
        int p_number = find(p);
        int q_number = find(q);

        //如果根节点编号不同, 将p的根节点连到q的根节点
        if (p_number != q_number) {
            a[p_number] = q_number;
            N--;
        }
    }

    //判断两个节点是否连通, 只需要判断根节点的位置
    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int count() {
        return N;
    }

    @Override
    public String toString() {
        return "UF_QUICK_FIND{" +
                "N=" + N +
                ", a=" + Arrays.toString(a) +
                '}';
    }

    public static void main(String[] args) {
        UF_QUICK_UNION uf = new UF_QUICK_UNION(10);
        uf.union(0, 5);
        StdOut.println(uf);
        uf.union(5, 8);
        StdOut.println(uf);
        StdOut.println(uf.connected(0,5));
        StdOut.println(uf.connected(8,5));
        StdOut.println(uf.connected(8, 1));
        StdOut.println(uf.count());

    }
}
