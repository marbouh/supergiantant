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
		"Parcours", "Distance", "Temps",
		"Parcours", "Distance", "Temps"
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
						   String fparcours, double fdistance, int ftemps,
						   String gparcours, double gdistance, int gtemps) {
		ArrayList nouvelleLigne = new ArrayList();
		nouvelleLigne.add(numtest);
		nouvelleLigne.add(fparcours);
		nouvelleLigne.add(fdistance);
		nouvelleLigne.add(ftemps);
		nouvelleLigne.add(gparcours);
		nouvelleLigne.add(gdistance);
		nouvelleLigne.add(gtemps);
		donnees.add(nouvelleLigne);
		fireTableStructureChanged();
	}

	public void vider() {
		if (getRowCount() == 0) return;
		donnees.clear();
		fireTableStructureChanged();
	}
}
