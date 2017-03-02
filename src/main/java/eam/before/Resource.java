package eam.before;

public class Resource {
    public Resource() {
        System.out.println("Instance created");
    }

    public void op1() {
        System.out.println("op1 called....");
    }

    public void op2() {
        System.out.println("op2 called...");
    }

    public void finalize() {
        System.out.println("do any cleanup here...");
    }

}
