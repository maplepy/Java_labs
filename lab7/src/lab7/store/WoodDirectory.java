package lab7.store;

import lab7.model.Wood;

public class WoodDirectory extends AbstractStore{

    public WoodDirectory() {
        super.add(new Wood(0, "Модрина", 1.1f));
        super.add(new Wood(1, "Ялина", 0.9f));
        super.add(new Wood(2, "Сосна", 0.7f));
        count = 3;
    }

    public Wood get(int id){
        for (int i = 0; i < count; i++){
            Wood wood = (Wood) arr[i];
            if(wood.getId() == id){
                return wood;
            }
        }
        return null;
    }
    public boolean add(Wood newWood){
        if(get(newWood.getId()) != null){
            return false;
        }
        super.add(newWood);
        return true;
    }

    @Override
    public String toString() {
        return "Список видів деревини:\n" + super.toString();
    }
}
