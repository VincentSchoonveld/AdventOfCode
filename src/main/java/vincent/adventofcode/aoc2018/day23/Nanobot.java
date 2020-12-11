package vincent.adventofcode.aoc2018.day23;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
class Nanobot {
    private Index index;
    @Getter
    private int beamSize;

    static Nanobot forInput(String input) {
        List<Integer> numbers = Arrays.stream(input.split("[^-?0-9]+"))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new Nanobot(new Index(numbers.get(0), numbers.get(1), numbers.get(2)), numbers.get(3));
    }

    boolean hasInRange(Nanobot other) {
        return index.distanceTo(other.index) <= beamSize;
    }

    int distanceToOrigin() {
        return index.distanceTo(new Index(0,0,0));
    }

    List<Index> getIndicesInRange() {
        List<Index> list = new ArrayList<>();
        for(int x=-beamSize; x<=beamSize; x++) {
            int xAbs = Math.abs(x);
            for(int y = -beamSize+ xAbs; y<=beamSize- xAbs; y++) {
                int yAbs = Math.abs(y);
                for(int z = -beamSize+ xAbs + yAbs; z<=beamSize- xAbs - yAbs; z++) {
                    list.add(new Index(x,y,z));
                }
            }
        }
        return list;
    }
}
