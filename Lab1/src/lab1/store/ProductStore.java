package lab1.store;

import lab1.model.Timber;

import java.util.Arrays;

public class ProductStore {
    private Timber[] arr = new Timber[1];
    private int count = 0;

    public void add(Timber tm){
        if(count == arr.length){
            arr = Arrays.copyOf(arr, count + count/2+1);
        }
        arr[count++] = tm;
    }
    public Timber[] getArr(){
        return Arrays.copyOf(arr, count);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Бруси:\n");
        for(int i = 0; i < count; i++){
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}
