package src.APP;

import java.util.Date;

public class dataVUT {
    private Date datum;
    private Double ag_testy;
    private Double pcr_testy;
    private Double kum_ag_testy;
    private Double kum_pcr_testy;
    private Double umrti;
    private Double kumUmrti;
    private Double pocNakazenych;
    private Double pocVylecenych;
    

    public dataVUT(Date datum, Double ag_testy, Double pcr_testy, Double kum_ag_testy, Double kum_pcr_testy,
            Double umrti, Double kumUmrti, Double pocNakazenych, Double pocVylecenych) {
        this.datum = datum;
        this.ag_testy = ag_testy;
        this.pcr_testy = pcr_testy;
        this.kum_ag_testy = kum_ag_testy;
        this.kum_pcr_testy = kum_pcr_testy;
        this.umrti = umrti;
        this.kumUmrti = kumUmrti;
        this.pocNakazenych = pocNakazenych;
        this.pocVylecenych = pocVylecenych;
    }

    public Date getDatum() {
        return datum;
    }

    public Double getAg_testy() {
        return ag_testy;
    }

    public Double getPcr_testy() {
        return pcr_testy;
    }

    public Double getKum_ag_testy() {
        return kum_ag_testy;
    }

    public Double getKum_pcr_testy() {
        return kum_pcr_testy;
    }
    public Double getUmrti() {
        return umrti;
    }
    public Double getKumUmrti() {
        return kumUmrti;
    }
    public Double getPocNakazenych() {
        return pocNakazenych;
    }
    public Double getPocVylecenych() {
        return pocVylecenych;
    }

    
    

}
