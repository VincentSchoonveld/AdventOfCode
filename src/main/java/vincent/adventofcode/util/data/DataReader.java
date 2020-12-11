package vincent.adventofcode.util.data;

import vincent.adventofcode.util.Day;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataReader {

    public static Object readOnce(Day day, String fileName) {
        final String input = "src/main/resources/" + day.getPackageName() + "/" + fileName;
        final File file = new File(input);
        try(FileInputStream fileInputStream = new FileInputStream(file);
            final ObjectInputStream oi = new ObjectInputStream(fileInputStream)) {
            return oi.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeDataException(e);
        }
    }
}
