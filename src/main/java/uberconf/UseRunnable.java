package uberconf;

/**
 * Created by Ken Kousen on 7/19/16.
 */
public class UseRunnable {

    private static void sayHello() {
        System.out.println("Hello from a method");
    }

    public static void main(String[] args) {

        // Using anonymous inner class
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello using anonymous inner class");
            }
        }).start();

        // Using a lambda expression
        new Thread(() -> System.out.println("Hello using lambda")).start();
        // new Thread(() -> 3).start();
        new Thread(() -> { int x = 3; }).start();

        Runnable runnable = () -> System.out.println("Hello from variable");
        new Thread(runnable).start();

        // Using a method reference
        new Thread(UseRunnable::sayHello).start();
        new Thread(() -> sayHello()).start();
    }
}
