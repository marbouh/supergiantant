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
	private double nbrePheromoneAEvap;
	private ArrayList<Fourmis> listeFourmis;
	private Graphe probleme;
	private Graphe resultant;//c'est dans ce graphe que l'on dépose le phéromone
	
	/*
	 * Constructeur de la classe fourmis
	 */
	public AlgoFourmis(int nbreFourmis, int nbreIterations, int vitesseEvaporationPheromone, double nbrePheromoneAEvap,Graphe probleme)
	{
		listeFourmis = new ArrayList<Fourmis>();
		this.nbreFourmis = nbreFourmis;
		this.nbreIterations = nbreIterations;
		this.setProbleme(probleme);
		this.vitesseEvapPheromone = vitesseEvaporationPheromone;
		this.nbrePheromoneAEvap = nbrePheromoneAEvap;
		
		resultant = probleme.copierGraphe() ;
		resultant.viderInformations();//On efface tous les poids des arrêtes afin de déposer le phéromone
	}
	
	/*
	 * Fonction permettant de créer l'ensemble des fourmis qui seront utilisées pour l'algorithme
	 */
	public void creerFourmis(NoeudList noeudDepart, int nbrePheromoneADeposer, int noeudArrivee)
	{
		for(int j=0; j < nbreFourmis;j++)
		{
			Fourmis f = new Fourmis(nbrePheromoneADeposer,noeudArrivee,this);
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
		ArrayList<Integer> cheminDejaPris = new ArrayList<Integer>();
		boolean cheminTrouve = false;
		
		for(int j=0; j < listeFourmis.size();j++)
		{
			while(cheminTrouve == false)//boucle permettant d'affecter au moins une fourmis sur chaque destination possible
			{
				random = (int)(Math.random() * (listeDestinations.size()));
			
				if(!cheminDejaPris.contains(random) && cheminDejaPris.size() <= listeDestinations.size())
				{
					ArreteList chemin = listeDestinations.get(random);
					listeFourmis.get(j).ajouterNoeudVisite(chemin.getArrivee());
					listeFourmis.get(j).setEtat(Etat.ParcoursGraphe);
					cheminTrouve = true;
					cheminDejaPris.add(random);
				}else if(cheminDejaPris.size() > listeDestinations.size())
				{
					ArreteList chemin = listeDestinations.get(random);
					listeFourmis.get(j).ajouterNoeudVisite(chemin.getArrivee());
					listeFourmis.get(j).setEtat(Etat.ParcoursGraphe);
					cheminTrouve = true;
				}			
			}
			cheminTrouve = false;
		}
	}
	
	/*
	 * Procédure permettant de créer l'ensemble des fourmis et d'exécuter l'algorithme des fourmis
	 */
	public void traiterProbleme(NoeudList noeudDepart, int nbrePheromoneADeposer, int noeudArrivee)
	{
		this.creerFourmis(noeudDepart,noeudArrivee, nbrePheromoneADeposer);
		this.affecterPremierNoeud();
		
		for(int i=0; i < nbreIterations;i++)
		{
			for(int j=0; j < listeFourmis.size();j++)
			{
				if(listeFourmis.get(j).getEtat()== Etat.ParcoursGraphe)
					listeFourmis.get(j).trouverChemin();
				else if(listeFourmis.get(j).getEtat()== Etat.Rentre)
				{
					//System.out.println("La fourmis rentre !");
					listeFourmis.get(j).rentrer();
				}
			}
			if((i % vitesseEvapPheromone) == 0 && i != 0)
				this.misAJourPheromone();
			//System.out.println("Affichage des pheromones :"+i);
			//resultant.afficherGraphe();
		}
	}
	
	/*
	 * Procédure permettant de déposer du phéromone sur une arrête dont les noeuds sont passés en paramètre
	 */
	public void deposerPheromone(NoeudList noeudDepart, NoeudList noeudArrivee, int nbrePheromones)
	{
		resultant.setPoids(noeudDepart, noeudArrivee, resultant.getPoids(noeudDepart, noeudArrivee)+ nbrePheromones);
	}
	
	/*
	 * Procédure mettant à jour les pheromones déposés sur les chemins
	 */
	public void misAJourPheromone()
	{
		double nouveauPheromone = 0.0;
		double pheromoneEnCours =0.0;
		for(int i =0; i < resultant.getNoeuds().size();i++)
		{
			ArrayList<ArreteList> listeArretes = resultant.getNoeuds().get(i).getDestinations();
			for(int j =0; j < listeArretes.size() ;j++)
			{
				pheromoneEnCours = listeArretes.get(j).getPoids();
				nouveauPheromone = pheromoneEnCours - this.nbrePheromoneAEvap; 
				if(nouveauPheromone < 0)
					listeArretes.get(j).setPoids(0);
				else
					listeArretes.get(j).setPoids(nouveauPheromone);
			}
		}
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

	public void setNbrePheromoneAEvap(double nbrePheromoneAEvap) {
		this.nbrePheromoneAEvap = nbrePheromoneAEvap;
	}

	public double getNbrePheromoneAEvap() {
		return nbrePheromoneAEvap;
	}

}
