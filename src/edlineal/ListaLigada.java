package edlineal;

import catalogos.Nodo;
import entradasalida.SalidaEstandar;
import utilidades.Comparador;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase implementa el TDA ListaLigada.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ListaLigada implements Lista
{
    /** Guarda el primer Nodo que compone la Listaligada. */
    protected Nodo primero;
    /** Guarda el último Nodo que compone la ListaLigada.  */
    protected Nodo ultimo;
    /** Guarda la cantidad de elementos que están contenidos en la Lista. */
    private int size;
    /** Guarda el nodo de la Lista en que se están iterando los elementos. */
    private Nodo nodoActualIterador;

    /**
     * Define el constructor de TDA ListaLigada.
     */
    public ListaLigada()
    {
        primero = null;
        ultimo = null;
        size = 0;
        nodoActualIterador = primero;
    }

    /**
     * Agrega al inicio de la lista el Nuevo nodo dado como
     * parámetro.
     * @param valor Nodo a ser insertado en el inicio.
     * @return Regresa <b>true</b> en caso de haber podido
     * insertar el Nodo en la lista, o <b>false</b> en caso
     * contrario.
     */
    public boolean insertarInicio(Object valor)
    {
        Nodo nuevo = new Nodo(valor);

        // No hubo problemas.
        if(nuevo != null)
        {
            // Está vacía.
            if(primero == null)
            {
                primero = nuevo;
                ultimo = nuevo;
            }
            // Ya hay algo.
            else
            {
                nuevo.setLigaDerecha(primero);
                primero = nuevo;
            }
            size++;
            return true;
        }
        // No hay espacio o hubo error.
        else
        {
            return false;
        }
    }

    /**
     * Remueve el elemento que está al principio de
     * la Lista.
     * @return Regresa el elemento que estaba al inicio de la
     * Lista, o Nulo si no había nada.
     */
    public Object eliminarInicio()
    {
        // Hay algo
        if(!vacia())
        {
            Object contenido = primero.getInfo();

            // El único que hay en la lista.
            if(primero == ultimo)
            {
                primero = null;
                ultimo = null;
            }
            // Hay varios.
            else
            {
                // El nuevo primero es el segundo.
                primero = primero.getLigaDerecha();
            }
            size--;
            return contenido;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean llena()
    {
        Nodo nuevo = new Nodo(0);
        // Si el nodo que se trató de crear es Nulo, es porque
        // la memoria está llena.
        if(nuevo == null)
        {
            return true;
        }
        // Si no es porque aún hay memoria.
        else
        {
            return false;
        }
    }

    @Override
    public boolean vacia()
    {
        if(primero == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Insertar al final.
    @Override
    public boolean agregar(Object valor)
    {
        Nodo nuevo = new Nodo(valor);

        // Tiene una dirección válida.
        if(nuevo != null)
        {
            // Vacía.
            if(ultimo == null)
            {
                primero = nuevo;
                ultimo = nuevo;
            }
            // Ya tiene algo.
            else
            {
                ultimo.setLigaDerecha(nuevo);
                ultimo = nuevo;
            }
            size++;
            return true;
        }
        // Hubo error o no hay espacio.
        else
        {
            return false;
        }
    }

    @Override
    public void imprimirR()
    {
        Pila pilaDatos = getPilaElementos();
        Nodo temporal = primero;

        SalidaEstandar.consola("Null");
        // Se sacan los elementos de la Pila consiguiendo sacarlos en órden inverso.
        while (pilaDatos.verTope() != null)
        {
            SalidaEstandar.consola(" <-- " + pilaDatos.pop());
        }
    }

    /**
     * Muestra los elementos de la Lista en la salida estándar
     * de manera inversa en forma recurisva.
     */
    public void imprimirRRecursivo()
    {
        Pila pilaDatos = getPilaElementosR();
        SalidaEstandar.consola("Null");
        imprimirRRecursivo(pilaDatos);
    }

    /**
     * Función real que muestra los elementos de la Lista
     * en la salida estándar de forma recurisva.
     * @param contador Posición de los datos que se está imprimiendo.
     */
    private void imprimirRRecursivo(Pila pilaDatos)
    {
        // Caso recursivo.
       if(pilaDatos.verTope() != null)
       {
           SalidaEstandar.consola(" <-- " + pilaDatos.pop());
           imprimirRRecursivo(pilaDatos);
       }
       // Caso base escondido.
    }

    @Override
    public Object buscar(Object valor)
    {
        Nodo temporal = primero;
        while (temporal != null &&
                !temporal.getInfo().toString().equalsIgnoreCase(valor.toString()))
        {
            temporal = temporal.getLigaDerecha();
        }
        // Está vacía o no existe.
        if(temporal == null)
        {
            return null;
        }
        // Sí lo encontró.
        else
        {
            return temporal.getInfo();
        }
    }

    /**
     * Localiza un en la Lista un valor en forma recurisva.
     * @param valor Es el elemento a encontrar.
     * @return Regresa el valor si lo encuentra, null en caso contrario.
     */
    public Object buscarR(Object valor)
    {
        return buscarR(valor, primero);
    }

    /**
     * Función real que localiza un valor en la Lista en forma recurisva.
     * @param valor Es el elemento a encontrar.
     * @param nodoActual Nodo actual de la lista que se está comparando su
     *                   contenido.
     * @return Regresa el valor si lo encuentra, null en caso contrario.
     */
    private Object buscarR(Object valor, Nodo nodoActual)
    {
        // Caso base1.
        if(nodoActual == null)
        {
            return null;
        }
        // Caso base2.
        else if(Comparador.comparar(nodoActual, valor) == 0)
        {
            return nodoActual.getInfo();
        }
        // Caso recursivo.
        else
        {
            return buscarR(valor, nodoActual.getLigaDerecha());
        }
    }

    @Override
    public Object eliminar(Object valor)
    {
        if(!vacia())
        {
            Object contenido = null;
            Nodo actual = primero;
            Nodo atras = primero;

            while (actual != null && Comparador.comparar(actual, valor) != 0)
            {
                atras = actual;
                actual = actual.getLigaDerecha();
            }
            if (actual == null)
            {
                return null;
            }
            // Cuando encontró algo.
            else
            {
                contenido = actual.getInfo();
                if(primero == ultimo)
                {
                    primero = null;
                    ultimo = null;
                }
                else if(actual == ultimo)
                {
                    atras.setLigaDerecha(null);
                    ultimo = atras;
                }
                else if(actual == primero)
                {
                    primero = primero.getLigaDerecha();
                }
                else
                {
                    atras.setLigaDerecha(actual.getLigaDerecha());
                }
            }
            size--;
            return contenido;
        }
        else
        {
            return null;
        }
    }

    /**
     * Suprime el elemento de forma utilizando recursividad.
     * @param valor Es el elemento a borrar.
     * @return Regresa el objeto de la posicion encontrada o null si no
     * se encuentra.
     */
    public Object eliminarR(Object valor)
    {
        // Sí tiene elementos.
        if(!vacia())
        {
            Pila nodos = buscarNodosR(valor);
            Nodo actual = (Nodo) nodos.pop();
            Nodo anterior = (Nodo) nodos.pop();

            // No encontró el valor a eliminar.
            if(actual == null)
            {
                return null;
            }
            // Sí se encontró un nodo a eliminar.
            // Cuando encontró algo.
            else
            {
                Object contenido = actual.getInfo();

                // Había solo un elemento en la Lista.
                if(primero == ultimo)
                {
                    primero = null;
                    ultimo = null;
                }
                // El elemento eliminado fue el último.
                else if(actual == ultimo)
                {
                    anterior.setLigaDerecha(null);
                    ultimo = anterior;
                }
                // El elemento eliminado fue el primero.
                else if(actual == primero)
                {
                    primero = primero.getLigaDerecha();
                }
                // El elemento eliminado está en el medio.
                else
                {
                    anterior.setLigaDerecha(actual.getLigaDerecha());
                }
                size--;
                return contenido;
            }
        }
        // Está vacía.
        else
        {
            return null;
        }
    }

    @Override
    public boolean setElemento(Object valorV, Object valorN, int numOcurrencias) {
        return false;
    }

    @Override
    public Arreglo buscarOcurrencias(Object valor) {
        return null;
    }

    @Override
    public void vaciar()
    {
        primero = null;
        ultimo = null;
        size = 0;
    }

    @Override
    public boolean agregarArreglo(Arreglo arreglo2) {
        return false;
    }

    @Override
    public void invertir() {

    }

    @Override
    public int contar(Object valor) {
        return 0;
    }

    @Override
    public Object eliminarTope()
    {
        // Hay algo.
        if(!vacia())
        {
            Object contenido = ultimo.getInfo();
            // Sólo hay uno en la Lista.
            if(primero == ultimo)
            {
                primero = null;
                ultimo = null;
            }
            // Hay más de uno en la Lista.
            else
            {
                Nodo penultimo = primero;
                while (penultimo.getLigaDerecha() != ultimo)
                {
                    penultimo = penultimo.getLigaDerecha();
                }
                penultimo.setLigaDerecha(null);
                ultimo = penultimo;
            }
            size--;
            return contenido;
        }
        // La lista está vacía.
        else
        {
            return null;
        }
    }


    /**
     * Remueve el último elemento contenido en la lista de forma recursiva.
     * @return Regresa el elemento que fue eliminado.
     */
    public Object eliminarTopeR()
    {
        // Hay algo.
        if(!vacia())
        {
            Object contenido = ultimo.getInfo();

            // Hay un elemento en la Lista.
            if(primero == ultimo)
            {
                primero = null;
                ultimo = null;
            }
            // Hay más de un elemento.
            else
            {
                reacomodarTope(primero);
            }
            size--;
            return contenido;
        }
        // La lista está vacía.
        else
        {
            return null;
        }
    }

    /**
     * Recorre el tope de la Lista una posición atrás y elimina.
     * el Nodo junto con su contenido que estaba en la última posición.
     * @param actual Nodo en curso de la iteración de la Lista.
     */
    public void reacomodarTope(Nodo actual)
    {
        // Caso base.
        if(actual.getLigaDerecha() == ultimo)
        {
            actual.setLigaDerecha(null);
            ultimo = actual;
        }
        // Caso recursivo.
        else
        {
            reacomodarTope(actual.getLigaDerecha());
        }
    }

    @Override
    public void imprimir()
    {
        Nodo temporal = primero;
        while (temporal != null)
        {
            SalidaEstandar.consola(temporal.getInfo().toString() + " --> ");
            temporal = temporal.getLigaDerecha();
        }
        SalidaEstandar.consola("Null");
    }

    /**
     * Realiza la impresión de los elementos de la lista
     * partiendo de la primera posición a la última de
     * forma recursiva.
     */
    public void imprimirRecursivo()
    {
        imprimirRecursivo(primero);
        SalidaEstandar.consola("Null");
    }

     /**
     * Realiza la impresión de los elementos de la lista
     * partiendo de la primera posición a la última de
     * forma recursiva.
     * @param actual Nodo que se está iterando en curso para
      *               imprimir su contenido.
     */
    public void imprimirRecursivo(Nodo actual)
    {
        // Caso recursivo.
        if(actual != null)
        {
            SalidaEstandar.consola(actual.getInfo().toString() + " --> ");
            imprimirRecursivo(actual.getLigaDerecha());
        }
        // Caso base oculto.
    }

    @Override
    public void rellenar(Object valor, int cuantos) {

    }

    @Override
    public Object getTope()
    {
        if(ultimo != null)
        {
            return ultimo.getInfo();
        }
        else
        {
            return null;
        }
    }

    /**
     * Agrega los Nodos de la Lista a una Pila.
     * @return Regresa la Pila con los elementos agregados.
     */
    private Pila getPilaElementos()
    {
        Pila pilaDatos = new Pila(size);

        Nodo temporal = primero;
        // Mientras temporal sea diferente de Null, se agregan los elementos a la Pila.
        while (temporal != null)
        {
            pilaDatos.push(temporal);
            temporal = temporal.getLigaDerecha();
        }
        return pilaDatos;
    }

    /**
     * Agrega los Nodos de la Lista a una Pila de forma recursiva.
     * @return Regresa la Pila con los elementos agregados.
     */
    private Pila getPilaElementosR()
    {
        Pila pilaDatos = new Pila(size);
        return getPilaElementosR(pilaDatos, primero);
    }

    /**
     * Función real que agrega los Nodos de la Lista a una Pila de forma recursiva.
     * @return Regresa la Pila con los elementos agregados.
     */
    private Pila getPilaElementosR(Pila pila, Nodo nodoActual)
    {
        // Caso base.
        if(nodoActual == null)
        {
            return pila;
        }
        // Caso recursivo.
        else
        {
            pila.push(nodoActual);
            return getPilaElementosR(pila, nodoActual.getLigaDerecha());
        }
    }

    /**
     * Inicializa el iterador de los elementos de la
     * Lista.
     */
    public void inicializaIterador()
    {
        nodoActualIterador = primero;
    }

    /**
     * Verifica si el nodo iterador está en una
     * posición válida.
     * @return Regresa <b>true</b> en caso de estar
     * en una posición válida, y <b>false</b> en caso
     * contrario.
     */
    public boolean hayNodoSiguiente()
    {
        if(nodoActualIterador != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Extrae el contenido del Nodo actual que está iterando
     * la Lista y después pasa la iteración hacia el siguiente Nodo.
     * @return Regresa el contenido del Nodo que está iterando.
     */
    public Object obtenerSiguiente()
    {
        if(hayNodoSiguiente())
        {
            Object contenido = nodoActualIterador.getInfo();
            nodoActualIterador = nodoActualIterador.getLigaDerecha();
            return contenido;
        }
        else
        {
            return null;
        }
    }


    /**
     * Recupera los datos contenidos en la Lista para transformarlos
     * a un Arreglo de forma recursiva.
     * @return Regresa el Arreglo con los elementos de la Lista.
     */
    public Arreglo listaAArregloRR()
    {
        Arreglo arregloDatos = new Arreglo(size);
        listaAArregloRR(arregloDatos, primero);
        return arregloDatos;
    }

    /**
     * Función real que recupera los datos contenidos en la Lista para transformarlos
     * a un Arreglo de forma recursiva.
     * @param arreglo Elementos copiados de la Lista.
     * @param nodo Nodo actual de donde se está copiando la
     *             información.
     */
    private void listaAArregloRR(Arreglo arreglo, Nodo nodo)
    {
        // Caso recursivo.
        if(nodo != null)
        {
            arreglo.agregar(nodo.getInfo());
            listaAArregloRR(arreglo, nodo.getLigaDerecha());
        }
        // Caso base oculto.
    }

    /**
     * Busca de manera recursiva entre los Nodos de la lista el que
     * contenga un valor y su nodo anterior.
     * @param valor Contenido a ser a ser buscado.
     * @return Regresa una Pila que en su tope contiene el Nodo que
     * tiene el valor buscado y en el fondo de la Pila está el Nodo que
     * le antecede.
     */
    private Pila buscarNodosR(Object valor)
    {
        Pila nodos = new Pila(2);
        return buscarNodosR(valor, primero, primero, nodos);
    }

    /**
     * Función real que busca de manera recursiva entre los Nodos
     * de la lista el que contenga un valor y su nodo anterior.
     * @param valor Contenido a ser buscado.
     * @param nodoAnterior Nodo anterior que se está iterando.
     * @param nodoActual Nodo actual que se está iterando.
     * @param nodos Pila para guardar el Nodo que contiene el valor
     *              buscado y su antecesor.
     * @return Regresa una Pila que en su tope contiene el Nodo que
     * tiene el valor buscado y en el fondo de la Pila está el Nodo que
     * le antecede.
     */
    private Pila buscarNodosR(Object valor, Nodo nodoAnterior, Nodo nodoActual, Pila nodos)
    {
        // Caso base1.
        if(nodoActual == null)
        {
            nodos.push(null);
            nodos.push(null);
            return nodos;
        }
        // Caso base2.
        else if(Comparador.comparar(nodoActual, valor) == 0)
        {
            // La búsqueda terminó en el primer nodo.
            if(nodoAnterior == primero && nodoActual == primero)
            {
                nodos.push(null);
                nodos.push(primero);
                return nodos;
            }
            // La búsqueda terminó en un nodo interno.
            else
            {
                nodos.push(nodoAnterior);
                nodos.push(nodoActual);
                return nodos;
            }
        }
        // Caso recursivo.
        else
        {
            nodoAnterior = nodoActual;
            return buscarNodosR(valor, nodoAnterior, nodoActual.getLigaDerecha(), nodos);
        }
    }

    /**
     * Obtiene la cantidad de elementos que tiene
     * la Lista.
     * @return Regresa el número de elementos de la Lista.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Obtiene el primer elemento insertado en la Lista.
     * @return Regresa el objeto que fue agregado
     * primero en caso de existir, o null en caso contrario.
     */
    public Object regresaPrimero()
    {
        if(primero != null)
        {
            return primero.getInfo();
        }
        else
        {
            return null;
        }
    }
}
