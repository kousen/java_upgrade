package refactoring.before;

import java.util.*;

public class LoopsSortsAndIfs {
    public static void main(String[] args) {
        String[] strings = "this is an array of strings".split(" ");

        var evenLengths = new ArrayList<String>();
        for (var s : strings) {
            if (s.length() % 2 == 0) {
                evenLengths.add(s.toUpperCase());
            }
        }

        Collections.sort(evenLengths, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        for (var s : evenLengths) {
            System.out.println(s);
        }
    }
}
