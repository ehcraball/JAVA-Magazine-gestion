package Vues;

import Tools.ConnexionBDD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FrmMenu extends JFrame{
    private JButton btnGestion;
    private JButton btnStats;
    private JPanel rootPane;
    private JLabel lblTitre;
    private ConnexionBDD cnx;

    public FrmMenu() throws SQLException, ClassNotFoundException {
        this.setTitle("Menu");
        this.setContentPane(rootPane);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        cnx = new ConnexionBDD();

        btnGestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestion frm = new FrmGestion();
                frm.setVisible(true);
            }
        });
        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmStats frm = new FrmStats();
                frm.setVisible(true);
            }
        });
    }
}
