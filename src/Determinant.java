import java.io.File;

public class Determinant {

    public static void gaussianDet() {
        int type = Main.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int rowNCol;
            System.out.print("Masukkan jumlah baris dan kolom: ");
            String input = Main.scanner.next();
            while (!Main.validChoice(input, 100)) {
                System.out.println("Masukkan jumlah yang benar");
                System.out.print("Masukkan jumlah baris dan kolom: ");
                input = Main.scanner.next();
            }
            rowNCol = Integer.parseInt(input);
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            System.out.print("Masukkan nama file: ");
            String filename = Main.scanner.next();
            while (!(new File("../test/" + filename)).exists()) {
                System.out.println("File not found! Enter the correct name");
                System.out.print("Masukkan nama file: ");
                filename = Main.scanner.next();
            }
            matriks.readMatriks(filename);
        }
        matriks.eliminasiGauss();

        for (int i = 0; i < matriks.NeffB; i++) {
            matriks.det *= matriks.Mat[i][i];
        }

        System.out.println();
        System.out.println("Determinant = " + Main.fixFloatingPoint(matriks.det));
    }
}
