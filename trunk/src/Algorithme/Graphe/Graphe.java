package Algorithme.Graphe;

/**
 * Interface Graphe
 */
public interface Graphe {
	public void setNom(String nom);
	public String getNom();
	public ArrayList<Noeud> getSuivants(Noeud noeud);
	public boolean checkTrajet(Noeud noeudDepart, Noeud noeudArrivee);
	public int getPoids(Noeud noeudDepart, Noeud noeudArrivee);
	public void setPoids(Noeud noeudDepart, Noeud noeudArrivee, int poids);
	public int getNbreNoeuds();
	public void setNbreNoeuds(int nbreNoeuds);
}
