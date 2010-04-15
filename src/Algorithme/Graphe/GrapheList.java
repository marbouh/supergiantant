package Algorithme.Graphe;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class GraphList
 */
public class GrapheList implements Graphe 
{
	private String nom;
	private ArrayList<NoeudList> noeuds;
	
	/*
	 * Constructeur de la classe GrapheList
	 */
	public GrapheList(String nom, int nbreNoeuds)
	{
		this.nom = nom;
		noeuds = new ArrayList<NoeudList>(nbreNoeuds);
	}

	/*
	 * Fonction retournant la liste des noeuds suivants d'un noeud donné
	 */
	public ArrayList<NoeudList> getSuivants(NoeudList noeud)
	{
		ArrayList<NoeudList> ns = new ArrayList<NoeudList>();
		ArreteList a = null;
		ArrayList<ArreteList> listeArretes = noeud.getDestinations();
		
		for(int i=0; i < listeArretes.size();i++)
		{
			ns.add(listeArretes.get(i).getArrivee());
		}
		return ns;
	}

	/*
	 * Fonction vérifiant qu'il y a un chemin (arrête) entre les deux noeuds passés en paramètre 
	 */
	public boolean checkTrajet(NoeudList noeudDepart, NoeudList noeudArrivee)
	{
		ArrayList<ArreteList> listeArretes = noeudDepart.getDestinations();

		for(int i = 0; i< listeArretes.size(); i++)
		{
			if (listeArretes.get(i).checkTrajet(noeudDepart, noeudArrivee))
				return true;
		}
		return false;
	}

	/*
	 * Fonction retournant le poids de l'arrête correspondant au deux noeuds passés en paramètre
	 * (la fonction retourne -1, s'il n'y a pas d'arrête entre ces deux points)
	 */
	public double getPoids(NoeudList noeudDepart, NoeudList noeudArrivee) {
		ArrayList<ArreteList> listeArretes = noeudDepart.getDestinations();
		
		for(int i = 0; i< listeArretes.size(); i++)
		{
			if (listeArretes.get(i).checkTrajet(noeudDepart, noeudArrivee))
				return listeArretes.get(i).getPoids();
		}
		return -1;
	}

	/*
	 * Procédure permettant de fixer le poids d'une arrête dont les deux noeuds sont passés en paramètre
	 */
	public void setPoids(NoeudList noeudDepart, NoeudList noeudArrivee, double poids) {
		ArrayList<ArreteList> listeArretes = noeudDepart.getDestinations();
		boolean estPresent = false;

		for(int i = 0; i < listeArretes.size() ;i++)
		{
			if (listeArretes.get(i).checkTrajet(noeudDepart, noeudArrivee)) {
				listeArretes.get(i).setPoids(poids);
				estPresent = true;
			}
		}
		if (!estPresent) 
			noeudDepart.addDestination(noeudArrivee, poids);
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
	public int getNbreNoeuds() {
		return noeuds.size();
	}
	
	/*
	 * Fonction effaçant le poids ou le nombre de pheromones des arrêtes du graphe
	 */
	public void viderInformations() 
	{
		for(int i=0; i < noeuds.size() ;i++)
		{
			ArrayList<ArreteList> listeArretes = noeuds.get(i).getDestinations();
			for(int j=0; j < listeArretes.size() ;j++)
			{
				listeArretes.get(j).setPoids(0);
			}
		}
	}

	/*Getter et Setter des attributs de la classe */
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	
}
