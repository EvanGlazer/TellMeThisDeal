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
    static List<pojo_states> stateList;

    public static List<pojo_states> parseFeed(String content) {
        try {
            JSONArray ar = new JSONArray(content);
            stateList = new ArrayList<>();
            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
                pojo_states state = new pojo_states();
                state.setState_name(obj.getString("name"));
                //state.setState_abv(obj.getString("abbreviation"));
                stateList.add(state);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stateList;
    }
}
