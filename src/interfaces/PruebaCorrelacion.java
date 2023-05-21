package interfaces;

import edlineal.Arreglo;
import edlineal.ListaDobleLigada;
import entradasalida.SalidaEstandar;
import entradasalida.archivos.LeerArchivo;
import matematicas.Estadistica;
import vistas.PantallaEstadistica;

/**
 * Clase con las pruebas para obtener la Correlacion
 * de dos conjuntos de datos.
 * @version 1.0
 * @author Clase de estructura de datos.
 */
public class PruebaCorrelacion
{
    public static void main(String[] args)
    {
        Arreglo arregloDatos = LeerArchivo.leer("resources/estadistica/datosPractica19.txt", 498);
        SalidaEstandar.consola("\nDatos sin separar:\n");
        arregloDatos.imprimir();
        Arreglo datosSeparados = arregloDatos.separarPorColumna('\t', 2);
        Arreglo columna1 = (Arreglo) datosSeparados.getElemento(0);
        Arreglo columna2 = (Arreglo) datosSeparados.getElemento(1);

        ListaDobleLigada lista1 = columna1.aListaDobleLigada();
        ListaDobleLigada lista2 = columna2.aListaDobleLigada();
        SalidaEstandar.consola("\n\nConjunto 1: " + lista1.getSize() + "\n");
        lista1.imprimir();
        SalidaEstandar.consola("\n\nConjunto 2: " + lista2.getSize() + "\n");
        lista2.imprimir();
        double correlacion = Estadistica.correlacionPearson(lista1, lista2);
        SalidaEstandar.consola("\n\nLa correlación es: " + correlacion);
        SalidaEstandar.consola("\nEl tipo de correlacion es: " +
                PantallaEstadistica.getTipoCorrelacion(correlacion));
    }
}
