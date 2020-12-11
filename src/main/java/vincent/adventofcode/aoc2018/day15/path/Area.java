package vincent.adventofcode.aoc2018.day15.path;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vincent.adventofcode.aoc2018.day15.Index;
import vincent.adventofcode.aoc2018.day15.creatures.Creature;
import vincent.adventofcode.aoc2018.day15.creatures.Creatures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Area {
    private final List<Index> indexList;

    public static Area forInput(List<String> input) {
        List<Index> indexList = new ArrayList<>();
        for(int y=0; y<input.size(); y++) {
            String s = input.get(y);
            for(int x = 0; x<s.length(); x++) {
                if(s.charAt(x) != '#') {
                    indexList.add(new Index(x,y));
                }
            }
        }
        return new Area(indexList);
    }

    public Optional<Index> nextMove(Creature creature,
                                    Creatures creatures) {
        return nextEnemyPosition(creature, creatures)
                .flatMap(enemyPosition -> firstStep(creature, enemyPosition, creatures.occupiedSpaces()));
    }

    private Optional<Index> nextEnemyPosition(Creature creature,
                                              Creatures creatures) {
        List<Index> enemies = creatures.enemies(creature).stream().map(Creature::getPosition).collect(Collectors.toList());
        List<Index> freeSpaces = freeSpaces(creatures);
        freeSpaces.add(creature.getPosition());
        Set<Index> reachedSpaces = new HashSet<>();
        Set<Index> neighborSpaces = new HashSet<>();
        neighborSpaces.add(creature.getPosition());
        boolean lastChanged = true;
        while(lastChanged
                && neighborSpaces.stream().noneMatch(enemies::contains)) {
            neighborSpaces.removeIf(a -> !freeSpaces.contains(a));
            lastChanged = reachedSpaces.addAll(neighborSpaces);
            reachedSpaces.stream()
                    .flatMap(Index::neighbors)
                    .forEach(neighborSpaces::add);
        }
        return reachedSpaces.stream()
                .filter(a -> a.neighbors().anyMatch(enemies::contains))
                .min(Index::compareTo);

    }

    private Optional<Index> firstStep(Creature creature,
                                      Index nextToEnemyPosition,
                                      List<Index> indices) {
        ArrayList<Index> indices1 = new ArrayList<>(indexList);
        indices1.removeAll(indices);
        indices1.add(creature.getPosition());
        indices1.add(nextToEnemyPosition);

        return FirstStep.first(indices1, creature.getPosition(), nextToEnemyPosition);
    }

    private List<Index> freeSpaces(Creatures creatures) {
        List<Index> occupiedSpaces = creatures.occupiedSpaces();
        return indexList.stream()
                .filter(index -> !occupiedSpaces.contains(index))
                .collect(Collectors.toList());
    }
}
