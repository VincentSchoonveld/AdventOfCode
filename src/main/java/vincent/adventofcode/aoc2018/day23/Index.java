package vincent.adventofcode.aoc2018.day23;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Index {
    private int x;
    private int y;
    private int z;

    int distanceTo(Index other) {
        return d(x,other.x)+d(y,other.y)+d(z,other.z);
    }

    private static int d(int i1, int i2) {
        return Math.abs(i1-i2);
    }
}
