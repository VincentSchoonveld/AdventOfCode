package vincent.adventofcode.aoc2019.day10;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
class Asteroids {
    private Set<Asteroid> asteroidSet;

    static Asteroids forInput(List<String> input) {
        Set<Asteroid> asteroidList = new HashSet<>();
        for(int y=0; y<input.size(); y++) {
            String s = input.get(y);
            for(int x=0; x<s.length(); x++) {
                if(s.charAt(x) == '#') {
                    asteroidList.add(new Asteroid(x,y));
                }
            }
        }
        return new Asteroids(asteroidList);
    }

    long findNumberOfMostAsteroidsToBeSeen() {
        Asteroid asteroidWithMostToBeSeen = findAsteroidWithMostToBeSeen();
        return findNumberOfAsteroidsToBeSeenBy(asteroidWithMostToBeSeen);
    }

    private Asteroid findAsteroidWithMostToBeSeen() {
        return asteroidSet.stream()
                .max(Comparator.comparing(this::findNumberOfAsteroidsToBeSeenBy))
                .orElseThrow(() -> new IllegalStateException("No asteroids available."));
    }

    private long findNumberOfAsteroidsToBeSeenBy(Asteroid asteroid) {
        return asteroidSet.stream()
                .filter(other -> !other.equals(asteroid))
                .map(other -> other.angleWith(asteroid))
                .distinct()
                .count();
    }

    Asteroid vaporize(int number) {
        Asteroid asteroid = findAsteroidWithMostToBeSeen();
        float angle = -1f;
        Asteroid last = null;
        for(int i=0; i<number; i++) {
            angle = nextAngle(asteroid, angle);
            last = asteroidToBeSeenBy(asteroid, angle);
            asteroidSet.remove(last);
        }
        return last;
    }

    private float nextAngle(Asteroid asteroid, float lastAngle) {
        return asteroidSet.stream()
                .map(other -> other.angleWith(asteroid))
                .min(new AngleComparator(lastAngle))
                .orElseThrow(() -> new IllegalStateException("There are no asteroids available."));
    }

    private Asteroid asteroidToBeSeenBy(Asteroid asteroid, double angle) {
        return asteroidSet.stream()
                .filter(other -> other.angleWith(asteroid) == angle)
                .min(Comparator.comparing(a -> a.distanceTo(asteroid)))
                .orElseThrow(() -> new IllegalStateException(String.format("There are no asteroids available at angle %s for asteroid: %s", angle, asteroid)));
    }
}
