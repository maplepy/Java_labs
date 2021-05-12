package lab3.store;

import lab3.model.Wood;

import java.io.*;
import java.util.Arrays;

public class AbstractStore implements Serializable {
    protected Object[] arr = new Object[1];
    protected int count = 0;
    protected String className = this.getClass().getSimpleName();

    protected void add(Object newObject){
        if(count == arr.length){
            arr = Arrays.copyOf(arr, count + count/2+1);
        }
        arr[count++] = newObject;
    }
    public void clear()
    {
        arr = new Object[1];
    }
    public Object[] getArr(){
        return Arrays.copyOf(arr, count);
    }
    public void setArr(Object[] objs){arr = objs;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++){
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}
