package vincent.adventofcode.aoc2019.day11;

import lombok.AllArgsConstructor;

class Output {

    Color getColor() {
        return null;
    }

    ClockDirection getClockDirection() {
        return null;
    }

    boolean isFinished() {
        return false;
    }

    static Output empty() {
        return new Empty();
    }

    static Output with(long first, long second) {
        return new Present(first, second);
    }

    @AllArgsConstructor
    static class Present extends Output {

        private Long first;
        private Long second;

        @Override
        public Color getColor() {
            return Color.forNumber(first);
        }

        @Override
        public ClockDirection getClockDirection() {
            return ClockDirection.forCode(second);
        }
    }

    static class Empty extends Output {
        @Override
        public boolean isFinished() {
            return true;
        }
    }
}
