package vincent.adventofcode.aoc2019.day12;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static java.lang.Math.abs;

@ToString
@EqualsAndHashCode
@Getter
class Moon {
    private int x;
    private int y;
    private int z;

    private int vx;
    private int vy;
    private int vz;

    @EqualsAndHashCode.Exclude private int vxDelta;
    @EqualsAndHashCode.Exclude private int vyDelta;
    @EqualsAndHashCode.Exclude private int vzDelta;

    Moon(int x, int y, int z) {
        this.x=x;
        this.y=y;
        this.z=z;

        this.vx = 0;
        this.vy = 0;
        this.vz = 0;

        this.vxDelta=0;
        this.vyDelta=0;
        this.vzDelta=0;
    }

    void adjustDeltaWithRespectTo(Moon moon) {
        this.vxDelta += rel(moon.x, x);
        this.vyDelta += rel(moon.y, y);
        this.vzDelta += rel(moon.z, z);
    }

    private static int rel(int one, int two) {
        return Integer.compare(one, two);
    }

    void handle() {
        vx += vxDelta;
        vy += vyDelta;
        vz += vzDelta;

        x += vx;
        y += vy;
        z += vz;

        this.vxDelta=0;
        this.vyDelta=0;
        this.vzDelta=0;
    }

    int totalEnergy() {
        return (abs(x) + abs(y) + abs(z))* (abs(vx) + abs(vy) + abs(vz));
    }
}
