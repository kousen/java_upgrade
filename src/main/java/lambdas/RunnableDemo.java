package lambdas;

public class RunnableDemo {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside anon inner class");
            }
        }).start();

        new Thread(() -> System.out.println("Inside an expression lambda")).start();

        new Thread(() -> {
            System.out.println("Inside a block lambda");
        }).start();

        Runnable r = () -> System.out.println("Use lambda as a variable");
        new Thread(r).start();
    }
}
