package rus.nsu.fit.oop.lab2.Executor;
import java.util.HashMap;
import java.util.Stack;

public class ExecutionContext {
    private Stack<Double> stack = new Stack<>();
    private HashMap<String, Double> defines = new HashMap<>();

    public Stack<Double> getStack() {
        return stack;
    }
    public HashMap<String, Double> getDefines() {
        return defines;
    }
}
