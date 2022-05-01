package ru.nsu.oop.shtang.lab1;
import java.io.IOException;

public class Main {
    static private final int ARG_COUNT = 2;
    static private final String ARG_ERR = "Wrong args";
    static private final int INPUT_ARG = 0;
    static private final int OUTPUT_ARG = 1;
    static private final int WORD_COUNT_INIT = 0;
    static private final String EMPTY_ERR = "Input is empty";

    public static void main(String[] args) throws IOException {
        if (args.length != ARG_COUNT) {
            System.out.print(ARG_ERR);
            return;
        }
        Reader reader = null;
        Writer writer = null;
        Container container = null;
        Parser parser = null;
        try {
            reader = new Reader(args[INPUT_ARG]);
            container = new Container();
            parser = new Parser(reader, container);
            parser.work();
            if (container.getWordCount() == WORD_COUNT_INIT) {
                System.out.print(EMPTY_ERR);
                return;
            }
            writer = new Writer(args[OUTPUT_ARG], container);
            writer.work();
        } catch (IOException err) {
            System.out.print(err.getMessage());
            err.printStackTrace();
            return;
        } finally  {
        if (reader != null) {
            reader.close();
        }
        if (writer != null) {
            writer.close();
        }
    }

    }
}
