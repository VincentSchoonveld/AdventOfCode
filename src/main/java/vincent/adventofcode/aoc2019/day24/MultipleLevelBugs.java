package vincent.adventofcode.aoc2019.day24;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class MultipleLevelBugs {

    private Map<Integer, Map<Index, InfectionState>> levelBugMap;

    static MultipleLevelBugs forInput(List<String> input, int levels) {
        Map<Index, InfectionState> map = indexInfectionStateMap(input);
        Map<Integer, Map<Index, InfectionState>> levelBugMap = new HashMap<>();
        levelBugMap.put(0, map);
        Map<Index, InfectionState> emptyMap = emptyMap(input.size());
        for(int i=1; i<=levels; i++) {
            levelBugMap.put(-i, new HashMap<>(emptyMap));
            levelBugMap.put(i, new HashMap<>(emptyMap));
        }
        return new MultipleLevelBugs(levelBugMap);
    }

    private static Map<Index, InfectionState> indexInfectionStateMap(List<String> input) {
        Map<Index, InfectionState> map = new HashMap<>();
        for (int y = 0; y < input.size(); y++) {
            String s = input.get(y);
            for(int x = 0; x< s.length(); x++) {
                if(y==input.size()/2 && x == input.size()/2) {
                    map.put(new Index(x,y), InfectionState.UNKNOWN);
                } else {
                    map.put(new Index(x,y), InfectionState.forInput(s.charAt(x)));
                }
            }
        }
        return map;
    }

    private static Map<Index, InfectionState> emptyMap(int size) {
        Map<Index, InfectionState> emptyMap = new HashMap<>();
        for (int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                if(y==size/2 && x == size/2) {
                    emptyMap.put(new Index(x,y), InfectionState.UNKNOWN);
                } else {
                    emptyMap.put(new Index(x,y), InfectionState.NOT_INFECTED);
                }
            }
        }
        return emptyMap;
    }

    long countBugs() {
        return levelBugMap.values()
                .stream()
                .flatMap(bugMap -> bugMap.values().stream())
                .filter(infectionState -> infectionState == InfectionState.IS_INFECTED)
                .count();
    }

    void next() {
        this.levelBugMap = levelBugMap.keySet()
                .stream()
                .collect(Collectors.toMap(level -> level, this::nextLevelMap));
    }

    private Map<Index, InfectionState> nextLevelMap(int level) {
        return levelBugMap.get(level)
                .keySet()
                .stream()
                .collect(Collectors.toMap(index -> index, index -> nextInfectionState(level, index)));
    }

    private InfectionState nextInfectionState(int level, Index index) {
        InfectionState infectionState = levelBugMap.get(level).get(index);
        long neighborsInfected = countNeighborsInfected(level, index);
        return infectionState.nextInfectionState(neighborsInfected);
    }

    private long countNeighborsInfected(int level, Index index) {
        long numberInfectedOnSameLevel = index.getNeighbors()
                .stream()
                .map(neighborIndex -> levelBugMap.get(level).getOrDefault(neighborIndex, InfectionState.NOT_INFECTED))
                .filter(infectionState -> infectionState == InfectionState.IS_INFECTED)
                .count();
        long numberOfInfectedOnOtherLevel = 0;
        if(index.getX() == 0 && index.getY() == 0) {
            numberOfInfectedOnOtherLevel = countInfected(level-1, new Index(2,1), new Index(1,2));
        } else if(index.getX() == 0 && index.getY() == 4) {
            numberOfInfectedOnOtherLevel = countInfected(level-1, new Index(2,3), new Index(1,2));
        } else if(index.getX() == 4 && index.getY() == 0) {
            numberOfInfectedOnOtherLevel = countInfected(level-1, new Index(2,1), new Index(3,2));
        } else if(index.getX() == 4 && index.getY() == 4) {
            numberOfInfectedOnOtherLevel = countInfected(level-1, new Index(2,3), new Index(3,2));
        } else if(index.getX() == 0) {
            numberOfInfectedOnOtherLevel = countInfected(level-1, new Index(1,2));
        } else if(index.getX() == 4) {
            numberOfInfectedOnOtherLevel = countInfected(level-1, new Index(3,2));
        } else if(index.getY() == 0) {
            numberOfInfectedOnOtherLevel = countInfected(level-1, new Index(2,1));
        } else if(index.getY() == 4) {
            numberOfInfectedOnOtherLevel = countInfected(level-1, new Index(2,3));
        } else if(index.getY() == 2 && index.getX() == 1) {
            numberOfInfectedOnOtherLevel = countInfected(level+1, new Index(0,0), new Index(0,1), new Index(0,2), new Index(0,3), new Index(0,4));
        } else if(index.getY() == 2 && index.getX() == 3) {
            numberOfInfectedOnOtherLevel = countInfected(level+1, new Index(4,0), new Index(4,1), new Index(4,2), new Index(4,3), new Index(4,4));
        } else if(index.getX() == 2 && index.getY() == 1) {
            numberOfInfectedOnOtherLevel = countInfected(level+1, new Index(0,0), new Index(1,0), new Index(2,0), new Index(3,0), new Index(4,0));
        } else if(index.getX() == 2 && index.getY() == 3) {
            numberOfInfectedOnOtherLevel = countInfected(level+1, new Index(0,4), new Index(1,4), new Index(2,4), new Index(3,4), new Index(4,4));
        }
        return numberInfectedOnSameLevel+numberOfInfectedOnOtherLevel;
    }

    private long countInfected(int level, Index... indices) {
        return Arrays.stream(indices)
                .map(index -> levelBugMap.getOrDefault(level, new HashMap<>()).getOrDefault(index, InfectionState.NOT_INFECTED))
                .filter(a -> a == InfectionState.IS_INFECTED)
                .count();
    }
}
