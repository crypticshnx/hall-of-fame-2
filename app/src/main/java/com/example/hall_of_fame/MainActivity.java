package com.example.hall_of_fame;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hall_of_fame.adapters.StarAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    RecyclerView rvFamers;
    StarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        try {
            InputStream is = getAssets().open("hall_of_famers.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();


            JsonArray starList = obj.getAsJsonArray("stars");
            Log.i(TAG, "results: " + starList.toString());
            List<Star> stars = Star.fromJsonArray(starList);
            Log.i(TAG, "stars: " + stars.toString());
            adapter = new StarAdapter(stars);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        rvFamers = findViewById(R.id.rvFamers);
        rvFamers.setLayoutManager(new LinearLayoutManager(this));
        rvFamers.setAdapter(adapter);
    }

}