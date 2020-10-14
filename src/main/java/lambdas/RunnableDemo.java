package lambdas;

public class RunnableDemo {
    public static void main(String[] args) {
        // Java 7 syntax
        new Thread(new Runnable() {  // Functional Interface
            @Override
            public void run() {  // Single Abstract Method
                System.out.println("Inside an anonymous inner class");
            }
        }).start();

        // Expression lambda: implementation is a single statement
        new Thread(() -> System.out.println("Inside expression lambda")).start();

        // Block lambda
        new Thread(() -> {
            System.out.println("Inside block lambda");
        }).start();

        // Can assign a lambda to a variable
        Runnable runnable = () -> System.out.println("Assigned to a variable");
        new Thread(runnable).start();

        // Note the very weird generated class name
        System.out.println(runnable.getClass().getName());
    }
}

