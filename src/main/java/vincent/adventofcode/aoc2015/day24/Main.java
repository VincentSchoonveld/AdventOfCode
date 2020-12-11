package vincent.adventofcode.aoc2015.day24;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> input = InputDataUtil.getInputIntegers(Day.AOC_2015_DAY_24);
        final int sum = input.stream().mapToInt(i -> i).sum();
        System.out.println(sum);
        long quantum = Long.MAX_VALUE;
        int i = 1;
        while(quantum == Long.MAX_VALUE && i <= 9) {
            System.out.println(i);
            for(List<Integer> ls : CombinationUtil.combinationsWithSizeAndSum(input,i, sum/4)) {
                quantum = Long.min(quantum, prod(ls));
            }
            i++;
        }
        System.out.println(quantum);
    }

    private static long prod(List<Integer> ls) {
        return ls.stream().mapToLong(i->i).reduce(1L, (i,j) -> i*j);
    }
}
