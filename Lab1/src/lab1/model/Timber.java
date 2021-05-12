package lab1.model;

public class Timber {
    private Wood wood;
    private float length;
    private float height;
    private float width;

    public Timber(Wood wood, float length, float height, float width) {
        this.wood = wood;
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public float volume() {
        return length * height * width;
    }

    public float weight() {
        return wood.getDensity() * volume();
    }

    @Override
    public String toString() {
        return String.format("%s вага: %1.3f", wood.getName(), weight());
    }
}
