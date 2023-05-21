package edlineal;

import catalogos.Nodo;
import catalogos.NodoDoble;
import catalogos.NodoDobleIndexado;
import entradasalida.SalidaEstandar;
import utilidades.Comparador;

/**
 * Esta clase implementa el TDA ListaEnlazadaIndexada.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ListaEnlazadaIndexada implements Lista
{
    /** Guarda el primer Nodo que compone la Lista. */
    private NodoDobleIndexado primero;
    /** Guarda el último Nodo que compone la Lista.  */
    private NodoDobleIndexado ultimo;
    /** Guarda la cantidad de elementos que están contenidos en la Lista. */
    private int size;
    /** Guarda el nodo de la Lista en que se están iterando los elementos. */

    /**
     * Define el constructor de TDA ListaEnlazadaIndexada.
     */
    public ListaEnlazadaIndexada()
    {
        primero = null;
        ultimo = null;
        size = 0;
    }

    /**
     * Indica la cantidad de elementos que componen la Lista.
     * @return Regresa el número de elementos que tiene la Lista.
     */
    public int getSize()
    {
       return size;
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
        if(size == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

     /**
     * Localiza la posición del valor en la Lista.
     * @param index Posición a ser buscada.
     * @return Regresa el valor si lo encuentra, null en caso contrario.
     */
    public Object buscar(int index)
    {
        NodoDobleIndexado nodo = buscarNodoPIndex(index);

        // Sí fue encontrado.
        if(nodo != null)
        {
            return nodo.getInfo();
        }
        // No fue encontrado.
        else
        {
            return null;
        }
    }


    /**
     * Agrega un elemento en la posición indicada.
     * @param index Posición a donde será agregado.
     * @param valor Valor a ser insertado.
     * @return Regresa true en caso de haber podido insertar,
     * o false en caso contrario.
     */
    public boolean insertar(int index, Object valor)
    {
        // El index es negativo.
        if(index < 0)
        {
            index =  index + (size - 1);
        }
        NodoDobleIndexado nuevoNodo = new NodoDobleIndexado(index, valor);

        // Si se pudo crear el Nodo.
        if(nuevoNodo != null)
        {
            // Está vacía la Lista.
            if(vacia())
            {
                primero = nuevoNodo;
                ultimo = nuevoNodo;
                nuevoNodo.setIndex(0);
            }
            // El index del nuevo Nodo es mayor a los contenidos.
            else if(index >= size)
            {
                NodoDobleIndexado penultimo = ultimo;
                penultimo.setLigaDerecha(nuevoNodo);
                nuevoNodo.setLigaDerecha(null);
                nuevoNodo.setLigaIzquierda(ultimo);
                nuevoNodo.setIndex(penultimo.getIndex() + 1);
                ultimo = nuevoNodo;
            }
            // El index está entre el medio o los primeros.
            else
            {
                NodoDobleIndexado nodo = buscarNodoPIndex(index);

                // El que contiene el index es el primero.
                if(nodo == primero)
                {
                    nuevoNodo.setLigaIzquierda(null);
                    nuevoNodo.setLigaDerecha(primero);
                    primero.setLigaIzquierda(nuevoNodo);
                    primero = nuevoNodo;
                    nuevoNodo.setIndex(0);
                    reacomodarAdelante(nodo);
                }
                // El que contiene el index es el último.
                else if(nodo == ultimo)
                {
                    NodoDobleIndexado anterior = ultimo.getLigaIzquierda();
                    anterior.setLigaDerecha(nuevoNodo);
                    nuevoNodo.setLigaIzquierda(anterior);
                    nuevoNodo.setLigaDerecha(ultimo);
                    ultimo.setLigaIzquierda(nuevoNodo);
                }
                // El que contiene el index es uno de en medio.
                else
                {
                    NodoDobleIndexado anterior = nodo.getLigaIzquierda();
                    NodoDobleIndexado siguiente = nodo.getLigaDerecha();
                    anterior.setLigaDerecha(nuevoNodo);
                    nuevoNodo.setLigaIzquierda(anterior);
                    nuevoNodo.setLigaDerecha(nodo);
                    nodo.setLigaIzquierda(nuevoNodo);
                    reacomodarAdelante(nodo);
                }
            }
            size++;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Elimina un elemento en la posición indicada.
     * @param index Posición a ser eliminada.
     * @return Regresa el valor eliminado.
     */
    public Object eliminar(int index)
    {
        // Hay elementos en la Lista.
        if(!vacia() && index < size)
        {
            // El index es negativo.
            if(index < 0)
            {
                index =  index + (size - 1);
            }
            NodoDobleIndexado nodoABorrar = buscarNodoPIndex(index);

            // Encontró el Nodo que tiene el elemento a borrar.
            if (nodoABorrar != null)
            {
                // El Nodo a borrar es el único en la Lista.
                if (primero == ultimo)
                {
                    primero = null;
                    ultimo = null;
                }
                // El Nodo a borrar es el primero.
                else if (nodoABorrar == primero)
                {
                    primero = primero.getLigaDerecha();
                    primero.setLigaIzquierda(null);
                    reacomodarAtras(nodoABorrar);

                }
                // El Nodo a borrar es el último.
                else if (nodoABorrar == ultimo)
                {
                    ultimo = ultimo.getLigaIzquierda();
                    ultimo.setLigaDerecha(null);

                }
                // El Nodo a borrar no es ni el primero ni el último.
                else
                {
                    NodoDobleIndexado anterior = nodoABorrar.getLigaIzquierda();
                    NodoDobleIndexado siguiente = nodoABorrar.getLigaDerecha();
                    anterior.setLigaDerecha(siguiente);
                    siguiente.setLigaIzquierda(anterior);
                    reacomodarAtras(siguiente);
                }
                size--;
                return nodoABorrar.getInfo();
            }
            // No se encontró ningún Nodo que tenga el elemento a borrar.
            else
            {
                return null;
            }
        }
        // No encontró un Nodo con el contenido a borrar.
        else
        {
            return null;
        }
    }

    /**
     * Asigna a la Lista el valor en la posición indicada.
     * @param pos Posición a realizar la asignación.
     * @param valor Elemento a ser asignado.
     * @return Regresa <b>true</b> en caso de haber realizado la
     * asignación, <b>false</b> en caso contrario.
     */
    public boolean setElemento(int pos, Object valor)
    {
        if(posicionValida(pos))
        {
            NodoDobleIndexado nodo = buscarNodoPIndex(pos);
            nodo.setInfo(valor);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Extrae de datos el valor contenido en la posición dada.
     * @param pos Posición del elemento.
     * @return Regresa el Object contenido en la posición indicada.
     */
    public Object getElemeto(int pos)
    {
        if(posicionValida(pos))
        {
            NodoDobleIndexado nodo = buscarNodoPIndex(pos);
            return nodo.getInfo();
        }
        else
        {
            return null;
        }
    }

    /**
     * Valida que la posición dada esté dentro de los
     * límites de la Lista.
     * @param valor Posición en la Lista a ser comprobada.
     * @return Regresa <b>true</b> si el valor es un índice
     * válido, <b>false</b> en caso contrario.
     */
    private boolean posicionValida(int index)
    {
        if(index >= 0 && index < size)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean agregar(Object valor) {
        return false;
    }

    @Override
    public void imprimirR() {

    }

    @Override
    public Object buscar(Object valor) {
        return null;
    }

    @Override
    public Object eliminar(Object valor) {
        return null;
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
    public void vaciar() {

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
    public Object eliminarTope() {
        return null;
    }

    @Override
    public void imprimir()
    {
        // Hay elementos en la Lista.
        if(!vacia())
        {
            SalidaEstandar.consola("Null <-- ");
            imprimir(primero);
            SalidaEstandar.consola(" --> Null");
        }
        // La lista está vacía.
        else
        {
            SalidaEstandar.consola("Null");
        }
    }


    /**
     * Realiza la impresión de los elementos de la lista
     * partiendo de la primera posición a la última de
     * forma recursiva.
     * @param actual Nodo que se está iterando en curso para
     *               imprimir su contenido.
     */
    private void imprimir(NodoDobleIndexado actual)
    {
        // Caso recursivo.
        if(actual != null)
        {
            SalidaEstandar.consola(actual.getInfo().toString());
            // Si el nodo actual no es el último.
            if(actual != ultimo)
            {
                SalidaEstandar.consola(" <--> ");
            }
            imprimir(actual.getLigaDerecha());
        }
        // Caso base oculto.
    }

    /**
     * Realiza la impresión de los elementos junto con su índice de la lista
     * partiendo de la primera posición a la última
     */
    public void imprimirConIndex()
    {
        // Hay elementos en la Lista.
        if(!vacia())
        {
            SalidaEstandar.consola("Null <-- ");
            imprimirConIndex(primero);
            SalidaEstandar.consola(" --> Null");
        }
        // La lista está vacía.
        else
        {
            SalidaEstandar.consola("Null");
        }
    }

    /**
     * Realiza la impresión de los elementos con índice de la lista
     * partiendo de la primera posición a la última de
     * forma recursiva.
     * @param actual Nodo que se está iterando en curso para
     *               imprimir su contenido.
     */
    private void imprimirConIndex(NodoDobleIndexado actual)
    {
        // Caso recursivo.
        if(actual != null)
        {
            SalidaEstandar.consola(actual.toString());
            // Si el nodo actual no es el último.
            if(actual != ultimo)
            {
                SalidaEstandar.consola(" <--> ");
            }
            imprimirConIndex(actual.getLigaDerecha());
        }
        // Caso base oculto.
    }




    @Override
    public void rellenar(Object valor, int cuantos) {

    }

    @Override
    public Object getTope() {
        return ultimo.getInfo();
    }


    /**
     * Recorre los index de los Nodos un número atras.
     * @param nodoAcual Nodo que está siendo modificado.
     */
    private void reacomodarAtras(NodoDobleIndexado nodoAcual)
    {
        // Caso recursivo.
        if(nodoAcual != null)
        {
            int temporal = nodoAcual.getIndex();
            nodoAcual.setIndex(temporal - 1);
            reacomodarAtras(nodoAcual.getLigaDerecha());
        }
        // Caso base escondido.
    }

    /**
     * Recorre los index de los Nodos un número adelante.
     * @param nodoAcual Nodo que está siendo modificado.
     */
    private void reacomodarAdelante(NodoDobleIndexado nodoAcual)
    {
        // Caso recursivo.
        if(nodoAcual != null)
        {
            int temporal = nodoAcual.getIndex();
            nodoAcual.setIndex(temporal + 1);
            reacomodarAdelante(nodoAcual.getLigaDerecha());
        }
        // Caso base escondido.
    }

    /**
     * Busca un nodo en la Lista de acuerdo a un Index.
     * @param index Identificador a buscar.
     * @return Regresa el Nodo que tiene el index buscado en caso
     * de haberlo encontrado, o null en caso contrario.
     */
    public NodoDobleIndexado buscarNodoPIndex(int index)
    {
        //Hay elementos.
        if(!vacia())
        {
            return buscarPorIndex(index, primero);
        }
        // Está vacía.
        else
        {
            return null;
        }
    }

    /**
     * Busca un nodo en la Lista de acuerdo a un index.
     * @param index Identificador a buscar.
     * @param nodoActual Nodo que se está revisando su index.
     * @return Regresa el Nodo que tiene el index buscado en caso
     * de haberlo encontrado, o null en caso contrario.
     */
    private NodoDobleIndexado buscarPorIndex(int index, NodoDobleIndexado nodoActual)
    {
        // Caso base1.
        if(nodoActual == null)
        {
            return null;
        }
        // Caso base2.
        else if(nodoActual.getIndex() == index)
        {
            return nodoActual;
        }
        // Caso recursivo.
        else
        {
            return buscarPorIndex(index, nodoActual.getLigaDerecha());
        }
    }
}
