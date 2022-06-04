package rus.nsu.fit.oop.lab2.Validator;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static rus.nsu.fit.oop.lab2.Const.*;

public class Validator {
    private String errMessage;
    private final Properties contextProperties = new Properties();
    private final Properties inputArgumentsProperties = new Properties();
    private ExecCommand execCommand;
    private ExecutionContext context;
    private List<String> inputArgs;


    public Validator(ExecCommand execCommand,ExecutionContext context, List<String> inputArgs) {
        this.execCommand = execCommand;
        this.context = context;
        this.inputArgs = inputArgs;
        try {
            contextProperties.load(Validator.class.getResourceAsStream(CONTEXT_FILE));
            inputArgumentsProperties.load(Validator.class.getResourceAsStream(INPUT_ARG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validate() {
        String curCommandName = execCommand.getClass().getName();
        int requiredArgAmount = Integer.parseInt(inputArgumentsProperties.getProperty(curCommandName));
        if (requiredArgAmount == COMMENT_ARG_AMOUNT) {
            return true;
        }
        int requiredSize = Integer.parseInt(contextProperties.getProperty(curCommandName));
        if (context.getStack().size() < requiredSize) {
            errMessage = curCommandName + STACK_ERR_MSG;
            return false;
        } else if (inputArgs.size() != requiredArgAmount) {
            errMessage = curCommandName + AMOUNT_ARG_ERR_MSG;
            return false;
        }
        return true;
    }

    public String getErrMsg() {
        return errMessage;
    }
}
