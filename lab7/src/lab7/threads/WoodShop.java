package lab7.threads;

import lab7.model.IWeight;
import lab7.store.ProductStore;
import lab7.store.WoodDirectory;

import java.util.Random;

public abstract class WoodShop implements Runnable{
    WoodDirectory wd;
    ProductStore ps;
    String name;
    Random rnd = new Random();
    int n;

    public WoodShop(String name, WoodDirectory wd, ProductStore s, int n){
        super();
        this.name = name;
        this.wd = wd;
        this.ps = s;
        this.n = n;
    }
    @Override
    public void run() {
        for(int i = 0; i < n; i++){
        try {
            IWeight timber = createProduct();
            ps.add(timber);
            System.out.println(this.getName() + " create " + timber);
        } catch (Exception e) {
            e.printStackTrace();
        }}
    }
    public String getName(){
        return name;
    }
    protected abstract IWeight createProduct() throws Exception;
}