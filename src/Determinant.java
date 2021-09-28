import java.io.FileWriter;
import java.io.IOException;

public class Determinant {

    private static void saveDeterminantToFile(matriks data, float determinant, String namaFile) {
        String line = "";

        try {
            String newFileDir = "./hasil/" + namaFile;
            FileWriter writeDeterm = new FileWriter(newFileDir);

            String dataMatrix = "Data matriks: \n";
            for (int i=0; i < data.NeffB; i++) {
                for (int j=0; j < data.NeffK; j ++) {
                    dataMatrix += data.Mat[i][j] + " | ";
                }
                dataMatrix += "\n";
            }

            line += dataMatrix + "\nHasil determinan adalah " + determinant;

            writeDeterm.write(line);
            writeDeterm.close();
            System.out.println("Berhasil menyimpan hasil determinan pada folder hasil, file \"" + namaFile + "\".");
        } catch(IOException e) {
            System.err.println("Error.");
            e.printStackTrace();
        }
    }

    public static void gaussianDet() {
        int type = Main.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
            if (matriks.NeffB != matriks.NeffK) {
                System.out.println("Matriks tidak berbentuk persegi, determinan tidak ada!");
                return;
            }
        }
        matriks copyMat = new matriks();
        copyMat.copyMatriksToThis(matriks);
        copyMat.displayMatriks();
        matriks.eliminasiGauss();

        for (int i = 0; i < matriks.NeffB; i++) {
            matriks.det *= matriks.Mat[i][i];
        }

        System.out.println();
        System.out.println("Determinan = " + Main.fixFloatingPoint(matriks.det));

        System.out.println();
        System.out.print("Simpan Hasil Determinan?(y/n) ");
        char simpan = Main.scanner.next().charAt(0);
        String namaFile;

        if (simpan == 'y') {
            System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
            namaFile = Main.scanner.next();
            saveDeterminantToFile(copyMat, Main.fixFloatingPoint(matriks.det), namaFile);
        }
    }

    public static void cofactorMinorDet() {
        int type = Main.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
            if (matriks.NeffB != matriks.NeffK) {
                System.out.println("Matriks tidak berbentuk persegi, determinan tidak ada!");
                return;
            }
        }
        float det = matriks.determinantRecc(matriks, matriks.NeffB);

        System.out.println();
        System.out.println("Determinan = " + Main.fixFloatingPoint(det));

        System.out.println();
        System.out.print("Simpan Hasil Determinan?(y/n) ");
        char simpan = Main.scanner.next().charAt(0);
        String namaFile;

        if (simpan == 'y') {
            System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
            namaFile = Main.scanner.next();
            saveDeterminantToFile(matriks, det, namaFile);
        }
    }
}
