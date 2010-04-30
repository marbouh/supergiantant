package AlgorithmeGenetique;

import Algorithme.Graphe.*;

/**
 *
 */
public class Individu {
	Graphe genes;

	static Individu nouveau(Graphe graphe) {
		
	}

	public Individu() {
		genes = null;
	}

	public Individu(Graphe graph) {

	}

	public int getDistance() {
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		int distance = 0;
		for (int i = 1; i < genes.getNbreNoeuds(); i++) {
			distance += genes.getPoids(noeuds[i - 1], noeuds[i]);
		}
		return distance;
	}

	public boolean same(Individu individu) {
		boolean pareil = true;
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		ArrayList<NoeudList> noeuds2 = individu.genes.getNoeuds();
		for (int i = 0; i < noeuds.size(); i++) {
			if (noeuds.get(i) == noeuds2.get(i))
				pareil = false;
		}
		return pareil;
	}

	public boolean has(NoeudList noeud) {
		ArrayList<NoeudList> noeuds = genes.getNoeuds();
		for (int i = 0; i < noeuds.size(); i++) {
			if (noeuds.get(i) == noeud)
				return true;
		}
		return false;
	}

	public void append(NoeudList noeud) {
		
	}
}
