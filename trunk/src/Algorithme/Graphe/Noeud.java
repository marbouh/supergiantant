package Algorithme.Graphe;

public class Noeud {
	private int id;
	private String nom;
		
	public Noeud(int id) {
		this.id = id;
	}

	public int obtenirId() { 
		return id;
	}

	public boolean compareTo(Noeud noeud) {
		
		if(this.nom == noeud.nom && this.obtenirId() == noeud.obtenirId())
			return true;
		return false;
	}

	/*public ArrayList<Arrete> getArretes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	public void modifierNom(String nom) {
		this.nom = nom;
	}

	public String obtenirNom() {
		return nom;
	}

}
