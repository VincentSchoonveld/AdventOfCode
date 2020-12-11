package vincent.adventofcode.aoc2018.day15.creatures;

import vincent.adventofcode.aoc2018.day15.Index;

class Elf extends Creature {

    private static final int GOBLIN_ATTACK = 3;

    Elf(Index startPosition) {
        super(startPosition, GOBLIN_ATTACK);
    }

    @Override
    boolean isElf() {
        return true;
    }
}
