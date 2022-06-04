package rus.nsu.fit.oop.lab2;
import rus.nsu.fit.oop.lab2.Executor.StackCalculator;
import java.io.FileNotFoundException;

import static rus.nsu.fit.oop.lab2.Const.*;

public class Main {
    public static void main(String[] args) {
        String stackCalculatorArg = null;
        if (args.length > NULL_STRING_LENGTH) stackCalculatorArg = args[FIRST_ARG];
        try {
            StackCalculator stackCalculator = new StackCalculator(stackCalculatorArg);
            stackCalculator.calculate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
