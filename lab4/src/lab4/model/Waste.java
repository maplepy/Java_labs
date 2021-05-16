package lab4.model;

public class Waste implements IWeight {
    private float weight;

    public Waste(float weight) throws Exception {
        if(weight < 0.02 || weight > 1)
            throw new Exception(weight + " is not correct!\nMust be from 0.02 to 1");
        this.weight = weight;
    }

    @Override
    public float weight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Waste: вага: " + weight;
    }
}
