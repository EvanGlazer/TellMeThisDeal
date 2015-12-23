package HTTP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 12/22/2015.
 */
public class json_state_city
{
    static List<pojo_state_city> cityList;
    public static List<pojo_state_city> parseFeed(String content)
    {
            try {
                JSONArray ar = new JSONArray(content);
                cityList = new ArrayList<>();
                for(int i=0; i<ar.length(); i++)
                {
                    JSONObject obj = ar.getJSONObject(i);
                    pojo_state_city city = new pojo_state_city();
                    city.setCountryName(obj.getString("full_county_name"));
                    cityList.add(city);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        return cityList;
    }







}
