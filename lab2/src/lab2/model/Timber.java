package lab2.model;

public class Timber extends AbstractForm {
    private float length;
    private float height;
    private float width;

    public Timber(Wood wood, float length, float height, float width) {
        super(wood);
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public float volume() {
        return length * height * width;
    }

    @Override
    public String toString() {
        return String.format("%s вага: %1.3f", wood.getName(), weight());
    }
}
