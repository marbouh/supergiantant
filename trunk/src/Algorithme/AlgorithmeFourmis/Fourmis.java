package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

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
	private ArrayList<Integer> listeNoeudsVisites;
	private Etat etat;
	private AlgoFourmis algo;
	
	public Fourmis(int vitesseEvapPheromone,int nbrePheromoneDeposes, AlgoFourmis algo)
	{
		this.vitesseEvapPheromone = vitesseEvapPheromone;
		this.nbrePheromonesADeposer = nbrePheromoneDeposes;
		//this.setEtat(Etat.CherchePremierNoeud);
		this.setEtat(Etat.ParcoursGraphe);
		this.listeNoeudsVisites = new ArrayList<Integer>();
		this.setAlgo(algo);
	}
	
	// Non fonctionnel
	public void trouverChemin()
	{
		int poidsMinimum = 9999999;
		int noeudSuivant =-1;
		/* Le noeud où se situe la fourmis est le dernier noeud qui a été visité */
		int noeudCourant = listeNoeudsVisites.get(listeNoeudsVisites.size()-1);
		if(etat == Etat.ParcoursGraphe)
		{
			for(int i = 0; i < algo.getNbreNoeuds();i++)
			{//On regarde s'il y a un chemin entre la ville où est la fourmis et une autre ville
				/*int poids = algo.recupererPoids(noeudCourant, i);
				if(poids != 0)
				{
					if(!aDejaEteVisite(i) && poidsMinimum > poids)
					{
						poidsMinimum = poids;
						noeudSuivant = i;
					}
				}*/
			}
			if(noeudSuivant != -1)
			{
				listeNoeudsVisites.add(noeudSuivant);
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
	
	public boolean aDejaEteVisite(int noeud)
	{
		for(int i = 0;i < listeNoeudsVisites.size();i++)
		{
			if(listeNoeudsVisites.get(i) == noeud)
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
	public void setListeNoeudsVisites(ArrayList<Integer> listeNoeudsVisites) {
		this.listeNoeudsVisites = listeNoeudsVisites;
	}
	public ArrayList<Integer> getListeNoeudsVisites() {
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
