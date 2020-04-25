package datastructure.lastChapter;

import datastructure.chapter19.Dictionary;
import datastructure.chapter21.ZipDictionary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DirectWeightedGraph<T> implements GraphInterface<T> {

    private Map<T, VertexInterface<T>> vertices;

    private int edgeCount;

    public DirectWeightedGraph() {
        this.vertices = new HashMap<>();
        edgeCount = 0;
    }

    @Override
    public boolean addVertex(T vertexLabel) {
        VertexInterface<T> vertex = new Vertex<>(vertexLabel);

        VertexInterface<T> result = vertices.put(vertexLabel, vertex);

        return result != null;

    }


    @Override
    public boolean addEdge(T start, T end, double weight) {
        //获取两个顶点
        VertexInterface<T> startVertex = vertices.get(start);
        VertexInterface<T> endVertex = vertices.get(end);

        boolean result = false;

        //只有两个顶点都存在的情况下, 才能添加边
        if (startVertex != null && endVertex != null) {
            //添加边是否成功保存在result中
            result = startVertex.connect(endVertex, weight);
            //如果添加成功就增加边的计数
            if (result) {
                edgeCount++;
            }

        }

        return result;
    }

    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0);
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        boolean result = false;

        VertexInterface<T> startVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        //两个顶点都存在才能进行后续
        if (startVertex != null && endVertex != null) {

            //遍历相邻的点寻找是否有边
            Iterator<VertexInterface<T>> vertexIterator = startVertex.getNeighborIterator();

            while (vertexIterator.hasNext()) {
                if (vertexIterator.next().equals(endVertex)) {
                    result = true;
                    break;
                }
            }
        }

        return result;

    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public int getNumberOfVertices() {
        return vertices.size();
    }

    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }

    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }

    @Override
    public void resetVertices() {
        for (VertexInterface<T> vertex : vertices.values()) {
            vertex.unvisit();
            vertex.setCost(0);
            vertex.setPredecessor(null);
        }
    }

    @Override
    public String toString() {
        return "DirectWeightedGraph{" +
                "vertices=" + vertices +
                ", edgeCount=" + edgeCount +
                '}';
    }

    public void showAll() {
        for (VertexInterface<T> vertex : vertices.values()) {
            System.out.println(vertex);
        }
    }


}




