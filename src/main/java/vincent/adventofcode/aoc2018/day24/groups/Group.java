package vincent.adventofcode.aoc2018.day24.groups;

import lombok.Value;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Group {

    private int numberOfUnits;

    private final GroupData groupData;

    Group(int numberOfUnits, int hitPoints, int attackDamage, String attackType, int initiative,
          List<String> weaknesses, List<String> immunities) {
        this.numberOfUnits = numberOfUnits;
        this.groupData = new GroupData(hitPoints, attackDamage, attackType, initiative, weaknesses, immunities);
    }

    void attackByGroup(Group other) {
        final int damage = damageFrom(other);
        attackWithDamage(damage);
    }

    boolean isDead() {
        return numberOfUnits == 0;
    }

    Optional<Group> selectGroupToAttack(List<Group> others) {
        return others.stream().
                max(Comparator.comparing(comparator -> comparator.damageFrom(this)))
                .filter(comparator -> comparator.damageFrom(this) > 0);
    }

    int getEffectivePower() {
        return groupData.attackDamage * numberOfUnits;
    }

    int getInitiative() {
        return groupData.initiative;
    }

    int damageFrom(Group other) {
        String attackTypeOther = other.groupData.attackType;
        if(groupData.weaknesses.contains(attackTypeOther)) {
            return 2*other.getEffectivePower();
        } else if(groupData.immunities.contains(attackTypeOther)) {
            return 0;
        }
        return other.getEffectivePower();
    }

    private void attackWithDamage(int damage) {
        numberOfUnits = Integer.max(0, numberOfUnits - damage/groupData.hitPoints);
    }

    @Value
    private class GroupData {
        private final int hitPoints;
        private final int attackDamage;
        private final String attackType;
        private final int initiative;
        private final List<String> weaknesses;
        private final List<String> immunities;

    }
}
