package vincent.adventofcode.aoc2018.day12;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2018_DAY_12);

//        input = Arrays.asList("initial state: #..#.#..##......###...###", "",
//                "...## => #",
//                "..#.. => #",
//                ".#... => #",
//                ".#.#. => #",
//                ".#.## => #",
//                ".##.. => #",
//                ".#### => #",
//                "#.#.# => #",
//                "#.### => #",
//                "##.#. => #",
//                "##.## => #",
//                "###.. => #",
//                "###.# => #",
//                "####. => #");

        System.out.println((50000000000L-17221-1)*40+690564);

//        Rules rules = Rules.forInput(input);
//        State state = State.forInput(input.get(0));
//        System.out.println(state);
//        for(long i=0; i<50000000000L; i++) {
//            state.next(rules);
//            System.out.println(state);
//            System.out.println(state.count());
//            System.out.println(i);
//        }
//        System.out.println(state.count());
        System.out.println((50000000000L-17221)*40+690564);
    }
}
