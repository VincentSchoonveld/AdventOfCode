package vincent.adventofcode.aoc2017.day1;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

public class Main {
    public static void main(String... args) {
        String inputString = InputDataUtil.getInputString(Day.AOC_2017_DAY_1);
        System.out.println(sumFirstPart(inputString));
        System.out.println(sumSecondPart(inputString));
    }

    private static long sumFirstPart(String input) {
        long sum = 0;
        for(int i=0; i<input.length()-1; i++) {
            if(input.charAt(i) == input.charAt(i+1)) {
                sum = sum + input.charAt(i) - '0';
            }
        }
        if(input.charAt(0) == input.charAt(input.length()-1)) {
            sum = sum + input.charAt(0) - '0';
        }
        return sum;
    }

    private static long sumSecondPart(String input) {
        int length = input.length();
        long sum = 0;
        for(int i=0; i<length; i++) {
            if(input.charAt(i) == input.charAt((i+length/2)%length)) {
                sum = sum + input.charAt(i) - '0';
            }
        }
        return sum;
    }
}
