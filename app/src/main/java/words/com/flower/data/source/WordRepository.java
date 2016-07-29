package words.com.flower.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import words.com.flower.data.Word;

/**
 * Repo for the data to
 *
 * it can be extend to use get remote data.
 *
 * <p/>
 * Created by malikumarbhutta on 7/27/16.
 */
public class WordRepository implements WordDataSource {

    private static WordRepository instance = null;
    private Context context;

    private WordRepository(Context context) {
        this.context = context;
    }

    public static WordRepository getInstance(Context context) {
        if (instance == null) {
            instance = new WordRepository(context);
        }
        return instance;
    }


    @Override
    public void getWords(@NonNull LoadCallBack callBack) {
        List<Word> words = loadWords();
        if (words.isEmpty()) {
            callBack.onParsingError();
        } else {
            callBack.onParsingComplete(words);
        }

    }

    @Override
    public void getWord(@NonNull String id, @NonNull LoadCallBack callBack) {

    }

    @Override
    public void updateWord(@NonNull String id, Word word) {

    }

    @Override
    public void resetWord(@NonNull String id) {

    }

    /**
     * Force to clean the list
     */
    @Override
    public void resetWords() {
        instance = null;
    }

    /**
     * Parser for the json and return the parsed json into list
     *
     * @return words list after parsing
     */

    private List<Word> loadWords() {
        List<Word> words = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject wordObject = jsonArray.optJSONObject(i);
                Word word = new Word(wordObject.optString("text_eng"), wordObject.optString("text_spa"));
                words.add(word);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (words.isEmpty()) return Collections.EMPTY_LIST;
        return words;
    }


    /**
     * Reads the file contains the json and form a json string
     *
     * @return String
     */
    private String loadJSONFromAsset() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = context.getAssets().open("words.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
