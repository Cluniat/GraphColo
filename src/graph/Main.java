package graph;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.buildGraph("queen15_15.txt");
        Greedy gr = new Greedy(graph);

//        gr.decreasing(graph);
//
//        graph.resetAll();
//
//        gr.increasing(graph);
//
//        graph.resetAll();

        gr.Random(graph);

        graph.resetAll();

//        Dsatur ds = new Dsatur(graph);
//
//        graph.resetAll();

        WelshPowell wp = new WelshPowell(graph);
//        wp.decreasing(graph);
//
//        graph.resetAll();
//        wp.increasing(graph);
//
//        graph.resetAll();
        wp.random(graph);

    }
}
