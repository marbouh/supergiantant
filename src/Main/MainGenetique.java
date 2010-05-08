package Main;

import Algorithme.AlgorithmeGenetique.*;
import InterfaceGraphique.Interface;
import Algorithme.Graphe.*;
import java.lang.Thread;

class MainGenetique {
	public static void main(String args[]) {

		/*		for (int i = 0; i < 1000; i++) {
			GrapheList probleme = new GrapheList("Test",6);
			NoeudList n1 = new NoeudList(1);
			NoeudList n2 = new NoeudList(2);
			NoeudList n3 = new NoeudList(3);
			NoeudList n4 = new NoeudList(4);
			NoeudList n5 = new NoeudList(5);
			NoeudList n6 = new NoeudList(6);

			n1.addDestination(n2, 4);
			n1.addDestination(n3, 5);
			n1.addDestination(n4, 1000);
			n1.addDestination(n5, 1000);
			n1.addDestination(n6, 1000);
			n2.addDestination(n4, 6);
			n2.addDestination(n5, 1);
			n2.addDestination(n3, 1000);
			n2.addDestination(n6, 1000);
			n3.addDestination(n5, 1);
			n3.addDestination(n6, 2);
			n3.addDestination(n4, 1000);
			n4.addDestination(n5, 2);
			n4.addDestination(n6, 1000);
			n6.addDestination(n5, 1);
		
			probleme.ajouterNoeud(n1);
			probleme.ajouterNoeud(n2);
			probleme.ajouterNoeud(n3);
			probleme.ajouterNoeud(n4);
			probleme.ajouterNoeud(n5);
			probleme.ajouterNoeud(n6);
		*/
			Interface fenetre = new Interface();

			/*	AlgoGenetique ag = new AlgoGenetique(20, 100, 25, probleme);
			ag.resoudre();
			System.out.println("Temps : " + ag.obtenirTemps() / 1000.);
			ag = null;
			probleme = null;
			n1 = null;
			n2 = null;
			n3 = null;
			n4 = null;
			n5 = null;
			n6 = null;
			System.gc();*/
	}
}
