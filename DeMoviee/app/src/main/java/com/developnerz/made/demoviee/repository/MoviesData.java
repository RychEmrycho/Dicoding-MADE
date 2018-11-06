package com.developnerz.made.demoviee.repository;

import com.developnerz.made.demoviee.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rych Emrycho on 7/21/2018 at 6:39 PM.
 * Updated by Rych Emrycho on 7/21/2018 at 6:39 PM.
 */
public class MoviesData {

    private static List<Movie> moviesData;

    public static Movie getMovieData(int position) {
        return moviesData.get(position);
    }

    public static List<Movie> getMoviesData() {
        return moviesData;
    }

    public static void setMoviesData(List<Movie> moviesData) {
        MoviesData.moviesData = moviesData;
    }
}
