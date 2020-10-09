package graph;

public interface WeightedVertex<V, E> extends Iterable<WeightedVertex<V, E>> {
    void addEdge(WeightedVertex<V, E> to, E edgeData);
    boolean removeEdge(WeightedVertex<V, E> to);
    boolean connectedTo(WeightedVertex<V, E> vertex);
    boolean pathTo(WeightedVertex<V, E> vertex);
    int degree();
    V value();
}
