package interfaces;

import edlineal.ListaLigada;
import entradasalida.SalidaEstandar;


public class PruebaListaLigada
{
    public static void main(String[] args)
    {
        ListaLigada lista = new ListaLigada();

        lista.insertarInicio(1);
        lista.insertarInicio(78);
        lista.insertarInicio(3);
        lista.insertarInicio(4);
        lista.agregar(67);
        lista.imprimir();
        SalidaEstandar.consola("\n");

        lista.inicializaIterador();
        int suma = 0;

        while (lista.hayNodoSiguiente())
        {
            Object valor = lista.obtenerSiguiente();
            suma += (int) valor;
        }
        SalidaEstandar.consola("La suma es: " + suma);
    }
}
