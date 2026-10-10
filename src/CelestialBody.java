public abstract class CelestialBody {

    protected String name;
    protected double mass;
    protected double radius;

    public CelestialBody(String name, double mass, double radius) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public abstract String getInformation();

    @Override
    public String toString() {
        return name;
    }
}