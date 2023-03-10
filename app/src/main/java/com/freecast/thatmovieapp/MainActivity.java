package com.freecast.thatmovieapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private static String api_key = "_YOUR_API_KEY_HERE";
    private static String base_url_movies_top_rated = "https://api.themoviedb.org/3/movie/top_rated?api_key=";
    private SimpleExoPlayer player = null;
    private PlayerView player_view;
    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMovies();

        player = new SimpleExoPlayer.Builder(this).build();
        player_view = findViewById(R.id.player_view);
        rv = findViewById(R.id.movies);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        player_view.setPlayer(player);

        try {
                DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, "sample");
                MediaSource ms =  new ProgressiveMediaSource.Factory(dataSourceFactory)

                        .createMediaSource(MediaItem.fromUri(Uri.parse("https://dl.dropbox.com/s/5y0ma68jhp0731t/sample.mp4")));

                 player.setMediaSource(ms);
                 player.prepare();
                    player.setPlayWhenReady(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMovies() {
        Thread thread = new Thread(() -> {

            Response response;

        try {
            String url = base_url_movies_top_rated + api_key;

            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                String stringResponse = responseBody.string();
                JSONObject objResponse = new JSONObject(stringResponse);
                Log.d("Response----%s", stringResponse);

                if (objResponse.has("results")) {
                    Object json = objResponse.get("results");

                    if ((json instanceof JSONArray)) {
                        JSONArray movies = objResponse.getJSONArray("results");
                                ArrayList<Pair<String, String>> namesAndImages = new ArrayList<Pair<String, String>>();

                                    for (int i = 0; i < movies.length(); i++) {
                                        JSONObject actor = movies.getJSONObject(i);
                                        String name = actor.getString("title");
                                        String image = actor.getString("poster_path");
                                        namesAndImages.add(new Pair(name, image));
                                    }

                                    runOnUiThread(() -> {
                                        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
                                        MovieAdapter adapter = new MovieAdapter(namesAndImages, this);
                                        rv.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    });
                        } else {
                                String msg = objResponse.getString("message");
                            runOnUiThread(() -> {
                                    Toast.makeText(this, "message is: " + msg, Toast.LENGTH_SHORT).show();
                            });
                        }
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show();
                });
            }
            }
        } catch (Exception e) {
            runOnUiThread(() -> {
                Toast.makeText(this, "This is " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        } });

        thread.start();
    }
}
