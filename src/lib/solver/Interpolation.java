package lib.solver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import lib.matriks.matriks;

public class Interpolation {

  matriks readTitikInterpolation() {
    Scanner scanner = new Scanner(System.in);
    matriks titik = new matriks();
    int N;

    System.out.println("Masukkan jumlah titik: ");
    N = scanner.nextInt();

    System.out.println("Masukkan kombinasi titik: ");
    titik.readMatriks(N,2);

    return titik;
  }

  // Masih belum coba, baru implementation sama kayak yang di matriks.java
  matriks readFileTitikInterpolation(String filename) {
    matriks titik = new matriks();
    titik.readMatriks(filename);

    return titik;
  }

  double readxTaksiran() {
    Scanner scanner = new Scanner(System.in);
    double x;
    x = scanner.nextDouble();
    return x;
  }
 
  void convertToMatAug(matriks titik) {

    titik.NeffK = titik.NeffK+(titik.NeffB-1);

    double[] x = new double[titik.NeffB];
    double[] y = new double[titik.NeffB];

    int i,j;
    for (i = 0; i < titik.NeffB; i++) {
      x[i] = titik.Mat[i][0];
      y[i] = titik.Mat[i][1];
    }

    for (i = 0; i < titik.NeffB; i++) {
      for (j = 0; j < titik.NeffK; j++) {
        if (j == 0) {
          titik.Mat[i][j] = 1;
        } else if (j == titik.NeffK-1) {
          titik.Mat[i][j] = (float) y[i];
        } else {
          titik.Mat[i][j] = (float) Math.pow(x[i], j);
        }
      }
    }
    System.out.println();
  }

  void displayInterpolasi(int count, double[] bilX, double[] y) {
    System.out.println("Hasil interpolasi: ");
    for (int i = 0; i < count; i++) {
      System.out.println("f(" + bilX[i] + ") = " + y[i]);
    }
  }

  void displayPolinomInterpolasi(double[] data) {
    System.out.print("f(x) = ");
    for (int i = 0; i < data.length; i++) {
      if (i == 0) {
        System.out.print(data[i] + " + ");
      } else if (i == data.length - 1) {
        System.out.print("(" + data[i] + ")" + "x^" + i);
      } else {
        System.out.print("(" + data[i] + ")" + "x^" + i + " + ");
      }
    }
    System.out.println();
  }

  void displayInterpolasiToFile(matriks titik, double[] solve, int count, double[] bilX, double[] y, String namaFile) {
    String line;

    try {
      String newFileDir = "../hasil/" + namaFile;
      FileWriter writeInterpolasi = new FileWriter(newFileDir);

      int i,j;

      String persamaanLanjar = "Sistem Persamaan Lanjar: \n";
      if (titik.NeffB == 1) {
        persamaanLanjar += "a0";
        persamaanLanjar += " = ";
        persamaanLanjar += Double.toString(titik.Mat[0][1]);
      } else {
        for (i = 0; i < titik.NeffB; i++) {
          for (j = 0; j < titik.NeffK; j++) {
            if (j == 0) {
              persamaanLanjar += "a0";
              persamaanLanjar += " + ";
            } else if (j == titik.NeffK-2) {
              persamaanLanjar += Double.toString(titik.Mat[i][j]);
              persamaanLanjar += "a";
              persamaanLanjar += Integer.toString(j);
              persamaanLanjar += " = ";
            } else if (j == titik.NeffK-1) {
              persamaanLanjar += Double.toString(titik.Mat[i][j]);
            } else {
              persamaanLanjar += Double.toString(titik.Mat[i][j]);
              persamaanLanjar += "a";
              persamaanLanjar += Integer.toString(j);
              persamaanLanjar += " + ";
            }
          }
          persamaanLanjar += "\n";
        }
      }

      String persamaanPolinom = "\nPersamaan Polinom: \n";
      persamaanPolinom += "f(x) = ";
      for (i = 0; i < solve.length; i++) {
        if (i == 0) {
          persamaanPolinom += solve[i] + " + ";
        } else if (i == solve.length - 1) {
          persamaanPolinom += "(" + solve[i] + ")" + "x^" + i;
        } else {
          persamaanPolinom += "(" + solve[i] + ")" + "x^" + i + " + ";
        }
      }
      persamaanPolinom += "\n";

      line = persamaanLanjar + persamaanPolinom + "\nHasil interpolasi dari nilai: \n";
      for (i = 0; i < count; i++) {
        line += (i+1) + ". x = " + bilX[i] + "\n";
      }
      line += "adalah: \n";
      for (i = 0; i < count; i++) {
        line += (i+1) + ". f(" + bilX[i] + ") = " + y[i] + "\n";
      }
      writeInterpolasi.write(line);
      writeInterpolasi.close();
      System.out.println("Berhasil menyimpan hasil Interpolasi pada folder hasil, file \"" + namaFile + "\".");
    } catch(IOException e) {
      System.err.println("Error.");
      e.printStackTrace();
    }
  }

  double[] getHasilGauss(matriks titik) {
    double[] sol = new double[titik.NeffB];
    int i,j;
    for (i = titik.NeffB-1; i >= 0; i--) {
      if (i == titik.NeffB-1) {
        sol[i] = titik.Mat[i][titik.NeffK-1];
      } else {
        double tempA = 0;
        for (j = 0; j < titik.NeffK-1; j++) {
          if (titik.Mat[i][j] != 0 || titik.Mat[i][j] != 1) {
            tempA += titik.Mat[i][j] * sol[j];
          }
        }
        sol[i] = titik.Mat[i][titik.NeffK-1] - tempA;
      }
    }

    return sol;
  }

  double[] getGaussSolve(matriks titik) {
    titik.eliminasiGauss();
    double[] sol = new double[titik.NeffB];
    int i,j;
    for (i = titik.NeffB-1; i >= 0; i--) {
      if (i == titik.NeffB-1) {
        sol[i] = titik.Mat[i][titik.NeffK-1];
      } else {
        double tempA = 0;
        for (j = 0; j < titik.NeffK-1; j++) {
          if (titik.Mat[i][j] != 0 || titik.Mat[i][j] != 1) {
            tempA += titik.Mat[i][j] * sol[j];
          }
        }
        sol[i] = titik.Mat[i][titik.NeffK-1] - tempA;
      }
    }
    return sol;
  }

  double interpolasiPolinom(double[] titik, double x) {
    double hasilInterpolasi = 0;
    for (int i = 0; i < titik.length; i++) {
      hasilInterpolasi += titik[i] * Math.pow(x, i);
    }

    return hasilInterpolasi;
  }

}