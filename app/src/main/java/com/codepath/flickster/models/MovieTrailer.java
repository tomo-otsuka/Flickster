package com.codepath.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieTrailer {

    public String getSource() {
        return source;
    }

    String source;

    public MovieTrailer(JSONObject jsonObject) throws JSONException {
        this.source = jsonObject.getString("source");
    }

    public static ArrayList<MovieTrailer> fromJSONArray(JSONArray array) {
        ArrayList<MovieTrailer> results = new ArrayList<>();
        for (int x = 0; x < array.length(); x++) {
            try {
                results.add(new MovieTrailer(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
