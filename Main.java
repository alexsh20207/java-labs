package rus.nsu.fit.oop.lab2;
import rus.nsu.fit.oop.lab2.Executor.StackCalculator;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String stackCalulatorArg = null;
        if (args.length > 0) stackCalulatorArg = args[0];
        try {
            StackCalculator stackCalculator = new StackCalculator(stackCalulatorArg);
            stackCalculator.calculate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}