package words.com.flower.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import words.com.flower.data.Word;

/**
 *
 * Datasource delegate with call backs to handle the local and remote data requests
 *
 * Created by malikumarbhutta on 7/27/16.
 */
public interface WordDataSource {

    interface LoadCallBack{

        void onParsingComplete(List<Word> words);
        void onParsingError();
    }

    void getWords(@NonNull LoadCallBack callBack);

    void getWord(@NonNull String id, @NonNull LoadCallBack callBack);

    void updateWord(@NonNull String id,Word word);
    void resetWord(@NonNull String id);
    void resetWords();


}
