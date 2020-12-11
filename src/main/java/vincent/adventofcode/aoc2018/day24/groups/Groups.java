package vincent.adventofcode.aoc2018.day24.groups;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Groups {
    private final List<Group> groupList;

    public List<AttackPair> selectTarget(Groups targetGroups) {
        List<AttackPair> attackPairs = new ArrayList<>();
        List<Group> targets = new ArrayList<>(targetGroups.groupList);
        groupList.stream().sorted()
                .forEach(group -> group.selectGroupToAttack(targets)
                        .ifPresent(target -> {
                            attackPairs.add(new AttackPair(group, target));
                            targets.remove(target);
                        }));
        return attackPairs;
    }

    public boolean removeTheDead() {
        groupList.removeIf(Group::isDead);
        return allDead();
    }

    private boolean allDead() {
        return groupList.isEmpty();
    }
}
