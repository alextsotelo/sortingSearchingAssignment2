import java.io.PrintWriter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class RandNumb {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        int range = 1000;

        for (int i = 0; i < range; i += 1) {
            numbers.add(i);
        }
        // After we have added every num from 0-999, shuffle to make it unsorted
        Collections.shuffle(numbers);
        FileOperations.createFile(numbers, "RandNumb.txt");

    }
}