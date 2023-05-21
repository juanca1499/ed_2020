package interfaces;

import edlineal.ListaLigada;
import ednolineal.GrafoListaA;
import ednolineal.GrafoMatrizA;
import entradasalida.SalidaEstandar;
import utilidades.RecorridoGrafo;

/**
 * Contiene las primeras pruebas para los
 * métodos de un TDA GrafoListaA dados en
 * el vídeo tutorial de la clase.
 * @author Clase de Estructura de Datos.
 * @version 1.0.
 */
public class PruebaGrafoListaA
{
    public static void main(String[] args)
    {
        GrafoListaA grafo = new GrafoListaA();

        grafo.agregarVertice("1");
        grafo.agregarVertice("2");
        grafo.agregarVertice("3");
        grafo.agregarVertice("4");
        grafo.agregarVertice("5");

        grafo.agregarArista("1", "2");
        grafo.agregarArista("2","1");
        grafo.agregarArista("1","4");
        grafo.agregarArista("4","1");
        grafo.agregarArista("2","3");
        grafo.agregarArista("3","2");
        grafo.agregarArista("3","4");
        grafo.agregarArista("4","3");
        grafo.agregarArista("2","5");
        grafo.agregarArista("5","2");
        grafo.agregarArista("4","5");
        grafo.agregarArista("5","4");

        grafo.imprimir();

        SalidaEstandar.consola("\nRecorrido en profundidad del grafo partiendo de 1: ");
        ListaLigada recorridoProfundidad = grafo.recorridoProfundidad("1");
        recorridoProfundidad.imprimir();
    }
}
