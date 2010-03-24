package Graphe;

public class Graphe {

	private int[][] poids;
	private int nbreNoeuds;
	
	public Graphe(int nbreNoeuds)
	{
		poids = new int[nbreNoeuds][nbreNoeuds];

	}

	
	
	
	public int[][] getPoids() {
		return poids;
	}
	public void setPoids(int[][] poids) {
		this.poids = poids;
	}

	public int getNbreNoeuds() {
		return nbreNoeuds;
	}
	public void setNbreNoeuds(int nbreNoeuds) {
		this.nbreNoeuds = nbreNoeuds;
	}
	
}
