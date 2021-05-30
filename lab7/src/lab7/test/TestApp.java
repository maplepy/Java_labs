package lab7.test;

import lab7.model.IWeight;
import lab7.model.Timber;
import lab7.store.ProductStore;
import lab7.store.WoodDirectory;
import lab7.threads.CylinderShop;
import lab7.threads.TimberShop;
import lab7.threads.WoodShop;

import javax.swing.*;
import java.util.function.Predicate;

public class TestApp {
    private final WoodDirectory wd = new WoodDirectory();
    private final ProductStore ps = new ProductStore();

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }
    private void startApp(){
        try
        {
            /*ps.add(new Timber(wd.get(1), 2f, 1f, 1f));
            ps.add(new Timber(wd.get(2), 10f, 5f, 4f));
            ps.add(new Timber(wd.get(1), 5f, 10f, 2f));
            ps.add(new Timber(wd.get(2), 11f, 7f, 1f));
            ps.add(new Timber(wd.get(3), 3f, 2f, 3f));
            ps.add(new Timber(wd.get(1), 15f, 4f, 2f));
            ps.add(new Timber(wd.get(3), 10f, 1f, 1f));*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Введення продуктів",
                    JOptionPane.ERROR_MESSAGE);
        }

        WoodShop shop1 = new TimberShop("timberShop", wd, ps, 5);
        WoodShop shop2 = new TimberShop("timberShop", wd, ps, 5);

        Thread tshop1 = new Thread(shop1);
        Thread tshop2 = new Thread(shop2);

        tshop1.start();
        tshop2.start();

        (new Thread(() -> {
            try {
                tshop1.join();
                tshop2.join();
                System.out.println(ps.getArr().length);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }
    private float calcWeight(){
        float fullWeight = 0;
        for (Object timber: ps.getArr()){
            fullWeight += ((IWeight)timber).weight();
        }
        return fullWeight;
    }
}
