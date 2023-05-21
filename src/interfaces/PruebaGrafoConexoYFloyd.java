package interfaces;

import edlineal.Arreglo;
import edlineal.ListaLigada;
import edlineal.Matriz;
import ednolineal.GrafoMatrizA;
import entradasalida.SalidaEstandar;
import utilidades.CriterioOrden;

/**
 * Contiene las pruebas de la práctica
 * 31 de Grafos.
 * En esta clase se comprueba el funcionamiento
 * de los procedimientos para identificar si
 * un grafo es conexo, fuertemente conexo y
 * el algoritmo de Floyd o recorrido más
 * óptimo.
 * @author Clase de Estructura de Datos.
 * @author Juan Carlos García Murillo
 * @version 1.0.
 */
public class PruebaGrafoConexoYFloyd
{

    public static void main(String[] args)
    {
        SalidaEstandar.consola("\nGRAFO DIRIGIDO\n");
        GrafoMatrizA grafoDirigido = new GrafoMatrizA(4, CriterioOrden.DESCENDENTE);
        grafoDirigido.agregarVertice("A");
        grafoDirigido.agregarVertice("B");
        grafoDirigido.agregarVertice("C");
        grafoDirigido.agregarVertice("D");

        grafoDirigido.agregarArista("A", "D");
        grafoDirigido.agregarArista("B", "A");
        grafoDirigido.agregarArista("D", "B");
        grafoDirigido.agregarArista("D", "C");
        grafoDirigido.agregarArista("A", "C");

        grafoDirigido.imprimir();

        SalidaEstandar.consola("\n¿Es fuertemente conexo? " + grafoDirigido.esFuertementeConexo());
        SalidaEstandar.consola("\nComponentes conexos del grafo:\n");
        imprimirListaDeListas(grafoDirigido.componentesConexos());
        SalidaEstandar.consola("\nDistancias minimas del grafo y recorridos: \n");
        Arreglo matrices = grafoDirigido.distanciaMinimaVerticesYRecorridos();
        SalidaEstandar.consola("\nDISTANCIAS\n");
        ((Matriz)matrices.getElemento(0)).imprimir();
        SalidaEstandar.consola("\nRECORRIDOS\n");
        ((Matriz)matrices.getElemento(1)).imprimir();


        SalidaEstandar.consola("\nGRAFO NO DIRIGIDO");
        GrafoMatrizA grafo =new GrafoMatrizA(7, CriterioOrden.DESCENDENTE);
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
        SalidaEstandar.consola("\n¿Es conexo? " + grafo.esConexo());
        SalidaEstandar.consola("\nComponentes conexos del grafo:\n");
        imprimirListaDeListas(grafo.componentesConexos());
        SalidaEstandar.consola("\nDistancias minimas del grafo: \n");
        grafo.distanciaMinimaVertices().imprimir();

        SalidaEstandar.consola("\nDistancias minimas del grafo y recorridos: \n");
        matrices = grafo.distanciaMinimaVerticesYRecorridos();
        SalidaEstandar.consola("\nDISTANCIAS\n");
        ((Matriz)matrices.getElemento(0)).imprimir();
        SalidaEstandar.consola("\nRECORRIDOS\n");
        ((Matriz)matrices.getElemento(1)).imprimir();

        /**
        GrafoMatrizA grafo3 = new GrafoMatrizA(7, CriterioOrden.ASCENDENTE);
        grafo3.agregarVertice("A");
        grafo3.agregarVertice("B");
        grafo3.agregarVertice("C");
        grafo3.agregarVertice("D");
        grafo3.agregarVertice("H");
        grafo3.agregarVertice("T");
        grafo3.agregarVertice("R");


        grafo3.agregarArista("H","A");
        grafo3.agregarArista("H","T");
        grafo3.agregarArista("H","D");
        grafo3.agregarArista("D","B");
        grafo3.agregarArista("D","C");
        grafo3.agregarArista("B","H");
        grafo3.agregarArista("C","R");
        grafo3.agregarArista("R","H");
        SalidaEstandar.consola("\n¿Es fuertemente conexo? " + grafo3.esFuertementeConexo());
        SalidaEstandar.consola("\nComponentes conexos del grafo:\n");
        imprimirListaDeListas(grafo3.componentesConexos());
        SalidaEstandar.consola("\nDistancias minimas del grafo: \n");
        grafo3.distanciaMinimaVertices().imprimir();

        SalidaEstandar.consola("\nDistancias minimas del grafo y recorridos: \n");
        matrices = grafo3.distanciaMinimaVerticesYRecorridos();
        SalidaEstandar.consola("\nDISTANCIAS\n");
        ((Matriz)matrices.getElemento(0)).imprimir();
        SalidaEstandar.consola("\nRECORRIDOS\n");
        ((Matriz)matrices.getElemento(1)).imprimir();
         */
    }

    /**
     * Imprime una lista ligada de listas
     * ligadas.
     * @param lista Lista a ser impresa.
     */
    private static void imprimirListaDeListas(ListaLigada lista)
    {
        // Este método es sólo para fines de pruebas.
        lista.inicializaIterador();
        while (lista.hayNodoSiguiente())
        {
            ListaLigada listaActual = (ListaLigada) lista.obtenerSiguiente();
            listaActual.imprimir();
            SalidaEstandar.consola("\n");
        }
    }
}
