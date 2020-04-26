package datastructure.lastChapter;

import java.util.Iterator;

public interface VertexInterface<T> {

    //返回这个顶点的label, 这个根据具体的数据, 可以是一个标识符, 也可以是这个顶点存放的数据.
    T getLabel();

    //标识该订单已经被访问过
    void visit();

    //取消该顶点的访问标记
    void unvisit();

    //很显然, 搭配前两个方法, 可以实现一些路径相关的查找, 内部需要有一个布尔值存放访问状态
    boolean isVisited();

    //尝试将当前顶点连接到另外一个顶点endVertex, 这条边的权重为edgeWeight
    //如果endVertex是当前顶点, 或者两个顶点之间已经有边, 则返回false.
    //如果能够连接, 则返回true
    //注意, 如果是无向图, 这个连接应该是相互的, 即连接之后, endVertex也连接到当前点.
    //如果是有向图, 则这个方法仅仅表示当前顶点有一条连接到endVertex的边, endVertex可不一定会连接到当前顶点
    boolean connect(VertexInterface<T> endVertex, double edgeWeight);

    //这是上一个版本的无权图重载
    boolean connect(VertexInterface<T> endVertex);

    //获取当前点所有的邻接点组成的迭代器, 所以很显然, 我们得有一个内部数据结构用来存放邻接点
    Iterator<VertexInterface<T>> getNeighborIterator();

    //获取由权重组成的迭代器, 用于有权图计算
    Iterator<Double> getWeightIterator();

    //有至少邻接点吗?
    boolean hasNeighbor();

    //返回一个未访问的邻接点, 如果全部邻接点都访问过了, 返回null.
    //很显然这个方法也是为了寻路等操作
    VertexInterface<T> getUnvisitedNeighbor();

    //设置当前节点的前驱节点, 很显然也是用于寻路. 在有向图中寻路, 可以通过这个找到路径中指向当前顶点的点
    void setPredecessor(VertexInterface<T> predecessor);

    //获取前驱节点, 没有就返回null
    VertexInterface<T> getPredecessor();

    //是否含有前驱节点, 这三个方法配合起来使用, 很显然, 内部又得有一个数据结构存放前驱顶点
    boolean hasPredecessor();

    //设置顶点的数据
    void setCost(double newCost);

    //获取顶点的数据
    double getCost();

    //获取当前顶点到指定点的权重
    double getWeightToVertex(VertexInterface<T> target);
}