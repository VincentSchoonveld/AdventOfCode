package vincent.adventofcode.aoc2018.day13;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class TrackMap {
    private Map<Index, Character> map;

    static TrackMap forInput(List<String> input) {
        Map<Index, Character> map = new HashMap<>();
        for(int y=0; y<input.size(); y++) {
            String s = input.get(y);
            for(int x=0; x<s.length(); x++) {
                map.put(new Index(x,y), charToTrackChar(s.charAt(x)));
            }
        }
        return new TrackMap(map);
    }

    char get(Index index) {
        return map.getOrDefault(index, ' ');
    }

    private static char charToTrackChar(char aChar) {
        switch (aChar) {
            case '<':
            case '>':
                return '-';
            case 'v':
            case '^':
                return '|';
            default:
                return aChar;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int y=0; y<151; y++) {
            for(int x=0; x<151; x++) {
                stringBuilder.append(get(new Index(x,y)));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
