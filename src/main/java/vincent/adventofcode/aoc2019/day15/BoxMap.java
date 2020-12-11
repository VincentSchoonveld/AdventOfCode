package vincent.adventofcode.aoc2019.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class BoxMap {
    private Map<Index, Box> map;

    BoxMap(Index origin) {
        map = new HashMap<>();
        map.put(origin, new Box(Type.EMPTY, 0, null));
    }

    boolean containsOxygenSystem() {
        return map.values().stream().anyMatch(Box::isOxygenSystem);
    }

    List<Unknown> getNextUnknowns(int depth) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().getPath() == depth-1)
                .filter(entry -> entry.getValue().getType() == Type.EMPTY)
                .flatMap(entry -> entry.getKey().getNeighbors()
                        .stream()
                        .filter(neighborIndex -> !map.containsKey(neighborIndex))
                        .map(neighborIndex -> new Unknown(neighborIndex, entry.getKey())))
                .collect(Collectors.toList());
    }

    void put(Index index, Box box) {
        map.put(index, box);
    }

    List<Index> getPathBetween(Index from, Index to) {
        List<Index> indices = pathToOrigin(to);
        List<Index> pathBetween = new ArrayList<>();
        Index last = from;
        while(!indices.contains(last)) {
            pathBetween.add(last);
            last = map.get(last).getLast();
        }
        int i = indices.indexOf(last);
        for(int j=i; j>=0; j--) {
            pathBetween.add(indices.get(j));
        }
        return pathBetween;
    }

    Index getOxygenIndex() {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isOxygenSystem())
                .findAny()
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new IllegalStateException("The oxygen box is not reached yet."));
    }

    private List<Index> pathToOrigin(Index index) {
        Box box = map.get(index);
        List<Index> ls = new ArrayList<>();
        ls.add(index);
        while(box.getLast() != null) {
            Index last = box.getLast();
            ls.add(last);
            box = map.get(last);
        }
        return ls;
    }
}
