package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.net.JarURLConnection;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {
public static final String YOUTUBE_API_KEY = "AIzaSyCSGcY6iHzWZMR1rGb5xzD9XlCnEktvU08";
public static final  String VIDEO = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        ratingBar = findViewById(R.id.ratingBar);
        youTubePlayerView = findViewById(R.id.player);

        //String title = getIntent().getStringExtra("title");
        Movie movie =  Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getRating());
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEO , movie.getMovieId()) , new JsonHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        try {
                            JSONArray results = json.jsonObject.getJSONArray("results");
                            if (results.length()== 0){
                                return ;

                            }
                          String youtubekey =  results.getJSONObject(0).getString("key");
                            Log.e("DetailActivity" , youtubekey);
                            InializeYoutube(youtubekey);

                        } catch (JSONException e) {
                            Log.e("DetailActivity" ,"onJsonHttpResponseHandler");
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                    }
                });


    }

    private void InializeYoutube(final String youtubekey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity" , "onInitializationSuccess");
                youTubePlayer.cueVideo(youtubekey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity" , "onInitializationFailure");
            }
        });
    }

}