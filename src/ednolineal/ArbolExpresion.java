package ednolineal;

import catalogos.NodoDoble;
import edlineal.ListaLigada;
import edlineal.Pila;
import edlineal.PilaLista;
import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;
import matematicas.ExpresionAritmetica;

import java.util.Random;


/**
 * Esta clase implementa el TDA ArbolExpresion.
 * Es una especialización de un ArbolBinario que
 * permite representar una expresión aritmética
 * entre sus nodos.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class ArbolExpresion extends ArbolBinario
{

    /** Guarda los operadores que hay en el Arbol. */
    private ListaLigada operadores;
    /** Guarda los operandos que hay en el Arbol. */
    private ListaLigada operandos;

    /**
     * Define el constructor TDA ArbolExpresion.
     */
    public ArbolExpresion()
    {
        super();
        operadores = new ListaLigada();
        operandos = new ListaLigada();
    }

    /**
     * Define el constructor TDA ArbolExpresion.
     * Crea el ArbolExpresion a partir
     * de una cadena de texto que contiene una
     * expresión matemática.
     * @param expresion Cadena a ser convertida a
     *                  ArbolExpresion.
     */
    public ArbolExpresion(String expresion)
    {
        super();
        operadores = new ListaLigada();
        operandos = new ListaLigada();
        crearArbol(expresion);
    }

    /**
     * Comienza la creación de un ArbolExpresion pidiendo
     * su raíz y posteriormente sus hijos.
     */
    public void crearArbol()
    {
        SalidaEstandar.consola("Dame la raíz del árbol de expresión: ");
        String infoR = EntradaEstandar.consolaCadena();
        if(agregarOperador(infoR) == false)
        {
            agregarOperando(infoR);
        }
        NodoDoble nuevoNodo = new NodoDoble(infoR);
        cantNodos++;
        raiz = nuevoNodo;
        crearArbol(raiz);
    }

    /**
     * Método real que construye el ArbolExpresion
     * a partir de una cadena con una expresión
     * matemática.
     * @param expresion Cadena con la expresión
     *                  matemática a convertir en
     *                  ArbolExpresion.
     */
    private void crearArbol(String expresion)
    {
        // La expresión luce estar priorizada por paréntesis
        if(expresion.charAt(0) == '(')
        {
            PilaLista pilaNodos = new PilaLista();
            PilaLista pilaVariable = new PilaLista();
            NodoDoble nodoActual = new NodoDoble();
            raiz = nodoActual;
            pilaNodos.push(nodoActual);

            // Se tokeniza la expresión.
            for(int index = 1; index < expresion.length(); index++)
            {
                char token = expresion.charAt(index);

                // El token es un paréntesis abierto.
                if(token == '(')
                {
                    NodoDoble nuevoNodo = new NodoDoble();
                    nodoActual.agregarLiga(nuevoNodo);
                    nodoActual = nuevoNodo;
                    pilaNodos.push(nodoActual);
                }
                // El token es un operador.
                else if(ExpresionAritmetica.esOperador(token) && token != ')')
                {
                    nodoActual = (NodoDoble) pilaNodos.pop();
                    nodoActual.setInfo(token);
                    operadores.agregar(token);
                    cantNodos++;
                }
                // El token es un operando.
                else if(ExpresionAritmetica.esOperando(token) && token != ' ')
                {
                    NodoDoble nuevoNodo;
                    // El operando es un número.
                    if(Character.isDigit(token))
                    {
                        nuevoNodo = new NodoDoble(token);
                        nodoActual.agregarLiga(nuevoNodo);
                        operandos.agregar(token);
                    }
                    // Se extrae el operando en curso de la cadena.
                    else
                    {
                        String variable = ExpresionAritmetica.getVariable(expresion, index);
                        // La variable es válida.
                        if(variable != null)
                        {
                            nuevoNodo = new NodoDoble(variable);
                            nodoActual.agregarLiga(nuevoNodo);
                            operandos.agregar(variable);
                            index += variable.length() - 1;
                        }
                    }
                    cantNodos++;
                }
            }
        }
        // La expresión no cumple con la condición de estar
        // encerrada entre paréntesis.
    }

    /**
     * Pide recursivamente al usuario hijos izquierdos y derechos del arbol
     * que se está construyendo.
     * @param subRaiz NodoDoble que está fungiendo actualmente como
     *                subraiz a agregarle opcionalmente un hijo izquierdo
     *                y derecho.
     */
    private void crearArbol(NodoDoble subRaiz)
    {
        SalidaEstandar.consola("¿El nodo '" + subRaiz.getInfo() +
                "' tiene hijo izquierdo [S/N]?");
        String respI = EntradaEstandar.consolaCadena();
        if(respI.equalsIgnoreCase("s"))
        {
            SalidaEstandar.consola("Dame el hijo izquierdo: ");
            String hijoI = EntradaEstandar.consolaCadena();
            // Se agrega hijoI a la lista de operadores u operandos.
            if(agregarOperador(hijoI) == false)
            {
                agregarOperando(hijoI);
            }
            NodoDoble nodoHijoIz = new NodoDoble(hijoI);
            subRaiz.setLigaIzquierda(nodoHijoIz);
            crearArbol(nodoHijoIz);
        }

        SalidaEstandar.consola("¿El nodo '" + subRaiz.getInfo() +
                "' tiene hijo derecho [S/N]?");
        String respD = EntradaEstandar.consolaCadena();
        if(respD.equalsIgnoreCase("s"))
        {

            SalidaEstandar.consola("Dame el hijo derecho: ");
            String hijoD = EntradaEstandar.consolaCadena();
            // Se agrega hijoD a la lista de operadores u operandos.
            if(agregarOperador(hijoD) == false)
            {
                agregarOperando(hijoD);
            }
            NodoDoble nodoHijoD = new NodoDoble(hijoD);
            subRaiz.setLigaDerecha(nodoHijoD);
            crearArbol(nodoHijoD);
        }
    }

    /**
     * Obtiene la lista de operadores que tiene
     * el ArbolExpresion.
     * @return Devuelve la Lista de operadores.
     */
    public ListaLigada getOperadores()
    {
       return operadores;
    }

    /**
     * Obtiene la lista de operandos que tiene
     * el ArbolExpresion
     * @return Devuelve la Lista de operandos.
     */
    public ListaLigada getOperandos()
    {
        return operandos;
    }

    /**
     * Crea una copia del ArbolExpresion sustituyendo
     * las variables y pidiendo su valor real usuario.
     * @return Regresa el nuevo arbol con las variables
     * sustituídas y los demás valores igual que el
     * original.
     */
    public ArbolExpresion reemplazarVariables()
    {
        ArbolExpresion arbol = new ArbolExpresion();
        arbol.raiz = getRaiz();
        reemplazarVariables(raiz, arbol.raiz);
        return arbol;
    }

    /**
     * Construye el ArbolExpresion a partir de
     * una expresión aritmética.
     * @param infija Expresión aritmética en notación
     *               infija a ser convertida en árbol.
     */
    public void infijaAArbol(String infija)
    {
        // Generamos un número aleatorio entre [0-1]
        Random random = new Random();
        int aleatorio = random.nextInt(2);

        // El número es cero: se convierte a prefija y se
        // construye el árbol.
        if(aleatorio == 0)
        {
            String preFija = ExpresionAritmetica.infijaAPreFija(infija);
            preFijaAArbol(preFija);
        }
        // El número es uno: se convierte a postfija y se
        // construye el árbol.
        else if(aleatorio == 1)
        {
            String postFija = ExpresionAritmetica.infijaAPostFija(infija);
            postFijaAArbol(postFija);
        }
    }

    /**
     * De acuerdo al ArbolExpresion construído obtiene
     * el resultado del mismo, haciendo un recorrido
     * en preorden.
     * @return Regresa el resultado de la expresión matemática
     * o null en caso de no poderse resolver.
     */
    public Double evaluarPreOrden()
    {
        // El arbol ya ha sido construído y puede
        // ser evaluado.
        if(raiz != null && cantNodos >= 3)
        {
            Pila pila = new Pila(cantNodos);
            evaluarPreOrden(raiz, pila);
            return Double.parseDouble(pila.pop().toString());
        }
        // Aún no se construye ningún árbol.
        else
        {
            return null;
        }
    }

    /**
     * Recorre el Arbol en preorden para obtener el
     * resultado de la expresión contenida en su interior.
     * @param subRaiz Nodo actual que se está iterando.
     * @param pila Pila que contiene operandos y operadores
     *             para ir evaluando parcialmente la expresión.
     */
    private void evaluarPreOrden(NodoDoble subRaiz, Pila pila)
    {
        // Caso recursivo.
        if(subRaiz != null)
        {
            // Se extrae el contenido del nodo actual.
            String infoNodo = subRaiz.getInfo().toString();

            // El nodo contiene un operando.
            if(ExpresionAritmetica.esOperando(infoNodo))
            {
                pila.push(infoNodo);
            }
            // El nodo contiene un operador.
            else if(ExpresionAritmetica.esOperador(infoNodo.charAt(0)))
            {
                evaluarPreOrden(subRaiz.getLigaIzquierda(), pila);
                evaluarPreOrden(subRaiz.getLigaDerecha(), pila);

                // Evaluación de cada parte de la expresión.
                Double segundoOp = Double.parseDouble(pila.pop().toString());
                Double primerOp = Double.parseDouble(pila.pop().toString());
                Double resultado = ExpresionAritmetica.ejecutaOperacion(
                        infoNodo.charAt(0), primerOp, segundoOp);
                pila.push(String.valueOf(resultado));
            }
        }
    }

    /**
     * De acuerdo al ArbolExpresion construído obtiene
     * el resultado del mismo, haciendo un recorrido
     * en postorden.
     * @return Regresa el resultado de la expresión matemática
     * o null en caso de no poderse resolver.
     */
    public Double evaluarPostOrden()
    {
        // El arbol ya ha sido construído y puede
        // ser evaluado.
        if(raiz != null && cantNodos >= 3)
        {
            Pila pila = new Pila(cantNodos);
            evaluarPostOrden(raiz, pila);
            return Double.parseDouble(pila.pop().toString());
        }
        // Aún no se construye ningún árbol.
        else
        {
            return null;
        }
    }

    /**
     * Recorre el Arbol en postorden para obtener el
     * resultado de la expresión contenida en su interior.
     * @param subRaiz Nodo actual que se está iterando.
     * @param operandos Pila que contiene los operandos
     *                  a evaluar.
     */
    private void evaluarPostOrden(NodoDoble subRaiz, Pila pOperandos)
    {
        // Caso recursivo.
        if(subRaiz != null)
        {
            evaluarPostOrden(subRaiz.getLigaIzquierda(), pOperandos);
            evaluarPostOrden(subRaiz.getLigaDerecha(), pOperandos);

            // Se extrae la información del nodo actual.
            String infoNodo = subRaiz.getInfo().toString();

            // El nodo contiene un operando.
            if(ExpresionAritmetica.esOperando(infoNodo))
            {
                pOperandos.push(infoNodo);
            }
            // El nodo contiene un operador.
            else if(ExpresionAritmetica.esOperador(infoNodo.charAt(0)))
            {
                // Evaluación de cada parte de la expresión.
                Double segundoOp = Double.parseDouble(pOperandos.pop().toString());
                Double primerOp = Double.parseDouble(pOperandos.pop().toString());
                Double resultado = ExpresionAritmetica.ejecutaOperacion
                        (infoNodo.charAt(0), primerOp, segundoOp);
                pOperandos.push(resultado);
            }
        }
        // Caso base implícito.
    }

     /**
     * Construye el ArbolExpresion a partir de una
     * expresión aritmética escrita en notación postfija.
     * @param postFija Expresión aritmética escrita
     *                en postfija.
     * @return Regresa <b>true</b> en caso de haber
     * creado exitosamente el árbol, o <b>false</b>
     * en caso contrario.
     */
    private boolean postFijaAArbol(String postFija)
    {
        Pila pila = new Pila(postFija.length());

        // 1.- Recibo infija y se convirtió en postfija
        // 2.- Tokenizar la cadena en postfija.
        for(int posicion = 0; posicion < postFija.length(); posicion++)
        {
            char token=postFija.charAt(posicion);
            // 2.1 Si es operando el token, crear nodo con la info del token y meterlo en la pila.
            if(ExpresionAritmetica.esOperando(token))
            {
                cantNodos++;
                operandos.agregar(token);
                NodoDoble nodoNuevo = new NodoDoble(token);
                pila.push(nodoNuevo);
            }
            else
            {
                // Cuando es operador
                // 2.2 si es operador, creamos nodo con el info del token
                // y sacamos dos nodos de la pila y los ligamos al nodo nuevo
                // el nodo nuevo lo metemos en la pila.
                cantNodos++;
                operadores.agregar(token);
                NodoDoble nodoDer=(NodoDoble) pila.pop(); //operando dos
                NodoDoble nodoIzq=(NodoDoble) pila.pop(); //operando uno
                NodoDoble nodoNuevo=new NodoDoble(token); //operador
                nodoNuevo.setLigaIzquierda(nodoIzq);
                nodoNuevo.setLigaDerecha(nodoDer);
                pila.push(nodoNuevo);
            }
        }  // Fin del ciclo de tokenizaciòn
        // Fuera del ciclo
        // 3.- El nodo de la pila en el tope es la raiz.
        NodoDoble nodoRaiz = (NodoDoble)pila.pop();
        if (nodoRaiz != null)
        {
            raiz = nodoRaiz;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Construye el ArbolExpresion a partir de una
     * expresión aritmética escrita en notación prefija.
     * @param prefija Expresión aritmética escrita
     *                en prefija.
     * @return Regresa <b>true</b> en caso de haber
     * creado exitosamente el árbol, o <b>false</b>
     * en caso contrario.
     */
    private boolean preFijaAArbol(String prefija)
    {
        Pila pila = new Pila(prefija.length());

        // Tokenizamos la expresión.
        for(int index = prefija.length() - 1; index >= 0; index--)
        {
            char token = prefija.charAt(index);

            // El token es un operando.
            if(ExpresionAritmetica.esOperando(token))
            {
                cantNodos++;
                operandos.agregar(token);
                pila.push(new NodoDoble(token));
            }
            // El token es un operador.
            else if(ExpresionAritmetica.esOperador(token))
            {
                cantNodos++;
                operadores.agregar(token);
                // Se crea el nodo de la operación.
                NodoDoble nodoDoble = new NodoDoble(token);
                // Se extrae el opearando izquierdo.
                nodoDoble.setLigaIzquierda((NodoDoble) pila.pop());
                // Se extrae el operando derecho.
                nodoDoble.setLigaDerecha((NodoDoble)pila.pop());
                pila.push(nodoDoble);
            }
        }
        // El último NodoDoble que queda en la Pila es
        // la raíz del árbol.
        NodoDoble nodoRaiz = (NodoDoble) pila.pop();
        // La raíz es válida.
        if(nodoRaiz != null)
        {
            raiz = nodoRaiz;
            return true;
        }
        // La raíz es inválida.
        else
        {
            return false;
        }
    }

    /**
     * Método recursivo que recorre los nodos del ArbolExpresion
     * original y susituye las variables.
     * @param subRaizOrg Subraíz actual del ArbolExpresion original.
     * @param subRaizCopia Subraíz actual del ArbolExpresion nuevo.
     * @return
     */
    private void reemplazarVariables(NodoDoble subRaizOrg, NodoDoble subRaizCopia)
    {
        // La subraíz es válida.
        if(subRaizOrg != null)
        {
            // El valor de la subraíz es una varible.
            if(esVariable(subRaizOrg.getInfo().toString()))
            {
                // Se reemplaza la variable con un valor dado por el usuario.
                SalidaEstandar.consola("\nIndica el valor de la variable '" +
                        subRaizOrg.getInfo().toString() + "': ");
                String valor = EntradaEstandar.consolaCadena();
                subRaizCopia.setInfo(valor);
            }
            // El valor de la subraíz no es una varaible.
            else
            {
                subRaizCopia.setInfo(subRaizOrg.getInfo());
            }
            // La subraíz original tiene hijo izquierdo.
            if(subRaizOrg.getLigaIzquierda() != null)
            {
                subRaizCopia.setLigaIzquierda(subRaizOrg.getLigaIzquierda().clonar());
            }
            // La subraíz original tiene hijo derecho.
            if(subRaizOrg.getLigaDerecha() != null)
            {
                subRaizCopia.setLigaDerecha(subRaizOrg.getLigaDerecha().clonar());
            }
            reemplazarVariables(subRaizOrg.getLigaIzquierda(), subRaizCopia.getLigaIzquierda());
            reemplazarVariables(subRaizOrg.getLigaDerecha(), subRaizCopia.getLigaDerecha());
        }
    }

    /**
     * Comprueba que un valor dado sea
     * un operador aritmético y lo agrega
     * a la lista de operadores.
     * @param operador Valor a ser comprobado.
     * @return Regresa <b>true</b> si lo pudo
     * agregar, o <b>false</b> en caso contrario.
     */
    private boolean agregarOperador(String operador)
    {
        // El valor es un operador
        if(operador.length() == 1 &&
        ExpresionAritmetica.esOperador(operador.charAt(0)))
        {
            operadores.agregar(operador);
            return true;
        }
        // El valor no es un operador.
        else
        {
            return false;
        }
    }

    /**
     * Comprueba que un valor dado sea un operando
     * y lo agrega a la lista de operandos.
     * @param operando Valor a ser comprbado.
     * @return Regresa <b>true</b> si lo pudo agregar,
     * o <b>false</b> en caso contrario.
     */
    private boolean agregarOperando(String operando)
    {
        // El valor es valido.
        if(operando != null)
        {
            // El valor es un operando de más de un caracter.
            if(operando.length() > 1)
            {
                operandos.agregar(operando);
                return true;
            }
            // El valor es un operando de un caracter.
            if(operando.length() == 1
            && ExpresionAritmetica.esOperando(operando.charAt(0)))
            {
                operandos.agregar(operando);
                return true;
            }
            // El valor no es un operando.
            else
            {
                return false;
            }
        }
        // El valor es inválido.
        else
        {
            return false;
        }
    }

    /**
     * Comprueba si la expresion dada corresponde
     * a una variable.
     * @param expresion Cadena a ser comprobada.
     * @return Regresa <b>true</b> en caso de ser
     * una variable, o <b>false</b> en caso contrario.
     */
    private boolean esVariable(String expresion)
    {
        // Sí es una variable.
        if(operandos.buscar(expresion) != null
        && !ExpresionAritmetica.esNumero(expresion))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
