package vincent.adventofcode.aoc2018.day15;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Index implements Comparable<Index> {
    private int x;
    private int y;

    public Stream<Index> neighbors() {
        return Stream.of(
                new Index(x-1,y),
                new Index(x+1,y),
                new Index(x,y-1),
                new Index(x,y+1));
    }

    @Override
    public int compareTo(Index o) {
        if(y == o.y) {
            return Integer.compare(x, o.x);
        } else {
            return Integer.compare(y, o.y);
        }
    }
}
