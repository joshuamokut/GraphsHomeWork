package main;

public class Edge {
    private final int from;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    private final int to;

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "(" + from+" "+to+") ";
    }
}
