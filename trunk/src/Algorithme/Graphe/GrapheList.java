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
		Noeud n;
		ArrayList<Arrete> a = noeud.getArretes();

		for (Iterator it = a.iterator(); it.hasNext(); n = it.next())
			ns.add(n);
		
		return ns;
	}

	public boolean checkTrajet(Noeud noeudDepart, Noeud noeudArrivee) {
		Noeud n = null;

		for (Iterator it = noeudDepart.getArretes().iterator(); it.hasNext(); n = (Noeud) it.next())
			if (n.compareTo(noeudArrivee))
				return true;
		return false;
	}

	public double getPoids(Noeud noeudDepart, Noeud noeudArrivee) {
		Noeud n;

		for (Iterator it = noeudDepart.getArretes().iterator(); it.hasNext(); n = (Noeud) it.next())
			if (n.compareTo(noeudArrivee))
				return /* valeur du poids ^^ */;
	}


	@Override
	public int getNbreNoeuds() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNbreNoeuds(int nbreNoeuds) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void creerGraphe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPoids(Noeud noeudDepart, Noeud noeudArrivee, double poids) {
		// TODO Auto-generated method stub
		
	}
}
