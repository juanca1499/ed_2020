package interfaces;

import edlineal.Arreglo;
import edlineal.Matriz;
import edlineal.Matriz3D;
import entradasalida.SalidaEstandar;

public class PruebaMatriz2D
{
    public static void main(String[] args)
    {

        SalidaEstandar.consola("\n\nPRUEBAS MATRIZ 2D:\n");

        Matriz matriz2D = new Matriz(2,3);
        matriz2D.setElemento(0,0,5);
        matriz2D.setElemento(0,1,9);
        matriz2D.setElemento(0,2,10);
        matriz2D.setElemento(1,0,8);
        matriz2D.setElemento(1,1,7);
        matriz2D.setElemento(1,2,3);
        //
        Matriz matriz2D2 = new Matriz(2,3);
        matriz2D2.setElemento(0,0,5);
        matriz2D2.setElemento(0,1,9);
        matriz2D2.setElemento(0,2,10);
        matriz2D2.setElemento(1,0,8);
        matriz2D2.setElemento(1,1,7);
        matriz2D2.setElemento(1,2,3);

        matriz2D.imprimir();
        matriz2D.aplicarTraspuesta();
        SalidaEstandar.consola("\nMatriz traspuesta:\n");
        matriz2D.imprimir();

        SalidaEstandar.consola("\n\nMatriz 1:\n");
        matriz2D.imprimir();
        SalidaEstandar.consola("\nMatriz 2:\n");
        matriz2D2.aplicarTraspuesta();
        matriz2D2.imprimir();
        SalidaEstandar.consola("Son iguales: " + matriz2D.esIgual(matriz2D2));

        SalidaEstandar.consola("\nVector Columna:\n");
        matriz2D.vectorCol(6, 10);
        matriz2D.imprimir();

        SalidaEstandar.consola("\nVector Renglon:\n");
        matriz2D.vectorReng(4, 5);
        matriz2D.imprimir();

        //
        //
        Matriz matrizVieja = new Matriz(2,2);
        Matriz matrizNueva = new Matriz(3,3);

        matrizVieja.setElemento(0,0,100);
        matrizVieja.setElemento(0,1,200);
        matrizVieja.setElemento(1,0,300);
        matrizVieja.setElemento(1,1,400);
        //
        matrizNueva.setElemento(0,0,40);
        matrizNueva.setElemento(0,1,60);
        matrizNueva.setElemento(0,2,80);
        matrizNueva.setElemento(1,0,90);
        matrizNueva.setElemento(1,1,40);
        matrizNueva.setElemento(1,2,60);
        matrizNueva.setElemento(2,0,80);
        matrizNueva.setElemento(2,1,90);
        matrizNueva.setElemento(2,2,90);
        SalidaEstandar.consola("\nRedefiniendo la matriz:\n");
        SalidaEstandar.consola("\nMatriz antigua:\n");
        matrizVieja.imprimir();
        SalidaEstandar.consola("\nMatriz nueva:\n");
        matrizVieja.definirMatriz(matrizNueva);
        matrizVieja.imprimir();


        Arreglo arreglo = new Arreglo(4);
        arreglo.agregar(30);
        arreglo.agregar(50);
        arreglo.agregar(100);
        arreglo.agregar(2000);
        SalidaEstandar.consola("\n\nAgregando un renglónn: \n");
        matriz2D.imprimir();
        SalidaEstandar.consola("\nAgregado: \n");
        matriz2D.agregarRenglon(arreglo);
        matriz2D.imprimir();

        Arreglo arreglo2 = new Arreglo(2);
        arreglo2.agregar(333);
        arreglo2.agregar(777);
        SalidaEstandar.consola("\n\nAgregando una columna: \n");
        matriz2D.imprimir();
        SalidaEstandar.consola("\nAgregada: \n");
        matriz2D.agregarColumna(arreglo2);
        matriz2D.imprimir();


        Matriz matrizColumna = new Matriz(2, 2);
        matrizColumna.setElemento(0,0,40);
        matrizColumna.setElemento(0,1,60);
        matrizColumna.setElemento(1,0,80);
        matrizColumna.setElemento(1,1,90);
        SalidaEstandar.consola("\n\nAgregando una matriz columna: \n");
        matriz2D.imprimir();
        SalidaEstandar.consola("\nAgregada: \n");
        matriz2D.agregarMatrizColumna(matrizColumna);
        matriz2D.imprimir();

        Matriz matrizRenglon = new Matriz(2, 7);
        matrizRenglon.setElemento(0,0,40);
        matrizRenglon.setElemento(0,1,60);
        matrizRenglon.setElemento(1,0,80);
        matrizRenglon.setElemento(1,1,90);
        SalidaEstandar.consola("\n\nAgregando una matriz renglon: \n");
        matriz2D.imprimir();
        SalidaEstandar.consola("\nAgregada: \n");
        matriz2D.agregarMatrizRenglon(matrizRenglon);
        matriz2D.imprimir();


        SalidaEstandar.consola("\n\nConvirtiendo a matriz 3D: \n");
        Matriz matrix1 = new Matriz(2,2);
        Matriz matrix2 = new Matriz(2,2);
        Matriz matrix3 = new Matriz(2,2);
        Arreglo arregloMatrices = new Arreglo(2);

        matrix1.setElemento(0,0,40);
        matrix1.setElemento(0,1,100);
        matrix1.setElemento(1,0,80);
        matrix1.setElemento(1,1,90);
        //
        matrix2.setElemento(0,0,40);
        matrix2.setElemento(0,1,200);
        matrix2.setElemento(1,0,80);
        matrix2.setElemento(1,1,90);
        //
        matrix3.setElemento(0,0,40);
        matrix3.setElemento(0,1,300);
        matrix3.setElemento(1,0,80);
        matrix3.setElemento(1,1,90);
        //
        arregloMatrices.agregar(matrix2);
        arregloMatrices.agregar(matrix3);
        //
        matrix1.imprimir();
        SalidaEstandar.consola("\n\nResultado conversion a 3D: \n");
        Matriz3D matriz3D = matrix1.aMatriz3D(arregloMatrices);
        matriz3D.imprimir();
    }
}
