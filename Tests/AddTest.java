package rus.nsu.fit.oop.lab2.Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import rus.nsu.fit.oop.lab2.Commands.Add;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;

class AddTest {

    @Test
    void execute() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(5.0);
        context.getStack().push(89.2);
        ExecCommand add = new Add();
        add.execute(context, null);
        assertEquals(94.2, context.getStack().peek(), 0.0);
    }
}