public class Star extends CelestialBody {

    private double luminosity;

    public Star(
            String name,
            double mass,
            double radius,
            double luminosity
    ) {
        super(name, mass, radius);

        this.luminosity = luminosity;
    }

    public double getLuminosity() {
        return luminosity;
    }

    @Override
    public String getInformation() {

        return "Star: " + name +
                "\nMass: " + mass + " kg" +
                "\nRadius: " + radius + " m" +
                "\nLuminosity: " + luminosity + " W";
    }
}