package streams;

import lambdas.Person;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalTest {
    private final Logger logger = Logger.getLogger(OptionalTest.class.getName());

    @Test
    void retrieveDefault() {
        Optional<String> opt = Optional.of("string");
        assertTrue(opt.isPresent());
        opt.ifPresent(s -> System.out.println("Here is the included value: " + s));
        String str = opt.orElse("no value included");
        System.out.println(str);
    }

    @Test
    void orElseGetPerson() {
        Optional<Person> optPerson = Optional.empty();
        Person p = optPerson.orElseGet(() -> new Person("No Name"));
        System.out.println(p);
        logger.info(() -> "Here is an info message");
    }
}
