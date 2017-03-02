package lambdas;

import java.util.concurrent.Callable;

public class Runner {
    public void invoke(Runnable r) {
        r.run();
    }

    public <T> T invoke(Callable<T> c) throws Exception {
        return c.call();
    }
}
