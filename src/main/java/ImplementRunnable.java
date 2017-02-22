
public class ImplementRunnable {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside anon inner class");
            }
        }).start();

        new Thread(() -> System.out.println("Inside lambda")).start();
        new Thread(System.out::println).start();
    }
}
