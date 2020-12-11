package vincent.adventofcode.aoc2019.day20;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_20);
        PlutoMap plutoMap = PlutoMap.forInput(inputStrings);
        MinPathFinder minPathFinder = new MinPathFinder(plutoMap);
        System.out.println("FIRST PART");
        System.out.println(minPathFinder.findMinPathFromStartToFinish(false)); //516
        //Ugly solution, takes a very long time:
        System.out.println("SECOND PART");
        System.out.println(minPathFinder.findMinPathFromStartToFinish(true)); //5966
    }
}
