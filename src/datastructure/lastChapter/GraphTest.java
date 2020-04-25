package datastructure.lastChapter;

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

        System.out.println(graph.getNumberOfEdges());
        System.out.println(graph.getNumberOfVertices());
        graph.showAll();
        System.out.println();


        //添加边
        graph.addEdge('A', 'B', 3);
        graph.addEdge('B', 'C', 2);
        graph.addEdge('B', 'D', 6);
        graph.addEdge('D', 'E', 1);
        graph.addEdge('E', 'F', 4);
        graph.addEdge('A', 'F', 7);

        System.out.println(graph.getNumberOfEdges());
        System.out.println(graph.getNumberOfVertices());
        graph.showAll();





    }

}
