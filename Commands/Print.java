package rus.nsu.fit.oop.lab2.Commands;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;
import java.util.List;

public class Print extends ExecCommand {


    @Override
    public void execute(ExecutionContext context, List<String> inputArgs) {
        System.out.println(context.getStack().peek());
    }
}
