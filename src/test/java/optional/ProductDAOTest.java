package optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDAOTest {
    private final ProductDAO dao = new ProductDAO();

    @Test
    public void findById_exists() {
        IntStream.rangeClosed(1, 3)
                .forEach(id -> assertTrue(dao.findById(id).isPresent()));
    }

    @Test
    public void findById_doesNotExist() {
        Optional<Product> optionalProduct = dao.findById(999);
        assertTrue(optionalProduct.isEmpty());
    }

    @Test
    public void getProductById_exists() {
        Product product = dao.getProductById(1);
        assertEquals(1, product.getId().intValue());
    }

    @Test
    public void getProductById_doesNotExist() {
        Product product = dao.getProductById(999);
        assertEquals(999, product.getId().intValue());
    }
}