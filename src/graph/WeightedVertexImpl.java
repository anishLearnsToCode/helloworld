package graph;

import java.util.*;

public class WeightedVertexImpl<V, E> implements WeightedVertex<V, E> {
    V data;
    Map<WeightedVertex<V, E>, E> edges = new HashMap<>();

    WeightedVertexImpl() {
        this(null);
    }

    WeightedVertexImpl(V data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedVertexImpl<?, ?> that = (WeightedVertexImpl<?, ?>) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "data=" + data +
                ", edges=" + edgesToString() +
                '}';
    }

    private String edgesToString() {
        StringBuilder accumulator = new StringBuilder("{");
        for (WeightedVertex<V, E> connected : this) {
            accumulator.append(connected.value()).append(" ");
        }
        accumulator.append("}");
        return accumulator.toString();
    }

    @Override
    public void addEdge(WeightedVertex<V, E> to, E edgeData) {
        edges.put(to, edgeData);
    }

    @Override
    public boolean removeEdge(WeightedVertex<V, E> to) {
        if (edges.containsKey(to)) {
            edges.remove(to);
            return true;
        }

        return false;
    }

    @Override
    public boolean connectedTo(WeightedVertex<V, E> vertex) {
        return edges.containsKey(vertex);
    }

    @Override
    public boolean pathTo(WeightedVertex<V, E> vertex) {
        return pathTo(this, vertex, new HashSet<>());
    }

    private boolean pathTo(WeightedVertex<V, E> from, WeightedVertex<V, E> to, Set<WeightedVertex<V, E>> visited) {
        if (from == to) {
            return true;
        }

        if (visited.contains(from)) {
            return false;
        }

        visited.add(from);

        for (WeightedVertex<V, E> connected : from) {
            if (pathTo(connected, to, visited)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int degree() {
        return edges.size();
    }

    @Override
    public Iterator<WeightedVertex<V, E>> iterator() {
        return edges.keySet().iterator();
    }

    @Override
    public V value() {
        return data;
    }
}
