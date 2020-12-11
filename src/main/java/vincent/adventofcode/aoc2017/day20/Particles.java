package vincent.adventofcode.aoc2017.day20;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Particles {
    private List<Particle> particleList;

    static Particles forInput(List<String> input) {
        return new Particles(
                input.stream()
                        .map(Particle::forInput)
                        //.filter(a -> a.sumAcceleration()<= 2)
                        .collect(Collectors.toList()));
    }

    boolean isFinal() {
        return particleList.stream()
                .allMatch(Particle::isInFinalPosition);
    }

    int getMinAcceleration() {
        Particle particle = particleList.stream()
                .min(Comparator.comparing(Particle::sumAcceleration))
                .get();
        int asInt = particleList.stream().mapToInt(Particle::sumAcceleration).min().getAsInt();
        List<Particle> collect = particleList.stream()
                .filter(a -> a.sumAcceleration() == asInt)
                .collect(Collectors.toList());
        System.out.println(collect);
        return particleList.indexOf(particle);
    }

    void next() {
        particleList.forEach(Particle::next);
    }

    int getMin() {
        Particle min = particleList.stream().min(Comparator.comparing(Particle::distance)).get();
        return particleList.indexOf(min);
    }
}
