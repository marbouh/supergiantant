package Algorithme.Graphe;

/**
 * Class ArreteList (le mettre disponible seulement pour le package)
 */
public class ArreteList
{
	private NoeudList depart, arrivee;
	private double poids;

	public ArreteList(NoeudList depart, NoeudList arrivee, double poids) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.poids = poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public double getPoids() {
		return poids;
	}

	public void setDepart(NoeudList depart) {
		this.depart = depart;
	}

	public NoeudList getDepart() {
		return depart;
	}

	public void setArrivee(NoeudList arrivee) {
		this.arrivee = arrivee;
	}

	public NoeudList getArrivee() {
		return arrivee;
	}

	public boolean checkTrajet(NoeudList depart, NoeudList arrivee) {
		if (this.depart == depart && this.arrivee == arrivee)
			return true;
		return false;
	}
}
