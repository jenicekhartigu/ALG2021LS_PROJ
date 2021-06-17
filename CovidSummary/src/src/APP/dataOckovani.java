package src.APP;

import java.util.Date;

public class dataOckovani {
    private Date datum; 
    private Double astra1;
    private Double astra2;
    private Double pfizer1;
    private Double pfizer2;
    private Double moderna1;
    private Double moderna2;
    private Double jansen;

    public dataOckovani(Date datum, Double astra1, Double astra2, Double pfizer1, Double pfizer2, Double moderna1,
            Double moderna2, Double jansen) {
        this.datum = datum;
        this.astra1 = astra1;
        this.astra2 = astra2;
        this.pfizer1 = pfizer1;
        this.pfizer2 = pfizer2;
        this.moderna1 = moderna1;
        this.moderna2 = moderna2;
        this.jansen = jansen;
    }

    public Date getDatum() {
        return datum;
    }

    public Double getAstra1() {
        return astra1;
    }
    public Double getAstra2() {
        return astra2;
    }

    public Double getPfizer1() {
        return pfizer1;
    }
    public Double getPfizer2() {
        return pfizer2;
    }

    public Double getModerna1() {
        return moderna1;
    }
    public Double getModerna2() {
        return moderna2;
    }

    public Double getJansen() {
        return jansen;
    }
    public Double getOckovaniCelkem1() {
        double ockovaniCelkem1 = astra1 + pfizer1 + moderna1;
        return ockovaniCelkem1;
    }
    public Double getOckovaniCelkem2() {
        double ockovaniCelkem2 = astra2 + pfizer2 + moderna2 + jansen;
        return ockovaniCelkem2;
    }

    public String toStringCelkem() {
        double astraCelkem = astra1 + astra2;
        double pfizerCelkem = pfizer1 + pfizer2;
        double modernaCelkem = moderna1 + moderna2;

        return "Ockovani celkem\n Astra Zeneca: " + astraCelkem + "\n Pfizer: " + pfizerCelkem + "\n Moderna: " + modernaCelkem + "\n Jansen: " + jansen +"\n";
    }
    @Override
    public String toString() {
        /*double astraCelkem = astra1 + astra2;
        double pfizerCelkem = pfizer1 + pfizer2;
        double modernaCelkem = moderna1 + moderna2;
        
        return "Ockovani celkem\n Astra Zeneca: " + astraCelkem + "\n Pfizer: " + pfizerCelkem + "\n Moderna: " + modernaCelkem + "\n Jansen: " + jansen +"\n";
        */
        return "\ndataOckovani " + datum + "\njansen=" + jansen + "\nastra1=" + astra1 + "\nastra2=" + astra2 + "\nmoderna1=" + moderna1 + "\nmoderna2=" + moderna2 + "\npfizer1=" + pfizer1 + "\npfizer2=" + pfizer2
                + "\n";
    }

    

    
    

    

}
