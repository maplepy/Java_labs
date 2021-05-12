package lab3.model;

import java.io.Serializable;

public abstract class AbstractForm implements IWeight, Serializable {
    protected Wood wood;

    public AbstractForm(Wood wood) {
        this.wood = wood;
    }

    public abstract float volume();

    @Override
    public float weight() {
        return wood.getDensity() * volume();
    }

    public String toString() {
        return String.format("%s вага: %1.3f", wood.getName(), weight());
    }
}
