package datastructure.lastChapter;

import datastructure.chapter10.QueueInterface;
import datastructure.chapter5.LinkedListStack;
import datastructure.chapter5.Stack;

import java.util.Arrays;
import java.util.PriorityQueue;

public class GraphTest {

    public static void main(String[] args) {

        //创建新图
        DirectWeightedGraph<Character> graph = new DirectWeightedGraph<>();

        //添加6个顶点
        graph.addVertex('1');
        graph.addVertex('3');
        graph.addVertex('4');
        graph.addVertex('5');
        graph.addVertex('2');
        graph.addVertex('8');
        graph.addVertex('9');
        graph.addVertex('A');
        graph.addVertex('6');
        graph.addVertex('7');

        System.out.println(graph.getNumberOfEdges());
        System.out.println(graph.getNumberOfVertices());
        graph.showAll();
        System.out.println();


        //添加边
        graph.addEdge('1', '2');

        graph.addEdge('6', '8');
        graph.addEdge('2', '5');
        graph.addEdge('2', '4');
        graph.addEdge('2', '3');
        graph.addEdge('5', 'A');

        graph.addEdge('7', '8');
        graph.addEdge('9', 'A');
        graph.addEdge('4', '7');
        graph.addEdge('4', '6');
        graph.addEdge('7', '9');
        graph.showAll();

        Stack<Character> s1 = new LinkedListStack<>();

        graph.getShortestPath('1', 'A', s1);
        System.out.println(Arrays.toString(s1.toArray()));

        s1.clear();

        graph.getCheapestPath('1', 'A', s1);

        System.out.println(Arrays.toString(s1.toArray()));


        Stack<Character> topo = graph.getTopologicalOrder();

        System.out.println(Arrays.toString(topo.toArray()));

        topo = graph.getTopologicalOrder();

        System.out.println(Arrays.toString(topo.toArray()));

    }

}
