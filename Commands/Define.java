package rus.nsu.fit.oop.lab2.Commands;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;
import java.util.List;

public class Define extends ExecCommand {

    @Override
    public void execute(ExecutionContext context, List<String> inputArgs) {
        try {
            context.getDefines().put(inputArgs.get(0), Double.valueOf(inputArgs.get(1)));
        } catch (NumberFormatException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
