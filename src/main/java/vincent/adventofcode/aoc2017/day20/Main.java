package vincent.adventofcode.aoc2017.day20;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2017_DAY_20);
        Particles particles = Particles.forInput(inputStrings);
        //System.out.println(particles.getMinAcceleration());
        while(!particles.isFinal()) {
            particles.next();
        }
        System.out.println(particles.getMin());
        System.out.println(particles.getMinAcceleration());
    }
}
