package vincent.adventofcode.aoc2018.day24.data;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Data {
    /*
    Immune System:
    17 units each with 5390 hit points (weak to radiation, bludgeoning) with
    an attack that does 4507 fire damage at initiative 2
    989 units each with 1274 hit points (immune to fire; weak to bludgeoning,
    slashing) with an attack that does 25 slashing damage at initiative 3

    Infection:
    801 units each with 4706 hit points (weak to radiation) with an attack
    that does 116 bludgeoning damage at initiative 1
    4485 units each with 2961 hit points (immune to radiation; weak to fire,
    cold) with an attack that does 12 slashing damage at initiative 4
     */

    private static final String iets = "\\d+ units each with \\d+ hit points (\\((immune to (\\w+, )*(\\w)*)?(, )?(weak to (\\w+, )*(\\w)*)?\\))? with an attack that does \\d+ \\w+ damage at initiative \\d+";

    public static void iets() {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2018_DAY_24, "test.txt");

    }
}
