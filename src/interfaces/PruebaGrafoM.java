package interfaces;

import edlineal.IPila;
import edlineal.ListaLigada;
import ednolineal.GrafoMatrizA;
import entradasalida.SalidaEstandar;

/**
 * Contiene pruebas para la clase GrafoMatrizA.
 * @author Clase de Estructura de Datos.
 * @version 1.0.
 */
public class PruebaGrafoM
{
    public static void main(String[] args)
    {
        GrafoMatrizA grafo = new GrafoMatrizA(4);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");

        grafo.agregarArista("A", "D");
        grafo.agregarArista("B", "A");
        grafo.agregarArista("D", "B");
        grafo.agregarArista("D", "C");
        grafo.agregarArista("A", "C");

        grafo.imprimir();
        SalidaEstandar.consola("Las incidencias de C: " +
                grafo.numIncidencias("C") + "\n\n");


        // PRUEBAS ORDENACIÓN TOPOLÓGICA //
        GrafoMatrizA grafoTopologico = new GrafoMatrizA(4);
        grafoTopologico.agregarVertice("P1");
        grafoTopologico.agregarVertice("P2");
        grafoTopologico.agregarVertice("P3");
        grafoTopologico.agregarVertice("P4");
        grafoTopologico.imprimir();

        ListaLigada ordenacionT = grafoTopologico.ordenacionTopologica();
        ordenacionT.imprimir();
        SalidaEstandar.consola("\n");



                         // PRUEBAS PRÁCTICA 29 //

        SalidaEstandar.consola("\nEliminando el vértice B: " +
                grafo.eliminarVertice("B") + "\n");
        grafo.imprimir();
        SalidaEstandar.consola("\nEliminando el vértice Z: " +
        grafo.eliminarVertice("Z") + "\n");
        grafo.imprimir();

        SalidaEstandar.consola("¿Hay adyacencia de A a C? " +
                grafo.esAdyacente("A", "C"));
        SalidaEstandar.consola("\n¿Hay adyacencia de C a A? " +
                grafo.esAdyacente("C", "A"));
        SalidaEstandar.consola("\n¿Hay adyacencia de B a A? " +
                grafo.esAdyacente("B", "A"));
        SalidaEstandar.consola("\n¿Hay adyacencia de D a C? " +
                grafo.esAdyacente("D", "C"));
        SalidaEstandar.consola("\n¿Hay adyacencia de A a D? " +
                grafo.esAdyacente("A", "D"));

        SalidaEstandar.consola("\n\nEliminando la arista entre A y D: " +
                grafo.eliminarArista("A", "D"));
        SalidaEstandar.consola("\n¿Hay adyacencia de A a D? " +
                grafo.esAdyacente("A", "D"));
        SalidaEstandar.consola("\nEliminando la arista entre A y D: " +
                grafo.eliminarArista("A", "D"));
        SalidaEstandar.consola("\nDevolviendo la arista de A a D " +
                grafo.agregarArista("A", "D"));

        SalidaEstandar.consola("\n\n¿Es pseudografo? " + grafo.esPseudografo());
        SalidaEstandar.consola("\nAgregando arista de D a D: " +
                grafo.agregarArista("D", "D"));
        SalidaEstandar.consola("\n¿Es pseudografo? " + grafo.esPseudografo());

        SalidaEstandar.consola("\n\nDevolviendo el vértice B: " +
                grafo.agregarVertice("B"));
        SalidaEstandar.consola("\nDevolviendo arista de B a A: " +
                grafo.agregarArista("B", "A"));
        SalidaEstandar.consola("\nDevolviendo arista de D a B: " +
                grafo.agregarArista("D", "B") + "\n");
        grafo.imprimir();

        SalidaEstandar.consola("\n¿Es multigrafo? " + grafo.esMultigrafo());
        SalidaEstandar.consola("\nAgregando una arista parela de C a D: " +
                grafo.agregarArista("C", "D"));
        SalidaEstandar.consola("\nAgregando una arista parela de A a B: " +
                grafo.agregarArista("A", "B"));
        SalidaEstandar.consola("\n¿Es multigrafo? " + grafo.esMultigrafo() + "\n");
        grafo.imprimir();

        SalidaEstandar.consola("\nGrado del vértice D: " +
                grafo.gradoVertice("D"));
        SalidaEstandar.consola("\nGrado del vértice Z: " +
                grafo.gradoVertice("Z"));

        SalidaEstandar.consola("\n¿Hay camino de D a A? " +
                grafo.hayRuta("D","A"));

        SalidaEstandar.consola("\n\nGrafo 2");
        GrafoMatrizA grafo2 = new GrafoMatrizA(4);
        grafo2.agregarVertice("A");
        grafo2.agregarVertice("B");
        grafo2.agregarVertice("C");
        grafo2.agregarVertice("D");
        grafo2.agregarArista("A", "C");
        grafo2.agregarArista("C", "B");
        grafo2.agregarArista("B", "A");
        grafo2.agregarArista("B","D");
        grafo2.agregarArista("B","B");
        SalidaEstandar.consola("\n");
        grafo2.imprimir();
        SalidaEstandar.consola("\n¿Hay Camino de D a A?: " +
                grafo2.hayRuta("D","A"));

        SalidaEstandar.consola("\n\nGrafo 3");
        GrafoMatrizA grafo3 = new GrafoMatrizA(6);
        grafo3.agregarVertice("A");
        grafo3.agregarVertice("B");
        grafo3.agregarVertice("C");
        grafo3.agregarVertice("D");
        grafo3.agregarVertice("E");
        grafo3.agregarVertice("F");
        grafo3.agregarArista("A","A");
        grafo3.agregarArista("A", "C");
        grafo3.agregarArista("A","B");
        grafo3.agregarArista("B", "B");
        grafo3.agregarArista("C","D");
        grafo3.agregarArista("D","B");
        grafo3.agregarArista("C", "E");
        grafo3.agregarArista("E","D");
        grafo3.agregarArista("B","F");
        grafo3.agregarArista("B","E");
        grafo3.agregarArista("F", "C");
        grafo3.agregarArista("F","A");
        SalidaEstandar.consola("\n");
        grafo3.imprimir();
        SalidaEstandar.consola("\n¿Hay camino de E a A?: " +
                grafo3.hayRuta("E", "A"));
        SalidaEstandar.consola("\n¿Hay camino cerrado de E a E?: " +
        grafo3.esCaminoCerrado("E", "E"));
        SalidaEstandar.consola("\n¿Hay camino cerrado de A a A? " +
                grafo3.esCaminoCerrado("A", "A"));
        SalidaEstandar.consola("\n¿Hay camino simple de F a F? " +
                grafo3.esCaminoSimple("F", "F"));


        SalidaEstandar.consola("\n\nGrafo 4");
        GrafoMatrizA grafo4 = new GrafoMatrizA(4);
        grafo4.agregarVertice("A");
        grafo4.agregarVertice("B");
        grafo4.agregarVertice("C");
        grafo4.agregarVertice("D");
        grafo4.agregarArista("A", "B");
        grafo4.agregarArista("B", "C");
        grafo4.agregarArista("C", "A");
        grafo4.agregarArista("C","D");
        SalidaEstandar.consola("\n");
        grafo4.imprimir();
        SalidaEstandar.consola("\n¿Hay camino de A a D? " +
                grafo4.hayRuta("A","D"));
    }
}
