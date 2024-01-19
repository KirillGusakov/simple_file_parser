import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FileParser {

    public static List<City> readFile(Path path) throws IOException {
        return Files.lines(path)
                .map(line -> {
                    String[] parts = line.split(" ");
                    return new City(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            Double.parseDouble(parts[2]),
                            parts[3]
                    );
                })
                .collect(Collectors.toList());
    }

    public static void writeToFile(Path path, Map<String, Double> averageTemperatures,
                                   Map<String, Integer> cityCount) throws IOException {

        for (String cityName : averageTemperatures.keySet()) {
            double averageTemperature = averageTemperatures.get(cityName) / cityCount.get(cityName);
            System.out.println(cityName + ": " + averageTemperature);
            String result = cityName + ": " + averageTemperature + "\n";
            Files.writeString(path, result, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        }
    }
}
