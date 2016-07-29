package words.com.flower.ui.gamescene;

import android.animation.Animator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import words.com.flower.BaseActivity;
import words.com.flower.R;
import words.com.flower.data.Word;
import words.com.flower.utils.Constants;

/**
 * An Game Scene to show random words
 *
 */
public class GameActivity extends BaseActivity implements GameView {

    @BindView(R.id.correct)
    FloatingActionButton correctBtn;
    @BindView(R.id.wrong)
    FloatingActionButton wrongBtn;
    @BindView(R.id.score)
    TextView score;

    @OnClick(R.id.correct)
    public void correctClicked(View view){
        scenePresenter.onCorrectClick();
    }

    @OnClick(R.id.wrong)
    public void wrongClicked(View view){
        scenePresenter.onWrongClick();
    }

    @BindView(R.id.textSwitcher)
    TextSwitcher textSwitcher;

    @BindView(R.id.textView)
    TextView answer;

    @BindView(R.id.main_frame)
    FrameLayout frameLayout;

    ScenePresenter scenePresenter;

    List<Word> wordList;

    List<Word> correctAnswered = new ArrayList<>();
    List<Word> wrongAnswered =  new ArrayList<>();
    List<Word> noAnswered =  new ArrayList<>();

    Word currentWord;
    int currentIndex;
    int currentScore = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wordList = (ArrayList<Word>) getIntent().getSerializableExtra("words");
        scenePresenter = new ScenePresenterImp(this,wordList);

        viewBottomControls();
        textFactory();




    }

    @Override
    public int getLayout() {
        return R.layout.activity_game;
    }


    /**
     *
     * Handle the animations to show the bottom control buttons.
     *
     */

    private void viewBottomControls(){

        correctBtn.animate().alpha(1).setDuration(Constants.NORMAL_DURATON).start();
        wrongBtn.animate().alpha(1).setDuration(Constants.NORMAL_DURATON).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                score.animate().alpha(1).setDuration(Constants.NORMAL_DURATON).start();

                scenePresenter.startGame(); // Starting the game presenter will interact with the data generation
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).start();


    }

//    private void animateLeft(){
//        DeviceUtils deviceUtils = new DeviceUtils(this);
//        final AnimationSet rollingIn = new AnimationSet(true);
//        Animation moving = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
//        moving.setDuration(1000);
//        moving.setFillAfter(true);
//        //ObjectAnimator objectAnimator = ObjectAnimator.ofObject(correctBtn,"translationX", correctBtn.getMeasuredWidth(),deviceUtils.getDeviceWidth()/2);
//        final Animation rotating = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotating.setDuration(1000);
//
//        rollingIn.setFillAfter(true);
//        rollingIn.addAnimation(rotating);
//        rollingIn.addAnimation(moving);
//        rollingIn.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                wrongBtn.setVisibility(View.VISIBLE);
//                animateRight();
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//                //remove the animation, its no longer needed, since button is really there
//                wrongBtn.clearAnimation();
//
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        wrongBtn.setAnimation(rollingIn);
//        wrongBtn.animate();
//    }
//
//    private void animateRight(){
//        DeviceUtils deviceUtils = new DeviceUtils(this);
//        final AnimationSet rollingIn = new AnimationSet(true);
//        Animation moving = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
//        moving.setDuration(1000);
//        moving.setFillAfter(true);
//        //ObjectAnimator objectAnimator = ObjectAnimator.ofObject(correctBtn,"translationX", correctBtn.getMeasuredWidth(),deviceUtils.getDeviceWidth()/2);
//        final Animation rotating = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotating.setDuration(1000);
//
//        rollingIn.setFillAfter(true);
//        rollingIn.addAnimation(rotating);
//        rollingIn.addAnimation(moving);
//        rollingIn.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                correctBtn.setVisibility(View.VISIBLE);
//                scenePresenter.startGame();
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//                scenePresenter.startGame();
//                correctBtn.clearAnimation();
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        correctBtn.setAnimation(rollingIn);
//        correctBtn.animate();
//    }


    /**
     *
     * Text Factory to setup textSwitcher
     *
     */
    private void textFactory(){

        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView myText = new TextView(GameActivity.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(36);
                myText.setTextColor(getResources().getColor(R.color.colorPrimary));
                return myText;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);

    }


    /**
     *
     * Call back from the gameLogic layer with the word to show on the screen
     * if shuffle is true then we switch b/w the answer to confuse the user
     *
     *
     * @param word
     * @param index
     * @param shuffle
     */

    @Override
    public void GeneratedWord(final Word word, int index, final boolean shuffle) {
        //Log.d("generated word at index"+index,word.getEngString());
        currentWord = word;
        currentIndex = index;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textSwitcher.setText(word.getEngString());
                if(shuffle){
                    setupAnswerView(word.getWrongMeaning());
                }else {
                    setupAnswerView(word.getSpanString());
                }
                correctBtn.setClickable(true);
                wrongBtn.setClickable(true);
            }
        });

    }

    @Override
    public void startGame() {

    }


    /**
     *
     * setup result to the Splash Screen
     *
     */

    @Override
    public void endGame() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("correct",correctAnswered.size());
        returnIntent.putExtra("wrong",wrongAnswered.size());
        returnIntent.putExtra("nothing",noAnswered.size());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }

    @Override
    public void updateScore(boolean update) {
        if(update){
            currentScore++;
            score.setText("Score "+currentScore);
        }else {
            if(currentScore > 0){
                currentScore--;
                score.setText("Score "+currentScore);
            }
        }

    }

    @Override
    public void showEndAlert() {
        scenePresenter.stopGame();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Game Exit");
        alertDialog.setMessage("Are you sure you want to exit Game ? ");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                scenePresenter.endGame();
            }
        });
        alertDialog.create();
        alertDialog.show();

    }

    /**
     *
     * Handling the correct answer button
     *
     */


    @Override
    public void correctPressed() {
        if(answer.getText().toString().equalsIgnoreCase(currentWord.getSpanString())){
            correctAnswered.add(currentWord);
            scenePresenter.updateScore(true);
        }else {
            wrongAnswered.add(currentWord);

        }
        correctBtn.setClickable(false);
        wrongBtn.setClickable(false);

    }

    /**
     *
     * Handling the wrong answer button
     *
     */

    @Override
    public void wrongPressed() {
        if(answer.getText().toString().equalsIgnoreCase(currentWord.getWrongMeaning())){
            correctAnswered.add(currentWord);
            scenePresenter.updateScore(true);
        }else {
            wrongAnswered.add(currentWord);
        }
            correctBtn.setClickable(false);
            wrongBtn.setClickable(false);


    }

    private void setupAnswerView(final String answerText){
        Animation animation = new TranslateAnimation(0,0,0, frameLayout.getMeasuredHeight());
        animation.setDuration(Constants.PEROID);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                answer.setVisibility(View.VISIBLE);
                answer.setText(answerText);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(answer.getVisibility() == View.VISIBLE){
                    answer.setVisibility(View.INVISIBLE);
                }

                /*
                Logic to handle the unAnswered questions and tracking them
                 */

                if(correctBtn.isClickable() && wrongBtn.isClickable()){
                    noAnswered.add(currentWord);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        answer.setAnimation(animation);
        answer.animate();


    }

    @Override
    public void onBackPressed() {
        scenePresenter.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scenePresenter.stopGame();
    }


}
