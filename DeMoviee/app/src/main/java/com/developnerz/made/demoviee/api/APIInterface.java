package com.developnerz.made.demoviee.api;

import com.developnerz.made.demoviee.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rych Emrycho on 7/21/2018 at 12:32 PM.
 * Updated by Rych Emrycho on 7/21/2018 at 12:32 PM.
 */
public interface APIInterface {
    @GET("search/movie")
    Call<APIResponse> getMoviesWithTitle(
            @Query("api_key") String apiKey,
            @Query("query") String movieTitle);

    @GET("movie/top_rated")
    Call<APIResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
