package got;

import java.time.LocalDate;

public class Member implements Comparable<Member> {
    private Long id;
    private Title title = Title.LORD;
    private String name;
    private LocalDate dob;
    private double salary;
    private House house;

    public Member(Long id, Title title, String name, LocalDate dob, double salary, House house) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.house = house;
    }

    @Override
    public int compareTo(Member member) {
        return id.compareTo(member.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public House getHouse() {
        return house;
    }

    public String getHouseName() {
        return house.getName();
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", title=" + title +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", house=" + house +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;

        if (Double.compare(member.salary, salary) != 0) return false;
        if (!id.equals(member.id)) return false;
        if (title != member.title) return false;
        if (!name.equals(member.name)) return false;
        if (dob != null ? !dob.equals(member.dob) : member.dob != null) return false;
        return house != null ? house.equals(member.house) : member.house == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (house != null ? house.hashCode() : 0);
        return result;
    }
}
