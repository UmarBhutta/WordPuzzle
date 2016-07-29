package words.com.flower.gamelogic;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import words.com.flower.data.Word;
import words.com.flower.utils.Constants;

/**
 * Created by malikumarbhutta on 7/28/16.
 *
 * Random scheduled word generator from the list of words,
 *
 */
public class DataGenerator {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private List<Word> words;
    private ScheduledFuture wordGenerator;
    private boolean automated;
    private Publisher publisher;
    private Timer timer;
    private boolean scheduled = false;



    public DataGenerator(List<Word> words,Publisher publisher) {
        this.words = words;
        this.automated = true;
        this.publisher = publisher;
        this.timer = new Timer();

    }

    /**
     *
     * Method to start generation.
     *
     */
    public void startGeneration(){

       // wordGenerator = scheduler.scheduleAtFixedRate(runnable, Constants.PEROID,Constants.PEROID, TimeUnit.SECONDS);
       if(scheduled){

       }else {
           timer.scheduleAtFixedRate(timerTask,3000, Constants.PEROID);
           scheduled = true;
       }


    }




    /**
     *
     * Scheduled runner to generate words at fix time period
     *
     */
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (automated){
                int index = randomIndex();
                if(index<words.size()){
                    Word word = words.get(index);
                    Word forWrong  = words.get(randomIndex());
                    word.setWrongMeaning(forWrong.getSpanString());
                    boolean wrongShow = index % 2 == 0;
                    publisher.publishWord(word,index,wrongShow);
                }

            }
        }
    };

    /**
     *
     * Generate Random index from 0 to wordList Size
     *
     * @return index
     */
    private int randomIndex(){
        Random rand = new Random();
        int minimum = 0;
        int maximum = words.size();
        int index =  minimum + rand.nextInt((maximum - minimum) + 1);
        return index;
    }

    /**
     * Manual call for the word.
     * @return Word
     */
    public Word getWord(){
        return words.get(randomIndex());
    }

    /**
     *
     * Cancel the Executor that generating the random words
     *
     */
    public void CancelGeneration(){
//        wordGenerator.cancel(true);
//        wordGenerator = null;
        timer.cancel();
        scheduled = false;
    }

    public boolean isAutomated() {
        return automated;
    }

    public void setAutomated(boolean automated) {
        this.automated = automated;
    }

    public interface Publisher{
        void publishWord(Word word,int index,boolean wrong);
    }
}
