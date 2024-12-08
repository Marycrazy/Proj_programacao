package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Input {
    private static Scanner scanner;
    private static BufferedReader fileReader;
    private static BufferedWriter fileWriter;

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

    public static int readInt() {
        try {
            return getScanner().nextInt();
        } catch (Exception e) {
            System.out.println("Error reading input.");
            System.out.println("Exception: " + e);
            return -1;
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

    public static void openFIleReade(String filename) {
        try {
            fileReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }

    // Abre o arquivo para escrita (append)
    public static void openFileWrite(String filename, boolean append) {
        try {
            fileWriter = new BufferedWriter(new FileWriter(filename, append));
        } catch (IOException e) {
            System.out.println("Error opening file for writing: " + e.getMessage());
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

    // Escreve uma linha no arquivo
    public static void writeFileLine(String line) {
        try {
            if (fileWriter != null) {
                //System.out.println("hello");
                fileWriter.write(line);
                fileWriter.newLine(); // Adiciona nova linha após a escrita
                fileWriter.flush(); // Força a escrita imediata no arquivo
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
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

    // Abre o arquivo para escrita (append)
    public static ObjectOutputStream openObjectWrite(String filename, boolean append) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename, append));
            return oos;
        } catch (IOException e) {
            System.out.println("Error opening file for writing: " + e.getMessage());
            return null;
        }
    }

}
