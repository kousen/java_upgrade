package sorting;

public class Golfer implements Comparable<Golfer> {
    private String first;
    private String last;
    private int score;

    public Golfer() {}

    public Golfer(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Golfer(String first, String last, int score) {
        this.first = first;
        this.last = last;
        this.score = score;
    }

    @Override
    public int compareTo(Golfer golfer) {
        return score - golfer.score;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Golfer golfer = (Golfer) o;

        if (first != null ? !first.equals(golfer.first) : golfer.first != null) return false;
        return last != null ? last.equals(golfer.last) : golfer.last == null;

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Golfer{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", score=" + score +
                '}';
    }
}
