# Tugas Besar Algeo 1

Anggota:

| Nama  | NIM |
| ------------- | ------------- |
| Fadil Fauzani  |  13520032  |
| Jova Andres Riski Sirait  | 13520072  |
| Jason Kanggara | 13520080 |

## Deskripsi Program
Program ini dibuat untuk memenuhi tugas mata kuliah IF2123 Aljabar Linear dan Geometri Semester 1 Tahun 2021/2022.
Program ini memiliki fungsionalitas khusus untuk mengolah matriks yaitu:
- Penyelesaian Sistem Persamaan Linear
- Menghitung Determinan Matriks
- Mencari Invers Matriks
- Interpolasi
- Regresi Linear Berganda

## Stuktur Program
- Folder bin berisi java bytecode (*.class)
- Folder src berisi source code dan library (folder lib) dari program java
- Folder test berisi data uji.
- Folder hasil berisi output data uji ke file.
- Folder doc berisi laporan.


## Instalasi Program
1. Clone repository
```sh
   git clone https://github.com/jasonk19/Algeo01-20032.git
   ```
2. Buka terminal pada folder utama (Algeo01-20032)
3. Untuk menjalankan program dengan internal library, jalankan command:
```sh
   cd src
   javac -d ../bin Main.java
   cd..
   cd bin
   java Main
   ```
4. Untuk menjalankan file utama (Main.java) dengan library dalam bentuk JAR, jalankan command:
 ```sh
   cd src
   java -cp matriks.jar Main.java
   ```
5. Untuk OS Windows, langkah nomor 3 bisa diganti dengan menjalankan batch file dengan klik atau run dari terminal:
```sh 
  .\runWithInternalLib.bat
   ```
6. Untuk OS Windows, langkah nomor 4 bisa diganti dengan menjalankan batch file dengan klik atau run dari terminal:
```sh 
  .\runWithExternalJAR.bat
   ```
