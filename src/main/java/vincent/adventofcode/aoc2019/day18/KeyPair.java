package vincent.adventofcode.aoc2019.day18;

import lombok.Value;

import java.io.Serializable;
import java.util.Objects;

@Value
class KeyPair implements Serializable {
    private char key1;
    private char key2;

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof KeyPair)) {
            return false;
        }
        KeyPair keyPair = (KeyPair) o;
        return (keyPair.key1 == key1 && keyPair.key2 == key2)
                || (keyPair.key2 == key1 && keyPair.key1 == key2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key1, key2) + Objects.hash(key2,key1);
    }
}
