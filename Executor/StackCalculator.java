package rus.nsu.fit.oop.lab2.Executor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Exceptions.CommandNotFoundException;
import rus.nsu.fit.oop.lab2.Factory.CommandFactory;
import rus.nsu.fit.oop.lab2.Validator.Validator;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static rus.nsu.fit.oop.lab2.Const.*;

public class StackCalculator {
    private final BufferedReader reader;

    public StackCalculator(String inputStreamName) throws FileNotFoundException {
        if (inputStreamName == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        } else {
            reader = new BufferedReader(new FileReader(inputStreamName));
        }
    }

    public void calculate() {
        ExecutionContext context = new ExecutionContext();
        String line;
        try {
            while (((line = reader.readLine()) != null)) {
                List<String> inputArgs;
                String[] commandLine = line.split(SPACES);

                ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext(XML_APP_CONF_FILE);
                CommandFactory commandFactory = xmlApplicationContext.getBean(BEAN_FABRIC_NAME, CommandFactory.class);
                ExecCommand execCommand = commandFactory.buildCommand(commandLine[FIRST_ARG]);

                inputArgs = Arrays.asList(commandLine).subList(SECOND_ARG, commandLine.length);
                Validator validator = new Validator(execCommand, context, inputArgs);

                if (validator.validate()) {
                    execCommand.execute(context, inputArgs);
                } else {
                    System.err.println(validator.getErrMsg());
                }
            }
        } catch (IOException | CommandNotFoundException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
