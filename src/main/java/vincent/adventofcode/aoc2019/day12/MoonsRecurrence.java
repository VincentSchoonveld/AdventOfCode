package vincent.adventofcode.aoc2019.day12;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MoonsRecurrence {

    static int getPeriodX(Moons moons) {
        return getRecurrence(moons, MoonsRecurrence::getXState);
    }

    private static State getXState(Moons moons) {
        return new State(moons, Moon::getX, Moon::getVx);
    }

    static int getPeriodY(Moons moons) {
        return getRecurrence(moons, MoonsRecurrence::getYState);
    }

    private static State getYState(Moons moons) {
        return new State(moons, Moon::getY, Moon::getVy);
    }

    static int getPeriodZ(Moons moons) {
        return getRecurrence(moons, MoonsRecurrence::getZState);
    }

    private static State getZState(Moons moons) {
        return new State(moons, Moon::getZ, Moon::getVz);
    }

    private static int getRecurrence(Moons moons, Function<Moons, State> moonStateFunction) {
        Set<State> stateSet = new HashSet<>();
        moons.runOnce();
        while(stateSet.add(moonStateFunction.apply(moons))) {
            moons.runOnce();
        }
        return stateSet.size();
    }

    @EqualsAndHashCode
    private static class State {

        List<PositionSpeed> positionSpeeds;

        private State(Moons moons, Function<Moon, Integer> getPosition, Function<Moon, Integer> getSpeed) {
            this.positionSpeeds = moons.getMoonList()
                    .stream()
                    .map(moon -> new PositionSpeed(moon, getPosition, getSpeed))
                    .collect(Collectors.toList());
        }

        @EqualsAndHashCode
        private static class PositionSpeed {
            int s;
            int v;

            private PositionSpeed(Moon moon,
                                  Function<Moon, Integer> getPosition,
                                  Function<Moon, Integer> getSpeed) {
                this.s = getPosition.apply(moon);
                this.v = getSpeed.apply(moon);
            }
        }
    }
}
