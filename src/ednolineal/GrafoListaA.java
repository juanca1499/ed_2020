package ednolineal;

import catalogos.Arista;
import catalogos.Vertice;
import edlineal.*;
import entradasalida.SalidaEstandar;


/**
 * Clase que implementa el concepto de un grafo con
 * una lista de adyacencia.
 * EL TDA GrafoListaA, como su nombre lo dice,
 * atiende a la estructura de datos no lineal llamada
 * "Grafo". Contiene un conjunto de vértices
 * que a su vez éstos almacenan información
 * que puede ser cualquier TDA. Los vértices del grafo
 * están conectados por aristas, que definen los caminos
 * que hay entre ellos.
 * <b>Este TDA usa memoria dinámica</b>.
 * @author Clase de Estructura de Datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0.
 */
public class GrafoListaA
{
    /** Guarda el listado de los vértices, y cada elemento de la lista
     * de aquí va a tener una lista con sus vértices adyacentes. */
    protected ListaLigada listaAdyacencia;
    /** Guarda las uniones que hay entre los vértices del grafo, así
     * como el peso que hay para recorrer dicha unión. */
    protected ListaLigada listaAristas;

    /**
     * Define el constructor TDA GrafoListaA.
     */
    public GrafoListaA()
    {
        listaAdyacencia = new ListaLigada();
        listaAristas = new ListaLigada();
    }

    /**
     * Busca la lista en la que se localiza el vértice origen.
     * @param nombre Es el vértice a buscar.
     * @return Regresa la lista del vértice encontrado.
     */
    private ListaLigada buscarListaVertice(Object nombre)
    {
        listaAdyacencia.inicializaIterador();

        while (listaAdyacencia.hayNodoSiguiente())
        {
            ListaLigada listaProcesada = (ListaLigada) listaAdyacencia.obtenerSiguiente();
            // Sacar el primer elemento de la lista procesada y verificar
            // si es igual al nombre nuevo a insertar
            // si es igual, cambiar existe a true y finalizar ciclo.
            Vertice primeroLista = (Vertice)listaProcesada.regresaPrimero();
            if(primeroLista.toString().equalsIgnoreCase(nombre.toString()))
            {
                return listaProcesada;
            }
        }
        return null;
    }

    /**
     * Inserta un nuevo vértice al grafo.
     * @param nombre Es el elemento a ser agregado.
     * @return Regresa <b> true </b> si pudo ser insertado, o
     * <b> false</b> en caso contrario.
     */
    public boolean agregarVertice(Object nombre)
    {
        // Buscar si existe ya el nombre del nuevo nodo
        // recorrer todos los elementos de listaAdyacencia
        // sacar cada elemento de esa lista (que es una lista)
        // y verificar que el primer elemento de esa lista no
        // sea el que quiero insertar.
        if(buscarListaVertice(nombre) == null)
        {
            // Si no existe, creo una nueva lista con el nodo
            // nuevo como cabeza de la lista.
            ListaLigada nuevaLista = new ListaLigada();
            Vertice verticeNuevo = new Vertice(nombre);
            nuevaLista.agregar(verticeNuevo);
            // Agregando una nueva lista por cada nuevo vértice.
            listaAdyacencia.agregar(nuevaLista);
            return true;
        }
        else
        {// En caso de que ya haya un vértice con el mismo nombre.
            return false;
        }
    }


