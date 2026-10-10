import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationPanel extends JPanel {

    private final Star sun;
    private final ArrayList<Planet> planets;

    private double simulationTime = 0;
    private double speedMultiplier = 1;
    private boolean running = false;

    private long previousTime;

    private Planet selectedPlanet;

    private final Timer timer;

    public SimulationPanel(Star sun, ArrayList<Planet> planets) {

        this.sun = sun;
        this.planets = planets;

        setBackground(Color.BLACK);

        if (!planets.isEmpty()) {
            selectedPlanet = planets.get(0);
        }

        timer = new Timer(16, e -> updateSimulation());

        timer.start();
    }

    private void updateSimulation() {

        if (!running) {
            return;
        }

        long currentTime = System.nanoTime();

        double realSeconds =
                (currentTime - previousTime)
                        / 1_000_000_000.0;

        previousTime = currentTime;

        simulationTime +=
                realSeconds
                        * 86400
                        * speedMultiplier;

        repaint();
    }

    public void startSimulation() {

        if (!running) {

            running = true;

            previousTime =
                    System.nanoTime();
        }
    }

    public void pauseSimulation() {

        running = false;
    }

    public void resetSimulation() {

        running = false;
        simulationTime = 0;

        repaint();
    }

    public void setSpeedMultiplier(double speedMultiplier) {

        this.speedMultiplier =
                speedMultiplier;
    }

    public void setSelectedPlanet(Planet selectedPlanet) {

        this.selectedPlanet =
                selectedPlanet;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 =
                (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int width = getWidth();
        int height = getHeight();

        int centerX = width / 2;
        int centerY = height / 2;

        int maximumOrbit =
                Math.min(width, height) / 2 - 40;

        drawTitle(g2);

        drawSun(
                g2,
                centerX,
                centerY
        );

        for (Planet planet : planets) {

            drawOrbit(
                    g2,
                    planet,
                    centerX,
                    centerY,
                    maximumOrbit
            );

            drawPlanet(
                    g2,
                    planet,
                    centerX,
                    centerY,
                    maximumOrbit
            );
        }

        drawSimulationTime(g2);
    }

    private void drawTitle(Graphics2D g2) {

        g2.setColor(Color.WHITE);

        g2.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        g2.drawString(
                "ORBITAL MECHANICS SIMULATOR",
                20,
                30
        );
    }

    private void drawSun(
            Graphics2D g2,
            int centerX,
            int centerY
    ) {

        int sunSize = 28;

        g2.setColor(
                new Color(
                        255,
                        190,
                        40
                )
        );

        g2.fillOval(
                centerX - sunSize / 2,
                centerY - sunSize / 2,
                sunSize,
                sunSize
        );

        g2.setColor(Color.WHITE);

        g2.drawString(
                "Sun",
                centerX - 12,
                centerY + 35
        );
    }

    private void drawOrbit(
            Graphics2D g2,
            Planet planet,
            int centerX,
            int centerY,
            int maximumOrbit
    ) {

        int orbitRadius =
                convertDistanceToScreen(
                        planet.getOrbitalRadius(),
                        maximumOrbit
                );

        g2.setColor(
                new Color(
                        80,
                        80,
                        80
                )
        );

        g2.drawOval(
                centerX - orbitRadius,
                centerY - orbitRadius,
                orbitRadius * 2,
                orbitRadius * 2
        );
    }

    private void drawPlanet(
            Graphics2D g2,
            Planet planet,
            int centerX,
            int centerY,
            int maximumOrbit
    ) {

        int orbitRadius =
                convertDistanceToScreen(
                        planet.getOrbitalRadius(),
                        maximumOrbit
                );

        double angle =
                OrbitalMechanics
                        .calculateAngle(
                                sun,
                                planet,
                                simulationTime
                        );

        int x =
                centerX
                        + (int) (
                        orbitRadius
                                * Math.cos(angle)
                );

        int y =
                centerY
                        + (int) (
                        orbitRadius
                                * Math.sin(angle)
                );

        int size =
                getPlanetDisplaySize(planet);

        g2.setColor(
                getPlanetColor(planet)
        );

        g2.fillOval(
                x - size / 2,
                y - size / 2,
                size,
                size
        );

        if (planet == selectedPlanet) {

            g2.setColor(Color.WHITE);

            g2.drawOval(
                    x - size / 2 - 4,
                    y - size / 2 - 4,
                    size + 8,
                    size + 8
            );
        }

        g2.setColor(Color.LIGHT_GRAY);

        g2.drawString(
                planet.getName(),
                x + 8,
                y - 8
        );
    }

    private int convertDistanceToScreen(
            double physicalDistance,
            int maximumOrbit
    ) {

        double au =
                OrbitalMechanics
                        .metersToAU(
                                physicalDistance
                        );

        double maxAU = 30.1;

        double normalized =
                Math.log(1 + au)
                        /
                        Math.log(1 + maxAU);

        int minimumOrbit = 45;

        return minimumOrbit
                + (int) (
                normalized
                        * (maximumOrbit
                        - minimumOrbit)
        );
    }

    private int getPlanetDisplaySize(Planet planet) {

        return switch (planet.getName()) {

            case "Mercury" -> 7;
            case "Venus" -> 10;
            case "Earth" -> 11;
            case "Mars" -> 8;
            case "Jupiter" -> 18;
            case "Saturn" -> 16;
            case "Uranus" -> 13;
            case "Neptune" -> 13;

            default -> 10;
        };
    }

    private Color getPlanetColor(Planet planet) {

        return switch (planet.getName()) {

            case "Mercury" -> Color.GRAY;

            case "Venus" ->
                    new Color(
                            220,
                            180,
                            100
                    );

            case "Earth" ->
                    new Color(
                            80,
                            140,
                            255
                    );

            case "Mars" ->
                    new Color(
                            210,
                            80,
                            50
                    );

            case "Jupiter" ->
                    new Color(
                            210,
                            160,
                            110
                    );

            case "Saturn" ->
                    new Color(
                            220,
                            190,
                            120
                    );

            case "Uranus" ->
                    new Color(
                            120,
                            220,
                            220
                    );

            case "Neptune" ->
                    new Color(
                            70,
                            100,
                            230
                    );

            default -> Color.WHITE;
        };
    }

    private void drawSimulationTime(Graphics2D g2) {

        double days =
                simulationTime / 86400;

        g2.setColor(Color.WHITE);

        g2.drawString(
                String.format(
                        "Simulation Time: %.1f days",
                        days
                ),
                20,
                getHeight() - 20
        );
    }
}