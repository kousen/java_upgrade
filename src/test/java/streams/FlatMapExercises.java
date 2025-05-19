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

        customers = Arrays.asList(sheridan, ivanova, garibaldi);
    }

    @Test
    public void getCustomerNames() {
        List<String> names = customers.stream()
                .map(Customer::getName)
                .collect(Collectors.toList());
        
        assertEquals(3, names.size());
        assertTrue(names.contains("Sheridan"));
        assertTrue(names.contains("Ivanova"));
        assertTrue(names.contains("Garibaldi"));
    }

    @Test
    public void getOrdersPerCustomer() {
        List<List<Order>> ordersPerCustomer = customers.stream()
                .map(Customer::getOrders)
                .collect(Collectors.toList());
        
        assertEquals(3, ordersPerCustomer.size());
        assertEquals(3, ordersPerCustomer.get(0).size()); // Sheridan has 3 orders
        assertEquals(2, ordersPerCustomer.get(1).size()); // Ivanova has 2 orders
        assertEquals(0, ordersPerCustomer.get(2).size()); // Garibaldi has 0 orders
    }

    @Test
    public void getAllOrdersFlat() {
        List<Order> allOrders = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .collect(Collectors.toList());
        
        assertEquals(5, allOrders.size());
        assertTrue(allOrders.stream().anyMatch(o -> o.getId() == 1));
        assertTrue(allOrders.stream().anyMatch(o -> o.getId() == 5));
    }

    @Test
    public void getAllOrderIds() {
        List<Integer> orderIds = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .map(Order::getId)
                .collect(Collectors.toList());
        
        assertEquals(5, orderIds.size());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), orderIds);
    }

    @Test
    public void findCustomersWithoutOrders() {
        List<Customer> customersWithoutOrders = customers.stream()
                .filter(customer -> customer.getOrders().isEmpty())
                .collect(Collectors.toList());
        
        assertEquals(1, customersWithoutOrders.size());
        assertEquals("Garibaldi", customersWithoutOrders.get(0).getName());
    }

    @Test
    public void countTotalOrders() {
        long totalOrders = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .count();
        
        assertEquals(5L, totalOrders);
    }

    @Test
    public void getOrdersForCustomersWithOrders() {
        List<Order> ordersFromActiveCustomers = customers.stream()
                .filter(customer -> !customer.getOrders().isEmpty())
                .flatMap(customer -> customer.getOrders().stream())
                .collect(Collectors.toList());
        
        assertEquals(5, ordersFromActiveCustomers.size());
    }

    @Test
    public void combineCustomerNamesWithOrderIds() {
        List<String> customerOrderStrings = customers.stream()
                .flatMap(customer -> customer.getOrders().stream()
                        .map(order -> customer.getName() + "-" + order.getId()))
                .collect(Collectors.toList());
        
        assertEquals(5, customerOrderStrings.size());
        assertTrue(customerOrderStrings.contains("Sheridan-1"));
        assertTrue(customerOrderStrings.contains("Ivanova-5"));
    }
}