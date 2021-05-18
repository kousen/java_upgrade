package optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductDAO {
    private static final Map<Integer, Product> products = new HashMap<>();

    static {
        products.put(1, new Product(1, "Football", 12.99));
        products.put(2, new Product(2, "Basketball", 15.99));
        products.put(3, new Product(3, "Baseball", 5.99));
    }

    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(products.get(id));
    }


    public Product getProductById(Integer id) {
        Optional<Product> optional = findById(id);
        // Create default product only if no product with that id
        return optional.orElseGet(() -> new Product(999, "No name", 0.00));
        // return optional.orElse(new Product(999, "No name", 0.00));
    }
}
