package vincent.adventofcode.aoc2020.day9;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        List<Long> input = InputDataUtil.getInputLongs(Day.DAY_9);
        Interval interval1 = new Interval(input, 25);
        while(!interval1.next()) { }
        long x = interval1.get();
        System.out.println(x);
        Interval interval = new Interval(input);
        while(!interval.next2(x)) { }
        System.out.println(interval.get2());
    }

    private static Set<Long> last25(List<Long> input, int index) {
        Set<Long> set = new HashSet<>();
        for(int i=index-25; i<index; i++) {
            for(int j=i+1; j<index; j++) {
                set.add(input.get(i)+input.get(j));
            }
        }
        return set;
    }
}
