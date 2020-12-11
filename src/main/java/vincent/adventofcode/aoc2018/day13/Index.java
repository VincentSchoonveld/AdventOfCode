package vincent.adventofcode.aoc2018.day13;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Index implements Comparable<Index> {
    private int x;
    private int y;

    Index next(Direction direction) {
        switch (direction) {
            case UP:
                return new Index(x, y-1);
            case DOWN:
                return new Index(x, y+1);
            case RIGHT:
                return new Index(x+1, y);
            case LEFT:
                return new Index(x-1, y);
            default:
                throw new IllegalArgumentException("The following direction is not known: " +direction);
        }
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
