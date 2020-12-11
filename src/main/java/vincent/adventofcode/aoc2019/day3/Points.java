package vincent.adventofcode.aoc2019.day3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Points {
    private Map<Index, Integer> indexIntegerMap;

    static Points forInput(List<String> input) {
        Index last = new Index(0, 0);
        Map<Index, Integer> indexIntegerMap = new HashMap<>();
        int path=0;
        for(String string:input) {
            Direction direction = Direction.forChar(string.charAt(0));
            int length = Integer.parseInt(string.substring(1));
            for(int i=0; i<length; i++) {
                last=last.next(direction);
                path++;
                indexIntegerMap.putIfAbsent(last, path);
            }
        }
        return new Points(indexIntegerMap);
    }

    Points intersectionWith(Points points) {
        return new Points(points.indexIntegerMap.keySet()
                .stream()
                .filter(this.indexIntegerMap::containsKey)
                .collect(
                        Collectors.toMap(
                                index->index,
                                index -> points.indexIntegerMap.get(index)+indexIntegerMap.get(index))
                ));
    }

    int getMinimalDistanceToCentre() {
        return indexIntegerMap.keySet()
                .stream()
                .mapToInt(Index::distanceToCentre)
                .min()
                .orElseThrow(thereAreNoPoints());
    }

    int getMinimalPathSum() {
        return indexIntegerMap.values()
                .stream()
                .mapToInt(a->a)
                .min()
                .orElseThrow(thereAreNoPoints());
    }

    private static Supplier<RuntimeException> thereAreNoPoints() {
        return () -> new RuntimeException("There are no points.");
    }
}
