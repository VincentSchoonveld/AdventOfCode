package vincent.adventofcode.aoc2018.day17;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2018_DAY_17);
//        input = Arrays.asList("x=495, y=2..7",
//                "y=7, x=495..501",
//                "x=501, y=3..7",
//                "x=498, y=2..4",
//                "x=506, y=1..2",
//                "x=498, y=10..13",
//                "x=504, y=10..13",
//                "y=13, x=498..504");
        PipeWaterMap pipeWaterMap = PipeWaterMap.forInput(input);
        int i = 0;
        long amount = -1;
        while(amount < pipeWaterMap.getAmountOfWater() || i++<10) {
            amount = pipeWaterMap.getAmountOfWater();
            try {
                pipeWaterMap.addWater();
            } catch (Error e) {
                System.out.println(pipeWaterMap);
                System.out.println(pipeWaterMap.getSize());
                throw e;
            }
        }
        System.out.println(pipeWaterMap);
        System.out.println(pipeWaterMap.getAmountOfWater());
    }
}
