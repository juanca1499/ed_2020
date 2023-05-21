package interfaces;

import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;
import utilidades.Encriptador;

/**
 * @version 1.0
 * @author Clase de estructura de datos.
 */

public class PruebaEncriptador
{
    public static void main(String[] args)
    {
        SalidaEstandar.consola("Indica el mensaje a encriptar: ");
        String mensaje = EntradaEstandar.consolaCadena();
        SalidaEstandar.consola("\nIndica la cantidad de agrupaciones a usar: ");
        int cantAgrupaciones = Integer.parseInt(EntradaEstandar.consolaCadena());
        String mensajeEncriptado = Encriptador.encriptar(mensaje, cantAgrupaciones);
        SalidaEstandar.consola("\nMensaje encriptado: " + mensajeEncriptado);
        SalidaEstandar.consola("\nMensaje desencriptado " + Encriptador.desencriptar(mensajeEncriptado));
    }
}
