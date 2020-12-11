package vincent.adventofcode.aoc2017.day6;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        String inputString = InputDataUtil.getInputString(Day.AOC_2017_DAY_6);
        List<Integer> collect = Arrays.stream(inputString.split("\t")).map(Integer::valueOf).collect(Collectors.toList());
        Memory memory = new Memory(collect);
        System.out.println(memory.recurrence());
        List<Integer> memory1 = memory.getMemory();
        System.out.println(new Memory(collect).recurrence() - new Memory(collect).firstSeen(memory1));

    }
}
