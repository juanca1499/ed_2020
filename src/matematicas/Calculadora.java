package matematicas;


import vistas.PantallaCalculadora;

/**
 * Esta clase implementa el TDA Calculadora.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Calculadora
{

    /**
     * Solicita una expresión aritmética al usuario.
     * @return Regresa la expresión dada.
     */
    public static String pedirExpresion()
    {
        return PantallaCalculadora.pedirExpresion();
    }

    /**
     * Obtiene el resultado de la expresión en forma
     * prefija dada.
     * @param prefija Expresión a ser evaluada.
     * @return Regresa el resultado de la operación.
     */
    public static Double evaluarExpresionPreFija(String prefija)
    {
        return ExpresionAritmetica.evaluarExpresionPreFija(prefija);
    }

    /**
     * Obtiene el resultado de la expresión en forma
     * postfija dada.
     * @param postfija Expresión a ser evaluada.
     * @return Regresa el resultado de la operación.
     */
    public static Double evaluarExpresionPostFija(String postfija)
    {
        return ExpresionAritmetica.evaluarExpresionPostFija(postfija);
    }

    /**
     * Busca en la expresión nombres de varaibles y solicita
     * el valor de cada variable encontrada.
     * @param expresionOrg Expresión a buscar posibles variables.
     * @return Regresa la expresión con las variables sustituídas
     * por su valor real.
     */
    public static String detectarVariables(String expresionOrg)
    {
        String nuevaExpresion = "";
        String variableTemporal = "";

        // 1.- Se tokeniza la expresión.
        for(int posToken = 0; posToken < expresionOrg.length(); posToken++)
        {
            // 2.- Se toma cada carácter de la expresión.
            char token = expresionOrg.charAt(posToken);

            if(!esSeparador(token) && !esNumero(token) && (posToken == expresionOrg.length() - 1))
            {
                String valor = PantallaCalculadora.pedirValorVariable(String.valueOf(token));
                nuevaExpresion += valor;
                variableTemporal = "";
            }

            // 3.- Se comprueba si el token es un separador de variable.
            if(esSeparador(token) || posToken == (expresionOrg.length() - 1))
            {
                // 3.1.- Se comprueba que se haya detectado una variable.
                if(!variableTemporal.isEmpty())
                {
                    //3.1.1.- Se pide el valor real de la variable y se guarda en la nueva expresión.
                    String valor = PantallaCalculadora.pedirValorVariable(variableTemporal);
                    nuevaExpresion += valor;
                    variableTemporal = "";
                }
                else if(!ExpresionAritmetica.esOperando(token) && token != ' ')
                {
                    System.out.println("Tomando: " + token);
                    nuevaExpresion += token;
                }

            }
            else if(esNumero(token) && !variableTemporal.isEmpty())
            {
                nuevaExpresion += token;
            }
            //4.- Si no es separador de variable entonces podría ser una variable.
            else
            {
                // 4.1.- Se comprueba si es el inicio de una variable y si comienza con una letra.
                if( esLetra(token) && variableTemporal.isEmpty() )
                {
                    variableTemporal += token;
                }
                // 4.2.- Se comprueba si es solamente un número
                else if( (esLetra(token) || esNumero(token))  && !variableTemporal.isEmpty() )
                {
                    variableTemporal += token;
                }
                // 4.3.- Si no se cumple ninguna condición, la variable es inválida y se rechaza.
                else
                {
                    variableTemporal = "";
                    while (!esSeparador(token))
                    {
                        if(posToken == expresionOrg.length() - 1)
                        {
                            break;
                        }
                        posToken++;
                        token = expresionOrg.charAt(posToken);
                    }
                }
            }
        }
        return nuevaExpresion;
    }

    /**
     * Comprueba si el argumento dado corresponde a un
     * separador de variables.
     * @param token Valor a ser comprobado.
     * @return Regresa <b>true</b> en caso de ser un
     * separador, o <b>false</b> en caso contrario.
     */
    private static boolean esSeparador(char token)
    {
        if(token == ' ' || token == '(' || token == ')' || !ExpresionAritmetica.esOperando(token))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Comprueba si el argumento dado es una letra.
     * @param token Valor a ser comprobado.
     * @return Regresa <b>true</b> en caso de ser una letra,
     * o <b>false</b> en caso contrario.
     */
    private static boolean esLetra(Character token)
    {
        int tokenAscii = token;

        if((tokenAscii >= 65 && tokenAscii <= 90) || (tokenAscii >= 97 && tokenAscii <= 122) ||
                tokenAscii == 130 || (tokenAscii >= 160 && tokenAscii <= 165) || tokenAscii == 'Á'
                || tokenAscii == 'É' || tokenAscii == 'Í' || tokenAscii == 'Ó' || tokenAscii == 'Ú')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Comprueba si el valor dado es un número.
     * @param token Valor a ser comprobado.
     * @return Regresa <b>true</b> en caso de ser un
     * número, o <b>false</b> en caso contrario.
     */
    private static boolean esNumero(Object token)
    {
        try
        {
            Integer numero = Integer.parseInt(token.toString());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
