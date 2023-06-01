package datetime;

public class NumberOfCores {
    public static void main(String[] args) {
        System.out.println("Number of cores: " +
                           Runtime.getRuntime().availableProcessors());
    }
}
