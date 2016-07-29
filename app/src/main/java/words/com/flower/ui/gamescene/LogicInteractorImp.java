package words.com.flower.ui.gamescene;

import java.util.List;

import words.com.flower.data.Word;
import words.com.flower.gamelogic.DataGenerator;

/**
 * Created by malikumarbhutta on 7/28/16.
 */
public class LogicInteractorImp implements LogicInteractor,DataGenerator.Publisher{



    private List<Word> words;
    private DataGenerator dataGenerator;
    private PublishToScreen publishToScreen;


    public LogicInteractorImp(List<Word> words,PublishToScreen publishToScreen) {
        this.words = words;
        this.publishToScreen = publishToScreen;
        dataGenerator = new DataGenerator(this.words,this);
    }

    @Override
    public void startGeneration() {
        dataGenerator.startGeneration();
    }

    @Override
    public void stopGeneration() {
        dataGenerator.CancelGeneration();
    }

    @Override
    public void publishWord(Word word, int index,boolean wrong) {
        publishToScreen.publish(word,index,wrong);
    }

    public interface PublishToScreen{
        void publish(Word word,int index,boolean wrong);
    }
}
