package lib.solver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import lib.matriks.matriks;
import lib.Utils.*;

public class Inversion {

    private static void saveInverseToFile(matriks req, matriks res, String namaFile) {
        String line = "";

        try {
            String newFileDir = "../hasil/" + namaFile;
            FileWriter writeDeterm = new FileWriter(newFileDir);

            String dataMatrix = "Matriks awal: \n";
            for (int i=0; i < req.NeffB; i++) {
                for (int j=0; j < req.NeffK; j ++) {
                    dataMatrix += req.Mat[i][j] + " | ";
                }
                dataMatrix += "\n";
            }

            line += dataMatrix + "\nMatriks balikan: \n";
            for (int i=0; i < res.NeffB; i++) {
                for (int j=0; j < res.NeffK; j ++) {
                    line += res.Mat[i][j] + " | ";
                }
                line += "\n";
            }

            writeDeterm.write(line);
            writeDeterm.close();
            System.out.println("Berhasil menyimpan hasil invers pada folder hasil, file \"" + namaFile + "\".");
        } catch(IOException e) {
            System.err.println("Error.");
            e.printStackTrace();
        }
    }

    public static void gaussianInversion() {
        Scanner scanner = new Scanner(System.in);
        int type = Utils.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int rowNCol = Utils.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Utils.inputDariFile());
            if (matriks.NeffB != matriks.NeffK-1) {
                System.out.println("Matriks tidak berbentuk persegi, tidak mempunyai invers");
                return;
            }
        }
        matriks copyMat = new matriks();
        copyMat.copyMatriksToThis(matriks);

        matriks.gaussJordanInversOf(matriks);

        System.out.println("Matriks balikan: ");
        matriks.displayMatriks();

        System.out.println();
        System.out.print("Simpan Hasil Invers?(y/n) ");
        char simpan = scanner.next().charAt(0);
        String namaFile;

        if (simpan == 'y') {
            System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
            namaFile = scanner.next();
            saveInverseToFile(copyMat, matriks, namaFile);
        }
    }

    public static void cofactorMinorInversion() {
        Scanner scanner = new Scanner(System.in);
        int type = Utils.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int rowNCol = Utils.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Utils.inputDariFile());
            if (matriks.NeffB != matriks.NeffK-1) {
                System.out.println("Matriks tidak berbentuk persegi, tidak mempunyai invers");
                return;
            }
        }
        matriks copyMat = new matriks();
        copyMat.copyMatriksToThis(matriks);

        matriks.cofactorMinorInvers();

        System.out.println("Matriks balikan: ");
        matriks.displayMatriks();

        System.out.println();
        System.out.print("Simpan Hasil Invers?(y/n) ");
        char simpan = scanner.next().charAt(0);
        String namaFile;

        if (simpan == 'y') {
            System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
            namaFile = scanner.next();
            saveInverseToFile(copyMat, matriks, namaFile);
        }
    }
}
