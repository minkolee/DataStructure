package datastructure.lastChapter;

import datastructure.chapter10.QueueInterface;
import datastructure.chapter5.Stack;

public interface GraphAlgorithms<T> {

    QueueInterface<T> getBreadthFirstTraversal(T origin);

    QueueInterface<T> getDepthFirstTraversal(T origin);

    Stack<T> getTopologicalOrder();

    int getShortestPath(T begin, T end, Stack<T> path);

    double getCheapestPath(T begin, T end, Stack<T> path);

}