    /**
     * Genera una unión dirigida entre un par de vértices.
     * @param nombreOrigen Vértice origen de la unión.
     * @param nombreDestino Vértice destino de la unión.
     * @param peso Costo que tendrá la unión al ser recorrida.
     * @return Regresa <b>true</b> en caso de haber podido
     * generar la unión, o <b>false</b> en caso contrario.
     */
    public boolean agregarArista(Object nombreOrigen, Object nombreDestino,
                                 double peso)
    {
        ListaLigada listaOrigen = buscarListaVertice(nombreOrigen);
        ListaLigada listaDestino = buscarListaVertice(nombreDestino);

        if(listaOrigen == null || listaDestino == null)
        {// El origen no existe o el destino no existe.
            return false;
        }
        else
        {// Sí existen los dos.
            Vertice verticeDestino = (Vertice) listaDestino.regresaPrimero();
            // Buscamos si el nombre del vértice destino está en la lista
            // del vértice origen, si no está, se puede agregar al final
            // pero se agrega el vértice destino ya creado.
            Vertice verticeDestinoEncontrado = (Vertice) listaOrigen.buscar(nombreDestino);

            if(verticeDestinoEncontrado == null)
            {// No existe el nodo nuevo en la lista del origen.
                listaOrigen.agregar(verticeDestino);
                // Se agrega la unión a la lista de aristas junto con su peso.
                listaAristas.agregar(new Arista((Vertice) listaOrigen.regresaPrimero(),
                verticeDestino, peso));
                return true;
            }
            else
            {// Supuestamente el nodo destino sí existe
                // validamos que se pueda agregar un lazo,
                // es decir, el nodo origen y el destino en
                // una arista son el mismo.
                if(verticeDestino.toString().equalsIgnoreCase(
                listaOrigen.regresaPrimero().toString()))
                {
                    // Lazo o bucle.
                    listaOrigen.agregar(verticeDestino);
                    // Se agrega la unión a la lista de aristas junto con su peso.
                    listaAristas.agregar(new Arista((Vertice) listaOrigen.regresaPrimero(),
                    verticeDestino, peso));
                    return true;
                }
                else
                {// Ya existe el vértice destino en alguna posición
                    // diferente a la primera.
                    return false;
                }
            }
        }
    }

    /**
     * Genera una unión dirigida entre un par de vértices con un
     * costo predeterminado de 1.
     * @param nombreOrigen Vértice origen de la unión.
     * @param nombreDestino Vértice destino de la unión.
     * @return Regresa <b>true</b> en caso de haber podido
     * generar la unión, o <b>false</b> en caso contrario.
     */
    public boolean agregarArista(Object nombreOrigen, Object nombreDestino)
    {
        return agregarArista(nombreOrigen, nombreDestino, 1);
    }

    /**
     * Cambia el peso de una de las aristas del grafo.
     * @param nombreOrigen Vertice origen de la arista.
     * @param nombreDestino Vertice destino de la arista.
     * @param peso Costo a ser asignado.
     * @return Regresa <b>true</b> en caso de haber podido
     * asignar el peso a la arista, o <b>false</b> en caso
     * contrario.
     */
    public boolean asignarPeso(Object nombreOrigen, Object nombreDestino, double peso)
    {
        // Se comprueba la existencia de la arista.
        Arista arista = (Arista) listaAristas.
        buscar(nombreOrigen.toString() + "," + nombreDestino.toString());
        if (arista != null)
        {// La arista sí existe, se cambia el peso.
            arista.setPeso(peso);
            return true;
        }
        else
        {// La arista no existe.
            return false;
        }
    }

    /**
     * Recupera el peso de una arista del grafo.
     * @param nombreOrigen Vertice origen de la arista.
     * @param nombreDestino Vertice destino de la arista.
     * @return Regresa el peso en caso de haberlo encontrado,
     * o <b>Double.NaN</b> en caso contrario.
     */
    public double obtenerPeso(Object nombreOrigen, Object nombreDestino)
    {
        // Se comprueba la existencia de la arista.
        Arista arista = (Arista) listaAristas.
        buscar(nombreOrigen.toString() + "," + nombreDestino.toString());
        if (arista != null)
        {// La arista sí existe, se obtiene el peso.
            return arista.getPeso();
        }
        else
        {// La arista no existe.
            return Double.NaN;
        }
    }

    /**
     * Imprime los vértices y aristas que contiene
     * el grafo.
     */
    public void imprimir()
    {
        // Recorrer la lista de adyacencia
        // sacar cada lista de adentro e imprimirla.
        SalidaEstandar.consola("\n");
        listaAdyacencia.inicializaIterador();
        while(listaAdyacencia.hayNodoSiguiente())
        {
            ListaLigada listaEnlazadaDeCadaVertice =
            (ListaLigada) listaAdyacencia.obtenerSiguiente();
            listaEnlazadaDeCadaVertice.imprimir();
            SalidaEstandar.consola("\n");
        }
    }

