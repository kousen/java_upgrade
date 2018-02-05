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

        new Thread(System.out::println).start();

        Stream.of("this is a string".split(" "))
                .forEach(System.out::println);
    }
}

