package alog4e.chapter01.section04;

import alog4e.libs.StdOut;

import java.util.Arrays;

public class UF_QUICK_FIND {
    private int N;
    private int[] a;


    UF_QUICK_FIND(int numbers) {
        this.N = numbers;
        a = new int[numbers];
        //初始化数组, 值 = 索引
        for (int i = 0; i < numbers; i++) {
            a[i] = i;
        }
    }


    void union(int p, int q) {
        //两个触点所在的连通分量的编号不同, 将所有与a[p]相同的值设置为a[q]
        if (a[p] != a[q]) {
            int p_number = find(p);
            int q_number = find(q);
            for (int i = 0; i < a.length; i++) {
                if (a[i] == p_number) {
                    a[i] = q_number;
                }
            }
            //成功连通一组, 需要N减去1
            N--;
        }
    }

    //查找分量很简单, 直接返回值
    int find(int p) {
        return a[p];
    }

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
        UF_QUICK_FIND uf = new UF_QUICK_FIND(10);
        StdOut.println(uf.find(0));
        StdOut.println(uf.find(9));
        StdOut.println(uf.find(5));
        uf.union(3, 5);
        uf.union(4, 9);
        StdOut.println(uf);
        uf.union(4, 5);
        StdOut.println(uf);
        StdOut.println(uf.connected(4,5));
        StdOut.println(uf.connected(4,9));
        StdOut.println(uf.connected(3,9));
        StdOut.println(uf.connected(2,9));
        StdOut.println(uf.count());
        uf.union(1, 3);
        uf.union(2, 9);
        StdOut.println(uf);
        StdOut.println(uf.count());
        uf.union(4, 7);
        uf.union(7, 6);
        uf.union(4, 3);
        uf.union(4, 3);
        uf.union(0, 9);
        uf.union(9, 8);
        uf.union(9, 8);
        StdOut.println(uf);
        StdOut.println(uf.count());

    }
}
