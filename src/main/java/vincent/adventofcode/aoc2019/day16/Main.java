package vincent.adventofcode.aoc2019.day16;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_16);
        List<Integer> collect = Arrays.stream(inputStrings.get(0).split("")).map(Integer::parseInt).collect(Collectors.toList());

        System.out.println("FIRST PART");
        Phases phases = new Phases(collect);
        for(int i=0; i<100; i++) {
            phases.next();
        }
        System.out.println(phases.getFirst8Digits());

        System.out.println("SECOND PART");
        int offSet = getFirst7Digits(collect);
        List<Integer> integers = duplicate10000(collect);
        removeFirstN(integers, offSet);
        Phases phases1 = new Phases(integers);
        for(int i=0; i<100; i++) {
            phases1.nextIfAllMultiplicationFactorsAreOne();
        }
        System.out.println(phases1.getFirst8Digits());
    }

    private static List<Integer> duplicate10000(List<Integer> ls) {
        return IntStream.range(0, 10_000)
                .boxed()
                .flatMap(a -> ls.stream())
                .collect(Collectors.toList());
    }

    private static void removeFirstN(List<Integer> ls, int amountToBeRemoved) {
        ls.subList(0, amountToBeRemoved).clear();
    }

    private static int getFirst7Digits(List<Integer> ls) {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i< 7; i++) {
            s.append(ls.get(i));
        }
        return Integer.parseInt(s.toString());
    }
}
