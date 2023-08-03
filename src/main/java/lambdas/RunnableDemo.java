package lambdas;

@SuppressWarnings("Convert2Lambda")
public class RunnableDemo {
    public static void main(String[] args) {
        // Java 7 syntax
        new Thread(new Runnable() {
            @Override
            public void run() {  // Single abstract method
                System.out.println("Inside an anonymous inner class");
            }
        }).start();

        // Expression lambda
        new Thread(() -> System.out.println("Inside expression lambda")).start();

        // Block lambda
        new Thread(() -> {
            sleepForOneSecond();
            System.out.println("Hello, World!");
            System.out.println("Inside block lambda");
        }).start();

        // Assign a lambda to a variable
        Runnable runnable = () -> System.out.println("Assigned to a variable");
        new Thread(runnable).start();
    }

    private static void sleepForOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

