package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Graphe.NoeudList;

/**
 *  Class Fourmis : ReprÃ©sente une entitÃ©e fourmis dans l'algorithme des fourmis
 */
public class Fourmis
{
	enum Etat{ 
		CherchePremierNoeud,
		ParcoursGraphe, 
		Rentre
	};
	private int vitesseEvapPheromone;
	private int nbrePheromonesADeposer;
	private ArrayList<NoeudList> listeNoeudsVisites;
	private Etat etat;
	private AlgoFourmis algo;
	
	public Fourmis(int vitesseEvapPheromone,int nbrePheromoneDeposes, AlgoFourmis algo)
	{
		this.vitesseEvapPheromone = vitesseEvapPheromone;
		this.nbrePheromonesADeposer = nbrePheromoneDeposes;
		this.setEtat(Etat.CherchePremierNoeud);
		//this.setEtat(Etat.ParcoursGraphe);
		this.listeNoeudsVisites = new ArrayList<NoeudList>();
		this.setAlgo(algo);
	}
	
	// Non fonctionnel
	public void trouverChemin()
	{
		double poidsMinimum = 9999999;
		NoeudList noeudSuivant = null;
		NoeudList noeudPossible = null;
		/* Le noeud où se situe la fourmis est le dernier noeud qui a été visité */
		NoeudList noeudCourant = listeNoeudsVisites.get(listeNoeudsVisites.size()-1);
		if(etat == Etat.ParcoursGraphe)
		{
			for(int i = 0; i < algo.getProbleme().getSuivants(noeudCourant).size();i++)
			{//On parcours la liste des chemins possibles depuis le noeud courant où est la fourmis
				noeudPossible = (NoeudList) algo.getProbleme().getSuivants(noeudCourant).get(i);
				double poids = algo.getProbleme().getPoids(noeudCourant, noeudSuivant);
				
				if(poids != 0)
				{
					if(!aDejaEteVisite(noeudPossible) && poidsMinimum > poids)
					{
						poidsMinimum = poids;
						noeudSuivant = noeudPossible;
					}
				}
			}
			if(noeudSuivant != null)
			{
				ajouterNoeudVisite(noeudSuivant);
			}
			else//on n'a pas trouvé d'autre noeud donc la fourmis doit rentrer
				setEtat(Etat.Rentre);
		}
	}
	
	public void rentrer()
	{
		if(etat == Etat.Rentre)
		{
			for(int j = listeNoeudsVisites.size()-1;j > 0;j--)
			{
				algo.deposerPheromone(listeNoeudsVisites.get(j),listeNoeudsVisites.get(j-1), this.nbrePheromonesADeposer);
			}
		}
	}
	
	public void ajouterNoeudVisite(NoeudList noeud)
	{
		listeNoeudsVisites.add(noeud);
	}
	
	public boolean aDejaEteVisite(NoeudList noeud)
	{
		for(int i = 0;i < listeNoeudsVisites.size();i++)
		{
			if(listeNoeudsVisites.get(i).compareTo(noeud))
				return true;			
		}
		return false;
	}
	
	public int getVitesseEvapPheromone() {
		return vitesseEvapPheromone;
	}
	public void setVitesseEvapPheromone(int vitesseEvapPheromone) {
		this.vitesseEvapPheromone = vitesseEvapPheromone;
	}
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

}
