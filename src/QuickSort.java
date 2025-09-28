import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class QuickSort {
    public static void main (String[] args) {
        List<Integer> numbers = FileOperations.readFile("RandNumb.txt");

        ThreadMXBean cpuTimer = ManagementFactory.getThreadMXBean();
        cpuTimer.setThreadCpuTimeEnabled(true);
        long startTime = cpuTimer.getCurrentThreadCpuTime();

        quickSort(numbers);

        long endTime = cpuTimer.getCurrentThreadCpuTime();
        long durationNano = endTime - startTime;
        double durationInSeconds = durationNano / 1_000_000_000.0;

        System.out.println("Used quick sort successfully in: " + durationInSeconds + " seconds.");

        FileOperations.createFile(numbers, "sortednumb.txt");
    }

    public static void quickSort (List<Integer> numbers) {
        quickSort(numbers, 0, numbers.size() - 1 );
    }
    public static void quickSort (List<Integer> numbers, int lowIdx, int highIdx) {
        if (lowIdx >= highIdx) {
            return;
        }

        // Always pick the first index for pivot
        int pivot = numbers.get(lowIdx);

        // Now, partitioning complete. Now recursively quickSort subarray to left and right of pivot

        int leftPointer = lowIdx;
        int rightPointer = highIdx;

        while (leftPointer < rightPointer) {

            // Move right pointer left until it is at a num smaller than pivot
            while (numbers.get(rightPointer) >= pivot && leftPointer < rightPointer) {
                rightPointer -= 1;
            }
            // Move left pointer right until it's at a num bigger than pivot
            while (numbers.get(leftPointer) <= pivot && leftPointer < rightPointer) {
                leftPointer += 1;
            }

            // Swap the two elements the pointers have found
            swap(numbers, leftPointer, rightPointer);
        }

        // Once the pointers are at same spot, STOP and swap pivot w/ number that left pointer is on
        swap(numbers, leftPointer, lowIdx);

        quickSort(numbers, lowIdx, leftPointer - 1);
        quickSort(numbers, leftPointer + 1, highIdx);
    }

    public static void swap (List<Integer> numbers, int idx1, int idx2) {
        int temp = numbers.get(idx1);
        numbers.set(idx1, numbers.get(idx2));
        numbers.set(idx2, temp);
    }
}
