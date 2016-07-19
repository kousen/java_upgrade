package interfaces;

/**
 * Created by Ken Kousen on 7/19/16.
 */
public interface Bird extends Animal {
    default String speak() {
        return "chirp";
    }
}
