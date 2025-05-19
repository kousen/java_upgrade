package sorting;

public record Golfer(String first, String last, int score) implements Comparable<Golfer> {
    
    @Override
    public int compareTo(Golfer golfer) {
        return score - golfer.score;
    }
}