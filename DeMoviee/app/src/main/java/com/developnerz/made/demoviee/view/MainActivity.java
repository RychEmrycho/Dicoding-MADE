package com.developnerz.made.demoviee.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.developnerz.made.demoviee.contract.MainViewContract;
import com.developnerz.made.demoviee.listener.ItemClickSupport;
import com.developnerz.made.demoviee.R;
import com.developnerz.made.demoviee.adapter.MovieAdapter;
import com.developnerz.made.demoviee.api.APIInterface;
import com.developnerz.made.demoviee.api.APIClient;
import com.developnerz.made.demoviee.model.APIResponse;
import com.developnerz.made.demoviee.presenter.MainViewPresenter;
import com.developnerz.made.demoviee.repository.MoviesData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainViewContract.View{

    private MainViewPresenter presenter;

    @BindView(R.id.root_layout) ConstraintLayout root_layout;

    @BindView(R.id.tv_action_title) TextView tv_action_title;

    @BindView(R.id.input_title_query) EditText input_title_query;

    @BindView(R.id.rv_movie_adapter) RecyclerView recyclerView;

    @OnClick(R.id.btn_search)
    void searchMovie(){
        if(TextUtils.isEmpty(input_title_query.getText().toString())){
            input_title_query.setError("Please fill search box to continue..");
        } else {
            if (presenter.isInternetAvailable(this)){
                tv_action_title.setText("Showing result for: " + input_title_query.getText().toString());
                presenter.searchMovieWithTitle(input_title_query.getText().toString());
            } else {
                presenter.showSnackBar("Please check your network conection..", Snackbar.LENGTH_INDEFINITE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainViewPresenter();
        presenter.setView(this, this);

        if(!presenter.isInternetAvailable(this)){
            presenter.showSnackBar("Please check your network connection..", Snackbar.LENGTH_INDEFINITE);
        } else {
            presenter.getTopRatedMovies();
        }
    }

    @Override
    public ConstraintLayout getRootLayout() {
        return root_layout;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
