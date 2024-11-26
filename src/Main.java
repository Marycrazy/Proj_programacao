package src;

public class Main {
    public static void main(String[] args) {
        try {
            //Ficheiros.readObjectsFicheiro();
            pressEnterKey();
            Ficheiros.doUsersExist();
            pressEnterKey();
            mainLoop();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    private static void mainLoop() {
        boolean running = true;
        while (running) {
            clearConsole();
            System.out.println("|------------|");
            System.out.println("|Menu:       |");
            System.out.println("|1. Login    |");
            System.out.println("|2. Register |");
            System.out.println("|0. Exit     |");
            System.out.println("|------------|");
            System.out.print("\nOption: ");
            String option = Input.readLine();
            switch (option) {
                case "1":

                    break;
                case "2":

                    break;
                case "0":
                    clearConsole();
                    System.out.println("A sair...");
                    running = false;
                    break;
                default:
                    clearConsole();
                    System.out.println("Invalid option. Please try again.");
                    pressEnterKey();
                    break;
            }
        }
    }

    // Press any key to continue
    public static void pressEnterKey() {
        System.out.print("Press Enter to continue...");
        Input.readLine();
    }

    // Clear the console
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
