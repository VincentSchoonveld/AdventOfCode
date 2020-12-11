package vincent.adventofcode.aoc2019.day18;

import lombok.Value;

import java.io.Serializable;
import java.util.HashSet;

@Value
class DistanceAndKeysNeeded implements Serializable {
    private int distance;
    private HashSet<Character> keysNeeded;
}
