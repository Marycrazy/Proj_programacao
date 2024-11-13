package src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Input {
    private static Scanner scanner;
    private static BufferedReader fileReader;

    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static String readLine() {
        try {
            return getScanner().nextLine();
        } catch (Exception e) {
            System.out.println("Error reading input.");
            System.out.println("Exception: " + e);
            return null;
        }
    }

    public static boolean hasNextInt() {
        try {
            return getScanner().hasNextInt();
        } catch (Exception e) {
            System.out.println("Error reading input.");
            System.out.println("Exception: " + e);
            return false;
        }
    }

    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }

    public static void openFile(String filename) {
        try {
            fileReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }

    public static String readFileLine() {
        try {
            if (fileReader != null) {
                return fileReader.readLine();
            }
            return null;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static void closeFile() {
        if (fileReader != null) {
            try {
                fileReader.close();
                fileReader = null;
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

}
