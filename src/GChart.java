import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

public class GChart extends ChartPanel {
    private XYSeriesCollection dataset;
    private XYSeries series[];
    private JFreeChart chart;

    public void setLabel(int serie, String label) {
        series[serie].setKey(label);
    }

    public void setData(int serie, Number[] x, Number[] y) {
        for(int i=0; i<x.length; i++) series[serie].add(x[i], y[i]);
    }

    public void addData(int serie, Number x, Number y) {
        series[serie].add(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mods = e.getModifiers();
        int panMask = MouseEvent.BUTTON1_MASK;
        if (mods == panMask+MouseEvent.SHIFT_MASK) panMask = 255;
        try {
            Field mask = ChartPanel.class.getDeclaredField("panMask");
            mask.setAccessible(true);
            mask.set(this, panMask);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        super.mousePressed(e);
    }

    public GChart(String title, int series) {
        super(null);
        dataset = new XYSeriesCollection();
        chart = ChartFactory.createXYLineChart(title, "", "", dataset, PlotOrientation.VERTICAL, true, true, false);
        setChart(chart);
        this.series = new XYSeries[series];
        for(int i=0; i<series; i++) {
            this.series[i] = new XYSeries(""+i);
            dataset.addSeries(this.series[i]);
        }

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeZeroBaselineVisible(true);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangePannable(true);
        plot.setDomainPannable(true);
        setMouseWheelEnabled(true);
        setMouseZoomable(true);
    }
}
