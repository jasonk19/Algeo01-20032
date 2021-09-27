import java.util.Arrays;

class solver{
    public static void gaussSolver(){
        int type = Main.inputData();
        matriks matriks = new matriks();
        
        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
        }
        int N = matriks.NeffB;
        int M = matriks.NeffK;
        String[][] sol = new String[M-1][2];
        for (int i = 0; i < M-1; i++) sol[i][0] = "x"+ (i+1);
        matriks.eliminasiGauss();
        for (int i = N-1; i >= 0; i--){
            float hasilF = matriks.Mat[i][M-1];
            String hasilS ="";
            int idx = 0;
            //mencari element bukan 0 pertama pada baris
            while (matriks.isZero(i, idx) && idx < M-2) idx++;
            for (int j = idx + 1; j < M-1; j++){
                try{
                    float x = matriks.Mat[i][j] * Float.parseFloat(sol[j][0]);
                    hasilF -= x;
                } catch (Exception e){
                    float fix = Main.fixFloatingPoint(matriks.Mat[i][j]);
                    if (fix > 0 && fix != 1)
                        hasilS += " -" +fix+"*x" + (j+1);
                    else if(fix > 0 && fix == 1)
                        hasilS += " -x" + (j+1);
                    else if(fix < 0 && fix != 1)
                        hasilS += "+ " +fix+"*x" + (j+1);
                    else if(fix < 0 && fix == 1)
                        hasilS += "+ x" + (j+1);
                    else 
                        hasilS = hasilS;
                }
                if (sol[j][1] != null && sol[j][1] != ""){
                    float fix = Main.fixFloatingPoint(matriks.Mat[i][j]);
                    if (fix > 0 && fix != 1)
                        hasilS += " -" +fix+"*("+sol[j][1]+ ")";
                    else if(fix > 0 && fix == 1)
                        hasilS += " -" + "("+sol[j][1]+ ")";
                    else if(fix < 0 && fix != 1)
                        hasilS += "+ " +fix+"*("+sol[j][1]+ ")";
                    else if(fix < 0 && fix == 1)
                        hasilS += " +" + "("+sol[j][1]+ ")";

                }
            }
            sol[idx][0] = Float.toString(hasilF);
            sol[idx][1] = hasilS;
        }
        for (int i = 0; i < M-1; i++) System.out.println("x"+(i+1) + " = " + ((Main.isNumber(sol[i][0]) && Float.parseFloat(sol[i][0]) == 0)  ? "" : sol[i][0]) + (sol[i][1] == null ? "" : sol[i][1]));
    }
    public static void gaussJordanSolver(){
        int type = Main.inputData();
        matriks matriks = new matriks();
        
        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
        }
        int N = matriks.NeffB;
        int M = matriks.NeffK;
        String[][] sol = new String[M-1][2];
        for (int i = 0; i < M-1; i++) sol[i][0] = "x"+ (i+1);
        matriks.eliminasiGaussJordan();
        for (int i = N-1; i >= 0; i--){
            float hasilF = matriks.Mat[i][M-1];
            String hasilS ="";
            int idx = 0;
            //mencari element bukan 0 pertama pada baris
            while (matriks.isZero(i, idx) && idx < M-2) idx++;
            for (int j = idx + 1; j < M-1; j++){
                try{
                    float x = matriks.Mat[i][j] * Float.parseFloat(sol[j][0]);
                    hasilF -= x;
                } catch (Exception e){
                    float fix = Main.fixFloatingPoint(matriks.Mat[i][j]);
                    if (fix > 0 && fix != 1)
                        hasilS += " -" +fix+"*x" + (j+1);
                    else if(fix > 0 && fix == 1)
                        hasilS += " -x" + (j+1);
                    else if(fix < 0 && fix != 1)
                        hasilS += "+ " +fix+"*x" + (j+1);
                    else if(fix < 0 && fix == 1)
                        hasilS += "+ x" + (j+1);
                    else 
                        hasilS = hasilS;
                }
                if (sol[j][1] != null && sol[j][1] != ""){
                    float fix = Main.fixFloatingPoint(matriks.Mat[i][j]);
                    if (fix > 0 && fix != 1)
                        hasilS += " -" +fix+"*("+sol[j][1]+ ")";
                    else if(fix > 0 && fix == 1)
                        hasilS += " -" + "("+sol[j][1]+ ")";
                    else if(fix < 0 && fix != 1)
                        hasilS += "+ " +fix+"*("+sol[j][1]+ ")";
                    else if(fix < 0 && fix == 1)
                        hasilS += " +" + "("+sol[j][1]+ ")";

                }
            }
            sol[idx][0] = Float.toString(hasilF);
            sol[idx][1] = hasilS;
        }
        for (int i = 0; i < M-1; i++) System.out.println("x"+(i+1) + " = " + ((Main.isNumber(sol[i][0]) && Float.parseFloat(sol[i][0]) == 0)  ? "" : sol[i][0]) + (sol[i][1] == null ? "" : sol[i][1]));
    }
    public static void inversSolver(){
        int type = Main.inputData();
        matriks matriks = new matriks();
        
        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
        }
        //split daerah variable dgn daerah hasil
        matriks.NeffK -=1;
        matriks matriksHasil = new matriks();
        for (int j = 0; j < matriks.NeffB; j++) matriksHasil.Mat[j][0] = matriks.Mat[j][matriks.NeffK];
        matriksHasil.NeffB = matriks.NeffB;
        matriksHasil.NeffK = 1;
        if (matriks.NeffB != matriks.NeffK){
            System.out.println("Matriks tidak berbentuk persegi. Tidak dapat diselesaikan dengan metode invers!");
        }
        else if (matriks.determinan() == 0){
            System.out.println("Matriks tidak mempunyai invers. Tidak dapat diselesaikan dengan metode invers!");
        } else
        {
            matriks sol;
            matriks.cofactorMinorInvers();
            sol = matriks.multiplyByMatrix(matriksHasil);
            for (int i = 0; i < matriksHasil.NeffB; i++)
                System.out.println("x" +(i+1) + " = " + sol.Mat[i][0]);
        }
        
    }
    public static void determSolver(){
        int type = Main.inputData();
        matriks matriks = new matriks();
        
        if (type == 1) {
            int rowNCol = Main.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Main.inputDariFile());
        }
        //split daerah variable dgn daerah hasil
        matriks.NeffK -=1;
        matriks matriksHasil = new matriks();
        for (int j = 0; j < matriks.NeffB; j++) matriksHasil.Mat[j][0] = matriks.Mat[j][matriks.NeffK];
        matriksHasil.NeffB = matriks.NeffB;
        matriksHasil.NeffK = 1;
        if (matriks.NeffB != matriks.NeffK){
            System.out.println("Matriks tidak berbentuk persegi. Tidak dapat diselesaikan dengan metode crammer!");
        }
        else if (matriks.determinan() == 0){
            System.out.println("Matriks mempunyai determinan 0. Tidak dapat diselesaikan dengan metode crammer!");
        } 
        else{
            float[] dets = new float[matriks.NeffB + 1];
            for (int i = 0; i < matriks.NeffB + 1; i++){
                if (i == 0) dets[i] = matriks.determinan();
                else {
                    matriks temp = matriks.swapKolomFunc(i-1, matriks.NeffB);
                    dets[i] = temp.determinan();
                }
            }
            float[] sol = new float[matriks.NeffB];
            for (int i = 0; i < matriks.NeffB; i++) sol[i] = dets[i+1] / dets[0];
            for (int i = 0; i < matriks.NeffB; i++)
                System.out.println("x" +(i+1) + " = " + sol[i]);

        }
    }

    // Masih testing interpolasi
    public static void interpolasiSolver() {
        // Inisiasi interpolasi
        Interpolation interpolasi = new Interpolation();
        
        // Input titik interpolasi dan ubah ke Matriks Augmented
        matriks titik = interpolasi.readTitikIntepolation();
        interpolasi.convertToMatAug(titik);
        interpolasi.displayInterpolasi(titik);
        // Input x taksiran
        float x;
        x = interpolasi.readxTaksiran();
        while (x != -999) {
            interpolasi.interpolasiPolinom(titik, x);    
            System.out.println();
            x = interpolasi.readxTaksiran();
        }
    }
}