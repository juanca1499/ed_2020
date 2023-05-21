package edlineal;

import catalogos.Nodo;
import catalogos.NodoDoble;
import catalogos.NodoHash;
import entradasalida.SalidaEstandar;
import utilidades.Comparador;

/**
 * Esta clase implementa el TDA ListaEnlazadaHash.
 * Almacena y manipula NodosHash que guardan la información
 * a manera de tabla hash (valor, información).
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ListaEnlazadaHash
{
    /** Guarda el primer NodoHash que compone la ListaEnlazadaHash. */
    private NodoHash primero;
    /** Guarda el último NodoHash que compone la ListaEnlazadaHash. */
    private NodoHash ultimo;
    /** Guarda la cantidad de elementos almacenados en la ListaEnlazadaHash. */
    private int size;

    /**
     * Define el constructor de TDA ListaEnlazadaHash.
     */
    public ListaEnlazadaHash()
    {
        primero = null;
        ultimo = null;
        size = 0;
    }

    /**
     * Comprueba si la Lista no contiene ningún elemento (NodoHash)
     * en su interior.
     * @return Regresa <b>true</b> en caso de estar vacía, o
     * <b>false</b> en caso contrario.
     */
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
     * Agrega un NodoHash al final de la Lista con la
     * información dada en los paráemtros.
     * Si la clave es una ya existente en la Lista, cambia
     * cambia el valor del nodo por el pasado como parámetro.
     * @param clave Identificador de la información.
     * @param valor Información a ser almacenada.
     * @return Regresa <b>true</b> en caso de haber podido agregar
     * el valor, o <b>false</b> en caso contrario.
     */
    public boolean insertar(Object clave, Object valor)
    {
        NodoHash nuevo = new NodoHash(clave, valor);

        // No hubo problemas.
        if(nuevo != null)
        {
            // Está vacía.
            if(primero == null)
            {
                primero = nuevo;
                ultimo = nuevo;
                size++;
            }
            // Ya hay algo.
            else
            {
                NodoHash nodoRepetido = buscarNodoPorClave(nuevo.getClave());

                // Hay un Nodo en la Lista que tiene la misma clave que el nuevo.
                if(nodoRepetido != null)
                {
                    nodoRepetido.setValor(nuevo.getValor());
                }
                // No hay ningún Nodo en la Lista con la misma clave que el nuevo.
                else
                {
                    ultimo.setLigaDerecha(nuevo);
                    ultimo = nuevo;
                    size++;
                }
            }
            return true;
        }
        // No hay espacio o hubo error.
        else
        {
            return false;
        }
    }

    /**
     * Suprime el elemento con la clave indicada.
     * @param clave Es la clave a buscar en los elementos de la Lista.
     * @return Regresa el valor del NodoHash eliminado, o null si no fue encontrado.
     */
    public Object eliminar(Object clave)
    {
        // Sí hay elementos.
        if(!vacia())
        {
            Object contenido = null;
            NodoHash actual = primero;
            NodoHash atras = primero;

            // Se itera la lista en busca del Nodo que tenga la clave.
            while (actual != null && Comparador.comparar(actual.getClave(), clave) != 0)
            {
                atras = actual;
                actual = actual.getLigaDerecha();
            }
            // Encontró algo.
            if(actual != null)
            {
                contenido = actual.getValor();
                // Era el único Nodo en la Lista.
                if(primero == ultimo)
                {
                    primero = null;
                    ultimo = null;
                }
                // Era el último Nodo en la Lista.
                else if(actual == ultimo)
                {
                    atras.setLigaDerecha(null);
                    ultimo = atras;
                }
                // Era el primer Nodo en la Lista.
                else if(actual == primero)
                {
                    primero = primero.getLigaDerecha();
                }
                // El Nodo no es ni el primero, ni el segundo, ni
                // el único.
                else
                {
                    atras.setLigaDerecha(actual.getLigaDerecha());
                }
                size--;
                return contenido;
            }
            // No se encontró.
            else
            {
                return null;
            }
        }
        // Está vacía.
        else
        {
            return null;
        }
    }

    /**
     * Suprime el elemento con el valor indicado.
     * @param valor Es el contenido (valor) a buscar en los
     *              elementos de la Lista.
     * @return Regresa el valor del NodoHash eliminado, o null si no fue
     * encontrado.
     */
    public Object eliminarValor(Object valor)
    {
        // Sí hay elementos.
        if(!vacia())
        {
            Object contenido = null;
            NodoHash actual = primero;
            NodoHash atras = primero;

            // Se itera la lista en busca del Nodo que tenga el valor.
            while (actual != null && Comparador.comparar(actual.getValor(), valor) != 0)
            {
                atras = actual;
                actual = actual.getLigaDerecha();
            }
            // Encontró algo.
            if(actual != null)
            {
                contenido = actual.getValor();
                // Era el único Nodo en la Lista.
                if(primero == ultimo)
                {
                    primero = null;
                    ultimo = null;
                }
                // Era el último Nodo en la Lista.
                else if(actual == ultimo)
                {
                    atras.setLigaDerecha(null);
                    ultimo = atras;
                }
                // Era el primer Nodo en la Lista.
                else if(actual == primero)
                {
                    primero = primero.getLigaDerecha();
                }
                // El Nodo no es ni el primero, ni el segundo, ni
                // el único.
                else
                {
                    atras.setLigaDerecha(actual.getLigaDerecha());
                }
                size--;
                return contenido;
            }
            // No se encontró.
            else
            {
                return null;
            }
        }
        // Está vacía.
        else
        {
            return null;
        }
    }

    /**
     * Recorre los elementos de la Lista en busca del que
     * posea la clave indicada.
     * @param clave Identificador a buscar.
     * @return Regresa el valor del NodoHash si fue encontrado,
     * o null en caso contrario.
     */
    public Object buscar(Object clave)
    {
        NodoHash nodo = buscarNodoPorClave(clave);

        // Sí fue encontrado.
        if(nodo != null)
        {
            return nodo.getValor();
        }
        // No fue encontrado.
        else
        {
            return null;
        }
    }

    /**
     * Recorre los elementos de la Lista en busca del que
     * posea el valor indicada.
     * @param valor Contenido (valor) a buscar.
     * @return Regresa el valor del NodoHash si fue encontrado,
     * o null en caso contrario.
     */
    public Object buscarValor(Object valor)
    {
        NodoHash nodo = buscarNodoPorValor(valor);

        // Sí fue encontrado.
        if(nodo != null)
        {
            return nodo.getValor();
        }
        // No fue encontrado.
        else
        {
            return null;
        }
    }

    /**
     * Cambia el valor de un elemento de la Lista en base a su clave.
     * @param clave Identificador del elemento a buscar.
     * @param valorNuevo Contenido (valor) sustituto que se pondrá en el valor
     *                   del elemento encontrado.
     * @return Regresa <b>true</b> en caso de haber podido realizar
     * la sustitución, o <b>false</b> en caso contrario.
     */
    public boolean sustituir(Object clave, Object valorNuevo)
    {
        NodoHash nodo = buscarNodoPorClave(clave);

        // Sí fue encontrado.
        if(nodo != null)
        {
            nodo.setValor(valorNuevo);
            return true;
        }
        // No fue encontrado.
        else
        {
            return false;
        }
    }

    /**
     * Cambia el valor de un elemento de la Lista en base a su contenido
     * (valor).
     * @param valor Contenido del elemento a buscar
     * @param valorNuevo Contenido sustituto que se pondrá en el valor
     *                   del elemento encontrado.
     * @return Regresa <b>true</b> en caso de haber podido realizar
     * la sustitución, o <b>false</b> en caso contrario.
     */
    public boolean sustituirValor(Object valor, Object valorNuevo)
    {
        NodoHash nodo = buscarNodoPorValor(valor);

        // Sí fue encontrado.
        if(nodo != null)
        {
            nodo.setValor(valorNuevo);
            return true;
        }
        // No fue encontrado.
        else
        {
            return false;
        }
    }

    /**
     * Realiza la impresión en la salida estandar
     * de los elementos de la Lista en la forma:
     * Clave (valor).
     */
    public void imprimir()
    {
        NodoHash temporal = primero;
        while (temporal != null)
        {
            SalidaEstandar.consola(temporal + " --> ");
            temporal = temporal.getLigaDerecha();
        }
        SalidaEstandar.consola("Null");
    }

    /**
     * Guarda los elementos contenidos en la ListaHash en una
     * ListaLigada con dos Arreglos en su interior: el primero contiene las claves
     * de los elementos, y el otro los contenidos (valores) de los elementos.
     * @return Regresa la ListaLigada con los contenidos guardados en dos arreglos,
     * o una ListaLigada vacía en caso de que esté vacía.
     */
    public ListaLigada aArreglos()
    {
        ListaLigada listaElementos = new ListaLigada();

        // Sí hay elementos en la Lista.
        if(!vacia())
        {
            Arreglo arregloClaves = new Arreglo(size);
            Arreglo arregloValores = new Arreglo(size);
            NodoHash actual = primero;

            // Se recorren los elementos de la Lista.
            while (actual != null)
            {
                arregloClaves.agregar(actual.getClave());
                arregloValores.agregar(actual.getValor());
                actual = actual.getLigaDerecha();
            }
            // Se agregan los dos arreglos a la Lista.
            listaElementos.agregar(arregloClaves);
            listaElementos.agregar(arregloValores);
            return listaElementos;
        }
        // Está vacía.
        else
        {
            return listaElementos;
        }
    }

    /**
     * Guarda los elementos contenidos en la ListaHash en una
     * ListaLigada con dos ListasLigadas en su interior: la primera contiene
     * las claves de los elementos, y la otra los contenidos (valores)
     * de los elementos.
     * @return Regresa la ListaLigada con los contenidos guardados en dos ListasLigadas,
     * o una ListaLigada vacía en caso de que esté vacía.
     */
    public ListaLigada aListas()
    {
        ListaLigada listaElementos = new ListaLigada();

        // Sí hay elementos.
        if(!vacia())
        {
            ListaLigada listaClaves = new ListaLigada();
            ListaLigada listaValores = new ListaLigada();
            NodoHash actual = primero;

            // Se recorren los elementos de la Lista.
            while (actual != null)
            {
                listaClaves.agregar(actual.getClave());
                listaValores.agregar(actual.getValor());
                actual = actual.getLigaDerecha();
            }
            // Se agregan los dos arreglos a la Lista.
            listaElementos.agregar(listaClaves);
            listaElementos.agregar(listaValores);
            return listaElementos;
        }
        // Está vacía.
        else
        {
            return listaElementos;
        }
    }

    /**
     * Guarda los elementos contenidos en la ListaHash en una
     * Matriz con dos columnas: la primera con las claves de los elementos,
     * y la segunda con los valores de los elementos.
     * @return Regresa la Matriz con la estructura de la ListaHash en caso de
     * que sí tenga elementos, o una ListaLigada vacía en caso de que esté vacía.
     */
    public Matriz aMatriz()
    {

        // Sí hay elementos en la Lista.
        if(!vacia())
        {
            Matriz matrizElementos = new Matriz(size,2);
            NodoHash actual = primero;

            // Se recorren los elementos de la Lista.
            for(int index = 0; index < size; index++)
            {
                matrizElementos.setElemento(index, 0, actual.getClave());
                matrizElementos.setElemento(index, 1, actual.getValor());
                actual = actual.getLigaDerecha();
            }
            return matrizElementos;
        }
        // Está vacía.
        else
        {
            return null;
        }
    }

    /**
     * Recorre los NodosHash de la Lista en busca del su clave
     * coincida con la clave indicada.
     * @param clave Identificador a ser buscado.
     * @return Regresa el NodoHash que contiene la clave indicada,
     * o null en caso de no haberlo encontrado.
     */
    private NodoHash buscarNodoPorClave(Object clave)
    {
        // Sí hay elementos.
        if(!vacia())
        {
            NodoHash temporal = primero;

            // Se itera la Lista en busca del Nodo que contiene la clave.
            while (temporal != null && Comparador.comparar(temporal.getClave(), clave) != 0)
            {
                temporal = temporal.getLigaDerecha();
            }
            // Sí encontro el Nodo.
            if(temporal != null)
            {
                return temporal;
            }
            // No encontró ningún Nodo que coincida.
            else
            {
                return null;
            }
        }
        // Está vacía.
        else
        {
            return null;
        }
    }

    /**
     * Recorre los NodosHash de la Lista en busca del que su
     * información coincida con el valor dado como parámetro.
     * @param valor Información a ser buscada.
     * @return Regresa el NodoHash que contiene la información indicada,
     * o null en caso de no haberlo encontrado.
     */
    private NodoHash buscarNodoPorValor(Object valor)
    {
        // Sí hay elementos.
        if(!vacia())
        {
            NodoHash temporal = primero;

            // Se itera la Lista en busca del Nodo que contiene el valor.
            while (temporal != null && Comparador.comparar(temporal.getValor(), valor) != 0)
            {
                temporal = temporal.getLigaDerecha();
            }
            // Sí encontro el Nodo.
            if(temporal != null)
            {
                return temporal;
            }
            // No encontró ningún Nodo que coincida.
            else
            {
                return null;
            }
        }
        // Está vacía.
        else
        {
            return null;
        }
    }
}
