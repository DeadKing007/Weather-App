package dead.weatherapp;

public class WeatherInfo {

    private String Location,Max_Temp,Min_Temp,Humidity,description;
    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String Date,Icon;


    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getMax_Temp() {
        return Max_Temp;
    }

    public void setMax_Temp(String max_Temp) {
        Max_Temp = max_Temp;
    }

    public String getMin_Temp() {
        return Min_Temp;
    }

    public void setMin_Temp(String min_Temp) {
        Min_Temp = min_Temp;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }
}
