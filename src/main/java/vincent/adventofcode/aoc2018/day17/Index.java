package vincent.adventofcode.aoc2018.day17;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Value
public class Index {
    private int x;
    private int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return x == index.x &&
                y == index.y;
    }

    @Override
    public int hashCode() {
        return x*11927+y;
    }

    static Stream<Index> forInput(String input) {
        List<Integer> numbers = Arrays.stream(input.split("[^0-9]"))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        if('x' == input.charAt(0)) {
            return IntStream.rangeClosed(numbers.get(1), numbers.get(2))
                    .mapToObj(y -> new Index(numbers.get(0), y));
        } else {
            return IntStream.rangeClosed(numbers.get(1), numbers.get(2))
                    .mapToObj(x -> new Index(x, numbers.get(0)));
        }
    }

    Index next(Direction direction) {
        switch (direction) {
            case NORTH: return getNorth();
            case SOUTH: return getSouth();
            case EAST: return getEast();
            case WEST: return getWest();
            default: throw new IllegalArgumentException("The following direction is not implemented:" +direction);
        }
    }

    Index getNorth() {
        return new Index(x,y-1);
    }

    Index getSouth() {
        return new Index(x, y+1);
    }

    Index getWest() {
        return new Index(x-1,y);
    }

    Index getEast() {
        return new Index(x+1, y);
    }
}
