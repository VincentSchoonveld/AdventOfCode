package vincent.adventofcode.aoc2019.day20;

import lombok.Value;

import java.util.Arrays;
import java.util.List;

@Value
public class Index {
    private int x;
    private int y;

    ThreeIndex withLevel(int level) {
        return new ThreeIndex(x,y,level);
    }

    List<Index> getNeighbors() {
        return Arrays.asList(
                new Index(x+1, y),
                new Index(x-1, y),
                new Index(x, y+1),
                new Index(x, y-1));
    }
}
