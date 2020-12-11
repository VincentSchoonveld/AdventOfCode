package vincent.adventofcode.aoc2015.day13;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2015_DAY_13);
        List<String> names = names(input);
        Collections.sort(names);
        System.out.println(names);
        Happinesses happinesses = Happinesses.forInput(input);
        List<List<Integer>> permutations = Permutations.permutations(names.size());
        int max = Integer.MIN_VALUE;
        for(List<Integer> permutation : permutations) {
            int happiness = happinesses.happiness(permutation);
            max = Math.max(max, happiness);
        }
        System.out.println(max);
    }

    private static List<String> names(List<String> input) {
        Set<String> names = new HashSet<>();
        for(String line : input) {
            names.add(line.split(" ")[0]);
        }
        return new ArrayList<>(names);
    }
}
