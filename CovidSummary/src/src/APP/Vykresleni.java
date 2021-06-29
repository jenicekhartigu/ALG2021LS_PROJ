package src.APP;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Vykresleni {
    static long millis=System.currentTimeMillis();  
    static java.sql.Date date=new java.sql.Date(millis);

    private List<Date> datum = new ArrayList<>();
    private List<Double> umrti = new ArrayList<>();
    private List<Double> pcrData = new ArrayList<>();
    private List<Double> agData = new ArrayList<>();
    private List<Double> nakazeni = new ArrayList<>();
    private List<Double> vyleceni = new ArrayList<>();
    private List<dataVUT> data;
    private int dny;
    private int umrtiCelkem = 0;

    public Vykresleni(List<dataVUT> celaData) {
        this.data = celaData;
        ziskejDataCela();
        this.dny = 0;

    }
    public Vykresleni(List<dataVUT> celaData, int dny) {
        this.data = celaData;
        ziskejDataDny(dny);
        this.dny = dny;

    }
    private void ziskejDataCela() {
        for (dataVUT i : data) {
            datum.add(i.getDatum());
            umrti.add(i.getUmrti());
            pcrData.add(i.getPcr_testy());
            agData.add(i.getAg_testy());
            nakazeni.add(i.getPocNakazenych());
            vyleceni.add(i.getPocVylecenych());
            umrtiCelkem += i.getUmrti();
        }
    }
    private void ziskejDataDny(int dny) {
        for (int i = 1; i < dny+1; i++) {
            datum.add(data.get(data.size() - i).getDatum());
            umrti.add(data.get(data.size() - i).getUmrti());
            agData.add(data.get(data.size() - i).getAg_testy());
            pcrData.add(data.get(data.size() - i).getPcr_testy());
            nakazeni.add(data.get(data.size() - i).getPocNakazenych());
            vyleceni.add(data.get(data.size() - i).getPocVylecenych());
            umrtiCelkem += data.get(data.size() - i).getUmrti();

        }
        Collections.reverse(datum);
        Collections.reverse(umrti);
        Collections.reverse(agData);
        Collections.reverse(pcrData);
        Collections.reverse(nakazeni);
        Collections.reverse(vyleceni);
    }
    public XYChart chart = new XYChartBuilder().xAxisTitle("Dny").theme(ChartTheme.Matlab).build();

    public void ChartDetails(){
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDatePattern("y-M-d");
        chart.getStyler().setDecimalPattern("#0");
    }

    public void VykresliUmrti() throws IOException {
        chart.setTitle("Covid Data - umrti");
        ChartDetails();
        chart.setYAxisTitle("Umrti");
        String popisek = "celou pandemii: " + umrtiCelkem;
        if(dny != 0) {
            popisek = "posledních " + dny + " dní: " + umrtiCelkem;
        }
        chart.addSeries("Počet mrtvých za "+ popisek, datum, umrti);
        if(dny == 0) {
            BitmapEncoder.saveBitmap(chart, "./umrti_k_"+date, BitmapFormat.PNG);
            } else {
            BitmapEncoder.saveBitmap(chart, "./umrti_"+dny+"dni_nazpet_k_", BitmapFormat.PNG);
        }
        
    }
    public void VykresliTesty() throws IOException {
        chart.setTitle("Covid Data - testy");
        chart.setYAxisTitle("Testy");
        ChartDetails();
        chart.addSeries("Počet antigeních testu", datum, agData);
        chart.addSeries("Počet PCR testu", datum, pcrData);
        if(dny == 0) {
            BitmapEncoder.saveBitmap(chart, "./provedene_testy_k_"+date+"", BitmapFormat.PNG);
        } else {
            BitmapEncoder.saveBitmap(chart, "./testy_"+dny+"dni_nazpet_k_"+date+"", BitmapFormat.PNG);
        }
    }
    public void VykresliNakazene() throws IOException {
        chart.setTitle("Covid Data - nakazeni/vyleceni");
        chart.setYAxisTitle("Nakazeni/Vyleceni");
        ChartDetails();
        chart.addSeries("Počet nakaženych", datum, nakazeni);
        chart.addSeries("Počet vylečených", datum, vyleceni);
        if(dny == 0) {
            BitmapEncoder.saveBitmap(chart, "./nakazeni_vyleceni_k_"+date+"", BitmapFormat.PNG);
        } else {
            BitmapEncoder.saveBitmap(chart, "./nakazeni_vyleceni_"+dny+"dni_nazpet_k_"+date+"", BitmapFormat.PNG);
        }
    }
    
}
