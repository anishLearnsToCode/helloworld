package graph;

public interface WeightedGraph<V, E> extends Graph<V> {
    boolean addEdge(V from, V to, E edgeData);
    E edgeVale(V from, V to);
}
