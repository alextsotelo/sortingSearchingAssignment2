import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class BubbleSort {
    public static void main(String[] args) {
        List<Integer> numbers = FileOperations.readFile("RandNumb.txt");
        bubbleSort(numbers);
        FileOperations.createFile(numbers, "sortednumb.txt");
    }
    public static void bubbleSort(List<Integer> numbers) {
        ThreadMXBean cpuTimer = ManagementFactory.getThreadMXBean();
        cpuTimer.setThreadCpuTimeEnabled(true);
        long startTime = cpuTimer.getCurrentThreadCpuTime();

        for (int i = 0; i < numbers.size() - 1; i += 1) {
            for (int j = 0; j < numbers.size() - 1 - i; j += 1) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    int temp = numbers.get(j + 1);
                    numbers.set(j + 1, numbers.get(j));
                    numbers.set(j, temp);
                }
            }
        }
        long endTime = cpuTimer.getCurrentThreadCpuTime();
        long durationNano = endTime - startTime;
        double durationInSeconds = durationNano / 1_000_000_000.0;

        System.out.println("Used bubble sort successfully in: " + durationInSeconds + " seconds.");
    }
}
