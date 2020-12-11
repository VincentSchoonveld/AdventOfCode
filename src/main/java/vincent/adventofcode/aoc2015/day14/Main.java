package vincent.adventofcode.aoc2015.day14;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2015_DAY_14);
        int[] ints = new int[input.size()];
        for(int i=1; i<=2503; i++) {
            int[] distance = new int[input.size()];
            for(int j=0; j<input.size(); j++) {
                distance[j] = distance(input.get(j), i);
            }
            int max = 0;
            for(int d : distance) {
                max = Math.max(d, max);
            }
            for(int j=0; j<input.size(); j++) {
                if(distance[j] == max) {
                    ints[j]++;
                }
            }
        }
        int max1 = 0;
        for(int d :ints)
            max1 = Integer.max(max1, d);
        System.out.println(max1);

    }

    private static int distance(String input, int time) {
        //Dancer can fly 27 km/s for 5 seconds, but then must rest for 132 seconds.
        String[] s = input.split(" ");
        int speed = Integer.parseInt(s[3]);
        int speedTime = Integer.parseInt(s[6]);
        int restTime = Integer.parseInt(s[13]);
        int distance = 0;
        while(time > speedTime + restTime) {
            time = time-speedTime-restTime;
            distance = distance+ speedTime*speed;
        }
        if(time > speedTime) {
            return distance + speedTime*speed;
        } else {
            return distance + time*speed;
        }
    }
}
