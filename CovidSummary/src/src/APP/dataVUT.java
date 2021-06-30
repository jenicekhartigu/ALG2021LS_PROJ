package src.APP;

import java.util.Date;

public class dataVUT implements Comparable<dataVUT> {
    private Date datum;
    private Double ag_testy;
    private int pcr_testy;
    public int umrti;
    private Double pocNakazenych;
    private Double pocVylecenych;

    /**
     * Třída pro uložení všech dat za daný den
     * 
     * @param datum         Datum záznamu
     * @param ag_testy      počet antigeních těstu za daný den
     * @param pcr_testy     počet PCR testu za daný den
     * @param umrti         počet umrtí za daný den
     * @param pocNakazenych počet nakažených za daný den
     * @param pocVylecenych počet vylíčených za daný ned
     */
    public dataVUT(Date datum, Double ag_testy, int pcr_testy, int umrti, Double pocNakazenych, Double pocVylecenych) {
        this.datum = datum;
        this.ag_testy = ag_testy;
        this.pcr_testy = pcr_testy;

        this.umrti = umrti;

        this.pocNakazenych = pocNakazenych;
        this.pocVylecenych = pocVylecenych;
    }

    /**
     * Metoda k získání data
     * 
     * @return (Date) datum
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * Metoda k získání počtu antigenách testu za daný den
     * 
     * @return <code>(Double)</code> počet antigenách testu
     */
    public Double getAg_testy() {
        return ag_testy;
    }

    /**
     * Metoda k získání počtu PCR testu za daný den
     * 
     * @return <code>(int)</code> počet PCR testu
     */
    public int getPcr_testy() {
        return pcr_testy;
    }

    /**
     * Metoda k získání počtu umrtí za daný den
     * 
     * @return <code>(int)</code> počet umrtí testu
     */
    public int getUmrti() {
        return umrti;
    }

    /**
     * Metoda k získání počtu nakažených za daný den
     * 
     * @return <code>(Double)</code> počet nakažených
     */
    public Double getPocNakazenych() {
        return pocNakazenych;
    }

    /**
     * Metoda k získání počtu vylečených za daný den
     * 
     * @return <code>(Double)</code> počet vylečených
     */
    public Double getPocVylecenych() {
        return pocVylecenych;
    }

    @Override
    public String toString() {
        return "dataVUT [ag_testy=" + ag_testy + ", datum=" + datum + ", pcr_testy=" + pcr_testy + ", pocNakazenych="
                + pocNakazenych + ", pocVylecenych=" + pocVylecenych + ", umrti=" + umrti + "]";
    }

    @Override
    public int compareTo(dataVUT o) {
        if (pcr_testy == o.pcr_testy)
            return 0;
        else if (pcr_testy > o.pcr_testy)
            return 1;
        else
            return -1;
    }

}
