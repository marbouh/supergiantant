package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Algorithme;
import Algorithme.AlgorithmeFourmis.Fourmis.Etat;
import Algorithme.Graphe.ArreteList;
import Algorithme.Graphe.Graphe;
import Algorithme.Graphe.GrapheList;
import Algorithme.Graphe.NoeudList;

/**
 *  Class AlgoFourmis : 
 */
public class AlgoFourmis extends Algorithme{

	private int nbreFourmis;
	private int nbreIterations;
	private int vitesseEvapPheromone;
	private double nbrePheromoneAEvap;
	private ArrayList<Fourmis> listeFourmis;
	private NoeudList noeudDeDepart;
	private Graphe probleme;
	private Graphe solution;//c'est dans ce graphe que l'on dépose le phéromone
	
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
		
		solution = probleme.copierGraphe() ;
		solution.viderInformations();//On efface tous les poids des arrêtes afin de déposer le phéromone
	}
	
	/*
	 * Fonction permettant de créer l'ensemble des fourmis qui seront utilisées pour l'algorithme
	 */
	public void creerFourmis()
	{
		for(int j=0; j < nbreFourmis;j++)
		{
			Fourmis f = new Fourmis(this);
			f.ajouterNoeudVisite(noeudDeDepart);
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
			
				if(!cheminDejaPris.contains(random) && cheminDejaPris.size() < listeDestinations.size())
				{
					ArreteList chemin = listeDestinations.get(random);
					listeFourmis.get(j).ajouterNoeudVisite(chemin.getArrivee());
					listeFourmis.get(j).setEtat(Etat.ParcoursGraphe);
					cheminTrouve = true;
					cheminDejaPris.add(random);
				}else if(cheminDejaPris.size() >= listeDestinations.size())
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
	public void traiterProbleme()
	{
		this.start();
		this.creerFourmis();
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
		this.stop();
		
	}
	
	/*
	 * Procédure permettant de déposer du phéromone sur une arrête dont les noeuds sont passés en paramètre
	 */
	public void deposerPheromone(NoeudList noeudDepart, NoeudList noeudArrivee, double nbrePheromones)
	{
		solution.setPoids(noeudDepart, noeudArrivee, solution.getPoids(noeudDepart, noeudArrivee)+ nbrePheromones);
		solution.setPoids(noeudArrivee, noeudDepart, solution.getPoids(noeudDepart, noeudArrivee));//mis à jour de l'arrête dont les noeuds sont inversés par rapport à la première arrête
	}
	
	/*
	 * Procédure mettant à jour les pheromones déposés sur les chemins
	 */
	public void misAJourPheromone()
	{
		double nouveauPheromone = 0.0;
		double pheromoneEnCours =0.0;
		for(int i =0; i < solution.getNoeuds().size();i++)
		{
			ArrayList<ArreteList> listeArretes = solution.getNoeuds().get(i).getDestinations();
			for(int j =0; j < listeArretes.size() ;j++)
			{
				pheromoneEnCours = listeArretes.get(j).getPoids();
				nouveauPheromone = pheromoneEnCours - pheromoneEnCours*this.nbrePheromoneAEvap; 
				if(nouveauPheromone < 0)
					listeArretes.get(j).setPoids(0);
				else
					listeArretes.get(j).setPoids(nouveauPheromone);
			}
		}
		//System.out.println("Mis à jour des pheromones");
	}
	
	/*
	 * Procédure affiche résultat 
	 */
	public String afficherSolution(NoeudList noeud,int nbreNoeud, ArrayList<NoeudList> noeudsVisites)
	{
		String chemin = "";
		double precPheromone = 0.0;
		NoeudList noeudDepS = null;
		NoeudList noeudSuivant = null;
				
		if(nbreNoeud > 0)
		{	
			if(noeud!=null)
			{	
				noeudsVisites.add(noeud);
				chemin = ""+noeud.getId();
				
				noeudDepS = NoeudList.trouverNoeud(this.solution.getNoeuds(), noeud.getId());
									
				if(noeudDepS != null)
				{
					ArrayList<ArreteList> listeArretes = noeudDepS.getDestinations();
					for(int i=0; i < listeArretes.size();i++)
					{
						if(NoeudList.trouverNoeud(noeudsVisites, listeArretes.get(i).getArrivee().getId()) == null)//le noeud n'a pas encore été visité
						{
							if(precPheromone < listeArretes.get(i).getPoids())
							{
								noeudSuivant = listeArretes.get(i).getArrivee();
								precPheromone = listeArretes.get(i).getPoids();
							}
						}
					}
					if(noeudSuivant != null)
					{
						chemin += " , " + afficherSolution(noeudSuivant,nbreNoeud-1,noeudsVisites);
					}
				}				
			}
		}else
			return "";
		return chemin;
	}
	
	/*
	 * Fonction permettant de savoir si une arrete(dont les noeuds(départ et arrivée) sont inversés) est présente dans une liste
	 */
	public boolean arreteInverseePresente(ArrayList<ArreteList> listeArretes, ArreteList a)
	{
		for(int i=0; i<listeArretes.size();i++)
		{
			if(listeArretes.get(i).getArrivee().compareTo(a.getDepart()))
			{
				if(listeArretes.get(i).getDepart().compareTo(a.getArrivee()))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	/*
	 * Fonction permettant d'obtenir la distance du plus court chemin trouvé
	 */
	public double obtenirDistance() 
	{
		ArrayList<NoeudList> listeNoeuds = new ArrayList<NoeudList>();
		double distance = calculDistance(getNoeudDeDepart(), getNbreNoeuds(),listeNoeuds);
		
		return distance;
	}
	
	private double calculDistance(NoeudList noeud,int nbreNoeud, ArrayList<NoeudList> noeudsVisites)
	{
		double distanceTotal = 0.0;
		double precPheromone = 0.0;
		double poids = 0.0;
	
		NoeudList noeudDepS = null;
		NoeudList noeudDepP = null;
		NoeudList noeudSuivant = null;
		
		if(nbreNoeud > 0)
		{	
			if(noeud!=null)
			{	
				noeudsVisites.add(noeud);
				noeudDepS = NoeudList.trouverNoeud(this.solution.getNoeuds(), noeud.getId());
				noeudDepP = NoeudList.trouverNoeud(this.probleme.getNoeuds(), noeud.getId());
					
				if(noeudDepS != null && noeudDepP != null)
				{
					ArrayList<ArreteList> listeArretes = noeudDepS.getDestinations();
					for(int i=0; i < listeArretes.size();i++)
					{
						if(NoeudList.trouverNoeud(noeudsVisites, listeArretes.get(i).getArrivee().getId()) == null)//le noeud n'a pas encore été visité
						{
							if(precPheromone < listeArretes.get(i).getPoids())
							{
								poids = noeudDepP.getDestinations().get(i).getPoids();
								noeudSuivant = listeArretes.get(i).getArrivee();
								precPheromone = listeArretes.get(i).getPoids();
							}
						}
					}
					if(noeudSuivant != null)
					{
						distanceTotal += poids + calculDistance(noeudSuivant,nbreNoeud-1,noeudsVisites);
					}
				}				
			}
		}else
			return 0;
		return distanceTotal;
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
	public void setSolution(Graphe solution) {
		this.solution = solution;
	}
	public GrapheList obtenirSolution() {
		return (GrapheList)solution;
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

	public NoeudList getNoeudDeDepart() {
		return noeudDeDepart;
	}

	public void setNoeudDeDepart(NoeudList noeudDeDepart) {
		this.noeudDeDepart = noeudDeDepart;
	}
	
}
