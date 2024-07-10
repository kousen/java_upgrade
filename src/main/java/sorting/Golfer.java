package sorting;

import java.util.Objects;

public class Golfer implements Comparable<Golfer> {
    private final String first;
    private final String last;
    private final int score;

    public Golfer(String first, String last, int score) {
        this.first = first;
        this.last = last;
        this.score = score;
    }

    @Override
    public int compareTo(Golfer golfer) {
        return Integer.compare(score, golfer.score);
        // return score - golfer.score;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Golfer golfer = (Golfer) o;

        if (!Objects.equals(first, golfer.first)) return false;
        return Objects.equals(last, golfer.last);

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
