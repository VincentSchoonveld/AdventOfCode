package vincent.adventofcode.aoc2017.day20;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
class Particle {
    private Index position;
    private Index speed;
    private Index acceleration;

    boolean isInFinalPosition() {
        return isInFinalPositon(Index::getX) && isInFinalPositon(Index::getY) && isInFinalPositon(Index::getZ);
    }

    private boolean isInFinalPositon(Function<Index, Integer> function) {
        int acceleration = function.apply(this.acceleration);
        int speed = function.apply(this.speed);
        int position = function.apply(this.position);
        int sign;
        if(sign(acceleration) != 0) {
            sign = sign(acceleration);
        } else if(sign(speed) != 0) {
            sign = sign(speed);
        } else {
            sign = sign(position);
        }
        if(sign<0) {
            return acceleration<=0 && speed<=0 && position<=0;
        } else {
            return acceleration>=0 && speed>=0 && position>=0;
        }
    }

    private static int sign(int i) {
        return Integer.compare(i, 0);
    }

    int distance() {
        return position.distance();
    }

    int sumAcceleration() {
        return acceleration.distance();
    }

    void next() {
        position = position.plus(speed);
        speed = speed.plus(acceleration);
    }

    static Particle forInput(String string) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(string);

        List<Integer> collect = new ArrayList<>();
        while(m.find()) {
            collect.add(Integer.parseInt(m.group()));
        }
        Index position = new Index(collect.get(0), collect.get(1), collect.get(2));
        Index velocity = new Index(collect.get(3), collect.get(4), collect.get(5));
        Index acceleration = new Index(collect.get(6), collect.get(7), collect.get(8));
        return new Particle(position, velocity, acceleration);
    }
}
