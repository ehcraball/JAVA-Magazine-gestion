package Controlers;

import Entities.Magazine;
import Entities.Pigiste;
import Entities.TotalPigiste;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlPigiste
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlPigiste() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Pigiste> GetAllPigistes() {
        ArrayList<Pigiste> lesPigistes = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select idPigiste, nomPigiste, prixFeuillet from pigiste");
            rs = ps.executeQuery();
            while (rs.next()) {
                Pigiste pigiste = new Pigiste(rs.getInt("idPigiste"), rs.getString("nomPigiste"),rs.getDouble("prixFeuillet"));
                lesPigistes.add(pigiste);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlPigiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesPigistes;
    }

    public String GetNomPigisteByIdArticle(int numeroArticle)
    {
        String nomPigiste = "";
        try {
            ps = cnx.prepareStatement("select nomPigiste from pigiste inner join article on idPigiste = numPig and idArticle = ?");
            ps.setInt(1, numeroArticle);
            rs = ps.executeQuery();
            rs.next();
            nomPigiste = rs.getString("nomPigiste");
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlPigiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomPigiste;
    }

    public boolean GetSpecialitesByPigiste(int numPigiste,String nomSpecialiteMagazine)
    {
        boolean trouve = false;
        try {
            ps = cnx.prepareStatement("select nomSpe from specialite inner join posseder on idSpe = numSpe and numPig = ?");
            ps.setInt(1, numPigiste);
            rs = ps.executeQuery();
            while(rs.next() && !trouve)
            {
                if(rs.getString("nomSpe").compareTo(nomSpecialiteMagazine)==0)
                {
                    trouve = true;
                }
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlPigiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trouve;
    }

    public int GetNumPigisteByName(String nomPigiste)
    {
        int numPigiste = 0;
        try {
            ps = cnx.prepareStatement("select idPigiste from pigiste where nomPigiste = ?");
            ps.setString(1, nomPigiste);
            rs = ps.executeQuery();
            rs.next();
            numPigiste = rs.getInt("idPigiste");
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlPigiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numPigiste;
    }
    public ArrayList<TotalPigiste> GetTotalPigistes(int numMagazine) {
        ArrayList<TotalPigiste> lesTotaux = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select pigiste.nomPigiste, round(sum(pigiste.prixFeuillet * article.nbFeuillets),2) as total\n" +
                    "                    from pigiste\n" +
                    "                    inner join article on article.numPig = pigiste.idPigiste\n" +
                    "                    where article.numMag = ?\n" +
                    "                    group by pigiste.nomPigiste");
            ps.setInt(1, numMagazine);
            rs = ps.executeQuery();
            while (rs.next()) {
                TotalPigiste total = new TotalPigiste(rs.getString("nomPigiste"), rs.getDouble("total"));
                lesTotaux.add(total);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlPigiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesTotaux;
    }
}
