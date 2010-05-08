package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Graphe.NoeudList;

/**
 *  Class Fourmis : Représente une entitée fourmis dans l'algorithme des fourmis
 */
public class Fourmis
{
	private static int CONSTANTE = 50;
	//Paramètre de la règle aléatoire de transition proportionnelle
	private static double ALPHA = 1;//0.666666666;//paramètre pour l'intensité de la piste de phéromone
	private static double BETA = 4;//0.333333333;//paramètre pour la visibilité des noeuds (1/distance)
	
	enum Etat{ 
		CherchePremierNoeud,
		ParcoursGraphe, 
		Rentre
	};
	
	private double distanceParcourue;
	private ArrayList<NoeudList> listeNoeudsVisites;
	private Etat etat;
	private AlgoFourmis algo;
	
	/*
	 * Constructeur de la classe fourmis
	 */
	public Fourmis(AlgoFourmis algo)
	{
		this.distanceParcourue = 0.0;
		this.setEtat(Etat.CherchePremierNoeud);
		this.listeNoeudsVisites = new ArrayList<NoeudList>();
		this.setAlgo(algo);
	}
	
	/*
	 * Procédure permettant à une fourmis de choisir le chemin qu'elle doit emprunter (soit le prochain noeud à prendre)
	 */
	public void trouverChemin()
	{
		NoeudList noeudSuivant = null;
		NoeudList noeudPossible = null;
		
		double somme = 0;
		double proba = 0;
		double ancienneProba =0;
		double poidsCoeff = 0.0;
		double pheroCoeff = 0.0;
		
		/* Le noeud où se situe la fourmis est le dernier noeud qui a été visité */
		NoeudList noeudCourant = listeNoeudsVisites.get(listeNoeudsVisites.size()-1);
		ArrayList<NoeudList> listeNoeudSuivant = algo.getProbleme().getSuivants(noeudCourant);
		
		for(int i = 0; i < listeNoeudSuivant.size();i++)
		{//On parcours la liste des chemins possibles depuis le noeud courant où est la fourmi
			noeudPossible = listeNoeudSuivant.get(i);
						
			if(!aDejaEteVisite(noeudPossible))//si le noeud n'a pas déjà été visité
			{
				double poids = algo.getProbleme().getPoids(noeudCourant, noeudPossible);
				double pheromone = algo.obtenirSolution().getPoids(noeudCourant, noeudPossible);
				
				for(int j =0;j < listeNoeudSuivant.size() ;j++)
				{
					if(!noeudPossible.compareTo(listeNoeudSuivant.get(j)))
					{
						pheroCoeff = Math.pow(algo.obtenirSolution().getPoids(noeudCourant, listeNoeudSuivant.get(j)),ALPHA);
						poidsCoeff = Math.pow(algo.getProbleme().getPoids(noeudCourant, listeNoeudSuivant.get(j)),BETA);
						somme += (pheroCoeff/poidsCoeff);	
					}
				}
				
				proba = (Math.pow(pheromone,ALPHA)/(Math.pow(poids,BETA)))/somme;
				if(proba >= ancienneProba)
				{
					noeudSuivant = noeudPossible;
					ancienneProba = proba;
				}
			}
		}
		if(noeudSuivant != null)
		{
			ajouterNoeudVisite(noeudSuivant);
			distanceParcourue += algo.getProbleme().getPoids(noeudCourant, noeudSuivant);
		}
		else//on n'a pas trouvé d'autre noeud donc la fourmis doit rentrer
			setEtat(Etat.Rentre);
	}
	
	
	/*public void trouverChemin()
	{
		double precPheromone = 0;
		boolean aDejaEteParcouru = true;
		
		NoeudList noeudSuivant = null;
		NoeudList noeudPossible = null;
				
		double somme = 0;
		double proba = 0;
		double ancienneProba =0;

		
		// Le noeud où se situe la fourmis est le dernier noeud qui a été visité
		NoeudList noeudCourant = listeNoeudsVisites.get(listeNoeudsVisites.size()-1);
		ArrayList<NoeudList> listeNoeudSuivant = algo.getProbleme().getSuivants(noeudCourant);
		
		for(int i = 0; i < listeNoeudSuivant.size();i++)
		{//On parcours la liste des chemins possibles depuis le noeud courant où est la fourmi
			noeudPossible = listeNoeudSuivant.get(i);
						
			if(!aDejaEteVisite(noeudPossible))//si le noeud n'a pas déjà été visité
			{
				double poids = algo.getProbleme().getPoids(noeudCourant, noeudPossible);
				double pheromone = algo.obtenirSolution().getPoids(noeudCourant, noeudPossible);
				
				for(int j =0;j < listeNoeudSuivant.size() ;j++)
				{
					if(!noeudPossible.compareTo(listeNoeudSuivant.get(j)))
					{
						double pheroCoeff = Math.pow(algo.obtenirSolution().getPoids(noeudCourant, listeNoeudSuivant.get(j)),ALPHA);
						if(pheroCoeff == 0)
							aDejaEteParcouru = aDejaEteParcouru && true;
						else
							aDejaEteParcouru = aDejaEteParcouru && false;
						double poidsCoeff = Math.pow(algo.getProbleme().getPoids(noeudCourant, listeNoeudSuivant.get(j)),BETA);
						//if(pheroCoeff != 0)
							somme += (pheroCoeff/poidsCoeff);
						//else
					//		somme += 1 / poidsCoeff;
					}
				}
				if(pheromone == 0.0 && aDejaEteParcouru == true)//si le noeud n'a jamais été emprunté par aucune fourmis
				{
					noeudSuivant = noeudPossible;
				}else
				{
					proba = (Math.pow(pheromone,ALPHA)/(Math.pow(poids,BETA)))/somme;
					if(proba >= ancienneProba)
						noeudSuivant = noeudPossible;
					ancienneProba = proba;
				}
				
				precPheromone = pheromone;
			}
		}
		if(noeudSuivant != null)
		{
			ajouterNoeudVisite(noeudSuivant);
			distanceParcourue += algo.getProbleme().getPoids(noeudCourant, noeudSuivant);
		}
		else//on n'a pas trouvé d'autre noeud donc la fourmis doit rentrer
			setEtat(Etat.Rentre);
	}*/
	
