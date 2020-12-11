package vincent.adventofcode.aoc2020.day5;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final List<String> INPUT_STRINGS = InputDataUtil.getInputStrings(Day.DAY_5);

    public static void main(String[] args) {
        System.out.println("FIRST PART");
        final int asInt = INPUT_STRINGS.stream().mapToInt(Main::seatId).max().getAsInt();
        System.out.println(asInt);
        System.out.println(seatId("BFFFBBFRRR"));
        List<Integer> collect = INPUT_STRINGS.stream().map(Main::seatId).collect(Collectors.toList());
        Collections.sort(collect);
        for(int i=0; i<collect.size()-1; i++) {
            if(collect.get(i)+1 != collect.get(i+1)) {
                System.out.println(collect.get(i)+1);
            }
        }

//        System.out.println(seatId("FFFBBBFRRR"));
    }

    private static int seatId(String input) {
        return Integer.parseInt(toBinaryString(input), 2);
    }


    private static String toBinaryString(String input) {
        return input.replaceAll("[FL]", "0").replaceAll("[BR]", "1");
    }

}
