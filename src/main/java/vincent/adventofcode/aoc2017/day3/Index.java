package vincent.adventofcode.aoc2017.day3;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Value
class Index {
    int x;
    int y;

    Stream<Index> neighbors() {
        List<Index> indices = new ArrayList<>();
        for(int i=-1; i<= 1; i++) {
            for(int j=-1; j<= 1; j++) {
                if(i != 0 || j != 0) {
                    indices.add(new Index(x+i, y+j));
                }
            }
        }
        return indices.stream();
    }

    Index next(Direction direction) {
        switch (direction) {
            case NORTH: return new Index(x,y-1);
            case SOUTH: return new Index(x,y+1);
            case WEST: return new Index(x-1,y);
            case EAST: return new Index(x+1,y);
            default: throw new IllegalArgumentException("There is no next for direction: "+direction);
        }
    }

    Direction getDirection(Index last) {
        if(last.x<x) {
            return Direction.EAST;
        } else if(last.x>x) {
            return Direction.WEST;
        } else if(last.y<y) {
            return Direction.NORTH;
        } else if(last.y>y) {
            return Direction.SOUTH;
        } else {
            throw new IllegalArgumentException("The two indices are the same: " +this);
        }
    }
}
