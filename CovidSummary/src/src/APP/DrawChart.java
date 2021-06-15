package src.APP;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

public class DrawChart {

    public static void vykresliUmrti(int dny) throws IOException, ParseException {
        List<dataUmrti> data = App.parseUmrti(API.fetchApi().toString());
        List<Date> xData = new ArrayList<Date>();
        List<Double> yData = new ArrayList<Double>();
        double mrtvoly = 0;
        String popisek = "";
        if (dny == 0) {
            for (dataUmrti i : data) {
                xData.add(i.getDatum());
                yData.add(i.getUmrti());
                mrtvoly += i.getUmrti();
                
            }
            popisek = "celou pandemii: " + mrtvoly;
        } else {
        
            for (int i = 1; i < dny+1; i++) {
                //System.out.println(i + ": " + data.get(data.size() - i).toString());
                xData.add(data.get(data.size() - i).getDatum());
                yData.add(data.get(data.size() - i).getUmrti());
                mrtvoly += data.get(data.size() - i).getUmrti();

            }
            Collections.reverse(xData);
            Collections.reverse(yData);
            popisek = "posledních " + dny + " dní: " + mrtvoly;
            
        }
        final CategoryChart chart = new CategoryChartBuilder().title("Covid Data").xAxisTitle("Dny").yAxisTitle("Umrti").build();
        //chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDatePattern("y-M-d");

        chart.addSeries("Počet mrtvých za " + popisek, xData, yData);

        // Show it
        new SwingWrapper<>(chart).displayChart();

    }
    public static void vykresliTesty(int dny) throws IOException, ParseException {
        List<dataTesty> data = App.parseTesty(API.fetchApi().toString());
        List<Date> xData = new ArrayList<Date>();
        List<Double> pcrData = new ArrayList<Double>();
        List<Double> agData = new ArrayList<Double>();

        if (dny == 0) {
            for (dataTesty i : data) {
                xData.add(i.getDatum());
                pcrData.add(i.getPcr_testy());
                agData.add(i.getAg_testy());
                
            }
        } else {
        
            for (int i = 1; i < dny+1; i++) {
                //System.out.println(i + ": " + data.get(data.size() - i).toString());
                xData.add(data.get(data.size() - i).getDatum());
                agData.add(data.get(data.size() - i).getAg_testy());
                pcrData.add(data.get(data.size() - i).getPcr_testy());


            }
            Collections.reverse(xData);
            Collections.reverse(agData);
            Collections.reverse(pcrData);
            
        }
        final XYChart chart = new XYChartBuilder().title("Covid Data").xAxisTitle("Dny").yAxisTitle("Testy").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDatePattern("y-M-d");
        chart.getStyler().setDecimalPattern("#0.0");

        chart.addSeries("Počet antigeních testu", xData, agData);
        chart.addSeries("Počet PCR testu", xData, pcrData);

        // Show it
        new SwingWrapper<>(chart).displayChart();

        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        System.out.println(date);

        BitmapEncoder.saveBitmap(chart, "./Testy"+dny+"nazpet"+date+"", BitmapFormat.PNG);

    }

    
}
