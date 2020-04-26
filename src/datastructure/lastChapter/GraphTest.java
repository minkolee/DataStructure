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
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');
        graph.addVertex('H');
        graph.addVertex('I');

        System.out.println(graph.getNumberOfEdges());
        System.out.println(graph.getNumberOfVertices());
        graph.showAll();
        System.out.println();


        //添加边
        graph.addEdge('A', 'B', 2);
        graph.addEdge('A', 'D', 5);
        graph.addEdge('A', 'E', 4);

        graph.addEdge('D', 'G', 2);

        graph.addEdge('G', 'H', 1);

        graph.addEdge('H', 'I', 1);

        graph.addEdge('I', 'F', 1);

        graph.addEdge('F', 'H', 3);
        graph.addEdge('F', 'C', 4);

        graph.addEdge('B', 'E', 1);
        graph.addEdge('C', 'B', 3);

        graph.addEdge('E', 'F', 3);
        graph.addEdge('E', 'H', 6);


        System.out.println(graph.getNumberOfEdges());
        System.out.println(graph.getNumberOfVertices());
        graph.showAll();

        Stack<Character> path = new LinkedListStack<>();

        System.out.println(graph.getCheapestPath('A', 'C', path));

        System.out.println(Arrays.toString(path.toArray()));



    }

}
