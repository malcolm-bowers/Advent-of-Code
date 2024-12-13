import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> reports = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File("Day 2/src/puzzleData.txt"));

            while (in.hasNextLine()) {
                reports.add(in.nextLine());
            }
            System.out.println(findPassingReports(reports));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int findPassingReports(ArrayList<String> reports) {
        int passingReports = 0;
        for (String report : reports) {
            System.out.println(report);
            int[] intReport = getReport(report);
            if(testReport(intReport)) {
                passingReports++;
            } else {
                for(int i = 0; i < intReport.length; i++) {
                    boolean passing = true;
                    ArrayList<Integer> tReport = new ArrayList<Integer>(intReport.length - 1);
                    for(int j = 0; j < intReport.length; j++) {
                        if(i == j) {
                            continue;
                        } else {
                            tReport.add(intReport[j]);
                        }
                        System.out.println(tReport);
                    }
                    if(!testReport(tReport)) {
                        passing = false;
                    } else {
                        passingReports++;
                        break;
                    };
                }
            };
        }
        return passingReports;
    }

    public static int[] getReport(String report) {
        String[] stringReportValues = report.split(" ");
        int[] intReportValues = new int[stringReportValues.length];
        for (int i = 0; i < stringReportValues.length; i++) {
            intReportValues[i] = Integer.parseInt(stringReportValues[i]);
        }
        return intReportValues;
    }
    public static boolean testReport(ArrayList<Integer> report) {
        boolean increasing = false;
        boolean passing = true;
        if (report.get(0) < report.get(1)) {
            increasing = true;
        }
        for (int i = 0; i < report.size() - 1; i++) {
            int difference = Math.abs(report.get(i) - report.get(i + 1));
            System.out.println(difference);
            if (difference < 4 && difference > 0) {
                if (increasing && report.get(i) > report.get(i + 1)) {
                    System.out.println("Failed: Not Sorted");
                    passing = false;
                } else if (!increasing && report.get(i) < report.get(i + 1)) {
                    System.out.println("Failed: Not Sorted");
                    passing = false;
                }
            } else {
                System.out.println("Failed: Out of bounds");
                passing = false;
            }
        }
        if (passing) {
            System.out.println("Passing report");
            return true;
        } else {
            System.out.println("Failing report");
            return false;
        }
    }

    public static boolean testReport(int[] report) {
        boolean increasing = false;
        boolean passing = true;
        if (report[0] < report[1]) {
            increasing = true;
        }
        for (int i = 0; i < report.length - 1; i++) {
            int difference = Math.abs(report[i] - report[i + 1]);
            System.out.println(difference);
            if (difference < 4 && difference > 0) {
                if (increasing && report[i] > report[i + 1]) {
                    System.out.println("Failed: Not Sorted");
                    passing = false;
                } else if (!increasing && report[i] < report[i + 1]) {
                    System.out.println("Failed: Not Sorted");
                    passing = false;
                }
            } else {
                System.out.println("Failed: Out of bounds");
                passing = false;
            }
        }
        if (passing) {
            System.out.println("Passing report");
            return true;
        } else {
            System.out.println("Failing report");
            return false;
        }
    }
}