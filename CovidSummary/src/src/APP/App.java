package src.APP;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;

//import jdk.internal.org.jline.utils.InputStreamReader;



public class App {

    public static void main(String[] args) throws IOException, ParseException {
        List<dataDny> data = parse(API.fetchApiHosp().toString());
        
        for (dataDny i : data) {
            System.out.println(i.toString());
        }
        /*
        for (int i = 11; i < 1; i--) {
            System.out.println(i + ": " + data.get(data.size() - i).getDatum());
        }*/
        
    }
    public static List<dataDny> parse(String responseBody) throws IOException, ParseException {
        JSONArray celaDataJSON = new JSONObject(responseBody).getJSONArray("data");
        List<dataDny> dataAll = new ArrayList<dataDny>();
        List<Date> dates = new ArrayList<Date>();
        List<Double> umrti = new ArrayList<Double>();
        List<Double> kumUmrti = new ArrayList<Double>();

        DateFormat format = new SimpleDateFormat("y-M-d");
        for (int i = 0; i < celaDataJSON.length(); i++) {
            
            JSONObject dataDny = celaDataJSON.getJSONObject(i);
            umrti.add(dataDny.getDouble("umrti"));
            dates.add(format.parse(dataDny.getString("datum")));
            kumUmrti.add(dataDny.getDouble("kum_umrti"));

            dataAll.add(new dataDny(format.parse(dataDny.getString("datum")), dataDny.getDouble("umrti"), dataDny.getDouble("kum_umrti")));
            //System.out.println(dataAll.get(i).toString());
        }
        
        return dataAll;
    }

}