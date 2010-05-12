package Algorithme;

import Algorithme.Graphe.Graphe;

/**
 *  Abstract class Algorithme : 
 */
public abstract class Algorithme
{
	private double tempsDebut;
	private double tempsFin;
	private boolean enFonctionnement;

	public Algorithme() {
		tempsDebut = 0;
		tempsFin = 0;
		enFonctionnement = false;
	}

	protected void debuter() {
		if (enFonctionnement)
			throw new IllegalStateException("L'algorithme est déjà en fonctionement");

		tempsFin = 0;
		enFonctionnement = true;
		tempsDebut = System.nanoTime();
	}

	protected void arreter() {
		if (!enFonctionnement)
			throw new IllegalStateException("L'algorightme n'est pas en court de fonctionement");

		tempsFin = System.nanoTime();
		
		enFonctionnement = false;
	}

	public boolean obtenirEtat() {
		return enFonctionnement;
	}

	public double obtenirTemps() {
		double temps;
		if (enFonctionnement)
			temps = System.nanoTime() - tempsDebut;
		else
			temps = tempsFin - tempsDebut;
		return temps;
	}

	public abstract double obtenirDistance();

	public abstract Graphe obtenirSolution();
}