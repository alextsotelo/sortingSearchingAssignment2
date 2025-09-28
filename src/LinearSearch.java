import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class LinearSearch {
    public static void main(String[] args) {
        List<Integer> numbers = FileOperations.readFile("RandNumb.txt");

        // Search for number 1 & track time
        searchNumber(numbers, 1);
        // Search for number 500 & output time
        searchNumber(numbers, 500);
        // Search for number 900 & output time
        searchNumber(numbers, 900);
    }

    public static void searchNumber(List<Integer> numbers, int target) {
        ThreadMXBean cpuTimer = ManagementFactory.getThreadMXBean();
        cpuTimer.setThreadCpuTimeEnabled(true);
        long startTime = cpuTimer.getCurrentThreadCpuTime();

        for (int i = 0; i < numbers.size(); i += 1) {
            if (numbers.get(i) == target) {
                long endTime = cpuTimer.getCurrentThreadCpuTime();
                long durationNano = endTime - startTime;
                double durationInSeconds = durationNano / 1_000_000_000.0;
                System.out.println("Number " + target + " found in: " + durationInSeconds + " seconds.");
                return;
            }
        }
    }
}
