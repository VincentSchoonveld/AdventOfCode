package vincent.adventofcode.aoc2015.day9;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class DistanceMap {
    private Map<NamePair, Integer> distances;

    static DistanceMap forInput(List<String> input) {
        Map<NamePair, Integer> map = input.stream()
                .collect(Collectors.toMap(NamePair::forInput, DistanceMap::distanceForInput));
        return new DistanceMap(map);
    }

    private static int distanceForInput(String string) {
        return Integer.parseInt(string.split(" ")[4]);
    }

    List<String> names() {
        return distances.keySet().stream()
                .flatMap(namePair -> namePair.names().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    int sumDistance(List<String> names) {
        int distance = 0;
        for(int i=0; i<names.size()-1; i++) {
            distance += distance(names.get(i), names.get(i+1));
        }
        return distance;
    }

    private int distance(String name1, String name2) {
        return distances.get(new NamePair(name1, name2));
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class NamePair {
        private String name1;
        private String name2;

        static NamePair forInput(String string) {
            String[] split = string.split(" ");
            return new NamePair(split[0], split[2]);
        }

        List<String> names() {
            return Arrays.asList(name1, name2);
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof NamePair)) {
                return false;
            }
            NamePair namePair = (NamePair) o;
            return (namePair.name1.equals(name1) && namePair.name2.equals(name2))
                    || (namePair.name1.equals(name2) && namePair.name2.equals(name1));
        }

        @Override
        public int hashCode() {
            return name1.hashCode() + name2.hashCode();
        }
    }
}
