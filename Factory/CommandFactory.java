package rus.nsu.fit.oop.lab2.Factory;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Exceptions.BuildCommandException;
import rus.nsu.fit.oop.lab2.Exceptions.CommandNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class CommandFactory {
    private static Properties commandsConfig = new Properties();
    private static CommandFactory instance = null;
    private CommandFactory() {
        try {
            commandsConfig.load(CommandFactory.class.getResourceAsStream("conf_file.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public ExecCommand buildCommand(String commandName) throws CommandNotFoundException, BuildCommandException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!commandsConfig.containsKey(commandName)) {
            throw new CommandNotFoundException("Command " + commandName + " - does not exist");
        }
        return (ExecCommand) Class.forName(commandsConfig.getProperty(commandName)).getDeclaredConstructor().newInstance();
    }
}
