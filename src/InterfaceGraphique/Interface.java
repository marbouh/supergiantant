package InterfaceGraphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;

import Algorithme.AlgorithmeFourmis.AlgoFourmis;
import Algorithme.Graphe.GrapheList;
import Algorithme.Graphe.NoeudList;

public class Interface extends JFrame implements ActionListener
{
	ComposantFourmis fourmis;
	ComposantGenetique genetique;
	JTextField nbreTests;
	JLabel resultats;

	public Interface()
	{
		/* Onglet du graphe */
		JPanel contenuGraphe = new JPanel();

		/* Onglet des paramètres */
		fourmis = new ComposantFourmis();
		genetique = new ComposantGenetique();
		JPanel contenuParametre = new JPanel();
		contenuParametre.setLayout(new GridLayout(1, 2));
		contenuParametre.add(genetique.creerPanneauParametre());
		contenuParametre.add(fourmis.creerPanneauParametre());

		/* Onglet du test */
		JPanel contenuTest = new JPanel();
		contenuTest.setLayout(new BoxLayout(contenuTest, BoxLayout.Y_AXIS));
		JPanel contenuTestLancement = new JPanel();
		contenuTest.add(contenuTestLancement);
		contenuTestLancement.setLayout(new BoxLayout(contenuTestLancement, BoxLayout.X_AXIS));
		contenuTestLancement.add(new JLabel("Nombre de test"));
		nbreTests = new JTextField(4);
		nbreTests.setText("10");
		contenuTestLancement.add(nbreTests);
		JButton lancement = new JButton("Lancement");
		contenuTestLancement.add(lancement);
		lancement.addActionListener(this);
		resultats = new JLabel("Sortie");
		contenuTest.add(resultats);

		/* Fenetre principal */
		JTabbedPane tab = new JTabbedPane();
		tab.addTab("Graphe", null, contenuGraphe, "Représentation du graphe initiale");
		tab.addTab("Paramètres", null, contenuParametre, "Paramètres");
		tab.addTab("Test", null, contenuTest, "Lance le test");

		setContentPane(tab);
		this.setLocationRelativeTo(null);
		this.setTitle("Comparateur d'algorithmes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.setVisible(true);
		pack();
	}

	public void actionPerformed(ActionEvent evenement) {
		int nbreTests = Integer.parseInt(this.nbreTests.getText());
		for (int i = 0; i < nbreTests; i++) {
			fourmis.lancement(resultats);
			genetique.lancement(resultats);
		}
	}
}
