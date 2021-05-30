package lab4.test;

import lab4.model.IWeight;
import lab4.model.Timber;
import lab4.model.Waste;
import lab4.store.ProductStore;
import lab4.store.WoodDirectory;

import javax.swing.*;
import java.util.Iterator;
import java.util.ListIterator;

public class TestApp {
    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();

    public static void main(String[] args) throws Exception {
        TestApp app = new TestApp();
        app.startApp();
    }
    private void startApp(){
        try
        {
            ps.add(new Timber(wd.get(1), 2f, 1f, 1f));
            ps.add(new Timber(wd.get(2), 10f, 5f, 4f));
            ps.add(new Timber(wd.get(2), 1f, 1f, 2f));
            ps.add(new Timber(wd.get(2), 3f, 1f, 2f));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Введення продуктів",
                    JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(wd);
        System.out.println(ps);
        System.out.printf("Загальна вага: %1.3f%n", calcWeight());

        ListIterator <Object> litr = ps.listIterator();

        int maxWeight = 1;

        System.out.println("\nПерелік виробів до вилучення");
        System.out.println(ps.toString());
        removeIf(litr, maxWeight);

        while(litr.hasPrevious())
        {
            IWeight obj = (IWeight) litr.previous();
            if(obj.weight() > maxWeight)
                litr.remove();
        }
        System.out.println("\nПерелік виробів після вилучення\n" + ps);
    }

    private float calcWeight(){
        float fullWeight = 0;
        for (Object timber: ps.getArr()){
            fullWeight += ((IWeight)timber).weight();
        }
        return fullWeight;
    }
    private void removeIf(ListIterator<Object> litr, int maxWeight) {
        while (litr.hasNext()){
            IWeight obj = (IWeight) litr.next();
            if(obj.weight() > maxWeight)
                litr.remove();
        }
    }
}
