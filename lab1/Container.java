package lab1;
import java.util.*;

public class Container {
    static private final int allWordsCountInit = 0;
    static private final int wordCountInit = 1;
    static private final int countStep = 1;

    private HashMap <String, Integer> words;
    private int wordCount;

    public Container() {
        words = new HashMap<String, Integer>();
        wordCount = allWordsCountInit;
    }
    public void addWord(String word) {
        if (words.containsKey(word)) words.put(word, words.get(word) + countStep);
        else {
            words.put(word, wordCountInit);
        }
        wordCount++;
    }

    public int getWordCount() {
        return wordCount;
    }

    public List<Map.Entry<String, Integer>> sort() {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(words.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list;
    }

    public int getCount(String word) {
        return words.get(word);
    }
}