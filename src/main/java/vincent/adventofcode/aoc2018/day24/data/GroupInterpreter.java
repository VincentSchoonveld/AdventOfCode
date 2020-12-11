package vincent.adventofcode.aoc2018.day24.data;

import lombok.RequiredArgsConstructor;
import vincent.adventofcode.aoc2018.day24.groups.Group;
import vincent.adventofcode.util.NotImplementedException;

@RequiredArgsConstructor
public class GroupInterpreter {
    /*
    801 units each with 4706 hit points (weak to radiation) with an attack
 that does 116 bludgeoning damage at initiative 1
     */

    private final Words words;
    private Type type;

    Group next() {
        throw new NotImplementedException();
    }

    boolean isEnd() {
        throw new NotImplementedException();
    }

    Type type() {
        throw new NotImplementedException();
    }

    private enum Type {
        IMMUNE_SYSTEM, INFECTION
    }
}
