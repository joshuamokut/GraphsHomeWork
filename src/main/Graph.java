package main;

import java.util.List;

public class Graph {
    // в этой переменной будем хранить количество вершин в графе.
    int size;

    // в этом массиве будем хранить вершины графа, i-ая вершине в i-ом индексе
    ArrayList vertices;


    Graph(int N){
        vertices= new ArrayList();
        for(int i=0; i<N+1; i++)
            vertices.add(new GraphNode(i));
        this.size=N;
    }

    //этот метод обеспечивает добавления ребра (u, v) в граф.
    public void addEdge(Edge edge) throws Exception {
        vertices.get(edge.getFrom()).addEdge(vertices.get(edge.getTo()));
    }

    //этот метод обеспечивает удаления ребра (u, v) из графа
    public void removeEdge(Edge edge) throws Exception {
        vertices.get(edge.getFrom()).removeEdge(vertices.get(edge.getTo()));
    }

    public int getSize(){
        return size;
    }

    public ArrayList getVertices(){
        return vertices;
    }

}
