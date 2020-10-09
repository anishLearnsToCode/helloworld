package graph;

public interface UnweightedGraph<V> extends Graph<V> {
    void addEdge(Vertex<V> from, Vertex<V> to);
}
