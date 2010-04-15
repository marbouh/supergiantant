package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.AlgorithmeFourmis.Fourmis.Etat;
import Algorithme.Graphe.ArreteList;
import Algorithme.Graphe.Graphe;
import Algorithme.Graphe.NoeudList;

/**
 *  Class AlgoFourmis : 
 */
public class AlgoFourmis{

	private int nbreFourmis;
	private int nbreIterations;
	private int vitesseEvapPheromone;
	private ArrayList<Fourmis> listeFourmis;
	private Graphe probleme;
	private Graphe resultant;//c'est dans ce graphe que l'on dépose le phéromone
	
	/*
	 * Constructeur de la classe fourmis
	 */
	public AlgoFourmis(int nbreFourmis, int nbreIterations, int vitesseEvaporationPheromone, Graphe probleme)
	{
		listeFourmis = new ArrayList<Fourmis>();
		this.nbreFourmis = nbreFourmis;
		this.nbreIterations = nbreIterations;
		this.setProbleme(probleme);
		this.vitesseEvapPheromone = vitesseEvaporationPheromone;
		resultant = probleme;
		//On efface tous les poids des arrêtes afin de déposer le phéromone
		resultant.viderInformations();
	}
	
	/*Fonctions à coder
	 * 
	 * Créer toutes les fourmis et les affecter à un noeud départ
	 * Répartir les fourmis sur l'ensemble des chemins possibles depuis le noeud de départ
	 * Relancer les fourmis (une fois qu'elles sont arrivées)
	 * Mettre à jour les pheromones (système d'évaporation)
	 * 
	 */
	
	/*
	 * Fonction permettant de créer l'ensemble des fourmis qui seront utilisées pour l'algorithme
	 */
	public void creerFourmis(NoeudList noeudDepart, int nbrePheromoneADeposer)
	{
		for(int j=0; j < nbreFourmis;j++)
		{
			Fourmis f = new Fourmis(nbrePheromoneADeposer,this);
			f.ajouterNoeudVisite(noeudDepart);
			listeFourmis.add(f);
		}
	
	}
	
	/*
	 * Fonction affectant les fourmis aléatoirement sur les différents noeuds suivants le départ
	 */
	public void affecterPremierNoeud()
	{
		NoeudList noeudDepart = listeFourmis.get(0).getListeNoeudsVisites().get(0);
		ArrayList<ArreteList> listeDestinations = noeudDepart.getDestinations();
		int random = -1;//permet de définir aléatoirement le chemin qui doit être pris
		
		for(int j=0; j < listeFourmis.size();j++)
		{
			random = (int)(Math.random() * (listeDestinations.size()));
			ArreteList chemin = listeDestinations.get(random);
			listeFourmis.get(j).ajouterNoeudVisite(chemin.getArrivee());
			listeFourmis.get(j).setEtat(Etat.ParcoursGraphe);
		}
			
	}
	
	/*
	 * Procédure permettant de créer l'ensemble des fourmis et d'exécuter l'algorithme des fourmis
	 */
	public void traiterProbleme(NoeudList noeudDepart, int nbrePheromoneADeposer)
	{
		this.creerFourmis(noeudDepart, nbrePheromoneADeposer);
		this.affecterPremierNoeud();
		
		for(int i=0; i < nbreIterations;i++)
		{
			for(int j=0; j < listeFourmis.size();j++)
			{
				if(listeFourmis.get(j).getEtat()== Etat.ParcoursGraphe)
					listeFourmis.get(j).trouverChemin();
				else if(listeFourmis.get(j).getEtat()== Etat.Rentre)
					listeFourmis.get(j).rentrer();
			}
			if((i % vitesseEvapPheromone) == 0 && i != 0)
				this.misAJourPheromone();
		}
	}
	
	/*
	 * Procédure permettant de déposer du phéromone sur une arrête dont les noeuds sont passés en paramètre
	 */
	public void deposerPheromone(NoeudList noeudDepart, NoeudList noeudArrivee, int nbrePheromones)
	{
		resultant.setPoids(noeudDepart, noeudArrivee, nbrePheromones);
	}
	
		
	public void misAJourPheromone()
	{
		System.out.println("Mis à jour des pheromones");
		
	}
		
	/* Getters et setters des attributs de la classe */
	
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

	public void setListeFourmis(ArrayList<Fourmis> listeFourmis) {
		this.listeFourmis = listeFourmis;
	}

	public ArrayList<Fourmis> getListeFourmis() {
		return listeFourmis;
	}

	public int getVitesseEvapPheromone() {
		return vitesseEvapPheromone;
	}

	public void setVitesseEvapPheromone(int vitesseEvapPheromone) {
		this.vitesseEvapPheromone = vitesseEvapPheromone;
	}

}
