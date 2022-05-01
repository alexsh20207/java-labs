package rus.nsu.fit.oop.lab2.Commands;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;
import java.util.List;

public abstract class ExecCommand {
    public abstract void execute(ExecutionContext context, List<String> inputArgs);
}
