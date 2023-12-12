package com.JacobKW;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraphPanel extends JFrame {
    private static final long serialVersionUID = 1L;

    static DefaultCategoryDataset dataset  =  new DefaultCategoryDataset();
    static String series1 = "Infected";
    static String series2 = "Dead";
    static String series3 = "Alive";
    String infectionName = "";

    ImageIcon graphImage = new ImageIcon("Graph.png");

    public GraphPanel(String name) {
        this.infectionName = name;
        generateGraph();
    }

    //Gets the graph from createChartPanel() the graph and JFrame which is displayed to the user
    private void generateGraph() {
        setTitle("Epidemulation - Graph");

        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);

        setIconImage(graphImage.getImage());
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Generates the graph
    private JPanel createChartPanel() {
        String chartTitle = "Graph of " + infectionName;
        String categoryAxisLabel = "Generation Number";
        String valueAxisLabel = "Population Count";

        JFreeChart chart = ChartFactory.createLineChart(chartTitle,
                categoryAxisLabel, valueAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, false);

        return new ChartPanel(chart);
    }

    // Clears the dataset for when the simulation is reset
    public static void clearDataSet() {
        dataset = new DefaultCategoryDataset();
    }

    // Adds the data passed to it to the dataset in a pre-determined format
    public static void addToDataSet(int alive, int infected, int dead, int genNum) {
        dataset.addValue(infected, series1, String.valueOf(genNum));
        dataset.addValue(dead, series2, String.valueOf(genNum));
        dataset.addValue(alive, series3, String.valueOf(genNum));
    }
}