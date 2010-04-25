package AlgoGenetique;

import java.util.Random;

public class AlgoGenetique {
	private int nbIndividus;
	private int nbIterations;
	private GrapheList graphe;
	private Individu population[];
	private int tauxMutation;
	private Random rnd;

	public void init(int nbIndividus, int nbIterations, long tauxMutation) {
		rnd = new Random();
		this.nbIndividus = nbIndividus;
		this.nbIterations = nbIterations;
		this.tauxMutation = tauxMutation;
	}

	public void setGraphe(GrapheList graphe) {
		this.graphe = graphe;
	}

	private void triePopulation() {
		Arrays.sort(population, new Comparator() {
				int compare(Object o1, Object o2) {
					Individu i1 = (Individu) o1;
					Individu i2 = (Individu) o2;
					return i1.getDistance() - i2.getDistance();
				}
			});
	}

	private boolean existeIndividu(Individu individu) {
		for (int i = 0; i < nbIndividus; i++)
			if (individu.same(population[i]))
				return true;
		return false;
	}

	public void creerPopulationInitiale() {
		Individu individu;
		population = new Individu[nbIndividus]();
		for (int i = 0; i < nbIndividus; i++) {
			individu = new Individu(graphe);
			if (existeIndividu(individu))
				continue;
			else
				population[i] = individu;
		}
		triePopulation();
	}

	/**
	 * Selectionne un individu parmis la population
	 */
	public Individu selection() {
		Integer roulette[] = new Integer[nbIndividus];
		for (int i = 0; i < nbIndividus; i++) {
			roulette[i] = nbIndividus - i + 1;
		}
		int alea = rnd.nextInt((nbIndividus * (nbIndividus + 1)) / 2);
		int choix = nbIndividus - 1;
		while (roulette[choix] < alea)
			choix++;
		return population[choix];
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
				avancement++;
			}
		}
	}

	/**
	 * Applique une mutation à un individu
	 */
	public void mutation(Individu individu) {
		int nbVilles = graphe.getNbreNoeuds();
		int alea = rnd.nextInt(nbVilles - 1);
		individu.inverse(alea, alea + 1);
	}

	/**
	 * Evalue la pertinence d'un individu (fils)
	 */
	public boolean evaluation(Individu individu) {
		if (individu.getDistance() >= population[nbIndividus - 1].getDistance())
			return false;
		return true;
	}

	/**
	 * Réinsert un individu
	 */ 
	public void insertion(Individu individu) {
		for (int i = nbIndividus - 2; i >= 0; i--) {
			if (individu.getDistance() < population[i].getDistance()) {
				population[i + 1] = population[i];
			} else {
				population[i + 1] = individu;
			}
		}
	}
	
	public void resoudre() {
		start();
		creerPopulationInitiale();
		for (int it = 0; it < nbIterations; it++) {
			Individu parent1 = selection();
			Individu parent2 = selection();
			Individu fils = croisement(parent1, parent2);
			if (rnd.nextInt(100) <= tauxMutation)
				mutation(fils);
			if (evaluation(fils))
				insertion(fils);
		}
		stop();
	}
}