import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PlanetDataLoader {

    public static ArrayList<Planet> loadPlanets(
            String filePath
    ) throws IOException {

        ArrayList<Planet> planets =
                new ArrayList<>();

        BufferedReader reader =
                new BufferedReader(
                        new FileReader(filePath)
                );

        String line;

        // Skip CSV header
        reader.readLine();

        while ((line = reader.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] data = line.split(",");

            String name = data[0];

            double mass =
                    Double.parseDouble(data[1]);

            double radius =
                    Double.parseDouble(data[2]);

            double orbitalRadius =
                    Double.parseDouble(data[3]);

            double angleDegrees =
                    Double.parseDouble(data[4]);

            double initialAngle =
                    Math.toRadians(angleDegrees);

            Planet planet =
                    new Planet(
                            name,
                            mass,
                            radius,
                            orbitalRadius,
                            initialAngle
                    );

            planets.add(planet);
        }

        reader.close();

        return planets;
    }
}