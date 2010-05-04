package Main;

import Algorithme.AlgorithmeFourmis.AlgoFourmis;
import Algorithme.Graphe.*;
import InterfaceGraphique.Interface;
import InterfaceGraphique.ParserGraphe;



public class Main {

	public static void main(String[] args)
	{
		Interface fenetre = new Interface();
		//resoudreSansInterface2("graphe.txt");
		//resoudreSansInterface1();
	}

	public static void resoudreSansInterface1()
	{
		GrapheList probleme = new GrapheList("Test",6);
		NoeudList n1 = new NoeudList(1);
		NoeudList n2 = new NoeudList(2);
		NoeudList n3 = new NoeudList(3);
		NoeudList n4 = new NoeudList(4);
		NoeudList n5 = new NoeudList(5);
		NoeudList n6 = new NoeudList(6);
		
		n1.addDestination(n2, 4);
		n1.addDestination(n3, 5);
		n2.addDestination(n4, 6);
		n2.addDestination(n5, 1);
		n3.addDestination(n5, 1);
		n3.addDestination(n6, 2);
		n4.addDestination(n5, 2);
		n6.addDestination(n5, 4);
		
		/*n2.addDestination(n1, 4);
		n3.addDestination(n1, 5);
		n4.addDestination(n2, 6);
		n5.addDestination(n2, 1);
		n5.addDestination(n3, 1);
		n5.addDestination(n4, 2);
		n5.addDestination(n6, 4);
		n6.addDestination(n3, 2);*/
		
		probleme.ajouterNoeud(n1);
		probleme.ajouterNoeud(n2);
		probleme.ajouterNoeud(n3);
		probleme.ajouterNoeud(n4);
		probleme.ajouterNoeud(n5);
		probleme.ajouterNoeud(n6);
		//System.out.println("Affichage du problème");
		//probleme.afficherGraphe();
		
		/*Test de l'algorithme
		 * Nombre d'itérations : 100
		 * Nombre de fourmis : 10
		 * Vitesse d'évaporation : 10
		 * Nombre de phéromones à évaporer : 0.5
		 */
		AlgoFourmis algo = new AlgoFourmis(10,100,10,0.5,probleme);//il faut qu'il y ait un facteur de 10 entre le nombre de fourmis et le nombre d'itération
		algo.traiterProbleme(n1);
		System.out.println("Algo terminé !");
		//algo.getProbleme().afficherGraphe();
		System.out.println("\n\n Affichage du résultat");
		algo.getResultant().afficherGraphe();		
	}
	
	public static void resoudreSansInterface2(String nomfichier)
	{
		GrapheList probleme = (GrapheList) ParserGraphe.chargerFichier(nomfichier);
		
		/*Test de l'algorithme
		 * Nombre d'itérations : 100
		 * Nombre de fourmis : 10
		 * Vitesse d'évaporation : 10
		 * Nombre de phéromones à évaporer : 0.5
		 */
		AlgoFourmis algo = new AlgoFourmis(10,100,10,0.5,probleme);//il faut qu'il y ait un facteur de 10 entre le nombre de fourmis et le nombre d'itération
		algo.traiterProbleme(probleme.getNoeuds().get(0));
		System.out.println("Algo terminé !");
		//algo.getProbleme().afficherGraphe();
		System.out.println("\n\n Affichage du résultat");
		algo.getResultant().afficherGraphe();
	}
	
}
