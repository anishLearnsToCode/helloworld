package graph;

public interface Graph<V> {
    boolean addVertex(V data);
    boolean removeVertex(V data);
    boolean containsVertex(V data);
    boolean removeEdge(V from, V to);
    int numberOfVertices();
    int numberOfEdges();
    boolean pathBetween(V from, V to);
    boolean edgeBetween(V from, V to);
}
