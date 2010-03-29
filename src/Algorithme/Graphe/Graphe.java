package Algorithme.Graphe;


/**
 *  Class Graphe
 */
public class Graphe {
	private String nom;
	private Poids[][] poids;
	private int nbreNoeuds;
	
	public Graphe(String nom, int nbreNoeuds)
	{
		poids = new Poids[nbreNoeuds][nbreNoeuds]();
		this.nbreNoeuds = nbreNoeuds;

		this.nom = nom;
	}

	/**
	 *  Constructeur de copie
	 */
	public Graphe(String nom, Graphe graphe) {
		setNbreNoeuds(graphe.getNbreNoeuds());
		this.nom = nom;
	}

	public void vider() {
		for (int i = 0; i < nbreNoeuds; i++)
			for (int j = 0; j < nbreNoeuds; j++)
				poids[i][j].set(0);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	// Non fonctionnel : this.noeuds, non déclarer
	public ArrayList<Noeud> getSuivants(Noeud noeud) {
		ArrayList<Noeud> noeuds = new ArrayList<Noeud>();

		int i = noeud.getId();
		for (int j = 0; j < nbreNoeuds; j++) {
			if (poids[i][j].get() != 0)
				noeuds.add(this.noeuds.get(j));
		}

		return noeuds;
	}

	public int getPoids(int i, int j) {
		if (i < nbreNoeuds || j < nbreNoeuds)
			throw new IllegalStateException("Graphe.getPoids : Dépassement de la fin du graphe");
		return poids[i][j].getPoids();
	}
	public void setPoids(int poids, int i, int j) {
		if (i < nbreNoeuds || j < nbreNoeuds)
			throw new IllegalStateException("Graphe.setPoids : Dépassement de la fin du graphe");
		this.poids[i][j].setPoids(poids);
	}

	public int getNbreNoeuds() {
		return nbreNoeuds;
	}
	public void setNbreNoeuds(int nbreNoeuds) {
		Poids[][] p = new Poids[nbreNoeuds][nbreNoeuds]();

		int n = (nbreNoeuds < this.nbreNoeuds) ? nbreNoeuds : this.nbreNOeuds;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				p[i][j].setPoids(poids[i][j].getPoids());
			}
		}

		this.nbreNoeuds = nbreNoeuds;
		this.poids = p;
	}
	
}
