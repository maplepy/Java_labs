package lab7.test;

import java.util.*;
import lab7.model.Wood;

public class TestCollections {
    static Random rnd = new Random();
    public static void main(String[] args) {
        task1();
        System.out.println("\n# # # # # # # # #\n");
        task2();
        System.out.println("\n# # # # # # # # #\n");
        task3();
        System.out.println("\n# # # # # # # # #\n");
        task5();
        System.out.println("\n# # # # # # # # #\n");
        task6();
    }

    private static void task1() {
        Collection<Integer> c1 = new Vector<>();
        for (int i = 0; i < 15; i++){
            c1.add(rnd.nextInt(10));
        }
        System.out.println("Collection vector");
        for(Integer x: c1)
            System.out.printf("%d ", x);
        Collection<Integer> c2 = new TreeSet<>(c1);
        System.out.println("\nCollection TreeSet");
        c2.forEach((x) -> System.out.printf("%d ", x));
    }

    public static void task2() {
        Collection<Integer> c1 = new ArrayList<>();
        Collection<Integer> c2 = new LinkedHashSet<>();
        Collection<Integer> c3 = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            c1.add(rnd.nextInt(10));
            c2.add(rnd.nextInt(10));
        }
        c3.addAll(c1); c3.removeAll(c2);
        System.out.println(c1 + " removeAll " + c2 + " = " + c3 + "\n");

        c3.clear(); c3.addAll(c2); c3.removeAll(c1);
        System.out.println(c2 + " removeAll " + c1 + " = " + c3 + "\n");

        c3.clear(); c3.addAll(c1); c3.removeAll(c2);
        System.out.println(c1 + " removeAll " + c2 + " = " + c3 + "\n");

        c3.clear(); c3.addAll(c2); c3.removeAll(c1);
        System.out.println(c2 + " removeAll " + c1 + " = " + c3 + "\n");
    }
    public static void task3(){
        Collection<Integer> c1 = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            c1.add(rnd.nextInt(10));
        }
        Collection<Integer> c2 = new LinkedHashSet<>();
        c2.addAll(c1);
        boolean b1 = c1.containsAll(c1);
        System.out.println(c1 + " containsAll " + c2 + " = " + b1);

        Collection<Integer> c3 = new TreeSet<>();
        c3.addAll(c1);
        boolean b2 = c1.containsAll(c3);
        System.out.println(c1 + " containsAll " + c3 + " = " + b2);
    }
    public static void task4(){
        Collection<Integer> c1 = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            c1.add(rnd.nextInt(10));
        }
        ((ArrayList<Integer>) c1).sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a-b;
            }
        });
    }
    public static void task5(){
        Collection<Integer> coll = new ArrayDeque<>();
        Collections.addAll(coll, 1, 3, 5, 3, 4, 2, 14);
        Collections.addAll(coll, new Integer[]{3, 7, 12});

        System.out.println(coll);
        System.out.println(Collections.frequency(coll, 3));
        System.out.println(Collections.max(coll));
        System.out.println(Collections.min(coll));
        System.out.println("#####");

        List<Integer> list = new ArrayList<>(coll);
        Collections.swap(list, 2, 6);
        System.out.println("Swap 2 - 6 " + list);
        Collections.sort(list);
        System.out.println("Sort " + list);
        System.out.println("BinarySearch key 3: " + Collections.binarySearch(list, 3, (a, b) -> b - a));
        Collections.reverse(list);
        System.out.println("Reverse " + list);
        Collections.rotate(list, 5);
        System.out.println("Rotate " + list);
        Collections.shuffle(list);
        System.out.println("Shuffle " + list);
        Collections.replaceAll(list, 3, 8);
        System.out.println("ReplaceAll " + list);
        Collections.fill(list, 0);
        System.out.println("Fill " + list);
    }
    public static void task6(){
        Set<Wood> set = new HashSet<>();
        set.add(new Wood(1, "Сосна", 1f));
        set.add(new Wood(1, "Сосна", 1f));
        set.add(new Wood(1, "Сосна", 1f));

        set.forEach(System.out::println);
    }
}
