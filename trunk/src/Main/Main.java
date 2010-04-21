package Main;

import Algorithme.AlgorithmeFourmis.AlgoFourmis;
import Algorithme.Graphe.*;


public class Main {

	public static void main(String[] args)
	{
		GrapheList probleme = new GrapheList("Test",5);
		NoeudList n1 = new NoeudList(1);
		NoeudList n2 = new NoeudList(2);
		NoeudList n3 = new NoeudList(3);
		NoeudList n4 = new NoeudList(4);
		NoeudList n5 = new NoeudList(5);
		
		n1.addDestination(n2, 4);
		n1.addDestination(n3, 5);
		n2.addDestination(n4, 6);
		n2.addDestination(n5, 1);
		n3.addDestination(n5, 1);
		n4.addDestination(n5, 2);
				
		n2.addDestination(n1, 4);
		n3.addDestination(n1, 5);
		n4.addDestination(n2, 6);
		n5.addDestination(n2, 1);
		n5.addDestination(n3, 1);
		n5.addDestination(n4, 2);
		
		probleme.ajouterNoeud(n1);
		probleme.ajouterNoeud(n2);
		probleme.ajouterNoeud(n3);
		probleme.ajouterNoeud(n4);
		probleme.ajouterNoeud(n5);
		//System.out.println("Affichage du problème");
		//probleme.afficherGraphe();
		
		AlgoFourmis algo = new AlgoFourmis(2,70,10,0,probleme);
		algo.traiterProbleme(n1,5 ,1);
		System.out.println("Algo terminé !");
		//algo.getProbleme().afficherGraphe();
		System.out.println("\n\n Affichage du résultat");
		algo.getResultant().afficherGraphe();
	}

}
