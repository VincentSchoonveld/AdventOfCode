package vincent.adventofcode.aoc2019.day8;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_8);
        Layers layers = Layers.forInput(inputStrings.get(0));
        System.out.println("FIRST PART:");
        System.out.println(layers.findNumberOfOnesTimeTwoWhereZerosAreLeast()); //1690
        System.out.println("SECOND PART:");
        System.out.println(layers.toString()); //ZPZUB
    }
}
