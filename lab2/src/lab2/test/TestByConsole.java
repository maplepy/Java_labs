package lab2.test;

import lab2.model.*;
import lab2.store.ProductStore;
import lab2.store.WoodDirectory;

import java.util.Scanner;


public class TestByConsole {
    private static WoodDirectory wd = new WoodDirectory();
    private static ProductStore ps = new ProductStore();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Додати деревину\n2. Додати брус\n3. Додати циліндричний брус\n4. Додати мішок з відходами.\n5. Підрахувати загальну вагу\n6. Завершити роботу");
        while (true){
            System.out.print("Виберіть номер дії: ");
            int num = in.nextInt();
            switch(num){
                case 1:
                    addWood(in);
                    break;
                case 2:
                    addTimber(in);
                    break;
                case 3:
                    addCylinder(in);
                    break;
                case 4:
                    addWaste(in);
                    break;
                case 5:
                    calcWeight();
                    break;
                case 6:
                    return;
            }
        }
    }

    private static void addWaste(Scanner in){
        System.out.println("Введіть вагу: ");
        float w = in.nextFloat();
        in.nextLine();

        ps.add(new Waste(w));
        System.out.println("Мішок успішно додано.");
    }

    private static void addCylinder(Scanner in){
        System.out.println(wd);
        System.out.println("Введіть числовий індетифікатор деревини. ");
        int id3 = in.nextInt();
        in.nextLine();

        System.out.println("Введіть довжину: ");
        float len = in.nextFloat();
        in.nextLine();

        System.out.println("Введіть діаметер: ");
        float d = in.nextFloat();

        ps.add(new Cylinder(wd.get(id3), len, d));
        System.out.println("Брус успішно додано.");
    }

    private static void calcWeight() {
        float res = 0;
        for(int i = 0; i < ps.getArr().length; i++)
        {
            res += ((IWeight)ps.getArr()[i]).weight();
        }
        System.out.println("Загальна масса: "+ res);
    }

    private static void addTimber(Scanner in) {
        System.out.println(wd);
        System.out.println("Введіть числовий індетифікатор деревини. ");
        int id2 = in.nextInt();
        in.nextLine();

        System.out.println("Введіть довжину: ");
        float len = in.nextFloat();
        in.nextLine();

        System.out.println("Введіть висоту: ");
        float h = in.nextFloat();
        in.nextLine();

        System.out.println("Введіть ширину: ");
        float w = in.nextFloat();

        ps.add(new Timber(wd.get(id2), len, h, w));
        System.out.println("Брус успішно додано.");
    }

    private static void addWood(Scanner in) {
        System.out.print("Введіть числовий індетифікатор деревини: ");
        int id1 = in.nextInt();
        in.nextLine();

        System.out.print("Введіть назву деревини: ");
        String name = in.nextLine();

        System.out.print("Введіть щільність деревини: ");
        float density = in.nextFloat();

        if(wd.add(new Wood(id1, name, density)))
            System.out.println("Деревину успішно додано.\n");
        else System.out.println("Помилка. Деревина з таким індетифікатором уже існує.");
    }
}
