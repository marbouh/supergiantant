package Algorithme.Graphe;

import java.util.ArrayList;

/**
 * Interface Graphe
 */
public interface Graphe {
	public void modifierNom(String nom);
	public String obtenirNom();
	public ArrayList<NoeudList> obtenirSuivants(NoeudList noeud);
	public boolean verifierTrajet(NoeudList noeudDepart, NoeudList noeudArrivee);
	public double obtenirPoids(NoeudList noeudDepart, NoeudList noeudArrivee);
	public void modifierPoids(NoeudList noeudDepart, NoeudList noeudArrivee, double poids);
	public int obtenirNbreNoeuds();
	public void viderInformations();
	public void afficherGraphe();
	public Graphe copierGraphe();
	public ArrayList<NoeudList> obtenirNoeuds() ;
}
