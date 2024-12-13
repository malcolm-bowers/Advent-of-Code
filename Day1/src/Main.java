import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        try {
            Scanner in = new Scanner(new File("src/numbers.txt"));
            while(in.hasNextLine()) {
                list1.add(in.nextInt());
                list2.add(in.nextInt());
            }
            Collections.sort(list1);
            Collections.sort(list2);
            System.out.println(list1);
            System.out.println(list2);
            System.out.println(findDiff(list1, list2));
            System.out.println(findSimilar(list2, list1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int findDiff(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> differenceList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            differenceList.add(Math.abs(list1.get(i) - list2.get(i)));
        }
        int diff = 0;
        for (int i = 0; i < differenceList.size(); i++) {
            diff += differenceList.get(i);
        }
        return diff;
    }

    public static int findSimilar(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> similarList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            similarList.add(Collections.frequency(list2, list1.get(i)) * list1.get(i));
        }
        System.out.println(similarList);
        int similarSum = 0;
        for (int i = 0; i < similarList.size(); i++) {
            similarSum += similarList.get(i);
        }
        return similarSum;
    }
}