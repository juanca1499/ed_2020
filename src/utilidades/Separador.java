package utilidades;

import edlineal.ListaDobleLigada;

/**
 * Esta clase implementa el TDA Separador.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Separador
{

    /**
     * Separa una cadena de caracteres en varias.
     * @param cadena Cadena de caracteres a ser separada.
     * @param separador Criterio de separación.
     * @return Regresa una ListaDobleLigada con las separaciones
     * realizadas, o null en caso de que la cadena no contenga nada.
     */
    public static ListaDobleLigada separar(String cadena, char separador)
    {
        ListaDobleLigada separaciones = new ListaDobleLigada();

        // La cadena tiene una longitud válida.
        if(cadena.length() > 0)
        {
            String temporal = "";

            // Tokenizamos la cadena.
            for(int posToken = 0; posToken < cadena.length(); posToken++)
            {
                char token = cadena.charAt(posToken);

                // Si el token es igual al primer caracter del separador.
                if(token == separador)
                {
                    // Si el token no estaba entre la primera y última posición.
                    if( posToken != 0 && posToken != (cadena.length() - 1) )
                    {
                        separaciones.agregar(temporal);
                        temporal = "";
                    }
                    // El token estaba entre la primera y la última posición.
                    else
                    {
                        continue;
                    }
                }
                // El token no es igual al primer caracter del separador.
                else
                {
                    temporal += token;
                }
            }
            separaciones.agregar(temporal);
            return separaciones;
        }
        // La cadena tiene una longitud inválida.
        else
        {
            return null;
        }
    }
}
