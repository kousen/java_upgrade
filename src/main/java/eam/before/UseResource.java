package eam.before;

public class UseResource {
    public static void main(String[] args) {
        Resource resource = new Resource();
        resource.op1();
        resource.op2();
        // cleanup?
    }
}
