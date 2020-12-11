package vincent.adventofcode.aoc2017.day22;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2017_DAY_22);
        Nodes nodes = Nodes.forInput(inputStrings);
        int size = inputStrings.size();
        Virus virus = new Virus(new Index(size/2, size/2));
        for(int i=0; i<10000000; i++) {
            virus.next(nodes);
        }
        System.out.println(virus.getCountInfections());
    }
}
