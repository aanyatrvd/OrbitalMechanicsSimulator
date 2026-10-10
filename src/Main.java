import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    try {

                        Star sun =
                                new Star(
                                        "Sun",
                                        1.98847e30,
                                        6.9634e8,
                                        3.828e26
                                );

                        ArrayList<Planet> planets =
                                PlanetDataLoader
                                        .loadPlanets(
                                                "data/planets.csv"
                                        );

                        new MainFrame(
                                sun,
                                planets
                        );

                    } catch (IOException e) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Could not load planet data.\n"
                                        + e.getMessage(),
                                "File Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                        e.printStackTrace();
                    }
                }
        );
    }
}