public class Determinant {

    public static void gaussianDet() {
        int type = Main.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
        }
        matriks.eliminasiGauss();

        for (int i = 0; i < matriks.NeffB; i++) {
            matriks.det *= matriks.Mat[i][i];
        }

        System.out.println();
        System.out.println("Determinant = " + Main.fixFloatingPoint(matriks.det));
    }

    public static void cofactorMinorDet() {
        int type = Main.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
        }
        float det = matriks.determinantRecc(matriks, matriks.NeffB);

        System.out.println();
        System.out.println("Determinant = " + Main.fixFloatingPoint(det));
    }
}