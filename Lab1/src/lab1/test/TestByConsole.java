package lab1.test;

import lab1.model.Timber;
import lab1.model.Wood;
import lab1.store.ProductStore;
import lab1.store.WoodDirectory;

import java.util.Scanner;


public class TestByConsole {
    private static WoodDirectory wd = new WoodDirectory();
    private static ProductStore ps = new ProductStore();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Додати деревину\n2. Додати брус\n3. Підрахувати загальну вагу\n4. Завершити роботу");
        while (true){
            System.out.print("Виберіть номер дії: ");
            int num = in.nextInt();
            switch(num){
                case 1:
                    woodAdd(in);
                    break;
                case 2:
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
                    System.out.println("Деревину успішно додано.");
                    break;
                case 3:
                    float res = 0;
                    for(int i = 0; i < ps.getArr().length; i++)
                    {
                        res += ps.getArr()[i].volume();
                    }
                    System.out.println("Загальна масса: "+ res);
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void woodAdd(Scanner in) {
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
        return;
    }
}
