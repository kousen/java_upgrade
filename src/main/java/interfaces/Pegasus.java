package interfaces;

public class Pegasus implements Horse, Bird {
    @Override
    public String speak() {
        return Horse.super.speak() + " " + Bird.super.speak();
    }
}
