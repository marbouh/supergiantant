package AlgorithmeFourmis;

public class Fourmis
{
	
	private int vitesseEvapPheromone;
	private int nbrePheromoneDeposes;
	//private ArrayList<> listeNoeudsVisites;
	private String etat;

	
	public Fourmis(int vitesseEvapPheromone,int nbrePheromoneDeposes, String etat)
	{
		this.vitesseEvapPheromone = vitesseEvapPheromone;
		this.nbrePheromoneDeposes = nbrePheromoneDeposes;
		this.etat = etat;
	}
	
	
	public int getVitesseEvapPheromone() {
		return vitesseEvapPheromone;
	}
	public void setVitesseEvapPheromone(int vitesseEvapPheromone) {
		this.vitesseEvapPheromone = vitesseEvapPheromone;
	}
	public int getNbrePheromoneDeposes() {
		return nbrePheromoneDeposes;
	}
	public void setNbrePheromoneDeposes(int nbrePheromoneDeposes) {
		this.nbrePheromoneDeposes = nbrePheromoneDeposes;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getEtat() {
		return etat;
	}

}
