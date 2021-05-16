package lab4.store;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class AbstractStore implements Serializable, Iterable <Object>{
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
        for(Object obj: this){
            sb.append(obj).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new StoreIterator();
    }
    public ListIterator<Object> listIterator(){
        return new StoreListIterator();
    }

    private class StoreIterator implements Iterator <Object>{
        int current = 0;

        @Override
        public boolean hasNext() {
            return current < count;
        }

        @Override
        public Object next() {
            return arr[current++];
        }

        @Override
        public void remove() {
            System.arraycopy(arr, 0, arr, 0, current);
            System.arraycopy(arr, current+1, arr, current, count-- - current--);
        }
    }

    private class StoreListIterator extends StoreIterator implements ListIterator <Object> {

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public Object previous() {
            if(current > 0) return arr[--current];
            else throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {

            if(current+1 < arr.length) return current+1;
            return arr.length;
        }

        @Override
        public int previousIndex() {
            if(current > 0) return current-1;
            return -1;
        }

        @Override
        public void set(Object o) {
            arr[current] = o;
        }

        @Override
        public void add(Object o) {
            arr = Arrays.copyOf(arr, count + 1);
            for(int i = arr.length-1; i > current; i--){
                arr[i] = arr[i-1];
            }
            arr[current] = o;
        }
    }
}
