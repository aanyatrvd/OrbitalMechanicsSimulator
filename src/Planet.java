public class Planet extends CelestialBody {

    private double orbitalRadius;
    private double initialAngle;

    public Planet(
            String name,
            double mass,
            double radius,
            double orbitalRadius,
            double initialAngle
    ) {
        super(name, mass, radius);

        this.orbitalRadius = orbitalRadius;
        this.initialAngle = initialAngle;
    }

    public double getOrbitalRadius() {
        return orbitalRadius;
    }

    public double getInitialAngle() {
        return initialAngle;
    }

    @Override
    public String getInformation() {

        return "Planet: " + name +
                "\nMass: " + mass + " kg" +
                "\nRadius: " + radius + " m" +
                "\nOrbital Radius: " + orbitalRadius + " m";
    }
}