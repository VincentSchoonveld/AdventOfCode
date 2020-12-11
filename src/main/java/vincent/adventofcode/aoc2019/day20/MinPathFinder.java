package vincent.adventofcode.aoc2019.day20;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class MinPathFinder {
    private PlutoMap plutoMap;

    int findMinPathFromStartToFinish(boolean isLevelDependent) {
        Set<ThreeIndex> foundIndices = new HashSet<>();
        foundIndices.add(plutoMap.getStartIndex().withLevel(0));
        ThreeIndex finishIndex = plutoMap.getFinishIndex().withLevel(0);
        int pathDistance = 0;
        while(!foundIndices.contains(finishIndex)) {
            pathDistance++;
            List<ThreeIndex> collect = foundIndices.stream()
                    .flatMap(a -> a.toIndex().getNeighbors().stream().map(b -> b.withLevel(a.getLevel())))
                    .filter(a -> !foundIndices.contains(a))
                    .filter(a -> plutoMap.contains(a.toIndex()))
                    .collect(Collectors.toList());
            List<ThreeIndex> collect1 = foundIndices.stream()
                    .filter(a -> plutoMap.get(a.toIndex()).getSpaceType() == SpaceType.WARP)
                    .map(a -> {
                        Space space = plutoMap.get(a.toIndex());
                        Index indexForWarpAndWarpPosition = plutoMap.getIndexForWarpAndWarpPosition(space.getWarp(), space.getWarpPosition().opposite());
                        if(isLevelDependent) {
                            if(space.getWarpPosition() == WarpPosition.INSIDE && a.getLevel()<15) {
                                return indexForWarpAndWarpPosition.withLevel(a.getLevel()+1);
                            } else if(space.getWarpPosition() == WarpPosition.OUTSIDE && a.getLevel()>0) {
                                return indexForWarpAndWarpPosition.withLevel(a.getLevel()-1);
                            } else {
                                return a;
                            }
                        } else {
                            return indexForWarpAndWarpPosition.withLevel(0);
                        }
                    })
                    .filter(a -> !foundIndices.contains(a))
                    .collect(Collectors.toList());
            foundIndices.addAll(collect);
            foundIndices.addAll(collect1);
        }
        return pathDistance;
    }
}
