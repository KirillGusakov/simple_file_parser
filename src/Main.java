import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Path inputPath = Path.of("src//cities.txt");
        Path outputPath = Path.of("src//average_temperatures.txt");

        List<City> cities = FileParser.readFile(inputPath);

        cities.sort(Comparator.comparing(City::getName));

        Map<String, Double> averageTemperatures = new HashMap<>();
        Map<String, Integer> cityCount = new HashMap<>();

        for (City city : cities) {
            averageTemperatures.merge(city.getName(), city.getTemperature(), Double::sum);
            cityCount.merge(city.getName(), 1, Integer::sum);
        }

        FileParser.writeToFile(outputPath, averageTemperatures, cityCount);
    }
}
