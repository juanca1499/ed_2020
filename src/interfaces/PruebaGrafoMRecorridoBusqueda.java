package interfaces;

import edlineal.Lista;
import edlineal.ListaLigada;
import ednolineal.GrafoMatrizA;
import entradasalida.SalidaEstandar;
import utilidades.CriterioOrden;
import utilidades.RecorridoGrafo;


/**
 * Contiene las pruebas de la práctica 30:
 * que consiste en en el recorrido de un grafo
 * utilizando los algoritmos BFS y DFS, así como
 * la búsqueda de un nodo o vértice dentro de él.
 * @author Clase de Estructura de Datos.
 * @author Juan Carlos García Murillo
 * @version 1.0.
 */
public class PruebaGrafoMRecorridoBusqueda
{
    public static void main(String[] args)
    {
        GrafoMatrizA grafo = new GrafoMatrizA(7);
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("H");
        grafo.agregarVertice("R");
        grafo.agregarVertice("T");
        grafo.agregarArista("B", "H");
        grafo.agregarArista("C", "R");
        grafo.agregarArista("D", "B");
        grafo.agregarArista("D", "C");
        grafo.agregarArista("H", "A");
        grafo.agregarArista("H", "T");
        grafo.agregarArista("H", "D");
        grafo.agregarArista("R", "H");

        SalidaEstandar.consola("Recorrido DFS \n");
        ListaLigada listaRecorrido = grafo.recorrer(RecorridoGrafo.DFS);
        listaRecorrido.inicializaIterador();
        while (listaRecorrido.hayNodoSiguiente())
        {
            ListaLigada listaActual = (ListaLigada) listaRecorrido.obtenerSiguiente();
            listaActual.imprimir();
            SalidaEstandar.consola("\n");
        }

        SalidaEstandar.consola("Recorrido BFS \n");
        listaRecorrido = grafo.recorrer(RecorridoGrafo.BFS);
        listaRecorrido.inicializaIterador();
        while (listaRecorrido.hayNodoSiguiente())
        {
            ListaLigada listaActual = (ListaLigada) listaRecorrido.obtenerSiguiente();
            listaActual.imprimir();
            SalidaEstandar.consola("\n");
        }

        SalidaEstandar.consola("\nBuscando el vértice C: " +
                grafo.buscar("C"));
        SalidaEstandar.consola("\nBuscando el vértice H: " +
                grafo.buscar("H"));
        SalidaEstandar.consola("\nBuscando el vértice Z: " +
                grafo.buscar("Z"));
        SalidaEstandar.consola("\nBuscando el vértice R partiendo de D: " +
                grafo.buscar("R", "D"));
        SalidaEstandar.consola("\nBuscando el vértice A partiendo de R: " +
                grafo.buscar("A", "R"));
        SalidaEstandar.consola("\nBuscando el vértice Y partiendo de Z: " +
                grafo.buscar("Y", "Z"));

        SalidaEstandar.consola("\n\n¿Es fuertemente conexo? " + grafo.esFuertementeConexo());
        SalidaEstandar.consola("\nComponentes conexos del grafo:\n");
        ListaLigada lista =  grafo.componentesConexos();
        SalidaEstandar.consola("\n");
        lista.inicializaIterador();
        while (lista.hayNodoSiguiente())
        {
            ListaLigada listaActual = (ListaLigada) lista.obtenerSiguiente();
            listaActual.imprimir();
            SalidaEstandar.consola("\n");
        }
    }
}
