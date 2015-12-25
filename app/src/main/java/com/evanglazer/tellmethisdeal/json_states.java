package com.evanglazer.tellmethisdeal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 12/22/2015.
 */
public class json_states {
    static ArrayList<pojo_states> stateList = new ArrayList<>();

    public static ArrayList<pojo_states> parseFeed(String content) {
        try {
            JSONArray ar = new JSONArray(content);

            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
               // pojo_states state = new pojo_states(obj.getString("name"), obj.getString("state_abbreviation"));
                stateList.add(new pojo_states(obj.getString("name"), obj.getString("state_abbreviation")));
                //state.setState_abv(obj.getString("abbreviation"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stateList;
    }
}