    /**
     * Visita y guarda en formato de profundidad cada uno de
     * los vértices del grafo, partiendo de un nodo específico.
     * @param origen Vértice de donde parte el recorrido.
     * @return Regresa una lista ligada con los nodos o vértices
     * que fueron visitados.
     */
    public ListaLigada recorridoProfundidad(Object origen)
    {
        ListaLigada recorrido = new ListaLigada();
        ListaLigada marcados = new ListaLigada();
        IPila pila = new PilaLista();

        // 1.- Partir de un origen, marcarlo y meterlo en la pila.
        ListaLigada listaOrigen = buscarListaVertice(origen);

        if(listaOrigen == null)
        {// El origen no existe.
            return null;
        }
        else
        {// Si me regresa algo (una lista), sí existe, y puedo recorrer.
            marcados.agregar(origen);
            pila.push(origen);
        }

        while (!pila.vacia())
        {
            // 2.- Mientras existan elementos en la pila,
            // sacar el tope y procesarlo.
            Object nodoActual = pila.pop();
            recorrido.agregar(nodoActual);

            // 3.- Los vértices adyacentes no marcados con referencia
            // al nodo actual se meten en la pila y se marcan.
            ListaLigada listaNodoActual = buscarListaVertice(nodoActual);
            agregarAdyacentesNoMarcados(listaNodoActual, pila, marcados);
        }
        return recorrido;
    }

