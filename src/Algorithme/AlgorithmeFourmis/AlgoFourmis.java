package Algorithme.AlgorithmeFourmis;

import Algorithme.Graphe.Graphe;

/**
 *  Class AlgoFourmis : 
 */
public class AlgoFourmis{

	private int nbreFourmis;
	private int nbreIterations;
	private int[][] pheromone;    // Utilisation du type graphe ?
	private Graphe probleme;
	private Graphe resultant;
	
	public AlgoFourmis(int nbreFourmis, int nbreIterations, Graphe probleme)
	{
		this.nbreFourmis = nbreFourmis;
		this.nbreIterations = nbreIterations;
		this.setProbleme(probleme);
		
		int nbreNoeuds = probleme.getNbreNoeuds();
		pheromone = new int[nbreNoeuds][nbreNoeuds];
		
		// Utiliser la fonction vider du graphe
		for(int i = 0;i < nbreNoeuds;i++)
		{
			for(int j = 0; j < nbreNoeuds;j++)
				pheromone[i][j]= 0;
		}
	}
	
	/*Fonctions à coder
	 * 
	 * Créer toutes les fourmis et les affecter à un noeud départ
	 * Répartir les fourmis sur l'ensemble des chemins possibles depuis le noeud de départ
	 * Relancer les fourmis (une fois qu'elles sont arrivées)
	 * Mettre à jour les pheromones (système d'évaporation)
	 * 
	 * 
	 */
	
	
	public void deposerPheromone(int noeud1, int noeud2, int nbrePheromones)
	{
		pheromone[noeud1][noeud2] = nbrePheromones;
	}
	
		
	public void misAJourPheromone()
	{
		
		
	}
	
	public void afficherPheromone()
	{
		for(int i=0; i < pheromone.length ;i++)
		{
			System.out.println("-------------------------------");
			for(int j=0; j< pheromone.length;j++)
			{
				System.out.print(" | "+pheromone[i][j]);
			}
			System.out.println(" |");
		}
	}
	
   	public int[][] getPheromone() {                /// Devrait Ãªtre en privÃ©e 
		return pheromone;                          //| (Fonctionnement interne de la classe)
	}                                              //| L'utilisateur de la classe devrait seulement
	public void setPheromone(int[][] pheromone) {  //| choisir le taux de phÃ©romone dÃ©posÃ© par une fourmis
		this.pheromone = pheromone;                //| et le taux de dissipation.
	}                                              //\
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
	public int getNbreNoeuds()
	{
		return probleme.getNbreNoeuds();
	}


	public void setResultant(Graphe resultant) {
		this.resultant = resultant;
	}

	public Graphe getResultant() {
		return resultant;
	}
	
}
