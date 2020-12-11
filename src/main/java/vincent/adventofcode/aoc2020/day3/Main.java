package vincent.adventofcode.aoc2020.day3;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {

    private static final List<String> INPUT_STRINGS = InputDataUtil.getInputStrings(Day.DAY_3);

    public static void main(String[] args) {
        System.out.println("FIRST PART");
        System.out.println(numberOfTrees(3,1));
        System.out.println("SECOND PART");
        System.out.println(
                numberOfTrees(1,1)*
                numberOfTrees(3,1)*
                numberOfTrees(5,1)*
                numberOfTrees(7,1)*
                numberOfTrees(1,2)
        );
    }

    private static long numberOfTrees(int right, int down) {
        int numberOfTrees=0;
        int position=0;
        for(int i=0; i<INPUT_STRINGS.size(); i=i+down) {
            if(INPUT_STRINGS.get(i).charAt(position) == '#') {
                numberOfTrees++;
            }
            final int length = INPUT_STRINGS.get(i).length();
            position=(position+right)% length;
        }
        return numberOfTrees;
    }
}
