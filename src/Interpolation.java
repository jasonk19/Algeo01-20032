import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Interpolation {

  matriks readTitikIntepolation() {
    matriks titik = new matriks();
    int N;

    System.out.println("Masukkan jumlah titik: ");
    N = Main.scanner.nextInt();

    System.out.println("Masukkan kombinasi titik: ");
    titik.readMatriks(N,2);

    return titik;

  }

  // Masih belum coba, baru implementation sama kayak yang di matriks.java
  void readFileTitikInterpolation(String filename) {
    matriks titik = new matriks();

    try {
      String line;
      BufferedReader reader = new BufferedReader(new FileReader("../test/" + filename));

      int i,j;
      i = 0;
      while ((line = reader.readLine()) != null) {
        j = 0;
        for (String value : line.split("")) {
          titik.Mat[i][j] = Float.parseFloat(value);
          j++;
        }
        i++;
        titik.NeffK = j;
      }
      titik.NeffB = i;
      reader.close();
    } catch (IOException e) {
      System.out.println("An error occured," + e.getMessage());
    }
  }

  float readxTaksiran() {
    float x;
    System.out.println("Masukkan nilai x yang akan ditaksir: ");
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

  void displayInterpolasi(matriks titik, float x) {
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

    System.out.print("Hasil taksiran dari nilai x = ");
    System.out.print(x);
    System.out.println(" adalah: ");
  }


}