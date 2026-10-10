public class OrbitalMechanics {

    public static final double G = 6.67430e-11;

    public static final double AU = 1.495978707e11;

    private OrbitalMechanics() {
    }

    public static double calculateOrbitalVelocity(
            Star star,
            Planet planet
    ) {

        double r = planet.getOrbitalRadius();

        return Math.sqrt(
                G * star.getMass() / r
        );
    }

    public static double calculateOrbitalPeriod(
            Star star,
            Planet planet
    ) {

        double r = planet.getOrbitalRadius();

        return 2 * Math.PI *
                Math.sqrt(
                        Math.pow(r, 3)
                                / (G * star.getMass())
                );
    }

    public static double calculateGravitationalForce(
            Star star,
            Planet planet
    ) {

        double r = planet.getOrbitalRadius();

        return G
                * star.getMass()
                * planet.getMass()
                / Math.pow(r, 2);
    }

    public static double calculateAngularVelocity(
            Star star,
            Planet planet
    ) {

        double period =
                calculateOrbitalPeriod(star, planet);

        return 2 * Math.PI / period;
    }

    public static double calculateAngle(
            Star star,
            Planet planet,
            double time
    ) {

        double angularVelocity =
                calculateAngularVelocity(
                        star,
                        planet
                );

        return planet.getInitialAngle()
                + angularVelocity * time;
    }

    public static double calculateX(
            Star star,
            Planet planet,
            double time
    ) {

        double angle =
                calculateAngle(
                        star,
                        planet,
                        time
                );

        return planet.getOrbitalRadius()
                * Math.cos(angle);
    }

    public static double calculateY(
            Star star,
            Planet planet,
            double time
    ) {

        double angle =
                calculateAngle(
                        star,
                        planet,
                        time
                );

        return planet.getOrbitalRadius()
                * Math.sin(angle);
    }

    public static double metersToAU(double meters) {

        return meters / AU;
    }
}