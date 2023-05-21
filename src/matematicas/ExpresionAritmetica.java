package matematicas;

import edlineal.Arreglo;
import edlineal.Pila;

/**
 * Esta clase implementa el TDA ExpresionAritmetica.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ExpresionAritmetica
{
    public static Double evaluarExpresionPostFija(String postfija)
    {
        Pila pila = new Pila(postfija.length());

        //1.- Tokenizar.
        for (int posToken = 0; posToken < postfija.length(); posToken++)
        {
            //1.1.- Obtener el token.
            char token = postfija.charAt(posToken);

            //2.- Si es operando, lo meto a la pila.
            if (esOperando(token))
            {
                if(!pila.push("" + token))
                {
                    return null;
                }
            }
            //3.- Si es operador, saco el tope de la pila como operando dos.
            //    Saco el tope de la pila y es el operando 1.
            else
            {
                String operando2 = (String) pila.pop();
                String operando1 = (String) pila.pop();
                if(operando1 == null || operando2 == null)
                {
                    return null;
                }
                //4.- Hago la operación con el token (operador actual) y esos
                //    dos operandos.
                double resultadoParcial = ejecutaOperacion(
                        token, Double.parseDouble(operando1), Double.parseDouble(operando2));
                //5.- Meto el resultado de esa operacion en la pila.
                if(!pila.push(""+resultadoParcial))
                {
                    return null;
                }
            }
        }//6.- Repito hasta que no haya tokens.

        //7.- El resultado de la operación está en el tope, y lo retorno.
        String resultadoCadena = (String) pila.pop();
        if(resultadoCadena != null)
        {
            return Double.parseDouble(resultadoCadena);
        }
        else
        {
            return null;
        }
    }

    public static double ejecutaOperacion(char tokenOperador, double op1, double op2)
    {
        if(tokenOperador == '^')
        {
            return Math.pow(op1, op2);
        }
        else if(tokenOperador == '*')
        {
            return op1 * op2;
        }
        else if(tokenOperador == '/')
        {
            return  op1 / op2;
        }
        else if(tokenOperador == '+')
        {
            return op1 + op2;
        }
        else if(tokenOperador == '-')
        {
            return op1 - op2;
        }
        else
        {
            return 0.0;
        }
    }


    /**
     * Tokeniza una cadena para comprobar si corresponde
     * a un operando.
     * @param expresion Cadena a ser comprobada.
     * @return Regresa <b>true</b> si la cadena es un
     * operando, o <b>false</b> en caso contrario.
     */
    public static boolean esOperando(String expresion)
    {
        // La expresión es válida.
       if(expresion != null)
       {
           // El posible operando es negativo.
           if(expresion.charAt(0) == '-' && expresion.length() > 1)
           {
               // Se tokeniza el posible operando ignorando el signo negativo.
               for(int index = 1; index < expresion.length(); index++)
               {
                   // El token no es un operando.
                   if(!esOperando(expresion.charAt(index)))
                   {
                       return false;
                   }
               }
           }
           // El posible operando es positivo.
           else
           {
               // Se tokeniza el posible operando.
               for(int index = 0; index < expresion.length(); index++)
               {
                   // El token no es un operando.
                   if(!esOperando(expresion.charAt(index)))
                   {
                       return false;
                   }
               }
           }
           return true;
       }
       // La expresión es inválida.
       else
       {
           return false;
       }
    }

    public static boolean esOperando(char token)
    {
        if(token != '^' && token != '*' && token != '/' && token != '+' &&
           token != '-' && token != '(' && token != ')')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Extrae un conjunto de caracteres que cumplan
     * con la característica de ser una variable partiendo
     * desde un determinado punto de una cadena.
     * @param expresion Cadena de donde sera extraída la
     *                  varaible.
     * @param inicio Punto desde comenzará la búsqueda de
     *               la variable.
     * @return Regresa la variable obtenida o null en caso
     * de no haber encontrado ninguna.
     */
    public static String getVariable(String expresion, int inicio)
    {
        // Los parámetros son válidos.
        if(expresion != null && inicio >= 0 && inicio < expresion.length())
        {
            String variable = "";

            // Se tokeniza la expresión.
            for(int index = inicio; index < expresion.length(); index++)
            {
                char token = expresion.charAt(index);

                // Se comprueba que sea el inicio de la variable.
                if(variable.length() == 0)
                {
                    // La variable no es un número en su inicio.
                    if(Character.isDigit(token) == false && esOperador(token) == false)
                    {
                        variable += token;
                    }
                    // Es un número; no es una variable.
                    else
                    {
                        return null;
                    }
                }
                // No es el inicio de la variable.
                else
                {
                    // Es un operando.
                    if(esOperando(token))
                    {
                        variable += token;
                    }
                    // Ya no es operando; la variable ha terminado.
                    else
                    {
                        return variable;
                    }
                }
            }
            return variable;
        }
        // Alguno de los parámetros es inválido.
        else
        {
            return null;
        }
    }

    /**
     * Verifica que el valor dado sea uno operador
     * aritmético.
     * @param token Valor a ser comprobado.
     * @return Regresa <b>true</b> en caso de sí serlo,
     * o <b>false</b> en caso contrario.
     */
    public static boolean esOperador(char token)
    {
        if(token == '^' || token == '*' || token == '/' || token == '+' ||
                token == '-' || token == '(' || token == ')')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Comprueba si la cadena dada está comprendida
     * solamente por números.
     * @param valor Cadena a analizar.
     * @return Regresa <b>true</b> si es número, o
     * <b>false</b> en caso contrario.
     */
    public static boolean esNumero(String valor)
    {
        // El valor es válido.
        if(valor != null)
        {
            // Se tokeniza la expresión.
            for(int index = 0; index < valor.length(); index++)
            {
                char token = valor.charAt(index);
                // El token actual es un número.
                if(!Character.isDigit(token))
                {
                    return false;
                }
            }
            return true;
        }
        // No es un valor válido.
        else
        {
            return false;
        }
    }

    public static Double evaluarExpresionPreFija(String prefija)
    {
        Pila pila = new Pila(prefija.length());

        //1.- Tokenizar.
        for (int posToken = prefija.length() - 1; posToken >= 0; posToken--)
        {
            //1.1.- Obtener el token.
            char token = prefija.charAt(posToken);

            //2.- Si es operando, lo meto a la pila.
            if (esOperando(token))
            {
                if(!pila.push("" + token))
                {
                    return null;
                }
            }
            //3.- Si es operador, saco el tope de la pila como operando dos.
            //    Saco el tope de la pila y es el operando1.
            else
            {
                String operando1 = (String) pila.pop();
                String operando2 = (String) pila.pop();
                if(operando1 == null || operando2 == null)
                {
                    return null;
                }
                //4.- Hago la operación con el token (operador actual) y esos
                //    dos operandos.
                double resultadoParcial = ejecutaOperacion(
                        token, Double.parseDouble(operando1), Double.parseDouble(operando2));
                //5.- Meto el resultado de esa operacion en la pila.
                if(!pila.push(""+resultadoParcial))
                {
                    return null;
                }
            }
        }//6.- Repito hasta que no haya tokens.

        //7.- El resultado de la operación está en el tope, y lo retorno.
        String resultadoCadena = (String) pila.pop();
        if(resultadoCadena != null)
        {
            return Double.parseDouble(resultadoCadena);
        }
        else
        {
            return null;
        }
    }

    /**
     * Convierte una expresión en forma infija a postfija.
     * @param infija Expresión infija a ser convertida.
     * @return Regresa la expresión convetida a postfija.
     */
    public static String infijaAPostFija(String infija)
    {
        Pila pilaOperadores = new Pila(infija.length());
        String postFija = "";

        // 1.- Tokenizar.
        for(int posToken = 0; posToken < infija.length(); posToken++)
        {
            // 2.- Se obtiene el carácter de cada posición de la expresión para evaluarlo.
            char token = infija.charAt(posToken);

            //  3.- Verificamos si el token es un operando.
            if(esOperando(token))
            {
                // 3.1.- Si el token es un operando, se concatena a la
                // expresión postfija resultante.
                postFija += token;
            }
            // 4.- Si el token no es un operando, entonces es un operador.
            else
            {
                // 4.1.- Si la pila de operadores está vacía, se agrega el token (operador)
                // a la pila.
                if (pilaOperadores.vacia())
                {
                    pilaOperadores.push(token);
                }
                // 4.2.- Si el operador1 contenido en el tope de la pila
                // es de menor prioridad al operador2 (token),
                // se agrega el token (operador) a la pila.
                else if(getPrioridadOperador((char)pilaOperadores.verTope()) < getPrioridadOperador(token) ||
                        token == '(' && token != ')')
                {
                    // Se agrega el token a la pila.
                    pilaOperadores.push(token);
                }
                // 4.3.- Si el operador1 (tope de la pila) es de mayor o igual prioridad que el
                // operador2 (token), entonces se extrae el operador1.
                else if(getPrioridadOperador( (char)pilaOperadores.verTope()) >= getPrioridadOperador(token)
                        && token != ')')
                {
                    // 4.3.1 .- Se itera la pila hasta que no haya operadores de mayor o igual prioridad
                    // que el token.
                    while(!pilaOperadores.vacia() &&
                            getPrioridadOperador((char)pilaOperadores.verTope()) >= getPrioridadOperador(token) )
                    {
                        // Se elimina operador1 de la pila.
                        Character operador = (Character) pilaOperadores.pop();
                        // Se concatena a la expresión.
                        postFija += operador;
                    }
                    // Cuando no haya operadores de mayor o igual prioridad se agrega el token a la pila.
                    pilaOperadores.push(token);
                }
                // 4.4.- Si no se cumple ninguna de las condiciones anteriores, entonces
                // se evaluará cuando se encuentre un paréntesis cerrado.
                else if (token == ')')
                {

                    // 4.3.2.- Se itera la pila hasta encontrar el paréntesis cerrado.
                    while((char)pilaOperadores.verTope() != '(')
                    {
                        // Se elimina el operador 1 de la pila.
                        Character operador = (Character) pilaOperadores.pop();
                        // Se concatena a la expresión.
                        postFija += operador;
                    }
                    pilaOperadores.pop();
                }
            }
        }
        // 5.- Todos los operadores que quedaron en la pila se agregan
        // al final.
        while (!pilaOperadores.vacia())
        {
            postFija += (Character) pilaOperadores.pop();
        }
        return postFija;
    }

    /**
     * Convierte una expresión en forma infija a prefija.
     * @param infija Expresión infija a ser convetida.
     * @return Regresa la expresión convetida a prefija.
     */
    public static String infijaAPreFija(String infija)
    {

        Pila pilaOperadores = new Pila(infija.length());
        String preFija = "";

        // 1.- Tokenizar.
        for(int posToken = infija.length() - 1; posToken >= 0; posToken--)
        {
            // 2.- Se obtiene el carácter de cada posición de la expresión para evaluarlo.
            char token = infija.charAt(posToken);

            //  3.- Verificamos si el token es un operando.
            if(esOperando(token))
            {
                // 3.1.- Si el token es un operando, se concatena a la
                // expresión prefija resultante.
                preFija = token + preFija;
            }
            // 4.- Si el token no es un operando, entonces es un operador.
            else
            {
                // 4.1.- Si la pila de operadores está vacía, se agrega el token (operador)
                // a la pila.
                if (pilaOperadores.vacia())
                {
                    pilaOperadores.push(token);
                }
                // 4.2.- Si el operador1 contenido en el tope de la pila
                // es de menor prioridad al operador2 (token),
                // se agrega el token (operador) a la pila.
                else if(getPrioridadOperador((char)pilaOperadores.verTope()) <= getPrioridadOperador(token) ||
                        token == ')' && token != '(')
                {
                    // Se agrega el token a la pila.
                    pilaOperadores.push(token);
                }
                // 4.3.- Si el operador1 (tope de la pila) es de mayor o igual prioridad que el
                // operador2 (token), entonces se extrae el operador1.
                else if(getPrioridadOperador( (char)pilaOperadores.verTope()) > getPrioridadOperador(token)
                        && token != '(')
                {
                    // 4.3.1 .- Se itera la pila hasta que no haya operadores de mayor o igual prioridad
                    // que el token.
                    while(!pilaOperadores.vacia() &&
                            getPrioridadOperador((char)pilaOperadores.verTope()) > getPrioridadOperador(token) )
                    {
                        // Se elimina operador1 de la pila.
                        Character operador = (Character) pilaOperadores.pop();
                        // Se concatena a la expresión.
                        preFija = operador + preFija;
                    }
                    // Cuando no haya operadores de mayor o igual prioridad se agrega el token a la pila.
                    pilaOperadores.push(token);
                }
                // 4.4.- Si no se cumple ninguna de las condiciones anteriores, entonces
                // se evaluará cuando se encuentre un paréntesis cerrado.
                else if (token == '(')
                {

                    // 4.3.2.- Se itera la pila hasta encontrar el paréntesis cerrado.
                    while((char)pilaOperadores.verTope() != ')')
                    {
                        // Se elimina el operador 1 de la pila.
                        Character operador = (Character) pilaOperadores.pop();
                        // Se concatena a la expresión.
                        preFija = operador + preFija;
                    }
                    pilaOperadores.pop();
                }
            }
        }
        // 5.- Todos los operadores que quedaron en la pila se agregan
        // al final.
        while (!pilaOperadores.vacia())
        {
            preFija = (Character) pilaOperadores.pop() + preFija;
        }
        return preFija;
    }

    /**
     * Obtiene la prioridad del operador dado según
     * la jeraquía de los operadores.
     * @param operador Operador a obtener su prioridad.
     * @return
     * Regresa <b>3</b> si el operador es una
     * potencia, que corresponde al nivel más alto de
     * operadores.
     * <p>Regresa <b>2</b> si el operados es una multiplicación
     * o división, correspondiente al nivel intermedio de los operadores.</p>
     * <p>Regresa <b>1</b> si el operador es una suma o resta, que corresponde
     * al nivel más bajo de los operadores.</p>
     * <p>Regresa <b>0</b> si el operador es inválido. </p>
     */
    public static int getPrioridadOperador(char operador)
    {
        // 1.- Si el operador es una potencia es de la jerarquía más alta.
        if (operador == '^')
        {
            return 3;
        }
        // 2.- Si el operador es una multiplicación o división es de la
        // jerarquía intermedia.
        else if (operador == '*' || operador == '/')
        {
            return 2;
        }
        // 3.- Si el operador es una suma o resta es de la jeraquía
        // mas baja.
        else if (operador == '+' || operador == '-')
        {
            return 1;
        }
        // 4.- Si no es ninguno de los valores anteriores, entonces
        // el parámetro dado no es un operador.
        else
        {
            return 0;
        }
    }
}
