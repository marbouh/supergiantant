package InterfaceGraphique;

import java.util.ArrayList;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import Algorithme.AlgorithmeFourmis.AlgoFourmis;
import Algorithme.Graphe.GrapheList;
import Algorithme.Graphe.NoeudList;


public class ComposantFourmis {
	private JTextField nbreIterations;
	private JTextField nbreFourmis;
	private JTextField vitesseEvapPheromone;
	private JTextField nbrePheromone;
	AlgoFourmis af;

	public ComposantFourmis() {
		af = null;
		nbreIterations = new JTextField(4);
		nbreIterations.setText("100");
		nbreFourmis = new JTextField(4);
		nbreFourmis.setText("10");
		vitesseEvapPheromone = new JTextField(4);
		vitesseEvapPheromone.setText("5");
		nbrePheromone = new JTextField(4);
		nbrePheromone.setText("0.5");
	}

	public JPanel creerPanneauParametre() {
		JPanel contenuParamFourmis = new JPanel();
		contenuParamFourmis.setLayout(new BoxLayout(contenuParamFourmis, BoxLayout.Y_AXIS));
		contenuParamFourmis.add(new JLabel("Algorithme Fourmis"));
		JPanel contenuParamFourmisGrid = new JPanel();
		contenuParamFourmis.add(contenuParamFourmisGrid);
		contenuParamFourmisGrid.setLayout(new GridLayout(4, 2));
		contenuParamFourmisGrid.add(new JLabel("Nombre d'itérations"));
		contenuParamFourmisGrid.add(nbreIterations);
		contenuParamFourmisGrid.add(new JLabel("Nombre de fourmis"));
		contenuParamFourmisGrid.add(nbreFourmis);
		contenuParamFourmisGrid.add(new JLabel("Vitesse d'evaporation des pheromones"));
		contenuParamFourmisGrid.add(vitesseEvapPheromone);
		contenuParamFourmisGrid.add(new JLabel("Nombre de pheromones"));
		contenuParamFourmisGrid.add(nbrePheromone);
		return contenuParamFourmis;
	}

	public void lancement(GrapheList graphe) {
		int it = Integer.parseInt(nbreIterations.getText());
		int fourmis = Integer.parseInt(nbreFourmis.getText());
		int evap = Integer.parseInt(vitesseEvapPheromone.getText());
		double phero = Double.parseDouble(nbrePheromone.getText());
		af = new AlgoFourmis(fourmis, it, evap, phero, graphe);
		af.modifierNoeudDeDepart(graphe.obtenirNoeuds().get(0));
		af.traiterProbleme();
	}

	public double obtenirDistance() {
		if (af == null) return -1;
		return af.obtenirDistance();
	}

	public double obtenirTemps() {
		if (af == null) return -1;
		return af.obtenirTemps();
	}

	public String obtenirParcours()
	{
		ArrayList<NoeudList> listeNoeuds = new ArrayList<NoeudList>();
		String chemin = af.afficherSolution(af.obtenirNoeudDeDepart(), af.obtenirNbreNoeuds(), listeNoeuds);
		
		
		return chemin;
	}

};
