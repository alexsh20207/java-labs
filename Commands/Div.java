package rus.nsu.fit.oop.lab2.Commands;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;
import java.util.List;

public class Div extends ExecCommand {
    @Override
    public void execute(ExecutionContext context, List<String> inputArgs) {
        double a = context.getStack().pop();
        double b = context.getStack().pop();
        if (b == 0) {
            System.err.println("Division by zero!");
            return;
        }
        context.getStack().push(a / b);
    }
}
