package Main;

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
		NoeudList n7 = new NoeudList(7);
		NoeudList n8 = new NoeudList(8);
		NoeudList n9 = new NoeudList(9);
		NoeudList n10 = new NoeudList(10);

		n1.addDestination(n2, 4);
		n1.addDestination(n3, 8);
		n1.addDestination(n4, 5);
		n1.addDestination(n5, 6);
		n1.addDestination(n6, 2);
		n1.addDestination(n7, 8);
		n1.addDestination(n8, 3);
		n1.addDestination(n9, 6);
		n1.addDestination(n10, 15);
		n2.addDestination(n3, 3);
		n2.addDestination(n4, 10);
		n2.addDestination(n5, 7);
		n2.addDestination(n6, 1);
		n2.addDestination(n7, 12);
		n2.addDestination(n8, 7);
		n2.addDestination(n9, 6);
		n2.addDestination(n10, 3);		
		n3.addDestination(n4, 9);
		n3.addDestination(n5, 5);
		n3.addDestination(n6, 8);
		n3.addDestination(n7, 5);
		n3.addDestination(n8, 13);
		n3.addDestination(n9, 16);
		n3.addDestination(n10, 19);
		n4.addDestination(n5, 2);
		n4.addDestination(n6, 10);
		n4.addDestination(n7, 17);
		n4.addDestination(n8, 4);
		n4.addDestination(n9, 11);
		n4.addDestination(n10, 10);
		n5.addDestination(n6, 7);
		n5.addDestination(n7, 18);
		n5.addDestination(n8, 1);
		n5.addDestination(n9, 4);
		n5.addDestination(n10, 9);
		n6.addDestination(n7, 13);
		n6.addDestination(n8, 8);
		n6.addDestination(n9, 7);
		n6.addDestination(n10, 7);
		n7.addDestination(n8, 14);
		n7.addDestination(n9, 9);
		n7.addDestination(n10, 19);
		n8.addDestination(n9, 13);
		n8.addDestination(n10, 3);
		n9.addDestination(n10, 20);

		graphe.ajouterNoeud(n1);
		graphe.ajouterNoeud(n2);
		graphe.ajouterNoeud(n3);
		graphe.ajouterNoeud(n4);
		graphe.ajouterNoeud(n5);
		graphe.ajouterNoeud(n6);
		graphe.ajouterNoeud(n7);
		graphe.ajouterNoeud(n8);
		graphe.ajouterNoeud(n9);
		graphe.ajouterNoeud(n10);

		//graphe.afficherGraphe();

		AlgoGenetique ag = new AlgoGenetique(20, 100, 25, graphe);
		ag.resoudre();
	}
}
