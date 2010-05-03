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

public class Interface extends JFrame //implements ActionListener
{
	ComposantFourmis fourmis;
	ComposantGenetique genetique;
	JTextField nbreTests;

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
		contenuTestLancement.add(new JButton("Lancement"));

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

		/************************ Création des différents panneaux de l'interface *****************************/ 
		//JPanel pCreationGraphe = this.creerGraphe();

	/*
	 * Fonction créant et retournant le pannel de création un graphe en fonction des données que rentre l'utilisateur
	 */
	/*
	public JPanel creerGraphe()
	{
		JPanel pCreationGraphe = new JPanel();
		JLabel lcreationGraphe =  new JLabel("Création d'un graphe");
		
		
		pCreationGraphe.add(lcreationGraphe);
		lcreationGraphe.setBounds(229, 5, 200, 16);
		{
			lNomGraphe = new JLabel();
			pCreationGraphe.add(lNomGraphe);
			lNomGraphe.setText("Nom du graphe");
			lNomGraphe.setBounds(12, 21, 100, 16);
		}
		return pCreationGraphe;
	}
	
	public JPanel gererAlgoFourmis()
	{
		JPanel pAlgoFourmis = new JPanel();
		
		/******* Création des différents textfield et labels pour entrer les paramètres ********/
	/*
		return pAlgoFourmis;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getActionCommand().compareTo("AlgoFourmis")==0)
		{
			int nbreIterations = Integer.parseInt(this.nbreIterations.getText());
			int nbreFourmis = Integer.parseInt(this.nbreFourmis.getText());
			int vitesseEvapPheromone = Integer.parseInt(this.vitesseEvapPheromone.getText());
			double nbrePheromoneAEvap = Double.parseDouble(this.nbrePheromoneAEvap.getText());
			AlgoFourmis algoF = new AlgoFourmis(nbreFourmis, nbreIterations,vitesseEvapPheromone,nbrePheromoneAEvap,graphe);
			algoF.traiterProbleme(noeudDepart);
		}else if(e.getActionCommand().compareTo("AlgoGenetique")==0)
		{
			
		}
		
		}*/
}
