package lab1.model;

public class Wood {
    private int id;
    private String name;
    private float density;

    public Wood(int id, String name, float density)
    {
        this.id = id;
        this.name = name;
        this.density = density;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public float getDensity()
    {
        return density;
    }

    @Override
    public String toString() {
        return String.format("Wood [id=%d, name=%s, density=%1.3f]", id, name, density);
    }
}
