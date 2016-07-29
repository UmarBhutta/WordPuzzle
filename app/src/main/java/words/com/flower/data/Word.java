package words.com.flower.data;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * Model for the words
 *
 * Created by malikumarbhutta on 7/27/16.
 */
public class Word implements Serializable {

    private String id;

    private String engString;  // english String

    private String spanString;  // spanish String

    private boolean answer;     // track that user answered or not?

    private boolean correct;   // Track Record that user mark right or wrong the answer

    private String wrongMeaning; // an wrong meaning that will be generated from the data

    public Word() {
    }

    /**
     *
     * Constructor to create new word structures
     * @param engString
     * @param spanString
     */
    public Word(String engString, String spanString) {
        id = UUID.randomUUID().toString();
        this.engString = engString;
        this.spanString = spanString;
        wrongMeaning = ""; // to avoid null pointere exception
        answer = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEngString() {
        return engString;
    }

    public void setEngString(String engString) {
        this.engString = engString;
    }

    public String getSpanString() {
        return spanString;
    }

    public void setSpanString(String spanString) {
        this.spanString = spanString;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getWrongMeaning() {
        return wrongMeaning;
    }

    public void setWrongMeaning(String wrongMeaning) {
        this.wrongMeaning = wrongMeaning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (!engString.equals(word.engString)) return false;
        return spanString.equals(word.spanString);

    }

    @Override
    public int hashCode() {
        int result = engString.hashCode();
        result = 31 * result + spanString.hashCode();
        return result;
    }
}
