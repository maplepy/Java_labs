package lab6.test;

import lab6.store.WoodDirectory;

import lab6.model.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;

public class TestFile {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser dialog = new JFileChooser();
        dialog.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f != null)
                {
                    return f.isDirectory() || f.toString().endsWith(".txt");
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "файли типу *.txt";
            }
        });
        dialog.setDialogTitle("Сергієнко А. КН-19");
        dialog.showOpenDialog(null);
        File f = dialog.getSelectedFile();

        writeFile(f);

        readFile(f);

        serialization();

        deserialization();
    }

    private static WoodDirectory deserialization() {
        WoodDirectory wd = null;
        File f = new File("wd.object");
        try{
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            wd = (WoodDirectory) ois.readObject();
            ois.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        if (wd != null)
        {
            for(Object w: wd.getArr())
                System.out.println(w.toString());
        }
        return wd;
    }

    private static void writeFile(File f) {
        BufferedReader reader = null;
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            for(int i = 0; i < 10; i++)
            {
                double x = Math.random();
                String s = String.valueOf(x);
                writer.write(s);
                writer.newLine();
            }
            writer.write(f.getName() + " | " + f.getAbsolutePath());
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void readFile(File f) {
        BufferedReader reader;
        if (f != null)
        {
            try{
                reader = new BufferedReader(new FileReader(f));
                String s;
                while ((s = reader.readLine()) != null){
                    System.out.println(s);
                }
                reader.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void serialization() {
        WoodDirectory wd = new WoodDirectory();
        wd.add(new Wood(4, "Дуб", 1f));
        File f = new File("wd.object");
        try{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(wd);
            oos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
