package entradasalida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Esta clase implementa los métodos de la entrada estandar.
 * @author Clase de estructura de datos.
 * @version 1.0.
 */
public class EntradaEstandar
{
    /**
     * Captura desde el teclado los datos introducidos
     * por el usuario.
     * @return Regresa el valor introducido.
     */
    public static String consolaCadena()
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        String cadenaEntrada = "";

        try
        {
            cadenaEntrada = buffer.readLine();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return cadenaEntrada;
        }
    }
}
