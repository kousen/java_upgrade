package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FlatMapDemo {
    public static void main(String[] args) {

        Customer sheridan = new Customer("Sheridan");
        Customer ivanova = new Customer("Ivanova");
        Customer garibaldi = new Customer("Garibaldi");

        sheridan.addOrder(new Order(1))
                .addOrder(new Order(2))
                .addOrder(new Order(3));
        ivanova.addOrder(new Order(4))
                .addOrder(new Order(5));

        List<Customer> customers = List.of(sheridan, ivanova, garibaldi);

        // map for 1-1 customer to name --> Stream<String>
        customers.stream()
                .map(Customer::getName) // function<Customer,String>
                .forEach(System.out::println);

        // map 1-many customer to orders --> Stream<List<Order>>
        customers.stream()
                .map(Customer::getOrders) // function<Customer,List<Order>>
                .forEach(System.out::println);

        // map 1-many customer to orders.stream() --> Stream<Stream<Order>>
        customers.stream()
                .map(customer -> customer.getOrders().stream()) // function<Customer,Stream<Order>>
                .forEach(System.out::println);

        // stream() on an empty collection is already an empty Stream
        customers.stream()
                .flatMap(customer -> customer.getOrders().stream()) // function<Customer,Stream<Order>>
                .forEach(System.out::println);

        // flatMap 1-many customer to orders.stream() --> Stream<Order>
        // Note: extra detail included just for illustration;
        //      stream() on an empty collection already returns an empty stream
        customers.stream()
                .flatMap(customer ->
                        customer.getOrders().isEmpty() ? Stream.empty() :
                                customer.getOrders().stream())
                .forEach(System.out::println);

    }
}
