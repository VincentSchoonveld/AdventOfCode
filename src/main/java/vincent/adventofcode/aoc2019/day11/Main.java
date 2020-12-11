package vincent.adventofcode.aoc2019.day11;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_11);
        List<Long> collect = Arrays.stream(inputStrings.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        Robot robotStartWithBlack = new Robot(collect);
        robotStartWithBlack.execute(Color.BLACK);
        System.out.println("FIRST PART:");
        System.out.println(robotStartWithBlack.getPainting().size()); //1907

        Robot robotStartWithWhite = new Robot(collect);
        robotStartWithWhite.execute(Color.WHITE);
        System.out.println("SECOND PART:");
        System.out.println(robotStartWithWhite.getPainting().toString()); //ABEKZGFG
    }
}
