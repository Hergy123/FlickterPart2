package com.example.flixster.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    String backDropath;
    String poterPath;
    String title;
    String overview;


    public Movie(JSONObject jsonObject) throws JSONException {
        backDropath = jsonObject.getString("backdrop_path");
        poterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    public String getPoterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", poterPath);
    }

    public String getTitle() {

        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackDropath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s" , backDropath);
    }


    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0 ; i < movieJsonArray.length() ; i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));

        }

        return movies;
    }

}
