package vincent.adventofcode.aoc2018.day15;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vincent.adventofcode.aoc2018.day15.creatures.Creature;
import vincent.adventofcode.aoc2018.day15.creatures.Creatures;
import vincent.adventofcode.aoc2018.day15.path.Area;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Board {
    private Area area;
    private Creatures creatures;
    private int rounds;

    static Board forInput(List<String> input, int attackPowerElfs) {
        Area area = Area.forInput(input);
        Creatures creatures = Creatures.forInputAndAttackPowerElfs(input, attackPowerElfs);
        return new Board(area, creatures, 0);
    }

    void playUntilEnd() {
        while(creatures.isNotFinished()) {
            playOnce();
        }
    }

    private void playOnce() {
        Creature creature = nextCreature();
        area.nextMove(creature, creatures)
                .ifPresent(creature::move);
        creatures.nextAttack(creature)
                .ifPresent(creatures::attack);
        creature.endTurn();
    }

    private Creature nextCreature() {
        Optional<Creature> creature = creatures.nextCreature(rounds);
        if(!creature.isPresent()) {
            rounds++;
            return nextCreature();
        }
        return creature.get();
    }

    public String state() {
        if(creatures.minTurns() > rounds) {
            rounds++;
        }
        return rounds + " * " + creatures.sumHitPoints() + " = " + rounds*creatures.sumHitPoints();
    }

    boolean anyElfsDied() {
        return creatures.anyElfsDied();
    }
}
