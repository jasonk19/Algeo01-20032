public class Interpolation {

  matriks readTitikInterpolation() {
    matriks titik = new matriks();
    int N;

    System.out.println("Masukkan jumlah titik: ");
    N = Main.scanner.nextInt();

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
    float x;
    System.out.println("Masukkan nilai x yang akan ditaksir[input -999 untuk berhenti]: ");
    x = Main.scanner.nextFloat();
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

  void interpolasiPolinom(matriks titik, float x) {
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

    System.out.print("Hasil taksiran dari nilai x = ");
    System.out.print(x);
    System.out.print(" adalah: ");

    System.out.println(hasilInterpolasi);
  }

}