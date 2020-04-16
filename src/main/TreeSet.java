package main;
import java.util.List;

import static main.AVLTree.*;

public class TreeSet {
    private AVLTree avlTree;

    public TreeSet() {
        this.avlTree = new AVLTree();
    }

    public void add(GraphNode graphNode) throws Exception {
        avlTree.root=insert(avlTree.root, graphNode);
    }

    public ArrayList getElements(){
        return AVLTree.getElements(avlTree);
    }

    public void remove(GraphNode graphNode) throws Exception {
        avlTree.root=delete(avlTree.root, graphNode);
    }

    public void print(){
        AVLTree.print(avlTree.root, "");
    }
}
