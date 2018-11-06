package com.developnerz.made.demoviee.presenter;

import com.developnerz.made.demoviee.contract.DetailViewContract;
import com.developnerz.made.demoviee.model.Movie;
import com.developnerz.made.demoviee.repository.MoviesData;

/**
 * Created by Rych Emrycho on 7/22/2018 at 11:10 AM.
 * Updated by Rych Emrycho on 7/22/2018 at 11:10 AM.
 */
public class DetailViewPresenter implements DetailViewContract.Presenter {

    private DetailViewContract.View view;

    @Override
    public void setView(DetailViewContract.View mView) {
        view = mView;
        view.setMovie(MoviesData.getMovieData(view.getPosition()));
    }
}
