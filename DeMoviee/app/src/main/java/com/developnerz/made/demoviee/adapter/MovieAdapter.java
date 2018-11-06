package com.developnerz.made.demoviee.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developnerz.made.demoviee.R;
import com.developnerz.made.demoviee.model.Movie;
import com.developnerz.made.demoviee.repository.MoviesData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rych Emrycho on 7/21/2018 at 12:00 PM.
 * Updated by Rych Emrycho on 7/21/2018 at 12:00 PM.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recyclerview_item_layout, parent, false);
        return new ViewHolder(view);
    }

    private void setFieldEmpty(TextView view){
        view.setLines(1);
        view.setText(view.getText().toString()+" -");
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        try {
            holder.tv_movie_title.setText(MoviesData.getMoviesData().get(position).getTitle());

            if(TextUtils.isEmpty(MoviesData.getMoviesData().get(position).getOverview())){
                Log.d("SHOOOOOWWWW TITLE: ", MoviesData.getMoviesData().get(position).getTitle());
                Log.d("SHOOOOOWWWW OVERVIEW: ", MoviesData.getMoviesData().get(position).getOverview());

                setFieldEmpty(holder.tv_movie_description);
            } else {
                holder.tv_movie_description.setText(MoviesData.getMoviesData().get(position).getOverview());
            }

            if(TextUtils.isEmpty(MoviesData.getMoviesData().get(position).getReleaseDate())){
                setFieldEmpty(holder.tv_movie_date);
            } else {
                holder.tv_movie_date.setText("Release Date: "+MoviesData.getMoviesData().get(position).getReleaseDate());
            }

//        } catch (NullPointerException e){
//
//        }

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w342/"+MoviesData.getMoviesData().get(position).getBackdropPath())
                .placeholder(R.drawable.img_no_movie_poster)
                //.error(R.mipmap.ic_launcher_round)
                .into(holder.img_movie_poster);
    }

    @Override
    public int getItemCount() {
        return MoviesData.getMoviesData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_movie_poster)
        ImageView img_movie_poster;

        @BindView(R.id.tv_movie_title)
        TextView tv_movie_title;

        @BindView(R.id.tv_movie_description)
        TextView tv_movie_description;

        @BindView(R.id.tv_movie_date)
        TextView tv_movie_date;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
