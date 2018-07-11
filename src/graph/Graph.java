package graph;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    /**
     * field name = nom du graphe
     */
    String name;
    /**
     * field listEdge = toutes les arrêtes du graphe
     */
    private ArrayList<Edge> listEdge = new ArrayList<>();
    /**
     * field listVertex = tous les sommets du graphe
     */
    ArrayList<Vertex> listVertex = new ArrayList<>();
    /**
     * field oriented = true si le graphe est orienté
     */
    private Boolean oriented;

    /**
     * Lire un fichier
     * @param filename nom du fichier à lire
     * @return le contenu du fichier ou null si une erreure s'est produite
     */
    private List<String> readFile(String filename)
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Construit le graphe à partir des données d'un fichier
     * @param nom nom du fichier
     */
    void buildGraph(String nom){
        List<String> lines = readFile("C:\\Users\\Philippine CLUNIAT\\Desktop\\3A INFO\\Graphe\\Graphes\\fichiers\\"+ nom);
        name = lines.get(0).split(" ")[1];
        Boolean oriented = lines.get(1).split(" ")[1].equals("oui");
        int vertexNb = Integer.parseInt(lines.get(2).split(" ")[1]);
        int edgeNb = Integer.parseInt(lines.get(4).split(" ")[1]);
        for (int i = 7; i < vertexNb + 7; i++) {
            listVertex.add(new Vertex(Integer.parseInt(lines.get(i).split(" ")[0])));
        }
        for (int i = 7 + vertexNb + 1; i < edgeNb + 7 + vertexNb + 1 ; i++) {
            Vertex v1 = getById(Integer.parseInt(lines.get(i).split(" ")[0]));
            Vertex v2 = getById(Integer.parseInt(lines.get(i).split(" ")[1]));
            ArrayList<Vertex> vertices = new ArrayList<>();
            vertices.add(v1);
            vertices.add(v2);
            listEdge.add(new Edge(vertices, oriented));
        }
        calcVerticesDegree();
        for (Vertex v: listVertex) {
            initializeAvailableColors(v);
        }
    }

    /**
     * Calcule le degré de chaque sommet du graphe
     */
    private void calcVerticesDegree(){
        for (Vertex v: listVertex) {
            int degree = 0;
            for (Edge e: listEdge) {
                ArrayList<Vertex> vertices = e.getListVertex();
                if (vertices.get(0)== v || vertices.get(1) == v){
                    degree++;
                }
            }
            v.setDegree(degree);
        }
    }

    /**
     * Donne le sommet avec le plus petit degré du graphe
     * @param listVertex : tous les sommets du graphe
     * @return le sommet de degré minimal
     */
    Vertex getVertexMinDegree(ArrayList<Vertex> listVertex){
        int min = Integer.MAX_VALUE;
        Vertex vMin = new Vertex(0);
        for (int i = 0; i < listVertex.size() ; i++) {
            if(listVertex.get(i).getDegree() < min){
                min = listVertex.get(i).getDegree();
                vMin = listVertex.get(i);
            }
        }
        return vMin;
    }

    /**
     * Donne le sommet avec le plus grand degré du graphe
     * @param listVertex : tous les sommets du graphe
     * @return le sommet de degré maximal
     */
    Vertex getVertexMaxDegree(ArrayList<Vertex> listVertex){
        int max = Integer.MIN_VALUE;
        Vertex vMax = new Vertex(0);
        for (int i = 0; i < listVertex.size() ; i++) {
            if(listVertex.get(i).getDegree() > max){
                max = listVertex.get(i).getDegree();
                vMax = listVertex.get(i);
            }
        }
        return vMax;
    }

    /**
     * Donne un sommet du graphe au hasard
     * @param listVertex : tous les sommets du graphe
     * @return un sommet du graphe
     */
    Vertex getRandomVertex(ArrayList<Vertex> listVertex){
        try {
            int min = listVertex.get(0).getId();
            int max = listVertex.size() - 1;
            Vertex v = getById((int) (Math.random() * (max - min + 1)) + min);
            while (!listVertex.contains(v)) {
                v = getById((int) (Math.random() * (max - min + 1)) + min);
            }
            return v;
        }
        catch (Exception e){
            return new Vertex(0);
        }
    }

    /**
     * Donne tous les sommets adjacent à un sommet donné
     * @param v : sommet dont on cherche les sommets adjacents
     * @return la liste des sommets adjacents a v
     */
    ArrayList<Vertex> adjacentList(Vertex v){
        ArrayList<Vertex> adjacentList = new ArrayList<>();
        for (Edge edge : edgesByVextex(v)) {
            Vertex v1 = edge.getListVertex().get(0);
            Vertex v2 = edge.getListVertex().get(1);
            if( v1 == v){
                adjacentList.add(v2);
            }
//            A decommenter uniquement pour crow10
//            if(v2 == v){
//                    adjacentList.add(v1);
//            }
        }

        return adjacentList;
    }

    /**
     * Donne toutes les arrêtes qui partent ou qui arrivent d'un sommet
     * @param v : le sommet donc on cherche les arrêtes
     * @return une liste des arrêtes qui partent ou qui arrivent de v
     */
    ArrayList<Edge> edgesByVextex(Vertex v){
        ArrayList<Edge> vertexEdges = new ArrayList<>();
        for (Edge edge: listEdge) {
            if(edge.getListVertex().get(0) == v || edge.getListVertex().get(1) == v){
                vertexEdges.add(edge);
            }
        }
        return vertexEdges;
    }

    /**
     * Réinitialise la liste des couleurs disponibles d'un sommet
     * @param v sommet dont on réinitialise la liste
     */
    private void initializeAvailableColors(Vertex v){
        ArrayList<Integer> availableColors = new ArrayList<>();
        for (int i = 1; i <= listVertex.size() ; i++) {
            availableColors.add(i);
        }
        v.setAvailableColor(availableColors);
    }

    /**
     * Donne la plus petite couleur disponible pour un sommet
     * @param vertex un sommet
     * @return le numéro de la plus petite couleur disponible pour ce sommet
     */
    int smallestAvailableColor(Vertex vertex){
        int kmin = 1000000;
        for (Integer c : vertex.getAvailableColor()) {
            if(c < kmin){
                kmin = c;
            }
        }
        return kmin;
    }

    /**
     * Mise à jour des couleurs disponibles pour un sommet v
     * @param vertex : sommet v
     */
    void updateAvailableColor(Vertex vertex){
        for (Vertex v : adjacentList(vertex)) {
            if(v.getColor() == 0 && v.getAvailableColor().contains(vertex.getColor())) {
                v.removeFromValue(vertex.getColor());
            }
        }
    }

    /**
     * affiche les arrêtes dans la console
     */
    public void displayEdges(){
        for (Edge edge : listEdge) {
            System.out.println(edge);
        }
    }

    /**
     * affiche les sommets dans la console
     */
    public void displayVertex(){
        for (Vertex vertex : listVertex) {
            System.out.println(vertex + " : " + vertex.getColor());
        }
    }

    /**
     * Donne un sommet à partir de son identifiant
     * @param id : identifiant du sommet
     * @return le sommet qui correspondant à l'identifiant
     */
    private Vertex getById(int id) {
        for (Vertex vertex : listVertex) {
            if(vertex.getId() == id){
                return vertex;
            }
        }
        return null;
    }

    /**
     * Donne le nombre de couleurs du graphe
     * @return nombre
     */
    int comptColors() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.clear();
        for (Vertex v : listVertex) {
            if(!colors.contains(v.getColor())){
                colors.add(v.getColor());
            }
        }
        return colors.size();
    }

    /**
     * Réinitialise les couleurs de chaque sommet du graphe
     */
    private void resetColors(){
        for (Vertex v:listVertex) {
            v.setColor(0);
        }
    }

    /**
     * Réinitialise la couleur et la liste des couleurs disponibles de chaque sommet du graphe
     */
    void resetAll(){
        this.resetColors();
        for (Vertex v: this.listVertex) {
            v.getAvailableColor().clear();
            this.initializeAvailableColors(v);
        }
    }
}