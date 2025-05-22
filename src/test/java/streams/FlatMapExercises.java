package streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FlatMapExercises {
    private List<Customer> customers;

    @BeforeEach
    public void setUp() {
        Customer sheridan = new Customer("Sheridan");
        Customer ivanova = new Customer("Ivanova");
        Customer garibaldi = new Customer("Garibaldi");

        sheridan.addOrder(new Order(1))
                .addOrder(new Order(2))
                .addOrder(new Order(3));
        ivanova.addOrder(new Order(4))
                .addOrder(new Order(5));
        // garibaldi has no orders

        customers = List.of(sheridan, ivanova, garibaldi);
    }

    @Test
    public void getCustomerNames() {
        // TODO: Use map to get a List<String> of customer names
        // List<String> names = ...
        
        // assertEquals(3, names.size());
        // assertTrue(names.contains("Sheridan"));
        // assertTrue(names.contains("Ivanova"));
        // assertTrue(names.contains("Garibaldi"));
    }

    @Test
    public void getOrdersPerCustomer() {
        // TODO: Use map to get a List<List<Order>> showing orders per customer
        // Note how this creates a nested structure
        // List<List<Order>> ordersPerCustomer = ...
        
        // assertEquals(3, ordersPerCustomer.size());
        // assertEquals(3, ordersPerCustomer.get(0).size()); // Sheridan has 3 orders
        // assertEquals(2, ordersPerCustomer.get(1).size()); // Ivanova has 2 orders
        // assertEquals(0, ordersPerCustomer.get(2).size()); // Garibaldi has 0 orders
    }

    @Test
    public void getAllOrdersFlat() {
        // TODO: Use flatMap to get all orders as a flat List<Order>
        // List<Order> allOrders = ...
        
        // assertEquals(5, allOrders.size());
        // assertTrue(allOrders.stream().anyMatch(o -> o.getId() == 1));
        // assertTrue(allOrders.stream().anyMatch(o -> o.getId() == 5));
    }

    @Test
    public void getAllOrderIds() {
        // TODO: Use flatMap to get all order IDs as a List<Integer>
        // Hint: You'll need to flatMap to orders, then map to IDs
        // List<Integer> orderIds = ...
        
        // assertEquals(5, orderIds.size());
        // assertEquals(List.of(1, 2, 3, 4, 5), orderIds);
    }

    @Test
    public void findCustomersWithoutOrders() {
        // TODO: Find customers who have no orders
        // List<Customer> customersWithoutOrders = ...
        
        // assertEquals(1, customersWithoutOrders.size());
        // assertEquals("Garibaldi", customersWithoutOrders.get(0).getName());
    }

    @Test
    public void countTotalOrders() {
        // TODO: Count the total number of orders across all customers
        // long totalOrders = ...
        
        // assertEquals(5L, totalOrders);
    }

    @Test
    public void getOrdersForCustomersWithOrders() {
        // TODO: Use flatMap to get orders only from customers who have orders
        // Hint: Use filter before flatMap
        // List<Order> ordersFromActiveCustomers = ...
        
        // assertEquals(5, ordersFromActiveCustomers.size());
    }

    @Test
    public void combineCustomerNamesWithOrderIds() {
        // TODO: Create strings in format "CustomerName-OrderId" for all orders
        // Example: "Sheridan-1", "Sheridan-2", etc.
        // List<String> customerOrderStrings = ...
        
        // assertEquals(5, customerOrderStrings.size());
        // assertTrue(customerOrderStrings.contains("Sheridan-1"));
        // assertTrue(customerOrderStrings.contains("Ivanova-5"));
    }
}