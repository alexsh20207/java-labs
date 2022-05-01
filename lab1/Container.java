package ru.nsu.oop.shtang.lab1;

import javafx.util.Pair;

import java.util.*;

public class Container {
    static private final int ALL_WORDS_COUNT_INIT = 0;
    static private final int WORD_COUNT_INIT = 1;
    static private final int COUNT_STEP = 1;

    private HashMap <String, Integer> words;
    private int wordCount;

    public Container() {
        words = new HashMap<String, Integer>();
        wordCount = ALL_WORDS_COUNT_INIT;
    }
    public void addWord(String word) {
        if (words.containsKey(word)) words.put(word, words.get(word) + COUNT_STEP);
        else {
            words.put(word, WORD_COUNT_INIT);
        }
        wordCount++;
    }

    public int getWordCount() {
        return wordCount;
    }

    public List<Pair<String, Integer>> sort() {
        List<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>();
        for (String key: words.keySet()) {
            list.add(new Pair<String, Integer>(key, words.get(key)));
        }
        list.sort(new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            };
        });
        return list;

    }

    public int getCount(String word) {
        return words.get(word);
    }
}
