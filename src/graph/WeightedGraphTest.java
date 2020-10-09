package graph;

public class WeightedGraphTest {
    public static void main(String[] args) {
        WeightedGraph<Integer, Integer> graph = new WeightedGraphImpl<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 10);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(3, 5, -190);

        System.out.println(graph);
    }
}
