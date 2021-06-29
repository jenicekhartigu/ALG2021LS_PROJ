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
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;

public class DrawChart {
    static long millis=System.currentTimeMillis();  
    static java.sql.Date date=new java.sql.Date(millis);

    public static void main(String[] args) throws IOException, ParseException {
        //parseVUT(API.fetchApi().toString());
        vykresliOckovani(0);

    }
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
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDatePattern("y-M-d");

        chart.addSeries("Počet mrtvých za " + popisek, datum, yData);

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
        final XYChart chart = new XYChartBuilder().title("Covid Data").xAxisTitle("Dny").yAxisTitle("Testy").theme(ChartTheme.XChart).build();
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
        final XYChart chart = new XYChartBuilder().title("Covid Data").xAxisTitle("Dny").yAxisTitle("Nakazeni / vyleceni").theme(ChartTheme.Matlab).build();
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

        if (dny == 0) {
            for (dataOckovani i : data) {
                datum.add(i.getDatum());
                prvniDavka.add(i.getOckovani1D());
                druhaDavka.add(i.getOckovani2D());
                pfizer1.add(i.getpocetPfize1D());
                pfizer2.add(i.getpocetPfize2D());
                astra1.add(i.getpocetAstra1D());
                astra2.add(i.getpocetAstra2D());
                moderna1.add(i.getpocetModer1D());
                moderna2.add(i.getpocetModer2D());
                jansen.add(i.getpocetJanse1D());

                prvniDavkyCelkem += i.getOckovani1D();
                druheDavkyCelkem += i.getOckovani2D();
                pfizer1Celkem += i.getpocetPfize1D();
                pfizer2Celkem += i.getpocetPfize2D();
                astra1Celkem += i.getpocetAstra1D();
                astra2Celkem += i.getpocetAstra2D();
                moderna1Celkem += i.getpocetModer1D();
                moderna2Celkem += i.getpocetModer2D();
                jansenCelkem += i.getpocetJanse1D();
                
            }
        } else {
            for (int i = 1; i < dny+1; i++) {
                datum.add(data.get(data.size() - i).getDatum());
                prvniDavka.add(data.get(data.size() - i).getOckovani1D());
                druhaDavka.add(data.get(data.size() - i).getOckovani2D());
                prvniDavkyCelkem += data.get(data.size() - i).getOckovani2D();
                druheDavkyCelkem += data.get(data.size() - i).getOckovani2D();
            }
            Collections.reverse(datum);
            Collections.reverse(prvniDavka);
            Collections.reverse(druhaDavka);
            
        }
        
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
