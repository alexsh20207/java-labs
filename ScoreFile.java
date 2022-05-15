package rus.nsu.fit.oop.ms;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static rus.nsu.fit.oop.ms.Const.*;

public class ScoreFile {
    static private final String PATH = "src/main/java/resources/hs.txt";
    File file;
    String scoreStr;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    ArrayList<Integer> list;

    public void write(int newScore) throws IOException {
        file = new File(PATH);
        if (file.exists() == false) {
            file.createNewFile();
        }
        scoreStr = Integer.toString(newScore) + NEW_LINE;
        fileWriter = new FileWriter(file, true);
        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(scoreStr);
        bufferedWriter.close();
        fileWriter.close();
        sort();

    }
    public void sort() throws IOException {
        bufferedReader = new BufferedReader(new FileReader(PATH));
        String line;
        list = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null ){
            if(!line.isEmpty()){
                list.add(Integer.parseInt(line));
                System.out.println(line);
            }}
        Collections.sort(list, Collections.reverseOrder());
        fileWriter = new FileWriter(PATH);
        for(int i = 0; i < list.size(); i++){
            fileWriter.write(Integer.toString(list.get(i)) + NEW_LINE);
        }
        bufferedReader.close();
        fileWriter.close();

    }
}
