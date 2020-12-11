package vincent.adventofcode.aoc2019.day10;

import lombok.AllArgsConstructor;

import java.util.Comparator;

@AllArgsConstructor
public class AngleComparator implements Comparator<Float> {

    private float minAngle;

    @Override
    public int compare(Float angle1, Float angle2) {
        return Float.compare(relativeAngle(angle1), relativeAngle(angle2));
    }

    private Float relativeAngle(Float f) {
        return f <= minAngle ? f + 360 : f;
    }
}
