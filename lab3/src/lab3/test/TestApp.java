package lab3.test;

import lab3.model.IWeight;
import lab3.model.Timber;
import lab3.model.Waste;
import lab3.store.ProductStore;
import lab3.store.WoodDirectory;

import javax.swing.*;

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
            ps.add(new Timber(wd.get(1), 5f, 0.5f, 0.4f));
            ps.add(new Timber(wd.get(2), 10f, 0.5f, 0.4f));
            ps.add(new Waste(50f));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Введення продуктів",
                    JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(wd);
        System.out.println(ps);
        System.out.printf("Загальна вага: %1.3f%n", calcWeight());
    }
    private float calcWeight(){
        float fullWeight = 0;
        for (Object timber: ps.getArr()){
            fullWeight += ((IWeight)timber).weight();
        }
        return fullWeight;
    }
}
