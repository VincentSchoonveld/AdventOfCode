package vincent.adventofcode.aoc2018.day25;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
class SpaceTimePoint {
    private int x;
    private int y;
    private int z;
    private int t;

    static SpaceTimePoint forInput(String string) {
        List<Integer> split = Arrays.stream(string.split("[^-0-9.]"))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new SpaceTimePoint(split.get(0), split.get(1), split.get(2), split.get(3));
    }

    int distanceTo(SpaceTimePoint other) {
        return d(x,other.x)+d(y,other.y)+d(z,other.z)+d(t,other.t);
    }

    private static int d(int i1, int i2) {
        return Math.abs(i1-i2);
    }
}
