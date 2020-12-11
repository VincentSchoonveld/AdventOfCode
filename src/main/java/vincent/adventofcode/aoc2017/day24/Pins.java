package vincent.adventofcode.aoc2017.day24;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
class Pins {
    private List<Pin> pinList = new ArrayList<>();

    Pins remove(Pin pin) {
        List<Pin> newPinList = new ArrayList<>(pinList);
        newPinList.remove(pin);
        return new Pins(newPinList);
    }

    Pins add(Pin pin) {
        List<Pin> newPinList = new ArrayList<>(pinList);
        newPinList.add(pin);
        return new Pins(newPinList);
    }

    List<Pin> getPinsForNumber(int number) {
        return pinList.stream()
                .filter(pin -> pin.contains(number))
                .collect(Collectors.toList());
    }

    int getLastNumber() {
        if(pinList.size() == 1) {
            return pinList.get(pinList.size()-1).getLastIfFirst();
        }
        return pinList.get(pinList.size()-1).getLast(pinList.get(pinList.size()-2));
    }

    int sum() {
        return pinList.stream()
                .mapToInt(Pin::strength)
                .sum();
    }

    Integer size() {
        return pinList.size();
    }
}
