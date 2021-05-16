package lab5.test;

import lab5.model.IWeight;
import lab5.model.Timber;
import lab5.model.Waste;
import lab5.store.ProductStore;
import lab5.store.WoodDirectory;

import javax.swing.*;
import java.util.ListIterator;
import java.util.function.Predicate;

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
            ps.add(new Timber(wd.get(1), 5f, 10f, 2f));
            ps.add(new Timber(wd.get(2), 11f, 7f, 1f));
            ps.add(new Timber(wd.get(3), 3f, 2f, 3f));
            ps.add(new Timber(wd.get(1), 15f, 4f, 2f));
            ps.add(new Timber(wd.get(3), 10f, 1f, 1f));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Введення продуктів",
                    JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(wd);
        System.out.println(ps);
        System.out.printf("Загальна вага: %1.3f%n", calcWeight());

        System.out.println("Перелік виробів до вилучення продуктів вагою понад 100 одиниць");
        ps.doForAll(System.out::println);

        Predicate<Object> prd = o -> o instanceof Timber && ((IWeight) o).weight() > 100f;
        ps.remove(prd);
        System.out.println("\nПерелік виробів після вилучення продуктів вагою понад 100 одиниць");
        ps.doForAll(System.out::println);
        System.out.println("\nСписок виробів вагою до 50 одиниць");

        Predicate<Object> prd1 = o -> o instanceof Timber && ((IWeight) o).weight() < 50f;
        ps.doOnlyFor(prd1, System.out::println);

    }
    private float calcWeight(){
        float fullWeight = 0;
        for (Object timber: ps.getArr()){
            fullWeight += ((IWeight)timber).weight();
        }
        return fullWeight;
    }
}
