package lab6.store;

import lab6.model.IWeight;

public class ProductStore extends AbstractStore{

    public void add(IWeight newProduct){
        super.add(newProduct);
    }

    @Override
    public String toString() {
        return "Вміст сховища продуктів:\n" + super.toString();
    }
}
