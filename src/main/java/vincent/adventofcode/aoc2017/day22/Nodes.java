package vincent.adventofcode.aoc2017.day22;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Nodes {
    private Map<Index, InfectionState> infectionMap;

    static Nodes forInput(List<String> input) {
        Map<Index, InfectionState> infectionMap = new HashMap<>();
        for(int y=0; y<input.size(); y++) {
            String s = input.get(y);
            for(int x = 0; x< s.length(); x++) {
                if(s.charAt(x) == '#') {
                    infectionMap.put(new Index(x,y), InfectionState.INFECTED);
                } else {
                    infectionMap.put(new Index(x,y), InfectionState.CLEAN);
                }
            }
        }
        return new Nodes(infectionMap);
    }

    InfectionState getInfectionState(Index index) {
        return infectionMap.getOrDefault(index, InfectionState.CLEAN);
    }

    boolean changeInfection(Index index) {
        InfectionState orDefault = getInfectionState(index);
        switch (orDefault) {
            case CLEAN: infectionMap.put(index, InfectionState.WEAKENED); break;
            case INFECTED: infectionMap.put(index, InfectionState.FLAGGED); break;
            case FLAGGED: infectionMap.put(index, InfectionState.CLEAN); break;
            case WEAKENED: infectionMap.put(index, InfectionState.INFECTED);
            default: return true;
        }
        return false;
    }

    @Override
    public String toString() {
        int minX = infectionMap.keySet().stream().mapToInt(Index::getX).min().orElse(0);
        int maxX = infectionMap.keySet().stream().mapToInt(Index::getX).max().orElse(0);
        int minY = infectionMap.keySet().stream().mapToInt(Index::getY).min().orElse(0);
        int maxY = infectionMap.keySet().stream().mapToInt(Index::getY).max().orElse(0);
        StringBuilder stringBuilder = new StringBuilder();
        for(int y=minY; y<=maxY; y++) {
            for(int x=minX; x<=maxX; x++) {
                InfectionState get = getInfectionState(new Index(x,y));
                switch (get) {
                    case CLEAN: stringBuilder.append("."); break;
                    case FLAGGED: stringBuilder.append("F"); break;
                    case INFECTED: stringBuilder.append("#"); break;
                    case WEAKENED: stringBuilder.append("W");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
