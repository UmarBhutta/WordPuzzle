package words.com.flower.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import words.com.flower.BaseActivity;
import words.com.flower.R;
import words.com.flower.data.Word;
import words.com.flower.ui.gamescene.GameActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends BaseActivity implements SplashView {


    private static final int REQUEST_RESULTS = 234;
    List<Word> words;
    SplashPresenter splashPresenter;

    @OnClick(R.id.button)
    public void startButton(View view){
        splashPresenter.onStartClick();
    }
    @BindView(R.id.button)
    Button button;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.correct)
    TextView correctView;

    @BindView(R.id.wrong)
    TextView wrongView;

    @BindView(R.id.no_answer)
    TextView noAnswerView;

    @BindView(R.id.centre)
    LinearLayout centre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashPresenter = new SplashPresenterImp(this);
        splashPresenter.loadWords();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public void loadedWords(List<Word> words) {

        this.words = words;
        button.setEnabled(true);

    }

    @Override
    public void startGame() {

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("words", (Serializable) words);
        startActivityForResult(intent,REQUEST_RESULTS);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateResult(final int correct, final int wrong, final int noAnswer) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                correctView.setText("Correct Answer : "+correct);
                wrongView.setText("Wrong Answer : "+wrong);
                noAnswerView.setText("UnAnswered : "+noAnswer);
                button.setText("Start Again!");

                centre.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_RESULTS){
            if(resultCode == Activity.RESULT_OK){
                if(data!=null){
                    splashPresenter.onActivityResult(data);
                }
            }
        }
    }
}
