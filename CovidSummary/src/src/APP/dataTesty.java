package src.APP;

import java.util.Date;

public class dataTesty {
    private Date datum;
    private Double ag_testy;
    private Double pcr_testy;
    private Double kum_ag_testy;
    private Double kum_pcr_testy;

    public dataTesty(Date datum, Double ag_testy, Double pcr_testy, Double kum_ag_testy, Double kum_pcr_testy) {
        this.datum = datum;
        this.ag_testy = ag_testy;
        this.pcr_testy = pcr_testy;
        this.kum_ag_testy = kum_ag_testy;
        this.kum_pcr_testy = kum_pcr_testy;
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



    @Override
    public String toString() {
        return "dataTesty [ag_testy=" + ag_testy + ", datum=" + datum + ", kum_ag_testy=" + kum_ag_testy
                + ", kum_pcr_testy=" + kum_pcr_testy + ", pcr_testy=" + pcr_testy + "]";
    }
    
    

}
