package vincent.adventofcode.aoc2018.day24;

import lombok.AllArgsConstructor;
import vincent.adventofcode.aoc2018.day24.groups.AttackPair;
import vincent.adventofcode.aoc2018.day24.groups.Groups;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Field {
    private final Groups infections;
    private final Groups immunes;

    boolean play() {
        final List<AttackPair> attackPairs = selectTargetPhase();
        attackPhase(attackPairs);
        return removeTheDead();
    }

    private List<AttackPair> selectTargetPhase() {
        final List<AttackPair> attackPairsInfections = infections.selectTarget(immunes);
        final List<AttackPair> attackPairsImmunes = immunes.selectTarget(infections);
        return addTwoLists(attackPairsInfections, attackPairsImmunes);
    }

    private void attackPhase(final List<AttackPair> attackPairs) {
        attackPairs.stream().sorted().forEach(AttackPair::attack);
    }

    private static <T> List<T> addTwoLists(final List<T> list1, final List<T> list2) {
        List<T> list = new ArrayList<>(list1);
        list.addAll(list2);
        return list;
    }

    private boolean removeTheDead() {
        boolean allInfections = infections.removeTheDead();
        boolean allImmunesDead = immunes.removeTheDead();
        return allInfections || allImmunesDead;
    }
}
