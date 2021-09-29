package lib.solver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import lib.matriks.matriks;
import lib.Utils.*;

public class Regression {

    private static void saveRegressionToFile(matriks data, matriks hasil, float y, float[] peubah, String namaFile) {
        String line = null;

        try {
            String newFileDir = "./hasil/" + namaFile;
            FileWriter writeRegresi = new FileWriter(newFileDir);

            String dataRegresi = "Data Regresi: \n";
            for (int i=0; i < data.NeffK; i++) {
                if (i != data.NeffK - 1) {
                    dataRegresi += "x" + (i+1) + " | ";
                } else {
                    dataRegresi += "y \n";
                }
            }
            for (int i=0; i < data.NeffB; i++) {
                for (int j=0; j < data.NeffK; j ++) {
                    dataRegresi += data.Mat[i][j] + " | ";
                }
                dataRegresi += "\n";
            }

            line = dataRegresi + "\nPersamaan hasil regresi: ";
            line += "y = " + hasil.Mat[0][hasil.NeffK-1] + " + ";
            for (int i = 1; i < hasil.NeffB; i++) {
                if (i == hasil.NeffB-1) {
                    line += hasil.Mat[i][hasil.NeffK-1] + "x" + i;
                } else {
                    line += hasil.Mat[i][hasil.NeffK-1] + "x" + i + " + ";
                }
            }

            line += "\nNilai yang ditaksir adalah \n";
            for (int i=0; i < peubah.length; i++) {
                line += "x" + (i+1) + "=" + peubah[i] + ", ";
            }

            line += "\n\nHasil taksiran adalah y = " + y;

            writeRegresi.write(line);
            writeRegresi.close();
            System.out.println("Berhasil menyimpan hasil regresi pada folder hasil, file \"" + namaFile + "\".");
        } catch(IOException e) {
            System.err.println("Error.");
            e.printStackTrace();
        }
    }

    public static void LinearRegression() {
        Scanner scanner = new Scanner(System.in);
        int type = Utils.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int[] rowNCol = Utils.inputDataRegression();
            matriks.readMatriks(rowNCol[0], rowNCol[1] + 1);
        } else if (type == 2) {
            matriks.readMatriks(Utils.inputDariFile());
        }

        System.out.println();
        System.out.println("Nilai yang ingin ditaksir");
        float[] peubah = new float[matriks.NeffK - 1];
        for (int i=0; i < matriks.NeffK - 1; i++) {
            System.out.print("Masukkan nilai peubah x" + (i + 1) + ": ");
            peubah[i] = scanner.nextFloat();
        }

        matriks regMatrix = new matriks();
        regMatrix.NeffK = matriks.NeffK + 1;
        regMatrix.NeffB = matriks.NeffK;

        // Inisiasi baris pertama dan kolom pertama
        regMatrix.Mat[0][0] = matriks.NeffB;
        for (int i = 1; i < regMatrix.NeffK; i++) {
            for (int j = 0; j < matriks.NeffB; j++) {
                regMatrix.Mat[0][i] += matriks.Mat[j][i-1];
            }
        }
        for (int i=1; i < regMatrix.NeffB; i++) {
            regMatrix.Mat[i][0] = regMatrix.Mat[0][i];
        }

        // Normal Estimation Equation
        for (int i=1; i < matriks.NeffK; i++) {
            for (int j=1; j < regMatrix.NeffK; j++) {
                for (int k=0; k < matriks.NeffB; k++) {
                    float multiplier = matriks.Mat[k][i-1];
                    regMatrix.Mat[i][j] += matriks.Mat[k][j-1] * multiplier;
                }
            }
        }
        regMatrix.eliminasiGaussJordan();

        float taksiran = regMatrix.Mat[0][regMatrix.NeffK-1];
        for (int i=1; i < regMatrix.NeffK - 1; i++) {
            taksiran += regMatrix.Mat[i][regMatrix.NeffK-1] * peubah[i-1];
        }

        System.out.println();
        System.out.println("Hasil taksiran menggunakan regresi linear berganda adalah " + taksiran);

        System.out.println();
        System.out.print("Simpan Hasil Interpolasi?(y/n) ");
        char simpan = scanner.next().charAt(0);
        String namaFile;

        if (simpan == 'y') {
            System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
            namaFile = scanner.next();
            saveRegressionToFile(matriks, regMatrix, taksiran, peubah, namaFile);
        }
    }
}
