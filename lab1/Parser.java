package lab1;

import java.io.IOException;

public class Parser {
    static private final int badValue = -1;

    private Reader reader;
    private Container container;

<<<<<<< HEAD
    public Parser(Reader reader, Container container, int wordCount) {
=======
    public Parser(Reader reader, Container container) {
>>>>>>> d63d4e49efbec1221f18fb3faadf30629a9afabb
        this.reader = reader;
        this.container = container;
    }

    public void work() {
        StringBuilder word = new StringBuilder();
        while (true) {
            int sym;
            try {
                sym = reader.getSym();
            }
            catch (IOException err) {
                System.out.print(err.getMessage());
                err.printStackTrace();
                return;
            }
            if (sym != badValue) {
                if (Character.isLetterOrDigit((char)sym)) {
                    word.append((char)sym);
                }
                else {
                    container.addWord(word.toString());
                    word.delete(0, word.length());
                }
            } else {
                if (!word.isEmpty()) {
                    container.addWord(word.toString());
                }
                break;
            }
        }
    }
}
