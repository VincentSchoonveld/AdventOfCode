package vincent.adventofcode.aoc2019.day24;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_24);
        System.out.println("FIRST PART");
        Bugs bugs = Bugs.forInput(inputStrings);

        while(!bugs.foundEarlier()) {
            bugs.next();
        }
        System.out.println(bugs.biodiversityRate()); //14539258

        System.out.println("SECOND PART");
        MultipleLevelBugs multipleLevelBugs = MultipleLevelBugs.forInput(inputStrings, 100);
        IntStream.range(0,200)
                .forEach(i -> multipleLevelBugs.next());
        System.out.println(multipleLevelBugs.countBugs()); //1977
    }
}
