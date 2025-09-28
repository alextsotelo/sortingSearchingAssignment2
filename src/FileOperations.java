import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperations {
    public static void createFile(List<Integer> numbers, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            for (int num : numbers) {
                writer.println(num);
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Error creating file.");
        }

        System.out.println("Created file successfully");
    }

    public static List<Integer> readFile(String fileName) {
        List<Integer> numbers = new ArrayList<>();

        try {
            Scanner scnr = new Scanner(new File(fileName));
            while (scnr.hasNextInt()) {
                numbers.add(scnr.nextInt());
            }
        } catch (Exception e) {
            System.out.println("Error reading the file.");
        }

        return numbers;
    }
}
