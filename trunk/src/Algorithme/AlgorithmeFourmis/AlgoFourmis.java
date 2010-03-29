package Algorithme.AlgorithmeFourmis;

import Algorithme.Graphe.Graphe;


public class AlgoFourmis{

	private int nbreFourmis;
	private int nbreIterations;
	private int[][] pheromone;	
	private Graphe probleme;
	private Graphe resultant;
	
	public AlgoFourmis(int nbreFourmis, int nbreIterations, Graphe probleme)
	{
		this.nbreFourmis = nbreFourmis;
		this.nbreIterations = nbreIterations;
		this.setProbleme(probleme);
		
		int nbreNoeuds = probleme.getNbreNoeuds();
		pheromone = new int[nbreNoeuds][nbreNoeuds];
		
		for(int i = 0;i < nbreNoeuds;i++)
		{
			for(int j = 0; j < nbreNoeuds;j++)
			pheromone[i][j]= 0;
		}
	}
	
	public int[][] getPheromone() {
		return pheromone;
	}
	public void setPheromone(int[][] pheromone) {
		this.pheromone = pheromone;
	}
	
	public int getNbreFourmis() {
		return nbreFourmis;
	}
	public void setNbreFourmis(int nbreFourmis) {
		this.nbreFourmis = nbreFourmis;
	}
	public void setNbreIterations(int nbreIterations) {
		this.nbreIterations = nbreIterations;
	}
	public int getNbreIterations() {
		return nbreIterations;
	}

	public void setProbleme(Graphe probleme) {
		this.probleme = probleme;
	}

	public Graphe getProbleme() {
		return probleme;
	}
	
}
