package Algorithme.Graphe;

import java.util.ArrayList;

/**
 * Class NoeudList
 */
public class NoeudList extends Noeud implements Cloneable
{
	private ArrayList<ArreteList> destinations;

	/*
	 * Constructeur de la classe
	 */
	public NoeudList(int id) {
		super(id);
		destinations = new ArrayList<ArreteList>();
	}

	
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
		ArreteList destination = new ArreteList(this, arrivee, poids);

		destinations.add(destination);
	}

	/* Getter de l'attribut de la classe*/
	public ArrayList<ArreteList> getDestinations() {
		return destinations;
	}
}
