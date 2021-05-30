package lab7.store;

import lab7.model.IWeight;

public class ProductStore extends AbstractStore{

    public void add(IWeight newProduct){
        super.add(newProduct);
    }

    @Override
    public String toString() {
        return "Вміст сховища продуктів:\n" + super.toString();
    }
}
