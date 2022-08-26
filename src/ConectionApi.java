import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;

public class ConectionApi implements IConectionApi {



    public String Get(String Url)
    {
        HttpURLConnection connection = null;
        StringBuilder Response = new StringBuilder("");
        try {

            connection = (HttpURLConnection) new URL(Url).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode())
            {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String resp;
                while((resp = buffer.readLine()) != null)
                {
                    Response.append(resp);
                }

            } else{

                System.out.println("ERROR:" + connection.getResponseCode());

            }

        }catch (Throwable cause){
            cause.getStackTrace();
        }finally {
            if (connection != null)
            {
            connection.disconnect();
            }
        }
        return Response.toString();
    }


}
