package lib.matriks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class matriks {
    final int maxB = 100;
    final int maxK = 100;
    public int NeffB, NeffK;
    public float det;
    public float[][] Mat = new float[maxB][maxK];

    public matriks() {
        this.det = 1;
        int i, j;
        for (i = 0; i < maxB; i++)
            for (j = 0; j < maxK; j++)
                this.Mat[i][j] = 0;
    }

    public void readMatriks(int N, int M) {
        Scanner scanner = new Scanner(System.in);
        int i, j;
        for (i = 0; i < N; i++)
            for (j = 0; j < M; j++)
                this.Mat[i][j] = scanner.nextFloat();
        this.NeffB = N;
        this.NeffK = M;
    }
    public int firstNonZero(int N){
        int M = this.NeffK;
        int idx = 0;
        while (this.Mat[N][idx] == 0 && idx < M) {
            idx++;
        };
        return idx;

    }
    public void readMatriks(String filename) {
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("../test/" + filename));
            int i, j;
            i = 0;
            while ((line = reader.readLine()) != null) {
                j = 0;
                for (String value : line.split(" ")) {
                    this.Mat[i][j] = Float.parseFloat(value);
                    j += 1;
                }
                i += 1;
                this.NeffK = j;
            }
            this.NeffB = i;
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occured, " + e.getMessage());
        }
    }

    public void displayMatriks() {
        int N = this.NeffB;
        int M = this.NeffK;
        int i, j;
        for (i = 0; i < N; i++) {
            System.out.print("|" + this.Mat[i][0]);
            for (j = 1; j < M; j++) System.out.print(" " + this.Mat[i][j]);
            System.out.print("|\n");
        }
    }
    public matriks swapKolomFunc(int K1, int K2) {
        matriks mOut = new matriks();
        int N = this.NeffB;
        int M = this.NeffK;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (j == K1){
                    mOut.Mat[i][j] = this.Mat[i][K2];
                } else {
                    mOut.Mat[i][j] = this.Mat[i][j];
                }
            }
        }
        mOut.NeffB = N;
        mOut.NeffK = M;
        return mOut;

    }
    public void swapBaris(int B1, int B2) {
        int M = this.NeffK;
        float temp;
        for (int i = 0; i < M; i++) {
            temp = this.Mat[B1][i];
            this.Mat[B1][i] = this.Mat[B2][i];
            this.Mat[B2][i] = temp;
        }
        this.det *= -1;
    }

    public void tambahBaris(int B1, int B2) {
        int M = this.NeffK;
        for (int i = 0; i < M; i++) this.Mat[B1][i] += this.Mat[B2][i];
    }

    public void tambahBarisNKali(int B1, int B2, float N) {
        int M = this.NeffK;
        for (int i = 0; i < M; i++) this.Mat[B1][i] += this.Mat[B2][i] * N;
    }

    public void kurangBaris(int B1, int B2) {
        int M = this.NeffK;
        for (int i = 0; i < M; i++) this.Mat[B1][i] -= this.Mat[B2][i];
    }

    public void kurangBarisNKali(int B1, int B2, float N) {
        int M = this.NeffK;
        for (int i = 0; i < M; i++) this.Mat[B1][i] -= this.Mat[B2][i] * N;
    }

    public void kaliBaris(int B, float K) {
        int M = this.NeffK;
        for (int i = 0; i < M; i++) this.Mat[B][i] *= K;
        this.det *= 1 / K;
    }

    public float[] BarisdiKali(int B, float K) {
        int M = this.NeffK;
        float[] MKali = new float[M];
        for (int i = 0; i < M; i++) MKali[i] = this.Mat[B][i] * K;
        return MKali;
    }

    public boolean isZero(int B, int K) {
        return (this.Mat[B][K] == 0);
    }

    public void sortObe() {
        int N = this.NeffB;
        int M = this.NeffK;
        int[] index0baris = new int[N];
        for (int i = 0; i < N; i++) {
            int j = 0;
            while (this.Mat[i][j] == 0 && j < M) j++;
            index0baris[i] = j;
        }
        ;
        for (int i = 0; i < N - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < N; j++)
                if (index0baris[j] < index0baris[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = index0baris[min_idx];
            index0baris[min_idx] = index0baris[i];
            index0baris[i] = temp;
            swapBaris(i, min_idx);
        }
    }

    public void copyMatriksToThis(matriks newMat) {
        for (int i=0; i < newMat.NeffB; i++) {
            for (int j=0; j < newMat.NeffK; j++) {
                this.Mat[i][j] = newMat.Mat[i][j];
            }
        }
        this.NeffB = newMat.NeffB;
        this.NeffK = newMat.NeffK;
    }

    public void transpose() {
        for (int i=0; i < this.NeffB; i++) {
            for (int j=i; j < this.NeffK; j++) {
                float temp = this.Mat[i][j];
                this.Mat[i][j] = this.Mat[j][i];
                this.Mat[j][i] = temp;
            }
        }
    }

    public void eliminasiGauss() {
        int N = this.NeffB;
        int M = this.NeffK;
        for (int i = 0; i < N; i++) {
            this.sortObe();
            int j = 0;
            //mencari element bukan 0 pertama pada baris
            while (this.Mat[i][j] == 0 && j < M) j++;
            //membuat element bukan 0 pertama baris menjadi 1
            if (!(isZero(i, j))) this.kaliBaris(i, 1 / this.Mat[i][j]);
            if (i != N - 1) {
                //membuat baris lain 0 di kolom j,
                for (int temp = i + 1; temp < N; temp++)
                    if (this.Mat[temp][j] != 0) this.kurangBarisNKali(temp, i, this.Mat[temp][j]);
            }
        }
    }

    public void eliminasiGaussJordan() {
        this.eliminasiGauss();
        int N = this.NeffB;
        int M = this.NeffK;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++){
                this.kurangBarisNKali(i, j, this.Mat[i][this.firstNonZero(j)]);
            }
        }
    }

    public void gaussJordanInversOf(matriks Min) {
        //menghitung inverse matrix dengan metode gaus jordan
        //prekondisi ukuran (N x N), NeffK < maxK dan mempunyai invers
        int N = Min.NeffB;
        int M = Min.NeffK;
        this.NeffB = N;
        this.NeffK = 2 * M;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) this.Mat[i][j] = Min.Mat[i][j];
            for (int j = M; j < 2 * M; j++) {
                if (i == j - M) this.Mat[i][j] = 1;
                else this.Mat[i][j] = 0;
            }

        }

        this.eliminasiGaussJordan();
        //menukar matriks identitas ke belakang
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                this.Mat[i][j] = this.Mat[i][j + M];
            }
            for (int j = M; j < 2 * M; j++) this.Mat[i][j] = 0;
        }
        this.NeffK = M;
    }

    public void getCofactor(matriks temp, int p, int q, int n) {
        int i = 0;
        int j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp.Mat[i][j++] = this.Mat[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    public float determinantRecc(matriks mat, int currentRow) {
        float det = 0;

        if (currentRow == 2) {
            return mat.Mat[0][0] * mat.Mat[1][1] - mat.Mat[1][0] * mat.Mat[0][1];
        }

        matriks temp = new matriks();
        temp.NeffB = currentRow - 1;
        temp.NeffK = currentRow - 1;

        for (int i = 0; i < currentRow; i++) {
            this.getCofactor(temp, 0, i, currentRow);
            det += Math.pow(-1, i) * mat.Mat[0][i] * determinantRecc(temp, currentRow - 1);
        }
        return det;
    }

    public void matrixCofactor() {
        matriks newMat = new matriks();
        newMat.NeffB = this.NeffB;
        newMat.NeffK = this.NeffK;

        matriks temp = new matriks();
        temp.NeffB = this.NeffB - 1;
        temp.NeffK = this.NeffK - 1;

        for (int i=0; i < this.NeffB; i++) {
            for (int j=0; j < this.NeffK; j++) {
                this.getCofactor(temp, i, j, this.NeffB);
                newMat.Mat[i][j] = (float) (Math.pow(-1, i + j) * temp.determinantRecc(temp, temp.NeffB));
            }
        }
        copyMatriksToThis(newMat);
    }

    public void adjoinCofactor() {
        this.matrixCofactor();
        this.transpose();
    }

    public void cofactorMinorInvers() {
        float det = this.determinantRecc(this, this.NeffB);
        this.adjoinCofactor();
        for (int i = 0; i < this.NeffK; i++) {
            this.Mat[i] = BarisdiKali(i, 1 / det);
        }
    }

    public matriks cofactor(int B, int K){
        int N = this.NeffB;
        matriks cof = new matriks();
        int idxBcof = 0;
        for (int i = 0; i < N; i++){
            int idxKcof = 0;
            for (int j = 0; j<N; j++){
                if (i != B && j != K) {
                    cof.Mat[idxBcof][idxKcof] = this.Mat[i][j];
                    idxKcof++;
                }
            }
            if (i != B) idxBcof++;
        }
        cof.NeffB = this.NeffB - 1;
        cof.NeffK = this.NeffK - 1;

        return cof;
    }
    public float determinan(){
        //menghitung determinan dengan ekspansi kofaktor
        //matriks harus berukuran (N x N)
        float det = 0;
        int N = this.NeffB;
        if (N == 2){                    //basis
            det += this.Mat[0][0] * this.Mat[1][1] - this.Mat[0][1] * this.Mat[1][0];
        }   
        else{                           //rekursif
            for (int i = 0; i < N; i++ ) det += Math.pow(-1, i)*this.Mat[0][i]*this.cofactor(0, i).determinan();
        }
        return det;
    }
    public matriks multiplyByMatrix(matriks Min){
        //prekondisi jumlah kolom object = jumlah baris M
        int N = this.NeffB;
        int M = this.NeffK;
        int O = Min.NeffK;
        matriks Mout = new matriks();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < O; j++)
                for(int k =0; k < M; k++)
                    Mout.Mat[i][j] += this.Mat[i][k] * Min.Mat[k][j];
        Mout.NeffB = N;
        Mout.NeffK = O;
        return Mout;
    }
}