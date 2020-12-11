package vincent.adventofcode.aoc2018.day24.groups;

import lombok.AllArgsConstructor;

import java.util.Comparator;

@AllArgsConstructor
class TargetSelectionComparator implements Comparator<Group> {

    private Group group;

    @Override
    public int compare(Group group1, Group group2) {
        Comparator<Group> comparator = Comparator.comparing(g -> g.damageFrom(group));
        return comparator.thenComparing(Group::getEffectivePower)
                .thenComparing(Group::getInitiative)
                .compare(group1, group2);
    }
}
