package words.com.flower.ui.splash;

import android.content.Context;

import java.util.List;

import words.com.flower.data.Word;
import words.com.flower.data.source.WordDataSource;
import words.com.flower.data.source.WordRepository;

/**
 * Created by malikumarbhutta on 7/27/16.
 */
public class DataInterActorImp implements DataInteractor {

    Context context;
    DataInteractorListener dataInteractorListener;
    WordRepository repository;


    public DataInterActorImp(Context context, DataInteractorListener dataInteractorListener) {
        this.context = context;
        this.dataInteractorListener = dataInteractorListener;
        repository = WordRepository.getInstance(context);

    }

    @Override
    public void dataParsing() {

        repository.getWords(new WordDataSource.LoadCallBack() {
            @Override
            public void onParsingComplete(List<Word> words) {
                dataInteractorListener.onDataParseSuccessful(words);
            }

            @Override
            public void onParsingError() {
                dataInteractorListener.onDataParsingError();
            }
        });


    }


    public interface DataInteractorListener{
        void onDataParseSuccessful(List<Word> words);
        void onDataParsingError();
    }


}
