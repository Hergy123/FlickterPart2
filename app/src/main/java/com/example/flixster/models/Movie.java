package com.example.flixster.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
public class Movie {
    int movieId;

    String backDropath;
    String poterPath;
    String title;
    String overview;
    double rating ;

    // empty constructor
    public Movie (){

    }


    public Movie(JSONObject jsonObject) throws JSONException {
        backDropath = jsonObject.getString("backdrop_path");
        poterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
       rating = jsonObject.getDouble("vote_average");
       movieId = jsonObject.getInt("id");
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

    public double getRating() {
        return rating;
    }

    public int getMovieId() {
        return movieId;
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
