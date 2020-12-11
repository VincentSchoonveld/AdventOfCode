package vincent.adventofcode.aoc2019.day1;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_1);
        Modules modules = Modules.forInput(inputStrings);
        System.out.println("Sum total fuel is: " +modules.getTotalFuel());
    }
}
