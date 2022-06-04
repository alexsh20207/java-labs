package rus.nsu.fit.oop.lab2.Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import rus.nsu.fit.oop.lab2.Commands.Sqrt;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;

class SqrtTest {
    @Test
    void execute() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(0.81);
        ExecCommand sqrt = new Sqrt();
        sqrt.execute(context,null);
        assertEquals(0.9, context.getStack().peek(), 0.0);
    }
}