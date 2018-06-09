/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

/**
 *
 * @author Nigel Currie
 * 
 * see https://matplotlib.org/users/pyplot_tutorial.html for introduction to PyPlot
 * see https://knowm.org/open-source/xchart/xchart-example-code/ for XChart example code
 * see https://github.com/eseifert/gral/wiki/tutorial for tutorials on Gral
 * see http://www.jzy3d.org/ for a 3D plotting library
 * 
 * The different charts supported by XChart:
 * 
 *      Bubble Chart (no equivalent)
 *      Category Chart (hist, bar)
 *      Dial Chart (no equivalent)
 *      OHLC Chart (a chart showing error ranges)
 *      Pie Chart (pie)
 *      Radar Chart (no equivalent)
 *      XY Chart (scatter, plot)
 * 
 *      Implement bar, hist, pie, plot and scatter.
 * 
 *      Histograms are used to show distributions of variables while bar charts are used to compare
 *      variables. Histograms plot quantitative data with ranges of the data grouped into bins or
 *      intervals while bar charts plot categorical data.
 * 
 * Next things to do:
 * 
 * 1. Support for multiple series
 * Would be best if we returned the XChart series object from the call to plot, etc., and 
 * provide support for getting the XChart chart object from the Plotter object, so that the user
 * can customise their charts.
 * 2. Support for the other chart types
 * 3. Support for multiple charts on the same window
 * 4. Methods to set the variables of the chart
 * 5. Overrides for setting the data, int arrays, Lists, etc.
 */
public class Plotter {
    private String title = "Title";
    private String xAxisLabel = "X";
    private String yAxisLabel = "Y";
    private int width = 600;
    private int height = 320;
    private String styleFormatStr;
    private ChartStyle chartStyle;
    private String histLegend;
    private double[] xData;
    private double[] yData;
    private List<Series> seriesList = new ArrayList<>();
    
    private XYChart chart;
    private CategoryChart histChart;
    
    /**
     * Default constructor. Sets title = "Title", xLabel = "X", and 
     * yLabel = "Y".
     */   
    public Plotter() {
    }
    
    /**
     * Constructor to set the title of the chart explicitly.
     * 
     * @param title - the title of the chart
     */
    public Plotter(final String title) {
        this();
        this.title = title;
    }
    
    /**
     * Constructor to set the title, xLabel and yLabel of the chart explicitly.
     * 
     * @param title - the title of the chart
     * @param xLabel - the x-axis label
     * @param yLabel - the y-axis label
     */
    public Plotter(final String title, final String xLabel, final String yLabel) {
        this();
        this.title = title;
        this.xAxisLabel = xLabel;
        this.yAxisLabel = yLabel;
    }
    
    public void plot(final double[] xyData) {
        plot(xyData, xyData);
    }
    
    public void plot(final double[] xData, final double[] yData) {
        plot(xData, yData, null);
    }
    
