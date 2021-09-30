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
          titik.Mat[i][j] = (float) Math.pow((double) x[i], (double) j);
        }
      }
    }
    // titik.displayMatriks();
    System.out.println();
  }

  void displayInterpolasi(matriks titik, int count, double[] bilX, double[] y) {
    int i,j;

    double[] sol = getHasilGauss(titik);

    System.out.println("Hasil interpolasi dari nilai: ");
    for (i = 0; i < count; i++) {
        System.out.print("x = ");
        System.out.println(bilX[i]);
    }
    System.out.println("adalah: ");
    for (i = 0; i < count; i++) {
      System.out.print("f(");
      System.out.print(bilX[i]);
      System.out.print(") = ");
      for (j = 0; j < titik.NeffB; j++) {
        if (j == 0) {
          System.out.print(sol[j]);
          System.out.print(" + ");
        } else if (j == titik.NeffB - 1) {
          if (sol[j] >= 0) {
            System.out.print(sol[j]);
            System.out.print("(");
            System.out.print(bilX[i]);
            System.out.print(")");
            if (j == 1) {
              System.out.print("");
            } else {
              System.out.print("^");
              System.out.print(j);
            }
            System.out.print(" = ");
            System.out.print(y[i]);
          } else {
            System.out.print("(");
            System.out.print(sol[j]);
            System.out.print(")");
            System.out.print("(");
            System.out.print(bilX[i]);
            System.out.print(")");
            if (j == 1) {
              System.out.print("");
            } else {
              System.out.print("^");
              System.out.print(j);
            }
            System.out.print(" = ");
            System.out.print(y[i]);
          }
        } else {
          if (sol[j] >= 0) {
            System.out.print(sol[j]);
            System.out.print("(");
            System.out.print(bilX[i]);
            System.out.print(")");
            if (j == 1) {
              System.out.print("");
            } else {
              System.out.print("^");
              System.out.print(j);
            }
            System.out.print(" + ");
          } else {
            System.out.print("(");
            System.out.print(sol[j]);
            System.out.print(")");
            System.out.print("(");
            System.out.print(bilX[i]);
            System.out.print(")");
            if (j == 1) {
              System.out.print("");
            } else {
              System.out.print("^");
              System.out.print(j);
            }
            System.out.print(" + ");
          }
        }
      }
      System.out.println();
    }

    System.out.println();
  }

  void displayPolinomInterpolasi(matriks titik) {
    titik.eliminasiGauss();
    double[] sol = getHasilGauss(titik);

    int i;
    System.out.print("f(x) = ");
    for (i = 0; i < titik.NeffB; i++) {
      if (i == 0) {
        System.out.print(sol[i]);
        System.out.print(" + ");
      } else if (i == titik.NeffB-1) {
        if (sol[i] >= 0) {
          System.out.print(sol[i]);
          System.out.print("x");
          if (i == 1) {
            System.out.print("");
          } else {
            System.out.print("^");
            System.out.print(i);
          }
        } else {
          System.out.print("(");
          System.out.print(sol[i]);
          System.out.print(")");
          System.out.print("x");
          if (i == 1) {
            System.out.print("");
          } else {
            System.out.print("^");
            System.out.print(i);
          }
        }
      } else {
        if (sol[i] >= 0) {
          System.out.print(sol[i]);
          System.out.print("x");
          if (i == 1) {
            System.out.print("");
          } else {
            System.out.print("^");
            System.out.print(i);
          }
        } else {
          System.out.print("(");
          System.out.print(sol[i]);
          System.out.print(")");
          System.out.print("x");
          if (i == 1) {
            System.out.print("");
          } else {
            System.out.print("^");
            System.out.print(i);
          }
        }
        
        System.out.print(" + ");
      }
    }
    System.out.println();
  }

  void displayInterpolasiToFile(matriks titik, int count, double[] bilX, double[] y, String namaFile) {
    String line;

    try {
      String newFileDir = "./hasil/" + namaFile;
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
      titik.eliminasiGauss();
      double[] sol = getHasilGauss(titik);
      persamaanPolinom += "f(x) = ";
      for (i = 0; i < titik.NeffB; i++) {
        if (i == 0) {
          persamaanPolinom += Double.toString(sol[i]);
          persamaanPolinom += " + ";
        } else if (i == titik.NeffB-1) {
          if (sol[i] >= 0) {
            persamaanPolinom += Double.toString(sol[i]);
            persamaanPolinom += "x";
            if (i == 1) {
              persamaanPolinom += "";
            } else {
              persamaanPolinom += "^";
              persamaanPolinom += Integer.toString(i);
            }
          } else {
            persamaanPolinom += "(";
            persamaanPolinom += Double.toString(sol[i]);
            persamaanPolinom += ")";
            persamaanPolinom += "x";
            if (i == 1) {
              persamaanPolinom += "";
            } else {
              persamaanPolinom += "^";
              persamaanPolinom += Integer.toString(i);
            }
          }
        } else {
          if (sol[i] >= 0) {
            persamaanPolinom += Double.toString(sol[i]);
            persamaanPolinom += "x";
            if (i == 1) {
              persamaanPolinom += "";
            } else {
              persamaanPolinom += "^";
              persamaanPolinom += Integer.toString(i);
            }
          } else {
            persamaanPolinom += "(";
            persamaanPolinom += Double.toString(sol[i]);
            persamaanPolinom += ")";
            persamaanPolinom += "x";
            if (i == 1) {
              persamaanPolinom += "";
            } else {
              persamaanPolinom += "^";
              persamaanPolinom += Integer.toString(i);
            }
          }
          
          persamaanPolinom += " + ";
        }
      }
      

      line = persamaanLanjar + persamaanPolinom + "\n\nHasil interpolasi dari nilai: \n";
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

  double interpolasiPolinom(matriks titik, double x) {
    titik.eliminasiGauss();
    double[] solusi = getHasilGauss(titik);
    
    int i;
    double hasilInterpolasi = 0;
    for (i = 0; i < titik.NeffB; i++) {
      if (i == 0) {
        hasilInterpolasi += solusi[i];
      } else {
        hasilInterpolasi += solusi[i] * Math.pow(x, i);
      }
    }

    return hasilInterpolasi;
  }

}