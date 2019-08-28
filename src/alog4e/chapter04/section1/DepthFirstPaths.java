package alog4e.chapter04.section1;

import alog4e.chapter01.section02.stack.Stack;
import alog4e.libs.In;
import alog4e.libs.StdOut;

public class DepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;

    //起点
    private final int s;

    //用数组表示的树
    private int[] edgeTo;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                //w索引的值是v, 对于第一次递归来说, v就是s, 这样就一层一层通过数组可以追查到s
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }


    public boolean hasPathTo(int v) {
        return marked[v];
    }

    //其实有了前边的数组, 从v开始不断循环v = edgeTo[v]就可以找到路径
    //这里是弄了一个方法专门保存了路径的栈
    public Iterable<Integer> pathTo(int v) {
        //如果没有路径, 就结束
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        //用循环不断跟着找下去, 直到i=s为止
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        //循环结束的时候, 所有的除了s之外的路径都被压入栈中, 最后压一个s的值进去
        path.push(s);
        return path;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths search = new DepthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.count() != G.V()) StdOut.println("NOT connected");
        else                         StdOut.println("connected");
    }

}