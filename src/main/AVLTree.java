package main;




import static java.lang.Integer.max;

public class AVLTree {
    private static ArrayList nodes;
    AVLNode root;

    static int getHeight(AVLNode node) {
        if (node == null)
            return 0;

        return node.height;
    }

    static AVLNode rightRotate(AVLNode node) throws Exception {
        if (node == null)
            throw new InvalidRotationException("вершина не должна быть null!");
        if (node.left == null)
            throw new InvalidRotationException("вращение невозможно. отсутствует левый сын");

        AVLNode x = node.left;

        //делаем вращения
        node.left = x.right;
        x.right = node;

        //обновляем высоты вершин
        node.height = max(getHeight(node.right), getHeight(node.left)) + 1;
        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;

        //возврщаем новое подддерево полученное после вращения
        return x;
    }

    static AVLNode leftRotate(AVLNode node) throws InvalidRotationException {
        if (node == null)
            throw new InvalidRotationException("вершина не должна быть null!");
        if (node.right == null)
            throw new InvalidRotationException("вращение невозможно. отсутствует правый сын");

        AVLNode y = node.right;

        //делаем вращения
        node.right = y.left;
        y.left = node;

        //обновляем высоты вершин
        y.height = max(getHeight(y.left), getHeight(y.right)) + 1;
        node.height = max(getHeight(node.left), getHeight(node.right)) + 1;

        //возврщаем новое подддерево полученное после вращения
        return y;
    }

    static int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    static AVLNode insert(AVLNode avlNode, GraphNode graphNode) throws Exception {
        //обчыная вставка в бинарное дерево поиска
        if (avlNode == null) {
            return new AVLNode(graphNode);
        }

        if (graphNode.key < avlNode.key)
            avlNode.left = insert(avlNode.left, graphNode);
        else if (graphNode.key > avlNode.key)
            avlNode.right = insert(avlNode.right, graphNode);
        else
            throw new DuplicateKeyException("такой ключ уже существует");

        avlNode.height = 1 + max(getHeight(avlNode.left), getHeight(avlNode.right));

        //восстановление баланса рассмотрим все 4 случае
        int balance = getBalance(avlNode);

        if (balance > 1 && graphNode.key < avlNode.left.key)
            return rightRotate(avlNode);

        if (balance > 1 && graphNode.key > avlNode.left.key) {
            avlNode.left = leftRotate(avlNode.left);
            return rightRotate(avlNode);
        }

        if (balance < -1 && graphNode.key > avlNode.right.key) {
            return leftRotate(avlNode);
        }

        if (balance < -1 && graphNode.key < avlNode.right.key) {
            avlNode.right = rightRotate(avlNode.right);
            return leftRotate(avlNode);
        }

        return avlNode;
    }

    static AVLNode delete(AVLNode root, GraphNode key) throws Exception {
        if (root==null)
            return null;

        if (key.key<root.key)
            root.left = delete(root.left, key);
        else if (key.key>root.key)
            root.right = delete(root.right, key);
        else{
            if (root.left==null && root.right==null)
                root=null;
            else if (root.left==null)
                root=root.right;
            else if (root.right==null)
                root=root.left;
            else{
                AVLNode successor=treeMin(root.right);
                root.key=successor.key;
                root.object=successor.object;
                root.right=delete(root.right, successor.object);
            }
        }

        if (root==null)
            return null;

        root.height=max(getHeight(root.left), getHeight(root.right))+1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left)>=0){
            return rightRotate(root);
        }
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left=leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right)<=0){
            return leftRotate(root);
        }
        if (balance<-1 && getBalance(root.right)>0){
            root.right=rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private static AVLNode treeMin(AVLNode avlNode) {
        while(avlNode.left!=null)
            avlNode=avlNode.left;
        return avlNode;

    }

    public static ArrayList getElements(AVLTree avlTree) {
        nodes = new ArrayList();
        preOrder(avlTree.root);
        return nodes;
    }

    public static void preOrder(AVLNode node) {
        if (node != null) {
            nodes.add(node.object);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void print(AVLNode node, String prefix) {
        if (node != null) {
            System.out.println(prefix+node.key);
            print(node.left, prefix+" ");
            print(node.right, prefix+" ");
        }
    }

    private static class DuplicateKeyException extends Exception {
        public DuplicateKeyException(String message) {
            super(message);
        }
    }
}
