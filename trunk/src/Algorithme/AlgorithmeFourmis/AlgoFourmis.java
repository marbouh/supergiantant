package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Graphe.Graphe;
import Algorithme.Graphe.Noeud;

/**
 *  Class AlgoFourmis : 
 */
public class AlgoFourmis{

	private int nbreFourmis;
	private int nbreIterations;
	//private int[][] pheromone;    // Utilisation du type graphe ?
	private Graphe probleme;
	private Graphe resultant;//c'est dans ce graphe que l'on dépose le phéromone
	
	public AlgoFourmis(int nbreFourmis, int nbreIterations, Graphe probleme)
	{
		this.nbreFourmis = nbreFourmis;
		this.nbreIterations = nbreIterations;
		this.setProbleme(probleme);
		
		resultant = probleme;
		//On efface tous les poids des arrêtes afin de déposer le phéromone
		resultant.vider();
	}
	
	/*Fonctions à coder
	 * 
	 * Créer toutes les fourmis et les affecter à un noeud départ
	 * Répartir les fourmis sur l'ensemble des chemins possibles depuis le noeud de départ
	 * Relancer les fourmis (une fois qu'elles sont arrivées)
	 * Mettre à jour les pheromones (système d'évaporation)
	 * 
	 */
	
	public void traiterProbleme()
	{
		ArrayList<Fourmis> listeFourmis = new ArrayList<Fourmis>();
		Noeud noeudDepart = null;
		//Création des fourmis
		for(int j=0; j < nbreFourmis;j++)
		{
			Fourmis f = new Fourmis(10,5,this);

			f.ajouterNoeudVisite(noeudDepart);
			listeFourmis.add(f);
		}
		
		for(int i=0; i < nbreIterations;i++)
		{
			
			
			
			/*if(i == vitesseEvaporationPheromone)
				this.misAJourPheromone();
			*/
		}
		
	}
	
	
	
	
	public void deposerPheromone(Noeud noeudDepart, Noeud noeudArrivee, int nbrePheromones)
	{
		resultant.setPoids(noeudDepart, noeudArrivee, nbrePheromones);
	}
	
		
	public void misAJourPheromone()
	{
		
		
	}
	
	public int getNbreFourmis() {
		return nbreFourmis;
	}
	public void setNbreFourmis(int nbreFourmis) {
		this.nbreFourmis = nbreFourmis;
	}
	public void setNbreIterations(int nbreIterations) {
		this.nbreIterations = nbreIterations;
	}
	public int getNbreIterations() {
		return nbreIterations;
	}
	public void setProbleme(Graphe probleme) {
		this.probleme = probleme;
	}
	public Graphe getProbleme() {
		return probleme;
	}
	public int getNbreNoeuds()
	{
		return probleme.getNbreNoeuds();
	}
	public void setResultant(Graphe resultant) {
		this.resultant = resultant;
	}
	public Graphe getResultant() {
		return resultant;
	}
	
}
