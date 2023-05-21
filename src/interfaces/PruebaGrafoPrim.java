package interfaces;

import edlineal.ListaLigada;
import ednolineal.GrafoListaA;
import ednolineal.GrafoMatrizA;
import entradasalida.SalidaEstandar;
import utilidades.CriterioOrden;

/**
 * Contiene las pruebas de la práctica 32.
 * En este ejercicio se implementa la funcionalidad
 * para obtener el árbol abarcador de costo mínimo con
 * <b>el algoritmo de Prim</b> de un grafo no dirigido
 * conexo.
 * @author Clase de Estructura de Datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0.
 */
public class PruebaGrafoPrim
{
    public static void main(String[] args)
    {
        GrafoListaA grafo = new GrafoListaA();

        SalidaEstandar.consola("\nGRAFO 1");
        grafo.agregarVertice("1");
        grafo.agregarVertice("2");
        grafo.agregarVertice("3");
        grafo.agregarVertice("4");
        grafo.agregarVertice("5");

        grafo.agregarArista("1","2",1);
        grafo.agregarArista("2","1",1);
        grafo.agregarArista("1","3",4);
        grafo.agregarArista("3","1",4);
        grafo.agregarArista("2","3",3);
        grafo.agregarArista("3","2",3);
        grafo.agregarArista("2","4",6);
        grafo.agregarArista("4","2",6);
        grafo.agregarArista("3","5",2);
        grafo.agregarArista("5","3",2);
        grafo.agregarArista("3","4",4);
        grafo.agregarArista("4","3",4);
        grafo.agregarArista("4","5",5);
        grafo.agregarArista("5","4",5);
        grafo.imprimir();


        SalidaEstandar.consola("\nArbol costo mínimo:\n");
        ListaLigada arbolCostoMinimo = grafo.arbolCostoMinimo();
        arbolCostoMinimo.imprimir();

        SalidaEstandar.consola("\n\n\nGRAFO 2");
        grafo = new GrafoListaA();
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");
        grafo.agregarVertice("F");
        grafo.agregarVertice("G");


        grafo.agregarArista("A","B",5.0);
        grafo.agregarArista("B","A",5.0);

        grafo.agregarArista("A","C",2.0);
        grafo.agregarArista("C","A",2.0);

        grafo.agregarArista("A","D",3.0);
        grafo.agregarArista("D","A",3.0);

        grafo.agregarArista("D","E",8.0);
        grafo.agregarArista("E","D",8.0);

        grafo.agregarArista("C","E",4.0);
        grafo.agregarArista("E","C",4.0);

        grafo.agregarArista("C","G",1.0);
        grafo.agregarArista("G","C",1.0);

        grafo.agregarArista("C","F",1.0);
        grafo.agregarArista("F","C",1.0);

        grafo.agregarArista("B","F",9.0);
        grafo.agregarArista("F","B",9.0);

        grafo.agregarArista("F","G",6.0);
        grafo.agregarArista("G","F",6.0);

        grafo.agregarArista("E","G",7.0);
        grafo.agregarArista("G","E",7.0);
        grafo.imprimir();
        SalidaEstandar.consola("\nArbol costo mínimo:\n");
        arbolCostoMinimo = grafo.arbolCostoMinimo();
        arbolCostoMinimo.imprimir();


    }
}
