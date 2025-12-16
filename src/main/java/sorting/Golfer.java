package sorting;

public record Golfer(String first, String last, Integer score) implements Comparable<Golfer> {

    @Override
    public int compareTo(Golfer golfer) {
        return score - golfer.score;
    }
}