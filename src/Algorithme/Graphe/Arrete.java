package Algorithme.Graphe;

// D'une utilité peut certaine
public class Arrete {
	private int poids;

	public Arrete() {
		this.poids = 0;
	}

	public Arrete(int poids) {
		this.poids = poids;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}
}
