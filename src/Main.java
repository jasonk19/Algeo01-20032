import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    private static boolean validChoice(String choice, int max) {
        int intChoice;
        try {
            intChoice = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            return false;
        }
        return 1 <= intChoice && intChoice <= max;
    }

    private static int buildMenu(String[] menu, String title, int maxMenu) {
        System.out.println();
        System.out.println("================================================");
        System.out.println(title);
        Arrays.stream(menu).forEachOrdered(System.out::println);
        System.out.println("================================================");
        System.out.println();
        System.out.print("YOUR LIFE CHOICE: ");

        String choice = scanner.next();

        while (!validChoice(choice, maxMenu)) {
            System.out.println("Please enter the correct choice[1-"+maxMenu+"]!!!");
            System.out.print("YOUR LIFE CHOICE: ");
            choice = scanner.next();
        }
        return Integer.parseInt(choice);
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        MainMenu();
    }

    private static void MainMenu() {
        int choice;
        String[] mainMenu = {
                "1. Sistem Persamaan Linier",
                "2. Determinan",
                "3. Matriks balikan",
                "4. Interpolasi Polinom",
                "5. Regresi linier berganda",
                "6. Keluar"
        };

        choice = buildMenu(mainMenu, "MENU", 6);

        switch (choice) {
            case 1:
                SPLMenu();
                break;
            case 2:
                DeterminantMenu();
                break;
            case 3:
                InverseMatrixMenu();
            case 4:
                InterpolationMenu();
                break;
            case 5:
                LinearRegressionMenu();
                break;
            case 6:
                ExitMenu();
                break;
            default:
                break;
        }
    }

    private static void SPLMenu() {
        int choice;
        String[] splMenu = {
                "1. Metode eliminasi Gauss",
                "2. Metode eliminasi Gauss-Jordan",
                "3. Metode matriks balikan",
                "4. Kaidah Cramer"
        };

        choice = buildMenu(splMenu, "Sistem Persamaan Linear", 4);
        ReturnMenu();
    }

    private static void DeterminantMenu() {
        int choice;
        String[] determinantMenu = {
                "1. Metode Eliminasi Gauss",
                "2. Metode Ekspansi Kofaktor-minor"
        };

        choice = buildMenu(determinantMenu, "Determinan", 2);
        ReturnMenu();
    }

    private static void InverseMatrixMenu() {
        int choice;
        String[] inverseMatrixMenu = {
                "1. Metode Adjoin",
                "2. Metode Eliminasi Gauss-Jordan"
        };

        choice = buildMenu(inverseMatrixMenu, "Matriks balikan", 2);
        ReturnMenu();
    }

    private static void InterpolationMenu() {
        ReturnMenu();
    }

    private static void LinearRegressionMenu() {
        ReturnMenu();
    }

    private static void ReturnMenu() {
        System.out.println();
        System.out.print("Ingin melanjutkan menggunakan aplikasi?[y/n] (default: n) ");
        String choice = scanner.next();
        if ((choice.toLowerCase().equals("y"))) {
            MainMenu();
        } else {
            ExitMenu();
        }
    }

    private static void ExitMenu() {
        System.out.println("Terimasih sudah menggunakan aplikasi kami!");
        scanner.close();
        System.exit(0);
    }
}