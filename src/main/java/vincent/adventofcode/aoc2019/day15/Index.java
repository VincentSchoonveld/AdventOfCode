package vincent.adventofcode.aoc2019.day15;

import lombok.Value;

import java.util.Arrays;
import java.util.List;

import static vincent.adventofcode.aoc2019.day15.Direction.*;

@Value
public class Index {
    private int x;
    private int y;

    Direction getDirection(Index other) {
        if(other.x<x) {
            return LEFT;
        } else if(other.x>x) {
            return RIGHT;
        } else if(other.y > y) {
            return UP;
        } else if(other.y < y) {
            return DOWN;
        } else {
            throw new IllegalArgumentException("The two indices are the same");
        }
    }

    List<Index> getNeighbors() {
        return Arrays.asList(next(UP), next(DOWN), next(RIGHT), next(LEFT));
    }

    private Index next(Direction direction) {
        switch (direction) {
            case UP: return new Index(x,y+1);
            case DOWN: return new Index(x,y-1);
            case RIGHT: return new Index(x+1,y);
            case LEFT: return new Index(x-1,y);
            default: throw new IllegalArgumentException("The following direction is not implemented: " + direction);
        }
    }
}
