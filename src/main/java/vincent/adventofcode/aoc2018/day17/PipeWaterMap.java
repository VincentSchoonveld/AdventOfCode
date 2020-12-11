package vincent.adventofcode.aoc2018.day17;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static vincent.adventofcode.aoc2018.day17.MapObject.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class PipeWaterMap {
    private final Map<Index, MapObject> map;
    private final int maxY;

    static PipeWaterMap forInput(List<String> input) {
        Map<Index, MapObject> collect = input.stream()
                .flatMap(Index::forInput)
                .distinct()
                .collect(Collectors.toMap(i -> i, i -> PIPE));
        collect.put(new Index(500, 0), SOURCE);
        int maxY = collect.entrySet()
                .stream()
                .filter(a -> a.getValue() == PIPE)
                .map(Map.Entry::getKey)
                .map(Index::getY)
                .reduce(Integer::max)
                .orElseThrow(() -> new IllegalStateException("There are no pipes"));
        return new PipeWaterMap(collect, maxY);
    }

    void addWater() {
        addWater(new Index(500, 0), Direction.SOUTH, false);
    }

    private void addWater(Index index, Direction direction, boolean movedOtherWay) {
        if(index.getY() > maxY) {
            return;
        }
        if(!map.containsKey(index)) {
            map.put(index, MapObject.WATER);
        } else if(isNotBlocked(index.getSouth())) {
            addWater(index.getSouth(), Direction.SOUTH, false);
        } else if(direction == Direction.SOUTH) {
            addWater(index, Direction.WEST, false);
            addWater(index, Direction.EAST, false);
        } else if(direction == Direction.NORTH) {
            if(isNotBlocked(index)) {
                addWater(index, Direction.SOUTH, false);
            } else {
                addWater(index.getNorth(), Direction.NORTH, false);
            }
        } else if(isNotBlocked(index.next(direction))) {
            addWater(index.next(direction), direction, movedOtherWay);
        } else if(movedOtherWay) {
            markRowBlocked(index);
            addWater(index, Direction.NORTH, false);
        } else {
            addWater(index, direction.opposite(), true);
        }
    }

    private void markRowBlocked(Index index) {
        while(map.get(index) == WATER) {
            index = index.getEast();
        }
        index = index.getWest();
        while(map.get(index) == WATER) {
            map.put(index, STILL_WATER);
            index = index.getWest();
        }
    }

    private boolean isNotBlocked(Index index) {
        if(!map.containsKey(index)) {
            return true;
        }
        MapObject mapObject = map.get(index);
        return mapObject != PIPE && mapObject != STILL_WATER;
    }

    long getAmountOfWater() {
        int minY = getIndexForGetterAndBinaryOperator(Index::getY, Integer::min);
        int maxY = getIndexForGetterAndBinaryOperator(Index::getY, Integer::max);
        return map.entrySet()
                .stream()
                .filter(a -> a.getValue() != PIPE)
                .map(Map.Entry::getKey)
                .map(Index::getY)
                .filter(y -> y >= minY && y <= maxY)
                .count();
    }

    private int getIndexForGetterAndBinaryOperator(Function<Index, Integer> function,
                                                   BinaryOperator<Integer> binaryOperator) {
        return map.entrySet()
                .stream()
                .filter(a -> a.getValue() == PIPE)
                .map(Map.Entry::getKey)
                .map(function)
                .reduce(binaryOperator)
                .orElseThrow(() -> new IllegalStateException("There are no pipes"));
    }

    @Override
    public String toString() {
        int minY = getIndexForGetterAndBinaryOperator(Index::getY, Integer::min);
        int maxY = getIndexForGetterAndBinaryOperator(Index::getY, Integer::max);
        int minX = getIndexForGetterAndBinaryOperator(Index::getX, Integer::min);
        int maxX = getIndexForGetterAndBinaryOperator(Index::getX, Integer::max);
        StringBuilder stringBuilder = new StringBuilder();
        for(int y=minY; y<=maxY; y++) {
            for(int x=minX; x<=maxX; x++) {
                MapObject mapObject = map.get(new Index(x, y));
                String string = isNull(mapObject) ? "." : mapObject.toString();
                stringBuilder.append(string);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getSize() {
        int maxY = map.keySet()
                .stream()
                .map(Index::getY)
                .reduce(Integer::max)
                .orElseThrow(() -> new IllegalStateException("There are no pipes"));
        int maxYForPipes = getIndexForGetterAndBinaryOperator(Index::getY, Integer::max);
        return maxY + "   " + maxYForPipes;
    }
}
