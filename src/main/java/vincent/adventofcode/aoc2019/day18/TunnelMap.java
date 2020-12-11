package vincent.adventofcode.aoc2019.day18;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class TunnelMap {
    private Map<Index, Character> map;

    static TunnelMap forInput(List<String> strings) {
        Map<Index, Character> map = new HashMap<>();
        for(int y=0; y<strings.size(); y++) {
            String s = strings.get(y);
            for(int x=0; x<s.length(); x++) {
                map.put(new Index(x,y), s.charAt(x));
            }
        }
        return new TunnelMap(map);
    }

    Index getIndexForKey(char key) {
        return map.entrySet()
                .stream()
                .filter(a -> a.getValue() == key)
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no index for key " + key));
    }

    Quadrant getQuadrantForKey(char key) {
        return getIndexForKey(key).getQuadrant();
    }

    Character get(Index index) {
        return map.get(index);
    }

    List<Index> getNonWallNeighbors(Index index) {
        return index.getNeighbors()
                .stream()
                .filter(this::isNonWall)
                .collect(Collectors.toList());
    }

    private boolean isNonWall(Index index) {
        return map.get(index) != '#';
    }
}
