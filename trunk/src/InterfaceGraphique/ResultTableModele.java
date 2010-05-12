package InterfaceGraphique;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Describe class ResultTableModele here.
 *
 *
 * Created: Thu May  6 13:58:59 2010
 *
 * @author <a href="mailto:demix@Pétunia"></a>
 * @version 1.0
 */
public class ResultTableModele extends AbstractTableModel {
	private String nomColonnes[] = {
		"Num test",
		"Parcours", "Distance", "Temps (ms)",
		"Parcours", "Distance", "Temps (ms)"
	};

	private ArrayList donnees;

	public ResultTableModele() {
		donnees = new ArrayList();
	}

	public int getColumnCount() {
		return nomColonnes.length;
	}
	
	public int getRowCount() {
		return donnees.size();
	}

	public String getColumnName(int col) {
		return nomColonnes[col];
	}

	public Object getValueAt(int ligne, int col) {
		return ((ArrayList)donnees.get(ligne)).get(col);
	}

	public Class getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}

	public boolean isCellEditable(int ligne, int col) {
		return false;
	}

	public void setValueAt(Object value, int ligne, int col) {
		fireTableCellUpdated(ligne, col);
	}

	public void ajoutLigne(int numtest, 
						   String gparcours, double gdistance, double gtemps,
						   String fparcours, double fdistance, double ftemps) {
		ArrayList nouvelleLigne = new ArrayList();
		if (numtest == -1)
			nouvelleLigne.add("Moyenne");
		else
			nouvelleLigne.add(numtest);
		nouvelleLigne.add(gparcours);
		nouvelleLigne.add(gdistance);
		nouvelleLigne.add(gtemps);
		nouvelleLigne.add(fparcours);
		nouvelleLigne.add(fdistance);
		nouvelleLigne.add(ftemps);
		donnees.add(nouvelleLigne);
		int ligne = getRowCount();
		fireTableRowsInserted(ligne - 1, ligne);
		fireTableRowsUpdated(ligne - 1, ligne);
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	public void ajoutMoyenne() {
		double gdistance = 0.;
		double fdistance = 0.;
		double gtemps = 0.;
		double ftemps = 0.;
		
		for (int i = 0; i < getRowCount(); i++) {
			gdistance += Double.valueOf(getValueAt(i, 2).toString());
			gtemps += Double.valueOf(getValueAt(i, 3).toString());
			fdistance += Double.valueOf(getValueAt(i, 5).toString());
			ftemps += Double.valueOf(getValueAt(i, 6).toString());
		}
		
		gdistance /= getRowCount();
		gtemps /= getRowCount();
		fdistance /= getRowCount();
		ftemps /= getRowCount();

		ajoutLigne(-1, "- Genetique -", gdistance, gtemps,
				   "- Fourmis -", fdistance, ftemps);
	}
	
	public void vider() {
		if (getRowCount() == 0) return;
		donnees.clear();
		fireTableStructureChanged();
	}
}
