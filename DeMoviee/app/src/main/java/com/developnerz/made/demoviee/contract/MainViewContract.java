package com.developnerz.made.demoviee.contract;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;

import com.developnerz.made.demoviee.adapter.MovieAdapter;

/**
 * Created by Rych Emrycho on 7/22/2018 at 7:30 AM.
 * Updated by Rych Emrycho on 7/22/2018 at 7:30 AM.
 */
public class MainViewContract {
    public interface View {
        ConstraintLayout getRootLayout();
        RecyclerView getRecyclerView();
    }

    public interface Presenter {
        boolean isInternetAvailable(Context context);
        void getTopRatedMovies();
        void setView(MainViewContract.View view, Context context);
        void setMovieAdapter(MovieAdapter movieAdapter);
        void searchMovieWithTitle(String titleQuery);
        void showSnackBar(String message, int duration);
    }

    public interface Service {
        void getTopRatedMovies();
        void setPresenter(MainViewContract.Presenter presenter);
        void searchMovieWithTitle(String titleQuery);
    }
}
