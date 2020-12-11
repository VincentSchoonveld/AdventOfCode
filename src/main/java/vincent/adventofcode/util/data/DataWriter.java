package vincent.adventofcode.util.data;

import lombok.AllArgsConstructor;
import vincent.adventofcode.util.Day;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@AllArgsConstructor
public class DataWriter {

    public static <T extends Serializable> void writeOnce(Day day, T t, String fileName) {
        String input = "src/main/resources/" + day.getPackageName() + "/" + fileName;
        try(FileOutputStream fileOutputStream = new FileOutputStream(new File(input));
            final ObjectOutputStream oi = new ObjectOutputStream(fileOutputStream)) {
            oi.writeObject(t);
        } catch (IOException e) {
            throw new RuntimeDataException(e);
        }
    }
}
