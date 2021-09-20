public class Inversion {

    public static void gaussianInversion() {
        int type = Main.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
        }
        matriks.gaussJordanInversOf(matriks);
        matriks.displayMatriks();
    }

    public static void cofactorMinorInversion() {
        int type = Main.inputData();
        matriks matriks = new matriks();

        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
        }
        matriks.cofactorMinorInvers();
        matriks.displayMatriks();
    }
}
