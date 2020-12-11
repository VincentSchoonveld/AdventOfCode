package vincent.adventofcode.aoc2020.day7;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Main {
    private static final List<String> INPUT_STRINGS = InputDataUtil.getInputStrings(Day.DAY_7);

    public static void main(String[] args) {
        System.out.println("FIRST PART");
        final Map<String, Map<String, Integer>> map = map();
        final Map<String, Integer> numberOfRequiredBagsMap = toNumberOfRequiredBagsMap(map);
        Map<String, Set<String>> names = getStrings(map);
        System.out.println(names.get("shiny gold bag").size());
        System.out.println("SECOND PART");
        System.out.println(numberOfRequiredBagsMap.get("shiny gold bag"));
    }

    private static Map<String, Set<String>> getStrings(Map<String, Map<String, Integer>> map) {
        Map<String, Set<String>> map1 = new HashMap<>();
        for(String s : map.keySet()) {
            Set<String> names = new HashSet<>();
            Set<String> toAdd = map.entrySet().stream().filter(s1 -> s1.getValue().containsKey(s)).map(Map.Entry::getKey).collect(Collectors.toSet());
            while(names.addAll(toAdd)) {
                toAdd = names.stream()
                        .flatMap(name -> map.entrySet().stream().filter(s1 -> s1.getValue().containsKey(name)).map(Map.Entry::getKey))
                        .collect(Collectors.toSet());
            }
            map1.put(s, names);
        }

        return map1;
    }

    private static Map<String, Integer> toNumberOfRequiredBagsMap(Map<String, Map<String, Integer>> map) {
        final Map<String, Integer> map2 = new ConcurrentHashMap<>();
        while(map2.size() < map.size()) {
            map.entrySet().stream()
                    .filter(i -> !map2.containsKey(i.getKey()))
                    .filter(i -> i.getValue().keySet().stream().allMatch(map2::containsKey))
                    .forEach(i -> map2.put(i.getKey(), i.getValue().keySet().stream().mapToInt(j -> (map2.get(j)+1)*(map.get(i.getKey()).get(j))).sum()));
        }
        return map2;
    }

    private static Map<String, Map<String, Integer>> map() {
        //light gold bags contain 2 light lime bags, 1 faded green bag, 3 clear olive bags, 2 dim bronze bags.
        final Map<String, Map<String, Integer>> stringListHashMap = new HashMap<>();
        for(String s : INPUT_STRINGS) {
            final String name = s.split("s contain ")[0];
            if(s.contains(" contain no other bags.")) {
                stringListHashMap.put(name, Collections.emptyMap());
                continue;
            }
            final String[] split = s.split("s contain ")[1].split(", ");

            final Map<String, Integer> collect = new HashMap<>();
            for(String s1 : split) {
                final String s4 = Arrays.stream(s1.split(" ")).skip(1).map(s6 -> s6.endsWith(".") ? s6.substring(0, s6.length()-1) : s6).reduce("", (s2, s3) -> s2 + " " + s3).substring(1).replaceAll("bags", "bag");
                final int i = Integer.parseInt(s1.split(" ")[0]);
                collect.put(s4, i);
            }
            stringListHashMap.put(name, collect);
        }
        return stringListHashMap;
    }
}
