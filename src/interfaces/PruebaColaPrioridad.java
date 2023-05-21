package interfaces;

import catalogos.Proceso;
import edlineal.ColaPrioridad;
import entradasalida.SalidaEstandar;
import utilidades.CriterioOrden;

/**
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class PruebaColaPrioridad
{
    public static void main(String[] args)
    {
        ColaPrioridad colaDocumentos = new ColaPrioridad(10, CriterioOrden.DESCENDENTE);
        Proceso documento1 = new Proceso("A", 3);
        Proceso documento2 = new Proceso("B", 14);
        Proceso documento3 = new Proceso("C", 12);
        Proceso documento4 = new Proceso("D", 7);
        Proceso documento5 = new Proceso("E", 10);
        Proceso documento6 = new Proceso("F", 1);
        Proceso documento7 = new Proceso("G", 6);
        Proceso documento8 = new Proceso("H", -8);
        Proceso documento9 = new Proceso("I", 9);
        Proceso documento10 = new Proceso("J", 5);

        colaDocumentos.agregar(documento1);
        colaDocumentos.agregar(documento2);
        colaDocumentos.agregar(documento3);
        colaDocumentos.agregar(documento4);
        colaDocumentos.agregar(documento5);
        colaDocumentos.agregar(documento6);
        colaDocumentos.agregar(documento7);
        colaDocumentos.agregar(documento8);
        colaDocumentos.agregar(documento9);
        colaDocumentos.agregar(documento10);
        SalidaEstandar.consola("\nImprimiendo los elementos: \n");
        colaDocumentos.imprimir();
        colaDocumentos.eliminar();
        SalidaEstandar.consola("\nImprimiendo los elementos: \n");
        colaDocumentos.imprimir();





    }
}
