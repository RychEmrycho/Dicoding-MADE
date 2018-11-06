package com.developnerz.made.demoviee.contract;

import android.widget.ImageView;
import android.widget.TextView;

import com.developnerz.made.demoviee.model.Movie;

/**
 * Created by Rych Emrycho on 7/22/2018 at 11:10 AM.
 * Updated by Rych Emrycho on 7/22/2018 at 11:10 AM.
 */
public class DetailViewContract {
    public interface View {
        int getPosition();
        void setMovie(Movie movie);
    }

    public interface Presenter {
        void setView(DetailViewContract.View view);
    }
}
