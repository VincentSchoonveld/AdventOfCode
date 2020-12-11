package vincent.adventofcode.aoc2019.day14;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_14);
        Reactions reactions = Reactions.forInput(inputStrings);
        System.out.println("FIRST PART:");
        System.out.println(reactions.getAmount("FUEL", 1)); //346961
        System.out.println("SECOND PART:");
        System.out.println(reactions.getAmountForAmountOfOre("FUEL", 1_000_000_000_000L)); //4065790
    }
}
