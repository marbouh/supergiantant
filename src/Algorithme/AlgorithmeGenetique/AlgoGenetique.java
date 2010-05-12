package Algorithme.AlgorithmeGenetique;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

import Algorithme.Graphe.*;
import Algorithme.Algorithme;

/**
 * Classe AlgorithmeGenetique
 */
public class AlgoGenetique extends Algorithme {
	private int nbIndividus;
	private int nbIterations;
	private GrapheList graphe;
	private NoeudList depart;
	private ArrayList<Individu> population;
	private int tauxMutation;
	private Random rnd;

	public AlgoGenetique(int nbIndividus, int nbIterations, int tauxMutation, GrapheList probleme) {
		initialisation(nbIndividus, nbIterations, tauxMutation);
		modifierGraphe(probleme);
		depart = null;
	}

	public void initialisation(int nbIndividus, int nbIterations, int tauxMutation) {
		rnd = new Random();
		this.nbIndividus = nbIndividus;
		this.nbIterations = nbIterations;
		this.tauxMutation = tauxMutation;
		population = null;
	}

	public void modifierGraphe(GrapheList graphe) {
		this.graphe = graphe;
	}

	public void modifierDepart(NoeudList depart) {
		this.depart = depart;
	}

	private void triePopulation() {
		Collections.sort(population);
	}

	private boolean existeIndividu(Individu individu) {
		for (int i = 0; i < population.size(); i++)
			if (individu.estPareil(population.get(i)))
				return true;
		return false;
	}

	private void creerPopulationInitiale() {
		Individu individu;
		//System.out.println("creerPopulationInitiale()");
		population = new ArrayList<Individu>();
		for (int i = 0; i < nbIndividus; i++) {
			individu = new Individu(graphe);
			individu.generer(depart);
			if (existeIndividu(individu)) {
				i--;
				continue;
			}
			population.add(individu);
		}		
		triePopulation();
	}

	/**
	 * Selectionne un individu parmis la population
	 */
	private Individu selection() {
		Integer roulette[] = new Integer[nbIndividus];
		for (int i = 0; i < nbIndividus; i++) {
			roulette[i] = nbIndividus - i + 1;
		}
		int alea = rnd.nextInt((nbIndividus * (nbIndividus + 1)) / 2);
		int choix = nbIndividus - 1;
		while (roulette[choix] < alea) {
			alea -= roulette[choix];
			choix--;
		}
		return population.get(choix);
	}

	/**
	 * Crée un individu à partir de 2 parents
	 * Implentation : crossover 1 point
	 * existe aussi : crossover 2 points, OSX
	 */
	private Individu croisement(Individu parent1, Individu parent2, int cassure) {
		Individu fils = new Individu(graphe);

		for (int i = 0; i < cassure; i++) {
			fils.ajouter(parent1.obtenir(i));
		}
		for (int i = cassure; i < graphe.obtenirNbreNoeuds(); i++) {
			if (!fils.possede(parent2.obtenir(i))) {
				fils.ajouter(parent2.obtenir(i));
			}
		}
		for (int i = 0; i < cassure; i++) {
			if (!fils.possede(parent2.obtenir(i))) {
				fils.ajouter(parent2.obtenir(i));
			}
		}
		return fils;
	}

	private Individu croisement2(Individu parent1, Individu parent2, int cassure1, int cassure2) {
		Individu fils = new Individu(graphe);

		if (cassure2 < cassure1) {
			int cassure = cassure1;
			cassure1 = cassure2;
			cassure2 = cassure;
		}

		for (int i = 0; i < cassure1; i++) {
			fils.ajouter(parent1.obtenir(i));
		}
		for (int i = cassure1; i < cassure2; i++) {
			if (!fils.possede(parent2.obtenir(i))) {
				fils.ajouter(parent2.obtenir(i));
			}
		}
		for (int i = cassure2; i < graphe.obtenirNbreNoeuds(); i++) {
			if (!fils.possede(parent1.obtenir(i))) {
				fils.ajouter(parent1.obtenir(i));
			}
		}
		for (int i = cassure1; i < cassure2; i++) {
			if (!fils.possede(parent1.obtenir(i))) {
				fils.ajouter(parent1.obtenir(i));
			}
		}
		return fils;
	}


	/**
	 * Applique une mutation Ã  un individu
	 */
	private void mutation(Individu individu) {
		//System.out.println("*** /!\\ Attention au Mutant !!! ***");

		int nbVilles = graphe.obtenirNbreNoeuds();
		int aleaMax = nbVilles - 2;
		if (depart != null) aleaMax--;
		int alea = rnd.nextInt(aleaMax); 
		if (depart != null) alea++;
		//individu.afficherIndividu();
		individu.inverser(alea, alea + 1);
		//individu.afficherIndividu();
		//System.out.println("*** Arrrrrgggggghhhhhhhhhhhhhhhhhhhh ***");
	}

	/**
	 * Evalue la pertinence d'un individu (fils)
	 */
	private boolean evaluation(Individu individu) {
		if (existeIndividu(individu) || individu.obtenirDistance() >= population.get(nbIndividus - 1).obtenirDistance())
			return false;
		return true;
	}

	/**
	 * Réinsert un individu
	 */ 
	private void insertion(Individu individu) {
		population.remove(nbIndividus - 1);
		for (int i = nbIndividus - 2; i >= 0; i--) {
			if (individu.compareTo(population.get(i)) >= 0) {
				population.add(i + 1, individu);
				break;
			} else if (i == 0) {
				population.add(i, individu);
			}
		}
	}
	
	public void resoudre() {
		graphe.rendreConnexe(10000000.);
		debuter();
		creerPopulationInitiale();
		for (int it = 0; it < nbIterations; it++) {
			int cassure1 = rnd.nextInt(graphe.obtenirNbreNoeuds() - 1) + 1;
			int cassure2 = rnd.nextInt(graphe.obtenirNbreNoeuds() - 1) + 1;
			Individu parent1 = selection();
			Individu parent2 = selection();
			for (int i = 0; i < 2; i++) {
				Individu fils;
				if (i == 0)
					fils = croisement2(parent1, parent2, cassure1, cassure2);
				else
					fils = croisement2(parent2, parent1, cassure1, cassure2);
				if (rnd.nextInt(100) <= tauxMutation)
					mutation(fils);
				if (evaluation(fils))
					insertion(fils);
			}
		}
		arreter();
		graphe.rendreConnexe(-1);
	}

	public void afficherPopulation() {
		for (int i = 0; i < nbIndividus; i++) 
			population.get(i).afficherIndividu();
	}

	public double obtenirDistance() {
		return population.get(0).obtenirDistance();
	}

	public GrapheList obtenirSolution() {
		return population.get(0).obtenirGraphe();
	}
}