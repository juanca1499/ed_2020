package interfaces;

import catalogos.Empresa;
import catalogos.Nodo;
import edlineal.Arreglo;
import edlineal.ListaOrdenada;
import entradasalida.SalidaEstandar;
import utilidades.CriterioOrden;


/**
 * @version 1.0
 * @author Clase de estructura de datos.
 */
public class PruebaListaOrdenada
{
    public static void main(String[] args)
    {
        ListaOrdenada listaOrdenada = new ListaOrdenada(CriterioOrden.DESCENDENTE);

        SalidaEstandar.consola("\nDespués de agregar elementos. \n");
        listaOrdenada.agregar(10);
        listaOrdenada.agregar(1);
        listaOrdenada.agregar(15);
        listaOrdenada.agregar(3);
        listaOrdenada.agregar("Hola");
        listaOrdenada.agregar("Adios");
        listaOrdenada.agregar("Adios");
        listaOrdenada.agregar(new Nodo("Escoba"));
        listaOrdenada.agregar(new Nodo("Escoba"));
        listaOrdenada.agregar("1");
        listaOrdenada.agregar(-1);
        listaOrdenada.agregar("Zacatecas");
        listaOrdenada.agregar(14);
        listaOrdenada.agregar(16);
        listaOrdenada.imprimir();

        SalidaEstandar.consola("\nDespues de eliminar un elemento.\n");
        SalidaEstandar.consola("\nElemento borrado: " + listaOrdenada.eliminar("Zacatecas") + "\n");
        listaOrdenada.imprimir();
        SalidaEstandar.consola("\n\nElemento borrado: " + listaOrdenada.eliminar(-1) + "\n");
        listaOrdenada.imprimir();
        listaOrdenada.eliminar(-1);
        SalidaEstandar.consola("\n\nElemento borrado: " + listaOrdenada.eliminar(-1) + "\n");
        listaOrdenada.imprimir();
        SalidaEstandar.consola("\n\nElemento borrado: " + listaOrdenada.eliminar(new Nodo("Escoba")) + "\n");
        listaOrdenada.imprimir();
        SalidaEstandar.consola("\n\nElemento borrado: " + listaOrdenada.eliminar(14) + "\n");
        listaOrdenada.imprimir();
        SalidaEstandar.consola("\n\n");
        listaOrdenada.imprimirR();
        SalidaEstandar.consola("\nLa longitud de la lista es: " + listaOrdenada.getSize());

        SalidaEstandar.consola("\n\nLista 2:");
        ListaOrdenada listaOrdenada2 = new ListaOrdenada(CriterioOrden.ASCENDENTE);
        listaOrdenada2.agregar(10);
        listaOrdenada2.agregar(30);
        SalidaEstandar.consola("\nElementos en la lista2\n");
        listaOrdenada2.eliminar(10);
        listaOrdenada2.agregar(100);
        listaOrdenada2.agregar(50);
        listaOrdenada.agregar(60);

        SalidaEstandar.consola("\nImprimiendo al derecho: \n");
        listaOrdenada2.imprimir();

        SalidaEstandar.consola("\nImprimiendo al revés: \n");
        listaOrdenada2.imprimirR();

        SalidaEstandar.consola("\nInvirtiendo la lista: \n");
        listaOrdenada2.invertir();
        listaOrdenada2.imprimir();

        SalidaEstandar.consola("\nLa longitud de la lista es: " + listaOrdenada2.getSize());

        Arreglo arreglo = listaOrdenada2.buscarOcurrencias(30);
        SalidaEstandar.consola("\nBuscando la posición del 30: ");
        arreglo.imprimir();

        Arreglo arreglo2 = new Arreglo(3);
        arreglo2.agregar(200);
        arreglo2.agregar("Jaja");
        arreglo2.agregar(300);
        SalidaEstandar.consola("Agregando el arreglo de datos a la lista: " + listaOrdenada2.agregarArreglo(arreglo2) + "\n");
        listaOrdenada2.imprimir();

        SalidaEstandar.consola("\nContando las ocurrencias del 300 en la Lista: " + listaOrdenada2.contar(300));
        SalidaEstandar.consola("\nContando las ocurrencias de ABC en la Lista: " + listaOrdenada2.contar("ABC"));
        SalidaEstandar.consola("\nCambiando el 300 por 400: " + listaOrdenada2.setElemento(300, 400, 1) + "\n");
        listaOrdenada2.imprimir();
        SalidaEstandar.consola("\nCambiando el 600 por 700: " + listaOrdenada2.setElemento(600, 700, 1) + "\n");
        listaOrdenada2.imprimir();


        SalidaEstandar.consola("\nVaciando la lista: \n");
        listaOrdenada2.vaciar();
        listaOrdenada2.imprimir();
    }
}
