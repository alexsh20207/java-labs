package rus.nsu.fit.oop.lab2.Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import rus.nsu.fit.oop.lab2.Commands.Mult;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;

class MultTest {
    @Test
    void execute() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(2.);
        context.getStack().push(-3.);
        ExecCommand multiplication = new Mult();
        multiplication.execute(context,null);
        assertEquals(-6., context.getStack().peek(), 0.0);
    }
}