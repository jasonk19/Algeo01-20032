package lib.solver;

import java.io.FileWriter;
import java.io.IOException;
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

  float readxTaksiran() {
    Scanner scanner = new Scanner(System.in);
    float x;
    x = scanner.nextFloat();
    return x;
  }
 
  void convertToMatAug(matriks titik) {

    titik.NeffK = titik.NeffK+(titik.NeffB-1);

    float[] x = new float[titik.NeffB];
    float[] y = new float[titik.NeffB];

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
          titik.Mat[i][j] = y[i];
        } else {
          titik.Mat[i][j] = (float) Math.pow((double) x[i], (double) j);
        }
      }
    }
    // titik.displayMatriks();
    System.out.println();
  }

  void displayInterpolasi(matriks titik) {
    int i,j;

    System.out.println("System persamaan lanjar yang terbentuk: ");
    // display matriks augmented dari titik
    if (titik.NeffB == 1) {
      System.out.print("a0");
      System.out.print(" = ");
      System.out.println(titik.Mat[0][1]);
    } else {
      for (i = 0; i < titik.NeffB; i++) {
        for (j = 0; j < titik.NeffK; j++) {
          if (j == 0) {
            System.out.print("a0");
            System.out.print(" + ");
          } else if (j == titik.NeffK-2) {
            System.out.print(titik.Mat[i][j]);
            System.out.print("a");
            System.out.print(j);
            System.out.print(" = ");
          } else if (j == titik.NeffK-1) {
            System.out.print(titik.Mat[i][j]);
          } else {
            System.out.print(titik.Mat[i][j]);
            System.out.print("a");
            System.out.print(j);
            System.out.print(" + ");
          }
        }
        System.out.println();
      }
    }

    System.out.println();
  }

  void displayInterpolasiToFile(matriks titik, int count, Float[] bilX, Float[] y, String namaFile) {
    String line;

    try {
      String newFileDir = "./hasil/" + namaFile;
      FileWriter writeInterpolasi = new FileWriter(newFileDir);

      int i,j;

      String persamaanPolinom = "Persamaan Polinom: \n";
      if (titik.NeffB == 1) {
        persamaanPolinom += "a0";
        persamaanPolinom += " = ";
        persamaanPolinom += Float.toString(titik.Mat[0][1]);
      } else {
        for (i = 0; i < titik.NeffB; i++) {
          for (j = 0; j < titik.NeffK; j++) {
            if (j == 0) {
              persamaanPolinom += "a0";
              persamaanPolinom += " + ";
            } else if (j == titik.NeffK-2) {
              persamaanPolinom += Float.toString(titik.Mat[i][j]);
              persamaanPolinom += "a";
              persamaanPolinom += Integer.toString(j);
              persamaanPolinom += " = ";
            } else if (j == titik.NeffK-1) {
              persamaanPolinom += Float.toString(titik.Mat[i][j]);
            } else {
              persamaanPolinom += Float.toString(titik.Mat[i][j]);
              persamaanPolinom += "a";
              persamaanPolinom += Integer.toString(j);
              persamaanPolinom += " + ";
            }
          }
          persamaanPolinom += "\n";
        }
      }
      line = persamaanPolinom + "\nHasil interpolasi dari nilai: \n";
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

  float interpolasiPolinom(matriks titik, float x) {
    titik.eliminasiGauss();

    int i,j;
    float[] sol = new float[titik.NeffB];

    for (i = titik.NeffB-1; i >= 0; i--) {
      if (i == titik.NeffB-1) {
        sol[i] = titik.Mat[i][titik.NeffK-1];
      } else {
        float tempA = 0;
        for (j = 0; j < titik.NeffK-1; j++) {
          if (titik.Mat[i][j] != 0 || titik.Mat[i][j] != 1) {
            tempA += titik.Mat[i][j] * sol[j];
          }
        }
        sol[i] = titik.Mat[i][titik.NeffK-1] - tempA;
      }
    }
    
    float hasilInterpolasi = 0;
    for (i = 0; i < titik.NeffB; i++) {
      if (i == 0) {
        hasilInterpolasi += sol[i];
      } else {
        hasilInterpolasi += sol[i] * Math.pow((double) x, (double) i);
      }
    }

    return hasilInterpolasi;
  }

}