    public void plot(final double[] xData, final double[] yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    public void plot(final double[] xData, final double[] yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (seriesList.size() + 1) : seriesLegend;
        seriesList.add(new Series(xData, yData, fmt, legend));
        this.chartStyle = ChartStyle.LineChart;      
    }
    
    public void plot(final int[] xyData) {
        plot(xyData, xyData);
    }
    
    public void plot(final int[] xData, final int[] yData) {
        plot(xData, yData, null);
    }
    
    public void plot(final int[] xData, final int[] yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    public void plot(final int[] xData, final int[] yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (seriesList.size() + 1) : seriesLegend;
        seriesList.add(new Series(xData, yData, fmt, legend));
        this.chartStyle = ChartStyle.LineChart;      
    }
    
    public void plot(final float[] xyData) {
        plot(xyData, xyData);
    }
    
    public void plot(final float[] xData, final float[] yData) {
        plot(xData, yData, null);
    }
    
    public void plot(final float[] xData, final float[] yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    public void plot(final float[] xData, final float[] yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (seriesList.size() + 1) : seriesLegend;
        seriesList.add(new Series(xData, yData, fmt, legend));
        this.chartStyle = ChartStyle.LineChart;      
    }
    
    public void plot(final List<? extends Number> xyData) {
        plot(xyData, xyData);
    }
    
    public void plot(final List<?> xData, final List<? extends Number> yData) {
        plot(xData, yData, null);
    }
    
    public void plot(final List<?> xData, final List<? extends Number> yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    public void plot(final List<?> xData, final List<? extends Number> yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (seriesList.size() + 1) : seriesLegend;
        seriesList.add(new Series(xData, yData, fmt, legend));
        this.chartStyle = ChartStyle.LineChart;      
    }
    
    /**
     * Set the title of the chart
     * 
     * @param title - the title 
     */
    public void title(final String title) {
        this.title = title;
    }
    
    /**
     * Set the x-axis label
     * 
     * @param xLabel - the x-axis label
     */
    public void xLabel(final String xLabel) {
        this.xAxisLabel = xLabel;
    }
    
    /**
     * Set the y-axis label
     * 
     * @param yLabel - the y-axis label
     */
    public void yLabel(final String yLabel) {
        this.yAxisLabel = yLabel;
    }
    
    /**
     * Set the width of the chart
     * 
     * @param width - the width of the chart
     */
    public void width(final int width) {
        this.width = width;
    }
    
    /**
     * Set the height of the chart
     * 
     * @param height - the height of the chart
     */
    public void height(final int height) {
        this.height = height;
    }
    
    /**
     * Save the chart to a file. The chart needs to be displayed on screen 
     * when this method is called. 
     * 
     * @param filename - the file name
     */
    public void savefig(final String filename) {
        try {
            ChartImage.saveImage(chart, filename);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public void hist(final double[] xData, final double[] yData, final String fmt, final String seriesLegend) {
        this.xData = xData;
        this.yData = yData;
        this.styleFormatStr = fmt;
        this.histLegend = (seriesLegend == null || seriesLegend.equals("")) ? " " : seriesLegend;
        this.chartStyle = ChartStyle.BarChart;       
    }
    
    private void initialiseChart() {
        if (chartStyle == ChartStyle.LineChart) {
            chart = new XYChartBuilder().width(width).height(height).theme(Styler.ChartTheme.Matlab).title(title).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();
            chart.getStyler().setXAxisTickMarkSpacingHint(100);
            
            int len = seriesList.size();
            for (int i = 0; i < len; i++) {
                Series s = seriesList.get(i);
                XYSeries series = s.addSeriesToChart(chart);

                if (series != null) {
                    StyleFormat fmt = StyleFormat.getStyleFormat(i, s.styleFormatStr);
                    series.setLineStyle(fmt.lineStyle);
                    series.setMarker(fmt.markerStyle);
                    series.setMarkerColor(fmt.colour);
                    series.setLineColor(fmt.colour);
                }
            }
            return;
        }
        
        if (chartStyle == ChartStyle.BarChart) {
            histChart = new CategoryChartBuilder().width(width).height(height).theme(Styler.ChartTheme.Matlab).title(title).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();
            histChart.getStyler().setHasAnnotations(true);
            CategorySeries series = histChart.addSeries(histLegend, xData, yData);
            
            StyleFormat fmt = StyleFormat.getStyleFormat(0, styleFormatStr);
            series.setFillColor(fmt.colour); 
            return;
        }
    }
    
    public void show() {
        initialiseChart();
        
        if (chart != null) {
            new XChartSwingWrapper<>(chart).displayChart();
        }
        
        if (histChart != null) {
            new XChartSwingWrapper<>(histChart).displayChart();
        }
    }
    
    enum ChartStyle {
        LineChart, BarChart
    }
    
    static class Series {
        public Object xData;
        public Object yData;
        public String styleFormatStr;
        public String legend;
        public SeriesType seriesType;
        
        enum SeriesType {
            Double, Float, Int, List, None
        }
        
        public Series(final String styleFormatStr, final String legend) {
            seriesType = SeriesType.None;
            this.styleFormatStr = styleFormatStr;
            this.legend = legend;
        }
        
        public Series(final double[] xData, final double[] yData, final String styleFormatStr, final String legend) {
            this(styleFormatStr, legend);
            seriesType = SeriesType.Double;
            this.xData = xData;
            this.yData = yData;           
        }
 
        public Series(final int[] xData, final int[] yData, final String styleFormatStr, final String legend) {
            this(styleFormatStr, legend);
            seriesType = SeriesType.Int;
            this.xData = xData;
            this.yData = yData;
        }
        
        public Series(final float[] xData, final float[] yData, final String styleFormatStr, final String legend) {
            this(styleFormatStr, legend);
            seriesType = SeriesType.Float;
            this.xData = xData;
            this.yData = yData;
        }
        
        public Series(final List<?> xData, final List<? extends Number> yData, final String styleFormatStr, final String legend) {
            this(styleFormatStr, legend);
            seriesType = SeriesType.List;
            this.xData = xData;
            this.yData = yData;
        }
        
        public XYSeries addSeriesToChart(final XYChart chart) {
            switch(seriesType) {
                case Double:
                    return chart.addSeries(legend, (double[]) xData, (double[]) yData);

                case Float:
                    return chart.addSeries(legend, (float[]) xData, (float[]) yData);
                    
                case Int:
                    return chart.addSeries(legend, (int[]) xData, (int[]) yData);
                    
                case List:
                    return chart.addSeries(legend, (List<?>) xData, (List<? extends Number>) yData);
            }
            
            return null;
        }
    }
}
