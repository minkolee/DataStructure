package datastructure.lastChapter;

import datastructure.chapter12.MyLinkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

class Vertex<T> implements VertexInterface<T> {

    //存放T类型数据的域
    private T label;

    //布尔值
    private boolean visited;

    //存放相连的顶点(边)的集合, 使用了之前自行编写的带迭代器的链表
    private MyLinkedList<Edge> edgeList;

    //前驱顶点
    private VertexInterface<T> previousVertex;

    //cost
    private double cost;

    //Edge内部类, 仅仅使用构造器创建对象, 是一个不可变对象
    private class Edge {
        private VertexInterface<T> vertex;
        private double weight;

        protected Edge(VertexInterface<T> vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        protected VertexInterface<T> getEndVertex() {
            return vertex;
        }

        protected double getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "vertex=" + vertex.getLabel() +
                    ", weight=" + weight +
                    '}';
        }
    }

    //创建一个新顶点的构造器
    public Vertex(T vertexLabel) {
        this.label = vertexLabel;
        this.edgeList = new MyLinkedList<>();
        this.visited = false;
        this.previousVertex = null;
        this.cost = 0;
    }

//以下是connect方法-----------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;

        //判断要连接的顶点不是自己
        if (!this.equals(endVertex)) {

            //再通过遍历邻接的顶点对象判断顶点是否已经连接

            boolean connected = false;

            for (Edge edge : this.edgeList) {
                if (edge.vertex.equals(endVertex)) {
                    connected = true;
                    break;
                }
            }

            //没有连接过, 创建新Edge对象加入邻接表, 同时设置结果为true
            if (!connected) {
                this.edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    }

    //以下是迭代器方法-----------------------------------------------------------------------------------------------------------------------
    private class NeighborIterator implements Iterator<VertexInterface<T>> {
        private Iterator<Edge> edges;

        NeighborIterator() {
            this.edges = edgeList.iterator();
        }

        @Override
        public boolean hasNext() {
            return edges.hasNext();
        }

        @Override
        public VertexInterface<T> next() {

            if (edges.hasNext()) {
                return edges.next().vertex;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    private class CostIterator implements Iterator<Double> {
        private Iterator<Edge> edges;

        CostIterator() {
            this.edges = edgeList.iterator();
        }

        @Override
        public boolean hasNext() {
            return edges.hasNext();
        }

        @Override
        public Double next() {
            if (edges.hasNext()) {
                return edges.next().vertex.getCost();
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }

    @Override
    public Iterator<Double> getWeightIterator() {
        return new CostIterator();
    }

    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();

        while (neighbors.hasNext()) {
            if (!neighbors.next().isVisited()) {
                result = neighbors.next();
                break;
            }
        }

        return result;
    }

//其他方法 hash and equals-----------------------------------------------------------------------------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(label, vertex.label);

    }

    @Override
    public int hashCode() {
        return Objects.hash(label, visited, edgeList, previousVertex, cost);
    }

//其他方法 label and visited-----------------------------------------------------------------------------------------------------------------------

    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public void visit() {
        this.visited = true;
    }

    @Override
    public void unvisit() {
        this.visited = false;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

//其他方法 hasNeighbor 前驱顶点  cost-----------------------------------------------------------------------------------------------------------------------

    //是否有邻居, 只要检查链表是不是为空, 为空就是没有邻居
    @Override
    public boolean hasNeighbor() {
        return !edgeList.isEmpty();
    }

    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }

    @Override
    public VertexInterface<T> getPredecessor() {
        return previousVertex;
    }

    @Override
    public boolean hasPredecessor() {
        return this.previousVertex != null;
    }

    @Override
    public void setCost(double newCost) {
        this.cost = newCost;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label=" + label +
                ", visited=" + visited +
                ", edgeList=" + Arrays.toString(edgeList.toArray())  +
                ", previousVertex=" + previousVertex +
                '}';
    }
}
