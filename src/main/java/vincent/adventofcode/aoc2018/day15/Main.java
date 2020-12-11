package vincent.adventofcode.aoc2018.day15;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {

    public static void main(String... args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2018_DAY_15);
        System.out.println("FIRST PART:");
        Board board = Board.forInput(input, 3);
        board.playUntilEnd();
        System.out.println(board.state()); //99 * 2645 = 261855

        System.out.println("SECOND PART:");
        System.out.println(minimalStateIfNoElfsDie(input));
    }

    private static String minimalStateIfNoElfsDie(List<String> input) {
        int low = 3;
        int high = 200;
        Board board = null;
        while(low < high) {
            int average = (high + low)/2;
            board = Board.forInput(input, average);
            board.playUntilEnd();
            if (board.anyElfsDied()) {
                low = average+1;
            } else {
                high = average;
            }
        }
        return board.state();
    }
}
