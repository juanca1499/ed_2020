package interfaces;

import edlineal.Arreglo;
import edlineal.ListaLigada;
import edlineal.Matriz;
import edlineal.Matriz3D;
import entradasalida.SalidaEstandar;
import utilidades.ConversionMatriz;

import java.util.Date;

/**
 * Contiene las pruebas de funciones recursivas
 * correspondientes a la práctica 21.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PruebaRecursion21
{
    public static void main(String[] args)
    {

        // PRUEBAS DE ARREGLO
        SalidaEstandar.consola("\nPRUEBAS DE ARREGLO:\n");
        Arreglo arreglo = new Arreglo(7);
        arreglo.agregar(10);
        arreglo.agregar(50);
        arreglo.agregar(63);
        arreglo.agregar(10);
        arreglo.agregar(50);
        arreglo.agregar(75);
        arreglo.agregar(80);
        SalidaEstandar.consola("\nImprimiendo al revés:\n");
        arreglo.imprimirRRecursivo();
        SalidaEstandar.consola("Imprimiendo al derecho: \n");
        arreglo.imprimirRecursivo();
        SalidaEstandar.consola("Buscando el 63: " + arreglo.buscarR(63));
        Integer valor = 50;
        SalidaEstandar.consola("\nEliminando el 50 por valor: " + arreglo.eliminarR(valor) + "\n");
        arreglo.imprimirRecursivo();
        SalidaEstandar.consola("Cambiando 50 por 55: " + arreglo.setElementoR(
                50, 55, 2) + "\n");
        arreglo.imprimirRecursivo();
        SalidaEstandar.consola("Ocurrencias del 10:  \n");
        Arreglo ocurrencias = arreglo.buscarOcurrenciasR(10);
        ocurrencias.imprimirRecursivo();
        SalidaEstandar.consola("Eliminando la posicion 3: " + arreglo.eliminarR(3) + "\n");
        arreglo.imprimirRecursivo();

        Arreglo arreglo2 = new Arreglo(3);
        arreglo2.agregar(10);
        arreglo2.agregar(20);
        arreglo2.agregar(30);
        SalidaEstandar.consola("\n\nImprimiendo Arreglo2: \n");
        arreglo2.imprimirRecursivo();
        SalidaEstandar.consola("Vaciando Arreglo \n");
        arreglo2.vaciarR();
        arreglo2.imprimirRecursivo();


        Arreglo arreglo3 = new Arreglo(5);
        arreglo3.agregar(10);
        arreglo3.agregar(20);
        arreglo3.agregar(30);
        arreglo3.agregar(40);
        arreglo3.agregar(50);
        SalidaEstandar.consola("\n\nArreglo 3: \n");
        arreglo3.imprimirRecursivo();
        SalidaEstandar.consola("\nAgregando el Arreglo 3:" + arreglo.agregarArregloR(arreglo3) + " \n");
        arreglo.imprimirRecursivo();

        SalidaEstandar.consola("\nInvirtiendo el Arreglo: \n");
        arreglo.invertirR();
        arreglo.imprimirRecursivo();

        SalidaEstandar.consola("Coincidencias del 10 en el Arreglo: " + arreglo.contarR(10));

        Arreglo arreglo4 = new Arreglo(7);
        arreglo4.agregar(100);
        arreglo4.agregar(200);
        arreglo4.agregar(300);
        arreglo4.agregar(400);
        arreglo4.agregar(500);
        SalidaEstandar.consola("\n\nArreglo 4: \n");
        arreglo4.imprimirRecursivo();
        SalidaEstandar.consola("\nRellenando con 1000 dos veces: \n");
        arreglo4.rellenarR(100, 2);
        arreglo4.imprimirRecursivo();

        SalidaEstandar.consola("\n\nEliminando el tope del arreglo: " + arreglo.eliminarTope());
        SalidaEstandar.consola("\nInsertando el 33 en la posicion 3: " + arreglo.insertarR(3, 33) + "\n");
        arreglo.imprimir();

        SalidaEstandar.consola("Copiando el arreglo4: " + arreglo.copiarArregloR(arreglo4) + "\n");
        arreglo.imprimirRecursivo();

        SalidaEstandar.consola("\nArreglo 3:  \n");
        arreglo3.imprimirRecursivo();
        SalidaEstandar.consola("\nAsignando el Arreglo 3: \n");
        arreglo.setArregloR(arreglo3.leerArregloR());
        arreglo.imprimirRecursivo();

        ListaLigada lista = new ListaLigada();
        SalidaEstandar.consola("\nConvirtiendo el arreglo a Lista:\n");
        lista = arreglo.arregloAListaRR();
        lista.imprimir();

        Arreglo arreglo5 = new Arreglo(6);
        arreglo5.agregar(15);
        arreglo5.agregar(20);
        arreglo5.agregar(25);
        arreglo5.agregar(30);
        arreglo5.agregar(35);
        arreglo5.agregar(40);
        SalidaEstandar.consola("\nArreglo 5:\n");
        arreglo5.imprimirRecursivo();

        SalidaEstandar.consola("\nConvirtiendo el arreglo5 a una Matriz:\n");
        Matriz matrizArray = arreglo5.reDim(3,2);
        SalidaEstandar.consola("\n");
        matrizArray.imprimir();

        // PRUEBAS DE LISTA.
        SalidaEstandar.consola("\n\n\nPRUEBAS DE LISTA LIGADA:\n");
        lista = new ListaLigada();
        lista.agregar("A");
        lista.agregar("B");
        lista.agregar("C");
        lista.agregar("D");
        lista.agregar("E");
        lista.agregar("F");
        lista.agregar("G");
        SalidaEstandar.consola("\nImprimiendo la lista al revés: \n");
        lista.imprimirRRecursivo();

        SalidaEstandar.consola("\n\nBuscando D en la Lista: " + lista.buscarR("D"));
        SalidaEstandar.consola("\n\nEliminando C de la Lista: " + lista.eliminarR("C") + "\n");
        lista.imprimir();
        SalidaEstandar.consola("\n\nEliminando tope de la lista: " + lista.eliminarTopeR() + "\n");
        lista.imprimir();

        SalidaEstandar.consola("\n\nConvirtiendo la Lista a un Arreglo: \n");
        Arreglo datosLista = lista.listaAArregloRR();
        datosLista.imprimirRecursivo();

        //PRUEBAS MATRICES
        SalidaEstandar.consola("\n\n\nPRUEBAS DE MATRICES 2D Y 3D:\n");
        Matriz matriz = new Matriz(2,2);
        matriz.setElemento(0,0,"A");
        matriz.setElemento(0,1, "B");
        matriz.setElemento(1,0, "C");
        matriz.setElemento(1,1, "D");
        SalidaEstandar.consola("\nMatriz:\n");
        matriz.imprimir();
        SalidaEstandar.consola("\nConvirtiendo la Matriz a Arreglo en base a sus Renglones\n");
        Arreglo arregloMatriz = matriz.reDim(ConversionMatriz.POR_RENGLON);
        arregloMatriz.imprimir();

        SalidaEstandar.consola("\nConvirtiendo la Matriz a Arreglo en base a sus Columnas\n");
        arregloMatriz = matriz.reDim(ConversionMatriz.POR_COLUMNA);
        arregloMatriz.imprimir();


        Matriz3D matriz3D = new Matriz3D(2,2, 3);
        matriz3D.setElemento(0,0,0, "A");
        matriz3D.setElemento(0,1,0, "B");
        matriz3D.setElemento(1,0,0, "C");
        matriz3D.setElemento(1,1,0, "D");
        matriz3D.setElemento(0,0,1,"E");
        matriz3D.setElemento(0,1,1,"F");
        matriz3D.setElemento(1,0,1,"G");
        matriz3D.setElemento(1,1,1,"H");
        matriz3D.setElemento(0,0,2,"I");
        matriz3D.setElemento(0,1,2,"J");
        matriz3D.setElemento(1,0,2,"K");
        matriz3D.setElemento(1,1,2,"L");
        SalidaEstandar.consola("\n\nMatriz3D:\n");
        matriz3D.imprimir();

        SalidaEstandar.consola("\nConvirtiendo la Matriz3D a Arreglo en base a sus Renglones:\n");
        arregloMatriz = matriz3D.reDim(ConversionMatriz.POR_RENGLON);
        arregloMatriz.imprimir();

        SalidaEstandar.consola("\nConvirtiendo la Matriz3D a Arreglo en base a sus Columnas:\n");
        arregloMatriz = matriz3D.reDim(ConversionMatriz.POR_COLUMNA);
        arregloMatriz.imprimir();

        SalidaEstandar.consola("\nConvirtiendo la Matriz3D a Arreglo en base a su Profundidad:\n");
        arregloMatriz = matriz3D.reDim(ConversionMatriz.POR_PROFUNDIDAD);
        arregloMatriz.imprimir();

        SalidaEstandar.consola("\nTransformando la Matriz3D a un Arreglo de Matrices2D:\n");
        Arreglo matrices = matriz3D.redimMat();
        Matriz matriz1 = (Matriz) matrices.getElemento(0);
        Matriz matriz2 = (Matriz) matrices.getElemento(1);
        Matriz matriz3 = (Matriz) matrices.getElemento(2);
        matriz1.imprimir();
        matriz2.imprimir();
        matriz3.imprimir();



    }
}