package vincent.adventofcode.aoc2018.day23;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2018_DAY_23);
        List<Nanobot> collect = input.stream()
                .map(Nanobot::forInput)
                .collect(Collectors.toList());
//        Nanobot nanobot = collect.stream().max(Comparator.comparing(Nanobot::getBeamSize)).get();
//        System.out.println(collect.stream().filter(nanobot::isInRange).count());
        Map<Index, Integer> countMap = new ConcurrentHashMap<>();
        collect.parallelStream()
                .flatMap(a -> a.getIndicesInRange().parallelStream())
                .forEach(i -> countMap.compute(i, (key,val) -> val == null ? 1 : val+1));

        Index index = null;
        Integer i = 0;
        for(Map.Entry<Index, Integer> entry: countMap.entrySet()) {
            if(entry.getValue() > i) {
                i = entry.getValue();
                index = entry.getKey();
            } else if(entry.getValue() == i && entry.getKey().distanceTo(new Index(0,0, 0)) < index.distanceTo(new Index(0,0,0))) {
                index = entry.getKey();
            }
        }

        System.out.println(index.distanceTo(new Index(0,0,0)));
        //>151663437 >156889435
        //System.out.println(collect.stream().filter(a -> a.getBeamSize() == 99416023));

//        System.out.println(collect.stream().filter((Nanobot nanobot) -> collect.stream().filter(nanobot1 -> nanobot1.hasInRange(nanobot)).count() == 824).count());
        //max=824

//        System.out.println(
//                collect.stream().collect(Collectors.groupingBy(a -> collect.stream().filter(nanobot1 -> nanobot1.hasInRange(a)).count()))
//        );
    }
}
