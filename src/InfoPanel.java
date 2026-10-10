import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoPanel extends JPanel {

    private final JLabel nameLabel;
    private final JLabel massLabel;
    private final JLabel radiusLabel;
    private final JLabel distanceLabel;
    private final JLabel velocityLabel;
    private final JLabel periodLabel;
    private final JLabel forceLabel;

    private final Star sun;

    public InfoPanel(Star sun) {

        this.sun = sun;

        setLayout(
                new BoxLayout(
                        this,
                        BoxLayout.Y_AXIS
                )
        );

        setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        setPreferredSize(
                new Dimension(
                        300,
                        600
                )
        );

        JLabel title =
                new JLabel(
                        "Planet Information"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        add(title);

        add(Box.createVerticalStrut(25));

        nameLabel = new JLabel();
        massLabel = new JLabel();
        radiusLabel = new JLabel();
        distanceLabel = new JLabel();
        velocityLabel = new JLabel();
        periodLabel = new JLabel();
        forceLabel = new JLabel();

        add(nameLabel);
        add(Box.createVerticalStrut(12));

        add(massLabel);
        add(Box.createVerticalStrut(12));

        add(radiusLabel);
        add(Box.createVerticalStrut(12));

        add(distanceLabel);
        add(Box.createVerticalStrut(12));

        add(velocityLabel);
        add(Box.createVerticalStrut(12));

        add(periodLabel);
        add(Box.createVerticalStrut(12));

        add(forceLabel);
    }

    public void updatePlanet(Planet planet) {

        double velocity =
                OrbitalMechanics
                        .calculateOrbitalVelocity(
                                sun,
                                planet
                        );

        double period =
                OrbitalMechanics
                        .calculateOrbitalPeriod(
                                sun,
                                planet
                        );

        double force =
                OrbitalMechanics
                        .calculateGravitationalForce(
                                sun,
                                planet
                        );

        double au =
                OrbitalMechanics
                        .metersToAU(
                                planet.getOrbitalRadius()
                        );

        nameLabel.setText(
                "Planet: "
                        + planet.getName()
        );

        massLabel.setText(
                String.format(
                        "Mass: %.3e kg",
                        planet.getMass()
                )
        );

        radiusLabel.setText(
                String.format(
                        "Radius: %.0f km",
                        planet.getRadius() / 1000
                )
        );

        distanceLabel.setText(
                String.format(
                        "Orbital Radius: %.3f AU",
                        au
                )
        );

        velocityLabel.setText(
                String.format(
                        "Orbital Velocity: %.2f km/s",
                        velocity / 1000
                )
        );

        periodLabel.setText(
                String.format(
                        "Orbital Period: %.2f days",
                        period / 86400
                )
        );

        forceLabel.setText(
                String.format(
                        "Solar Force: %.3e N",
                        force
                )
        );
    }
}