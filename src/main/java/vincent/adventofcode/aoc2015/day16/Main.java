package vincent.adventofcode.aoc2015.day16;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2015_DAY_16);
        input.removeIf(s -> !isValid(s));
//        isValid("[Sue 280: trees: 3, vizslas: 0, children: 0, Sue 370: vizslas: 0, cars: 2, perfumes: 5, Sue 373: pomeranians: 3, perfumes: 1, vizslas: 0]\n");
        System.out.println(input);
    }

    private static boolean isValid(String s) {
        return map().entrySet().stream().allMatch(entry -> correct(s, entry.getKey(), entry.getValue()));
    }

    private static boolean correct(String s, String name, int amount) {
        //Sue 461: children: 6, vizslas: 4, perfumes: 5
        Pattern pattern = Pattern.compile(name + ": (\\d+)(,)?");
        Matcher matcher = pattern.matcher(s);
        if(!matcher.find()) {
            return true;
        }
        int foundAmount = Integer.parseInt(matcher.group(1));
        if(Arrays.asList("cats", "trees").contains(name)) {
            return !matcher.find() || amount < Integer.parseInt(matcher.group(1));
        } else if(Arrays.asList("pomeranians", "goldfish").contains(name)) {
            return !matcher.find() || amount > Integer.parseInt(matcher.group(1));
        }
        return !matcher.find() || String.valueOf(amount).equals(matcher.group(1));
    }

    private static Map<String, Integer> map() {
        String s = "children: 3\n" +
                "cats: 7\n" +
                "samoyeds: 2\n" +
                "pomeranians: 3\n" +
                "akitas: 0\n" +
                "vizslas: 0\n" +
                "goldfish: 5\n" +
                "trees: 3\n" +
                "cars: 2\n" +
                "perfumes: 1";
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(
                s.split("\n")
        ).forEach(
                line -> {
                    String[] split = line.split(": ");
                    map.put(split[0], Integer.valueOf(split[1]));
                }
        );
        return map;
    }
}
