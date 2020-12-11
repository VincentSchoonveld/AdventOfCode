package vincent.adventofcode.aoc2019.day24;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Bugs {
    private Map<Index, InfectionState> map;
    private Set<Map<Index, InfectionState>> foundMaps;

    static Bugs forInput(List<String> input) {
        Map<Index, InfectionState> map = new HashMap<>();
        for (int y = 0; y < input.size(); y++) {
            String s = input.get(y);
            for(int x = 0; x< s.length(); x++) {
                map.put(new Index(x,y), InfectionState.forInput(s.charAt(x)));
            }
        }
        return new Bugs(map, new HashSet<>());
    }

    void next() {
        foundMaps.add(map);
        this.map = this.map.keySet()
                .stream()
                .collect(Collectors.toMap(index -> index, this::nextInfectionState));
    }

    boolean foundEarlier() {
        return foundMaps.contains(map);
    }

    long biodiversityRate() {
        return map.entrySet()
                .stream()
                .filter(a -> a.getValue() == InfectionState.IS_INFECTED)
                .map(Map.Entry::getKey)
                .mapToLong(index -> powerTwo(index.getX()+index.getY()*5))
                .sum();
    }

    private static long powerTwo(long power) {
        return power == 0 ? 1L : 2L*powerTwo(power-1);
    }

    private InfectionState nextInfectionState(Index index) {
        InfectionState infectionState = map.get(index);
        long neighborsInfected = countNeighborsInfected(index);
        return infectionState.nextInfectionState(neighborsInfected);
    }

    private long countNeighborsInfected(Index index) {
        return index.getNeighbors()
                .stream()
                .map(neighborIndex -> map.getOrDefault(neighborIndex, InfectionState.NOT_INFECTED))
                .filter(infectionState -> infectionState == InfectionState.IS_INFECTED)
                .count();
    }
}
