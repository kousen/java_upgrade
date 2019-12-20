package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class RandomStream {
    public static void main(String[] args) {
        ArrayList<Object> randoms = DoubleStream.generate(Math::random)
                .limit(10)
                .collect(ArrayList::new,    // Supplier for the resulting collection
                        ArrayList::add,     // Add a single element to the collection
                        ArrayList::addAll); // Combine collections
        System.out.println(randoms);
    }
}
