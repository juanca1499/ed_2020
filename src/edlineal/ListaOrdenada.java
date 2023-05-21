package edlineal;


import catalogos.Nodo;
import entradasalida.SalidaEstandar;
import utilidades.Comparador;
import utilidades.CriterioOrden;


/**
 * Esta clase implementa el TDA ListaOrdenada.
 * Almacena objetos de cualquier tipo de manera ordenada.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class ListaOrdenada implements Lista
{

    /** Guarda el primer Nodo que compone la ListaOrdenada. */
    protected Nodo primero;
    /** Guarda el último Nodo que compone la ListaOrdenada.  */
    protected Nodo ultimo;
    /** Guarda el tipo ordenamiento que se estará manejando en la ListaOrdenada, (ascendente o descendente). */
    private CriterioOrden tipoOrdenamiento;
    /** Guarda la cantidad de elementos que están contenidos en la Lista. */
    private int size;

    /**
     * Define el constructor de TDA ListaOrdenada.
     * @param tipoOrdenamiento Criterio de ordenamiento en que se
     *                         almacenarán los elementos en la ListaOrdenada:
     *                         (ascendente o descendente).
     */
    public ListaOrdenada(CriterioOrden tipoOrdenamiento)
    {
        this.tipoOrdenamiento = tipoOrdenamiento;
        primero = null;
        ultimo = null;
        size = 0;
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

    @Override
    public boolean agregar(Object valor)
    {
        Nodo nuevo = new Nodo(valor);
        if(nuevo != null)
        {
            // Si la lista está vacía.
            if(vacia())
            {
                primero = nuevo;
                ultimo = nuevo;
                size++;
                return true;
            }
            // Si el tipo de ordenamiento es ascendente.
            else if(tipoOrdenamiento == CriterioOrden.ASCENDENTE)
            {
                return agregarAscendente(nuevo);
            }
            // Si el tipo de ordenamiento es descendente.
            else
            {
                return agregarDescendente(nuevo);
            }
        }
        // Si ya no hay memoria para agregar un nuevo Nodo.
        else
        {
            return false;
        }
    }

    /**
     * Agrega un nuevo elemento en la lista, asignándole la posición que
     * le corresponde en forma ascendente.
     * @param nuevo Elemento a ser insertado en la lista.
     * @return Regresa <b>true</b> en caso de haber podido agregar el elemento,
     * o <b>false</b> en caso contrario.
     */
    private boolean agregarAscendente(Nodo nuevo)
    {
        // Si el nuevo elemento es menor al primer elemento de la lista.
        if(Comparador.comparar(nuevo.getInfo(), primero.getInfo()) < 0)
        {
            nuevo.setLigaDerecha(primero);
            primero = nuevo;
            size++;
        }
        // Si el nuevo elemento es mayor al último de la lista.
        else if(Comparador.comparar(nuevo.getInfo(), ultimo.getInfo()) > 0)
        {
            ultimo.setLigaDerecha(nuevo);
            ultimo = nuevo;
            size++;
        }
        // Si el nuevo elemento no es menor al primero ni mayor al último.
        else
        {
            Nodo temporal = primero;
            Nodo anteior = primero;
            // Se recorre la lista hasta encontrar la posición correcta del nuevo elemento.
            while (Comparador.comparar(nuevo.getInfo(), temporal.getInfo()) > 0)
            {
                anteior = temporal;
                temporal = temporal.getLigaDerecha();
            }
            // Si el nuevo elemento no es igual a uno ya existente.
            if(Comparador.comparar(nuevo.getInfo(), temporal.getInfo()) != 0)
            {
                nuevo.setLigaDerecha(temporal);
                anteior.setLigaDerecha(nuevo);
                size++;
            }
        }
        return true;
    }

    /**
     * Agrega un nuevo elemento en la lista, asignándole la posición que
     * le corresponde en forma descendente.
     * @param nuevo Elemento a ser insertado en la lista.
     * @return Regresa <b>true</b> en caso de haber podido agregar el elemento,
     * o <b>false</b> en caso contrario.
     */
    private boolean agregarDescendente(Nodo nuevo)
    {
        // Si el nuevo elemento es mayor al primer elemento de la lista.
        if(Comparador.comparar(nuevo.getInfo(), primero.getInfo()) > 0)
        {
            nuevo.setLigaDerecha(primero);
            primero = nuevo;
        }
        // Si el nuevo elemento es menor al último de la lista.
        else if(Comparador.comparar(nuevo.getInfo(), ultimo.getInfo()) < 0)
        {
            ultimo.setLigaDerecha(nuevo);
            ultimo = nuevo;
        }
        // Si el nuevo elemento no es mayor al primero ni menor al último.
        else
        {
            Nodo temporal = primero;
            Nodo anteior = primero;
            // Se recorre la lista hasta encontrar la posición correcta del nuevo elemento.
            while (Comparador.comparar(nuevo.getInfo(), temporal.getInfo()) < 0)
            {
                anteior = temporal;
                temporal = temporal.getLigaDerecha();
            }
            // Si el nuevo elemento no es igual a uno ya existente.
            if(Comparador.comparar(nuevo.getInfo(), temporal.getInfo()) != 0)
            {
                nuevo.setLigaDerecha(temporal);
                anteior.setLigaDerecha(nuevo);
            }
            // Si el nuevo elemento es igual a uno ya existente, entonces no es agregado.
            else
            {
                return false;
            }
        }
        size++;
        return true;
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

    @Override
    public Object eliminar(Object valor)
    {
        // Si la lista no está vacía.
        if(!vacia())
        {
            Object contenido = primero.getInfo();
            // Si el primer y último elemento en la lista son iguales al que se va a borrar.
            if(Comparador.comparar(primero.getInfo(), valor) == 0 && Comparador.comparar(ultimo.getInfo(), valor) == 0)
            {
                primero = null;
                ultimo = null;
            }
            // Si el primer elemento es igual al que se va a borrar y el último no es igual.
            else if(Comparador.comparar(primero.getInfo(), valor) == 0 && Comparador.comparar(ultimo.getInfo(), valor) != 0)
            {
                primero = primero.getLigaDerecha();
            }
            // Si el primer elemento no equivale al que se va a borrar, ni tampoco el último.
            else
            {
                Nodo temporal = primero;
                Nodo anterior = primero;
                //Mientras no se encuentre el elemento a borrar se sigue avanzando en la lista.
                while (temporal != null && Comparador.comparar(temporal.getInfo(), valor) != 0)
                {
                    anterior = temporal;
                    temporal = temporal.getLigaDerecha();
                    contenido = temporal;
                }
                // Si el elemento a borrar fue encontrado.
                if(temporal != null)
                {
                    anterior.setLigaDerecha(temporal.getLigaDerecha());
                    contenido = temporal;
                }
                else
                {
                    contenido = temporal;
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

    @Override
    public boolean setElemento(Object valorV, Object valorN, int numOcurrencias)
    {
        Nodo temporal = primero;

        while (temporal != null &&
                !temporal.getInfo().toString().equalsIgnoreCase(valorV.toString()))
        {
            temporal = temporal.getLigaDerecha();
        }
        // Está vacía o no existe.
        if(temporal == null)
        {
            return false;
        }
        // Sí fue encontrado. Se cambia el contenido del Nodo por ValorN.
        else
        {
            temporal.setInfo(valorN);
            return true;
        }
    }

    @Override
    public Arreglo buscarOcurrencias(Object valor)
    {
        int posicion = 0;
        Arreglo arregloOcurrencias = new Arreglo(1);
        Nodo temporal = primero;
        // Se recorre la lista hasta encontrar la posición del elemento que se está buscando.
        while (temporal != null &&
                !temporal.getInfo().toString().equalsIgnoreCase(valor.toString()))
        {
            temporal = temporal.getLigaDerecha();
            posicion++;
        }
        // Está vacía o no existe.
        if(temporal == null)
        {
            arregloOcurrencias.agregar(-1);
            return arregloOcurrencias;
        }
        // Sí lo encontró.
        else
        {
            arregloOcurrencias.agregar(posicion);
            return arregloOcurrencias;
        }
    }

    @Override
    public void vaciar()
    {
        // Si la Lista no está vacía.
        if(!vacia())
        {
            Nodo temporal = primero;
            Nodo siguiente = primero.getLigaDerecha();

            //Mientras haya elementos en la Lista son eliminados.
            while (siguiente.getLigaDerecha() != null)
            {
                siguiente = temporal.getLigaDerecha();
                temporal.setLigaDerecha(null);
                temporal = siguiente;
            }
            primero = null;
            ultimo = null;
        }
    }

    @Override
    public boolean agregarArreglo(Arreglo arreglo2)
    {
        int contadorInserciones = 0;
        // Si el arreglo no es null
        if(arreglo2 != null)
        {
            // Se agregan los elementos del Arreglo a la Lista.
            for(int index = 0; index < arreglo2.getLongitud(); index++)
            {
                if(agregar(arreglo2.getElemento(index)))
                {
                    contadorInserciones++;
                }
            }
            // Si hubo por lo menos una inserción, se regresa true.
            if(contadorInserciones > 0)
            {
                return true;
            }
            // Si no, se regresa false.
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
            if(primero != ultimo)
            {
                Pila pilaDatos = getPilaElementos();
                // Se invierten el primer y el último elemento.
                Nodo temporal = ultimo;
                ultimo = primero;
                primero = temporal;
                pilaDatos.pop();
                // Mientras haya elementos en la Pila.
                while (pilaDatos.verTope() != null)
                {
                    temporal.setLigaDerecha((Nodo) pilaDatos.pop());
                    temporal = temporal.getLigaDerecha();
                }
                temporal.setLigaDerecha(null);
            }
        }
        // Se inverte también el tipo de ordenamiento que tiene la lista actualmente.
        if(tipoOrdenamiento == CriterioOrden.ASCENDENTE)
        {
            tipoOrdenamiento = CriterioOrden.DESCENDENTE;
        }
        else if(tipoOrdenamiento == CriterioOrden.DESCENDENTE)
        {
            tipoOrdenamiento = CriterioOrden.ASCENDENTE;
        }
    }

    @Override
    public int contar(Object valor)
    {
        // Si el objeto fue encontrado en la Lista su ocurrencia
        // sólo es uno porque nose permiten repetidos.
        if(buscar(valor) != null)
        {
            return 1;
        }
        // Si no se encontró, no hay ocurrencias.
        else
        {
            return 0;
        }
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
            return contenido;
        }
        // La lista está vacía.
        else
        {
            return null;
        }
    }

    @Override
    public void imprimir()
    {
        Nodo temporal = primero;
        // Mientras siga habiendo elementos en la lista, se siguen imprimiendo sus valores.
        while (temporal != null)
        {
            SalidaEstandar.consola(temporal.getInfo().toString() + " --> ");
            temporal = temporal.getLigaDerecha();
        }
        SalidaEstandar.consola("Null");
    }

    @Override
    public void rellenar(Object valor, int cuantos)
    {
        agregar(valor);
    }

    @Override
    public Object getTope()
    {
        return ultimo.getInfo();
    }

    /**
     * Regresa la cantidad de elementos contenidos en la Lista.
     * @return Regresa el número de elementos en la lista.
     */
    public int getSize()
    {
        return size;
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
     * Devuelve el tipo de ordenamiento que se está efectuando en la Lista.
     * @return Regresa un enum de tipo CriterioOrden con el tipo de ordenamiento
     * en que se están almacenando los datos en la Lista.
     */
    public CriterioOrden getTipoOrdenamiento()
    {
        return tipoOrdenamiento;
    }
}
