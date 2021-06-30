package APP;

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
    static long millis = System.currentTimeMillis();
    static java.sql.Date date = new java.sql.Date(millis);

    private List<Date> datum = new ArrayList<>();
    private List<Integer> umrti = new ArrayList<>();
    private List<Integer> pcrData = new ArrayList<>();
    private List<Double> agData = new ArrayList<>();
    private List<Double> nakazeni = new ArrayList<>();
    private List<Double> vyleceni = new ArrayList<>();
    private List<dataVUT> data;
    private int dny;
    private int umrtiCelkem = 0;

    /**
     * Konstruktor s jedním vstupem pro načtení celkových dat
     * 
     * @param celaData <code>List<dataVUT></code> JSON data
     */
    public Vykresleni(List<dataVUT> celaData) {
        this.data = celaData;
        ziskejDataCela();
        this.dny = 0;

    }

    /**
     * Konstruktor s dvěma vstupy pro načtení celkových dat
     * 
     * @param celaData <code>List<dataVUT></code> JSON data
     * @param dny      <code>(int)</code> počet dní nazpátek
     */
    public Vykresleni(List<dataVUT> celaData, int dny) {
        this.data = celaData;
        ziskejDataDny(dny);
        this.dny = dny;

    }

    /**
     * Načetení dat do Listů
     */
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

    List<dataVUT> zaPosledniDny = new ArrayList<>();

    /**
     * Načtení dat do Listů s omezením na počet dnů
     * 
     * @param dny <code>(int)</code> počet dní nazpátek
     */
    private void ziskejDataDny(int dny) {
        for (int i = 1; i < dny + 1; i++) {
            datum.add(data.get(data.size() - i).getDatum());
            umrti.add(data.get(data.size() - i).getUmrti());
            agData.add(data.get(data.size() - i).getAg_testy());
            pcrData.add(data.get(data.size() - i).getPcr_testy());
            nakazeni.add(data.get(data.size() - i).getPocNakazenych());
            vyleceni.add(data.get(data.size() - i).getPocVylecenych());
            umrtiCelkem += data.get(data.size() - i).getUmrti();
            zaPosledniDny.add(new dataVUT(data.get(data.size() - i).getDatum(), data.get(data.size() - i).getAg_testy(),
                    data.get(data.size() - i).getPcr_testy(), data.get(data.size() - i).getUmrti(),
                    data.get(data.size() - i).getPocNakazenych(), data.get(data.size() - i).getPocVylecenych()));

        }
        Collections.reverse(datum);
        Collections.reverse(umrti);
        Collections.reverse(agData);
        Collections.reverse(pcrData);
        Collections.reverse(nakazeni);
        Collections.reverse(vyleceni);
    }

    private XYChart chart = new XYChartBuilder().xAxisTitle("Dny").theme(ChartTheme.Matlab).build();

    /**
     * Detailní dostylovaní grafu
     */
    private void ChartDetails() {
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDatePattern("y-M-d");
        chart.getStyler().setDecimalPattern("#0");
    }

    /**
     * Vykreslení a uložení grafů o umrtí
     * 
     * @throws IOException
     */
    public void VykresliUmrti() throws IOException {
        chart.setTitle("Covid Data - umrti");
        ChartDetails();
        chart.setYAxisTitle("Umrti");
        String popisek = "celou pandemii: " + umrtiCelkem;
        if (dny != 0) {
            popisek = "posledních " + dny + " dní: " + umrtiCelkem;
        }
        chart.addSeries("Počet mrtvých za " + popisek, datum, umrti);

        if (dny == 0) {
            BitmapEncoder.saveBitmap(chart, "./umrti_k_" + date, BitmapFormat.PNG);
            System.out.println("Nejvice mrtvých za celou epidemii bylo " + getMaxComparator(data) + " mrtvých");

        } else {
            BitmapEncoder.saveBitmap(chart, "./umrti_" + dny + "dni_nazpet_k_" + date, BitmapFormat.PNG);
            System.out.println("Nejvice mrtvých za posledních " + dny + "dní bylo " + getMaxComparator(zaPosledniDny)
                    + " mrtvých");
        }

    }

    /**
     * Vykreslení a uložení grafů o testech
     * 
     * @throws IOException
     */
    public void VykresliTesty() throws IOException {
        chart.setTitle("Covid Data - testy");
        chart.setYAxisTitle("Testy");
        ChartDetails();
        chart.addSeries("Počet antigeních testu", datum, agData);
        chart.addSeries("Počet PCR testu", datum, pcrData);

        if (dny == 0) {
            BitmapEncoder.saveBitmap(chart, "./provedene_testy_k_" + date + "", BitmapFormat.PNG);
            System.out.println("Nejvice PCR Testu za celou epidemii bylo " + getMaxComparable(data) + " testu");
        } else {
            BitmapEncoder.saveBitmap(chart, "./testy_" + dny + "dni_nazpet_k_" + date + "", BitmapFormat.PNG);
            System.out.println("Nejvice PCR Testu za posledních " + dny + "dní bylo " + getMaxComparable(zaPosledniDny)
                    + " testu");
        }
    }

    /**
     * Vykreslení a uložení grafů o nakažených a vylečených
     * 
     * @throws IOException
     */
    public void VykresliNakazene() throws IOException {
        chart.setTitle("Covid Data - nakazeni/vyleceni");
        chart.setYAxisTitle("Nakazeni/Vyleceni");
        ChartDetails();
        chart.addSeries("Počet nakaženych", datum, nakazeni);
        chart.addSeries("Počet vylečených", datum, vyleceni);
        if (dny == 0) {
            BitmapEncoder.saveBitmap(chart, "./nakazeni_vyleceni_k_" + date + "", BitmapFormat.PNG);
        } else {
            BitmapEncoder.saveBitmap(chart, "./nakazeni_vyleceni_" + dny + "dni_nazpet_k_" + date + "",
                    BitmapFormat.PNG);
        }
    }

    /**
     * COMPARATOR Metoda pro vypsání maximální naměřené hodnoty v zadaném
     * Listu<dataVUT>
     * 
     * @param data2 vstupní list
     * @return <code>String</code> Datum a počet nejvíce umrtí
     */
    public String getMaxComparator(List<dataVUT> data2) {
        Collections.sort(data2, new UmrtiComparator());
        return "" + data2.get(data2.size() - 1).getDatum() + " " + data2.get(data2.size() - 1).getUmrti() + "";
    }

    /**
     * COMPARABLE Metoda pro vypsání maximální naměřené hodnoty v zadaném
     * Listu<dataVUT>
     * 
     * @param data2 vstupní list
     * @return <code>String</code> Datum a počet nejvíce PCR testu
     */
    public String getMaxComparable(List<dataVUT> data2) {
        Collections.sort(data2);
        return "" + data2.get(data2.size() - 1).getDatum() + " " + data2.get(data2.size() - 1).getPcr_testy() + "";
    }

}
