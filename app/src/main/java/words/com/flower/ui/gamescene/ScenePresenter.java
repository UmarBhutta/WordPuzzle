package words.com.flower.ui.gamescene;

/**
 * Created by malikumarbhutta on 7/28/16.
 */
public interface ScenePresenter {

    void onBackPressed();
    void onCorrectClick();
    void onWrongClick();
    void startGame();
    void endGame();
    void stopGame();
    void updateScore(boolean update);



}
