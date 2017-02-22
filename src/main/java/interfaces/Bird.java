package interfaces;

public interface Bird extends Animal {
    default String speak() {
        return "chirp";
    }
}
