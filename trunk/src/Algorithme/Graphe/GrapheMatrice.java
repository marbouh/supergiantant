package Algorithme.Graphe;

import java.util.ArrayList;

/**
 *  Class GrapheMatrice
 */
public class GrapheMatrice implements Graphe {
	private String nom;
	private double[][] poids;
	private int nbreNoeuds;
	
	
	public GrapheMatrice(String nom, int nbreNoeuds)
	{
		poids = new double[nbreNoeuds][nbreNoeuds];
		this.nbreNoeuds = nbreNoeuds;

		this.nom = nom;
	}

	/**
	 *  Constructeur de copie
	 */
	public GrapheMatrice(String nom, Graphe graphe) {
		setNbreNoeuds(graphe.getNbreNoeuds());
		this.nom = nom;
	}

	public void vider() {
		for (int i = 0; i < nbreNoeuds; i++)
			for (int j = 0; j < nbreNoeuds; j++)
				poids[i][j] = 0;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	// Non fonctionnel : this.noeuds, non déclarer
	public ArrayList<Noeud> getSuivants(Noeud noeud) {
		ArrayList<Noeud> noeuds = new ArrayList<Noeud>();

		int i = noeud.getId();
		for (int j = 0; j < nbreNoeuds; j++) {
			//if (poids[i][j] != 0)
				//noeuds.add(this.noeud.get(j));
		}

		return noeuds;
	}

	public double getPoids(Noeud noeudDepart, Noeud noeudArrivee) {
		int i = noeudDepart.getId();
		int j = noeudArrivee.getId();
		if (i < nbreNoeuds || j < nbreNoeuds)
			throw new IllegalStateException("Graphe.getPoids : Dépassement de la fin du graphe");
		return poids[i][j];
	}
	public void setPoids(Noeud noeudDepart, Noeud noeudArrivee, double poids) {
		int i = noeudDepart.getId();
		int j = noeudArrivee.getId();
		if (i < nbreNoeuds || j < nbreNoeuds)
			throw new IllegalStateException("Graphe.setPoids : Dépassement de la fin du graphe");
		this.poids[i][j] = poids;
	}

	public int getNbreNoeuds() {
		return nbreNoeuds;
	}
	public void setNbreNoeuds(int nbreNoeuds) {
		double[][] p = new double[nbreNoeuds][nbreNoeuds];

		int n = (nbreNoeuds < this.nbreNoeuds) ? nbreNoeuds : this.nbreNoeuds;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				p[i][j]=(poids[i][j]);
			}
		}

		this.nbreNoeuds = nbreNoeuds;
		this.poids = p;
	}

	@Override
	public boolean checkTrajet(Noeud noeudDepart, Noeud noeudArrivee) {
		// TODO Auto-generated method stub
		return false;
	}

}
