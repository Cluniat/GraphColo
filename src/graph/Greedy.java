package graph;


import java.util.ArrayList;

class Greedy {

    /**
     * field graph : un graphe
     */
    private Graph graph;
    /**
     * field colorVertecesDec = liste des sommets colorés pour l'algo décroissant
     */
    private ArrayList<Vertex> colorVertecesDec = new ArrayList<>();
    /**
     * field colorVertecesInc = liste des sommets colorés pour l'algo croissant
     */
    private ArrayList<Vertex> colorVertecesInc = new ArrayList<>();
    /**
     * fiel colorVertecesRd = liste des sommets colorés pour l'algo aléatoire
     */
    private ArrayList<Vertex> colorVertecesRd = new ArrayList<>();


    Greedy(Graph graph) {
        this.graph = graph;
        }

    /**
     * Algorithme de Greedy qui choisit les sommets dans l'ordre décroissant de leur degré
     * @param graph : un graphe
     */
    void decreasing(Graph graph){
        long debut = System.currentTimeMillis();

        int color;
        int size = graph.listVertex.size();
        int cpt =0;
        while (cpt!=size)
        {
            Vertex vertex = graph.getVertexMaxDegree(graph.listVertex);
            color = graph.smallestAvailableColor(vertex);
            vertex.setColor(color);
            graph.updateAvailableColor(vertex);
            colorVertecesDec.add(vertex);
            graph.listVertex.remove(vertex);
            cpt++;
        }
        graph.listVertex.clear();
        graph.listVertex = colorVertecesDec;

        int k = graph.comptColors();
        long duration = System.currentTimeMillis() - debut;
        System.out.println("Greedy décroissant a colorié le graph " +graph.name+ " avec " +k+ " couleurs en " + duration +"s.\n");
    }

    /**
     * Algorithme de Greedy qui choisit les sommets dans l'ordre croissant de leur degré
     * @param graph : un graphe
     */
    void increasing(Graph graph){
        long debut = System.currentTimeMillis();

        int color;
        int size = graph.listVertex.size();
        int cpt =0;
        while (cpt!=size)
        {
            Vertex vertex = graph.getVertexMinDegree(graph.listVertex);
            color = graph.smallestAvailableColor(vertex);
            vertex.setColor(color);
            graph.updateAvailableColor(vertex);
            colorVertecesInc.add(vertex);
            graph.listVertex.remove(vertex);
            cpt++;
        }
        graph.listVertex.clear();
        graph.listVertex = colorVertecesInc;

        int k = graph.comptColors();
        long duration = System.currentTimeMillis() - debut;
        System.out.println("Greedy croissant a colorié le graph " +graph.name+ " avec " +k+ " couleurs en " + duration +"s.\n");

    }

    /**
     * Algorithme de Greedy qui choisit les sommets aléatoirement
     * @param graph : un graphe
     */
    void Random(Graph graph){
        long debut = System.currentTimeMillis();

        int color;
        int size = graph.listVertex.size();
        int cpt =0;
        while (cpt!=size)
        {
            Vertex vertex = graph.getRandomVertex(graph.listVertex);
            color = graph.smallestAvailableColor(vertex);
            vertex.setColor(color);
            graph.updateAvailableColor(vertex);
            colorVertecesRd.add(vertex);
            graph.listVertex.remove(vertex);
            cpt++;
        }
        graph.listVertex.clear();
        graph.listVertex = colorVertecesRd;

        int k = graph.comptColors();
        long duration = System.currentTimeMillis() - debut;
        System.out.println("Greedy random a colorié le graph " +graph.name+ " avec " +k+ " couleurs en " + duration +"s.\n");
    }



}
