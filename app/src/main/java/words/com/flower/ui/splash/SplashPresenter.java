package words.com.flower.ui.splash;

import android.content.Intent;

/**
 * Created by malikumarbhutta on 7/25/16.
 */
public interface SplashPresenter {

    void onStartClick(); //Presenter method to start the game scene
    void loadWords();
    void onActivityResult(Intent intent);

}
