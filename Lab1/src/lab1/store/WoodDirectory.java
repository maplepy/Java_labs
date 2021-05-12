package lab1.store;

import lab1.model.Wood;

import java.util.Arrays;

public class WoodDirectory {
    private Wood[] arr = new Wood[3];
    private int count = 0;

    public WoodDirectory() {
        arr[0] = new Wood(1, "Модрина", 1.1f);
        arr[1] = new Wood(2, "Ялина", 0.9f);
        arr[2] = new Wood(3, "Сосна", 0.7f);
        count = 3;
    }

    public Wood[] getArr(){
        return Arrays.copyOf(arr, count);
    }

    public Wood get(int id){
        for (int i = 0; i < count; i++){
            if(arr[i].getId() == id){
                return arr[i];
            }
        }
        return null;
    }
    public boolean add(Wood newWood){
        if(get(newWood.getId()) != null){
            return false;
        }
        if(arr.length == count){
            arr = Arrays.copyOf(arr, count + count/2+1);
        }
        arr[count++] = newWood;
        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("Каталог деревини:\n");
        for(int i = 0; i < count; i++){
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}
