package InterfaceGraphique;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class ComposantFourmis {
	private JTextField nbreIterations;
	private JTextField nbreFourmis;
	private JTextField vitesseEvapPheromone;

	public ComposantFourmis() {
		nbreIterations = new JTextField(4);
		nbreIterations.setText("10");
		nbreFourmis = new JTextField(4);
		nbreFourmis.setText("20");
		vitesseEvapPheromone = new JTextField(4);
		vitesseEvapPheromone.setText("0.34");
	}

	public JPanel creerPanneauParametre() {
		JPanel contenuParamFourmis = new JPanel();
		contenuParamFourmis.setLayout(new BoxLayout(contenuParamFourmis, BoxLayout.Y_AXIS));
		contenuParamFourmis.add(new JLabel("Algorithme Fourmis"));
		JPanel contenuParamFourmisGrid = new JPanel();
		contenuParamFourmis.add(contenuParamFourmisGrid);
		contenuParamFourmisGrid.setLayout(new GridLayout(3, 2));
		contenuParamFourmisGrid.add(new JLabel("Nombre d'itérations"));
		contenuParamFourmisGrid.add(nbreIterations);
		contenuParamFourmisGrid.add(new JLabel("Nombre de fourmis"));
		contenuParamFourmisGrid.add(nbreFourmis);
		contenuParamFourmisGrid.add(new JLabel("Vitesse d'évaporation des phéromonoes"));
		contenuParamFourmisGrid.add(vitesseEvapPheromone);
		return contenuParamFourmis;
	}

	public void lancement(JLabel sortie) {
		StringBuffer text = new StringBuffer(sortie.getText());
		text.append("Fourmis\n");
		sortie.setText(text.toString());
	}
};
