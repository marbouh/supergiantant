package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

/**
 *  Class Fourmis : Représente une entitée fourmis dans l'algorithme des fourmis
 */
public class Fourmis
{
	
	private int vitesseEvapPheromone;
	private int nbrePheromoneDeposes;
	private ArrayList<Integer> listeNoeudsVisites;
	private String etat;

	
	public Fourmis(int vitesseEvapPheromone,int nbrePheromoneDeposes, String etat)
	{
		this.vitesseEvapPheromone = vitesseEvapPheromone;
		this.nbrePheromoneDeposes = nbrePheromoneDeposes;
		this.etat = etat;
		this.listeNoeudsVisites = new ArrayList<Integer>();
	}
	
	
	public int getVitesseEvapPheromone() {
		return vitesseEvapPheromone;
	}
	public void setVitesseEvapPheromone(int vitesseEvapPheromone) {
		this.vitesseEvapPheromone = vitesseEvapPheromone;
	}
	public int getNbrePheromoneDeposes() {
		return nbrePheromoneDeposes;
	}
	public void setNbrePheromoneDeposes(int nbrePheromoneDeposes) {
		this.nbrePheromoneDeposes = nbrePheromoneDeposes;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getEtat() {
		return etat;
	}


	public void setListeNoeudsVisites(ArrayList<Integer> listeNoeudsVisites) {
		this.listeNoeudsVisites = listeNoeudsVisites;
	}


	public ArrayList<Integer> getListeNoeudsVisites() {
		return listeNoeudsVisites;
	}

}