	/*
	 * Procédure faisant rentrer une fourmis à la colonie en déposant des phéromones
	 */
	public void rentrer()
	{
		NoeudList noeudDepart = null;
		NoeudList noeudArrivee = null;
		double nbrePheromoneADeposer = 0.0;
		if(etat == Etat.Rentre)
		{
			int dernierElement = listeNoeudsVisites.size()-1;
			if(dernierElement > 0)
			{	
				for(int j = 0;j < algo.obtenirSolution().getNbreNoeuds() ;j++)
				{
					if(algo.obtenirSolution().getNoeuds().get(j).compareTo(listeNoeudsVisites.get(dernierElement)))
					{
						noeudDepart = algo.obtenirSolution().getNoeuds().get(j);
					}
					if(algo.obtenirSolution().getNoeuds().get(j).compareTo(listeNoeudsVisites.get(dernierElement-1)))
					{
						noeudArrivee = algo.obtenirSolution().getNoeuds().get(j);
					}
				}
				if(noeudDepart != null && noeudArrivee != null)
				{
					if(this.listeNoeudsVisites.size() == this.getAlgo().getNbreNoeuds())
						nbrePheromoneADeposer = CONSTANTE - this.distanceParcourue;
					else
						nbrePheromoneADeposer = CONSTANTE - (this.distanceParcourue + 0.5 * this.distanceParcourue);
					if(nbrePheromoneADeposer <= 0)
						nbrePheromoneADeposer = 1;
					algo.deposerPheromone(noeudDepart,noeudArrivee, nbrePheromoneADeposer);
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
		this.distanceParcourue = 0;
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
	public double getDistanceParcourue() {
		return distanceParcourue;
	}

	public void setDistanceParcourue(double distanceParcourue) {
		this.distanceParcourue = distanceParcourue;
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

}
