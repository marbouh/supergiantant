package Algorithme.Graphe;

import java.util.ArrayList;

/**
 * Interface Graphe
 */
public interface Graphe {
	public void setNom(String nom);
	public String getNom();
	public ArrayList<Noeud> getSuivants(Noeud noeud);
	public boolean checkTrajet(Noeud noeudDepart, Noeud noeudArrivee);
	public double getPoids(Noeud noeudDepart, Noeud noeudArrivee);
	public void setPoids(Noeud noeudDepart, Noeud noeudArrivee, double poids);
	public int getNbreNoeuds();
	public void setNbreNoeuds(int nbreNoeuds);
	public void vider();
}