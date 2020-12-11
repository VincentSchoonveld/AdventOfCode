package vincent.adventofcode.aoc2018.day7;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class Main {
    public static void main(String... args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2018_DAY_7);
        Rules rules = Rules.forInput(input);
        CharacterComparator characterComparator = new CharacterComparator(rules);
        String collect = IntStream.range(0, 26)
                .mapToObj(i -> (char) (i + 'A'))
                .sorted(characterComparator)
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
        System.out.println(collect);
    }
}
