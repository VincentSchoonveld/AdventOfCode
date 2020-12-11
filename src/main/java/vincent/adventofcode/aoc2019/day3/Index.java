package vincent.adventofcode.aoc2019.day3;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class Index {
    private int x;
    private int y;

    Index next(Direction direction) {
        switch (direction) {
            case UP: return new Index(x,y+1);
            case DOWN: return new Index(x,y-1);
            case RIGHT: return new Index(x+1,y);
            case LEFT: return new Index(x-1,y);
            default: throw new IllegalArgumentException("The following direction is not known: "+direction);
        }
    }

    int distanceToCentre() {
        return Math.abs(x) + Math.abs(y);
    }
}
