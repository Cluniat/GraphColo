package graph;

import java.util.ArrayList;

public class Vertex {

    /**
     * field id : identifiant du sommet
     */
    private int id;
    /**
     * field color : numéro de la couleur du sommet
     */
    private int color = 0;
    /**
     * field degree : degré du sommet
     */
    private int degree;
    /**
     * field availableColor : liste des couleurs disponibles pour le sommet
     */
    private ArrayList<Integer> availableColor;
    /**
     * field DSAT : nombre DSAT
     */
    private int DSAT;


    Vertex(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }

    int getDegree() {
        return degree;
    }

    void setDegree(int degree) {
        this.degree = degree;
    }

    void setColor(int color) {

        this.color = color;
        removeFromValue(color);
    }


    int getColor() {
        return color;
    }


    int getDSAT() {
        return DSAT;
    }

    void setDSAT(int DSAT) {
        this.DSAT = DSAT;
    }

    ArrayList<Integer> getAvailableColor() {
        return availableColor;
    }

    void setAvailableColor(ArrayList<Integer> availableColor) {
        this.availableColor = availableColor;
    }

    /**
     * Retire une couleur en fonction de sa valeur
     * @param value : valeur de la couleur à retirer
     */
    void removeFromValue(int value){
        for (int i = 0; i < availableColor.size() ; i++) {
            if(availableColor.get(i) == value){
                availableColor.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return String.valueOf((id));
    }
}