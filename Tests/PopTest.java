package rus.nsu.fit.oop.lab2.Tests;

import org.junit.jupiter.api.Test;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Commands.Pop;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;

import static org.junit.jupiter.api.Assertions.*;

class PopTest {
    @Test
    void execute() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(1.);
        ExecCommand pop = new Pop();
        pop.execute(context,null);
        assertEquals(context.getStack().size(), 0);

        context.getStack().push(5.);
        context.getStack().push(7.2);
        pop.execute(context,null);
        assertEquals(context.getStack().peek(), 5, 0.0);
    }
}