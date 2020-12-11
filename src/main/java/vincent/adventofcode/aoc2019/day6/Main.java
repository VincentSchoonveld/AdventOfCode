package vincent.adventofcode.aoc2019.day6;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        Orbits orbits = Orbits.forInput(InputDataUtil.getInputStrings(Day.AOC_2019_DAY_6)
                .stream()
                .map(Orbit::new)
                .collect(Collectors.toList()));

        System.out.println("FIRST PART:");
        System.out.println(orbits.getCheckSum());
        System.out.println("SECOND PART:");
        System.out.println(orbits.distanceBetweenYOUAndSAN());
    }
}
