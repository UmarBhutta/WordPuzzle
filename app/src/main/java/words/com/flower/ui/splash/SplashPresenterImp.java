package words.com.flower.ui.splash;

import android.app.Activity;
import android.content.Intent;

import java.util.List;

import words.com.flower.data.Word;

/**
 * Created by malikumarbhutta on 7/27/16.
 */
public class SplashPresenterImp implements SplashPresenter,DataInterActorImp.DataInteractorListener {


    SplashView splashView;
    DataInteractor dataInteractor;

    public SplashPresenterImp(SplashView splashView) {
        this.splashView = splashView;
        this.dataInteractor = new DataInterActorImp(((Activity)splashView),this);

    }


    @Override
    public void onDataParseSuccessful(List<Word> words) {
        splashView.stopProgress();
        splashView.loadedWords(words);
    }

    @Override
    public void onDataParsingError() {
        splashView.stopProgress();
    }

    @Override
    public void onStartClick() {
        splashView.startGame();
    }

    @Override
    public void loadWords() {
        splashView.showProgress();
        dataInteractor.dataParsing();
    }

    @Override
    public void onActivityResult(Intent intent) {
        int correct = intent.getIntExtra("correct",0);
        int wrong = intent.getIntExtra("wrong",0);
        int nothing = intent.getIntExtra("nothing",0);
        splashView.updateResult(correct,wrong,nothing);
    }
}
