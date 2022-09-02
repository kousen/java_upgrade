package lambdas;

// From attendee "AS"

@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

class MyPerson {
    private final String firstName;
    private final String lastName;
    private final int age;

    public MyPerson(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static void main(String[] args) {
        MyPerson aPerson = MyPerson.constructPerson(MyPerson::new);
        System.out.println(aPerson);
    }

    private static MyPerson constructPerson(TriFunction<String, String, Integer, MyPerson> triFunction) {
        return triFunction.apply("firstName", "lastName", 40);
    }

    @Override
    public String toString() {
        return "[firstName->" + firstName + "-lastName->" + lastName + "-Age->" + age + "]";
    }
}