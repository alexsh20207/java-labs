package rus.nsu.fit.oop.lab2.Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import rus.nsu.fit.oop.lab2.Commands.Div;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;

class DivTest {
    @Test
    void execute() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-2.0);
        context.getStack().push(5.0);
        ExecCommand div = new Div();
        div.execute(context, null);
        assertEquals(-2.5, context.getStack().peek(), 0.0);
    }
}