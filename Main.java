import lab1.Container;
import lab1.Parser;
import lab1.Reader;
import lab1.Writer;

import java.io.IOException;

public class Main {
    static private final int argCount = 2;
    static private final String argErr = "Wrong args";
    static private final int inputArg = 0;
    static private final int outputArg = 1;
<<<<<<< HEAD
    static private final int wordCountInit = 0;
=======
>>>>>>> d63d4e49efbec1221f18fb3faadf30629a9afabb

    public static void main(String[] args) {
        if (args.length != argCount) {
            System.out.print(argErr);
            return;
        }
        try {
            Reader reader = new Reader(args[inputArg]);
            Container container = new Container();
<<<<<<< HEAD
            Parser parser = new Parser(reader, container, wordCountInit);
=======
            Parser parser = new Parser(reader, container);
>>>>>>> d63d4e49efbec1221f18fb3faadf30629a9afabb
            parser.work();
            Writer writer = new Writer(args[outputArg], container);
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