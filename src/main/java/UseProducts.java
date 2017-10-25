import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UseProducts {
    private static Logger logger = Logger.getLogger(UseProducts.class.getName());

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(new Product("football", 10),
                new Product("basketball", 12), new Product("baseball", 5),
                new Product("tennis racket", 12));

        List<String> names = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());

        // complex string arg always gets formed!
        // Arguments to methods always get evaluated
        logger.info("Product not found; available products are: " + names);

        // supplier.get() only invoked if loggable
        logger.info(() -> "Product not found; available products are: " + names);

        Stream<Product> productStream = products.stream();

        Optional<Product> max = productStream.max((p1, p2) ->
                (int) (p1.getPrice() - p2.getPrice()));
        System.out.println(max);

        max = products.stream()
                .max(Comparator.comparingDouble(Product::getPrice)
                        .thenComparing(Product::getName).reversed());
        System.out.println(max);

        // throws an IllegalStateException because stream is closed
        // productStream.forEach(System.out::println);
    }
}
