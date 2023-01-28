package Controlers;

import Entities.Magazine;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlMagazine
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMagazine() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Magazine> GetAllMagazines() {
        ArrayList<Magazine> lesMagazines = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select idMag, nomMag, nomSpe from magazine inner join specialite on numSpecialite = idSpe");
            rs = ps.executeQuery();
            while (rs.next()) {
                Magazine magazine = new Magazine(rs.getInt("idMag"), rs.getString("nomMag"),rs.getString("nomSpe"));
                lesMagazines.add(magazine);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMagazine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesMagazines;
    }

    public double GetMontantMagazine(int numeroDuMagazine)
    {
        double montant = 0;
        try {
            ps = cnx.prepareStatement("select sum(nbFeuillets * prixFeuillet) as total from article inner join pigiste on numPig = idPigiste WHERE numMag = ?");
            ps.setInt(1, numeroDuMagazine);
            rs = ps.executeQuery();
            rs.next();
            montant = rs.getDouble("total");
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMagazine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (double) Math.round(montant*100)/100;
    }
}
