package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Graphe.NoeudList;

/**
 *  Class Fourmis : Représente une entitée fourmis dans l'algorithme des fourmis
 */
public class Fourmis
{
	enum Etat{ 
		CherchePremierNoeud,
		ParcoursGraphe, 
		Rentre
	};
	private int nbrePheromonesADeposer;
	private ArrayList<NoeudList> listeNoeudsVisites;
	private Etat etat;
	private AlgoFourmis algo;
	private NoeudList noeudArrivee;//noeud où doit arriver la fourmis
	
	/*
	 * Constructeur de la classe fourmis
	 */
	public Fourmis(int nbrePheromoneDeposes,int noeudArrivee, AlgoFourmis algo)
	{
		this.nbrePheromonesADeposer = nbrePheromoneDeposes;
		this.noeudArrivee = new NoeudList(noeudArrivee);
		this.setEtat(Etat.CherchePremierNoeud);
		this.listeNoeudsVisites = new ArrayList<NoeudList>();
		this.setAlgo(algo);
	}
	
	/*
	 * Procédure permettant à une fourmis de choisir le chemin qu'elle doit emprunter (soit le prochain noeud à prendre)
	 */
	public void trouverChemin()
	{
		double poidsMinimum = 9999999;
		double pheromoneMaximum = 0;
		NoeudList noeudSuivant = null;
		NoeudList noeudPossible = null;
		/* Le noeud où se situe la fourmis est le dernier noeud qui a été visité */
		NoeudList noeudCourant = listeNoeudsVisites.get(listeNoeudsVisites.size()-1);
		ArrayList<NoeudList> listeNoeudSuivant = algo.getProbleme().getSuivants(noeudCourant);
		for(int i = 0; i < listeNoeudSuivant.size();i++)
		{//On parcours la liste des chemins possibles depuis le noeud courant où est la fourmi
			noeudPossible = listeNoeudSuivant.get(i);
			double poids = algo.getProbleme().getPoids(noeudCourant, noeudPossible);
			double pheromone = algo.getResultant().getPoids(noeudCourant, noeudPossible);
			
			if(poids > 0)//s'il y a un chemin correct entre les deux noeuds
			{
				if(!aDejaEteVisite(noeudPossible) && (poidsMinimum > poids || pheromone > pheromoneMaximum))
				{
					poidsMinimum = poids;
					pheromoneMaximum = pheromone;
					noeudSuivant = noeudPossible;
				}
			}
		}
		if(noeudSuivant != null)
		{
			ajouterNoeudVisite(noeudSuivant);
			if(noeudSuivant.compareTo(getNoeudArrivee()))
				setEtat(Etat.Rentre);
		}
		else//on n'a pas trouvé d'autre noeud donc la fourmis doit rentrer
			setEtat(Etat.Rentre);
	}
	
	/*
	 * Procédure faisant rentrer une fourmis à la colonie en déposant des phéromones
	 */
	public void rentrer()
	{
		NoeudList noeudDepart = null;
		NoeudList noeudArrivee = null;
		if(etat == Etat.Rentre)
		{
			int dernierElement = listeNoeudsVisites.size()-1;
			if(dernierElement > 0)
			{	
				for(int j = 0;j < algo.getResultant().getNbreNoeuds() ;j++)
				{
					if(algo.getResultant().getNoeuds().get(j).compareTo(listeNoeudsVisites.get(dernierElement)))
					{
						noeudDepart = algo.getResultant().getNoeuds().get(j);
					}
					if(algo.getResultant().getNoeuds().get(j).compareTo(listeNoeudsVisites.get(dernierElement-1)))
					{
						noeudArrivee = algo.getResultant().getNoeuds().get(j);
					}
				}
				if(noeudDepart != null && noeudArrivee != null)
				{
					algo.deposerPheromone(noeudDepart,noeudArrivee, this.nbrePheromonesADeposer);
					listeNoeudsVisites.remove(dernierElement);
				}
			}
			else if(dernierElement == 0)
			{
				this.reinitialiserFourmis();
			}
			/*for(int i = listeNoeudsVisites.size()-1;i > 0;i--)
			{
				for(int j = 0;j < algo.getResultant().getNbreNoeuds() ;j++)
				{
					if(algo.getResultant().getNoeuds().get(j).compareTo(listeNoeudsVisites.get(i)))
					{
						noeudDepart = algo.getResultant().getNoeuds().get(j);
					}
					if(algo.getResultant().getNoeuds().get(j).compareTo(listeNoeudsVisites.get(i-1)))
					{
						noeudArrivee = algo.getResultant().getNoeuds().get(j);
					}
				}
				if(noeudDepart != null && noeudArrivee != null)
				{
					algo.deposerPheromone(noeudDepart,noeudArrivee, this.nbrePheromonesADeposer);
				}
			}
			this.reinitialiserFourmis();*/
		}
	}
	
	/*
	 * Procédure pour réinitialiser une fourmis qui vient de rentrer à la colonie afin qu'elle puisse repartir
	 */
	public void reinitialiserFourmis()
	{
		this.etat = Etat.ParcoursGraphe;
		NoeudList noeudDepart = this.listeNoeudsVisites.get(0);
		this.listeNoeudsVisites.clear();
		this.listeNoeudsVisites.add(noeudDepart);
	}
	
	/*
	 * Procédure ajoutant un noeud à la liste des noeuds visités par la fourmis
	 */
	public void ajouterNoeudVisite(NoeudList noeud)
	{
		listeNoeudsVisites.add(noeud);
	}
	
	/*
	 * Fonction permettant de déterminer si une fourmis a déjà visité le noeud passé en paramètre
	 * La fonction retourne vrai si la fourmis a déja visité ce noeud et faux sinon 
	 */
	public boolean aDejaEteVisite(NoeudList noeud)
	{
		for(int i = 0;i < listeNoeudsVisites.size();i++)
		{
			if(listeNoeudsVisites.get(i).compareTo(noeud))
				return true;			
		}
		return false;
	}
	
	/* Getters et setters des attributs de la classe */
	
	public int getNbrePheromonesADeposer() {
		return nbrePheromonesADeposer;
	}
	public void setNbrePheromonesADeposer(int nbrePheromonesADeposer) {
		this.nbrePheromonesADeposer = nbrePheromonesADeposer;
	}
	public void setListeNoeudsVisites(ArrayList<NoeudList> listeNoeudsVisites) {
		this.listeNoeudsVisites = listeNoeudsVisites;
	}
	public ArrayList<NoeudList> getListeNoeudsVisites() {
		return listeNoeudsVisites;
	}
	public void setAlgo(AlgoFourmis algo) {
		this.algo = algo;
	}
	public AlgoFourmis getAlgo() {
		return algo;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	public Etat getEtat() {
		return etat;
	}

	public void setNoeudArrivee(NoeudList noeudArrivee) {
		this.noeudArrivee = noeudArrivee;
	}

	public NoeudList getNoeudArrivee() {
		return noeudArrivee;
	}

}
