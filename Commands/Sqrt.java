package rus.nsu.fit.oop.lab2.Commands;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;
import java.util.List;

public class Sqrt extends ExecCommand {
    @Override
    public void execute(ExecutionContext context, List<String> inputArgs) {
        double a = context.getStack().pop();
        if (a < 0) {
            System.err.println("Extracting the root of a negative number: " + a);
            return;
        }
        context.getStack().push(Math.sqrt(a));
    }
}
