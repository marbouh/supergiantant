package Algorithme.AlgorithmeGenetique;

import java.util.Random;
import java.util.ArrayList;
import Algorithme.Graphe.*;

/**
 *
 */
public class Individu implements Comparable {
	GrapheList genes;
	GrapheList base;

	public Individu(GrapheList graphe) {
		genes = new GrapheList("Individu", graphe.getNbreNoeuds());
		base = graphe;
	}

	public void generer() {
		Random rnd = new Random();
		ArrayList<NoeudList> noeuds = base.getNoeuds();
		int nbreNoeuds = base.getNbreNoeuds();
		Integer tab[] = new Integer[nbreNoeuds];
		int tab_long = 0;

		for (int i = 0; i < nbreNoeuds; i++) {
			NoeudList n1 = noeuds.get(rnd.nextInt(nbreNoeuds));
			boolean nouveau = true;
			for (int j = 0; j < tab_long; j++) {
				if (tab[j] == n1.getId())
					nouveau = false;
			}

			if (!nouveau) {
				i--;
				continue;
			}

			NoeudList n2 = new NoeudList(n1.getId());

			tab[tab_long] = n1.getId();
			tab_long++;
			genes.ajouterNoeud(n2);
		}
	}

	public double obtenirDistance() {
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		double distance = 0;
		for (int i = 1; i < genes.getNbreNoeuds(); i++) {
			distance += base.getPoids(noeuds.get(i - 1), noeuds.get(i));
		}
		return distance;
	}
	
	public Graphe getGenes() {
		return genes;
	}

	public int compareTo(Object individu) {
		int distance = obtenirDistance();
		int idistance = ((Individu)individu).obtenirDistance();
		if (distance > idistance)
			return 1;
		else if (distance < idistance)
			return -1;
		else
			return 0;
	}

	public boolean estPareil(Individu individu) {
		boolean pareil = true;
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		ArrayList<NoeudList> noeuds2 = individu.genes.getNoeuds();
		for (int i = 0; i < noeuds.size(); i++) {
			if (!noeuds.get(i).compareTo(noeuds2.get(i)))
				pareil = false;
		}
		return pareil;
	}

	public boolean possede(NoeudList noeud) {
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		if (noeuds.size() == 0)
			return false;
		for (int i = 0; i < noeuds.size(); i++) {
			if (noeud.compareTo(noeuds.get(i)))
				return true;
		}
		return false;
	}

	public NoeudList get(int i) {
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		return noeuds.get(i);
	}

	public void inverse(int i, int j) {
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		NoeudList noeud = noeuds.get(i);
		noeuds.set(i, noeuds.get(j));
		noeuds.set(j, noeud);
	}

	public void append(NoeudList noeud) {
		NoeudList n = new NoeudList(noeud.getId());
		genes.ajouterNoeud(n);
	}

	public void afficherIndividu() {
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		for (int i = 0; i < genes.getNbreNoeuds(); i++) {
			if (i != 0)
				System.out.print(" -( " + base.getPoids(noeuds.get(i - 1), noeuds.get(i)) + " )--> ");
			System.out.print("" + noeuds.get(i).getId());
		}
		System.out.print("\tDistance : " + obtenirDistance() + "\n");
	}

	public GrapheList obtenirGraphe() {
		GrapheList graphe = new GrapheList(base.getNom(), genes.getNbreNoeuds());
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		NoeudList noeud = null;
		for (int i = 0; i < genes.getNbreNoeuds(); i++) {
			graphe.ajouterNoeud(noeuds.get(i));
			if (i != 0) {
				noeud.addDestination(noeuds.get(i), base.getPoids(noeud, noeuds.get(i)));
			}
			noeud = noeuds.get(i);
		}
		return graphe;
	}
}
