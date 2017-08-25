package interfaces;

public class MyGreeting implements Greeting {
    private String user = getDefaultName();

    public MyGreeting() {}

    public MyGreeting(String user) {
        this.user = user;
    }

    @Override
    public String sayHello(String name) {
        //String user = (name == null) ? getDefaultName() : name;
        String n = user != null && user.length() > 0 ? user : getDefaultName();
        return String.format("Hello, %s!", user);
    }
}
