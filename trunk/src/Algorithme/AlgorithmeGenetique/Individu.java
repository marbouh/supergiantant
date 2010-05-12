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
		genes = new GrapheList("Individu", graphe.obtenirNbreNoeuds());
		base = graphe;
	}

	public void generer(NoeudList depart) {
		Random rnd = new Random();
		ArrayList<NoeudList> noeuds = base.obtenirNoeuds();
		int nbreNoeuds = base.obtenirNbreNoeuds();
		Integer tab[] = new Integer[nbreNoeuds];
		int tabLong = 0;

		for (int i = 0; i < nbreNoeuds; i++) {
			NoeudList n1;
			if (i == 0 && depart != null) {
				n1 = depart;
			} else {
				n1 = noeuds.get(rnd.nextInt(nbreNoeuds));
				boolean nouveau = true;
				for (int j = 0; j < tabLong; j++) {
					if (tab[j] == n1.obtenirId())
						nouveau = false;
				}

				if (!nouveau) {
					i--;
					continue;
				}
			}

			NoeudList n2 = new NoeudList(n1.obtenirId());

			tab[tabLong] = n1.obtenirId();
			tabLong++;
			genes.ajouterNoeud(n2);
		}
	}

	public double obtenirDistance() {
		ArrayList<NoeudList> noeuds = genes.obtenirNoeuds();
		double distance = 0.;
		for (int i = 1; i < genes.obtenirNbreNoeuds(); i++) {
			distance += base.obtenirPoids(noeuds.get(i - 1), noeuds.get(i));
		}
		return distance;
	}
	
	public Graphe obtenirGenes() {
		return genes;
	}

	public int compareTo(Object individu) {
		double distance = obtenirDistance();
		double idistance = ((Individu)individu).obtenirDistance();
		if (distance > idistance)
			return 1;
		else if (distance < idistance)
			return -1;
		else
			return 0;
	}

	public boolean estPareil(Individu individu) {
		boolean pareil = true;
		ArrayList<NoeudList> noeuds = genes.obtenirNoeuds();
		ArrayList<NoeudList> noeuds2 = individu.genes.obtenirNoeuds();
		for (int i = 0; i < noeuds.size(); i++) {
			if (!noeuds.get(i).compareTo(noeuds2.get(i)))
				pareil = false;
		}
		return pareil;
	}

	public boolean possede(NoeudList noeud) {
		ArrayList<NoeudList> noeuds = genes.obtenirNoeuds();
		if (noeuds.size() == 0)
			return false;
		for (int i = 0; i < noeuds.size(); i++) {
			if (noeud.compareTo(noeuds.get(i)))
				return true;
		}
		return false;
	}

	public NoeudList obtenir(int i) {
		ArrayList<NoeudList> noeuds = genes.obtenirNoeuds();
		return noeuds.get(i);
	}

	public void inverser(int i, int j) {
		ArrayList<NoeudList> noeuds = genes.obtenirNoeuds();
		NoeudList noeud = noeuds.get(i);
		noeuds.set(i, noeuds.get(j));
		noeuds.set(j, noeud);
	}

	public void ajouter(NoeudList noeud) {
		NoeudList n = new NoeudList(noeud.obtenirId());
		genes.ajouterNoeud(n);
	}

	public void afficherIndividu() {
		ArrayList<NoeudList> noeuds = genes.obtenirNoeuds();
		for (int i = 0; i < genes.obtenirNbreNoeuds(); i++) {
			if (i != 0)
				System.out.print(" -( " + base.obtenirPoids(noeuds.get(i - 1), noeuds.get(i)) + " )--> ");
			System.out.print("" + noeuds.get(i).obtenirId());
		}
		System.out.print("\tDistance : " + obtenirDistance() + "\n");
	}

	public GrapheList obtenirGraphe() {
		GrapheList graphe = new GrapheList(base.obtenirNom(), genes.obtenirNbreNoeuds());
		ArrayList<NoeudList> noeuds = genes.obtenirNoeuds();
		NoeudList noeud = null;
		for (int i = 0; i < genes.obtenirNbreNoeuds(); i++) {
			graphe.ajouterNoeud(noeuds.get(i));
			if (i != 0) {
				noeud.ajouterDestination(noeuds.get(i), base.obtenirPoids(noeud, noeuds.get(i)));
			}
			noeud = noeuds.get(i);
		}
		return graphe;
	}
}
