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

  float readxTaksiran() {
    float x;
    System.out.println("Masukkan nilai x yang akan ditaksir: ");
    x = Main.scanner.nextFloat();
    return x;
  }

  // void displayTitik(matriks titik) {
  //   titik.displayMatriks();
  // }
 
  matriks convertToMatAug() {
    // convert dari titik ke matriks augmented
  }

  void displayInterpolasi(matriks matAug, float x) {
    int i,j;
    // display matriks augmented dari titik
    for (i = 0; i < matAug.NeffB; i++) {
      for (j = 0; j < matAug.NeffK; j++) {
        if (j == 0) {
          System.out.print("a0");
          System.out.print(" + ");
        }
        if (j == 1) {
          System.out.print(matAug.Mat[i][j]);
          System.out.print("a1");
          System.out.print(" + ");
        }
        if(j == 2) {
          System.out.print(matAug.Mat[i][j]);
          System.out.print("a2");
          System.out.print(" = ");
        }
        if (j == 3) {
          System.out.print(matAug.Mat[i][j]);
        }
      }
      System.out.println();
    }

    System.out.print("Hasil taksiran dari nilai x = ");
    System.out.print(x);
    System.out.println(" adalah: ");
  }


}