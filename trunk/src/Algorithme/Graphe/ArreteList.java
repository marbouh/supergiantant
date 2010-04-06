package Algorithme.Graphe;

/**
 * Class ArreteList (le mettre disponible seulement pour le package)
 */
public class ArreteList
{
	private Noeud depart, arrivee;
	private double poids;

	public ArreteList(Noeud depart, Noeud arrivee, double poids) {
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

	public void setDepart(Noeud depart) {
		this.depart = depart;
	}

	public Noeud getDepart() {
		return depart;
	}

	public void setArrivee(Noeud arrivee) {
		this.arrivee = arrivee;
	}

	public Noeud getArrivee() {
		return arrivee;
	}

	public boolean checkTrajet(Noeud depart, Noeud arrivee) {
		if (this.depart == depart && this.arrivee == arrivee)
			return true;
		return false;
	}
}
