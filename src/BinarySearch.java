import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        List<Integer> numbers = FileOperations.readFile("sortednumb.txt");

        // Search for number 1 & track time
        binarySearch(numbers, 1);
        // Search for number 500 & track time
        binarySearch(numbers, 500);
        // Search for number 900 & track time
        binarySearch(numbers, 900);
    }

    public static void binarySearch(List<Integer> numbers, int target) {
        ThreadMXBean cpuTimer = ManagementFactory.getThreadMXBean();
        cpuTimer.setThreadCpuTimeEnabled(true);
        long startTime = cpuTimer.getCurrentThreadCpuTime();

        int low = 0;
        int high = numbers.size() - 1;

        while (low <= high) {
            int middleIdx = (high + low) / 2;
            int middleVal = numbers.get(middleIdx);
            // 1 2 3 4 5 6 7 8 9 10

            if (middleVal == target) {
                long endTime = cpuTimer.getCurrentThreadCpuTime();
                long durationNano = endTime - startTime;
                double durationInSeconds = durationNano / 1_000_000_000.0;
                System.out.println("Number " + target + " found in: " + durationInSeconds + " seconds.");
                return;
            } else if (middleVal > target) {
                high = middleIdx - 1;
            } else {
                low = middleIdx + 1;
            }
        }
    }
}
