package vincent.adventofcode.aoc2019.day18;

import lombok.Value;

import java.util.Arrays;
import java.util.List;

import static vincent.adventofcode.aoc2019.day18.Quadrant.*;

@Value
public class Index {
    private int x;
    private int y;

    List<Index> getNeighbors() {
        return Arrays.asList(
                new Index(x+1, y),
                new Index(x-1, y),
                new Index(x, y+1),
                new Index(x, y-1));
    }

    boolean isSameQuadrant(Index other) {
        return getQuadrant() == other.getQuadrant();
    }

    int differenceFactorWithStart(Index other) {
        if(isSameQuadrant(other)) {
            throw new IllegalArgumentException("The indices are of the same quadrant: " + getQuadrant());
        }
        return getQuadrant().isCrossOpposite(other.getQuadrant()) ? 0 : -2;
    }

    Quadrant getQuadrant() {
        if(x<40) {
            if(y<40) {
                return UPPER_LEFT;
            } else if(y > 40) {
                return UPPER_RIGHT;
            }
        } else if(x > 40) {
            if(y<40) {
                return DOWN_LEFT;
            } else if(y > 40) {
                return DOWN_RIGHT;
            }
        }
        throw new IllegalStateException("Not part of a quadrant");
    }

}
