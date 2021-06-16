package src.APP;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;

//import jdk.internal.org.jline.utils.InputStreamReader;



public class App {

    static DateFormat format = new SimpleDateFormat("y-M-d");

    public static void main(String[] args) throws IOException, ParseException {
        /*
        List<dataDny> data = parseHosp(API.fetchApiHosp().toString());
        
        for (dataDny i : data) {
            System.out.println(i.toString());
        }
        
        for (int i = 11; i < 1; i--) {
            System.out.println(i + ": " + data.get(data.size() - i).getDatum());
        }*/
        //parseUmrti(API.fetchApi().toString());
        parseVUT(API.fetchApi().toString());

        

    }
    /*
    public static List<dataUmrti> parseUmrti(String responseBody) throws IOException, ParseException {
        JSONArray celaDataJSON = new JSONObject(responseBody).getJSONArray("data");
        List<dataUmrti> dataUmrti = new ArrayList<dataUmrti>();

        
        for (int i = 0; i < celaDataJSON.length(); i++) {
            
            JSONObject dataDny = celaDataJSON.getJSONObject(i);

            dataUmrti.add(new dataUmrti(format.parse(dataDny.getString("datum")), dataDny.getDouble("prirustkovy_pocet_umrti"), dataDny.getDouble("kumulativni_pocet_umrti")));
            //System.out.println(dataAll.get(i).toString());
        }
        
        return dataUmrti;
    }
    */
    public static List<dataVUT> parseVUT(String responseBody) throws IOException, ParseException {
        JSONArray celaDataJSON = new JSONObject(responseBody).getJSONArray("data");
        List<dataVUT> dataTesty = new ArrayList<dataVUT>();
        
        for (int i = 0; i < celaDataJSON.length(); i++) {
            JSONObject dataDny = celaDataJSON.getJSONObject(i);

            dataTesty.add(new dataVUT(format.parse(dataDny.getString("datum")),
                            dataDny.getDouble("prirustkovy_pocet_provedenych_ag_testu"), 
                            dataDny.getDouble("prirustkovy_pocet_provedenych_testu"), 
                            dataDny.getDouble("kumulativni_pocet_ag_testu"), 
                            dataDny.getDouble("kumulativni_pocet_testu"), 
                            dataDny.getDouble("prirustkovy_pocet_umrti"),
                            dataDny.getDouble("kumulativni_pocet_umrti"),
                            dataDny.getDouble("prirustkovy_pocet_nakazenych"),
                            dataDny.getDouble("prirustkovy_pocet_vylecenych")
                            
                            ));
        }

        return dataTesty;
    }
    /*
    public static List<dataVUT> parseOckovani(String responseBody) throws IOException, ParseException {
        JSONArray celaDataJSON = new JSONObject(responseBody).getJSONArray("data");
        List<dataVUT> dataTesty = new ArrayList<dataVUT>();
        
        for (int i = 0; i < celaDataJSON.length(); i++) {
            JSONObject dataDny = celaDataJSON.getJSONObject(i);

            dataTesty.add(n
            ew dataVUT(format.parse(dataDny.getString("datum")), dataDny.getDouble("prirustkovy_pocet_provedenych_ag_testu"), dataDny.getDouble("prirustkovy_pocet_provedenych_testu"), dataDny.getDouble("kumulativni_pocet_ag_testu"), dataDny.getDouble("kumulativni_pocet_testu")));
        }

        return dataTesty;
    }
    */


}