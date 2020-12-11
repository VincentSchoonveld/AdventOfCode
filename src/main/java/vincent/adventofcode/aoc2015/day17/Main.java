package vincent.adventofcode.aoc2015.day17;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
//        System.out.println(subsets(Arrays.asList(20, 15, 10, 5, 5)));
        Map<Integer, Integer> map = map(Arrays.asList(50,
                44,
                11,
                49,
                42,
                46,
                18,
                32,
                26,
                40,
                21,
                7,
                18,
                43,
                10,
                47,
                36,
                24,
                22,
                40));
        System.out.println(map.values().stream().mapToInt(i -> i).sum());
        System.out.println(map.entrySet().stream().min(Map.Entry.comparingByKey()).map(Map.Entry::getValue));
    }

    private static Map<Integer, Integer> map(List<Integer> ls) {
        int n = ls.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < (1 << n); i++) {
            int sum = 0;
            int times = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    sum = sum+ls.get(j);
                    times++;
                }
            }
            if(sum == 150) {
                map.put(times, map.getOrDefault(times, 0)+1);
            }

        }
        return map;
    }
}
