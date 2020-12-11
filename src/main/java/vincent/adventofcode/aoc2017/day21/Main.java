package vincent.adventofcode.aoc2017.day21;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2017_DAY_21);
        Rules rules = Rules.forInput(inputStrings);
        MainPattern mainPattern = new MainPattern();
        for(int i=0; i<18; i++) {
            //System.out.println(mainPattern);
            mainPattern.next(rules);
        }
        //System.out.println(mainPattern);
        System.out.println(mainPattern.amountOfPixelsOn());
    }
}
