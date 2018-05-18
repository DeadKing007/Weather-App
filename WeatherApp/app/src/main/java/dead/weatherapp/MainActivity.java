package dead.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView Location,Date,Max_Temp,Min_Temp,Description;
    private ImageView Image;
    private  final String TAG=MainActivity.class.getName();
    String code;
    String Receive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Location=findViewById(R.id.Location);
        Date=findViewById(R.id.Date);
        Max_Temp=findViewById(R.id.Max_Temp);
        Min_Temp=findViewById(R.id.Min_Temp);
        Image=findViewById(R.id.Image);
        Description=findViewById(R.id.Description);
        Image.setVisibility(View.INVISIBLE);
        Location.setVisibility(View.INVISIBLE);
        Min_Temp.setVisibility(View.INVISIBLE);
        Max_Temp.setVisibility(View.INVISIBLE);

        Date.setVisibility(View.INVISIBLE);
        Description.setVisibility(View.INVISIBLE);

         Receive=getIntent().getStringExtra("Key");

    }

    @Override
    protected void onStart() {
        super.onStart();
        new WeatherFeatch().execute();
    }

    class WeatherFeatch extends AsyncTask<Void, Void, WeatherInfo> {

        @Override
        protected WeatherInfo doInBackground(Void... voids) {
            //Getting  information from site to String
           String information= DownloadingWeatherInformation();
           // Log.d(TAG,"Data="+information);

            WeatherInfo info=getparsedJSON(information);

            return info;
        }

        private WeatherInfo getparsedJSON(String information) {
            WeatherInfo info =new WeatherInfo();;

            try {
                JSONObject rawobject=new JSONObject(information);
                JSONObject object=rawobject.getJSONObject("main");
                code=rawobject.getString("cod");


                JSONArray array=rawobject.getJSONArray("weather");
                JSONObject arrayobject=array.getJSONObject(0);
                String description=arrayobject.getString("description");
                String icon=arrayobject.getString("icon");

                String Min_temp=object.getString("temp_min");
                String Max_temp=object.getString("temp_max");
                String Humidity=object.getString("humidity");


                info.setDescription(description);
                info.setHumidity(Humidity);
                info.setIcon(icon);
                info.setMax_Temp(Max_temp);
                info.setMin_Temp(Min_temp);
                info.setLocation(Receive);
                info.setDate("");
                info.setIcon(icon);
                info.setDate(Utility.getTodaysDate());
                info.setCode(code);



            } catch (JSONException e) {
                e.printStackTrace();
            }


            return info;
        }

        private String DownloadingWeatherInformation() {

            //1. Uri Builder
            String output="";

            try {


                Uri.Builder builder=new Uri.Builder();
                builder.scheme("http");
                builder.authority("api.openweathermap.org");
                builder.appendPath("data");
                builder.appendPath("2.5");
                builder.appendPath("weather");
                builder.appendQueryParameter("q",Receive);
                builder.appendQueryParameter("appid","e41f4d81bd5c7cc90cd395f6a3c18045");
                builder.appendQueryParameter("mode","json");
                builder.appendQueryParameter("units","metric");

                URL url=new URL(builder.build().toString());

                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(15000);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                int responsecode=connection.getResponseCode();
                if (responsecode==HttpURLConnection.HTTP_OK){
                    InputStream inputStream=connection.getInputStream();

                    if (inputStream!=null){
                        output=converttoString(inputStream);
                    }

                }
                connection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return output;
        }

        private String converttoString(InputStream inputStream) throws IOException {
            String output="";

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder=new StringBuilder();
            String line="";

            if ((line=bufferedReader.readLine())!=null){
                builder=builder.append(line);
            }
            output=builder.toString();

            return output;
        }

        @Override
        protected void onPostExecute(WeatherInfo weatherInfo) {
            super.onPostExecute(weatherInfo);
            if (weatherInfo!=null) {
                setInterface(weatherInfo);

            }


        }
    }

    private void setInterface(WeatherInfo weatherInfo) {

            if (weatherInfo.getIcon()!=null) {

                Image.setVisibility(View.VISIBLE);
                Location.setVisibility(View.VISIBLE);
                Min_Temp.setVisibility(View.VISIBLE);
                Max_Temp.setVisibility(View.VISIBLE);

                Date.setVisibility(View.VISIBLE);
                Description.setVisibility(View.VISIBLE);


                Location.setText(weatherInfo.getLocation());
                Max_Temp.setText(weatherInfo.getMax_Temp());
                Min_Temp.setText(weatherInfo.getMin_Temp());
                Description.setText(weatherInfo.getDescription());
                Date.setText(weatherInfo.getDate());


                String icon = weatherInfo.getIcon();


                // Handle all conditions for weather images
                if ("04d".equals(icon) || "04n".equals(icon)) {
                    Image.setImageResource(R.drawable.broken_clouds);
                } else if ("01d".equals(icon) || "01n".equals(icon)) {
                    Image.setImageResource(R.drawable.clearsky);
                } else if ("02d".equals(icon) || "02n".equals(icon)) {
                    Image.setImageResource(R.drawable.fewclouds);
                } else if ("03d".equals(icon) || "03n".equals(icon)) {
                    Image.setImageResource(R.drawable.scattered_clouds);
                } else if ("09d".equals(icon) || "09n".equals(icon)) {
                    Image.setImageResource(R.drawable.shower_rain);
                } else if ("10d".equals(icon) || "10n".equals(icon)) {
                    Image.setImageResource(R.drawable.rain);
                } else if ("11d".equals(icon) || "11n".equals(icon)) {
                    Image.setImageResource(R.drawable.thunderstorm);
                } else if ("13d".equals(icon) || "13n".equals(icon)) {
                    Image.setImageResource(R.drawable.snow);
                } else if ("50d".equals(icon) || "50n".equals(icon)) {
                    Image.setImageResource(R.drawable.mist);
                }
            }
            else {
                Intent intent=new Intent(this,EditClass.class);
                intent.putExtra("Return","0");
                startActivity(intent);

            }

    }
}
