package lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Writer {
    static private final int extraUnit = 1;
    static private final int forInit = 0;
    static private final int oneHundred = 100;

    private Container wordCont;
    private FileWriter fileWriter;

    public Writer(String fileName, Container container) throws IOException {
            fileWriter = new FileWriter(fileName, false);
            wordCont = container;
    }

    public void work() throws IOException{
        List<Map.Entry<String, Integer>> words = wordCont.sort();
        int wordCount = wordCont.getWordCount();
        for (int i = words.size() - extraUnit; i >= forInit; i--) {
            String wordcount = words.get(i).getValue().toString();
            String freq = ((Double)(((double)words.get(i).getValue() / wordCount) * oneHundred)).toString();
            fileWriter.write(words.get(i).getKey() + ";" + wordCount + ";" + freq + '\n');
        }
    }
    public void close() throws IOException {
        fileWriter.close();
    }
}
