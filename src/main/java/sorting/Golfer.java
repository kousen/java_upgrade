package sorting;

public class Golfer {
    private int score;
    private String first;
    private String last;

    public Golfer() {}

    public Golfer(String first, String last, int score) {
        this.score = score;
        this.first = first;
        this.last = last;
    }

    public Golfer(Golfer golfer) {
        this.score = golfer.score;
        this.first = golfer.first;
        this.last = golfer.last;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return String.format("Golfer{score=%2d, last='%8s', first='%5s'}", score, last, first);
    }
}
