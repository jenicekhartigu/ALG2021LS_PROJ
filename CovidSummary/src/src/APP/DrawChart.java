package src.APP;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.internal.chartpart.Annotation;
import org.knowm.xchart.style.PieStyler.AnnotationType;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;

public class DrawChart {
    static long millis=System.currentTimeMillis();  
    static java.sql.Date date=new java.sql.Date(millis);

    public static void vykresliUmrti(int dny) throws IOException, ParseException {
        List<dataVUT> data = App.parseVUT(API.fetchApi().toString());
        List<Date> datum = new ArrayList<Date>();
        List<Double> yData = new ArrayList<Double>();
        double mrtvoly = 0;
        String popisek = "";
        if (dny == 0) {
            for (dataVUT i : data) {
                datum.add(i.getDatum());
                yData.add(i.getUmrti());
                mrtvoly += i.getUmrti();
                
            }
            popisek = "celou pandemii: " + mrtvoly;
        } else {
        
            for (int i = 1; i < dny+1; i++) {
                //System.out.println(i + ": " + data.get(data.size() - i).toString());
                datum.add(data.get(data.size() - i).getDatum());
                yData.add(data.get(data.size() - i).getUmrti());
                mrtvoly += data.get(data.size() - i).getUmrti();

            }
            Collections.reverse(datum);
            Collections.reverse(yData);
            popisek = "posledních " + dny + " dní: " + mrtvoly;
            
        }
        final XYChart chart = new XYChartBuilder().title("Covid Data").xAxisTitle("Dny").yAxisTitle("Umrti").theme(ChartTheme.GGPlot2).build();
        //chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDatePattern("y-M-d");

        chart.addSeries("Počet mrtvých za " + popisek, datum, yData);

        // Show it
        //new SwingWrapper<>(chart).displayChart();
        if(dny == 0) {
            BitmapEncoder.saveBitmap(chart, "./umrti_k_"+date+"", BitmapFormat.PNG);
        } else {
            BitmapEncoder.saveBitmap(chart, "./umrti_"+dny+"dni_nazpet_k_"+date+"", BitmapFormat.PNG);
        }

    }
    public static void vykresliTesty(int dny) throws IOException, ParseException {
        List<dataVUT> data = App.parseVUT(API.fetchApi().toString());
        List<Date> datum = new ArrayList<Date>();
        List<Double> pcrData = new ArrayList<Double>();
        List<Double> agData = new ArrayList<Double>();

        if (dny == 0) {
            for (dataVUT i : data) {
                datum.add(i.getDatum());
                pcrData.add(i.getPcr_testy());
                agData.add(i.getAg_testy());
                
            }
        } else {
        
            for (int i = 1; i < dny+1; i++) {
                //System.out.println(i + ": " + data.get(data.size() - i).toString());
                datum.add(data.get(data.size() - i).getDatum());
                agData.add(data.get(data.size() - i).getAg_testy());
                pcrData.add(data.get(data.size() - i).getPcr_testy());


            }
            Collections.reverse(datum);
            Collections.reverse(agData);
            Collections.reverse(pcrData);
            
        }
        final XYChart chart = new XYChartBuilder().title("Covid Data").xAxisTitle("Dny").yAxisTitle("Testy").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDatePattern("y-M-d");
        chart.getStyler().setDecimalPattern("#0");

        chart.addSeries("Počet antigeních testu", datum, agData);
        chart.addSeries("Počet PCR testu", datum, pcrData);

        // Show it
        //new SwingWrapper<>(chart).displayChart();


        if(dny == 0) {
            BitmapEncoder.saveBitmap(chart, "./provedene_testy_k_"+date+"", BitmapFormat.PNG);
        } else {
            BitmapEncoder.saveBitmap(chart, "./testy_"+dny+"dni_nazpet_k_"+date+"", BitmapFormat.PNG);
        }
    }
    public static void vykresliNakazene(int dny) throws IOException, ParseException {
        List<dataVUT> data = App.parseVUT(API.fetchApi().toString());
        List<Date> datum = new ArrayList<Date>();
        List<Double> nakazeni = new ArrayList<Double>();
        List<Double> vyleceni = new ArrayList<Double>();

        if (dny == 0) {
            for (dataVUT i : data) {
                datum.add(i.getDatum());
                nakazeni.add(i.getPocNakazenych());
                vyleceni.add(i.getPocVylecenych());
                
            }
        } else {
        
            for (int i = 1; i < dny+1; i++) {
                //System.out.println(i + ": " + data.get(data.size() - i).toString());
                datum.add(data.get(data.size() - i).getDatum());
                nakazeni.add(data.get(data.size() - i).getPocNakazenych());
                vyleceni.add(data.get(data.size() - i).getPocVylecenych());


            }
            Collections.reverse(datum);
            Collections.reverse(nakazeni);
            Collections.reverse(vyleceni);
            
        }
        final XYChart chart = new XYChartBuilder().title("Covid Data").xAxisTitle("Dny").yAxisTitle("Nakazeni / vyleceni").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDatePattern("y-M-d");
        chart.getStyler().setDecimalPattern("#0");

        chart.addSeries("Počet nakaženych", datum, nakazeni);
        chart.addSeries("Počet vylečených", datum, vyleceni);

        // Show it
        //new SwingWrapper<>(chart).displayChart();


        if(dny == 0) {
            BitmapEncoder.saveBitmap(chart, "./nakazeni_vyleceni_k_"+date+"", BitmapFormat.PNG);
        } else {
            BitmapEncoder.saveBitmap(chart, "./nakazeni_vyleceni_"+dny+"dni_nazpet_k_"+date+"", BitmapFormat.PNG);
        }
    }
    public static void vykresliOckovani(int dny) throws IOException, ParseException {
        List<dataOckovani> data = App.parseOckovani(API.fetchApiOckovani().toString());
        List<Date> datum = new ArrayList<Date>();
        List<Double> prvniDavka = new ArrayList<Double>();
        List<Double> druhaDavka = new ArrayList<Double>();
        List<Double> pfizer1 = new ArrayList<Double>();
        List<Double> pfizer2 = new ArrayList<Double>();
        List<Double> astra1 = new ArrayList<Double>();
        List<Double> astra2 = new ArrayList<Double>();
        List<Double> moderna1 = new ArrayList<Double>();
        List<Double> moderna2 = new ArrayList<Double>();
        List<Double> jansen = new ArrayList<Double>();
        double pfizer1Celkem = 0;
        double pfizer2Celkem = 0;
        double astra1Celkem = 0;
        double astra2Celkem = 0;
        double moderna1Celkem = 0;
        double moderna2Celkem = 0;
        double jansenCelkem = 0;

        double prvniDavkyCelkem = 0;
        double druheDavkyCelkem = 0;
        //double celkemNaockovano = 0;
        if (dny == 0) {
            for (dataOckovani i : data) {
                datum.add(i.getDatum());
                prvniDavka.add(i.getOckovaniCelkem1());
                druhaDavka.add(i.getOckovaniCelkem2());
                pfizer1.add(i.getPfizer1());
                pfizer2.add(i.getPfizer2());
                astra1.add(i.getAstra1());
                astra2.add(i.getAstra2());
                moderna1.add(i.getModerna1());
                moderna2.add(i.getModerna2());
                jansen.add(i.getJansen());
                prvniDavkyCelkem += i.getOckovaniCelkem1();
                druheDavkyCelkem += i.getOckovaniCelkem2();
                pfizer1Celkem += i.getPfizer1();
                pfizer2Celkem += i.getPfizer2();
                astra1Celkem += i.getAstra1();
                astra2Celkem += i.getAstra2();
                moderna1Celkem += i.getModerna1();
                moderna2Celkem += i.getModerna2();
                jansenCelkem += i.getJansen();
                
            }
        } else {
        
            for (int i = 1; i < dny+1; i++) {
                //System.out.println(i + ": " + data.get(data.size() - i).toString());
                datum.add(data.get(data.size() - i).getDatum());
                prvniDavka.add(data.get(data.size() - i).getOckovaniCelkem1());
                druhaDavka.add(data.get(data.size() - i).getOckovaniCelkem2());
                prvniDavkyCelkem += data.get(data.size() - i).getOckovaniCelkem1();
                druheDavkyCelkem += data.get(data.size() - i).getOckovaniCelkem2();


            }
            Collections.reverse(datum);
            Collections.reverse(prvniDavka);
            Collections.reverse(druhaDavka);
            
        }
        /*
        //celkemNaockovano = prvniDavkyCelkem + druheDavkyCelkem;
        final PieChart chart = new PieChartBuilder().title("Covid data - Data o očkovani").theme(ChartTheme.GGPlot2).build();
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.addSeries("Pfizer: " + pfizerCelkem, pfizerCelkem);
        chart.addSeries("AstraZeneca: " + astraCelkem, astraCelkem);
        chart.addSeries("Moderna: " + modernaCelkem, modernaCelkem);
        chart.addSeries("Janssen: " + jansenCelkem, jansenCelkem);
        */
        if(dny==0) { 
        final CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("CovidSummary - Data o očkovani").xAxisTitle("Vakcíny").yAxisTitle("Počet dávek").theme(ChartTheme.GGPlot2).build();
 
        // Customize Chart
        chart.getStyler().setDecimalPattern("#");

        // Series
        chart.addSeries("Prvni davky\n"+prvniDavkyCelkem , new ArrayList<String>(Arrays.asList(new String[] { "Pfizer", "AstraZeneca", "Moderna", "Janssen" })), new ArrayList<Number>(Arrays.asList(new Number[] { pfizer1Celkem, astra1Celkem, moderna1Celkem, 0 })));
        chart.addSeries("Druhe davky\n"+druheDavkyCelkem , new ArrayList<String>(Arrays.asList(new String[] { "Pfizer", "AstreZeneca", "Moderna", "Janssen" })), new ArrayList<Number>(Arrays.asList(new Number[] { pfizer2Celkem, astra2Celkem, moderna2Celkem, jansenCelkem })));
        //Ulozeni
        BitmapEncoder.saveBitmap(chart, "./ockovani_k_"+date+"", BitmapFormat.PNG);
        }
    }
    
}
