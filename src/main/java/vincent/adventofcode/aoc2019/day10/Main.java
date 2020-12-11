package vincent.adventofcode.aoc2019.day10;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_10);
        Asteroids asteroids = Asteroids.forInput(inputStrings);
        System.out.println("FIRST PART");
        System.out.println(asteroids.findNumberOfMostAsteroidsToBeSeen()); //319
        System.out.println("SECOND PART");
        System.out.println(asteroids.vaporize(200)); //517
    }
}
