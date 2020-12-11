package vincent.adventofcode.aoc2018.day20;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Coordinate {
    private final int x;
    private final int y;

    Coordinate() {
        this(0,0);
    }

    Coordinate nextForChar(char c) {
        switch (c) {
            case 'N': return new Coordinate(x, y+1);
            case 'W': return new Coordinate(x-1, y);
            case 'S': return new Coordinate(x, y-1);
            case 'E': return new Coordinate(x+1, y);
            default: throw new IllegalArgumentException("The character is not valid: " + c);
        }
    }
}
