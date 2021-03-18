package com.example.hall_of_fame;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Star {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("major")
    public String major;
    @SerializedName("favorite_cp_app")
    public String favoriteApp;
    @SerializedName("fun_fact")
    public String funFact;
    @SerializedName("year_started_programming")
    public int firstCodeYear;

    static List<Star> fromJsonArray(JsonArray data) throws JSONException {
        List<Star> stars = new ArrayList<>();

        Gson gson = new Gson();

        for (int i = 0; i < data.size(); i++)
            stars.add(gson.fromJson(data.get(i), Star.class));
        return stars;
    }
}
