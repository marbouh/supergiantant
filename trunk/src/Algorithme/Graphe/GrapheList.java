package Algorithme.Graphe;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class GraphList
 */
public class GrapheList implements Graphe 
{
	private String nom;
	private ArrayList<Noeud> noeuds;
	
	public GrapheList(String nom, int nbreNoeuds) {
		this.nom = nom;
		noeuds = new ArrayList<Noeud>(nbreNoeuds);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public ArrayList<Noeud> getSuivants(Noeud noeud) {
		ArrayList<Noeud> ns = new ArrayList<Noeud>();
		Noeud n = null;
		ArrayList<ArreteList> a = noeud.getDestinations();

		for (Iterator it = a.iterator(); it.hasNext(); n = (Noeud) it.next())
			ns.add(n.getArrivee());
		
		return ns;
	}

	public boolean checkTrajet(Noeud noeudDepart, Noeud noeudArrivee) {
		ArreteList trajet;

		for (Iterator it = noeudDepart.getDestinations().iterator(); it.hasNext(); trajet = (ArreteList) it.next())
			if (trajet.checkTrajet(noeudDepart, noeudArrivee))
				return true;
		return false;
	}

	// Non fonctionnel
	public double getPoids(Noeud noeudDepart, Noeud noeudArrivee) {
		ArreteList trajet;

		for (Iterator it = noeudDepart.getDestinations().iterator(); it.hasNext(); trajet = (ArreteList) it.next())
			if (trajet.checkTrajet(noeudDepart, noeudArrivee))
				return trajet.getPoids();
		return -1;
	}

	public int getNbreNoeuds() {
		return noeuds.size();
	}

	public void setNbreNoeuds(int nbreNoeuds) {
	}

	public void setPoids(Noeud noeudDepart, Noeud noeudArrivee, double poids) {
		ArreteList trajet;
		boolean estPresent = false;

		for (Iterator it = noeudDepart.getDestinations().iterator(); it.hasNext(); trajet = (ArreteList) it.next()) {
			if (trajet.checkTrajet(noeudDepart, noeudArrivee)) {
				trajet.setPoids(poids);
				estPresent = true;
			}
		}
		if (!estPresent) 
			noeudDepart.addDestination(noeudArrivee, poids);
	}

	public void vider() {
		noeuds.clear();
	}
}
