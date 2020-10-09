package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class GraphHelloWorld {
    public static void main(String[] args) {
        UndirectedUnweightedGraph<Integer> graph = new UndirectedUnweightedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(4, 3);

        graph.addVertex(5);
        graph.addEdge(4, 5);

        System.out.println(graph.pathBetween(1, 2));
        System.out.println(graph.pathBetween(1, 5));
        System.out.println(graph.pathBetween(5, 3));

        graph.addVertex(6);

        System.out.println(graph.pathBetween(1, 6));

        System.out.println(graph);
    }

    private static class UndirectedUnweightedGraph<T> {
        private final Map<T, Vertex<T>> vertices = new HashMap<>();
        private int numberOfEdges = 0;

        public static class Vertex<T> {
            public final T data;
            private final Set<Vertex<T>> edges;

            Vertex() {
                this(null);
            }

            Vertex(T data) {
                this.data = data;
                edges = new HashSet<>();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Vertex<?> vertex = (Vertex<?>) o;
                return data.equals(vertex.data);
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

            public int degree() {
                return edges.size();
            }

            private String edgesToString() {
                StringBuilder accumulator = new StringBuilder("{");
                for (Vertex<T> vertex : edges) {
                    accumulator.append(vertex.data).append(" ");
                }
                accumulator.append("}");
                return accumulator.toString();
            }

            private void addEdge(Vertex<T> vertex) {
                edges.add(vertex);
            }

            private void removeEdge(Vertex<T> vertex) {
                edges.remove(vertex);
            }

            private boolean containsEdgeTo(Vertex<T> vertex) {
                return edges.contains(vertex);
            }

            private boolean pathTo(Vertex<T> to) {
                return pathTo(this, to, new HashSet<>());
            }

            private boolean pathTo(Vertex<T> from, Vertex<T> to, Set<Vertex<T>> visited) {
                if (from == to) {
                    return true;
                }

                if (visited.contains(from)) {
                    return false;
                }

                visited.add(from);

                for (Vertex<T> connected : from.edges) {
                    if (pathTo(connected, to, visited)) {
                        return true;
                    }
                }

                return false;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UndirectedUnweightedGraph<?> that = (UndirectedUnweightedGraph<?>) o;
            return vertices.equals(that.vertices);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertices);
        }

        @Override
        public String toString() {
            return "Graph" + vertices.values();
        }

        public int numberOfVertices() {
            return vertices.size();
        }

        public int numberOfEdges() {
            return numberOfEdges;
        }

        public boolean addVertex(T data) {
            if (containsVertex(data)) {
                return false;
            }

            vertices.put(data, new Vertex<>(data));
            return true;
        }

        public boolean removeVertex(T data) {
            if (!containsVertex(data)) {
                return false;
            }

            Vertex<T> vertex = vertices.get(data);
            for (Vertex<T> connectedVertex : vertex.edges) {
                connectedVertex.edges.remove(vertex);
                numberOfEdges--;
            }

            vertices.remove(data);
            return true;
        }

        public boolean containsVertex(T data) {
            return vertices.containsKey(data);
        }

        public boolean addEdge(T from, T to) {
            if (edgeBetween(from, to)) {
                return false;
            }

            if (containsVertex(from) && containsVertex(to)) {
                vertices.get(from).addEdge(vertices.get(to));
                vertices.get(to).addEdge(vertices.get(from));
                numberOfEdges++;
                return true;
            }

            return false;
        }

        public boolean removeEdge(T from, T to) {
            if (containsVertex(from) && containsVertex(to) && edgeBetween(from, to)) {
                Vertex<T> vertex1 = vertices.get(from);
                Vertex<T> vertex2 = vertices.get(to);
                vertex1.removeEdge(vertex2);
                vertex2.removeEdge(vertex1);
                numberOfEdges--;
                return true;
            }

            return false;
        }

        public boolean edgeBetween(T from, T to) {
            return vertices.getOrDefault(from, new Vertex<>())
                    .containsEdgeTo(vertices.getOrDefault(to, new Vertex<>()));
        }

        public boolean pathBetween(T from, T to) {
            return vertices.getOrDefault(from, new Vertex<>())
                    .pathTo(vertices.getOrDefault(to, new Vertex<>()));
        }
    }
}
