package datastructure.lastChapter;

public interface GraphInterface<T> {

    //向图中添加一个标识为T的顶点
    boolean addVertex(T vertexLabel);

    //向图中添加一条从start到end的边, 带权
    boolean addEdge(T start, T end, double weight);

    //添加一条无权边
    boolean addEdge(T begin, T end);

    //检测从begin到end是否有一条边相连
    boolean hasEdge(T begin, T end);

    //是否图为空
    boolean isEmpty();

    //图的总顶点数
    int getNumberOfVertices();

    //图的总边数
    int getNumberOfEdges();

    //清空图
    void clear();

    //重置所有顶点状态
    void resetVertices();
}
