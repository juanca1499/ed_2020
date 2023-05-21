package utilidades;

/**
 * Esta clase implementa el TDA ConversionBaseNum.
 * Contiene funciones que transforman números a
 * diferentes bases numéricas.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ConversorBaseNum
{

    /** Guarda los valores que se pueden tener entre un número desde base 2 a 16.  */
    private static char[] arrayHexadecimal = {'0','1','2','3','4','5','6','7',
                                              '8','9','A','B','C','D','E','F'};


    /**
     * Transforma un número en base 10 a base 2 o binario.
     * @param numero Valor a ser transformado.
     * @return Regresa una cadena con el equivalente del número
     * en binario, o -1 si el número a convertir es inválido (menor a 0).
     */
    public static String decimalABinario(int numero)
    {
        // Es un número válido.
        if(numero >= 0)
        {
            return decimalABinarioR(numero);
        }
        // Es un número inválido.
        else
        {
            return "-1";
        }
    }

    /**
     * Función recursiva real que transforma un número en base 10
     * a base 2 o binario.
     * @param numero Valor a ser transformado.
     * @return Regresa una cadena con el equivalente del número
     * en binario.
     */
    private static String decimalABinarioR(int numero)
    {
        // Caso base.
        if(numero == 1 || numero == 0)
        {
            return numero + "";
        }
        // Caso recursivo.
        else
        {
            // Si el número es múltiplo de 2.
            if(numero % 2 == 0)
            {
                return decimalABinarioR(numero/2) + "0";
            }
            // Si no es múltiplo de 2.
            else
            {
                return decimalABinarioR( (numero - 1) / 2 ) + "1";
            }
        }
    }

    /**
     * Convierte un número en base 10 a hexadecimal
     * o base 16.
     * @param numero Valor a ser convetido.
     * @return Regresa una cadena con el número convertido
     * a base 16 o hexadecimal, o -1 si el número a convertir
     * es inválido (menor a -1).
     */
    public static String decimalAHexadecimal(int numero)
    {
        // Es un número válido.
        if(numero > 1)
        {
            return decimalABaseNR(numero, 16);
        }
        // El número es cero o uno.
        else if(numero == 0 || numero == 1)
        {
            return  "" + numero;
        }
        // Es un número inválido.
        else
        {
            return "-1";
        }
    }

    /**
     * Convierte un número en base 10 o decimal a una base
     * entre 1 y 9.
     * @param numero Valor a ser convertido.
     * @param base Base a la que se convertirá el número.
     * @return Regresa el número convertido a la base indicada, o -1
     * si el número o la base es inválida (menor a -1).
     */
    public static String decimalABaseN(int numero, int base)
    {
        // Si el número a convertir está entre el rango permitido.
        if(base > 1 && base < 9 && numero != 0 && numero != 1)
        {
            return decimalABaseNR(numero, base);
        }
        // El número es cero o uno.
        else if(numero == 0 || numero == 1)
        {
            return "" + numero;
        }
        // Es un número inválido.
        else
        {
            return "-1";
        }
    }

    /**
     * Funcion real que convierte un número en base 10 o decimal a una base
     * entre 1 y 9.
     * @param numero Valor a ser convertido.
     * @param base Base a la que se convertirá el número.
     * @return Regresa el número convertido a la base indicada.
     */
    private static String decimalABaseNR(int numero, int base)
    {
        // Caso base.
        if(numero == 0 ||  numero == 1)
        {
            // La base es binaria.
            if(base == 2)
            {
                return 1 + "";
            }
            // La base no es binaria.
            else
            {
                return "";
            }
        }
        // Caso recursivo.
        else
        {
            int residuo = numero % base;
            return decimalABaseNR(numero / base, base) +
                                 arrayHexadecimal[residuo] + "";
        }
    }
}
