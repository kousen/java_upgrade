package got;

/**
 * Created by Ken Kousen on 7/18/16.
 */
public class House {
    private String name;

    public House(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        return name.equals(house.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
