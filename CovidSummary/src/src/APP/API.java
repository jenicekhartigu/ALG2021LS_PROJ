package src.APP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    static BufferedReader reader;
    static String line;
    static StringBuffer responseContent = new StringBuffer();

    public static StringBuffer fetchApiHosp() {
        try {

            URL url = new URL("https://onemocneni-aktualne.mzcr.cz/api/v2/covid-19/hospitalizace.json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int status = conn.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    public static StringBuffer fetchApiOckovani() {
        try {

            URL url = new URL("https://onemocneni-aktualne.mzcr.cz/api/v2/covid-19/hospitalizace.json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int status = conn.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseContent;
    }
}
