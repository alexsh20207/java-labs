package rus.nsu.fit.oop.lab2.Commands;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;
import java.util.List;

import static rus.nsu.fit.oop.lab2.Const.SQRT_MSG_ERR;

public class Sqrt extends ExecCommand {

    @Override
    public void execute(ExecutionContext context, List<String> inputArgs) {
        double a = context.getStack().pop();
        if (a < 0) {
            System.err.println(SQRT_MSG_ERR + a);
            return;
        }
        context.getStack().push(Math.sqrt(a));
    }
}
