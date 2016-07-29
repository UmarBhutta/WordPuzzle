package words.com.flower.ui.gamescene;

import words.com.flower.data.Word;

/**
 * Created by malikumarbhutta on 7/28/16.
 */
public interface GameView {

    void GeneratedWord(Word word, int index,boolean shuffle);
    void startGame();
    void endGame();
    void updateScore(boolean update);
    void showEndAlert();
    void correctPressed();
    void wrongPressed();


}
