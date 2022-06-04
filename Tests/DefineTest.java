package rus.nsu.fit.oop.lab2.Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import rus.nsu.fit.oop.lab2.Commands.Define;
import rus.nsu.fit.oop.lab2.Commands.ExecCommand;
import rus.nsu.fit.oop.lab2.Executor.ExecutionContext;

import java.util.Arrays;
import java.util.List;

class DefineTest {
    @Test
    void execute() {
        ExecutionContext context = new ExecutionContext();
        ExecCommand define = new Define();
        List<String> inputArgs = Arrays.asList("a", "6");
        define.execute(context, inputArgs);
        assertTrue(context.getDefines().containsKey("a"));
        assertEquals(6.0, context.getDefines().get("a"), 0.0);
    }
}