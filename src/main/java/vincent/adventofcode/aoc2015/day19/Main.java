package vincent.adventofcode.aoc2015.day19;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2015_DAY_19);
        Map<String, String> map = map(input);
        String molecule = input.get(input.size()-1);
        List<String> keyList = new ArrayList<>(map.keySet());
//        Set<String> molecules = new HashSet<>();
//        for(int left=0; left<molecule.length(); left++) {
//            for(int right=left+1; right-left<=2; right++) {
//                if(map.containsKey(molecule.substring(left, right))) {
//                    List<String> strings = map.get(molecule.substring(left, right));
//                    for(String s : strings) {
//                        molecules.add(molecule.substring(0, left) + s + molecule.substring(right));
//                    }
////                    left = right;
//                    break;
//                }
//            }
//        }
//        System.out.println(molecules.size());
        String molecule1 = molecule;
        int i=0;
        while(!"e".equals(molecule1)) {
            i++;
            String next = next(molecule1, keyList);
            molecule1 = molecule1.replaceFirst(next, map.get(next));
        }
        System.out.println(i);
        System.out.println(molecule1);
    }

    private static String next(String molecule1, List<String> keyList) {
        return keyList.stream()
                .filter(molecule1::contains)
                .findAny()
                .orElseThrow(() -> new RuntimeException(molecule1));
    }

    private static Map<String, String> map(List<String> input) {
        Map<String, String> map = new HashMap<>();
        input.forEach(
                s -> {
                    String[] split = s.split(" => ");
                    if(split.length == 2) {
                        map.put(split[1], split[0]);
                    }
                }
        );
        return map;
    }
}
