package vincent.adventofcode.aoc2015.day23;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2015_DAY_23);
        int a = 1;
        int b = 0;
        int register = 0;
        while(register < input.size()) {
            String s = input.get(register);
            String[] split = s.split(" ");
            boolean isA = split[1].contains("a");
            System.out.println(register);
            switch (split[0]) {
                case "hlf":
                    if(isA) {
                        a = a/2;
                    } else {
                        b = b/2;
                    }
                    register++;
                    break;
                case "tpl":
                    if(isA) {
                        a = a*3;
                    } else {
                        b = b*3;
                    }
                    register++;
                    break;
                case "inc":
                    if(isA) {
                        a++;
                    } else {
                        b++;
                    }
                    register++;
                    break;
                case "jmp":
                    register=register+Integer.parseInt(split[1]);
                    break;
                case "jie":
                    if((isA ? a : b)%2 == 0) {
                        register=register+Integer.parseInt(split[2]);
                    } else {
                        register++;
                    }
                    break;
                case "jio":
                    if((isA ? a : b) == 1) {
                        register=register+Integer.parseInt(split[2]);
                    } else {
                        register++;
                    }
                    break;
                default:
                    throw new IllegalArgumentException(split[0]);
            }
        }
        System.out.println(b);
    }
}

