package src.APP;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

public class DrawChart {

    public static void VykresliGraf() throws IOException, ParseException {
        List<dataDny> data = App.parse(API.fetchApiHosp().toString());
        List<Date> xData = new ArrayList<Date>();
        List<Double> yData = new ArrayList<Double>();
        /*List<Double> yData2 = new ArrayList<Double>();
        
        for (dataDny i : data) {
            xData.add(i.getDatum());
            yData.add(i.getUmrti());
            yData2.add(i.getKumUmrti());
            
        }
        */
        double mrtvoly = 0;
        for (int i = 1; i < 11; i++) {
            //System.out.println(i + ": " + data.get(data.size() - i).toString());
            xData.add(data.get(data.size() - i).getDatum());
            yData.add(data.get(data.size() - i).getUmrti());
            mrtvoly += data.get(data.size() - i).getUmrti();
        }
        Collections.reverse(xData);
        
        final XYChart chart = new XYChartBuilder().title("Covid Data").xAxisTitle("Dny").yAxisTitle("Umrti").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
        chart.getStyler().setDatePattern("y-M-d");

        chart.addSeries("Mrtvých za posledních 10 dní: " + mrtvoly, xData, yData);

        // Show it
        new SwingWrapper<>(chart).displayChart();

    }

    
}
