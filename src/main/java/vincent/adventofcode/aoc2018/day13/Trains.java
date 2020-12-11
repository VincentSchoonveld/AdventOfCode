package vincent.adventofcode.aoc2018.day13;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Trains {
    private List<Train> trainList;
    private int tick;

    static Trains forInput(List<String> input) {
        List<Train> trainList = new ArrayList<>();
        for(int y=0; y<input.size(); y++) {
            String s = input.get(y);
            for(int x=0; x<s.length(); x++) {
                char c = s.charAt(x);
                if(isTrain(c)) {
                    Index index = new Index(x, y);
                    Direction direction = Direction.forChar(c);
                    trainList.add(new Train(index, direction));
                }
            }
        }
        return new Trains(trainList, 0);
    }

    private static boolean isTrain(char c) {
        return c == '<' || c == '>' || c == '^' || c == 'v';
    }

    Index next(TrackMap trackMap) {
        Index index = nextTrain().next(trackMap);
        removeBrokenTrains();
        return index;
    }

    int getNumber() {
        return trainList.size();
    }

    private Train nextTrain() {
        Optional<Train> min = getMinTrain();
        if(!min.isPresent()) {
            tick++;
            return nextTrain();
        }
        return min.get();
    }

    private Optional<Train> getMinTrain() {
        return trainList.stream()
                    .filter(a -> a.getPathsWalked() == tick)
                    .min(Comparator.comparing(Train::getIndex));
    }

    private void removeBrokenTrains() {
        duplicateIndex().ifPresent(
                index -> trainList.removeIf(train -> index.equals(train.getIndex()))
        );
    }

    private Optional<Index> duplicateIndex() {
        Set<Index> indexSet = new HashSet<>();
        for (Train train : trainList) {
            Index index = train.getIndex();
            if (!indexSet.add(index)) {
                return Optional.of(index);
            }
        }
        return Optional.empty();
    }
}
