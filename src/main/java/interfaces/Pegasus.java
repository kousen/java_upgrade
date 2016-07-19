package interfaces;

/**
 * Created by Ken Kousen on 7/19/16.
 */
public class Pegasus implements Horse, Bird {
    @Override
    public String speak() {
        return Horse.super.speak() + " " + Bird.super.speak();
    }
}
