package vincent.adventofcode.aoc2018.day15.creatures;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vincent.adventofcode.aoc2018.day15.Index;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Creatures {
    private final List<Creature> creatureList;
    private final int size;
    private boolean anyElfsDied;

    public static Creatures forInputAndAttackPowerElfs(List<String> input, int attackPowerElfs) {
        List<Creature> creatureList = new ArrayList<>();
        for(int y=0; y<input.size(); y++) {
            String s = input.get(y);
            for(int x=0; x<s.length(); x++) {
                if(s.charAt(x) == 'E') {
                    creatureList.add(new Elf(new Index(x,y)));
                } else if(s.charAt(x) == 'G') {
                    creatureList.add(new Goblin(new Index(x,y), attackPowerElfs));
                }
            }
        }
        return new Creatures(creatureList, input.size(), false);
    }

    public Optional<Creature> nextCreature(int turns) {
        return creatureList.stream()
                .filter(a -> a.getTurns() == turns)
                .min(Comparator.comparing(Creature::getPosition));
    }

    public Optional<Creature> nextAttack(Creature creature) {
        List<Index> neighborIndexList = creature.getPosition().neighbors().collect(Collectors.toList());
        return creatureList.stream()
                .filter(creature1 -> creature1.isElf() != creature.isElf())
                .filter(creature1 -> neighborIndexList.contains(creature1.getPosition()))
                .min(Comparator.comparing(Creature::getHitPoints).thenComparing(Creature::getPosition));
    }

    public List<Index> occupiedSpaces() {
        return creatureList.stream()
                .map(Creature::getPosition)
                .collect(Collectors.toList());
    }

    public List<Creature> enemies(Creature creature) {
        return creatureList.stream()
                .filter(a -> a.isElf() != creature.isElf())
                .collect(Collectors.toList());
    }

    public boolean isNotFinished() {
        return creatureList.stream().collect(Collectors.groupingBy(Creature::isElf)).size() == 2;
    }

    public int sumHitPoints() {
        return creatureList.stream().mapToInt(Creature::getHitPoints).sum();
    }

    public int minTurns() {
        return creatureList.stream()
                .mapToInt(Creature::getTurns)
                .min()
                .orElseThrow(() -> new IllegalStateException("There are no creatures."));
    }

    public void attack(Creature enemy) {
        boolean enemyDies = enemy.attack();
        if(enemyDies) {
            creatureList.remove(enemy);
            if(enemy.isElf()) {
                anyElfsDied = true;
            }
        }
    }

    public boolean anyElfsDied() {
        return anyElfsDied;
    }
}
