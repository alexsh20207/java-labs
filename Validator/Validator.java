package rus.nsu.fit.oop.lab2.Validator;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Validator {
    private static Validator instance = null;
    private String errMessage;
    private static final Properties contextProperties = new Properties();
    private static final Properties inputArgumentsProperties = new Properties();

    private Validator() {
        try {
            contextProperties.load(Validator.class.getResourceAsStream("context.properties"));
            inputArgumentsProperties.load(Validator.class.getResourceAsStream("inputArgs.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Validator getInstance() {
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }

    public boolean validate(ExecCommand command, ExecutionContext context, List<String> inputArgs) {
        String curCommandName = command.getClass().getName();
        if (context.getStack().size() < Integer.parseInt(contextProperties.getProperty(curCommandName))) {
            errMessage = curCommandName + ": Not enough elements in stack for this command";
            return false;
        } else if (inputArgs.size() != Integer.parseInt(inputArgumentsProperties.getProperty(curCommandName))) {
            errMessage = curCommandName + ": Wrong count of input arguments";
            return false;
        }
        return true;
    }

    public String getErrMsg() {
        return errMessage;
    }
}
