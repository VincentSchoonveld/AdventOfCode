package vincent.adventofcode.aoc2019.day2;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_2);
        List<Integer> collect = Arrays.stream(inputStrings.get(0).split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        System.out.println("PART 1:");
        IntcodeProgram intcodeProgram = new IntcodeProgram(collect, 12 ,2);
        List<Integer> finishedState = intcodeProgram.getFinishedState();
        System.out.println(finishedState);

        System.out.println("PART 2:");
        IntegerPair integerPair = forInitialSet(collect);
        System.out.println(integerPair);
    }

    private static IntegerPair forInitialSet(List<Integer> ls) {
        for(int i=0; i<99; i++) {
            for(int j=0; j<99; j++) {
                if(forInitialSet(ls, i, j)) {
                    return new IntegerPair(i,j);
                }
            }
        }
        throw new RuntimeException();
    }

    private static boolean forInitialSet(List<Integer> ls, int noun, int verb) {
        List<Integer> finishedState = new IntcodeProgram(ls, noun, verb).getFinishedState();
        return finishedState.get(0) == 19690720;
    }
}


