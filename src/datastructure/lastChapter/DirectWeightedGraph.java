package datastructure.lastChapter;

import datastructure.chapter10.ArrayQueue;
import datastructure.chapter10.QueueInterface;
import datastructure.chapter19.Dictionary;
import datastructure.chapter21.ZipDictionary;
import datastructure.chapter5.LinkedListStack;
import datastructure.chapter5.Stack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class DirectWeightedGraph<T> implements GraphInterface<T>, GraphAlgorithms<T> {

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


    //广度优先遍历
    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {

        //第一个队列, 用于存放顶点
        QueueInterface<VertexInterface<T>> temp = new ArrayQueue<>();
        //第二个队列, 用于返回队列标识给客户, 不能直接返回VertexInterface类.
        QueueInterface<T> result = new ArrayQueue<>();

        //获取初始顶点
        VertexInterface<T> start = vertices.get(origin);
        //标记为已访问
        start.isVisited();
        //放入队列
        temp.enqueue(start);

        //开始弹出队列, 只要不为空就反复弹, 每次弹的时候将T放入result, 然后将弹出的顶点的所有unvisited的邻接点放入temp队列中.
        while (!temp.isEmpty()) {
            //从队列中弹出一个顶点
            VertexInterface<T> currentVertex = temp.dequeue();

            Iterator<VertexInterface<T>> neighbors = currentVertex.getNeighborIterator();

            while (neighbors.hasNext()) {
                VertexInterface<T> aNeighbor = neighbors.next();
                if (!aNeighbor.isVisited()) {
                    aNeighbor.visit();
                    temp.enqueue(aNeighbor);
                }
            }

            result.enqueue(currentVertex.getLabel());

        }

        //得到了结果之后, 重置图状态
        resetVertices();

        //当temp弹完的时候, result里应该都进去了, 因为这个循环是每次从temp中拿一个, 再放入result中一个.
        return result;

    }

    //深度优先遍历
    @Override
    public QueueInterface<T> getDepthFirstTraversal(T origin) {

        //第一个栈, 用于存放顶点
        Stack<VertexInterface<T>> temp = new LinkedListStack<>();

        //第二个队列, 用于返回结果, 每次入栈的时候, 把那个顶点标签放入到result中.
        QueueInterface<T> result = new ArrayQueue<>();

        //先把起点的标签放入结果队列中
        result.enqueue(origin);

        //获取初始顶点
        VertexInterface<T> start = vertices.get(origin);
        //标记为已访问
        start.isVisited();
        //放入栈中
        temp.push(start);

        //只要栈不空
        while (!temp.isEmpty()) {
            //寻找栈顶的顶点
            VertexInterface<T> currentVertex = temp.peek();

            //将所有未访问的点标记为已访问然后入栈, 每入栈一个, 在result中进行记录.
            //如果全部都已经访问, 则需要弹出当前点
            Iterator<VertexInterface<T>> neighbors = currentVertex.getNeighborIterator();

            boolean allVisited = true;

            while (neighbors.hasNext()) {
                VertexInterface<T> aNeighbor = neighbors.next();

                //注意这里, 只要放入一个就足够了, 然后立刻break
                if (!aNeighbor.isVisited()) {
                    allVisited = false;
                    aNeighbor.visit();
                    temp.push(aNeighbor);
                    result.enqueue(aNeighbor.getLabel());
                    break;
                }
            }

            //如果循环跑完了一个都没放进去, 则弹出当前顶点
            if (allVisited) {
                temp.pop();
            }
        }



        //栈空之后, 所有的顶点及其邻居都进来过了, 返回 result即可
        resetVertices();

        return result;

    }

    @Override
    public Stack<T> getTopologicalOrder() {
        return null;
    }

    //获取最短路径长度和路径栈
    @Override
    public int getShortestPath(T begin, T end, Stack<T> path) {

        //只需要用到一个队列
        QueueInterface<VertexInterface<T>> temp = new ArrayQueue<>();

        //获取初始顶点
        VertexInterface<T> start = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        //进行初始判断, 是不是相同. 相同就往栈中加入开始节点的标签, 然后结束
        if (start.equals(endVertex)) {
            path.push(endVertex.getLabel());
            resetVertices();
            return 0;
        }

        //标记为已访问
        start.isVisited();
        //这里将距离当成cost, 起点相距起点自然是0
        start.setCost(0);

        //把起点放入队列
        temp.enqueue(start);

        //开始进行广度优先搜索
        while (!temp.isEmpty()) {
            //从队列中弹出一个顶点
            VertexInterface<T> currentVertex = temp.dequeue();

            //有了前边的判断, 顶点一定不是要找的点, 就开始找邻居
            Iterator<VertexInterface<T>> neighbors = currentVertex.getNeighborIterator();

            //对于每个邻居顶点, 如果就是目标, 则要填充栈并返回, 如果不是, 则要设置前驱节点然后增加路径长度
            while (neighbors.hasNext()) {
                VertexInterface<T> aNeighbor = neighbors.next();

                //这里进行判断, 如果某个邻居节点就是要找的点, 直接返回邻居的路径长度+1即可
                //还需要在栈中装载整条路径, 这个类似链表, 不断把节点标签写入即可.
                if (aNeighbor.equals(endVertex)) {
                    int length = (int) (currentVertex.getCost() + 1);
                    path.push(aNeighbor.getLabel());
                    while (currentVertex != null) {
                        path.push(currentVertex.getLabel());
                        currentVertex = currentVertex.getPredecessor();
                    }

                    resetVertices();

                    return length;
                }

                //如果不是要找到的点, 需要额外添加一些步骤
                if (!aNeighbor.isVisited()) {
                    aNeighbor.visit();

                    //这里需要增加一些步骤:

                    //设置新入队的顶点的前驱节点是刚弹出队的顶点
                    aNeighbor.setPredecessor(currentVertex);

                    //设置新入队的顶点的距离是前驱节点+1
                    aNeighbor.setCost(currentVertex.getCost() + 1);

                    temp.enqueue(aNeighbor);
                }
            }
        }

        //能走到这一步, 说明整个遍历过程都没有找到, 重置状态然后返回-1即可
        resetVertices();

        return -1;
    }

    //最优路线 - Dijkstra算法
    @Override
    public double getCheapestPath(T begin, T end, Stack<T> path) {
        //创建一个优先队列
        PriorityQueue<Entry> entries = new PriorityQueue<>();

        //先获取起点, 然后包装一个类扔进优先队列
        VertexInterface<T> startVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        Entry firstEntry = new Entry(startVertex, 0, null);
        entries.add(firstEntry);

        //寻找标志
        boolean found = false;

        //只要还没找到并且entries不为空
        while (!found && !entries.isEmpty()) {

            //从优先队列里弹出一项
            Entry anEntry = entries.remove();
            System.out.println("队列中弹出的是: " + anEntry);
            //如果这一项其中的顶点还没有被访问过
            if (!anEntry.currentVertext.isVisited()) {

                VertexInterface<T> currentVertex = anEntry.currentVertext;

                //就去访问那个顶点, 设置权重和前驱节点为Entry中的相应数据
                currentVertex.visit();
                currentVertex.setCost(anEntry.totalWeight);
                currentVertex.setPredecessor(anEntry.predecessor);


                //如果优先队列里弹出来的就是目标,直接结束循环
                if (currentVertex.equals(endVertex)) {

                    found = true;
                } else {
                    //遍历这个顶点的所有邻居, 如果被访问过, 就忽略, 如果没有访问过, 需要计算自己的cost加边长, 然后包装一个新的对象扔进去
                    Iterator<VertexInterface<T>> neighbors = currentVertex.getNeighborIterator();
                    while (neighbors.hasNext()) {
                        VertexInterface<T> anNeighbor = neighbors.next();

                        if (!anNeighbor.isVisited()) {
                            //获取权重
                            double totalWeight = currentVertex.getWeightToVertex(anNeighbor) + currentVertex.getCost();
                            //将邻居节点, 到这个邻居节点的总权重, 前驱节点=当前节点包装对象扔进优先队列.
                            Entry newEntry = new Entry(anNeighbor, totalWeight, currentVertex);
                            System.out.println("放入队列的是: " + newEntry);
                            entries.add(newEntry);
                        }

                    }
                }

            }

        }

        //运行到这里检测是否寻找到
        if (found) {
            double weight = endVertex.getCost();
            while (endVertex != null) {
                path.push(endVertex.getLabel());
                endVertex = endVertex.getPredecessor();
            }
            return weight;
        } else {
            return -1;
        }

    }

    private class Entry implements Comparable<Entry> {
        private VertexInterface<T> currentVertext;
        private double totalWeight;
        private VertexInterface<T> predecessor;

        public Entry(VertexInterface<T> currentVertext, double totalWeight, VertexInterface<T> predecessor) {
            this.currentVertext = currentVertext;
            this.totalWeight = totalWeight;
            this.predecessor = predecessor;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "currentVertext=" + currentVertext +
                    ", totalWeight=" + totalWeight +
                    ", predecessor=" + predecessor +
                    '}';
        }

        //这个方法很关键, Java的PriorityQueue默认是优先移除小的, 即按照升序排列
        @Override
        public int compareTo(Entry entry) {
            return (int) (this.totalWeight - entry.totalWeight);
        }
    }



}




