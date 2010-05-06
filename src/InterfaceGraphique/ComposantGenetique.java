package InterfaceGraphique;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class ComposantGenetique {
	private JTextField nbreIterations;
	private JTextField nbreIndividus;
	private JTextField tauxMutation;

	public ComposantGenetique() {
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

	public void lancement(JLabel sortie) {
		StringBuffer text = new StringBuffer(sortie.getText());
		text.append("Génétique\n");
		sortie.setText(text.toString());
	}
};
