package vincent.adventofcode.aoc2020.day8;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    private static final List<String> INPUT_STRINGS = InputDataUtil.getInputStrings(Day.DAY_8);

    public static void main(String[] args) {
        System.out.println("FIRST PART");
        Computer computer = new Computer(INPUT_STRINGS);
        computer.run();
        System.out.println(computer.getAccumulator());
        System.out.println("SECOND PART");
        int i = -1;
        while(!computer.isFinished()) {
            computer.setOff(++i);
            computer.run();
        }
        System.out.println(computer.getAccumulator());
    }
}
