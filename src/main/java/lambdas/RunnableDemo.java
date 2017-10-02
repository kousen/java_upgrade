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

        new Thread(() -> System.out.println("Inside expression lambda")).start();

        new Thread(() -> {
            System.out.println("Inside block lambda");
        }).start();

        Runnable runnable = () -> System.out.println("Assigning lambda to a Runnable reference");
        new Thread(runnable).start();

        // Legal, but not a good idea (because there are no elements in context,
        //   so just prints a carriage return
        new Thread(System.out::println).start();

        Stream.of("this is a string".split(" "))  // Stream<String>
                .forEach(System.out::println);
    }
}

