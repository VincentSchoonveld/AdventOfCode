package vincent.adventofcode.aoc2018.day20;

import java.util.LinkedHashSet;
import java.util.Set;

class Doors {

    private Set<Door> doorSet = new LinkedHashSet<>();

    boolean add(Door door) {
        return doorSet.add(door);
    }

    boolean add(Doors doors) {
        return this.doorSet.addAll(doors.doorSet);
    }
}
