package vincent.adventofcode.aoc2018.day25;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2018_DAY_25);
        List<SpaceTimePoint> collect = input.stream()
                .map(SpaceTimePoint::forInput)
                .collect(Collectors.toList());
        List<List<SpaceTimePoint>> interstellars = new ArrayList<>();
        List<SpaceTimePoint> interstellar = new ArrayList<>();
        interstellar.add(collect.get(0));
        interstellars.add(interstellar);
        collect.remove(0);
        System.out.println(spaceTimePoints(collect, interstellars).size());
    }


    private static List<List<SpaceTimePoint>> spaceTimePoints(List<SpaceTimePoint> remaining, List<List<SpaceTimePoint>> interstellars) {
        if(remaining.isEmpty()) {
            return interstellars;
        }
        List<SpaceTimePoint> interstellar = interstellars.get(interstellars.size() - 1);
        Optional<SpaceTimePoint> any = remaining.stream()
                .filter(a -> interstellar.stream().anyMatch(b -> a.distanceTo(b) <= 3))
                .findAny();
        if(any.isPresent()) {
            remaining.remove(any.get());
            interstellar.add(any.get());
        } else {
            SpaceTimePoint spaceTimePoint = remaining.remove(0);
            List<SpaceTimePoint> objects = new ArrayList<>();
            objects.add(spaceTimePoint);
            interstellars.add(objects);
        }
        return spaceTimePoints(remaining, interstellars);
    }
}
