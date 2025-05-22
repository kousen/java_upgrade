import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class UseProducts {
    private static final Logger logger = Logger.getLogger(UseProducts.class.getName());

    public static void main(String[] args) {
        List<Product> products = List.of(new Product("football", 10),
                new Product("basketball", 12), new Product("baseball", 5));

        List<String> names = products.stream()  // Stream<Product>
                .map(Product::getName)          // Stream<String>
                // .peek(System.out::println)
                .collect(Collectors.toList());

        // complex string arg always gets formed!
        // Arguments to methods always get evaluated
        logger.info("Product not found; available products are: " + names);

        // supplier.get() only invoked if loggable
        logger.info(() -> "Product not found; available products are: " + names);
    }
}
