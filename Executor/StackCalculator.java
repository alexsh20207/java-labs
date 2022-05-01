package rus.nsu.fit.oop.lab2.Executor;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Exceptions.BuildCommandException;
import rus.nsu.fit.oop.lab2.Exceptions.CommandNotFoundException;
import rus.nsu.fit.oop.lab2.Factory.CommandFactory;
import rus.nsu.fit.oop.lab2.Validator.Validator;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class StackCalculator {
    private BufferedReader reader = null;
    public StackCalculator(String inputStreamName) throws FileNotFoundException {
        if (inputStreamName == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        } else {
            reader = new BufferedReader(new FileReader(inputStreamName));
        }
    }

    public void calculate() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String line;
        ExecutionContext context = new ExecutionContext();
        List<String> inputArgs;
        try {
            while (((line = reader.readLine()) != null)) {
                if (line.startsWith("#")) continue;
                String[] commandLine = line.split("\\s+");
                ExecCommand nextCommand = CommandFactory.getInstance().buildCommand(commandLine[0]);
                inputArgs = Arrays.asList(commandLine).subList(1, commandLine.length);
                if (Validator.getInstance().validate(nextCommand, context, inputArgs)) {
                    nextCommand.execute(context, inputArgs);
                } else {
                    System.err.println(Validator.getInstance().getErrMsg());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CommandNotFoundException e) {
            e.printStackTrace();
        } catch (BuildCommandException e) {
            e.printStackTrace();
        }
    }
}
