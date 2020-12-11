package vincent.adventofcode.aoc2020.day9;

import java.util.List;

public class Interval {
    private final List<Long> input;
    private int min;
    private int maxInclusive;
    private long sum;

    Interval(List<Long> input) {
        this.input = input;
        this.min = 0;
        this.maxInclusive = 0;
        this.sum = input.get(0);
    }

    Interval(List<Long> input, int size) {
        this.input = input;
        this.min = 0;
        this.maxInclusive = size-1;
        this.sum = input.stream().limit(size).mapToLong(i->i).sum();
    }

    boolean next() {
        sum -= input.get(min++);
        sum += input.get(++maxInclusive);
        return sum == input.get(maxInclusive+1);
    }

    boolean next2(long number) {
        if(sum > number) {
            sum -= input.get(min++);
        } else {
            sum += input.get(++maxInclusive);
        }
        return sum == number;
    }

    long get() {
        return input.get(maxInclusive+1);
    }

    long get2() {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for(int i=this.min; i<=maxInclusive; i++) {
            min = Long.min(min, input.get(i));
            max = Long.max(max, input.get(i));
        }
        return min + max;
    }
}
