package vincent.adventofcode.aoc2018.day24.groups;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class AttackPair implements Comparable<AttackPair> {

    private static final AttackOrderComparator ATTACK_ORDER_COMPARATOR = new AttackOrderComparator();

    private final Group attacker;
    private final Group attacked;

    public void attack() {
        attacked.attackByGroup(attacker);
    }

    @Override
    public int compareTo(AttackPair o) {
        return ATTACK_ORDER_COMPARATOR.compare(this.attacker, o.attacker);
    }
}
