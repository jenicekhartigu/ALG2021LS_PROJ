package src.APP;

import java.util.Date;

public class dataUmrti {
    private Date datum;
    private Double umrti;
    private Double kumUmrti;

    public dataUmrti(Date datum, Double umrti, Double kumUmrti) {
        this.datum = datum;
        this.umrti = umrti;
        this.kumUmrti = kumUmrti;
    }

    public Date getDatum() {
        return datum;
    }
    public Double getUmrti() {
        return umrti;
    }
    public Double getKumUmrti() {
        return kumUmrti;
    }

    @Override
    public String toString() {
        String metodaToString = "Datum: " + this.datum +  ", umrti: " + this.umrti + ", kumulativni umrti: " + this.kumUmrti;
        return metodaToString;
    }


    
}
