package main;

import java.util.List;

public class GraphNode {
    /*индекс вершины, нумерация с единицы*/
    int key;

    /*список смежности реализован на АВЛ-дерево.*/
    TreeSet adjacencyList;

    //создание вершины графа
    GraphNode(int key) {
        adjacencyList = new TreeSet();
        this.key = key;
    }

    //этот метод обеспечивает добавление ориентированного ребра из текушей вершины в вершину v
    public void addEdge(GraphNode v) throws Exception {
        adjacencyList.add(v);//добавление вершины v в список смежности (который реализован на АВЛ-дерево
    }

    //этот метод обеспечивает удаление ориентированного ребра из текушей вершины в вершину v
    public void removeEdge(GraphNode v) throws Exception {
        adjacencyList.remove(v);//удаление вершины v из списка смежности (который реализован на АВЛ-дерево)
    }

    public ArrayList getNeighbours() {
        return adjacencyList.getElements();
    }

}
