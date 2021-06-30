package APP;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseData {

    static DateFormat format = new SimpleDateFormat("y-M-d");

    
    /** 
     * Testovací metoda pro vypsání spravně nafetchovanych dat
     * @param args
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {
        // parseVUT(API.fetchApi().toString());
        // System.out.println(parseOckovani(API.fetchApiOckovani().toString()));
    }

    
    /** 
     * @param responseBody
     * @return List<dataVUT>
     * @throws IOException
     * @throws ParseException
     */
    public static List<dataVUT> parseVUT(String responseBody) throws IOException, ParseException {
        JSONArray celaDataJSON = new JSONObject(responseBody).getJSONArray("data");

        List<dataVUT> dataTesty = new ArrayList<dataVUT>();

        for (int i = 0; i < celaDataJSON.length(); i++) {
            JSONObject dataDny = celaDataJSON.getJSONObject(i);

            dataTesty.add(new dataVUT(format.parse(dataDny.getString("datum")),
                    dataDny.getDouble("prirustkovy_pocet_provedenych_ag_testu"),
                    dataDny.getInt("prirustkovy_pocet_provedenych_testu"), dataDny.getInt("prirustkovy_pocet_umrti"),
                    dataDny.getDouble("prirustkovy_pocet_nakazenych"),
                    dataDny.getDouble("prirustkovy_pocet_vylecenych")));

        }

        return dataTesty;
    }
    /*
     * public static List<dataOckovani> parseOckovani(String responseBody) throws
     * IOException, ParseException { JSONArray celaDataJSON = new
     * JSONObject(responseBody).getJSONArray("data"); List<dataOckovani> dataOcko =
     * new ArrayList<dataOckovani>();
     * 
     * double astra1 = 0; double pfizer1 = 0; double moderna1 = 0; double astra2 =
     * 0; double pfizer2 = 0; double moderna2 = 0; double jansen = 0;
     * 
     * for (int i = 1; i < celaDataJSON.length(); i++) { JSONObject aktualniZaznam =
     * celaDataJSON.getJSONObject(i-1); JSONObject dalsiZaznam =
     * celaDataJSON.getJSONObject(i);
     * 
     * if (aktualniZaznam.getString("datum").equals(dalsiZaznam.getString("datum")))
     * { if (aktualniZaznam.getString("vakcina").matches("((V)([A-Z]{7})(A))")) {
     * astra1 += aktualniZaznam.getDouble("prvnich_davek"); astra2 +=
     * aktualniZaznam.getDouble("druhych_davek"); } if
     * (aktualniZaznam.getString("vakcina").matches("[A-Z][a-z]{8}")) { pfizer1 +=
     * aktualniZaznam.getDouble("prvnich_davek"); pfizer2 +=
     * aktualniZaznam.getDouble("druhych_davek"); } if
     * (aktualniZaznam.getString("vakcina").
     * matches("[A-Z]{5}-[0-9]{2} ([A-Z]*[a-z]{6} [M]*[a-z]{6})")) { moderna1 +=
     * aktualniZaznam.getDouble("prvnich_davek"); moderna2 +=
     * aktualniZaznam.getDouble("druhych_davek"); } if
     * (aktualniZaznam.getString("vakcina").
     * matches("[A-Z]{5}-[0-9]{2} ([A-Z]*[a-z]{6} [J]*[a-z]{6})")) { //String nazev
     * = "COVID-19 Vaccine Janssen"; jansen +=
     * aktualniZaznam.getDouble("prvnich_davek"); } } else { dataOcko.add(new
     * dataOckovani(format.parse(aktualniZaznam.getString("datum")),astra1,astra2,
     * pfizer1,pfizer2,moderna1,moderna2,jansen)); astra1 = 0; pfizer1 = 0; moderna1
     * = 0; astra2 = 0; pfizer2 = 0; moderna2 = 0; jansen = 0; }
     * 
     * }
     * 
     * return dataOcko; }
     */

}