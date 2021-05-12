package lab2.model;

public abstract class AbstractForm implements IWeight {
    protected Wood wood;

    public AbstractForm(Wood wood) {
        this.wood = wood;
    }

    public abstract float volume();

    @Override
    public float weight() {
        return wood.getDensity() * volume();
    }
}
