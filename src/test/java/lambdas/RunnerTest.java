package lambdas;

import org.junit.Test;

import static org.junit.Assert.*;

public class RunnerTest {
    private Runner runner = new Runner();

    @Test
    public void invokeRunnable() throws Exception {
        runner.invoke(() -> System.out.println("Inside runnable"));
    }

    @Test
    public void invokeCallable() throws Exception {
        String result = runner.invoke(() -> "Inside callable");
        assertEquals("Inside callable", result);
    }

    @Test
    public void invokeWhich() throws Exception {
        // Which 'invoke' gets called here?
        runner.invoke(() -> "done");
    }
}