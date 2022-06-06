package rus.nsu.fit.oop.ms;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static rus.nsu.fit.oop.ms.Const.*;

public class ScoreFile {

    private final File file;
    private FileWriter fileWriter;

    public ScoreFile() throws IOException {
        file = new File(PATH_FILE_HS);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void write(int newScore) throws IOException {
        String scoreStr = newScore + NEW_LINE;
        fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(scoreStr);
        bufferedWriter.close();
        fileWriter.close();
        sort();

    }
    public void sort() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_FILE_HS));
        String line;
        ArrayList<Integer> list = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null ){
            if (!line.isEmpty()){
                list.add(Integer.parseInt(line));
            }}
        list.sort(Collections.reverseOrder());
        fileWriter = new FileWriter(PATH_FILE_HS);
        for (Integer integer : list) {
            fileWriter.write(integer + NEW_LINE);
        }
        bufferedReader.close();
        fileWriter.close();

    }
}
