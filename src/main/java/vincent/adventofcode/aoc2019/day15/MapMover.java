package vincent.adventofcode.aoc2019.day15;

import java.util.List;

class MapMover {
    private final IntcodeProgram intcodeProgram;
    private BoxMap boxMap;
    private Index position;

    MapMover(IntcodeProgram intcodeProgram, Index initialPosition) {
        this.intcodeProgram = intcodeProgram;
        initializeBoxMap(initialPosition);
        this.position = initialPosition;
    }

    private void initializeBoxMap(Index initialPosition) {
        this.boxMap = new BoxMap(initialPosition);
    }

    int getStepsToOxygen() {
        int depth = 0;
        while(!boxMap.containsOxygenSystem()) {
            depth++;
            handleUnknowns(depth);
        }
        return depth;
    }

    int getStepsToOxygenEverywhere() {
        Index oxygenIndex = boxMap.getOxygenIndex();
        moveTo(oxygenIndex);
        initializeBoxMap(oxygenIndex);
        int depth = 0;
        while(!boxMap.getNextUnknowns(depth+1).isEmpty()) {
            depth++;
            handleUnknowns(depth);
        }
        return depth-1;
    }

    private void handleUnknowns(int depth) {
        List<Unknown> nextUnknowns = boxMap.getNextUnknowns(depth);
        for (Unknown unknown : nextUnknowns) {
            moveTo(unknown.getLast());
            long execute = intcodeProgram.execute(unknown.getLast().getDirection(unknown.getIndex()).getCode());
            Type type = Type.forCode(execute);
            boxMap.put(unknown.getIndex(), new Box(type, depth, unknown.getLast()));
            if (type != Type.WALL) {
                position = unknown.getIndex();
            }
        }
    }

    private void moveTo(Index index) {
        List<Index> pathBetween = boxMap.getPathBetween(position, index);
        for(int i=0; i<pathBetween.size()-1; i++) {
            intcodeProgram.execute(pathBetween.get(i).getDirection(pathBetween.get(i + 1)).getCode());
            position = pathBetween.get(i+1);
        }
    }
}
