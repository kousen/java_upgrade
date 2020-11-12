package optional;

import java.util.Optional;

public class OrderItemDemo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();

        Optional<OrderItem> optionalOrderItem = orderDAO.findOrderItemById(1);
    }
}