    /**
     * Verifica si el grafo es no dirigido.
     * @return Regresa <b>true</b> en caso
     * de serlo, o <b>false</b> en caso contrario.
     */
    public boolean esNoDirigido() {

        listaAristas.inicializaIterador();
        // Arreglo para marcar las aristas ya verificadas.
        Arreglo aristasMarcadas = new Arreglo(listaAristas.getSize());

        // Se recorren todas las aristas.
        while (listaAristas.hayNodoSiguiente() && !aristasMarcadas.llena())
        {
            // Arista a buscarse su par inverso.
            Arista aristaActual = (Arista) listaAristas.obtenerSiguiente();

            if(aristasMarcadas.buscar(aristaActual) == null)
            {// La arista actual no ha sido marcada.
                // Se marca la arista actual.
                aristasMarcadas.agregar(aristaActual);
                // Se invierte la arista actual.
                Arista aristaInversa = new Arista(
                aristaActual.getDestino(), aristaActual.getOrigen(), aristaActual.getPeso());
                // Se comprueba la existencia de la arista actual inversa.
                if(listaAristas.buscar(aristaInversa) != null)
                {
                    // Se marca la arista inversa.
                    aristasMarcadas.agregar(aristaInversa);
                }
                else
                {
                    // Con una sola arista inversa que no exista
                    // el grafo ya no es NO DIRIGIDO.
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Indica si el grafo es conexo, es decir, que exista
     * un camino para cualquier par de vértices. El grafo
     * debe ser <b>no dirigido</b>.
     * @return Regresa <b>true</b> en caso de serlo,
     * o <b>false</b> en caso contrario.
     */
    public boolean esConexo()
    {
        if(esNoDirigido())
        {// El grafo es no dirigido.
            if(!listaAdyacencia.vacia())
            {// El grafo sí contiene vértices.
                // Lista con los vértices alcanzados en el recorrido.
                ListaLigada listaVertices = recorridoProfundidad(
                ((ListaLigada)listaAdyacencia.regresaPrimero()).regresaPrimero());

                if(listaVertices.getSize() == listaAdyacencia.getSize())
                {// La cantidad de vértices alcanzados es igual al total
                    // de vértices del grafo, por tanto sí es conexo.
                    return true;
                }
                else
                {// No es conexo.
                    return false;
                }
            }
            else
            {// No hay vértices aún en el grafo.
                return false;
            }
        }
        else
        {// El grafo es dirigido, no es conexo.
            return false;
        }
    }

    /**
     * De la lista de un vértice dado, se marcan como visitados los nodos
     * con los que es adyacente y se agregan a la pila de vertices
     * próximos a visitar.
     * @param listaNodoActual Lista que contiene tanto al nodo actual
     *                        como sus nodos adyacentes.
     * @param pila Pila de vértices próximos a ser visitados.
     * @param marcados Lista de vértices que ya han sido visitados.
     */
    private void agregarAdyacentesNoMarcados(ListaLigada listaNodoActual,
                                             IPila pila, ListaLigada marcados)
    {
        listaNodoActual.inicializaIterador();
        // Excluyo el primer elemento, es decir, saco el
        // primero pero no hago nada con él.
        listaNodoActual.hayNodoSiguiente();
        while (listaNodoActual.hayNodoSiguiente())
        {// Esos sí son los vecinos.
            Vertice verticeVecino = (Vertice) listaNodoActual.obtenerSiguiente();
            if(marcados.buscar(verticeVecino.toString()) == null)
            {// No está marcado.
                // En pila.
                pila.push(verticeVecino.getNombre());
                // Marcarlo.
                marcados.agregar(verticeVecino.getNombre());
            }
        }
    }

    /**
     * Busca la unión del grafo que contiene el costo
     * más pequeño, partiendo de alguno de los vértices
     * que ya han sido marcados.
     * @param verticesMarcados Arreglo con los vértices
     *                         que ya han sido marcados.
     * @return Regresa la arista de menor peso encontrada.
     */
    private Arista buscarAristaMenorPeso(Arreglo verticesMarcados)
    {
        Arista aristaMenorP = new Arista();
        listaAristas.inicializaIterador();
        // Se compararán los pesos hasta que se tenga una primer arista
        // como referencia para comparar.
        boolean compararPesos = false;

        // Se itera la lista de aristas.
        while(listaAristas.hayNodoSiguiente())
        {
            // Se extrae la arista en curso.
            Arista aristaActual = (Arista) listaAristas.obtenerSiguiente();

            // Se verifica que el origen sea un vértice marcado y que el
            // destino no se haya marcado para no generar ciclos.
            if(verticesMarcados.buscar(aristaActual.getOrigen()) != null &&
               verticesMarcados.buscar(aristaActual.getDestino()) == null)
            {
                if(compararPesos)
                {// Ya hay una arista con peso mínimo como referencia,
                 // ya se puede comparar con las demás que se encuentren.
                    if(aristaActual.getPeso() < aristaMenorP.getPeso())
                    {// Se encontró una arista con un menor peso.
                        aristaMenorP = aristaActual;
                    }
                }
                else
                {// Aún no hay una arista con peso mínimo como referencia,
                 // se agrega la primera que se encuentre.
                    aristaMenorP = aristaActual;
                    compararPesos = true;
                }
            }
        }
        return aristaMenorP;
    }

    /**
     * Genera una lista de aristas que contienen el
     * recorrido de menor peso o costo sobre el grafo.
     * @return Regresa una lista con las aristas que
     * generar el arbol de costo mínimo.
     */
    public ListaLigada arbolCostoMinimo()
    {
        // SE ESTÁ USANDO EL ALGORITMO DE PRIM //

        // Todos los vértices del grafo deben estar conetados
        // para poder generar el árbol de costo mínimo.
        if(esConexo())
        {
            ListaLigada listaCostoMinimo = new ListaLigada();
            // Se inicializa el arreglo con el mismo tamaño de vértices
            // que posee el grafo.
            Arreglo verticesMarcados = new Arreglo(listaAdyacencia.getSize());
            // Se marca el primer vértice del grafo para tomarlo de punto
            // de partida.
            verticesMarcados.agregar(
            ((ListaLigada)listaAdyacencia.regresaPrimero()).regresaPrimero());

            // Se itera hasta que se hayan marcado todos los vértices.
            while (verticesMarcados.getLongitud() != listaAdyacencia.getSize())
            {
                // Se busca la arista con el menor costo que tenga como origen
                // alguno de los vértices marcados.
                Arista aristaMenorP = buscarAristaMenorPeso(verticesMarcados);
                // Se agrega dicha arista a la lista que será retornada.
                listaCostoMinimo.agregar(aristaMenorP);
                // Se marca el vértice destino de la arista.
                verticesMarcados.agregar(aristaMenorP.getDestino());
            }
            return listaCostoMinimo;
        }
        else
        {// El grafo no es conexo, no se puede generar el árbol de costo mínimo.
            return null;
        }
    }
}
