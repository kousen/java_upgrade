package eam.after;

import java.util.function.Consumer;
import java.util.function.Function;

public class Resource {
    private Resource() {
        System.out.println("Instance created");
    }

    public void op1() {
        System.out.println("op1 called....");
    }

    public void op2() {
        System.out.println("op2 called...");
    }

    private void close() {
        System.out.println("do any cleanup here...");
    }

    public static void use(Consumer<Resource> consume) {
        Resource resource = new Resource();
        try {
            consume.accept(resource);
        } finally {
            resource.close();
        }
    }

    public static <R> R useWithReturn(Function<Resource, R> function) {
        R result = null;
        Resource resource = new Resource();
        try {
            result = function.apply(resource);
        } finally {
            resource.close();
        }
        return result;
    }
}
