package InterfaceGraphique;

import java.util.ArrayList;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import Algorithme.AlgorithmeGenetique.AlgoGenetique;
import Algorithme.Graphe.GrapheList;
import Algorithme.Graphe.NoeudList;

public class ComposantGenetique {
	private JTextField nbreIterations;
	private JTextField nbreIndividus;
	private JTextField tauxMutation;
	AlgoGenetique ag;

	public ComposantGenetique() {
		ag = null;
		nbreIterations = new JTextField(4);
		nbreIterations.setText("10");
		nbreIndividus = new JTextField(4);
		nbreIndividus.setText("20");
		tauxMutation = new JTextField(4);
		tauxMutation.setText("25");
	}

	public JPanel creerPanneauParametre() {
		JPanel contenuParamGenetique = new JPanel();
		contenuParamGenetique.setLayout(new BoxLayout(contenuParamGenetique, BoxLayout.Y_AXIS));
		contenuParamGenetique.add(new JLabel("Algorithme Génétique"));
		JPanel contenuParamGenetiqueGrid = new JPanel();
		contenuParamGenetique.add(contenuParamGenetiqueGrid);
		contenuParamGenetiqueGrid.setLayout(new GridLayout(3, 2));
		contenuParamGenetiqueGrid.add(new JLabel("Nombre d'itérations"));
		contenuParamGenetiqueGrid.add(nbreIterations);
		contenuParamGenetiqueGrid.add(new JLabel("Nombre de fourmis"));
		contenuParamGenetiqueGrid.add(nbreIndividus);
		contenuParamGenetiqueGrid.add(new JLabel("Taux de mutation"));
		contenuParamGenetiqueGrid.add(tauxMutation);
		return contenuParamGenetique;
	}

	public void lancement(GrapheList graphe) {
		int it = Integer.parseInt(nbreIterations.getText());
		int indiv = Integer.parseInt(nbreIndividus.getText());
		int mute = Integer.parseInt(tauxMutation.getText());
		ag = new AlgoGenetique(indiv, it, mute, graphe);
		ag.resoudre();
	}
	
	public int obtenirDistance() {
		if (ag == null) return -1;
		return ag.obtenirDistance();
	}

	public double obtenirTemps() {
		if (ag == null) return -1;
		return ag.obtenirTemps();
	}

	public String obtenirParcours() {
		ArrayList<NoeudList> noeuds = ag.obtenirSolution().getNoeuds();
		String parcours = "";
		for (int i = 0; i < noeuds.size(); i++) {
			parcours = parcours + noeuds.get(i).getId();
			if (i < (noeuds.size() - 1))
				parcours = parcours + ", ";
		}
		return parcours;
	}
};
