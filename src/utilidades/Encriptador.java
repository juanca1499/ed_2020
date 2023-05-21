package utilidades;

import edlineal.Arreglo;
import edlineal.Pila;
import edlineal.VectorNum;
import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;

/**
 * Esta clase implementa el TDA Encriptador.
 * @author Clase de estructura de datos.
 * @version 1.0
 */

public class Encriptador
{

    /**
     * Según el mensaje dado y la cantidad de agrupaciones a insertarse,
     * pregunta por la posición y longitud de los paréntesis y realiza
     * la encriptación del mensaje.
     * @param mensaje Mensaje a ser encriptado.
     * @param cantAgrupaciones Cantidad de secciones del mensaje
     *                         a ser encriptadas.
     * @return Regresa el mensaje encriptado, o null en caso no haber
     * podido realizar el proceso.
     */
    public static String encriptar(String mensaje, int cantAgrupaciones)
    {
        // 1.- Inicializamos el mensaje a encriptarse.
        String msjEncriptado = "";
        // 2.- Inicializamos los vectores paralelos para almacenar en donde se insertarán los paréntesis.
        VectorNum vectorPosParAbiertos = new VectorNum(cantAgrupaciones);
        VectorNum vectorPosParCerrados = new VectorNum(cantAgrupaciones);

        // 3.- Según la cantidad de agrupaciones a insertarse,
        // Se piden sus posiciones y longitud y se agregan a los
        // vectores paralelos correspondientes.
        for(int contAgrupaciones = 0; contAgrupaciones < cantAgrupaciones; contAgrupaciones++)
        {
            Integer posUnoParentesis = pedirPosicion(contAgrupaciones + 1);
            Integer posDosParentesis = pedirLongitudParentesis();
            posUnoParentesis--;
            posDosParentesis += posUnoParentesis;
            vectorPosParAbiertos.agregar(posUnoParentesis);
            vectorPosParCerrados.agregar(posDosParentesis);
        }

        // 4.- Si las posiciones de los paréntesis indicadas son inválidas, no se realiza
        // la encriptación.
        if(!validarPosParentesis(vectorPosParAbiertos, vectorPosParAbiertos, mensaje.length()))
        {
            return null;
        }

        int contParentesis = 0;
        // 5.- Se itera el mensaje original para ser encriptado.
        for(Integer posToken = 0; posToken < mensaje.length(); posToken++)
        {
            // 6.- Se extrae el caracter correspondiente a la posición actual del mensaje.
            char token = mensaje.charAt(posToken);

            // 7.- Se entra si la posición del mensaje corresponde a donde se indicó
            // que se insertara una encriptación (paréntesis de apertura).
            if(posToken == vectorPosParAbiertos.getElemento(contParentesis))
            {
                // 7.1.- Se calcula el tamaño de la pila.
                Integer longPila = (posToken + (Integer) vectorPosParCerrados.getElemento(contParentesis));
                Pila pilaEncriptacion = new Pila(longPila);

                // 7.2.- Se agrega el paréntesis de apertura al mensaje encriptado.
                msjEncriptado += '(';
                // 7.3.- Se almacenan los caracteres que englobará la agrupación.
                while (posToken != vectorPosParCerrados.getElemento(contParentesis))
                {
                    token = mensaje.charAt(posToken);
                    pilaEncriptacion.push(token);
                    posToken++;
                }
                // 7.4.- Se vacía la pila invirtiendo los caracteres de la agrupación.
                while (pilaEncriptacion.verTope() != null)
                {
                    msjEncriptado += pilaEncriptacion.pop().toString();
                }
                // 7.5.- Se suma el conteo de agrupaciones ya insertadas en el mensaje.
                contParentesis++;
                // 7.6.- Se agrega el paréntesis de cierre y la letra que sigue después
                // de la agrupación.
                msjEncriptado += ')';
                msjEncriptado += mensaje.charAt(posToken);
            }
            else
            {
                //8.- Si no corresponde a una posición donde
                // irá una agrupación, entonces simplemente se concatena el caracter.
                msjEncriptado += token;
            }
        }
        return msjEncriptado;
    }

    /**
     * Pide al usuario ingresar la posición donde comenzará
     * la agrupación de encriptación.
     * @param numeroParentesis Número de paréntesis que se está
     *                         preguntando de los que fueron solicitados
     *                         agregar al mensaje encriptado.
     * @return Regresa el número introducido por el usuario.
     */
    private static int pedirPosicion(int numeroParentesis)
    {
        SalidaEstandar.consola("\nIndica la posición del paréntesis " + numeroParentesis + ": ");
        return Integer.parseInt(EntradaEstandar.consolaCadena());
    }

    /**
     * Pide al usuario ingresar la longitud de la agrupación
     * que contendrá la encriptación en el mensaje.
     * @return Regresa el número introducido por el usuario.
     */
    private static int pedirLongitudParentesis()
    {
        SalidaEstandar.consola("\nIndica la longitud del paréntesis: ");
        return Integer.parseInt(EntradaEstandar.consolaCadena());
    }

