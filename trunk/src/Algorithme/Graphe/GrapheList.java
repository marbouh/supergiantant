package Algorithme.Graphe;

/**
 * Class GraphList
 */
public class GrapheList implements Graphe {
	private String nom;
	private ArrayList<Noeud> noeuds;
	
	public GraphList(String nom, int nbreNoeuds) {
		this.nom = nom;
		noeuds = new ArrayList<Noeud>(nbreNoeuds);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public ArrayList<Noeud> getSuivants(Noeud noeud) {
		ArrayList<Noeud> ns = new ArrayList<Noeud>();
		Noeud n;
		ArrayList<Arrete> a = noeud.getArretes();

		for (Iterator it = a.iterator(); it.hasNext(); n = it.next())
			ns.add(n);
		
		return ns;
	}

	public boolean checkTrajet(Noeud noeudDepart, Noeud noeudArrivee) {
		Noeud n;

		for (Iterator it = noeud.getArretes(); it.hasNext(); n = it.next())
			if (n.compareTo(noeudArrivee))
				return true;
		return false;
	}

	public int getPoids(Noeud noeudDepart, Noeud noeudArrivee) {
		Noeud n;

		for (Iterator it = noeud.getArretes(); it.hasNext(); n = it.next())
			if (n.compareTo(noeudArrivee))
				return /* valeur du poids ^^ */;
	}

	public int setPoids
