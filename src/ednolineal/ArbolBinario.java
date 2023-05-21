package ednolineal;


import catalogos.Nodo;
import catalogos.NodoDoble;
import edlineal.Cola;
import edlineal.ColaLista;
import edlineal.Pila;
import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;


/**
 * Esta clase implementa el TDA ArbolBinario.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0.
 */
public class ArbolBinario
{
    /** Guarda el primer nodo o nodo raíz que compone el ArbolBinario. */
    protected NodoDoble raiz;
    /** Guarda la cantidad de nodos padre que hay en el ArbolBinario. */
    protected int cantPadres;
    /** Guarda la cantidad de nodo hoja que hay en el ArbolBinario. */
    protected int cantHojas;
    /** Guarda la altura que posee el ArbolBinario. */
    protected int altura;
    /** Guarda la cantidad de nodos que posee el ArbolBinario. */
    protected int cantNodos;

    /**
     * Define el constructor TDA ArbolBinario.
     */
    public ArbolBinario()
    {
        raiz = null;
        cantPadres = 0;
        cantHojas = 0;
        altura = 0;
        cantNodos = 0;
    }

    /**
     * Comienza la creación de un ArbolBinario pidiendo
     * su raíz y posteriormente sus hijos.
     */
    public void crearArbol()
    {
        SalidaEstandar.consola("Dame la raíz del árbol: ");
        String infoR = EntradaEstandar.consolaCadena();
        NodoDoble nuevoNodo = new NodoDoble(infoR);
        raiz = nuevoNodo;
        cantNodos++;
        crearArbol(raiz);
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
            NodoDoble nodoHijoIz = new NodoDoble(hijoI);
            subRaiz.setLigaIzquierda(nodoHijoIz);
            cantNodos++;
            crearArbol(nodoHijoIz);
        }

