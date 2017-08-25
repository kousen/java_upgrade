package lambdas;

import java.util.stream.Stream;

public class RunnableDemo {
    public static void main(String[] args) {
        // Java 7 syntax
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside an anonymous inner class");
            }
        }).start();

        String name = "Dolly";
        new Thread(() -> System.out.println("Inside expression lambda:" + " Hello, " + name)).start();

        new Thread(() -> {
            System.out.println("Inside block lambda");
        }).start();

        Runnable r = () -> System.out.println("Assigned to a variable");
        new Thread(r).start();

        // Not very useful, because it prints nothing (carriage return)
        new Thread(System.out::println).start();

        Stream.of("this is a string".split(" "))
                .forEach(System.out::println);
    }
}

