import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class solver{
    static char[] parametric = {'p','q','r','s','t','u','v','w','a','b','c'}; 
    //maksimal variable 10
    private static void saveSPLToFile(matriks matriksSpl, String hasil, String namaFile) {
        String line = "";

        try {
            String newFileDir = "./hasil/" + namaFile;
            FileWriter writeDeterm = new FileWriter(newFileDir);

            String dataMatrix = "SPL dalam bentuk matriks: \n";
            for (int i=0; i < matriksSpl.NeffB; i++) {
                for (int j=0; j < matriksSpl.NeffK; j ++) {
                    dataMatrix += matriksSpl.Mat[i][j] + " | ";
                }
                dataMatrix += "\n";
            }

            line += dataMatrix + "\nHasil sistem persamaan linear adalah\n" + hasil;

            writeDeterm.write(line);
            writeDeterm.close();
            System.out.println("Berhasil menyimpan hasil spl pada folder hasil, file \"" + namaFile + "\".");
        } catch(IOException e) {
            System.err.println("Error.");
            e.printStackTrace();
        }
    }

    public static void gaussSolver(){
        Scanner scanner = new Scanner(System.in);
        int type = Utils.inputData();
        matriks matriks = new matriks();
        
        if (type == 1) {
            int rowNCol = Utils.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Utils.inputDariFile());
        }
        matriks copyMat = new matriks();
        copyMat.copyMatriksToThis(matriks);

        int N = matriks.NeffB;
        int M = matriks.NeffK;
        String[][] sol = new String[M-1][2];
        for (int i = 0; i < M-1; i++) sol[i][0] = String.valueOf(parametric[i]);
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
                    float fix = Utils.fixFloatingPoint(matriks.Mat[i][j]);
                    if (fix > 0 && fix != 1)
                        hasilS += " -" +fix+ "*" + sol[j][0];
                    else if(fix == 1)
                        hasilS += " -" + sol[j][0];
                    else if(fix < 0 && fix != -1)
                        hasilS += " +" +fix+"*" + sol[j][0];
                    else if(fix == -1)
                        hasilS += " +" + sol[j][0];
                }
                if (sol[j][1] != null && !sol[j][1].equals("")){
                    float fix = Utils.fixFloatingPoint(matriks.Mat[i][j]);
                    if (fix > 0 && fix != 1)
                        hasilS += " -" +fix+"*("+sol[j][1]+ ")";
                    else if(fix == 1)
                        hasilS += " -" + "("+sol[j][1]+ ")";
                    else if(fix < 0 && fix != -1)
                        hasilS += " +" +fix+"*("+sol[j][1]+ ")";
                    else if(fix == -1)
                        hasilS += " +" + "("+sol[j][1]+ ")";

                }
            }
            sol[idx][0] = Float.toString(hasilF);
            sol[idx][1] = hasilS;
        }

        String line = "";
        for (int i = 0; i < M-1; i++) {
            String eq = "x"+(i+1) + " = " + ((Main.isNumber(sol[i][0]) && Float.parseFloat(sol[i][0]) == 0)  ? "" : sol[i][0]) + (sol[i][1] == null ? "" : sol[i][1]);
            line += eq + "\n";
            System.out.println(eq);
        }

        System.out.println();
        System.out.print("Simpan Hasil SPL?(y/n) ");
        char simpan = scanner.next().charAt(0);
        String namaFile;

        if (simpan == 'y') {
            System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
            namaFile = scanner.next();
            saveSPLToFile(copyMat, line, namaFile);
        }
    }
    public static void gaussJordanSolver(){
        Scanner scanner = new Scanner(System.in);
        int type = Utils.inputData();
        matriks matriks = new matriks();
        
        if (type == 1) {
            int rowNCol = Utils.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Utils.inputDariFile());
        }
        matriks copyMat = new matriks();
        copyMat.copyMatriksToThis(matriks);

        int N = matriks.NeffB;
        int M = matriks.NeffK;
        String[][] sol = new String[M-1][2];
        for (int i = 0; i < M-1; i++) sol[i][0] = String.valueOf(parametric[i]);
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
                    float fix = Utils.fixFloatingPoint(matriks.Mat[i][j]);
                    if (fix > 0 && fix != 1)
                        hasilS += " -" +fix+ "*" + sol[j][0];
                    else if(fix == 1)
                        hasilS += " -" + sol[j][0];
                    else if(fix < 0 && fix != -1)
                        hasilS += " +" +fix+"*" + sol[j][0];
                    else if(fix == -1)
                        hasilS += " +" + sol[j][0];
                }
                if (sol[j][1] != null && !sol[j][1].equals("")){
                    float fix = Utils.fixFloatingPoint(matriks.Mat[i][j]);
                    if (fix > 0 && fix != 1)
                        hasilS += " -" +fix+"*("+sol[j][1]+ ")";
                    else if(fix == 1)
                        hasilS += " -" + "("+sol[j][1]+ ")";
                    else if(fix < 0 && fix != -1)
                        hasilS += " +" +fix+"*("+sol[j][1]+ ")";
                    else if(fix == -1)
                        hasilS += " +" + "("+sol[j][1]+ ")";

                }
            }
            sol[idx][0] = Float.toString(hasilF);
            sol[idx][1] = hasilS;
        }

        String line = "";
        for (int i = 0; i < M-1; i++) {
            String eq = "x"+(i+1) + " = " + ((Main.isNumber(sol[i][0]) && Float.parseFloat(sol[i][0]) == 0)  ? "" : sol[i][0]) + (sol[i][1] == null ? "" : sol[i][1]);
            line +=  eq + "\n";
            System.out.println(eq);
        }

        System.out.println();
        System.out.print("Simpan Hasil SPL?(y/n) ");
        char simpan = scanner.next().charAt(0);
        String namaFile;

        if (simpan == 'y') {
            System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
            namaFile = scanner.next();
            saveSPLToFile(copyMat, line, namaFile);
        }
    }
    public static void inversSolver(){
        Scanner scanner = new Scanner(System.in);
        int type = Utils.inputData();
        matriks matriks = new matriks();
        
        if (type == 1) {
            int rowNCol = Utils.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Utils.inputDariFile());
        }
        matriks copyMat = new matriks();
        copyMat.copyMatriksToThis(matriks);

        //split daerah variable dgn daerah hasil
        matriks.NeffK -=1;
        matriks matriksHasil = new matriks();
        for (int j = 0; j < matriks.NeffB; j++) matriksHasil.Mat[j][0] = matriks.Mat[j][matriks.NeffK];
        matriksHasil.NeffB = matriks.NeffB;
        matriksHasil.NeffK = 1;

        String line = "";
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
            for (int i = 0; i < matriksHasil.NeffB; i++) {
                String eq = "x" +(i+1) + " = " + sol.Mat[i][0];
                line +=  eq + "\n";
                System.out.println(eq);
            }

            System.out.println();
            System.out.print("Simpan Hasil SPL?(y/n) ");
            char simpan = scanner.next().charAt(0);
            String namaFile;

            if (simpan == 'y') {
                System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
                namaFile = scanner.next();
                saveSPLToFile(copyMat, line, namaFile);
            }
        }
    }

    public static void determSolver(){
        Scanner scanner = new Scanner(System.in);
        int type = Utils.inputData();
        matriks matriks = new matriks();
        
        if (type == 1) {
            int rowNCol = Utils.inputBarisNKolom();
            matriks.readMatriks(rowNCol, rowNCol);
        } else if (type == 2) {
            matriks.readMatriks(Utils.inputDariFile());
        }
        matriks copyMat = new matriks();
        copyMat.copyMatriksToThis(matriks);
        //split daerah variable dgn daerah hasil
        matriks.NeffK -=1;
        matriks matriksHasil = new matriks();
        for (int j = 0; j < matriks.NeffB; j++) matriksHasil.Mat[j][0] = matriks.Mat[j][matriks.NeffK];
        matriksHasil.NeffB = matriks.NeffB;
        matriksHasil.NeffK = 1;

        String line = "";
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
            for (int i = 0; i < matriks.NeffB; i++) {
                String eq = "x" +(i+1) + " = " + sol[i];
                line +=  eq + "\n";
                System.out.println(eq);
            }

            System.out.println();
            System.out.print("Simpan Hasil SPL?(y/n) ");
            char simpan = scanner.next().charAt(0);
            String namaFile;

            if (simpan == 'y') {
                System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
                namaFile = scanner.next();
                saveSPLToFile(copyMat, line, namaFile);
            }
        }
    }

    // Masih testing interpolasi
    public static void interpolasiSolver() {
        Scanner scanner = new Scanner(System.in);
        int type = Utils.inputData();
        // Inisialisasi Interpolasi
        Interpolation interpolasi = new Interpolation();
        matriks titik = new matriks();
        if (type == 1) {
            titik = interpolasi.readTitikInterpolation();
        } else if (type == 2) {
            titik = interpolasi.readFileTitikInterpolation(Utils.inputDariFile());
        }

        // Input titik interpolasi dan ubah ke Matriks Augmented
        interpolasi.convertToMatAug(titik);
        interpolasi.displayInterpolasi(titik);
        // Input x taksiran
        int i, count;
        System.out.print("Masukkan banyaknya nilai x yang ingin ditaksir: ");
        count = scanner.nextInt();
        Float[] y = new Float[count];
        float x;
        Float[] bilX = new Float[count];

        System.out.println("Masukkan nilai x yang akan ditaksir: ");
        for (i = 0; i < count; i++) {
            x = interpolasi.readxTaksiran();
            bilX[i] = x;
            y[i] = interpolasi.interpolasiPolinom(titik, x);
        }
        System.out.println();
        System.out.println("Hasil interpolasi dari nilai: ");
        for (i = 0; i < count; i++) {
            System.out.print(i+1);
            System.out.print(". x = ");
            System.out.println(bilX[i]);
        }
        System.out.println("adalah: ");
        for (i = 0; i < count; i++) {
            System.out.print(i+1);
            System.out.print(". f(");
            System.out.print(bilX[i]);
            System.out.print(") = ");
            System.out.println(y[i]);
        }
        System.out.println();
        System.out.print("Simpan Hasil Interpolasi?(y/n) ");
        char simpan = scanner.next().charAt(0);
        String namaFile;

        if (simpan == 'y') {
            System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
            namaFile = scanner.next();
            interpolasi.displayInterpolasiToFile(titik, count, bilX, y, namaFile);
        }
    }
}