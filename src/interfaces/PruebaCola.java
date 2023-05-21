package interfaces;

import edlineal.Cola;
import entradasalida.SalidaEstandar;
import matematicas.Matematica;

public class PruebaCola
{
    public static void main(String[] args)
    {
        Cola cola = new Cola(5);

        cola.agregar("a");
        cola.agregar("b");
        cola.agregar("c");
        cola.agregar("d");
        cola.agregar("e");
        cola.agregar("f");
        cola.imprimir();
        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("\nAtendiendo el elmento: " + cola.eliminar());
        SalidaEstandar.consola("\n");
        cola.imprimir();
        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("Atendiendo el elemento: " + cola.eliminar());
        SalidaEstandar.consola("\nAtendiendo el elemento: " + cola.eliminar());
        SalidaEstandar.consola("\n");
        cola.agregar("x");
        cola.agregar("z");
        cola.imprimir();
        SalidaEstandar.consola("\n");
        SalidaEstandar.consola("\n");
    }
}
