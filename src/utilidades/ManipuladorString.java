package utilidades;


import edlineal.ListaDobleLigada;

/**
 * Contiene métodos para el manejo y manipulación
 * de cadenas de caracteres.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo
 * @version 1.0
 */
public class ManipuladorString
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
                        // La cadena temporal contiene algo.
                        if(temporal != "")
                        {
                            separaciones.agregar(temporal);
                            temporal = "";
                        }
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

    /**
     * Genera una parte o subcadena del String original.
     * @param cadena Cadena original.
     * @param limite Caracter hasta donde se terminará
     *               de generar la subcadena.
     * @return Regresa una parte de la cadena original en
     * caso de haber podido realizar el proceso, o null en caso
     * contrario.
     */
    public static String subCadena(String cadena, char limite)
    {
        String subCadena = "";

        // La cadena tiene una longitud válida.
        if(cadena.length() > 0)
        {
            // Tokenizamos la cadena.
            for(int posToken = 0; posToken < cadena.length(); posToken++)
            {
                char token = cadena.charAt(posToken);

                // Si el token es diferente al caracter terminal, la subcadena se
                // sigue acumulando.
                if(token != limite)
                {
                    subCadena += token;
                }
                // Se encontró el caracter terminal, por lo
                // tanto se deja de acumlar la subcadena.
                else
                {
                    break;
                }
            }
            return subCadena;
        }
        // La cadena tiene una longitud inválida.
        else
        {
            return null;
        }
    }
}
