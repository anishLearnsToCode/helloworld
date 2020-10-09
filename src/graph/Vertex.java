package graph;

public interface Vertex<V> {
    boolean removeEdge(Vertex<V> to);
    boolean connectedTo(Vertex<V> vertex);
    boolean pathTo(Vertex<V> vertex);
    int degree();
}
