package Vues;

import Controlers.CtrlArticle;
import Controlers.CtrlMagazine;
import Controlers.CtrlPigiste;
import Entities.Article;
import Entities.Pigiste;
import Tools.ModelJTable;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class FrmGestion extends JFrame {

    private JPanel rootPane;
    private JLabel lblTitre;
    private JLabel lblMagazine;
    private JTextField txtMontantMagazine;
    private JTextField txtNomPigiste;
    private JButton btnAjouter;
    private JLabel lblMontant;
    private JLabel lblPigiste;
    private JLabel lblAjouter;
    private JLabel lblTitreArticle;
    private JLabel lblChoixPigiste;
    private JLabel lblNbFeuillets;
    private JTextField txtTitreArticle;
    private JComboBox cboPigistes;
    private JSpinner spNbFeuillets;
    private JLabel lblArticle;
    private JTable tblTotauxPigistes;
    private JTable tblMagazines;
    private JTable tblArticles;

    private CtrlMagazine ctrlMagazine;
    private CtrlArticle ctrlArticle;
    private CtrlPigiste ctrlPigiste;
    private ModelJTable modelJTable;

    public FrmGestion()
    {
        this.setTitle("Gestion");
        this.setContentPane(rootPane);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlMagazine = new CtrlMagazine();
        ctrlArticle = new CtrlArticle();
        ctrlPigiste = new CtrlPigiste();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);

                spNbFeuillets.setModel(new SpinnerNumberModel(01,01,10,1));
                modelJTable = new ModelJTable();
                modelJTable.loadDatasMagazines(ctrlMagazine.GetAllMagazines());
                tblMagazines.setModel(modelJTable);

                for (Pigiste pigiste:ctrlPigiste.GetAllPigistes())
                {
                    cboPigistes.addItem(pigiste.getNomPigiste());
                }
            }
        });
        tblMagazines.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                InfosArticles();
            }
        });
        tblArticles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int numArticle = Integer.parseInt(tblArticles.getValueAt(tblArticles.getSelectedRow(), 0).toString());
                txtNomPigiste.setText(String.valueOf(ctrlPigiste.GetNomPigisteByIdArticle(numArticle)));

            }
        });
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(tblMagazines.getSelectedRowCount() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Sélectionner un magazine","Choix du magazine",JOptionPane.WARNING_MESSAGE);
                }
                else if(txtTitreArticle.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Saisir un titre","Erreur de saisie",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    // Récupérer le pigiste
                    int numPigiste = ctrlPigiste.GetNumPigisteByName(cboPigistes.getSelectedItem().toString());
                    // Vérifier s'il possède la spécialité

                    if(ctrlPigiste.GetSpecialitesByPigiste(numPigiste, tblMagazines.getValueAt(tblMagazines.getSelectedRow(), 2).toString()))
                    {
                        ctrlArticle.AjouterNouvelArticle(txtTitreArticle.getText(), Integer.parseInt(spNbFeuillets.getValue().toString()),numPigiste, Integer.parseInt(tblMagazines.getValueAt(tblMagazines.getSelectedRow(), 0).toString()));
                        InfosArticles();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Le pigiste choisi ne possède pas \nla spécialité du magazine","Choix du pigiste",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    public void InfosArticles()
    {
        int numMagazine = Integer.parseInt(tblMagazines.getValueAt(tblMagazines.getSelectedRow(), 0).toString());
        modelJTable = new ModelJTable();
        modelJTable.loadDatasArticles(ctrlArticle.GetAllArticlesByIdMagazine(numMagazine));
        tblArticles.setModel(modelJTable);
        txtMontantMagazine.setText(String.valueOf(ctrlMagazine.GetMontantMagazine(numMagazine)));
        modelJTable = new ModelJTable();
        modelJTable.loadDatasTotalPigiste(ctrlPigiste.GetTotalPigistes(numMagazine));
        tblTotauxPigistes.setModel(modelJTable);
    }
}
