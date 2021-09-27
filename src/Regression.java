public class Regression {

    public static void LinearRegression() {
        int type = Main.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int[] rowNCol = Main.inputDataRegression();
            matriks.readMatriks(rowNCol[0], rowNCol[1] + 1);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
        }

        System.out.println();
        System.out.println("Nilai yang ingin ditaksir");
        float[] peubah = new float[matriks.NeffK - 1];
        for (int i=0; i < matriks.NeffK - 1; i++) {
            System.out.print("Masukkan nilai peubah x" + (i + 1) + ": ");
            peubah[i] = Main.scanner.nextFloat();
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
    }
}
