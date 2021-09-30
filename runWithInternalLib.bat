@echo off
cd src
javac -d ../bin Main.java
cd..
cd bin
java Main
PAUSE