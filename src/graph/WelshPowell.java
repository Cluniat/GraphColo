package graph;

import java.util.ArrayList;

class WelshPowell {
    /**
     * field graph = un graphe
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


    WelshPowell(Graph graph) {
        this.graph = graph;
    }

    /**
     * Algorithme de Welsh-Powell qui choisit les sommets dans l'ordre décroissant de leur degré
     * @param graph : un graphe
     */
    void decreasing(Graph graph){
        long debut = System.currentTimeMillis();
        ArrayList<Vertex> listTemp = new ArrayList<>();
        int k = 1;
        int size;
        Vertex x;
        Vertex y;
        while ( !graph.listVertex.isEmpty()){
            int i = 0;
            x = graph.getVertexMaxDegree(graph.listVertex);
            x.setColor(k);
            colorVertecesDec.add(x);
            graph.listVertex.remove(x);
            size = graph.listVertex.size();
            y = graph.getVertexMaxDegree(graph.listVertex);
            while(i < size){
                if ( !adjacentTo(y, k)){
                    y.setColor(k);
                    colorVertecesDec.add(y);
                    graph.listVertex.remove(y);
                    y = graph.getVertexMaxDegree(graph.listVertex);
                    i++;
                }
                else {
                    i++;
                    Vertex temp = y;
                    graph.listVertex.remove(y);
                    y = graph.getVertexMaxDegree(graph.listVertex);
                    listTemp.add(temp);
                }
            }

            k++;
            for (Vertex v : listTemp) {
                if(!graph.listVertex.contains(v)) {
                    graph.listVertex.add(v);
                }
            }
            listTemp.clear();
        }
        graph.listVertex.clear();
        graph.listVertex = colorVertecesDec;
        int nb = graph.comptColors();
        long duration = System.currentTimeMillis() - debut;
        System.out.println("WelshPowell décroissant a colorié le graph " +graph.name+ " avec " +nb+ " couleurs en " + duration +"s.\n");
    }

    /**
     * Algorithme de Welsh-Powell qui choisit les sommets dans l'ordre croissant de leur degré
     * @param graph : un graphe
     */
    void increasing(Graph graph){
        long debut = System.currentTimeMillis();
        ArrayList<Vertex> listTemp = new ArrayList<>();
        int k = 1;
        int size;
        Vertex x;
        Vertex y;
        while ( !graph.listVertex.isEmpty()){
            int i = 0;
            x = graph.getVertexMinDegree(graph.listVertex);
            x.setColor(k);
            colorVertecesInc.add(x);
            graph.listVertex.remove(x);
            size = graph.listVertex.size();
            y = graph.getVertexMinDegree(graph.listVertex);
            while(i < size){
                if ( !adjacentTo(y, k)){
                    y.setColor(k);
                    colorVertecesInc.add(y);
                    graph.listVertex.remove(y);
                    y = graph.getVertexMinDegree(graph.listVertex);
                    i++;
                }
                else {
                    i++;
                    Vertex temp = y;
                    graph.listVertex.remove(y);
                    y = graph.getVertexMinDegree(graph.listVertex);
                    listTemp.add(temp);
                }
            }
            k++;
            for (Vertex v : listTemp) {
                if(!graph.listVertex.contains(v)) {
                    graph.listVertex.add(v);
                }
            }
            listTemp.clear();
        }
        graph.listVertex.clear();
        graph.listVertex = colorVertecesInc;
        int nb = graph.comptColors();
        long duration = System.currentTimeMillis() - debut;
        System.out.println("WelshPowell croissant a colorié le graph " +graph.name+ " avec " +nb+ " couleurs en " + duration +"s.\n");
    }

    /**
     * Algorithme de Welsh-Powell qui choisit les sommets au hasard
     * @param graph : un graphe
     */
    void random(Graph graph){
        long debut = System.currentTimeMillis();
        ArrayList<Vertex> listTemp = new ArrayList<>();
        int k = 1;
        int size;
        Vertex x;
        Vertex y;
        while ( graph.listVertex.size() != 0){
            int i = 0;
            x = graph.getRandomVertex(graph.listVertex);
            x.setColor(k);
            colorVertecesRd.add(x);
            graph.listVertex.remove(x);
            size = graph.listVertex.size();
            y = graph.getRandomVertex(graph.listVertex);
            while(i < size){
                if ( !adjacentTo(y, k)){
                    y.setColor(k);
                    colorVertecesRd.add(y);
                    graph.listVertex.remove(y);
                    if(graph.listVertex.size() != 0) {
                        y = graph.getRandomVertex(graph.listVertex);
                    }
                    i++;
                }
                else {
                    i++;
                    Vertex temp = y;
                    graph.listVertex.remove(y);
                    if(graph.listVertex.size() != 0) {
                        y = graph.getRandomVertex(graph.listVertex);
                    }
                    listTemp.add(temp);
                }
            }

            k++;
            for (Vertex v : listTemp) {
                if(!graph.listVertex.contains(v)) {
                    graph.listVertex.add(v);
                }
            }
            listTemp.clear();
        }
        graph.listVertex.clear();
        graph.listVertex = colorVertecesRd;
        int nb = graph.comptColors();
        long duration = System.currentTimeMillis() - debut;
        System.out.println("WelshPowell random a colorié le graph " +graph.name+ " avec " +nb+ " couleurs en " + duration +"s.\n");
    }




    /**
     *  Vérifie si un sommet est adjacent a un autre sommet de couleur donnée
     * @param v : sommet
     * @param color : couleur
     * @return true si le sommet est adjacent à un autre sommet de couleur color
     */
    public Boolean adjacentTo (Vertex v, int color){
        int cpt = 0;
        for (Edge edge : graph.edgesByVextex(v)) {
            if(edge.getListVertex().get(1).getColor() == color || edge.getListVertex().get(0).getColor() == color){
                cpt++;
            }
        }
        return (cpt>0);
    }

}
