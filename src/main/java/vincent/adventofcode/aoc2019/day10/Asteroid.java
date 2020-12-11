package vincent.adventofcode.aoc2019.day10;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
class Asteroid {
    private int x;
    private int y;

    int distanceTo(Asteroid other) {
        return (x-other.x)*(x-other.x) + (y-other.y)*(y-other.y);
    }

    float angleWith(Asteroid other) {
        float angle = (float) Math.toDegrees(Math.atan2(other.y-y, other.x-x))-90f;
        return angle < 0 ? angle+360 : angle;
    }
}
