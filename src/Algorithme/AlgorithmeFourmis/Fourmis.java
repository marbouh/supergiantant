package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Graphe.NoeudList;

/**
 *  Class Fourmis : Repr�sente une entit�e fourmis dans l'algorithme des fourmis
 */
public class Fourmis
{
	private static int CONSTANTE = 50;
	//Param�tre de la r�gle al�atoire de transition proportionnelle
	private static double ALPHA = 1;//param�tre pour l'intensit� de la piste de ph�romone
	private static double BETA = 3;//param�tre pour la visibilit� des noeuds (1/distance)
	
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
		this.modifierEtat(Etat.CherchePremierNoeud);
		this.listeNoeudsVisites = new ArrayList<NoeudList>();
		this.modifierAlgo(algo);
	}
	
	/*
	 * Proc�dure permettant � une fourmis de choisir le chemin qu'elle doit emprunter (soit le prochain noeud � prendre)
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
		double distance = 0.0;
		
		/* Le noeud o� se situe la fourmis est le dernier noeud qui a �t� visit� */
		NoeudList noeudCourant = listeNoeudsVisites.get(listeNoeudsVisites.size()-1);
		ArrayList<NoeudList> listeNoeudSuivant = algo.obtenirProbleme().obtenirSuivants(noeudCourant);
		
		for(int i = 0; i < listeNoeudSuivant.size();i++)
		{//On parcours la liste des chemins possibles depuis le noeud courant o� est la fourmi
			noeudPossible = listeNoeudSuivant.get(i);
						
			if(!aDejaEteVisite(noeudPossible))//si le noeud n'a pas d�j� �t� visit�
			{
				double poids = algo.obtenirProbleme().obtenirPoids(noeudCourant, noeudPossible);
				double pheromone = algo.obtenirSolution().obtenirPoids(noeudCourant, noeudPossible);
				
				for(int j =0;j < listeNoeudSuivant.size() ;j++)
				{
					if(!noeudPossible.compareTo(listeNoeudSuivant.get(j)))
					{
						pheroCoeff = Math.pow(algo.obtenirSolution().obtenirPoids(noeudCourant, listeNoeudSuivant.get(j)),ALPHA);
						poidsCoeff = Math.pow(1/algo.obtenirProbleme().obtenirPoids(noeudCourant, listeNoeudSuivant.get(j)),BETA);
						somme += (pheroCoeff*poidsCoeff);	
					}
				}
				
				proba = (Math.pow(pheromone,ALPHA)*(Math.pow(1/poids,BETA)))/somme;
				if(proba >= ancienneProba)
				{
					noeudSuivant = noeudPossible;
					ancienneProba = proba;
					distance = poids;
				}
			}
		}
		if(noeudSuivant != null)
		{
			ajouterNoeudVisite(noeudSuivant);
			distanceParcourue += algo.obtenirProbleme().obtenirPoids(noeudCourant, noeudSuivant);
		}
		else//on n'a pas trouv� d'autre noeud donc la fourmis doit rentrer
			modifierEtat(Etat.Rentre);
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

		
		// Le noeud o� se situe la fourmis est le dernier noeud qui a �t� visit�
		NoeudList noeudCourant = listeNoeudsVisites.get(listeNoeudsVisites.size()-1);
		ArrayList<NoeudList> listeNoeudSuivant = algo.obtenirProbleme().obtenirSuivants(noeudCourant);
		
		for(int i = 0; i < listeNoeudSuivant.size();i++)
		{//On parcours la liste des chemins possibles depuis le noeud courant o� est la fourmi
			noeudPossible = listeNoeudSuivant.get(i);
						
			if(!aDejaEteVisite(noeudPossible))//si le noeud n'a pas d�j� �t� visit�
			{
				double poids = algo.obtenirProbleme().obtenirPoids(noeudCourant, noeudPossible);
				double pheromone = algo.obtenirSolution().obtenirPoids(noeudCourant, noeudPossible);
				
				for(int j =0;j < listeNoeudSuivant.size() ;j++)
				{
					if(!noeudPossible.compareTo(listeNoeudSuivant.get(j)))
					{
						double pheroCoeff = Math.pow(algo.obtenirSolution().obtenirPoids(noeudCourant, listeNoeudSuivant.get(j)),ALPHA);
						if(pheroCoeff == 0)
							aDejaEteParcouru = aDejaEteParcouru && true;
						else
							aDejaEteParcouru = aDejaEteParcouru && false;
						double poidsCoeff = Math.pow(algo.getProbleme().obtenirPoids(noeudCourant, listeNoeudSuivant.get(j)),BETA);
						//if(pheroCoeff != 0)
							somme += (pheroCoeff/poidsCoeff);
						//else
					//		somme += 1 / poidsCoeff;
					}
				}
				if(pheromone == 0.0 && aDejaEteParcouru == true)//si le noeud n'a jamais �t� emprunt� par aucune fourmis
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
			distanceParcourue += algo.obtenirProbleme().obtenirPoids(noeudCourant, noeudSuivant);
		}
		else//on n'a pas trouv� d'autre noeud donc la fourmis doit rentrer
			modifierEtat(Etat.Rentre);
	}*/
	
	/*
	 * Proc�dure faisant rentrer une fourmis � la colonie en d�posant des ph�romones
	 */
	public void rentrer()
	{
		NoeudList noeudDepart = null;
		NoeudList noeudArrivee = null;
		NoeudList noeudPossible = null;
		double nbrePheromoneADeposer = 0.0;
		
		int dernierElement = listeNoeudsVisites.size()-1;
		if(dernierElement > 0)
		{	
			for(int j = 0;j < algo.obtenirSolution().obtenirNbreNoeuds() ;j++)
			{
				noeudPossible = algo.obtenirSolution().obtenirNoeuds().get(j);
				if(noeudPossible.compareTo(listeNoeudsVisites.get(dernierElement)))
				{
					noeudDepart = noeudPossible;
				}
				if(noeudPossible.compareTo(listeNoeudsVisites.get(dernierElement-1)))
				{
					noeudArrivee = noeudPossible;
				}
			}
			if(noeudDepart != null && noeudArrivee != null)
			{
				if(this.listeNoeudsVisites.size() == this.obtenirAlgo().obtenirNbreNoeuds())
					nbrePheromoneADeposer = CONSTANTE - this.distanceParcourue;
				else//si jamais une fourmis n'a pas pu visiter tous les noeuds alors on augmente la distance parcourue de moiti�
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
	}
	
	/*
	 * Proc�dure pour r�initialiser une fourmis qui vient de rentrer � la colonie afin qu'elle puisse repartir
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
	 * Proc�dure ajoutant un noeud � la liste des noeuds visit�s par la fourmis
	 */
	public void ajouterNoeudVisite(NoeudList noeud)
	{
		listeNoeudsVisites.add(noeud);
	}
	
	/*
	 * Fonction permettant de d�terminer si une fourmis a d�j� visit� le noeud pass� en param�tre
	 * La fonction retourne vrai si la fourmis a d�ja visit� ce noeud et faux sinon 
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
	public double obtenirDistanceParcourue() {
		return distanceParcourue;
	}

	public void modifierDistanceParcourue(double distanceParcourue) {
		this.distanceParcourue = distanceParcourue;
	}
	
	public void modifierListeNoeudsVisites(ArrayList<NoeudList> listeNoeudsVisites) {
		this.listeNoeudsVisites = listeNoeudsVisites;
	}
	
	public ArrayList<NoeudList> obtenirListeNoeudsVisites() {
		return listeNoeudsVisites;
	}
	public void modifierAlgo(AlgoFourmis algo) {
		this.algo = algo;
	}
	public AlgoFourmis obtenirAlgo() {
		return algo;
	}
	public void modifierEtat(Etat etat) {
		this.etat = etat;
	}
	public Etat obtenirEtat() {
		return etat;
	}

}
