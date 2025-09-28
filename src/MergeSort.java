import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class MergeSort {
    public static void main(String[] args) {
        List<Integer> numbers = FileOperations.readFile("RandNumb.txt");

        ThreadMXBean cpuTimer = ManagementFactory.getThreadMXBean();
        cpuTimer.setThreadCpuTimeEnabled(true);
        long startTime = cpuTimer.getCurrentThreadCpuTime();

        List<Integer> sortedNumbers = mergeSort(numbers);

        long endTime = cpuTimer.getCurrentThreadCpuTime();
        long durationNano = endTime - startTime;
        double durationInSeconds = durationNano / 1_000_000_000.0;

        System.out.println("Used merge sort successfully in: " + durationInSeconds + " seconds.");

        FileOperations.createFile(sortedNumbers, "sortednumb.txt");
    }

    public static List<Integer> mergeSort(List<Integer> numbers) {
        // Base Case: Smallest list
        if (numbers.size() <= 1) {
            return numbers;
        }

        int middleIdx = numbers.size() / 2;

        List<Integer> left = numbers.subList(0, middleIdx);
        List<Integer> right = numbers.subList(middleIdx, numbers.size());

        List<Integer> sortedLeft = mergeSort(left);
        List<Integer> sortedRight = mergeSort(right);

        return merge(sortedLeft, sortedRight);
    }

    public static List<Integer> merge (List<Integer> list1, List<Integer> list2) {
        List<Integer> merged = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < list1.size() && j < list2.size()) {
            int val1 = list1.get(i);
            int val2 = list2.get(j);

            if (val1 < val2) {
                merged.add(val1);
                i += 1;
            } else {
                merged.add(val2);
                j += 1;
            }
        }

        merged.addAll(list1.subList(i, list1.size()));
        merged.addAll(list2.subList(j, list2.size()));

        return merged;
    }
}
