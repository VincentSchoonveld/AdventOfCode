package vincent.adventofcode.aoc2019.day17;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
class PathMap {
    private Map<Index, Type> map;

    Type get(Index index) {
        return map.get(index);
    }

    boolean isCorrect(Collection<Index> movedTo) {
        return movedTo.equals(map.entrySet().stream()
                .filter(a -> a.getValue() == Type.SCAFFELD)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
    }

    boolean movedEveryWhere(Collection<Index> movedTo) {
        return map.entrySet().stream()
                .filter(a -> a.getValue() == Type.SCAFFELD)
                .map(Map.Entry::getKey)
                .allMatch(movedTo::contains);
    }

    @Override
    public String toString() {
        System.out.println(map.values().stream().filter(a -> a == Type.SCAFFELD).count());


        int xMax = map.keySet().stream().mapToInt(Index::getX).max().getAsInt();
        int yMax = map.keySet().stream().mapToInt(Index::getY).max().getAsInt();
        StringBuilder stringBuilder = new StringBuilder();
        for(int y=0; y<= yMax; y++) {
            for(int x=0; x<= xMax; x++) {
                stringBuilder.append(map.get(new Index(x,y)).asChar());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    long sumAlignmentParameters() {
        return map.keySet().stream()
                .filter(this::isScaffoldIntersection)
                .mapToLong(this::alignmentParameter)
                .sum();
    }

    private boolean isScaffoldIntersection(Index index) {
        return map.get(index) == Type.SCAFFELD
                && map.get(index.next(Direction.UP)) == Type.SCAFFELD
                && map.get(index.next(Direction.DOWN)) == Type.SCAFFELD
                && map.get(index.next(Direction.LEFT)) == Type.SCAFFELD
                && map.get(index.next(Direction.RIGHT)) == Type.SCAFFELD;
    }

    private long alignmentParameter(Index index) {
        long top = getPositionTo(index, Direction.UP);
        long left = getPositionTo(index, Direction.LEFT);
        return top*left;
    }

    private long getPositionTo(Index index, Direction direction) {
        long left = 0;
        Index next;
        next = index.next(direction);
        while(map.containsKey(next)) {
            left++;
            next = next.next(direction);
        }
        return left;
    }
}
