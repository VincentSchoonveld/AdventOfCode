package vincent.adventofcode.aoc2019.day1;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class Modules {
    private List<Module> moduleList;

    static Modules forInput(List<String> input) {
        return new Modules(input.stream()
                .map(Long::valueOf)
                .map(Module::new)
                .collect(Collectors.toList()));
    }

    long getTotalFuel() {
        return moduleList.stream()
                .mapToLong(Module::getTotalFuel).sum();
    }

    long getFuelNaively() {
        return moduleList.stream()
                .mapToLong(Module::getFuel).sum();
    }

    private static long getFuelForMass(long mass) {
        return mass/3-2;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Module {
        private long mass;

        long getFuel() {
            return getFuelForMass(mass);
        }

        long getTotalFuel() {
            long totalFuel = getFuel();
            long remainingMass = getFuelForMass(totalFuel);
            while(remainingMass > 0) {
                System.out.println(remainingMass);
                totalFuel = totalFuel+remainingMass;
                remainingMass = getFuelForMass(remainingMass);
            }
            return totalFuel;
        }
    }
}

//4875349-4878269
