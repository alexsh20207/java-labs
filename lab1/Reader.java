package ru.nsu.oop.shtang.lab1;
import java.io.*;
import java.lang.StringBuilder;

public class Reader {
    private InputStreamReader reader;

    public Reader(String FileName) throws IOException {
        reader = new InputStreamReader(new FileInputStream(FileName));
    }

    public int getSym() throws IOException {
        return reader.read();
    }

    public void close() throws IOException {
        reader.close();
    }
}
