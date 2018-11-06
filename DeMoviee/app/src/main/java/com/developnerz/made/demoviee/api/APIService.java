package com.developnerz.made.demoviee.api;

import android.support.design.widget.Snackbar;

import com.developnerz.made.demoviee.adapter.MovieAdapter;
import com.developnerz.made.demoviee.contract.MainViewContract;
import com.developnerz.made.demoviee.model.APIResponse;
import com.developnerz.made.demoviee.repository.MoviesData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rych Emrycho on 7/22/2018 at 8:31 AM.
 * Updated by Rych Emrycho on 7/22/2018 at 8:31 AM.
 */
public class APIService implements MainViewContract.Service {

    private MainViewContract.Presenter presenter;
    private final static String API_KEY = "08fa02ddf6f267c38015beceb63833ad";
    private APIInterface webService;
    private Call<APIResponse> call;

    private void setUpService() {
        webService = APIClient.getClient().create(APIInterface.class);
    }

    @Override
    public void setPresenter(MainViewContract.Presenter presenter) {
        this.presenter = presenter;
        setUpService();
    }

    @Override
    public void getTopRatedMovies() {
        call = webService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful()){
                    MoviesData.setMoviesData(response.body().getResults());
                    presenter.setMovieAdapter(new MovieAdapter(MoviesData.getMoviesData()));
                } else {
                    presenter.showSnackBar("Some error Occured..", Snackbar.LENGTH_INDEFINITE);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void searchMovieWithTitle(String titleQuery) {
        call = webService.getMoviesWithTitle(API_KEY, titleQuery);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                MoviesData.setMoviesData(response.body().getResults());
                presenter.setMovieAdapter(new MovieAdapter(MoviesData.getMoviesData()));
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }
}
