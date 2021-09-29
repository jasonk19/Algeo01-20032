package lib.Utils;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Utils {
    public static boolean invalidChoice(String choice, int max) {
        int intChoice;
        try {
            intChoice = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            return true;
        }
        return 1 > intChoice || intChoice > max;
    }

    public static int buildMenu(String[] menu, String title, int maxMenu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("================================================");
        System.out.println(title);
        Arrays.stream(menu).forEachOrdered(System.out::println);
        System.out.println("================================================");
        System.out.println();
        System.out.print("YOUR LIFE CHOICE: ");

        String choice = scanner.next();

        while (invalidChoice(choice, maxMenu)) {
            System.out.println("Please enter the correct choice[1-"+maxMenu+"]!!!");
            System.out.print("YOUR LIFE CHOICE: ");
            choice = scanner.next();
        }
        return Integer.parseInt(choice);
    }

    public static int inputData() {
        int inputType;
        String[] inputTypeMenu = {
                "1. Input data dari keyboard",
                "2. Input data dari file"
        };
        inputType = buildMenu(inputTypeMenu, "Input Type", 2);
        return inputType;
    }

    public static int inputBarisNKolom() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris dan kolom: ");
        String input = scanner.next();
        while (invalidChoice(input, 100)) {
            System.out.println("Masukkan jumlah yang benar");
            System.out.print("Masukkan jumlah baris dan kolom: ");
            input = scanner.next();
        }
        return Integer.parseInt(input);
    }

    public static int[] inputDataRegression() {
        Scanner scanner = new Scanner(System.in);
        int[] colNRow = {0, 0};

        System.out.print("Masukkan jumlah peubah (x): ");
        String kolom = scanner.next();
        while (invalidChoice(kolom, 100)) {
            System.out.println("Masukkan jumlah yang benar");
            System.out.print("Masukkan jumlah peubah (x): ");
            kolom = scanner.next();
        }

        System.out.print("Masukkan jumlah data: ");
        String baris = scanner.next();
        while (invalidChoice(baris, 100)) {
            System.out.println("Masukkan jumlah yang benar");
            System.out.print("Masukkan jumlah data: ");
            baris = scanner.next();
        }

        colNRow[0] = Integer.parseInt(baris);
        colNRow[1] = Integer.parseInt(kolom);

        return colNRow;
    }

    public static String inputDariFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file: ");
        String filename = scanner.next();
        while (!(new File("../test/" + filename)).exists()) {
            System.out.println("File not found! Enter the correct name");
            System.out.print("Masukkan nama file: ");
            filename = scanner.next();
        }
        return filename;
    }

    public static float fixFloatingPoint(float number) {
        return (Math.abs(Math.round(number) - number)) < 1.0e-4 ? Math.round(number) : number;
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
