package vincent.adventofcode.aoc2018.day13;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Train {
    @Getter
    private Index index;
    private Direction direction;
    private int numberOfCrossroads = 0;
    @Getter
    private int pathsWalked = 0;

    Train(Index index, Direction direction) {
        this(index, direction, 0, 0);
    }

    Index next(TrackMap trackMap) {
        pathsWalked++;
        index = index.next(direction);
        char nextChar = trackMap.get(index);
        direction = direction.next(nextChar, numberOfCrossroads);
        if(nextChar == '+') {
            numberOfCrossroads++;
        }
        return index;
    }


}
