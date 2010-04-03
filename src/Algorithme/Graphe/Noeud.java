package Algorithme.Graphe;

import java.util.ArrayList;

public class Noeud {
	private int id;
	private String nom; // à voir

	public Noeud(int id) {
		this.id = id;
	}

	public int getId() { // le mettre en droit de package
		return id;
	}

	public boolean compareTo(Noeud noeudArrivee) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Arrete> getArretes() {
		// TODO Auto-generated method stub
		return null;
	}
}
