package dao;

import java.util.Optional;

public class Employee {
    private int id;
    private String first;
    private String middleInitial;
    private String last;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public Optional<String> getMiddleInitial() {
        return Optional.ofNullable(middleInitial);
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
