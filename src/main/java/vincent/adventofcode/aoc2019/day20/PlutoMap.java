package vincent.adventofcode.aoc2019.day20;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
class PlutoMap {
    private Map<Index, Space> map;

    static PlutoMap forInput(List<String> ls) {
        Map<Index, Space> map = new HashMap<>();
        for(int y=0; y<ls.size(); y++) {
            String s = ls.get(y);
            for(int x=0; x<s.length(); x++) {
                if(s.charAt(x) == '.') {
                    PossibleWarp possibleWarp = possiblyWarpForInputAndIndices(ls, x, y);
                    map.put(new Index(x,y), new Space(possibleWarp));
                }
            }
        }
        return new PlutoMap(map);
    }

    private static PossibleWarp possiblyWarpForInputAndIndices(List<String> input, int x, int y) {
        if(Character.isLetter(input.get(y-1).charAt(x))) {
            CharPair charPair = new CharPair(input.get(y - 2).charAt(x), input.get(y - 1).charAt(x));
            boolean isInside = y-2 != 0;
            return new PossibleWarp(charPair, isInside);
        } else if(Character.isLetter(input.get(y+1).charAt(x))) {
            CharPair charPair = new CharPair(input.get(y + 1).charAt(x), input.get(y + 2).charAt(x));
            boolean isInside = y+2 != input.size()-1;
            return new PossibleWarp(charPair, isInside);
        } else if(Character.isLetter(input.get(y).charAt(x-1))) {
            CharPair charPair = new CharPair(input.get(y).charAt(x - 2), input.get(y).charAt(x - 1));
            boolean isInside = x-2 != 0;
            return new PossibleWarp(charPair, isInside);
        } else if(Character.isLetter(input.get(y).charAt(x+1))) {
            CharPair charPair = new CharPair(input.get(y).charAt(x + 1), input.get(y).charAt(x + 2));
            boolean isInside = x+2 != input.get(y).length()-1;
            return new PossibleWarp(charPair, isInside);
        }
        return null;
    }

    Index getStartIndex() {
        return getIndexForSpaceType(SpaceType.START);
    }

    Index getFinishIndex() {
        return getIndexForSpaceType(SpaceType.FINISH);
    }

    private Index getIndexForSpaceType(SpaceType spaceType) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getSpaceType() == spaceType)
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no index for space type "+ spaceType));
    }

    public boolean contains(Index index) {
        return map.containsKey(index);
    }

    public Space get(Index index) {
        return map.get(index);
    }

    List<Index> getIndicesForWarp(CharPair warp) {
        return map.entrySet().stream()
                .filter(a -> a.getValue().getSpaceType() == SpaceType.WARP)
                .filter(a -> a.getValue().getWarp().equals(warp))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    Index getIndexForWarpAndWarpPosition(CharPair warp, WarpPosition warpPosition) {
        return map.entrySet().stream()
                .filter(a -> a.getValue().getSpaceType() == SpaceType.WARP)
                .filter(a -> a.getValue().getWarp().equals(warp) && a.getValue().getWarpPosition() == warpPosition)
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
