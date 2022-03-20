package ru.nsu.oop.shtang.lab1;
import java.io.IOException;

public class Parser {
    static private final int BAD_VALUE = -1;
    static private final String EMPTY_STRING = "";
    static private final int WORD_LENGTH_INIT = 0;

    private Reader reader;
    private Container container;

    public Parser(Reader reader, Container container) {
        this.reader = reader;
        this.container = container;
    }

    public void work() throws IOException {
        StringBuilder word = new StringBuilder();
        while (true) {
            int sym;
            sym = reader.getSym();
            if (sym != BAD_VALUE) {
                if (Character.isLetterOrDigit((char)sym)) {
                    word.append((char)sym);
                }
                else if (word.toString() != EMPTY_STRING){
                    container.addWord(word.toString());
                    word.delete(WORD_LENGTH_INIT, word.length());
                }
            } else {
                if  ((word.toString() != EMPTY_STRING)) {
                    container.addWord(word.toString());
                }
                break;
            }
        }
    }
}
