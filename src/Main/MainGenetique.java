import Algorithme.AlgorithmeGenetique.*;
import Algorithme.Graphe.*;

class MainGenetique {
	public static void main(String args[]) {
		GrapheList graphe = new GrapheList("AG", 6);
		NoeudList n1 = new NoeudList(1);
		NoeudList n2 = new NoeudList(2);
		NoeudList n3 = new NoeudList(3);
		NoeudList n4 = new NoeudList(4);
		NoeudList n5 = new NoeudList(5);
		NoeudList n6 = new NoeudList(6);

		n1.addDestination(n2, 4);
		n1.addDestination(n3, 8);
		n1.addDestination(n4, 5);
		n1.addDestination(n5, 6);
		n1.addDestination(n6, 2);
		n2.addDestination(n3, 3);
		n2.addDestination(n4, 10);
		n2.addDestination(n5, 7);
		n2.addDestination(n6, 1);
		n3.addDestination(n4, 9);
		n3.addDestination(n5, 5);
		n3.addDestination(n6, 8);
		n4.addDestination(n5, 2);
		n4.addDestination(n6, 10);
		n5.addDestination(n6, 7);

		graphe.ajouterNoeud(n1);
		graphe.ajouterNoeud(n2);
		graphe.ajouterNoeud(n3);
		graphe.ajouterNoeud(n4);
		graphe.ajouterNoeud(n5);
		graphe.ajouterNoeud(n6);

		graphe.afficherGraphe();

		AlgoGenetique ag = new AlgoGenetique(10, 1000, 75, graphe);
	}
}
