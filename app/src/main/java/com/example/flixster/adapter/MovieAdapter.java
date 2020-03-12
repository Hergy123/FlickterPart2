package com.example.flixster.adapter;


import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter " , "OncreateViewHolder");

        View movieview = LayoutInflater.from(context).inflate(R.layout.item_movie , parent ,false);
        return new ViewHolder(movieview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter " , "OnBlindHolder" + position);
       Movie movie = movies.get(position);
       holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvOverview = itemView.findViewById(R.id.tvOverview);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        ivPoster = itemView.findViewById(R.id.ivPoster);
    }

        public void bind(Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        String imageUrl;
        if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            imageUrl = movie.getBackDropath();
        }else {
            imageUrl = movie.getPoterPath();
        }

        Glide.with(context).load(imageUrl).into(ivPoster);

        }
    }
}
