package lab4.test;

import lab4.model.*;
import lab4.store.ProductStore;
import lab4.store.WoodDirectory;

import java.io.*;
import java.util.Scanner;
import java.util.Date;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;


public class TestByConsole implements Serializable {
    private static WoodDirectory wd = new WoodDirectory();
    private static ProductStore ps = new ProductStore();
    private static Scanner in = new Scanner(System.in);
    private static BufferedWriter bw;

    static {
        try {
            bw = new BufferedWriter(new FileWriter("Log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TestByConsole() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        Name();
        System.out.println("1. Додати деревину\n2. Додати брус\n3. Додати циліндричний брус\n4. Додати мішок з відходами.\n5. Підрахувати загальну вагу\n6. Серіалізація\n7. Десеріалізація\n8. Експорт в файл\n9. Вийти");
        while (true) {
            System.out.print("Виберіть номер дії: ");
            int num = in.nextInt();
            switch (num) {
                case 1:
                    addObject("Wood");
                    break;
                case 2:
                    addObject("Timber");
                    break;
                case 3:
                    addObject("Cylinder");
                    break;
                case 4:
                    addObject("Waste");
                    break;
                case 5:
                    calcWeight();
                    break;
                case 6:
                    Serialization();
                    break;
                case 7:
                    Deserialization();
                    break;
                case 8:
                    ToTXTFile();
                    break;
                case 9:
                    bw.close();
                    return;
            }
        }
    }

    static void addObject(String func) throws Exception {
        while (true) {
            try {
                switch (func) {
                    case "Wood":
                        addWood();
                        return;
                    case "Timber":
                        addTimber();
                        return;
                    case "Cylinder":
                        addCylinder();
                        return;
                    case "Waste":
                        addWaste();
                        return;
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("\nБажаєте повторити введення? 1 - Так, 2 - Ні.");
                in.nextLine();
                switch (in.nextLine()) {
                    case "1":
                        break;
                    case "2":
                        System.out.println("Об'єкт не додано.");
                        return;
                    default:
                        System.out.println("Незрозуміла команда. Об'єкт не додано.");
                        return;
                }
            }
        }
    }

    static void addWaste() throws Exception {
        System.out.println("Введіть вагу: ");
        float w = in.nextFloat();
        in.nextLine();

        Waste ws = new Waste(w);
        ps.add(ws);
        System.out.println("Мішок успішно додано.");
        logging(ws.toString());
    }

    static void addCylinder() throws Exception {
        System.out.println(wd);
        System.out.println("Введіть числовий індетифікатор деревини. ");
        int id = in.nextInt();
        in.nextLine();

        if (wd.get(id) == null) {
            System.out.println("Такої деревини немає в каталозі. Брус не додано.");
            return;
        }

        System.out.println("Введіть довжину: ");
        float len = in.nextFloat();
        in.nextLine();

        System.out.println("Введіть діаметер: ");
        float d = in.nextFloat();

        Cylinder c = new Cylinder(wd.get(id), len, d);
        ps.add(c);
        System.out.println("Брус успішно додано.");
        logging(c.toString());
    }

    static void calcWeight() {
        float res = 0;
        for (int i = 0; i < ps.getArr().length; i++) {
            res += ((IWeight) ps.getArr()[i]).weight();
        }
        System.out.println("Загальна масса: " + res);
        logging("calcWeight: " + res);
    }

    static void addTimber() throws Exception {
        System.out.println(wd);
        System.out.println("Введіть числовий індетифікатор деревини. ");
        int id = in.nextInt();
        in.nextLine();

        if (wd.get(id) == null) {
            System.out.println("Такої деревини немає в каталозі. Брус не додано.");
            return;
        }

        System.out.println("Введіть довжину: ");
        float len = in.nextFloat();
        in.nextLine();

        System.out.println("Введіть висоту: ");
        float h = in.nextFloat();
        in.nextLine();

        System.out.println("Введіть ширину: ");
        float w = in.nextFloat();

        Timber t = new Timber(wd.get(id), len, h, w);
        ps.add(t);
        System.out.println("Брус успішно додано.");
        logging(t.toString());
    }

    static void addWood() {
        System.out.print("Введіть унікальний числовий індетифікатор деревини: ");
        int id1 = in.nextInt();
        in.nextLine();

        System.out.print("Введіть назву деревини: ");
        String name = in.nextLine();

        System.out.print("Введіть щільність деревини: ");
        float density = in.nextFloat();

        Wood wood = new Wood(id1, name, density);
        if (wd.add(wood))
            System.out.println("Деревину успішно додано.\n");
        else System.out.println("Помилка. Деревина з таким індетифікатором уже існує.");
        logging(wood.toString());
    }

    private static void Serialization() {
        File fwd = new File("wd.object");
        try {
            FileOutputStream fos = new FileOutputStream(fwd);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(wd);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File fps = new File("ps.object");
        try {
            FileOutputStream fos1 = new FileOutputStream(fps);
            ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
            oos1.writeObject(ps);
            oos1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logging("Serialization");
    }

    static private void Deserialization() {
        File f = new File("wd.object");
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            wd = (WoodDirectory) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wd != null) {
            for (Object w : wd.getArr())
                System.out.println(w.toString());
        }

        File ff = new File("ps.object");
        try {
            FileInputStream fis1 = new FileInputStream(ff);
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            ps = (ProductStore) ois1.readObject();
            ois1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ps != null) {
            for (Object w : ps.getArr())
                System.out.println(w.toString());
        }
        logging("Deserialization");
    }

    static private void ToTXTFile() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFileChooser dialog = new JFileChooser();
        dialog.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f != null) {
                    return f.isDirectory() || f.toString().endsWith(".txt");
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "файли типу *.txt";
            }
        });
        dialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        dialog.setDialogTitle("Виберіть потрібні файли і папки");
        dialog.setApproveButtonText("Open");
        dialog.setMultiSelectionEnabled(true);
        dialog.showSaveDialog(null);
        File[] ff = dialog.getSelectedFiles();
        if (ff != null) {
            for (File f : ff) {
                System.out.println(f.getAbsolutePath());
                //формування текстового файлу з каталогом деревини та виробів
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                    writer.write(wd.toString());
                    writer.newLine();
                    writer.write(ps.toString());
                    writer.close();
                    System.out.println("Файл збережено");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static private void Name() {
        System.out.println("Введіть ваше ім'я та прізвище");
        String s = in.nextLine();
        try {
            bw.write((new Date()) + " " + s + " logged in");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static private void logging(String s) {
        try {
            bw.write((new Date()) + " added " + s);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}