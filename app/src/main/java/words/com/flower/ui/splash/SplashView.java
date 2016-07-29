package words.com.flower.ui.splash;

import java.util.List;

import words.com.flower.data.Word;

/**
 * Created by malikumarbhutta on 7/27/16.
 */
public interface SplashView {

    void loadedWords(List<Word> words);  //method call back to done data parsing at the application load time

    void startGame();  //method call to start the game

    void showProgress(); //method to show progress bar

    void stopProgress(); //method to stop progress bar

    void updateResult(int correct,int wrong,int noAnswer);


}
