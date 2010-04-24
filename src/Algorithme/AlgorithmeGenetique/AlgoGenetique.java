package AlgoGenetique;

public class AlgoGenetique {
	private int nbIndividus;
	private int nbIterations;
	private GrapheList graphe;
	private Individu population[][];
	private int tauxMutation;

	public void init(int nbIndividus, int nbIterations, long tauxMutation) {
		this.nbIndividus = nbIndividus;
		this.nbIterations = nbIterations;
		this.tauxMutation = tauxMutation;
	}

	public void setGraphe(GrapheList graphe) {
		this.graphe = graphe;
	}

	/**
	 * Selectionne un individu parmis la population
	 */
	public Individu selection() {
	}

	/**
	 * Crée un individu à partir de 2 parents
	 * Implentation : crossover 1 point
	 * existe aussi : crossover 2 points, OSX
	 */
	public Individu croisement(Individu parent1, Individu parent2) {
		int avancement;
		int cassure = graphe.getNbreNoeuds() / 2;
		Individu fils = new Individu();

		for (avancement = 0; avancement < cassure; avancement++) {
			fils.set(avancement, parent1.get(avancement));
		}
		for (int i = cassure; i < graphe.getNbreNoeuds(); i++) {
			if (!fils.has(parent2.get(i))) {
				fils.set(avancement, parent2.get(i));
			}
		}
		for (int i = 0; i < cassure; i++) {
			if (!fils.has(parent2.get(i))) {
				fils.set(avancement, parent2.get(i));
			}
		}
	}

	/**
	 * Evalue la pertinence d'un individu (fils)
	 */
	public boolean evaluation(Individu individu) {
	}

	/**
	 * Applique une mutation à un individu
	 */
	public void mutation(Individu individu) {
		
	}

	public void resourdre() {
		start();
		int nbVilles = graphe.getNbreNoeuds();
		for (int it = 0; it < nbIterations; it++) {
			population = new Individu[nbIndividus][nbVilles]();
			for (int i = 0; i < nbIndividus; i++) {
				for (int j = 0; j < nbVilles; j++) {
				}
			}
		}
		stop();
	}
}