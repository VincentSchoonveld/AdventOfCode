package vincent.adventofcode.aoc2017.day3;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Main {
    public static void main(String... args) {
        int i =  265149;
        System.out.println(manhattanDistanceTo1(i));
        System.out.println(spiralSum(i));
    }

    private static int manhattanDistanceTo1(int n) {
        validateInput(n);
        if(n == 1) {
            return 0;
        }
        int layer = (int) ceil(0.5D * sqrt(n) - 0.5D);
        return layer + abs((n-1)%(2*layer) - layer);
    }

    private static int spiralSum(int n) {
        List<Pair> spiralSums = new ArrayList<>();
        spiralSums.add(new Pair(new Index(0,0), 1));
        spiralSums.add(new Pair(new Index(1, 0), 1));
        int i=2;
        while(i++<n) {
            if(i%10_000==0) {
                System.out.println(i);
            }
            Index next = next(spiralSums);
            int sum = next.neighbors().flatMap(a -> spiralSums.stream().filter(b -> b.index.equals(a)).map(b -> b.value)).mapToInt(a-> a).sum()                                                                                      ;
            spiralSums.add(new Pair(next, sum));
        }
        return spiralSums.get(spiralSums.size()-1).value;
    }

    private static int squareFloor(int i) {
        return (int) floor(sqrt(i));
    }

    private static void validateInput(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("There is no point for number: " + n);
        }
    }

    private static Index next(List<Pair> indices) {
        Index beforeLast = indices.get(indices.size()-2).index;
        Index last = indices.get(indices.size()-1).index;
        Direction direction = last.getDirection(beforeLast);
        Index next = last.next(direction.next());
        if(indices.stream().anyMatch(a -> a.index.equals(next))) {
            return last.next(direction);
        } else {
            return last.next(direction.next());
        }
    }

    @Value
    static class Pair {
        Index index;
        int value;
    }
}
