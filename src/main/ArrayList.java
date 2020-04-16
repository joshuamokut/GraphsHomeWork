package main;

public class ArrayList {
    int size = 1; //изначальный размер массива, в котором хранится списка
    int ptr = 0; //изначальный размер списка и также указатель на конец списка

    /* массив в котором список будет хранится. GraphNode это структура,
    обозначающая вершины графа, о нем поговорим попозже */
    GraphNode[] array;

    //создание списка без указанного размера
    public ArrayList() {
        array = new GraphNode[size];
    }

    //создание списка с указанным размера
    public ArrayList(int size) {
        this.size = size;
        this.array = new GraphNode[size];
    }

    //добавление элемента в список
    public void add(GraphNode graphNode) {
        if (ptr == size) {
            /*здесь создаем новый массив и делаем копию старого в новый*/
            GraphNode[] newArray = new GraphNode[size * 2];
            if (ptr >= 0) System.arraycopy(array, 0, newArray, 0, ptr);
            array = newArray;
        }

        //присвоение элемента новому ячейку и увеличение указатель
        array[ptr++] = graphNode;
    }

    //чтение элемента
    public GraphNode get(int i) throws IndexOutOfBoundsException {
        if (i >= ptr)
            throw new IndexOutOfBoundsException();
        else
            return array[i];
    }

    //присвоение элемента
    public void set(int i, GraphNode t) {
        if (i >= ptr)
            throw new IndexOutOfBoundsException();
        else
            array[i] = t;
    }

    public int size() {
        return ptr;
    }
}
