package graph;

public interface UnweightedVertex<V> extends Vertex<V> {
    boolean addEdge(Vertex<V> to);
}
