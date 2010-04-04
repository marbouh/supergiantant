package Algorithme.Graphe;

import java.util.ArrayList;

/**
 * Class NoeudList
 */
public class NoeudList extends Noeud
{
	private ArrayList<ArreteList> destinations;

	public NoeudList(int id) {
		destinations = new ArrayList<ArreteList>();
		super(id);
	}

	public void addDestination(Noeud arrivee, double poids) {
		ArreteList destination = ArreteList(this, arrivee, poids);

		destinations.add(destinations);
	}

	public ArrayList<ArreteList> getDestinations() {
		return destinations;
	}
}
