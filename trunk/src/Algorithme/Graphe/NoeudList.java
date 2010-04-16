package Algorithme.Graphe;

import java.util.ArrayList;

/**
 * Class NoeudList
 */
public class NoeudList extends Noeud implements Cloneable
{
	private ArrayList<ArreteList> destinations;

	public NoeudList(int id) {
		super(id);
		destinations = new ArrayList<ArreteList>();
		
	}
	
	public NoeudList clone()
	{    
		NoeudList copieNoeud = null;
		try {
			copieNoeud = (NoeudList) super.clone();
			for(int i=0; i < this.getDestinations().size() ;i++)
			{
				copieNoeud.getDestinations().add(this.getDestinations().get(i).clone());
			}
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	    // on renvoie le clone
	    return copieNoeud;
  	}
	
	public void addDestination(NoeudList arrivee, double poids) {
		ArreteList destination = new ArreteList(this, arrivee, poids);

		destinations.add(destination);
	}

	public ArrayList<ArreteList> getDestinations() {
		return destinations;
	}
}