    /**
     * Verifica que las posiciones de los paréntesis indicadas
     * a insertar en el mensaje encriptado sean válidas y no
     * colisionen entre sí.
     * @param parAbiertos Vector paralelo con las posiciones de los paréntesis
     *                    abiertos indicados a insertar.
     * @param parCerrados Vector paralelo con las posiciones de los paréntesis
     *      *             cerrados indicados a insertar.
     * @param longMsj Longitud del mensaje a encriptar.
     * @return Regresa <b>true</b> si todas las posiciones de los paréntesis
     * son válidas, o <b>false</b> en caso contrario.
     */
    private static boolean validarPosParentesis(VectorNum parAbiertos, VectorNum parCerrados, int longMsj)
    {
        // 1.- Se iteran los elementos de los vectores.
        for(int index = 0; index < parAbiertos.getLongitud(); index++)
        {
            // 2.- Se obtiene el elemento de cada vector (posición del mensaje a insertar un paréntesis).
            Integer posAbiertos = (Integer)parAbiertos.getElemento(index);
            Integer posCerrados = (Integer)parCerrados.getElemento(index);

            // 3.- Se valida si alguna de las posiciones está por fuera de los límites del mensaje.
            if(posAbiertos < 0 || posAbiertos > (longMsj - 1) || posCerrados < 0 || posCerrados > (longMsj - 1))
            {
                return false;
            }
            // 4.- Se valida que no se haya indicado una posición de cierre menor a la de una de apertura.
            else if(posCerrados < posCerrados)
            {
                return false;
            }
            // 5.- Se valida que no haya colisiones entre las posiciones de los paréntesis de apertura y cierre.
            else if(index != 0 && !(posAbiertos > (Integer) parCerrados.getElemento(index - 1)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Según el mensaje dado, realiza el proceso de desencriptación del
     * mismo, eliminando las agrupaciones y restaurando el mensaje a su
     * valor original antes de ser encriptado.
     * @param mensajeEncriptado Mensaje a ser desencriptado.
     * @return Regresa el mensaje desencriptado.
     */
    public static String desencriptar(String mensajeEncriptado)
    {
        // 1.- Se inicializa el mensaje resultante de la desencriptación.
        String msjDesencriptado = "";
        // 2.- Se obtienen las posiciones en el mensaje de los paréntesis cerrados.
        VectorNum vectorPosParCerrados = getPosicionesParentesisCerrados(mensajeEncriptado);

        int contParentesis = 0;
        // 3.- Se itera cada uno de los caracteres del mensaje.
        for(int posToken = 0; posToken < mensajeEncriptado.length(); posToken++)
        {
            // 4.- Se extrae el caracter de la posición actual del mensaje encriptado.
            char token = mensajeEncriptado.charAt(posToken);

            // 5.- Si el caracter corresponde a un paréntesis de apertura, se entra.
            if(token == '(')
            {
                // 5.1.- Se calcula la longitud de la pila y se inizaliza.
                Integer longPila = posToken + (Integer) vectorPosParCerrados.getElemento(contParentesis);
                Pila pilaDesencriptacion = new Pila(longPila);
                // 5.2.- Se aumenta la posición del token para evitar el paréntesis.
                posToken++;
                // 5.3.- Se aumenta el contador de paréntesis desencriptados.
                contParentesis++;

                // 5.4.- Se almacenan los caracteres que se encontraban encriptados.
                while (mensajeEncriptado.charAt(posToken) != ')')
                {
                    token = mensajeEncriptado.charAt(posToken);
                    pilaDesencriptacion.push(token);
                    posToken++;
                }
                // 5.5.- Se devuelven los caracteres encriptados a su posición original
                // y se anexan al mensaje desencriptado.
                while (pilaDesencriptacion.verTope() != null)
                {
                    msjDesencriptado += pilaDesencriptacion.pop().toString();
                }
            }
            else
            {
                //6.- Si el caracter no corresponde a una agrupación,
                // se anexa al mensaje desencriptado.
                msjDesencriptado += token;
            }
        }
        return msjDesencriptado;
    }

    /**
     * Según el mensaje (encrpitado) dado realiza la búsqueda
     * de las posiciones del mismo donde hay paréntesis de cierre y
     * las guarda.
     * @param mensaje Texto a ser analizado.
     * @return Regresa un <b>VectorNum</b> con las posiciones
     * de cierre contenidas en el mensaje encriptado.
     */
    private static VectorNum getPosicionesParentesisCerrados(String mensaje)
    {
        // 1.- Se inicializa el vector de posicioens.
        VectorNum posiciones = new VectorNum(mensaje.length());

        // 2.- Se recorre el mensaje.
        for(int posToken = 0; posToken < mensaje.length(); posToken++)
        {
            // 3.- Se extrae cada caracter del mensaje.
            char token = mensaje.charAt(posToken);

            // 4.- Si el caracter es un paréntesis de cierre,
            // se agrega la posición de este en el mensaje en el vector.
            if(token == ')')
            {
                posiciones.agregar(posToken);
            }
        }
        return posiciones;
    }
}
