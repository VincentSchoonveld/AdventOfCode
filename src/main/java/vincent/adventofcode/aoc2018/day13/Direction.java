package vincent.adventofcode.aoc2018.day13;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@AllArgsConstructor
enum Direction {
    UP('^'), DOWN('v'), RIGHT('>'), LEFT('<');

    private char c;

    static Direction forChar(char c) {
        return Arrays.stream(Direction.values())
                .filter(a -> a.c == c)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no direction for char: "+c));
    }

    Direction next(char c, int turns) {
        switch (c) {
            case '/':
                return slash();
            case '\\':
                return opposite().next('/', turns);
            case '+':
                if(turns%3 == 0) {
                    return counterClockWise();
                } else if(turns%3 == 1) {
                    return this;
                } else {
                    return clockWise();
                }
            default:
                return this;
        }
    }

    Direction slash() {
        return BidirectionalDirectionMap.SLASH.getValue(this);
    }

    Direction opposite() {
        return BidirectionalDirectionMap.OPPOSITE.getValue(this);
    }

    Direction clockWise() {
        return BidirectionalDirectionMap.CLOCK_WISE.getValue(this);
    }

    Direction counterClockWise() {
        return BidirectionalDirectionMap.CLOCK_WISE.getKey(this);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class BidirectionalDirectionMap {
        static final BidirectionalDirectionMap CLOCK_WISE = BidirectionalDirectionMap.map(UP, RIGHT, RIGHT, DOWN, DOWN, LEFT, LEFT, UP);
        static final BidirectionalDirectionMap OPPOSITE = BidirectionalDirectionMap.map(UP, DOWN, DOWN, UP, LEFT, RIGHT, RIGHT, LEFT);
        static final BidirectionalDirectionMap SLASH = BidirectionalDirectionMap.map(UP, RIGHT, RIGHT, UP, DOWN, LEFT, LEFT, DOWN);

        private Map<Direction, Direction> map;

        static BidirectionalDirectionMap map(Direction... keysValues) {
            Map<Direction, Direction> map = new EnumMap<>(Direction.class);
            for(int i=0; i<keysValues.length; i=i+2) {
                map.put(keysValues[i], keysValues[i+1]);
            }
            return new BidirectionalDirectionMap(map);
        }

        Direction getValue(Direction key) {
            return map.get(key);
        }

        Direction getKey(Direction value) {
            return map.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(value))
                    .findAny()
                    .map(Map.Entry::getKey)
                    .orElse(null);
        }
    }
}
