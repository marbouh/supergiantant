package Algorithme.Graphe;

import java.util.ArrayList;

/**
 * Class NoeudList
 */
public class NoeudList extends Noeud
{
	private ArrayList<ArreteList> destinations;

	public NoeudList(int id) {
		super(id);
		destinations = new ArrayList<ArreteList>();
		
	}

	public void addDestination(Noeud arrivee, double poids) {
		ArreteList destination = new ArreteList(this, arrivee, poids);

		destinations.add(destination);
	}

	public ArrayList<ArreteList> getDestinations() {
		return destinations;
	}
}
