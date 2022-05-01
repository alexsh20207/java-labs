package rus.nsu.fit.oop.lab2.Tests;

import org.junit.jupiter.api.Test;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Commands.Print;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;

import static org.junit.jupiter.api.Assertions.*;

class PrintTest {
    @Test
    void execute() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(1.);
        context.getStack().push(2.);
        ExecCommand print = new Print();
        print.execute(context, null);
        assertEquals(2., context.getStack().peek(), 0.0);
    }
}