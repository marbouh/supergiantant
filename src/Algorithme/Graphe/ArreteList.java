package Algorithme.Graphe;

/**
 * Class ArreteList (le mettre disponible seulement pour le package)
 */
public class ArreteList implements Cloneable
{
	private NoeudList depart, arrivee;
	private double poids;

	/*
	 * Constructeur de la classe
	 */
	public ArreteList(NoeudList depart, NoeudList arrivee, double poids) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.poids = poids;
	}
	
	public boolean verifierTrajet(NoeudList depart, NoeudList arrivee) {
		if (this.depart.compareTo(depart) && this.arrivee.compareTo(arrivee))
			return true;
		else if(this.depart.compareTo(arrivee) && this.arrivee.compareTo(depart))
			return true;
		return false;
	}
	
	public void modifierPoids(double poids) {
		this.poids = poids;
	}

	public double obtenirPoids() {
		return poids;
	}

	public void modifierDepart(NoeudList depart) {
		this.depart = depart;
	}

	public NoeudList obtenirDepart() {
		return depart;
	}

	public void modifierArrivee(NoeudList arrivee) {
		this.arrivee = arrivee;
	}

	public NoeudList obtenirArrivee() {
		return arrivee;
	}
}
