package vincent.adventofcode.aoc2020.day11;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static boolean changes = true;

    public static void main(String[] args) {
        List<StringBuilder> input = InputDataUtil.getInputStringBuilders(Day.DAY_11);
        while(changes) {
            input = next(input);
        }
        final long count = input.stream().flatMapToInt(CharSequence::chars).filter(i -> i == '#').count();
        System.out.println(count);
    }

    private static List<StringBuilder> next(List<StringBuilder> before) {
        final List<StringBuilder> strings = new ArrayList<>();
        changes = false;
        for(int i=0; i<before.size(); i++) {
            final StringBuilder next = new StringBuilder();
            strings.add(next);
            final StringBuilder s = before.get(i);
            for(int j=0; j<s.length(); j++) {
                int count = count2(before, i, j);
                final char c = s.charAt(j);
                if(c == '#' && count >= 5) {
                    next.append("L");
                    changes = true;
                } else if(c == 'L' && count == 0) {
                    next.append("#");
                    changes = true;
                } else {
                    next.append(c);
                }
            }
        }
        return strings;
    }

    private static int count(List<StringBuilder> ls, int i, int j) {
        int count = 0;
        for(int i1=-1; i1<=1; i1++) {
            for(int j1=-1; j1<=1; j1++) {
                try {
                    if (ls.get(i+i1).charAt(j+j1) == '#') {
                        count++;
                    }
                } catch (Exception e) {

                }
            }
        }
        return count;
    }

    private static int count2(List<StringBuilder> ls, int i, int j) {
        int count = 0;
        for(int i1=-1; i1<=1; i1++) {
            for(int j1=-1; j1<=1; j1++) {
                if((i1 != 0 || j1 != 0) && seesOccupied(ls, i, j, i1, j1)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean seesOccupied(List<StringBuilder> ls, int i, int j, int i1, int j1) {
        int k=1;
        int i2 = i1;
        int j2 =j1;
        try {
            while(ls.get(i+i2).charAt(j+j2) == '.') {
                k++;
                i2 = i1*k;
                j2 = j1*k;
            }
        } catch (Exception e) {
            return false;
        }
        return ls.get(i+i2).charAt(j+j2) == '#';
    }
}
