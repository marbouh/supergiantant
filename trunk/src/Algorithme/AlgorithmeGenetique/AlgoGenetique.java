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
		init(nbIndividus, nbIterations, tauxMutation);
		setGraphe(probleme);
		depart = null;
	}

	public void init(int nbIndividus, int nbIterations, int tauxMutation) {
		rnd = new Random();
		this.nbIndividus = nbIndividus;
		this.nbIterations = nbIterations;
		this.tauxMutation = tauxMutation;
		population = null;
	}

	public void setGraphe(GrapheList graphe) {
		this.graphe = graphe;
	}

	public void setDepart(NoeudList depart) {
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

	public void creerPopulationInitiale() {
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
	public Individu selection() {
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
	public Individu croisement(Individu parent1, Individu parent2, int cassure) {
		Individu fils = new Individu(graphe);

		for (int i = 0; i < cassure; i++) {
			fils.append(parent1.get(i));
		}
		for (int i = cassure; i < graphe.getNbreNoeuds(); i++) {
			if (!fils.possede(parent2.get(i))) {
				fils.append(parent2.get(i));
			}
		}
		for (int i = 0; i < cassure; i++) {
			if (!fils.possede(parent2.get(i))) {
				fils.append(parent2.get(i));
			}
		}
		return fils;
	}

	/**
	 * Applique une mutation Ã  un individu
	 */
	public void mutation(Individu individu) {
		//System.out.println("*** /!\\ Attention au Mutant !!! ***");

		int nbVilles = graphe.getNbreNoeuds();
		int aleaMax = nbVilles - 1;
		if (depart != null) aleaMax--;
		int alea = rnd.nextInt(aleaMax); 
		if (depart != null) alea++;
		//individu.afficherIndividu();
		individu.inverse(alea, alea + 1);
		//individu.afficherIndividu();
		//System.out.println("*** Arrrrrgggggghhhhhhhhhhhhhhhhhhhh ***");
	}

	/**
	 * Evalue la pertinence d'un individu (fils)
	 */
	public boolean evaluation(Individu individu) {
		if (individu.obtenirDistance() >= population.get(nbIndividus - 1).obtenirDistance())
			return false;
		return true;
	}

	/**
	 * Réinsert un individu
	 */ 
	public void insertion(Individu individu) {
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
		start();
		creerPopulationInitiale();
		//afficherPopulation();
		//System.out.println("--------------------------");
		for (int it = 0; it < nbIterations; it++) {
			int cassure = rnd.nextInt(graphe.getNbreNoeuds() - 1) + 1;
			//System.out.println("\nIteration " + it);
			//System.out.println("Cassure : " + cassure);
			Individu parent1 = selection();
			//System.out.println("Parent1 :");
			//parent1.afficherIndividu();
			Individu parent2 = selection();
			//System.out.println("Parent2 :");
			//parent2.afficherIndividu();
			for (int i = 0; i < 2; i++) {
				Individu fils;
				if (i == 0)
					fils = croisement(parent1, parent2, cassure);
				else
					fils = croisement(parent2, parent1, cassure);
				if (rnd.nextInt(100) <= tauxMutation)
					mutation(fils);
				if (evaluation(fils))
					insertion(fils);
				//System.out.println("Fils" + (i + 1) + " :");
				//fils.afficherIndividu();
			}
		}
		population.get(0).afficherIndividu();
		stop();
		System.out.println("Temps mis : " + obtenirTemps() + " ms");
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