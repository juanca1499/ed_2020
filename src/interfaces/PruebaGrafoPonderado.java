package interfaces;

import ednolineal.GrafoMatrizA;
import entradasalida.SalidaEstandar;
import utilidades.CriterioOrden;

/**
 * Contiene las pruebas de la clase
 * GrafoMatrizA con la particularidad
 * de que ahora le será asignada una
 * ponderación no sólo de 1 o 0.
 * @author Clase de Estructura de Datos.
 * @version 1.0.
 */
public class PruebaGrafoPonderado
{
    public static void main(String[] args)
    {
        GrafoMatrizA grafo=new GrafoMatrizA(7,CriterioOrden.DESCENDENTE);

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

        SalidaEstandar.consola("\n");
        grafo.imprimir();

        SalidaEstandar.consola("La métrica óptima de A-G es: "+
                grafo.obtenerMetricaOptima("A","G"));

        SalidaEstandar.consola("\nLa ruta óptima de A-G es: ");
        grafo.obtenerRutaOptima("A","G").imprimir();
    }
}
