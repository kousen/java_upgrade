package optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderDAO {

    private static final Map<Integer, OrderItem> orderItems = new HashMap<>();

    static {
        orderItems.put(1, new OrderItem(1, new Product(1, "chessboard", 10.0), 1));
        orderItems.put(2, new OrderItem(2, new Product(2, "chess set", 20.0), 1));
        orderItems.put(3, new OrderItem(3, new Product(3, "chess videos", 25.0), 5));
    }

    public Optional<OrderItem> findOrderItemById(int id) {
        return Optional.ofNullable(orderItems.get(id));
    }
}