        SalidaEstandar.consola("¿El nodo '" + subRaiz.getInfo() +
                "' tiene hijo derecho [S/N]?");
        String respD = EntradaEstandar.consolaCadena();
        if(respD.equalsIgnoreCase("s"))
        {
            SalidaEstandar.consola("Dame el hijo derecho: ");
            String hijoD = EntradaEstandar.consolaCadena();
            NodoDoble nodoHijoD = new NodoDoble(hijoD);
            subRaiz.setLigaDerecha(nodoHijoD);
            cantNodos++;
            crearArbol(nodoHijoD);
        }
    }

    /**
     * Crea una copia de la raíz del ArbolBinario.
     * @return Regresa la copia de la raíz o null
     * en caso de que aún no se tenga asignada.
     */
    public NodoDoble getRaiz()
    {
        // El árbol tiene raíz.
        if(raiz != null)
        {
            NodoDoble nodoDoble = new NodoDoble(raiz.getInfo());
            nodoDoble.setLigaIzquierda(raiz.getLigaIzquierda());
            nodoDoble.setLigaDerecha(raiz.getLigaDerecha());
            return nodoDoble;
        }
        // El árbol aún no tiene raíz.
        else
        {
            return null;
        }
    }

    /**
     * Obtiene la cantidad de Nodos
     * contenidos en el ArbolBinario.
     * @return Regresa la cantidad de nodos.
     */
    public int getCantNodos()
    {
        return cantNodos;
    }

    /*
        A
      B   C
      Recorrido: raiz, izq, der: ABC.
     */
    /**
     * Imprime el ArbolBinario con un recorrido
     * en preorden.
     */
    public void preOrden()
    {
        preOrden(raiz);
    }

    /**
     * Imprime recursivamente el contenido de cada Nodo
     * del arbol haciendo un recorrido recursivo en
     * preorden del mismo.
     * @param subRaiz Nodo que se está procesando al momento para
     *                imprimir su contenido y el de sus hijos
     *                izquierdo y derecho.
     */
    private void preOrden(NodoDoble subRaiz)
    {
        if(subRaiz != null)
        {
            SalidaEstandar.consola(subRaiz.getInfo().toString() + " ");
            preOrden(subRaiz.getLigaIzquierda());
            preOrden(subRaiz.getLigaDerecha());
        }
    }

    /*
        A
      B   C
      Recorrido: izq, raiz, der: BAC.
     */
    /**
     * Imprime el ArbolBinario con un recorrido
     * en inorden.
     */
    public void inOrden()
    {
        inOrden(raiz);
    }

    /**
     * Imprime recursivamente el contenido de cada Nodo
     * del arbol haciendo un recorrido recursivo en
     * inorden del mismo.
     * @param subRaiz Nodo que se está procesando al momento para
     *                imprimir el contenido de su hijo izquierdo, el
     *                de él mismo y finalmente el de su hijo derecho.
     */
    private void inOrden(NodoDoble subRaiz)
    {
        if(subRaiz != null)
        {
            inOrden(subRaiz.getLigaIzquierda());
            SalidaEstandar.consola(subRaiz.getInfo().toString() + " ");
            inOrden(subRaiz.getLigaDerecha());
        }
    }

    /*
        A
      B   C
      Recorrido: izq, der, raiz: BCA.
     */
    /**
     * Imprime el ArbolBinario con un recorrido
     * en postorden.
     */
    public void postOrden()
    {
        postOrden(raiz);
    }

    /**
     * Imprime recursivamente el contenido de cada Nodo
     * del arbol haciendo un recorrido recursivo en
     * postorden del mismo.
     * @param subRaiz Nodo que se está procesando al momento para
     *                imprimir el contenido de su hijo izquierdo, el
     *                de su hijo derecho y finalmente el de él mismo.
     */
    private void postOrden(NodoDoble subRaiz)
    {
        if(subRaiz != null)
        {
            postOrden(subRaiz.getLigaIzquierda());
            postOrden(subRaiz.getLigaDerecha());
            SalidaEstandar.consola(subRaiz.getInfo().toString() + " ");
        }
    }

    /**
     * Imprime el ArbolBinario con un recorrido
     * por amplitud de izquierda a derecha.
     */
    public void imprimirPorAmplitud()
    {
        // El arbol tiene raíz.
        if(raiz != null)
        {
            Cola cola = new Cola(cantNodos);
            cola.agregar(raiz);
            imprimirPorAmplitud(cola);
        }
    }

    /**
     * Imprime recursivamente cada uno de los nodos
     * del ArbolBinario nivel por nivel de izquierda
     * a derecha.
     * @param cola Almacena los Nodos
     *             a imprimirse.
     */
    private void imprimirPorAmplitud(Cola cola)
    {
        // Caso recursivo
        if(cola.vacia() != true)
        {
            NodoDoble nodo = (NodoDoble) cola.eliminar();
            SalidaEstandar.consola(nodo.getInfo().toString() + " ");
            // Se agrega la liga izquierda si no es nula.
            if(nodo.getLigaIzquierda() != null)
            {
                cola.agregar(nodo.getLigaIzquierda());
            }
            // Se agrega la liga derecha si no es nula.
            if(nodo.getLigaDerecha() != null)
            {
                cola.agregar(nodo.getLigaDerecha());
            }
            imprimirPorAmplitud(cola);
        }
        // Caso base implíctio.
    }

    /**
     * Imprime el ArbolBinario con un recorrido
     * en preorden de derecha a izquierda.
     */
    public void imprimirPostOrdenInverso()
    {
        // El arbol tiene raíz.
        if(raiz != null)
        {
            Pila pila = new Pila(cantNodos);
            pila.push(raiz);
            imprimirPostOrdenInverso(pila);
        }
    }

    /**
     * Imprime recursivamente cada uno de los nodos
     * del ArbolBinario en recorrido de preorden de derecha
     * a izquierda.
     * @param pila Almacena los Nodos
     *             a imprimirse.
     */
    private void imprimirPostOrdenInverso(Pila pila)
    {
        // Caso recursivo
        if(pila.vacia() != true)
        {
            NodoDoble nodo = (NodoDoble) pila.pop();
            SalidaEstandar.consola(nodo.getInfo().toString() + " ");
            // Se agrega la liga izquierda si no es nula.
            if(nodo.getLigaIzquierda() != null)
            {
                pila.push(nodo.getLigaIzquierda());
            }
            // Se agrega la liga derecha si no es nula.
            if(nodo.getLigaDerecha() != null)
            {
                pila.push(nodo.getLigaDerecha());
            }
            imprimirPostOrdenInverso(pila);
        }
        // Caso base implíctio.
    }

    /**
     * Obtiene la cantidad de nodos hoja que hay
     * en el ArbolBinario.
     * @return Devuelve la cantidad de nodos hoja.
     */
    public int getCantNodosHoja()
    {
       cantHojas = 0;
       getCantNodosHoja(raiz);
       return cantHojas;
    }

    /**
     * Recorre recursivamente el ArbolBinario aumentando
     * la cantidad de nodos hoja que hay en el mismo cada
     * que encuentra uno.
     * @param subRaiz Nodo actual de la iteración.
     */
    private void getCantNodosHoja(NodoDoble subRaiz)
    {
        // Subraíz válida (Caso recursivo).
        if(subRaiz != null)
        {
            // El nodo es hoja.
            if(esHoja(subRaiz))
            {
                cantHojas++;
            }
            getCantNodosHoja(subRaiz.getLigaIzquierda());
            getCantNodosHoja(subRaiz.getLigaDerecha());
        }
        // Caso base implícito.
    }

    /**
     * Obtiene la cantidad de nodos padre que hay
     * en el ArbolBinario.
     * @return Devuelve la cantidad de nodos padre.
     */
    public int getCantNodosPadre()
    {
        cantPadres = 0;
        getCantNodosPadre(raiz);
        return cantPadres;
    }

    /**
     * Recorre recursivamente el ArbolBinario aumentando
     * la cantidad de nodos padre que hay en el mismo cada
     * que encuentra uno.
     * @param subRaiz Nodo actual de la iteración.
     */
    private void getCantNodosPadre(NodoDoble subRaiz)
    {
        // Subraíz válida (caso recursivo).
        if(subRaiz != null)
        {
            // El nodo es padre.
            if(esPadre(subRaiz))
            {
                cantPadres++;
            }
            getCantNodosPadre(subRaiz.getLigaIzquierda());
            getCantNodosPadre(subRaiz.getLigaDerecha());
        }
        // Caso base implícito.
    }

    /**
     * Obtiene la altura del ArbolBinario.
     * @return Devuelve la altura.
     */
    public int getAltura()
    {
        altura = 0;
        altura = getAltura(raiz);
        return altura;
    }

    /**
     * Recorre recursivamente el ArbolBinario aumentando la altura
     * cada que encuentra un nuevo nivel.
     * @param subRaiz Nodo actual de la iteración.
     * @return Regresa la altura del árbol.
     */
    private int getAltura(NodoDoble subRaiz)
    {
        // Caso base.
        if(subRaiz == null)
        {
            return 0;
        }
        // Caso recrusivo.
        else
        {
            int alturaIz = getAltura(subRaiz.getLigaIzquierda());
            int alturaDer = getAltura(subRaiz.getLigaDerecha());

            // La altura izquierda es mayor.
            if(alturaIz > alturaDer)
            {
                return alturaIz + 1;
            }
            // La altura derecha es mayor.
            else
            {
                return alturaDer + 1;
            }
        }
    }

    /**
     * Comprueba si el Nodo funge como
     * hoja en el ArbolBinario.
     * @param subRaiz Nodo a comprobar si es hoja.
     * @return Regresa <b>true</b> en caso de ser hoja,
     * o <b>false</b> en caso contrario.
     */
    protected boolean esHoja(NodoDoble subRaiz)
    {
        // El Nodo es válido.
        if(subRaiz != null)
        {
            // La subraíz tiene sus hijos nulos.
            if(subRaiz.getLigaIzquierda() == null
            && subRaiz.getLigaDerecha() == null)
            {
                return true;
            }
            // No tiene sus hijos nulos.
            else
            {
                return false;
            }
        }
        // El Nodo es inválido.
        else
        {
            return false;
        }
    }

    /**
     * Comprueba si el Nodo funge como padre
     * en el ArbolBinario.
     * @param subRaiz Nodo a comprobar si es padre.
     * @return Regresa <b>true</b> en caso de ser padre,
     * o <b>false</b> en caso contrario.
     */
    protected boolean esPadre(NodoDoble subRaiz)
    {
        // El nodo es válido.
        if(subRaiz != null)
        {
            // Por lo menos el Nodo tiene un hijo.
            if(subRaiz.getLigaIzquierda() != null ||
            subRaiz.getLigaIzquierda() != null)
            {
                return true;
            }
            // No tiene hijos.
            else
            {
                return false;
            }
        }
        // Es inválido.
        else
        {
            return false;
        }
    }
}
