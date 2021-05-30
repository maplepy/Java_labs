package lab7.model;

public class Cylinder extends AbstractForm{
    private float length;
    private float diameter;

    public Cylinder(Wood wood, float length, float diameter) throws Exception {
        super(wood);
        if(length < 1 || length > 50)
            throw new Exception(length + " is not correct!\nMust be from 1 to 50");
        if(diameter < 0.1f || diameter > 10)
            throw new Exception(length + " is not correct!\nMust be from 0.1 to 10");
        this.length = length;
        this.diameter = diameter;
    }

    public float volume(){
        return (float) (Math.PI/4*(diameter*diameter)*length);
    }
}
