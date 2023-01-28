package Controlers;

import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlGraphique
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlGraphique() {
        cnx = ConnexionBDD.getCnx();
    }
    public HashMap<String,Integer> GetDatasGraphique1()
    {
        HashMap<String, Integer> datas = new HashMap();
        try {
            ps = cnx.prepareStatement("select nomMag, count(*) as nbArticles " +
                    "from magazine " +
                    "inner join article on idMag = numMag " +
                    "group by nomMag");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString("nomMag"), rs.getInt("nbArticles"));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }
    public HashMap<String, ArrayList<String>> GetDatasGraphique2()
    {
        HashMap<String, ArrayList<String>> datas = new HashMap();
        try {
            ps = cnx.prepareStatement("SELECT magazine.nomMag,pigiste.nomPigiste, COUNT(*) as nb\n" +
                    "from pigiste\n" +
                    "inner join article on pigiste.idPigiste = article.numPig\n" +
                    "inner join magazine on article.numMag = magazine.idMag\n" +
                    "group by magazine.nomMag, pigiste.nomPigiste");
            rs = ps.executeQuery();
            while(rs.next())
            {
                if(!datas.containsKey(rs.getString("nomMag")))
                {
                    ArrayList<String> lesMagazines = new ArrayList<>();
                    lesMagazines.add(rs.getString("nomPigiste"));
                    lesMagazines.add(rs.getString("nb"));
                    datas.put(rs.getString("nomMag"),lesMagazines);
                }
                else
                {
                    datas.get(rs.getString("nomMag")).add(rs.getString("nomPigiste"));
                    datas.get(rs.getString("nomMag")).add(rs.getString("nb"));
                }
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }
    public HashMap<String,Integer> GetDatasGraphique3()
    {
        HashMap<String, Integer> datas = new HashMap();
        try {
            ps = cnx.prepareStatement("SELECT specialite.nomSpe,count(*) as nb\n" +
                    "from specialite\n" +
                    "inner join posseder on posseder.numSpe = specialite.idSpe\n" +
                    "GROUP by specialite.nomSpe");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString("nomSpe"), rs.getInt("nb"));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }
    public HashMap<String,Double> GetDatasGraphique4()
    {
        HashMap<String, Double> datas = new HashMap();
        try {
            ps = cnx.prepareStatement("select pigiste.nomPigiste, round(sum(pigiste.prixFeuillet * article.nbFeuillets),2) as total\n" +
                    "from pigiste\n" +
                    "inner join article on article.numPig = pigiste.idPigiste\n" +
                    "group by pigiste.nomPigiste");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString("nomPigiste"), rs.getDouble("total"));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }
}
