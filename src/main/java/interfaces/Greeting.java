package interfaces;

@FunctionalInterface
public interface Greeting {
    String sayHello(String name);

    default String getDefaultName() {
        return "World";
    }

    // void whatup();
}
