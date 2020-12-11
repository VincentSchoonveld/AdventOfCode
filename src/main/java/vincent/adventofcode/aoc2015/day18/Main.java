package vincent.adventofcode.aoc2015.day18;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2015_DAY_18);
        boolean[][] lights = lights(input);
        for(int i=0; i<100; i++) {
            lights = next(lights);
        }
        int count = 0;
        for(int i=0; i<lights.length; i++) {
            boolean[] light = lights[i];
            for(int j=0; j<light.length; j++) {
                if(light[j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean[][] next(boolean[][] before) {
        boolean[][] next = new boolean[before.length][before[0].length];
        for(int i=0; i<next.length; i++) {
            for(int j=0; j<next[i].length; j++) {
                int count = 0;
                for(int i1=-1; i1<=1; i1++) {
                    for(int i2=-1; i2<=1; i2++) {
                        if(i+i1>=0 && j+i2>=0 && i+i1 < next.length && j+i2 < next[i].length && before[i+i1][j+i2]) {
                            count++;
                        }
                    }
                }
                if(count == 3 || (count == 4 && before[i][j]) || (i == 0 && j == 0) || (i == 0 && j == before[i].length-1) || (i == next.length-1 && j == next[i].length-1) || (i == next.length-1 && j == 0)) {
                    next[i][j] = true;
                }
            }
        }
        return next;
    }

    private static boolean[][] lights(List<String> input) {
        boolean[][] booleans = new boolean[input.size()][input.get(0).length()];
        for(int i=0; i<input.size(); i++) {
            String s = input.get(i);
            for(int j=0; j<s.length(); j++) {
                booleans[i][j] = s.charAt(j) == '#';
            }
        }
        return booleans;
    }
}
