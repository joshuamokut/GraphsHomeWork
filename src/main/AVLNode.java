package main;

public class AVLNode {
    /* в этой переменной будем хранить информацию об обЪекте, который хранится во вершинах AVL-дерево */
    GraphNode object;
    /* это ключ вершины */
    int key;
    /* это высота поддерева текущей вершины */
    int height;
    /* это левый сын текущей вершины */
    AVLNode left;
    /* это правый сын текущей вершины */
    AVLNode right;

    //создание вершину АВЛ дерево для хранения вершины графа t
    public AVLNode(GraphNode t) {
        this.key = t.key;
        this.height = 1;
        this.object = t;
    }

}
