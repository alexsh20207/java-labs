package ru.nsu.oop.shtang.lab1;

import javafx.util.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {
    static private final int EXTRA_UNIT = 1;
    static private final int FOR_LIMIT = 0;
    static private final int ONE_HUNDRED = 100;
    static private final char COMMA = ';';
    static private final char NEW_LINE = '\n';
    private Container wordCont;
    private FileWriter fileWriter;

    public Writer(String fileName, Container container) throws IOException {
            fileWriter = new FileWriter(fileName, false);
            wordCont = container;
    }

    public void work() throws IOException{
        List<Pair<String, Integer>> words = wordCont.sort();
        int wordCount = wordCont.getWordCount();
        for (int i = words.size() - EXTRA_UNIT; i >= FOR_LIMIT; i--) {
            String thisWordCount = words.get(i).getValue().toString();
            String freq = ((Double)(((double)words.get(i).getValue() / wordCount) * ONE_HUNDRED)).toString();
            fileWriter.write(words.get(i).getKey() + COMMA + thisWordCount + COMMA + freq + NEW_LINE);
        }
    }
    public void close() throws IOException {
        fileWriter.close();
    }
}
