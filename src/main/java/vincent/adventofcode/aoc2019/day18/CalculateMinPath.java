package vincent.adventofcode.aoc2019.day18;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@AllArgsConstructor
class CalculateMinPath {
    private TunnelMap tunnelMap;
    private DistanceMap distanceMap;

    int getMinPart1() {
        List<Character> min = getMin(this::totalDistancePart1);
        return totalDistancePart1(min);
    }

    int getMinPart2() {
        List<Character> min = getMin(this::totalDistancePart2);
        return totalDistancePart2(min);
    }

    private List<Character> getMin(Function<List<Character>, Integer> distance) {
        List<Character> min = getDefaultOrder();
        List<Character> next = minForOneIteration(min, distance);
        while(distance.apply(next) < distance.apply(min)) {
            min = next;
            next = minForOneIteration(min, distance);
        }
        return min;
    }

    private List<Character> minForOneIteration(List<Character> current,
                                               Function<List<Character>, Integer> distance) {
        List<Character> min = new ArrayList<>(current);
        int currentMin = distance.apply(current);

        for(int startRemove=0; startRemove<current.size(); startRemove++) {
            for(int sizeRemove=1; sizeRemove<=current.size()-startRemove; sizeRemove++) {
                for(int add=0; add<=current.size()-sizeRemove; add++) {
                    List<Character> var = new ArrayList<>(current.subList(0, startRemove));
                    var.addAll(current.subList(startRemove+sizeRemove, current.size()));
                    var.addAll(add, current.subList(startRemove, startRemove+sizeRemove));
                    if(isValidList(var)
                            && distance.apply(var) < currentMin) {
                        min = var;
                        currentMin = distance.apply(var);
                    }
                }
            }
        }
        return min;
    }

    private List<Character> getDefaultOrder() {
        List<Character> foundCharacters = new ArrayList<>();
        Optional<Character> nextPossible = getNextPossible(foundCharacters, '@');
        while(nextPossible.isPresent()) {
            foundCharacters.add(nextPossible.get());
            nextPossible = getNextPossible(foundCharacters, foundCharacters.get(foundCharacters.size()-1));
        }
        return foundCharacters;
    }

    private Optional<Character> getNextPossible(List<Character> foundCharacters,
                                                Character current) {
        return keys()
                .filter(key -> !foundCharacters.contains(key))
                .filter(key -> foundCharacters.containsAll(distanceMap.get(new KeyPair(key, current)).getKeysNeeded()))
                .findAny();
    }

    private static Stream<Character> keys() {
        return IntStream.range(0,26).mapToObj(i -> (char) (i + 'a'));
    }

    private boolean isValidList(List<Character> var) {
        return IntStream.range(1, var.size()-1)
                .allMatch(i -> var.subList(0, i).containsAll(distanceMap.get(new KeyPair(var.get(i-1), var.get(i))).getKeysNeeded()));
    }

    private int totalDistancePart1(List<Character> foundCharacters) {
        int distanceFromStartToFirstKey = distanceMap.get(new KeyPair(foundCharacters.get(0), '@')).getDistance();
        int distancesBetweenKeys = IntStream.range(0, foundCharacters.size() - 1)
                .map(i -> distanceMap.get(new KeyPair(foundCharacters.get(i), foundCharacters.get(i + 1))).getDistance())
                .sum();
        return distancesBetweenKeys + distanceFromStartToFirstKey;
    }

    private int totalDistancePart2(List<Character> foundCharacters) {
        int distance = 0;
        for(int i=0; i<foundCharacters.size(); i++) {
            Character character = getLastCharacterInSameQuadrant(foundCharacters, i);
            if(character == null) {
                distance += distanceMap.getToStartPart2(foundCharacters.get(i)).getDistance();
            } else {
                distance += distanceMap.get(new KeyPair(foundCharacters.get(i), character)).getDistance();
            }
        }
        return distance;
    }

    private Character getLastCharacterInSameQuadrant(List<Character> foundCharacters, int current) {
        Quadrant quadrantForKey = tunnelMap.getQuadrantForKey(foundCharacters.get(current));
        for(int i=current-1; i>= 0; i--) {
            if(tunnelMap.getQuadrantForKey(foundCharacters.get(i)) == quadrantForKey) {
                return foundCharacters.get(i);
            }
        }
        return null;
    }
}
