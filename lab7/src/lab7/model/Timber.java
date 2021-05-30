package lab7.model;

public class Timber extends AbstractForm {
    private float length;
    private float height;
    private float width;

    public Timber(Wood wood, float length, float height, float width) throws Exception {
        super(wood);
        if(length < 1 || length > 50)
            throw new Exception(length + " is not correct!\nMust be from 1 to 50");
        if(height < 0.1f || height > 10)
            throw new Exception(height + " is not correct!\nMust be from 0.1 to 10");
        if(width < 0.1f || width > 10)
            throw new Exception(length + " is not correct!\nMust be from 0.1 to 10");
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
