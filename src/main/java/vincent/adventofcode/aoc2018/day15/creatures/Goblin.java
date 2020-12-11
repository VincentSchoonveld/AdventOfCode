package vincent.adventofcode.aoc2018.day15.creatures;

import vincent.adventofcode.aoc2018.day15.Index;

class Goblin extends Creature {

    Goblin(Index startPosition, int elfAttack) {
        super(startPosition, elfAttack);
    }

    @Override
    boolean isElf() {
        return false;
    }
}
