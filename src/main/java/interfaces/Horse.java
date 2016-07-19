package interfaces;

/**
 * Created by Ken Kousen on 7/19/16.
 */
public interface Horse extends Animal {
    default String speak() {
        return "neigh";
    }
}
