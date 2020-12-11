package vincent.adventofcode.aoc2019.day20;

import lombok.Getter;

@Getter
class Space {
    private CharPair warp;
    private SpaceType spaceType;
    private WarpPosition warpPosition;

    Space(PossibleWarp possibleWarp) {
        if(possibleWarp == null) {
            this.warp = null;
            this.spaceType = SpaceType.REGULAR;
            this.warpPosition = WarpPosition.NONE;
        } else if(possibleWarp.getCharPair().isStart()) {
            this.warp = null;
            this.spaceType = SpaceType.START;
            this.warpPosition = WarpPosition.NONE;
        } else if(possibleWarp.getCharPair().isFinish()) {
            this.warp = null;
            this.spaceType = SpaceType.FINISH;
            this.warpPosition = WarpPosition.NONE;
        } else {
            this.warp = possibleWarp.getCharPair();
            this.spaceType = SpaceType.WARP;
            this.warpPosition = WarpPosition.forBoolean(possibleWarp.isInside());
        }
    }
}
