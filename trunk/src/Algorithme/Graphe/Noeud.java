package Algorithme.Graphe;

import java.util.ArrayList;

public class Noeud {
	private int id;
	private String nom; // Ã  voir
	private NoeudList listeArrete;//à ajouter ou mettre le type NoeudList ?
	
	public Noeud(int id) {
		this.id = id;
	}

	public int getId() { // le mettre en droit de package
		return id;
	}

	public boolean compareTo(Noeud noeudArrivee) {
		
		if(this.nom == noeudArrivee.nom && this.getId() == noeudArrivee.getId())
			return true;
		return false;
	}

	/*public ArrayList<Arrete> getArretes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

}
