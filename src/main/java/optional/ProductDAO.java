package optional;

import java.util.Map;
import java.util.Optional;

//import static java.util.Map.entry;
//import static java.util.Map.ofEntries;

public class ProductDAO {
//    private static Map<Integer, Product> products =
//            ofEntries(entry(1, new Product(1, "Football", 12.99)),
//                    entry(2, new Product(2, "Basketball", 15.99)),
//                    entry(3, new Product(3, "Baseball", 5.99)));

    public Optional<Product> findById(Integer id) {
//        return Optional.ofNullable(products.get(id));
        return Optional.empty();
    }


    public Product getProductById(Integer id) {
        Optional<Product> optional = findById(id);
        // Create default product only if no product with that id
        return optional.orElseGet(() -> new Product(999, "No name", 0.00));
    }
}
