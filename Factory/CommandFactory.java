package rus.nsu.fit.oop.lab2.Factory;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Exceptions.CommandNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import static rus.nsu.fit.oop.lab2.Const.*;

public class CommandFactory {
    private static Properties commandsConfig = null;

    public CommandFactory() {
        commandsConfig = new Properties();
        try {
            commandsConfig.load(CommandFactory.class.getResourceAsStream(CONFIG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExecCommand buildCommand(String commandName) throws CommandNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!commandsConfig.containsKey(commandName)) {
            throw new CommandNotFoundException(commandName + NOT_FOUND_CMD_ERR_MSG);
        }
        return (ExecCommand)Class.forName(commandsConfig.getProperty(commandName)).getDeclaredConstructor().newInstance();
    }
}
