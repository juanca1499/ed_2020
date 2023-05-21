package interfaces;

import edlineal.ArregloOrdenado;
import entradasalida.SalidaEstandar;
import utilidades.CriterioOrden;


/**
 *
 * @author Clase de estructura de datos.
 */
public class PruebaArregloOrdenado
{
    public static void main(String[] args)
    {
        ArregloOrdenado arrayOrd1 = new ArregloOrdenado(5, CriterioOrden.ASCENDENTE);
        ArregloOrdenado arrayOrd2 = new ArregloOrdenado(5, CriterioOrden.DESCENDENTE);

        arrayOrd1.agregar(20);
        arrayOrd1.agregar(5);
        arrayOrd1.agregar(1);
        arrayOrd1.agregar(3);
        arrayOrd1.agregar(7);
        arrayOrd1.imprimir();

        arrayOrd2.agregar(30);
        arrayOrd2.agregar(5);
        arrayOrd2.agregar(1);
        arrayOrd2.agregar(3);
        arrayOrd2.agregar(7);

        SalidaEstandar.consola("El numero 30 esta en la posicion " + arrayOrd1.buscar(5));
        SalidaEstandar.consola("\nCambiando el 1 por 100... " + arrayOrd1.setElemento(1,100,1000));
        SalidaEstandar.consola("\n");
        arrayOrd1.imprimir();
        SalidaEstandar.consola("\nCambiando el valor en la posicion 3 por el numero 13 " + arrayOrd1.setElemento(3,21));
        SalidaEstandar.consola("\n");
        arrayOrd1.imprimir();

        Integer numero = 100;
        SalidaEstandar.consola("\nEliminando el numero 100 " + arrayOrd1.eliminar(numero));
        SalidaEstandar.consola("\n");
        arrayOrd1.imprimir();

        SalidaEstandar.consola("\nAgregando el arreglo 2 " + arrayOrd1.agregarArreglo(arrayOrd2));
        SalidaEstandar.consola("\n");
        arrayOrd1.imprimir();

        SalidaEstandar.consola("\nInvirtiendo arreglo ");
        arrayOrd1.invertir();
        SalidaEstandar.consola("\n");
        arrayOrd1.imprimir();

        SalidaEstandar.consola("\nRellenando arreglo: ");
        numero = 13;
        arrayOrd1.eliminar(numero);
        arrayOrd1.rellenar(33,100);
        SalidaEstandar.consola("\n");
        arrayOrd1.imprimir();

        SalidaEstandar.consola("\n\n");
        arrayOrd2.imprimir();


    }
}
