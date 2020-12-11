package vincent.adventofcode.aoc2019.day11;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
class Robot {
    private Direction direction;
    private IntcodeProgram intcodeProgram;
    @Getter
    private Painting painting;
    private Index currentIndex;

    Robot(List<Long> input) {
        direction = Direction.UP;
        intcodeProgram = new IntcodeProgram(input);
        painting = new Painting();
        currentIndex = new Index(0,0);
    }

    void execute(Color initialColor) {
        painting.put(currentIndex, initialColor);
        Output output = getOutput();
        while(!output.isFinished()) {
            painting.put(currentIndex, output.getColor());
            direction = direction.next(output.getClockDirection());
            currentIndex = currentIndex.next(direction);
            output = getOutput();
        }
    }

    private Output getOutput() {
        Color currentColor = painting.get(currentIndex);
        return intcodeProgram.execute(currentColor.getNumber());
    }
}

