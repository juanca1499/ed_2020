package interfaces;

import edlineal.Matriz;
import edlineal.MatrizNum;
import entradasalida.SalidaEstandar;
import matematicas.TipoLogaritmo;

public class PruebaMatrizNum
{
    public static void main(String[] args)
    {
        MatrizNum matrizA;
        MatrizNum matrizB;


        SalidaEstandar.consola("Multiplicación de matriz por un escalar:\n");
        matrizA = new MatrizNum(3,3);
        matrizA.inicializar(1);
        SalidaEstandar.consola("\nMatriz original:\n");
        matrizA.imprimir();
        matrizA.multiplicarEscalar(10);
        SalidaEstandar.consola("Matriz multiplicada:\n");
        matrizA.imprimir();


        SalidaEstandar.consola("\n\nMultiplicación de matrices:\n");
        matrizA = new MatrizNum(1, 3);
        matrizB = new MatrizNum(3, 2);

        matrizA.setElemento(0,0,1.0);
        matrizA.setElemento(0,1,2.0);
        matrizA.setElemento(0,2,-3.0);
        matrizA.setElemento(1,0,4.0);
        matrizA.setElemento(1,1,0.0);
        matrizA.setElemento(1,2,-2.0);
        //
        matrizB.setElemento(0,0, 3.0);
        matrizB.setElemento(0,1,1.0);
        matrizB.setElemento(1,0,2.0);
        matrizB.setElemento(1,1, 4.0);
        matrizB.setElemento(2,0, -1.0);
        matrizB.setElemento(2,1,5.0);

        SalidaEstandar.consola("\nMatriz A:\n");
        matrizA.imprimir();
        SalidaEstandar.consola("Matriz B:\n");
        matrizB.imprimir();
        matrizA.multiplicarMatriz(matrizB);
        SalidaEstandar.consola("Resultado:\n");
        matrizA.imprimir();

        SalidaEstandar.consola("\n\nSuma de matrices:\n");
        matrizA = new MatrizNum(3,3);
        matrizB = new MatrizNum(3,3);
        matrizA.inicializar(10);
        matrizB.inicializar(3);
        SalidaEstandar.consola("\nMatriz A:\n");
        matrizA.imprimir();
        SalidaEstandar.consola("Matriz B:\n");
        matrizB.imprimir();
        matrizA.sumarMatriz(matrizB);
        SalidaEstandar.consola("Resultado:\n");
        matrizA.imprimir();

        SalidaEstandar.consola("\n\nPotencia en la matriz:\n");
        matrizA = new MatrizNum(5,7);
        matrizA.inicializar(7);
        SalidaEstandar.consola("\nMatriz Original:\n");
        matrizA.imprimir();
        matrizA.aplicarPotencia(2);
        SalidaEstandar.consola("Resultado:\n");
        matrizA.imprimir();

        SalidaEstandar.consola("\n\nLogaritmo en la matrices:\n");
        matrizA = new MatrizNum(7,2);
        matrizA.inicializar(8);
        //matrizA.setElemento(5,0,0);
        SalidaEstandar.consola("\nMatriz Original:\n");
        matrizA.imprimir();
        matrizA.aplicarLog(TipoLogaritmo.NATURAL);
        SalidaEstandar.consola("Resultado:\n");
        matrizA.imprimir();

        SalidaEstandar.consola("\n\nMatriz diagonal:\n");
        matrizA = new MatrizNum(4,4);
        matrizA.inicializar(9);
        SalidaEstandar.consola("\nMatriz Original:\n");
        matrizA.imprimir();
        matrizA.matrizDiagonal(5);
        SalidaEstandar.consola("Resultado:\n");
        matrizA.imprimir();

        SalidaEstandar.consola("\n\nComprobando si es diagonal superior:\n");
        SalidaEstandar.consola("\nMatriz Original:\n");
        matrizA = new MatrizNum(3,3);
        matrizA.setElemento(2,0,10);
        matrizA.imprimir();
        Boolean diagonalSup;
        diagonalSup = matrizA.esDiagonalSup();
        System.out.println(diagonalSup.toString());

        SalidaEstandar.consola("\n\nComprobando si es diagonal inferior:\n");
        SalidaEstandar.consola("\nMatriz Original:\n");
        matrizA.imprimir();
        Boolean diagonalInf;
        diagonalInf = matrizA.esDiagonalInf();
        System.out.println(diagonalInf);





    }

}
