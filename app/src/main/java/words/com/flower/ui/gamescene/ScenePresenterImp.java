package words.com.flower.ui.gamescene;

import java.util.List;

import words.com.flower.data.Word;

/**
 * Created by malikumarbhutta on 7/28/16.
 */
public class ScenePresenterImp implements ScenePresenter,LogicInteractorImp.PublishToScreen {


    private GameView gameView;
    private List<Word> words;
    private LogicInteractorImp logicInteractorImp;

    public ScenePresenterImp(GameView gameView, List<Word> words) {
        this.gameView = gameView;
        this.words = words;
        logicInteractorImp = new LogicInteractorImp(this.words,this);


    }

    @Override
    public void publish(Word word, int index,boolean shuffle) {
        gameView.GeneratedWord(word,index,shuffle);
    }

    @Override
    public void onBackPressed() {
        gameView.showEndAlert();
    }

    @Override
    public void onCorrectClick() {
        gameView.correctPressed();
    }

    @Override
    public void onWrongClick() {
        gameView.wrongPressed();
    }

    @Override
    public void startGame() {
        logicInteractorImp.startGeneration();

    }

    @Override
    public void endGame() {
        logicInteractorImp.stopGeneration();
        gameView.endGame();
    }

    @Override
    public void stopGame() {
        logicInteractorImp.stopGeneration();
    }

    @Override
    public void updateScore(boolean update) {
        gameView.updateScore(update);
    }
}
