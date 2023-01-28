package Vues;

import Controlers.CtrlGraphique;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmStats extends JFrame
{
    private JPanel rootPane;
    private JLabel lblTitre;
    private JPanel pnlGraph1;
    private JPanel pnlGraph2;
    private JPanel pnlGraph3;
    private JPanel pnlGraph4;

    private CtrlGraphique ctrlGraphique;
    public FrmStats()
    {
        this.setTitle("Statistiques");
        this.setContentPane(rootPane);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);

                ctrlGraphique = new CtrlGraphique();
                // Graphique n°1 : Courbe
                DefaultCategoryDataset donnees = new DefaultCategoryDataset();
                int nbArticles;
                String nomMagazine;
                // Remplir le dataSet à partir des données provenant de la HashMap
                for (String valeur : ctrlGraphique.GetDatasGraphique1().keySet())
                {
                    nbArticles = ctrlGraphique.GetDatasGraphique1().get(valeur);
                    nomMagazine = valeur;
                    donnees.setValue(nbArticles,"",nomMagazine);
                }
                // Créer le graphique avec ses options
                JFreeChart chart1 = ChartFactory.createLineChart(
                        "Nombre d'articles par magazine",
                        "Nom des magazines",
                        "Nombre d'articles",
                        donnees,
                        PlotOrientation.VERTICAL,false, true, false);
                // le graphique sera dans un JPanel
                ChartPanel graph = new ChartPanel(chart1);
                // Ajout du graphique dans le JPanel
                pnlGraph1.add(graph);
                // Mettre à jour le JPanel
                pnlGraph1.validate();

                // Graphique n°2 : Histogramme plusieurs séries
                donnees = new DefaultCategoryDataset();
                // Remplir le dataSet à partir des données provenant de la HashMap
                for (String valeur : ctrlGraphique.GetDatasGraphique2().keySet())
                {
                    for(int i = 0;i< ctrlGraphique.GetDatasGraphique2().get(valeur).size();i+=2)
                    {
                        donnees.setValue
                                (
                                Double.parseDouble(ctrlGraphique.GetDatasGraphique2().get(valeur).get(i+1)),
                                valeur.toString(),
                                ctrlGraphique.GetDatasGraphique2().get(valeur).get(i).toString()
                                );
                    }
                }
                // Créer le graphique avec ses options
                chart1 = ChartFactory.createBarChart(
                        "Nombre d'articles par magazine et par pigiste",
                        "Nom des pigistes",
                        "Nombre d'articles",
                        donnees,
                        PlotOrientation.VERTICAL,
                        true, true, false);
                // le graphique sera dans un JPanel
                graph = new ChartPanel(chart1);
                // Ajout du graphique dans le JPanel
                pnlGraph2.add(graph);
                // Mettre à jour le JPanel
                pnlGraph2.validate();

                // Graphique n°3 : Donuts

                DefaultPieDataset dataset = new DefaultPieDataset( );
                int nbPigiste;
                String nomSpecialite;
                for (String valeur : ctrlGraphique.GetDatasGraphique3().keySet())
                {
                    nbPigiste = ctrlGraphique.GetDatasGraphique3().get(valeur);
                    nomSpecialite = valeur;

                    dataset.setValue(nomSpecialite,nbPigiste);
                }
                chart1 = ChartFactory.createRingChart("Nombre de pigistes par spécialité", dataset, true, false, false);
                RingPlot plot = (RingPlot) chart1.getPlot();
                plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
                //plot.setSectionDepth(0.5);
                graph = new ChartPanel(chart1);
                pnlGraph3.add(graph);
                pnlGraph3.validate();

                // Graphique n°4 : Histogramme 1 seule série

                donnees = new DefaultCategoryDataset();
                double total;
                String nomPigiste;
                for (String valeur : ctrlGraphique.GetDatasGraphique4().keySet())
                {
                    total = ctrlGraphique.GetDatasGraphique4().get(valeur);
                    nomPigiste = valeur;
                    donnees.setValue(total,"",nomPigiste);
                }
                chart1 = ChartFactory.createBarChart(
                        "Chiffre d'affaires par pigiste",
                        "Nom des pigistes",
                    "Montant du CA",
                        donnees,
                        PlotOrientation.VERTICAL,
                        false, true, false);
                graph = new ChartPanel(chart1);
                pnlGraph4.add(graph);
                pnlGraph4.validate();
            }
        });
    }
}
