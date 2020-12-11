package vincent.adventofcode.aoc2018.day15.creatures;

import lombok.Getter;
import vincent.adventofcode.aoc2018.day15.Index;

@Getter
public abstract class Creature {
    private Index position;
    private int hitPoints;
    private int turns;
    private int otherAttack;

    Creature(Index startPosition, int otherAttack) {
        this.position = startPosition;
        this.hitPoints = 200;
        this.turns = 0;
        this.otherAttack = otherAttack;
    }

    abstract boolean isElf();

    public void move(Index index) {
        position = index;
    }

    boolean attack() {
        hitPoints -= otherAttack;
        return hitPoints <= 0;
    }

    public void endTurn() {
        turns++;
    }
}
