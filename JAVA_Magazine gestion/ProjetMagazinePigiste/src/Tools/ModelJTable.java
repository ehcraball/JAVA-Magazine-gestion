package Tools;

import Entities.Article;
import Entities.Magazine;
import Entities.Pigiste;
import Entities.TotalPigiste;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel {
    private String[] nomsColonnes;
    private Object[][] rows;

    @Override
    public String getColumnName(int column) {
        return nomsColonnes[column];
    }

    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return nomsColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    public void loadDatasMagazines(ArrayList<Magazine> lesMagazines) {
        nomsColonnes = new String[]{"Numéro", "Nom","Spécialité"};
        rows = new Object[lesMagazines.size()][3];
        int i = 0;
        for (Magazine magazine : lesMagazines) {
            rows[i][0] = magazine.getNumMagazine();
            rows[i][1] = magazine.getNomMagazine();
            rows[i][2] = magazine.getLaSpecialite();
            i++;
        }
        fireTableChanged(null);
    }
    public void loadDatasArticles(ArrayList<Article> lesArticles) {
        nomsColonnes = new String[]{"Numéro", "Titre","Nombre de feuillets"};
        rows = new Object[lesArticles.size()][3];
        int i = 0;
        for (Article article : lesArticles) {
            rows[i][0] = article.getIdArticle();
            rows[i][1] = article.getTitreArticle();
            rows[i][2] = article.getNbFeuillets();
            i++;
        }
        fireTableChanged(null);
    }
    public void loadDatasTotalPigiste(ArrayList<TotalPigiste> lesTotaux) {
        nomsColonnes = new String[]{"Nom", "Montant"};
        rows = new Object[lesTotaux.size()][2];
        int i = 0;
        for (TotalPigiste total : lesTotaux) {
            rows[i][0] = total.getNomPigiste();
            rows[i][1] = total.getTotal();
            i++;
        }
        fireTableChanged(null);
    }
}
