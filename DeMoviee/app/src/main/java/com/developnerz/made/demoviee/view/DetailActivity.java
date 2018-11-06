package com.developnerz.made.demoviee.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developnerz.made.demoviee.R;
import com.developnerz.made.demoviee.contract.DetailViewContract;
import com.developnerz.made.demoviee.model.Movie;
import com.developnerz.made.demoviee.presenter.DetailViewPresenter;
import com.developnerz.made.demoviee.repository.MoviesData;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailViewContract.View {

    public final static String EXTRA_POSITION = "extra_position";
    private DetailViewPresenter presenter;
    private Movie movie;
    private int position;

    @BindView(R.id.img_big_movie_poster) ImageView img_big_movie_poster;

    @BindView(R.id.tv_movie_title) TextView tv_movie_title;

    @BindView(R.id.tv_movie_description) TextView tv_movie_description;

    @BindView(R.id.tv_movie_lang) TextView tv_movie_lang;

    @BindView(R.id.tv_movie_vote) TextView tv_movie_vote;

    @BindView(R.id.tv_movie_adult) TextView tv_movie_adult;

    @BindView(R.id.tv_movie_popularity) TextView tv_movie_popularity;

    @BindView(R.id.tv_movie_release_date) TextView tv_movie_release_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        position = getIntent().getIntExtra(EXTRA_POSITION, 0);

        presenter = new DetailViewPresenter();
        presenter.setView(this);

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w780/"+movie.getBackdropPath())
                .placeholder(R.drawable.img_big_light_no_movie_poster)
                //.error(R.mipmap.ic_launcher_round)
                .into(img_big_movie_poster);

        tv_movie_title.setText(movie.getTitle());

        setEmptyField(movie);

        if(movie.getAdult()){
            tv_movie_adult.setText("Yes");
        } else {
            tv_movie_adult.setText("No");
        }

    }

    @Override
    public int getPosition() {
        return position;
    }

    public void setEmptyField(Movie movie){
        if(TextUtils.isEmpty(movie.getOverview())){
            setNoData(tv_movie_description);
        } else {
            tv_movie_description.setText(movie.getOverview());
        }

        if(TextUtils.isEmpty(movie.getOriginalLanguage())){
            setNoData(tv_movie_lang);
        } else {
            tv_movie_lang.setText(movie.getOriginalLanguage().toUpperCase());
        }

        if(TextUtils.isEmpty(String.valueOf(movie.getVoteAverage()))){
            setNoData(tv_movie_vote);
        } else {
            tv_movie_vote.setText(String.valueOf(movie.getVoteAverage()));
        }

        if(TextUtils.isEmpty(String.valueOf(movie.getPopularity()))){
            setNoData(tv_movie_popularity);
        } else {
            tv_movie_popularity.setText(String.valueOf(movie.getPopularity()));
        }

        if(TextUtils.isEmpty(movie.getReleaseDate())){
            setNoData(tv_movie_release_date);
        } else {
            tv_movie_release_date.setText(movie.getReleaseDate());
        }
    }

    public void setNoData(TextView view){
        view.setText("-");
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
