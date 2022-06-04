package rus.nsu.fit.oop.lab2.Tests;

import org.junit.jupiter.api.Test;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Commands.Push;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PushTest {
    @Test
    void execute() {
        ExecutionContext context = new ExecutionContext();
        ExecCommand push = new Push();
        List<String> inputArgs = Arrays.asList("6");
        push.execute(context, inputArgs);
        assertEquals(context.getStack().size(), 1);
        assertEquals(context.getStack().peek(), 6.0, 0.0);
    }
}