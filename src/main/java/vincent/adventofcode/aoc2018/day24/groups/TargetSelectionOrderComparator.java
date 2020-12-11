package vincent.adventofcode.aoc2018.day24.groups;

import java.util.Comparator;

class TargetSelectionOrderComparator implements Comparator<Group> {

    @Override
    public int compare(Group group1, Group group2) {
        return Comparator.comparing(Group::getEffectivePower)
                .thenComparing(Group::getInitiative)
                .compare(group1, group2);
    }
}
