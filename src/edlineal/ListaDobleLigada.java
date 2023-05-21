package edlineal;

import catalogos.Nodo;
import catalogos.NodoDoble;
import entradasalida.SalidaEstandar;
import utilidades.Comparador;

/**
 * Esta clase implementa el TDA ListaDobleLigada.
 * Permite guardar datos usando memoria dinámica.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ListaDobleLigada implements Lista
{
    /** Guarda el primer NodoDoble que compone la ListaDobleLigada. */
    protected NodoDoble primero;
    /** Guarda el último NodoDoble que compone la ListaDobleLigada.  */
    protected NodoDoble ultimo;
    /** Guarda el nodo de la Lista en que se están iterando los elementos. */
    protected NodoDoble nodoActualiterador;
    /** Guarda la cantidad de elementos que tiene la ListaDobleLigada. */
    protected int size;

    /**
     * Define el constructor de TDA ListaDobleLigada.
     */
    public ListaDobleLigada()
    {
        primero = null;
        ultimo = null;
        nodoActualiterador = primero;
        size = 0;
    }

    /**
     * Agrega al inicio de la lista un NuevoNodoDoble con
     * la información dada como parámetro.
     * @param valor Información a guardarse en el NodoDoble que será
     *              insertado al inicio.
     * @return Regresa <b>true</b> en caso de haber podido
     * guardar el nuevo dato en la lista, o <b>false</b> en caso
     * contrario.
     */
    public boolean insertarInicio(Object valor)
    {
        NodoDoble nuevo = new NodoDoble(valor);
        // Hay espacio o no hay error.
        if(nuevo != null)
        {
            // Está vacía.
            if(vacia())
            {
                primero = nuevo;
                ultimo = nuevo;
            }
            // Ya hay algo.
            else
            {
                nuevo.setLigaDerecha(primero);
                primero.setLigaIzquierda(nuevo);
                primero = nuevo;
            }
            // En ambos casos regresa true.
            size++;
            return true;
        }
        // Hay error o no hay espacio.
        else
        {
            return false;
        }
    }


    @Override
    public boolean llena()
    {
        NodoDoble nuevo = new NodoDoble(new Object());
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

    @Override
    public boolean agregar(Object valor)
    {
        NodoDoble nuevo = new NodoDoble(valor);

        // Sí hay espacio de memoria.
        if(nuevo != null)
        {
            // Está vacía.
            if(vacia())
            {
                primero = nuevo;
                ultimo = nuevo;
            }
            // Ya tenía algo.
            else
            {
                ultimo.setLigaDerecha(nuevo);
                nuevo.setLigaIzquierda(ultimo);
                ultimo = nuevo;
            }
            size++;
            return true;
        }
        // No hay espacio de memoria.
        else
        {
            return false;
        }
    }

    @Override
    public void imprimirR()
    {
        // Tiene elementos.
        if(!vacia())
        {
            NodoDoble temporal = ultimo;
            SalidaEstandar.consola("Null <-- ");
            // Se iteran los nodos.
            while (temporal != null)
            {
                SalidaEstandar.consola(temporal.getInfo().toString());
                // Si el nodo actual no es el último.
                if(temporal != primero)
                {
                    SalidaEstandar.consola(" <--> ");
                }
                temporal = temporal.getLigaIzquierda();
            }
            SalidaEstandar.consola(" --> Null");
        }
        // Está vacía.
        else
        {
            SalidaEstandar.consola("Null");
        }
    }

    @Override
    public Object buscar(Object valor)
    {
        // Sí tiene elementos.
        if(!vacia())
        {
            NodoDoble temporal = primero;

            // Se itera la lista.
            while (temporal != null && Comparador.comparar(temporal, valor) != 0)
            {
                // Se recorre la iteración hacia el nodo de la izquierda.
                temporal = temporal.getLigaDerecha();
            }
            // Se encontró el valor.
            if(temporal != null)
            {
                return temporal.getInfo();
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

    @Override
    public Object eliminar(Object valor)
    {
        // Hay elementos en la Lista.
        if(!vacia())
        {
            NodoDoble nodoABorrar = buscarNodo(valor);

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
                    NodoDoble anterior = nodoABorrar.getLigaIzquierda();
                    NodoDoble siguiente = nodoABorrar.getLigaDerecha();
                    anterior.setLigaDerecha(siguiente);
                    siguiente.setLigaIzquierda(anterior);
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

    @Override
    public boolean setElemento(Object valorV, Object valorN, int numOcurrencias)
    {
        NodoDoble temporal = primero;
        int contadorCambios = 0;

        // Se itera la Lista completa.
        while (temporal != null && numOcurrencias != 0)
        {
            // Si se encuentra un Nodo con información a cambiar.
            if(Comparador.comparar(temporal, valorV) == 0)
            {
                temporal.setInfo(valorN);
                contadorCambios++;
                numOcurrencias--;
            }
            temporal = temporal.getLigaDerecha();
        }
        // Se realizó por lo menos un cambio.
        if(contadorCambios > 0)
        {
            return true;
        }
        // No se realizó ningún cambio.
        else
        {
            return false;
        }
    }

    @Override
    public Arreglo buscarOcurrencias(Object valor)
    {
        Arreglo arrayOcurrencias = new Arreglo(size);
        int posicion = 0;
        NodoDoble temporal = primero;

        // Se recorre la Lista.
        while (temporal != null)
        {
            // Si el contenido del nodo es igual al que se está buscando.
            if(Comparador.comparar(temporal, valor) == 0)
            {
                arrayOcurrencias.agregar(posicion);
            }
            temporal = temporal.getLigaDerecha();
            posicion++;
        }
        return arrayOcurrencias;
    }

    @Override
    public void vaciar()
    {
        primero = null;
        ultimo = null;
        size = 0;
    }

    @Override
    public boolean agregarArreglo(Arreglo arreglo2)
    {
        // Si el arreglo no es null.
        if(arreglo2 != null)
        {
            int contadorInserciones = 0;

            // Se iteran los elementos del arreglo.
            for(int index = 0; index < arreglo2.getLongitud(); index++)
            {
                // Si se pudo agregar un elemento a la Lista.
                if(agregar(arreglo2.getElemento(index)))
                {
                    contadorInserciones++;
                }
            }
            // Si se realizó por lo menos una inserción en la Lista.
            if(contadorInserciones > 0)
            {
                return true;
            }
            // Si no se realizó ninguna inserción.
            else
            {
                return false;
            }
        }
        // Si el arreglo es null.
        else
        {
            return false;
        }
    }

    @Override
    public void invertir()
    {
        // Si la Lista no está vacía.
        if(!vacia())
        {
            // Si hay más de un elemento en la Lista.
            if (primero != ultimo)
            {
                Pila pilaDatos = getPilaElementos();
                // Se invierten el primer y el último elemento.
                NodoDoble temporal = ultimo;
                ultimo = primero;
                primero = temporal;
                primero.setLigaIzquierda(null);
                pilaDatos.pop();
                // Mientras haya elementos en la Pila.
                while (pilaDatos.verTope() != null)
                {
                    temporal.setLigaDerecha((NodoDoble) pilaDatos.pop());
                    NodoDoble anterior = temporal;
                    temporal = temporal.getLigaDerecha();
                    temporal.setLigaIzquierda(anterior);
                }
                temporal.setLigaDerecha(null);
            }
        }
    }

    @Override
    public int contar(Object valor)
    {
        int ocurrencias = 0;

        inicializarIteradorPrincipio();
        // Se iteran los elementos de la Lista.
        while (hayNodo())
        {
            // Si el elemento en curso es igual al que se busca.
            if(Comparador.comparar(obtenerSiguiente(), valor) == 0)
            {
                ocurrencias++;
            }
        }
        return ocurrencias;
    }

    @Override
    public Object eliminarTope()
    {
        // Hay algo.
        if(!vacia())
        {
            Object contenido = ultimo.getInfo();
            // Solo hay un elemento en la Lista.
            if(primero == ultimo)
            {
                primero = null;
                ultimo = null;
            }
            // Hay más de un elemento en la Lista.
            else
            {
                NodoDoble penultimo = ultimo.getLigaIzquierda();
                penultimo.setLigaDerecha(null);
                ultimo = penultimo;
            }
            return contenido;
        }
        // Está vacía.
        else
        {
            return null;
        }
    }

    @Override
    public void imprimir()
    {
        // Tiene elementos.
        if(!vacia())
        {
            NodoDoble temporal = primero;
            SalidaEstandar.consola("Null <-- ");
            // Se iteran los nodos.
            while (temporal != null)
            {
                SalidaEstandar.consola(temporal.getInfo().toString());
                // Si el nodo actual no es el último.
                if(temporal != ultimo)
                {
                    SalidaEstandar.consola(" <--> ");
                }
                temporal = temporal.getLigaDerecha();
            }
            SalidaEstandar.consola(" --> Null");
        }
        // Está vacía.
        else
        {
            SalidaEstandar.consola("Null");
        }
    }

    @Override
    public void rellenar(Object valor, int cuantos)
    {
        // Se agrega el valor a la Lista las veces indicadas.
        for(int contador = 0; contador < cuantos; contador++)
        {
            agregar(valor);
        }
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
     * Realiza la búsqueda de un elemento en la Lista partiendo
     * desde el último Nodo.
     * @param valor Elemento a ser buscado.
     * @return Regresa el objeto encontrado, o null en caso
     * de no haberlo podido encontrar.
     */
    public Object buscarDesdeFinal(Object valor)
    {
        // Sí tiene elementos.
        if(!vacia())
        {
            NodoDoble temporal = ultimo;

            // Se itera la lista.
            while (temporal != null && Comparador.comparar(temporal, valor) != 0)
            {
                // Se recorre la iteración hacia el nodo de la izquierda.
                temporal = temporal.getLigaIzquierda();
            }
            return temporal.getInfo();
        }
        // Está vacía.
        else
        {
            return null;
        }
    }

    /**
     * Inicializa el iterador de los elementos de la Lista
     * en la última posición.
     */
    public void inicializarIteradorFinal()
    {
        nodoActualiterador = ultimo;
    }

    /**
     * Inicializa el iterador de los elementos de la Lista
     * en la primera posición.
     */
    public void inicializarIteradorPrincipio()
    {
        nodoActualiterador = primero;
    }

    /**
     * Verifica si el nodo iterador está en una
     * posición válida.
     * @return Regresa <b>true</b> en caso de estar
     * en una posición válida, y <b>false</b> en caso
     * contrario.
     */
    public boolean hayNodo()
    {
        if(nodoActualiterador != null)
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
     * @return Regresa el contenido del Nodo que está iterando, o null
     * si el Nodo en que está la iteración es inválido.
     */
    public Object obtenerSiguiente()
    {
        // Es un Nodo válido.
        if(hayNodo())
        {
            Object contenido = nodoActualiterador.getInfo();
            nodoActualiterador = nodoActualiterador.getLigaDerecha();
            return contenido;
        }
        // El Nodo es inválido.
        else
        {
            return null;
        }
    }

    /**
     * Extrae el contenido del Nodo actual que está iterando
     * la Lista y después pasa la iteración hacia el Nodo anterior.
     * @return Regresa el contenido del Nodo que está iterando, o null
     * si el Nodo en que está la iteración es inválido.
     */
    public Object obtenerAnterior()
    {
        // Es un Nodo válido.
        if(hayNodo())
        {
            Object contenido = nodoActualiterador.getInfo();
            nodoActualiterador = nodoActualiterador.getLigaIzquierda();
            return contenido;
        }
        // El Nodo es inválido.
        else
        {
            return null;
        }
    }

    /**
     * Obtiene la cantidad de elementos que contiene
     * la Lista.
     * @return Regresa el número de elementos de la Lista.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Busca en los contenidos de los nodos de la Lista
     * el que contenga el valor especificado.
     * @param valor Elemento a ser buscado en los nodos
     *              de la Lista.
     * @return Regresa el NodoDoble que contiene el elemento,
     * o null en caso de no haberlo encontrado.
     */
    private NodoDoble buscarNodo(Object valor)
    {
        // Sí hay elementos.
        if(!vacia())
        {
            NodoDoble temporal = primero;

            // Se itera la Lista en busca del Nodo.
            while (temporal != null && Comparador.comparar(temporal, valor) != 0)
            {
                temporal = temporal.getLigaDerecha();
            }
            return temporal;
        }
        // Está vacía.
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

        NodoDoble temporal = primero;
        // Mientras temporal sea diferente de Null, se agregan los elementos a la Pila.
        while (temporal != null)
        {
            pilaDatos.push(temporal);
            temporal = temporal.getLigaDerecha();
        }
        return pilaDatos;
    }

    /**
     * Recupera los datos contenidos en la Lista para transformarlos
     * a un VectorNum.
     * @return Regresa el Vector con los elementos copiados.
     */
    public VectorNum aVector()
    {
        VectorNum copia = new VectorNum(size);
        inicializarIteradorPrincipio();

        // Se iteran y copian los datos de la lista.
        while (hayNodo())
        {
            copia.agregar(Double.parseDouble(
            obtenerSiguiente().toString()));
        }
        return copia;
    }
}
