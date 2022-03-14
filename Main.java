import lab1.Container;
import lab1.Parser;
import lab1.Reader;
import lab1.Writer;

import java.io.IOException;

public class Main {
    static private final int ARG_COUNT = 2;
    static private final String ARG_ERR = "Wrong args";
    static private final int INPUT_ART = 0;
    static private final int OUTPUT_ARG = 1;
    static private final int WORD_COUNT_INIT = 0;
    static private final String EMPTY_ERR = "Input is empty";

    public static void main(String[] args) {
        if (args.length != ARG_COUNT) {
            System.out.print(ARG_ERR);
            return;
        }
        try {
            Reader reader = new Reader(args[INPUT_ARG]);
            Container container = new Container();
            Parser parser = new Parser(reader, container, WORD_COUNT_INIT);
            parser.work();
            if (container.getWordCount() == WORD_COUNT_INIT) {
                System.out.print(EMPTY_ERR);
                return;
            }
            Writer writer = new Writer(args[OUTPUT_ARG], container);
            writer.work();
            writer.close();
            reader.close();
        } catch (IOException err) {
            System.out.print(err.getMessage());
            err.printStackTrace();
            return;
        }
    }
}