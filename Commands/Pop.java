package rus.nsu.fit.oop.lab2.Commands;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;
import java.util.List;

public class Pop extends ExecCommand {


    @Override
    public void execute(ExecutionContext context, List<String> inputArgs) {
        context.getStack().pop();
    }
}
