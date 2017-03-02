package interfaces;

public class MyCompany implements Company {

    @Override
    public String getName() {
        return "Yoyodyne Propulsion Systems";
    }

    public static void main(String[] args) {
        MyCompany co = new MyCompany();
        System.out.println(co.getName());
    }
}
