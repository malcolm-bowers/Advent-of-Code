import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String[]> pairs = new ArrayList<>();
        List<List<String>> orders = new ArrayList<>();
        getData("Day 5/src/puzzle.txt", pairs, orders);


        int passingOrdersCount = getPassingTests(pairs, orders);
    }
    public static int getPassingTests(List<String[]> pairs, List<List<String>> orders) {
        int passingCount = 0;
        int correctedMiddleNumberSum = 0;
        List<List<String>> invalidOrders = new ArrayList<>();

        for (List<String> order : orders) {
            if (isOrderValid(order, pairs)) {
                passingCount++;
                // int middleIndex = order.size()/2;
                // middleNumberSum += Integer.parseInt(order.get(middleIndex));
            } else {
                invalidOrders.add(order);
            }
        }

        List<List<String>> correctedOrders = correctInvalidOrders(invalidOrders, pairs);

        for (List<String> correctedOrder : correctedOrders) {
            int middleIndex = correctedOrder.size() / 2;
            correctedMiddleNumberSum += Integer.parseInt(correctedOrder.get(middleIndex));
        }
        System.out.println(correctedMiddleNumberSum);
        return passingCount;
    }

    public static List<List<String>> correctInvalidOrders(List<List<String>> invalidOrders, List<String[]> pairs) {
        List<List<String>> correctedOrders = new ArrayList<>();

        for (List<String> order : invalidOrders) {
            List<String> correctedOrder = new ArrayList<>(order);

            correctedOrder.sort((a, b) -> {
                for (String[] pair : pairs) {
                    if (pair[0].equals(a) && pair[1].equals(b)) {
                        return -1;
                    } else if (pair[0].equals(b) && pair[1].equals(a)) {
                        return 1;
                    }
                }
                return 0;
            });

            correctedOrders.add(correctedOrder);
        }

        return correctedOrders;
    }

    public static boolean isOrderValid(List<String> order, List<String[]> pairs) {
        for (String[] pair : pairs) {
            String first = pair[0];
            String second = pair[1];

            int firstIndex = order.indexOf(first);
            int secondIndex = order.indexOf(second);
            if (firstIndex != -1 && secondIndex != -1) {
                if (firstIndex > secondIndex) {
                    return false;
                }
            }
        }
        return true;
    }



    public static void getData(String fileName, List<String[]> pairs, List<List<String>> orders) {
        boolean isOrdersSection = false;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line.trim();
                if (line.isEmpty()) {
                    isOrdersSection = true;
                    continue;
                }

                if(!isOrdersSection) {
                    pairs.add(line.split("\\|"));
                } else {
                    orders.add(Arrays.asList(line.split(",")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}