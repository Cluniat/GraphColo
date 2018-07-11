package graph;

import java.util.ArrayList;

public class Edge {

    /**
     * field listVertex : tous les sommets du graphe
     */
    private ArrayList<Vertex> listVertex;
    /**
     * field oriented : true si le graphe est orienté
     */
    private Boolean oriented;


    Edge(ArrayList<Vertex> listVertex, Boolean oriented) {
        this.listVertex = listVertex;
        this.oriented = oriented;
    }


    ArrayList<Vertex> getListVertex() {
        return listVertex;
    }

    @Override
    public String toString() {
        String operator = oriented ? " --> " : " -- ";
        return listVertex.get(0) + operator + listVertex.get(1);
    }
}