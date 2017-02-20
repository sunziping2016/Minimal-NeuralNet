package DataChooser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sun on 4/7/16.
 *
 * DataChooser class.
 */
public class DataChooser {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.lines(Paths.get("data.txt")).sorted(Comparator.comparing(p -> Integer.parseInt(p.split("\\s+")[4]))).collect(Collectors.toList());
            System.out.println(lines.get(0).split("\\s+")[4]);
            Files.write(Paths.get("newdata.txt"), lines);
        }
        catch (Exception error) {
            System.out.println(error);
        }
    }
}
