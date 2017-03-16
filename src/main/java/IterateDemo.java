import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class IterateDemo {
    public static void main(String[] args) {
        Stream.iterate(100, n -> n + 2)
                .limit(20)
                .forEach(System.out::println);

        Stream.iterate(LocalDate.now(), date -> date.plusMonths(1))
                .limit(12)
                .forEach(System.out::println);
    }
}
