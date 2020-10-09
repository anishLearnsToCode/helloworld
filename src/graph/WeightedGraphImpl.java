package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WeightedGraphImpl<V, E> implements WeightedGraph<V, E> {

    private final Map<V, WeightedVertexImpl<V, E>> vertices = new HashMap<>();
    private int numberOfEdges = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedGraphImpl<?, ?> that = (WeightedGraphImpl<?, ?>) o;
        return numberOfEdges == that.numberOfEdges && vertices.equals(that.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, numberOfEdges);
    }

    @Override
    public String toString() {
        return "WeightedGraph{"
                + vertices.values() +
                ", numberOfEdges=" + numberOfEdges +
                '}';
    }

    @Override
    public boolean addVertex(V data) {
        if (containsVertex(data)) {
            return false;
        }

        vertices.put(data, new WeightedVertexImpl<>(data));
        return true;
    }

    @Override
    public boolean removeVertex(V data) {
        if (!containsVertex(data)) {
            return false;
        }

        WeightedVertex<V, E> disposal = vertices.get(data);

        for (WeightedVertex<V, E> connected : disposal) {
            connected.removeEdge(disposal);
            numberOfEdges--;
        }

        vertices.remove(data);
        return true;
    }

    @Override
    public boolean containsVertex(V data) {
        return vertices.containsKey(data);
    }

    @Override
    public boolean addEdge(V from, V to, E edgeData) {
        if (!containsVertex(from) || !containsVertex(to)) {
            return false;
        }

        vertices.get(from).addEdge(vertices.get(to), edgeData);
        vertices.get(to).addEdge(vertices.get(from), edgeData);
        numberOfEdges++;
        return true;
    }

    @Override
    public boolean removeEdge(V from, V to) {
        if (!edgeBetween(from, to)) {
            return false;
        }

        return vertices.getOrDefault(from, new WeightedVertexImpl<>())
                .removeEdge(vertices.getOrDefault(to, new WeightedVertexImpl<>()));
    }

    @Override
    public int numberOfVertices() {
        return vertices.size();
    }

    @Override
    public int numberOfEdges() {
        return numberOfEdges;
    }

    @Override
    public boolean pathBetween(V from, V to) {
        if (!containsVertex(from)) {
            return false;
        }

        return vertices.get(from)
                .pathTo(vertices.getOrDefault(to, new WeightedVertexImpl<>()));
    }

    @Override
    public boolean edgeBetween(V from, V to) {
        if (!containsVertex(from)) {
            return false;
        }

        return vertices.get(from).connectedTo(vertices.getOrDefault(to, new WeightedVertexImpl<>()));
    }

    @Override
    public E edgeVale(V from, V to) {
        if (!containsVertex(from)) {
            return null;
        }

        return null;
    }
}
