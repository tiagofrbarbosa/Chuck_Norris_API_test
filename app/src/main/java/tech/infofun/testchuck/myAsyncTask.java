package tech.infofun.testchuck;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by tfbarbosa on 09/08/17.
 */

public class myAsyncTask extends AsyncTask<Context, Void, String>{

    private Context context;
    public static final String BASE_URL = "https://api.chucknorris.io/jokes";

    @Override
    protected String doInBackground(Context... params) {

        context = params[0];
        String joke = null;

        try {

            StringBuilder url = new StringBuilder(BASE_URL + "/random");
            URL api = new URL(url.toString());
            URLConnection conn = api.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            in.close();


            JSONObject jsonObject = new JSONObject(new JSONTokener(response.toString()));
            joke = jsonObject.optString("value");

        }catch (Exception e){
            e.printStackTrace();
        }

        return joke;
    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }
}
