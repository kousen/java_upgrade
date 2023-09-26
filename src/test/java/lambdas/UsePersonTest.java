package lambdas;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UsePersonTest {
    private final UsePerson usePerson = new UsePerson();

    @Test
    void convertNamesToPeople_java7andEarlier() {
        List<Person> beatles = usePerson.convertNamesToPeopleJava7();
        assertThat(beatles).contains(
                new Person("John"),
                new Person("Paul"),
                new Person("George"),
                new Person("Ringo"));
    }

    @Test
    void convertNamesToPeople_streams() {
        List<Person> beatles = usePerson.convertNamesToPeopleStreams();
        assertThat(beatles).contains(
                new Person("John"),
                new Person("Paul"),
                new Person("George"),
                new Person("Ringo"));
    }

    @Test
    void convertNamesToPeople_ctrRef() {
        List<Person> beatles = usePerson.convertNamesUsingCtorRef();
        assertThat(beatles).contains(
                new Person("John"),
                new Person("Paul"),
                new Person("George"),
                new Person("Ringo"));
    }

    @Test
    void convertNamesToPersonArray() {
        Person[] beatles = usePerson.convertNamesToPersonArray();
        assertThat(beatles).contains(
                new Person("John"),
                new Person("Paul"),
                new Person("George"),
                new Person("Ringo"));
    }

    @Test
    void convertFullNamesToPeople() {
        List<Person> beatles = usePerson.convertFullNamesToPerson();
        assertThat(beatles).contains(
                new Person("John", "Lennon"),
                new Person("Paul", "McCartney"),
                new Person("George", "Harrison"),
                new Person("Ringo", "Starr"));
    }

    @Test
    void threeArgCollect() {
        List<Person> beatles = usePerson.threeArgCollect();
        assertThat(beatles).contains(
                new Person("John"),
                new Person("Paul"),
                new Person("George"),
                new Person("Ringo"));
    }

    @Test
    void threeArgCollect_methodRefs() {
        List<Person> beatles = usePerson.threeArgCollectMethodRefs();
        assertThat(beatles).contains(
                new Person("John"),
                new Person("Paul"),
                new Person("George"),
                new Person("Ringo"));
    }

}