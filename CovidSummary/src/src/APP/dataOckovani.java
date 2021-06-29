package src.APP;

import java.util.Date;

public class dataOckovani {
    private Date datum; 
    private Double pocetAstra1D;
    private Double pocetAstra2D;
    private Double pocetPfize1D;
    private Double pocetPfize2D;
    private Double pocetModer1D;
    private Double pocetModer2D;
    private Double pocetJanse1D;

    public dataOckovani(Date datum, 
                        Double pocetAstra1D, 
                        Double pocetAstra2D, 
                        Double pocetPfize1D, 
                        Double pocetPfize2D, 
                        Double pocetModer1D,
                        Double pocetModer2D, 
                        Double pocetJanse1D) {
        this.datum = datum;
        this.pocetAstra1D = pocetAstra1D;
        this.pocetAstra2D = pocetAstra2D;
        this.pocetPfize1D = pocetPfize1D;
        this.pocetPfize2D = pocetPfize2D;
        this.pocetModer1D = pocetModer1D;
        this.pocetModer2D = pocetModer2D;
        this.pocetJanse1D = pocetJanse1D;
    }

    public Date getDatum() {
        return datum;
    }

    public Double getpocetAstra1D() {
        return pocetAstra1D;
    }
    public Double getpocetAstra2D() {
        return pocetAstra2D;
    }

    public Double getpocetPfize1D() {
        return pocetPfize1D;
    }
    public Double getpocetPfize2D() {
        return pocetPfize2D;
    }

    public Double getpocetModer1D() {
        return pocetModer1D;
    }
    public Double getpocetModer2D() {
        return pocetModer2D;
    }

    public Double getpocetJanse1D() {
        return pocetJanse1D;
    }

    public Double getOckovani1D() {
        double ockovaniCelkem1 = pocetAstra1D + pocetPfize1D + pocetModer1D;
        return ockovaniCelkem1;
    }
    public Double getOckovani2D() {
        double ockovaniCelkem2 = pocetAstra2D + pocetPfize2D + pocetModer2D + pocetJanse1D;
        return ockovaniCelkem2;
    }

    public String toStringCelkem() {
        double astraCelkem = pocetAstra1D + pocetAstra2D;
        double pfizerCelkem = pocetPfize1D + pocetPfize2D;
        double modernaCelkem = pocetModer1D + pocetModer2D;

        return "Ockovani celkem\n Astra Zeneca: " + astraCelkem + "\n Pfizer: " + pfizerCelkem + "\n Moderna: " + modernaCelkem + "\n pocetJanse1D: " + pocetJanse1D +"\n";
    }
    @Override
    public String toString() {
        return "\ndataOckovani " + datum + "\npocetJanse1D=" + pocetJanse1D + "\npocetAstra1D=" + pocetAstra1D + "\npocetAstra2D=" + pocetAstra2D + "\npocetModer1D=" + pocetModer1D + "\npocetModer2D=" + pocetModer2D + "\npocetPfize1D=" + pocetPfize1D + "\npocetPfize2D=" + pocetPfize2D
                + "\n";
    }

    

    
    

    

}
