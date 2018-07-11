package graph;

import java.util.ArrayList;

class Dsatur {

    /**
     * field graph : un graphe
     */
    private Graph graph;
    /**
     * field colorVerteces : liste des sommets colorés
     */
    private ArrayList<Vertex> colorVerteces = new ArrayList<>();


    Dsatur(Graph graph) {
        long debut = System.currentTimeMillis();
        this.graph = graph;
        process(graph);
        int k = graph.comptColors();
        long duration = System.currentTimeMillis() - debut;
        System.out.println("Dsatur a colorié le graph " +graph.name+ " avec " +k+ " couleurs en " + duration +"s.\n");    }

    /**
     * Algorithme de Dsatur
     * @param graph : un graphe
     */
    private void process(Graph graph){
        int cpt = 0;
        int size = graph.listVertex.size();
        int color;
        Vertex x = graph.getVertexMaxDegree(graph.listVertex);
        color = 1;
        x.setColor(color);
        graph.updateAvailableColor(x);
        colorVerteces.add(x);
        graph.listVertex.remove(x);
        cpt ++;
        while(cpt != size){
            Vertex y = dSatMax();
            color = graph.smallestAvailableColor(y);
            y.setColor(color);
            graph.updateAvailableColor(y);
            colorVerteces.add(y);
            graph.listVertex.remove(y);
            cpt++;
        }
        graph.listVertex.clear();
        graph.listVertex = colorVerteces;
    }

    /**
     * Donne la valeur de DSAT d'un sommet
     * @param v : un sommet
     */
    private void calcDSAT(Vertex v){
        ArrayList<Integer> adjacentColor = new ArrayList<>();
        for (Vertex vertex: graph.adjacentList(v)) {
            if(!adjacentColor.contains(vertex.getColor())){
                adjacentColor.add(vertex.getColor());
            }
        }
        v.setDSAT(adjacentColor.size()-1);
    }

    /**
     * Donne le sommet avec le nombre de DSAT maximum
     * @return le sommet avec le DSAT maximum
     */
    private Vertex dSatMax(){
        int max = 0;
        int dsat;
        ArrayList<Vertex> maxDSAT = new ArrayList<>();
        for (Vertex v : graph.listVertex) {
            calcDSAT(v);
            dsat = v.getDSAT();
            if(dsat> max){
                max = dsat;
            }
        }
        for (Vertex v : graph.listVertex) {
            if(v.getDSAT()== max){
                maxDSAT.add(v);
            }
        }
        if(maxDSAT.size() != 1 ){
            return graph.getVertexMaxDegree(maxDSAT);
        }
        else{
            return maxDSAT.get(0);
        }
    }
}
