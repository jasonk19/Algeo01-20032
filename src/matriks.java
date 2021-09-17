import java.util.Scanner; 

class matriks {
    final int maxB = 100;
    final int maxK = 100;
    int NeffB, NeffK;
    float[][] Mat = new float[maxB][maxK];
    matriks(){
        int i,j;
        for (i = 0; i < maxB; i++)
            for (j = 0; j < maxK; j++)
                this.Mat[i][j] = 0;
    }
    void readMatriks(int N, int M){
        Scanner inp = new Scanner(System.in);
        int i,j;
        for (i = 0; i < N; i++)
            for (j = 0; j < M; j++)
                this.Mat[i][j] = inp.nextFloat();
        this.NeffB = N;
        this.NeffK = M;
    }
    void displayMatriks(){
        int N = this.NeffB;
        int M = this.NeffK;
        int i,j;
        for (i = 0; i < N; i++){
            System.out.print("|"+this.Mat[i][0]);
            for (j = 1; j < M; j++) System.out.print(" "+ this.Mat[i][j]);
            System.out.print("|\n"); 
        }
    }
    void swapBaris(int B1, int B2){
        int M = this.NeffK;
        float temp;
        for (int i = 0; i < M ; i++){
            temp = this.Mat[B1][i];
            this.Mat[B1][i] = this.Mat[B2][i];
            this.Mat[B2][i] = temp;
        }
    }
    void tambahBaris(int B1, int B2){
        int M = this.NeffK;
        for (int i = 0; i < M; i++) this.Mat[B1][i] += this.Mat[B2][i];
    }
    void tambahBarisNKali(int B1, int B2, float N){
        int M = this.NeffK;
        for (int i = 0; i < M; i++) this.Mat[B1][i] += this.Mat[B2][i] * N;
    }
    void kurangBaris(int B1, int B2){
        int M = this.NeffK;
        for (int i = 0; i < M; i++) this.Mat[B1][i] -= this.Mat[B2][i];
    }
    void kurangBarisNKali(int B1, int B2,float N){
        int M = this.NeffK;
        for (int i = 0; i < M; i++) this.Mat[B1][i] -= this.Mat[B2][i] * N;
    }
    void kaliBaris(int B, float K){
        int M = this.NeffK;
        for (int i = 0; i < M ; i++) this.Mat[B][i] *= K;
    }
    float[] BarisdiKali(int B, float K){
        int M = this.NeffK;
        float[] MKali = new float[M];
        for (int i = 0; i < M; i++) MKali[i] = this.Mat[B][i] * K;
        return MKali;
    }
    boolean isZero(int B, int K){
        return (this.Mat[B][K] == 0);
    }
    void sortObe(){
        int N = this.NeffB;
        int M = this.NeffK;
        int[] index0baris = new int[N];
        for (int i = 0; i < N; i++){
            int j = 0;
            while (this.Mat[i][j] == 0 && j < M) j++;
            index0baris[i] = j;
        };
        for (int i = 0; i < N-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < N; j++)
                if (index0baris[j] < index0baris[min_idx])
                    min_idx = j;
 
            // Swap the found minimum element with the first
            // element
            int temp = index0baris[min_idx];
            index0baris[min_idx] = index0baris[i];
            index0baris[i] = temp;
            swapBaris(i, min_idx);
        }
        // for (int i = 0; i < N ; i++) System.out.println(index0baris[i] +"");
    }
    void eliminasiGauss(){
        int N = this.NeffB;
        int M = this.NeffK;
        for (int i = 0; i < N; i++){
            this.sortObe();
            int j=0;
            //mencari element bukan 0 pertama pada baris
            while (this.Mat[i][j] == 0 && j < M) j++;
            //membuat element bukan 0 pertama baris menjadi 1
            if (!(isZero(i, j))) this.kaliBaris(i, 1/this.Mat[i][j]);
            if (i != N - 1){
                //membuat baris lain 0 di kolom j,
                for (int temp = i + 1; temp < N; temp++)
                if (this.Mat[temp][j] != 0) this.kurangBarisNKali(temp, i, this.Mat[temp][j]);
            }
        }
    }
    void eliminasiGaussJordan(){
        this.eliminasiGauss();
        int N = this.NeffB;
        int M = this.NeffK;
        for (int i = 0; i < N-1; i++){
            int idx = 0;
            //mencari element bukan 0 pertama pada baris
            while (isZero(i, idx) && idx < M) idx++;
            int temp = i + 1;
            //membuat element setelah element bukan 0 pertama pada baris bernilai 0;
            for (int j = idx +1; j <M - 1; j++){
                while (!(isZero(i, j)) && temp < N){
                    this.kurangBarisNKali(i, temp, this.Mat[i][j]);
                    temp += 1;
                };

            };
        };
    }
    // void inversOf(matriks Min){
    //     int N = Min.NeffB;
    //     int M = Min.NeffK;
    //     this.NeffB = N;
    //     this.NeffK = 2 * M;
    //     for (int i= 0; i < N; i++){
    //         for (int j = 0; j < M; j ++) this.Mat[i][j] = Min.Mat[i][j];
    //         for (int j = M; j < 2 * M; j++) {
    //             if (i == j - M) this.Mat[i][j] = 1;
    //             else this.Mat[i][j] = 0;
    //         };
    //     };
    //     this.eliminasiGaussJordan();
    //     for (int i = 0; i < N-1; i ++){
    //         Min.kurangBarisNKali(i, N-1, this.Mat[i][M-1]);
    //         System.out.println(this.Mat[N-1][M-1]);
    //     }
    //     // Min.displayMatriks();
    //     for (int i = 0; i < N; i++){
    //         for(int j = 0; j < M; j++) this.Mat[i][j] = Min.Mat[i][j+M];
    //         for (int j = M; j < 2 * M; j++) this.Mat[i][j] = 0;
    //     }

        
    // }
}