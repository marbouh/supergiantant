package Algorithme.Graphe;

import java.util.ArrayList;

/**
 * Class NoeudList
 */
public class NoeudList extends Noeud
{
	private ArrayList<ArreteList> destinations;

	/*
	 * Constructeur de la classe
	 */
	public NoeudList(int id) {
		super(id);
		destinations = new ArrayList<ArreteList>();
	}

	/*
	 * Fonction copiant un noeud (avec toutes ses arrêtes) et retournant la copie de ce noeud
	 */
	public NoeudList copierNoeud(ArrayList<NoeudList> noeuds)
	{
		NoeudList copieNoeud = new NoeudList(this.getId());
		
		for(int i = 0; i < this.getDestinations().size();i++)
		{
			ArreteList arrete = this.getDestinations().get(i);
			for(int j = 0; j < noeuds.size();j++)
			{
				if(arrete.getArrivee().getId() == noeuds.get(j).getId())
				{
					copieNoeud.addDestination(noeuds.get(j), arrete.getPoids());
				}
			}			
		}		
		return copieNoeud;		
	}
	
	/*
	 * Procédure ajoutant une arrete entre deux noeuds (celui passé en paramètre et le noeud courant)
	 */
	public void addDestination(NoeudList arrivee, double poids) {
		ArreteList aller = new ArreteList(this, arrivee, poids);
		destinations.add(aller);
		/*Ajout automatique de l'arrete de retour pour le noeud arrivee*/
		ArreteList retour= new ArreteList(arrivee, this, poids);
		arrivee.getDestinations().add(retour);
	}

	/*
	 * Fonction retournant le noeud dont l'identifiant est passé en paramètre et qui est trouvé dans listeNoeuds
	 * (si aucun noeud est trouvé la fonction retourne null)
	 */
	public static NoeudList trouverNoeud(ArrayList<NoeudList> listeNoeuds, int id)
	{
		for(int i = 0; i < listeNoeuds.size() ;i++)
		{
			if(listeNoeuds.get(i).getId() == id)
				return listeNoeuds.get(i);
		}
		return null;
	}
	
	/* Getter de l'attribut de la classe*/
	public ArrayList<ArreteList> getDestinations() {
		return destinations;
	}

}
