package Algorithme.Graphe;

import java.util.ArrayList;

/**
 * Class GraphList
 */
public class GrapheList implements Graphe
{
	private String nom;
	private ArrayList<NoeudList> noeuds;
	private double inf;
	
	/*
	 * Constructeur de la classe GrapheList
	 */
	public GrapheList(String nom, int nbreNoeuds)
	{
		this.nom = nom;
		inf = -1;
		noeuds = new ArrayList<NoeudList>(nbreNoeuds);
	}

	/*
	 * Fonction retournant la copie conforme du graphe qui appelle la méthode
	 */
	public GrapheList copierGraphe()
	{
		GrapheList copie = new GrapheList(this.obtenirNom(),this.obtenirNbreNoeuds());
		ArrayList<NoeudList> listeNoeuds = new ArrayList<NoeudList>();
		//On créer la liste des noeuds (sans les arrêtes)
		for(int i = 0; i < this.obtenirNbreNoeuds(); i++)
		{
			NoeudList copieNoeud = new NoeudList(this.obtenirNoeuds().get(i).obtenirId());
			listeNoeuds.add(copieNoeud);
		}
		
		for(int i = 0; i < this.obtenirNbreNoeuds(); i++)
		{
			copie.obtenirNoeuds().add(this.obtenirNoeuds().get(i).copierNoeud(listeNoeuds));
		}
		return copie;
	}
	
	/*
	 * Fonction retournant la liste des noeuds suivants d'un noeud donné
	 */
	public ArrayList<NoeudList> obtenirSuivants(NoeudList noeud)
	{
		ArrayList<NoeudList> listeNoeuds = new ArrayList<NoeudList>();
		ArrayList<ArreteList> listeArretes = noeud.obtenirDestinations();
		
		for(int i=0; i < listeArretes.size();i++)
		{
			listeNoeuds.add(listeArretes.get(i).obtenirArrivee());
		}
		return listeNoeuds;
	}

	/*
	 * Fonction vérifiant qu'il y a un chemin (arrête) entre les deux noeuds passés en paramètre 
	 */
	public boolean verifierTrajet(NoeudList noeudDepart, NoeudList noeudArrivee)
	{
		ArrayList<ArreteList> listeArretes = null;
		NoeudList nDepart = null;
		for(int j = 0; j < this.obtenirNbreNoeuds(); j++)
		{
			if(this.obtenirNoeuds().get(j).compareTo(noeudDepart))
			{
				nDepart = this.obtenirNoeuds().get(j);
				listeArretes = nDepart.obtenirDestinations();
			}
		}
		for(int i = 0; i< listeArretes.size(); i++)
		{
			if (listeArretes.get(i).verifierTrajet(nDepart, noeudArrivee))
				return true;
		}
		return false;
	}

	/*
	 * Fonction retournant le poids de l'arrête correspondant au deux noeuds passés en paramètre
	 * (la fonction retourne -1, s'il n'y a pas d'arrête entre ces deux points)
	 */
	public double obtenirPoids(NoeudList noeudDepart, NoeudList noeudArrivee)
	{
		ArrayList<ArreteList> listeArretes = null;
		NoeudList nDepart = null;
		for(int j = 0; j < this.obtenirNbreNoeuds(); j++)
		{
			if(this.obtenirNoeuds().get(j).compareTo(noeudDepart))
			{
				nDepart = this.obtenirNoeuds().get(j);
				listeArretes = nDepart.obtenirDestinations();
			}
		}
		for(int i = 0; i< listeArretes.size(); i++)
		{
			if (listeArretes.get(i).verifierTrajet(noeudDepart, noeudArrivee))
				return listeArretes.get(i).obtenirPoids();
		}
		return inf;
	}

	/*
	 * Procédure permettant de fixer le poids d'une arrête dont les deux noeuds sont passés en paramètre
	 */
	public void modifierPoids(NoeudList noeudDepart, NoeudList noeudArrivee, double poids) 
	{
		ArrayList<ArreteList> listeArretes = noeudDepart.obtenirDestinations();
		boolean estPresent = false;

		for(int i = 0; i < listeArretes.size() ;i++)
		{
			if (listeArretes.get(i).verifierTrajet(noeudDepart, noeudArrivee)) {
				listeArretes.get(i).modifierPoids(poids);
				estPresent = true;
			}
		}
		if (!estPresent) 
			noeudDepart.ajouterDestination(noeudArrivee, poids);
	}

	/*
	 * Procédure ajoutant le noeud passé en paramètre à la liste des noeuds du graphe
	 */
	public void ajouterNoeud(NoeudList noeud)
	{
		this.noeuds.add(noeud);
	}

	/*
	 * Fonction renvoyant le nombre de noeuds du graphe
	 */
	public int obtenirNbreNoeuds() 
	{
		return noeuds.size();
	}
	
	/*
	 * Fonction effaçant le poids ou le nombre de pheromones des arrêtes du graphe
	 */
	public void viderInformations() 
	{
		for(int i=0; i < this.obtenirNbreNoeuds() ;i++)
		{
			ArrayList<ArreteList> listeArretes = noeuds.get(i).obtenirDestinations();
			for(int j=0; j < listeArretes.size() ;j++)
			{
				listeArretes.get(j).modifierPoids(1);
			}
		}
	}

	/*
	 * Procédure affichant le graphe 
	 */
	public void afficherGraphe()
	{
		System.out.println("Noeud de départ \t Noeud d'arrivée \t Poids");
		for(int i = 0; i < this.obtenirNbreNoeuds();i++)
		{
			ArrayList<ArreteList> listeArretes = noeuds.get(i).obtenirDestinations();
			for(int j = 0; j < listeArretes.size() ;j++)
			{
				System.out.println("\t"+listeArretes.get(j).obtenirDepart().obtenirId()+"\t\t\t" + listeArretes.get(j).obtenirArrivee().obtenirId()+"\t\t  "+listeArretes.get(j).obtenirPoids());
			}
		}		
	}

	public void rendreConnexe(double valeurInf) {
		inf = valeurInf;
	}
	
	public void modifierNom(String nom)
	{
		this.nom = nom;
	}

	public String obtenirNom() 
	{
		return nom;
	}

	public ArrayList<NoeudList> obtenirNoeuds() 
	{
		return noeuds;
	}

	public void modifierNoeuds(ArrayList<NoeudList> noeuds) 
	{
		this.noeuds = noeuds;
	}
}
