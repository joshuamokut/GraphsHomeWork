package main;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zadacha {

    private static Stack topSortStack;
    private static List<Boolean> visited;


    public static void main(String[] args) throws Exception {

        //первый прогон
        List<Edge> edges1 = createEdges(1);
        run(edges1, 6);

        //второй прогон
        List<Edge> edges2 = createEdges(2);
        run(edges2, 6);

        //третий прогон
        List<Edge> edges3 = createEdges(3);
        run(edges3, 10);
    }

    private static void run(List<Edge> edges1, int n) throws Exception {

        //декодирования
        printEdges(edges1);

        //инцилизируем новый граф с n вершинами
        Graph graph = new Graph(n);

        //ребра довавляются в граф с помощью процедуру addEge.
        for (Edge edge : edges1) {
            graph.addEdge(edge);
        }

        //вызываем топологическую сортировку
        topologicalSort(graph);

        //разбиваем и классифицируем ребер
        splitGraph(graph);

        //По заданному списковому представлению ациклического графа построим представление для графа,
        // в котором направление каждого ребра исходного графа изменено на противоположное.
        reverse(edges1);
    }


    //процедура декодирования
    private static void printEdges(List<Edge> edges1) {
        System.out.println("ребра графа");
        edges1.forEach(System.out::println);
        System.out.println('\n');
    }

    //процедура кодирования.
    private static List<Edge> createEdges(int n) {

        if (n == 1)
            return Stream.of(new Edge(1, 6), new Edge(1, 4), new Edge(2, 3),
                    new Edge(4, 5), new Edge(4, 6), new Edge(5, 3), new Edge(5, 2), new Edge(6, 2), new Edge(6, 5))
                    .collect(Collectors.toList());
        else if (n == 2)
            return Stream.of(new Edge(2, 3), new Edge(3, 1), new Edge(4, 1),
                    new Edge(4, 6), new Edge(5, 6), new Edge(5, 2))
                    .collect(Collectors.toList());
        else
            return Stream.of(new Edge(1, 3), new Edge(1, 4), new Edge(2, 4), new Edge(2, 5), new Edge(10, 9), new Edge(8, 9),
                    new Edge(6, 8), new Edge(4, 6), new Edge(4, 7))
                    .collect(Collectors.toList());
    }


    //процедура для построения представлениЯ для графа,
    //в котором направление каждого ребра исходного графа изменено на противоположное
    private static void reverse(List<Edge> edges) {
        //для каждого ребра (u, v)  мы добавляем в список ребер нового графа ребро (v, u)
        List<Edge> newList = edges.stream().map(edge -> new Edge(edge.getTo(), edge.getFrom())).collect(Collectors.toList());

        //вывод на консоль
        System.out.println("графа, в котором направление каждого ребра исходного графа изменено на противоположное.");
        newList.forEach(System.out::println);
        System.out.println('\n');
    }

    //процедура для разбиения ребер в группы
    private static void splitGraph(Graph graph) {
        //здесь буду хранить все ребра, исходящие из некоторой вершины, из которой не исходит ни одного другого ребра
        List<Edge> singleton = new ArrayList<>();
        //здесь буду хранить все остальные ребра
        List<Edge> nonSingletons = new ArrayList<>();

        //итерирую по всем вершинам
        //если вершина имеет единственный ребро выходящий  то он соответсвует первому категорию
        // иначе все ребра исходящие из этой вершин соответствуют второму категрию
        Arrays.stream(graph.getVertices().array).filter(vertex -> vertex!=null && vertex.key != 0).forEach(vertex -> {
            if (vertex.getNeighbours().size() == 1)
                Arrays.stream(vertex.getNeighbours().array).filter(Objects::nonNull).forEach(v -> {
                    singleton.add(new Edge(vertex.key, v.key));
                });
            else
                Arrays.stream(vertex.getNeighbours().array).filter(Objects::nonNull).forEach(v -> {
                    nonSingletons.add(new Edge(vertex.key, v.key));
                });
        });

        //вывод в косоль
        System.out.println("все ребра, исходящие из некоторой вершины, из которой не исходит ни одного другого ребра: ");
        singleton.forEach(s -> System.out.println(s.toString()));

        System.out.println("все остальные");
        nonSingletons.forEach(s -> System.out.println(s.toString()));

        System.out.println('\n');
    }

    //процедруа для топологической сортировки
    private static void topologicalSort(Graph graph) throws EmptyStackException {
        //инцилизируем стек и массив visited
        topSortStack = new Stack();

        visited = new ArrayList<>();//здесь помечаем, посещен ли вершина или нет

        for (int i = 0; i < graph.getSize() + 1; i++)
            visited.add(false);

        //для каждой непосещенной вершины запускаем поиск в глубину
        Arrays.stream(graph.getVertices().array).filter(Objects::nonNull).forEach(treeNode -> {
            if (!visited.get(treeNode.key) && treeNode.key > 0)
                dfs(treeNode);
        });

        //выводится на консоль все вешшины в отсортированном порядке.
        System.out.println("вершины в отсортированном порядке  ");
        emptyStack().forEach(treeNode -> System.out.print(treeNode.key + " "));
        System.out.println('\n');
    }

    //процедура которая извлекает вершины из стека в список
    private static List<GraphNode> emptyStack() throws EmptyStackException {
        List<GraphNode> sortedList = new ArrayList<>();

        while (!topSortStack.empty())
            sortedList.add(topSortStack.pop());

        return sortedList;
    }

    //обход в глубину
    private static void dfs(GraphNode graphNode) {
        visited.set(graphNode.key, true);//помечаем что вершина теперь посещена

        //переходим по всем ребрам исходящим из текущей вершины и их обрабатываем
        Arrays.stream(graphNode.getNeighbours().array).filter(Objects::nonNull).forEach(neighbour -> {
            if (!visited.get(neighbour.key)) {
                dfs(neighbour);
            }
        });

        //теперь вешина обработана и можно положить в стек
        topSortStack.push(graphNode);
    }


}
