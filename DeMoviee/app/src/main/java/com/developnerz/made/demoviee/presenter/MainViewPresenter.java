package com.developnerz.made.demoviee.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.developnerz.made.demoviee.adapter.MovieAdapter;
import com.developnerz.made.demoviee.api.APIService;
import com.developnerz.made.demoviee.contract.MainViewContract;
import com.developnerz.made.demoviee.listener.ItemClickSupport;
import com.developnerz.made.demoviee.view.DetailActivity;

/**
 * Created by Rych Emrycho on 7/22/2018 at 7:29 AM.
 * Updated by Rych Emrycho on 7/22/2018 at 7:29 AM.
 */
public class MainViewPresenter implements MainViewContract.Presenter {

    private MainViewContract.View view;
    private MainViewContract.Service service;
    private Context context;


    private void setUpPresenterService() {
        service = new APIService();
        service.setPresenter(this);
    }

    @Override
    public void setView(MainViewContract.View view, Context context) {
        this.view = view;
        this.context = context;
        setUpPresenterService();
    }

    private void seeMovieDetail(Context context, int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        context.startActivity(intent);
    }

    @Override
    public void showSnackBar(String message, int duration) {
        Snackbar.make(view.getRootLayout(), message, duration).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    @Override
    public void setMovieAdapter(MovieAdapter movieAdapter) {
        view.getRecyclerView().setHasFixedSize(true);
        view.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        view.getRecyclerView().setAdapter(movieAdapter);

        ItemClickSupport.addTo(view.getRecyclerView()).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                seeMovieDetail(context, position);
            }
        });
    }

    @Override
    public void searchMovieWithTitle(String titleQuery) {
        service.searchMovieWithTitle(titleQuery);
    }

    @Override
    public void getTopRatedMovies() {
        service.getTopRatedMovies();
    }

    @Override
    public boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
