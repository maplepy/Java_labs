package lab2.store;

import java.lang.Object;

import java.util.Arrays;

public class AbstractStore {
    protected Object[] arr = new Object[1];
    protected int count = 0;

    protected void add(Object newObject){
        if(count == arr.length){
            arr = Arrays.copyOf(arr, count + count/2+1);
        }
        arr[count++] = newObject;
    }
    public Object[] getArr(){
        return Arrays.copyOf(arr, count);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++){
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}
