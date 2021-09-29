import java.util.Scanner;

public class Main {

    public static Scanner scanner;

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

        choice = Utils.buildMenu(mainMenu, "MENU", 6);

        switch (choice) {
            case 1:
                SPLMenu();
                break;
            case 2:
                DeterminantMenu();
                break;
            case 3:
                InverseMatrixMenu();
                break;
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

        choice = Utils.buildMenu(splMenu, "Sistem Persamaan Linear", 4);
        if (choice == 1){
            solver.gaussSolver();
        } else if (choice == 2)
        {
            solver.gaussJordanSolver();
        } else if (choice == 3)
        {
            solver.inversSolver();
        } else if (choice == 4){
            solver.determSolver();
        }
        ReturnMenu();
    }

    private static void DeterminantMenu() {
        int choice;
        String[] determinantMenu = {
                "1. Metode Eliminasi Gauss",
                "2. Metode Ekspansi Kofaktor-minor"
        };

        choice = Utils.buildMenu(determinantMenu, "Determinan", 2);
        if (choice == 1) {
            Determinant.gaussianDet();
        } else if (choice == 2) {
            Determinant.cofactorMinorDet();
        }
        ReturnMenu();
    }

    private static void InverseMatrixMenu() {
        int choice;
        String[] inverseMatrixMenu = {
                "1. Metode Adjoin",
                "2. Metode Eliminasi Gauss-Jordan"
        };

        choice = Utils.buildMenu(inverseMatrixMenu, "Matriks balikan", 2);
        if (choice == 1) {
            Inversion.cofactorMinorInversion();
        } else if (choice == 2) {
            Inversion.gaussianInversion();
        }
        ReturnMenu();
    }

    // Masih testing interpolasi
    private static void InterpolationMenu() {
        solver.interpolasiSolver();
        ReturnMenu();
    }

    private static void LinearRegressionMenu() {
        Regression.LinearRegression();
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
    public static boolean isNumber(String str) { 
        try {  
            Float.parseFloat(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
    }
}