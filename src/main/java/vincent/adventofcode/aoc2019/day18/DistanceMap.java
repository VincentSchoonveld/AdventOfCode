package vincent.adventofcode.aoc2019.day18;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class DistanceMap implements Serializable {

    private HashMap<KeyPair, DistanceAndKeysNeeded> map;

    static DistanceMap forTunnelMap(TunnelMap tunnelMap) {
        HashMap<KeyPair, DistanceAndKeysNeeded> map = new HashMap<>();
        List<Character> keys = keys();
        Index atIndex = tunnelMap.getIndexForKey('@');
        for(Character key : keys) {
            System.out.println(key);
            Index finishIndex = tunnelMap.getIndexForKey(key);
            map.put(new KeyPair(key, '@'), distanceAndKeysNeeded(atIndex, finishIndex, tunnelMap));
        }
        while(!keys.isEmpty()) {
            System.out.println(keys.size());
            Character startKey = keys.remove(0);
            for(Character finishKey : keys) {
                Index startIndex = tunnelMap.getIndexForKey(startKey);
                Index finishIndex = tunnelMap.getIndexForKey(finishKey);
                if(!startIndex.isSameQuadrant(finishIndex)) {
                    DistanceAndKeysNeeded distanceAndKeysNeededForStartKey = map.get(new KeyPair('@', startKey));
                    DistanceAndKeysNeeded distanceAndKeysNeededForFinishKey = map.get(new KeyPair('@', finishKey));
                    int distance = distanceAndKeysNeededForStartKey.getDistance()
                            + distanceAndKeysNeededForFinishKey.getDistance()
                            + startIndex.differenceFactorWithStart(finishIndex);

                    HashSet<Character> keysNeeded = new HashSet<>(distanceAndKeysNeededForStartKey.getKeysNeeded());
                    keysNeeded.addAll(distanceAndKeysNeededForFinishKey.getKeysNeeded());
                    map.put(new KeyPair(startKey, finishKey), new DistanceAndKeysNeeded(distance, keysNeeded));
                } else {
                    map.put(new KeyPair(startKey, finishKey), distanceAndKeysNeeded(startIndex, finishIndex, tunnelMap));
                }
            }
        }
        return new DistanceMap(map);
    }

    DistanceAndKeysNeeded get(KeyPair keyPair) {
        return map.get(keyPair);
    }

    DistanceAndKeysNeeded getToStartPart2(char key) {
        DistanceAndKeysNeeded distanceAndKeysNeeded = map.get(new KeyPair(key, '@'));
        return new DistanceAndKeysNeeded(distanceAndKeysNeeded.getDistance()-2, distanceAndKeysNeeded.getKeysNeeded());
    }

    private static DistanceAndKeysNeeded distanceAndKeysNeeded(Index startIndex,
                                                               Index finishIndex,
                                                               TunnelMap tunnelMap) {
        Map<Index, Index> map = tunnelMap.getNonWallNeighbors(startIndex)
                .stream()
                .collect(Collectors.toMap(a -> a, a -> startIndex));

        while (!map.containsKey(finishIndex)) {
            List<Index> collect = map.keySet().stream()
                    .distinct()
                    .filter(a -> !map.containsValue(a))
                    .collect(Collectors.toList());
            for(Index index : collect) {
                List<Index> nonWallNeighbors = tunnelMap.getNonWallNeighbors(index);
                for(Index to: nonWallNeighbors) {
                    map.putIfAbsent(to, index);
                }
            }
        }
        HashSet<Character> keys = new HashSet<>();
        Index index = map.get(finishIndex);
        int distance = 1;
        while(!index.equals(startIndex)) {
            distance++;
            Character character = tunnelMap.get(index);
            if(Character.isUpperCase(character)) {
                keys.add(Character.toLowerCase(character));
            }
            index = map.get(index);
        }
        return new DistanceAndKeysNeeded(distance, keys);
    }

    private static List<Character> keys() {
        return IntStream.range(0,26).mapToObj(i -> (char) (i+'a')).collect(Collectors.toList());
    }
}
