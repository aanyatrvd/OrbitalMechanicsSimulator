import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private final ArrayList<Planet> planets;

    private final SimulationPanel simulationPanel;

    private final InfoPanel infoPanel;

    public MainFrame(
            Star sun,
            ArrayList<Planet> planets
    ) {

        this.planets = planets;

        setTitle(
                "Orbital Mechanics Simulator"
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setSize(
                1200,
                750
        );

        setLocationRelativeTo(null);

        setLayout(
                new BorderLayout()
        );

        simulationPanel =
                new SimulationPanel(
                        sun,
                        planets
                );

        infoPanel =
                new InfoPanel(sun);

        JPanel controlPanel =
                createControlPanel();

        add(
                simulationPanel,
                BorderLayout.CENTER
        );

        add(
                infoPanel,
                BorderLayout.EAST
        );

        add(
                controlPanel,
                BorderLayout.SOUTH
        );

        Planet earth =
                findPlanet("Earth");

        if (earth != null) {

            simulationPanel
                    .setSelectedPlanet(
                            earth
                    );

            infoPanel
                    .updatePlanet(
                            earth
                    );
        }

        setVisible(true);
    }

    private JPanel createControlPanel() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        JButton startButton =
                new JButton("Start");

        JButton pauseButton =
                new JButton("Pause");

        JButton resetButton =
                new JButton("Reset");

        JLabel speedLabel =
                new JLabel("Speed:");

        String[] speedOptions = {
                "0.5x",
                "1x",
                "5x",
                "10x",
                "50x",
                "100x"
        };

        JComboBox<String> speedBox =
                new JComboBox<>(
                        speedOptions
                );

        speedBox.setSelectedItem("1x");

        JLabel planetLabel =
                new JLabel(
                        "Select Planet:"
                );

        JComboBox<Planet> planetBox =
                new JComboBox<>(
                        planets.toArray(
                                new Planet[0]
                        )
                );

        Planet earth =
                findPlanet("Earth");

        if (earth != null) {
            planetBox.setSelectedItem(
                    earth
            );
        }

        startButton.addActionListener(
                e ->
                        simulationPanel
                                .startSimulation()
        );

        pauseButton.addActionListener(
                e ->
                        simulationPanel
                                .pauseSimulation()
        );

        resetButton.addActionListener(
                e ->
                        simulationPanel
                                .resetSimulation()
        );

        speedBox.addActionListener(
                e -> {

                    String selected =
                            (String)
                                    speedBox
                                            .getSelectedItem();

                    double multiplier =
                            switch (selected) {

                                case "0.5x" -> 0.5;
                                case "5x" -> 5;
                                case "10x" -> 10;
                                case "50x" -> 50;
                                case "100x" -> 100;

                                default -> 1;
                            };

                    simulationPanel
                            .setSpeedMultiplier(
                                    multiplier
                            );
                }
        );

        planetBox.addActionListener(
                e -> {

                    Planet planet =
                            (Planet)
                                    planetBox
                                            .getSelectedItem();

                    if (planet != null) {

                        simulationPanel
                                .setSelectedPlanet(
                                        planet
                                );

                        infoPanel
                                .updatePlanet(
                                        planet
                                );
                    }
                }
        );

        panel.add(startButton);
        panel.add(pauseButton);
        panel.add(resetButton);

        panel.add(speedLabel);
        panel.add(speedBox);

        panel.add(planetLabel);
        panel.add(planetBox);

        return panel;
    }

    private Planet findPlanet(String name) {

        for (Planet planet : planets) {

            if (
                    planet
                            .getName()
                            .equalsIgnoreCase(
                                    name
                            )
            ) {

                return planet;
            }
        }

        return null;
    }
}