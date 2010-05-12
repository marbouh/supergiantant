package InterfaceGraphique;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import Algorithme.AlgorithmeFourmis.AlgoFourmis;
import Algorithme.Graphe.GrapheList;
import Algorithme.Graphe.NoeudList;
import Algorithme.Graphe.ArreteList;

public class Interface extends JFrame implements ActionListener
{
	ComposantFourmis fourmis;
	ComposantGenetique genetique;
	JTextField nbreTests;
	JTable resultat;
	GrapheList graphe;

	public Interface()
	{
		recupGraphe();
		/* Onglet du graphe */
		JPanel contenuGraphe = new JPanel();
		contenuGraphe.setLayout(new GridLayout(1, 1));
		ListenableGraph g = new ListenableUndirectedGraph(DefaultEdge.class);
		JGraphModelAdapter adapt = new JGraphModelAdapter(g);
		JGraph jgraph = new JGraph(adapt);
		JScrollPane scrollpane = new JScrollPane(jgraph);
		contenuGraphe.add(scrollpane);

		for (int i = 0; i < graphe.obtenirNbreNoeuds(); i++) {
			g.addVertex("" + graphe.obtenirNoeuds().get(i).obtenirId());
			positionNoeud("" + graphe.obtenirNoeuds().get(i).obtenirId(), adapt, i, 600, 600);
		}

		for (int i = 0; i < graphe.obtenirNbreNoeuds(); i++) {
			NoeudList n = graphe.obtenirNoeuds().get(i);
			for (int j = 0; j < n.obtenirDestinations().size(); j++) {
				ArreteList a = n.obtenirDestinations().get(j);
				g.addEdge("" + n.obtenirId(), "" + a.obtenirArrivee().obtenirId(), a.obtenirPoids());
			}
		}

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
		nbreTests.setMaximumSize(new Dimension(2000, 30));
		nbreTests.setText("100");
		contenuTestLancement.add(nbreTests);
		JButton lancement = new JButton("Lancement");
		contenuTestLancement.add(lancement);
		lancement.addActionListener(this);
		/***********************************************************************************/
		String[] nomColonnes = {
			"Parcours", "Distance", "Temps", 
			"Parcours", "Distance", "Temps"
		};
		resultat = new JTable(new ResultTableModele());
		JScrollPane scroll = new JScrollPane(resultat);
		ResultTableModele refModele = (ResultTableModele)resultat.getModel();
		contenuTest.add(scroll);

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

	public void positionNoeud(Object vertex, JGraphModelAdapter adapt, int num, int largeur, int hauteur) {
		double angle = (2. * Math.PI / graphe.obtenirNbreNoeuds()) * num;
		int demiLargeur = largeur / 2;
		int demiHauteur = hauteur / 2;
		int x = (int)(demiLargeur * Math.cos(angle) + demiLargeur);
		int y = (int)(demiHauteur * Math.sin(angle) + demiHauteur);

		DefaultGraphCell cell = adapt.getVertexCell(vertex);
        Map attr = cell.getAttributes();
        Rectangle b = GraphConstants.getBounds(attr).getBounds();

        GraphConstants.setBounds(attr, new Rectangle(x, y, b.width, b.height));

        Map cellAttr = new HashMap();
        cellAttr.put(cell, attr);
        adapt.edit(cellAttr, null, null, null);
	}

	public void actionPerformed(ActionEvent evenement) {
		ResultTableModele refModele = (ResultTableModele)resultat.getModel();
		refModele.vider();
		int nbreTests = Integer.parseInt(this.nbreTests.getText());
		for (int i = 0; i < nbreTests; i++) {
		   	genetique.lancement(graphe);
			fourmis.lancement(graphe);
			refModele.ajoutLigne(i + 1, 
								 genetique.obtenirParcours(), 
								 genetique.obtenirDistance(), 
								 genetique.obtenirTemps() / 1000000,
								 fourmis.obtenirParcours(), 
								 fourmis.obtenirDistance(), 
								 fourmis.obtenirTemps() / 1000000);
		}
		refModele.ajoutMoyenne();
	}
	
	public void recupGraphe() {
		/*graphe = new GrapheList("AG", 10);
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

		//		n1.ajouterDestination(n2, 4);
		n1.ajouterDestination(n3, 8);
		//		n1.ajouterDestination(n4, 5);
		//		n1.ajouterDestination(n5, 6);
		//		n1.ajouterDestination(n6, 2);
		//		n1.ajouterDestination(n7, 8);
		//		n1.ajouterDestination(n8, 3);
		//		n1.ajouterDestination(n9, 6);
		//		n1.ajouterDestination(n10, 15);
		//		n2.ajouterDestination(n3, 3);
		//		n2.ajouterDestination(n4, 10);
		//		n2.ajouterDestination(n5, 7);
		//		n2.ajouterDestination(n6, 1);
		//		n2.ajouterDestination(n7, 12);
		//		n2.ajouterDestination(n8, 7);
		n2.ajouterDestination(n9, 6);
		n2.ajouterDestination(n10, 3);		
		//		n3.ajouterDestination(n4, 9);
		n3.ajouterDestination(n5, 5);
		//		n3.ajouterDestination(n6, 8);
		//		n3.ajouterDestination(n7, 5);
		//		n3.ajouterDestination(n8, 13);
		//		n3.ajouterDestination(n9, 16);
		//		n3.ajouterDestination(n10, 19);
		n4.ajouterDestination(n5, 2);
		//		n4.ajouterDestination(n6, 10);
		//		n4.ajouterDestination(n7, 17);
		n4.ajouterDestination(n8, 4);
		//		n4.ajouterDestination(n9, 11);
		//		n4.ajouterDestination(n10, 10);
		//		n5.ajouterDestination(n6, 7);
		//		n5.ajouterDestination(n7, 18);
		//		n5.ajouterDestination(n8, 1);
		//		n5.ajouterDestination(n9, 4);
		//		n5.ajouterDestination(n10, 9);
		n6.ajouterDestination(n7, 13);
		//		n6.ajouterDestination(n8, 8);
		//		n6.ajouterDestination(n9, 7);
		//		n6.ajouterDestination(n10, 7);
		//		n7.ajouterDestination(n8, 14);
		n7.ajouterDestination(n9, 9);
		//		n7.ajouterDestination(n10, 19);
		//		n8.ajouterDestination(n9, 13);
		n8.ajouterDestination(n10, 3);
		//		n9.ajouterDestination(n10, 20);

		graphe.ajouterNoeud(n1);
		graphe.ajouterNoeud(n2);
		graphe.ajouterNoeud(n3);
		graphe.ajouterNoeud(n4);
		graphe.ajouterNoeud(n5);
		graphe.ajouterNoeud(n6);
		graphe.ajouterNoeud(n7);
		graphe.ajouterNoeud(n8);
		graphe.ajouterNoeud(n9);
		graphe.ajouterNoeud(n10);*/
		
		
		
		graphe = new GrapheList("Test",6);
		NoeudList n1 = new NoeudList(1);
		NoeudList n2 = new NoeudList(2);
		NoeudList n3 = new NoeudList(3);
		NoeudList n4 = new NoeudList(4);
		NoeudList n5 = new NoeudList(5);
		NoeudList n6 = new NoeudList(6);
		
		//n1.ajouterDestination(n2, 4);
		//n1.ajouterDestination(n3, 5);
		//n1.ajouterDestination(n4, 3);
		//		n1.ajouterDestination(n5, 1);
		n1.ajouterDestination(n6, 8);
		n2.ajouterDestination(n4, 1);
		n2.ajouterDestination(n5, 1);
		n2.ajouterDestination(n3, 3);
		//n2.ajouterDestination(n6, 2);
		n3.ajouterDestination(n5, 1);
		n3.ajouterDestination(n6, 1);
		n3.ajouterDestination(n4, 5);
		n4.ajouterDestination(n5, 2);
		n4.ajouterDestination(n6, 1);
		n6.ajouterDestination(n5, 4);
		
		graphe.ajouterNoeud(n1);
		graphe.ajouterNoeud(n2);
		graphe.ajouterNoeud(n3);
		graphe.ajouterNoeud(n4);
		graphe.ajouterNoeud(n5);
		graphe.ajouterNoeud(n6);
	}
}
