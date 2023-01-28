package Controlers;

import Entities.Article;
import Entities.Magazine;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlArticle
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlArticle() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Article> GetAllArticlesByIdMagazine(int numeroDuMagazine) {
        ArrayList<Article> lesArticles = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select idArticle, titreArticle, nbFeuillets from article where numMag = ?");
            ps.setInt(1, numeroDuMagazine);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article(rs.getInt("idArticle"), rs.getString("titreArticle"),rs.getInt("nbFeuillets"));
                lesArticles.add(article);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlArticle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesArticles;
    }

    public void AjouterNouvelArticle(String titre,int nbFeuillets,int numPigiste,int numMagazine)
    {
        try {
            ps = cnx.prepareStatement("insert into article values (?,?,?,?,?)");
            ps.setObject(1, null);
            ps.setString(2, titre);
            ps.setInt(3, nbFeuillets);
            ps.setInt(4, numPigiste);
            ps.setInt(5, numMagazine);
            ps.executeUpdate();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlArticle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
