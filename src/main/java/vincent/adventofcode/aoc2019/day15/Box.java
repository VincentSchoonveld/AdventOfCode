package vincent.adventofcode.aoc2019.day15;

import lombok.Value;

@Value
class Box {
    private Type type;
    private int path;
    private Index last;

    boolean isOxygenSystem() {
        return type == Type.OXYGEN_SYSTEM;
    }
}
