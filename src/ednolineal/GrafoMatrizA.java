package ednolineal;

import catalogos.Arista;
import catalogos.EtiquetaGrafo;
import catalogos.Vertice;
import edlineal.*;
import entradasalida.SalidaEstandar;
import utilidades.CaminoGrafo;
import utilidades.CriterioOrden;
import utilidades.RecorridoGrafo;


/**
 * Clase que implementa el TDA Grafo matriz de
 * adyacencia.
 * EL TDAGrafoMatrizA, como su nombre lo dice,
 * atiende a la estructura de datos no lineal llamada
 * "Grafo". Contiene un conjunto de vértices
 * que a su vez éstos almacenan información
 * que puede ser cualquier TDA. Los vértices del grafo
 * están conectados por aristas, que definen los caminos
 * que hay entre ellos.
 * <b>Este TDA usa memoria estática</b>.
 * @author Clase de Estructura de Datos.
 * @author Juan Carlos García Murillo
 * @version 1.0.
 */
public class GrafoMatrizA
{
    /** Guarda todos los vértices del grafo. */
    protected Arreglo vertices;
    /** Guarda las aristas del grafo. */
    protected Matriz aristas;
    /** Define un orden en procesamiento de ponderaciones. */
    protected CriterioOrden criterio;

    /**
     * Define el constructor TDA GrafoMatrizA.
     * @param cantVertices Número máximo de vértices que
     *                     se podrán almacenar.
     */
    public GrafoMatrizA(int cantVertices)
    {
        // Creamos nuestro arreglo para guardar los vértices.
        vertices = new Arreglo(cantVertices);
        // Creamos nuestra matriz de adyacencia.
        aristas = new Matriz(cantVertices, cantVertices);
        // Iniciamos las aristas en ceros.
        aristas.inicializar(0.0);
    }

    /**
     * Define el constructor TDA GrafoMatrizA.
     * Permite definir un orden de prioridad de acuerdo
     * a las ponderaciones que se asignen a las aristas
     * del grafo.
     * @param cantVertices Número máximo de vértices que
     *                     se podrán almacenar.
     * @param criterio Criterio de mayor valor para las
     *                 ponderaciones del grafo.
     */
    public GrafoMatrizA(int cantVertices, CriterioOrden criterio)
    {
        vertices = new Arreglo(cantVertices);
        aristas = new Matriz(cantVertices, cantVertices);
        this.criterio = criterio;
        if(criterio == CriterioOrden.DESCENDENTE)
        {
            aristas.inicializar(Double.MAX_VALUE); // + Inf.
        }
        else
        {
            aristas.inicializar(Double.MIN_VALUE); // - Inf.
        }
        aristas.rellenarDiagonal(0.0);
    }

    /**
     * Inserta un nuevo vértice al grafo.
     * @param nombre Es el elemento a ser agregado.
     * @return Regresa <b> true </b> si pudo ser insertado, o
     * <b> false </b> en caso contrario.
     */
    public boolean agregarVertice(Object nombre)
    {
        // Validar que no exista.
        // Buscamos su índice usando su nombre.
        Integer encontrado = (Integer) vertices.buscar(nombre);

        if(encontrado == null)
        {// No existe.
            // Creamos el nuevo vértice.
            Vertice verticeNuevo = new Vertice(nombre);
            // Obtenemos su número usando el máximo + 1;
            int numVertice = vertices.getLongitud();
            // Asignamos su número.
            verticeNuevo.setNumVertice(numVertice);
            return vertices.agregar(verticeNuevo);
        }
        else
        {// Ya hay uno con ese nombre.
            return false;
        }
    }

    /**
     * Genera una unión dirigida entre un par de vértices.
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
     * Genera una unión dirigida entre un par de vétices.
     * @param nombreOrigen Vértice origen de la unión.
     * @param nombreDestino Vértice destino de la unión.
     * @param peso Costo que tendrá la artista por ser recorrida.
     * @return Regresa <b>true</b> en caso de haber podido
     * generar la unión, o <b>false</b> en caso contrario.
     */
    public boolean agregarArista(Object nombreOrigen, Object nombreDestino, double peso)
    {
        // Comprobamos la existencia de los vértices en el grafo.
        Integer numVerticeOrigen = (Integer)vertices.buscar(nombreOrigen);
        Integer numVerticeDestino = (Integer)vertices.buscar(nombreDestino);

        // Si existen, entonces podemos crear una adyacencia, usando un peso.
        if(numVerticeOrigen != null && numVerticeOrigen != null)
        {// Existen los dos vértices.
            return aristas.setElemento(numVerticeOrigen, numVerticeDestino, peso);
        }
        else
        { // Los vértices son inválidos.
            return false;
        }
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
        // Comprobamos la existencia de los vértices en el grafo.
        Integer numVerticeOrigen = (Integer)vertices.buscar(nombreOrigen);
        Integer numVerticeDestino = (Integer)vertices.buscar(nombreDestino);

        if(numVerticeOrigen != null && numVerticeOrigen != null)
        {// Los vértices sí existen.
            aristas.setElemento(numVerticeOrigen, numVerticeDestino, peso);
            return true;
        }
        else
        {// Uno o los dos vértices no existen en el grafo.
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
        // Comprobamos la existencia de los vértices en el grafo.
        Integer numVerticeOrigen = (Integer)vertices.buscar(nombreOrigen);
        Integer numVerticeDestino = (Integer)vertices.buscar(nombreDestino);

        if(numVerticeOrigen != null && numVerticeOrigen != null)
        {// Los vértices sí existen.
            return (double)aristas.getElemento(numVerticeOrigen, numVerticeDestino);
        }
        else
        {// Uno o los dos vértices no existen en el grafo.
            return Double.NaN;
        }
    }

    /**
     * Imprime los vértices y aristas que contiene
     * el grafo.
     */
    public void imprimir()
    {
        SalidaEstandar.consola("Vértices:\n");
        vertices.imprimir();
        SalidaEstandar.consola("Aristas:\n");
        aristas.imprimir();
    }

    /**
     * Calcula el número de vértices que inciden
     * con un vértice dado.
     * @param nombreVertice Vértice a calcular sus incidencias.
     * @return Regresa la cantidad de incidencias que se encontraron
     * sobre el vértice en caso de que éste sí exista en el grafo, o
     * <b>-1</b> en caso de no existir el vértice en el grafo.
     */
    public int numIncidencias(Object nombreVertice)
    {
        // Primero buscamos que sí existan vértices con ese nombre.
        Integer numVertice = (Integer) vertices.buscar(nombreVertice);

        if(numVertice != null)
        {// Sí existe.
            // Hay que buscar los vértices que llegan a él, es decir, qué partes de cualquier lado
            // y que llegan a él. Se debe recorrer todos los vértices para verificar los que llegan a él.
            // Por tanto se debe verificar todos los renglones para una sola columna, donde cada renglón
            // es el origen y el destino es cada columna, pero sólo nos interesa una sola columna que es la
            // del nodo que se busca en este método "nombreVertice".
            int numIncidencias = 0;
            for(int numVerticeOrigen = 0; numVerticeOrigen < vertices.getLongitud(); numVerticeOrigen++)
            {
                // Verificamos si hay un valor en esa rejilla de la matriz,
                // desde cada origen al destino buscado.
                if((esPesoValido((double)aristas.getElemento(numVerticeOrigen, numVertice))
                    && aristas.getElemento(numVerticeOrigen, numVertice) != null))
                {// Si hay incidencia, entonces se incrementa el contador de éstas.
                    numIncidencias++;
                }
            }
            // Regresa cero si no hay, o un número positivo si hay incidencia.
            return numIncidencias;
        }
        else
        {// No existe.
            return -1;
        }
    }

    /**
     * Suprime un nodo o vértice del grafo.
     * @param nombreVertice Vértice a ser eliminado.
     * @return Regresa la información del vértice que fue
     * eliminado, o <b>null</b> en caso de no haberse podido
     * eliminar.
     */
    public Object eliminarVertice(Object nombreVertice)
    {
        // Comprobamos la existencia del vértice.
        Integer posVertice = (Integer) vertices.buscar(nombreVertice);

        if(posVertice != null)
        {// El vértice sí existe.
            Vertice contenido = (Vertice) vertices.getElemento(posVertice);

            // Eliminamos las aristas incidentes al vértice.
            recorrerRenglonVertice(posVertice.intValue());
            recorrerColumnaVertice(posVertice.intValue());
            // Eliminamos el vértice del arreglo.
            vertices.eliminar(posVertice.intValue());

            // Se reacomodan los números de los vértices.
            for(int index = posVertice; index < vertices.getLongitud(); index++)
            {
                Vertice vertice = (Vertice) vertices.getElemento(index);
                vertice.setNumVertice(index);
            }
            return contenido.getNombre();
        }
        else
        {// El vértice no existe.
            return null;
        }
    }

    /**
     * Recorre los valores de los renglones que están por delante del
     * que le correspondía al vértice eliminado en la matriz de
     * adyacencia.
     * @param renglon Índice del renglón a ser eliminado.
     */
    public void recorrerRenglonVertice(int renglon)
    {
        int cantRenglones = aristas.numRenglones();
        int cantColumnas = aristas.numColumnas();

        if(renglon >= 0 && renglon < cantRenglones)
        {// El renglón tiene una posición válida en la matriz.

            // Partimos de la fila siguiente a anular y recorremos
            // los datos de las que están delante.
            for(int numRenglon = (renglon + 1); numRenglon < cantRenglones; numRenglon++)
            {
                for(int numColumna = 0; numColumna < cantColumnas; numColumna++)
                {
                    aristas.setElemento( (numRenglon - 1), numColumna,
                    aristas.getElemento(numRenglon, numColumna));
                }
            }
            // Se asignan ceros al último renglón.
            for(int numColumna = 0; numColumna < cantColumnas; numColumna++)
            {
                aristas.setElemento( (cantRenglones - 1), numColumna, 0.0);
            }
        }
    }

    /**
     * Recorre los valores de las columnas que están por delante de la
     * que le correspondía al vértice eliminado en la matriz de
     * adyacencia.
     * @param columna Índice de la columna a ser eliminada.
     */
    public void recorrerColumnaVertice(int columna)
    {
        int cantRenglones = aristas.numRenglones();
        int cantColumnas = aristas.numColumnas();

        if(columna >= 0 && columna < cantColumnas)
        {// La columna tiene una posición válida en la matriz.

            // Partimos de la columna siguiente a borrar y recorremos
            // los datos de las que están delante.
            for(int numRenglon = 0; numRenglon < cantRenglones; numRenglon++)
            {
                for(int numColumna = (columna + 1); numColumna < cantColumnas; numColumna++)
                {
                    aristas.setElemento(numRenglon, (numColumna - 1),
                    aristas.getElemento(numRenglon, numColumna));
                }
            }
            // Se asignan ceros a la última columna.
            for(int numRenglon = 0; numRenglon < cantRenglones; numRenglon++)
            {
                aristas.setElemento(numRenglon, (cantColumnas - 1), 0.0);
            }
        }
    }

    /**
     * Indica si hay adyacencia o unión desde un vértice
     * a otro.
     * @param nombreOrigen Vértice origen de la unión.
     * @param nombreDestino Vértice destino de la unión.
     * @return Regresa <b>true</b> en caso de existir
     * la adyacencia o unión, o <b>false</b> en caso
     * contrario.
     */
    public boolean esAdyacente(Object nombreOrigen, Object nombreDestino)
    {
        // Comprobamos la existencia de los dos vértices.
        Integer posVerticeOrigen = (Integer) vertices.buscar(nombreOrigen);
        Integer posVerticeDestino = (Integer) vertices.buscar(nombreDestino);

        if(posVerticeOrigen != null && posVerticeDestino != null)
        {// Los dos vértices existen en el grafo.

            if(esPesoValido((double)aristas.getElemento(posVerticeOrigen, posVerticeDestino)))
            {// Sí existe la adyacencia.
                return true;
            }
            // No hay adyacencia entre los vértices.
            return false;
        }
        else
        {// Uno o los dos vértices no existen en el grafo.
            return false;
        }
    }

    /**
     * Quita una unión, adyacencia o arista entre
     * un par de vértices.
     * @param nombreOrigen Vértice origen de la arista.
     * @param nombreDestino Vertice destino de la arista.
     * @return Regresa <b>true</b> en caso de haber podido
     * eliminar la arista, o <b>false</b> en caso contrario,
     * puede ser que no exista la arista o uno de los vértices
     * no esté en el grafo.
     */
    public boolean eliminarArista(Object nombreOrigen, Object nombreDestino)
    {
        // Comprobamos la existencia de los dos vértices.
        Integer posVerticeOrigen = (Integer) vertices.buscar(nombreOrigen);
        Integer posVerticeDestino = (Integer) vertices.buscar(nombreDestino);

        if(posVerticeOrigen != null && posVerticeDestino != null)
        {// Los dos vértices existen en el grafo.

            if(esPesoValido((double)aristas.getElemento(posVerticeOrigen, posVerticeDestino)))
            {// La arista existe y se elimina.
                aristas.setElemento(posVerticeOrigen, posVerticeDestino, 0.0);
                return true;
            }
            else
            {// La arista no existía.
                return false;
            }
        }
        else
        {// Uno o los dos vértices no existen en el grafo.
            return false;
        }
    }

    /**
     * Verifica si el grafo tiene lazos o bucles.
     * @return Regresa <b>true</b> si por lo menos
     * hay un bucle o lazo, o <b>false</b> si no
     * existe ninguno.
     */
    public boolean esPseudografo()
    {
        // Se obtiene el número de lazos.
        int numLazos = lazos();
        if(numLazos > 0)
        {// Por lo menos hay un bucle.
            return true;
        }
        else
        {// No existe ningún lazo.
            return false;
        }
    }

    /**
     * Calcula la cantidad de lazos o bucles
     * que tiene el grafo.
     * @return Regresa el número de lazos encontrados.
     */
    private int lazos()
    {
        int numLazos = 0;
        // Se recorre la diagonal principal de la matriz
        // de adyacencia en busca de lazos.
        for(int index = 0; index < vertices.getLongitud(); index++)
        {
            if(!aristas.getElemento(index, index).equals(0.0))
            {// Se encontró un lazo.
                numLazos++;
            }
        }
        return numLazos;
    }

    /**
     * Verifica si el grafo tiene aristas
     * múltiples o paralelas.
     * @return Regresa <b>true</b> si el grafo tiene
     * por los menos un par de aristas paralelas, o <b>false</b>
     * en caso contrario.
     */
    public boolean esMultigrafo()
    {
        // Se obtiene el número de aristas paralelas.
        int numParalelas = aristasParalelas();
        if(numParalelas > 0)
        {// Por lo menos hay una arista paralela.
            return true;
        }
        else
        {// No existe ninguna arista paralela.
            return false;
        }
    }

    /**
     * Calcula la cantidad de pares de aristas paralelas
     * que tiene el grafo.
     * @return Regresa el número de aristas paralelas
     * encontradas.
     */
    private int aristasParalelas()
    {
        int numParalelas = 0;

        // Se recorre la matriz de adyacencia para contar la existencia de
        // aristas paralelas.
        for(int numRenglon = 0; numRenglon < vertices.getLongitud(); numRenglon++)
        {
            for(int numColumna = 0; numColumna < vertices.getLongitud(); numColumna++)
            {
                // 1.- Se omiten las posiciones de la diagonal principal.
                // 2.- Se comprueba la existencia de una arista.
                // 2.1.- En caso de existir una arista, se comprueba
                // que también haya una con las posiciones invertidas en la
                // matriz, esto indicaría que las aristas son paralelas.
                if(numRenglon != numColumna &&
                esPesoValido((double)aristas.getElemento(numRenglon, numColumna)) &&
                esPesoValido((double)aristas.getElemento(numColumna, numRenglon)))
                {// Se encontraron aristas paralelas.
                    numParalelas++;
                }
            }
        }
        return numParalelas / 2;
    }

    /**
     * Calcula el número de aristas que llegan hacia el vértice
     * o llegan hacia él.
     * @param nombreVertice Vértice a calcular su grado.
     * @return Regresa un número mayor o igual a 0 con el grado
     * del vértice obtenido, o -1 en caso de que el elemento
     * nisiquiera se encuentre en el grafo.
     */
    public int gradoVertice(Object nombreVertice)
    {
        Integer posVertice = (Integer) vertices.buscar(nombreVertice);

        // Se comprueba la existencia del vértice en el grafo.
        if(posVertice != null)
        {// El vértice sí existe en el grafo.
            int grado = 0;

            // Se recorre la columna del vértice renglón
            // por renglón en la matriz de adyacencia.
            for(int numRenglon = 0; numRenglon < vertices.getLongitud(); numRenglon++)
            {
                // Se evita contar la posición del recorrido que coincide
                // con el renglón del vértice en la matriz de adyacencia.
                if(numRenglon != posVertice &&
                (esPesoValido((double)aristas.getElemento(numRenglon, posVertice))))
                {// Se encontró una arista que tiene como destino el vértice.
                    grado++;
                }
            }
            // Se recorre el renglón del vértice
            // columna por columna en la matriz de adyacencia.
            for(int numColumna = 0; numColumna < vertices.getLongitud(); numColumna++)
            {
                if((esPesoValido((double)aristas.getElemento(posVertice, numColumna))))
                {// Se encontró una arista que tiene como origen el vértice.

                    if(posVertice == numColumna)
                    {// Se encontró un lazo y vale por dos.
                        grado = grado + 2;
                    }
                    else
                    {
                        grado++;
                    }
                }
            }
            return grado;
        }
        else
        {// El vértice no existe en el grafo.
            return -1;
        }
    }

    /**
     * Dado un vértice como origen y otro como destino,
     * intenta realizar un camino válido sobre el grafo.
     * @param nombreOrigen Vértice del que parte el camino.
     * @param nombreDestino Vértice donde termina el camino.
     * @param tipoCamino Tipo de camino que deberá buscar cumplir
     *                   la ruta generada sobre el grafo.
     * @return Regresa una implementación de la interfaz Lista
     * con los vértices recorridos hasta llegar al vértice destino.
     * Por el funcionamiento del algoritmo el camino estará en sentido inverso,
     * es decir, en la última posición estará el origen y en la primera el destino.
     * En caso de no haber un camino válido regresa <b>null.</b>
     */
    private Lista generarCamino(Object nombreOrigen, Object nombreDestino, CaminoGrafo tipoCamino)
    {
        // Se busca el índice de los vértices de origen y destino.
        Integer posVerticeOrigen = (Integer) vertices.buscar(nombreOrigen);
        Integer posVerticeDestino = (Integer) vertices.buscar(nombreDestino);

        if(posVerticeOrigen != null && posVerticeDestino != null)
        {// Los vértices sí existen.

            // Guarda las aristas que ya han sido recorridas.
            Arreglo aristasRecorridas = new Arreglo(aristas.numRenglones() * 2);
            // Guarda el recorrido hecho y servirá para regresar de vértices que no
            // tengan salida en el grafo.
            Lista listaCamino = new Arreglo(aristas.numRenglones() * 2);
            listaCamino.agregar(vertices.getElemento(posVerticeDestino));
            // Posiciones actuales en la matriz de adyacencia.
            int numFila = 0;
            // Comenzamos desde el vértice destino para desde un
            // principio comprobar si algún vértice es adyacente a él,
            // en caso contrario, no hay camino.
            int numColumna = posVerticeDestino;

            // Comprobamos que la lista del camino no esté vacía.
            while (!listaCamino.vacia())
            {
                if(listaCamino.getTope().toString().equals(nombreOrigen.toString())  &&
                !aristasRecorridas.vacia())
                {// Se llegó al vértice del  origen.
                    break;
                }
                else
                {
                    if(numFila == vertices.getLongitud())
                    {// No sé encontró ningún vértice que apunte al que está en curso.
                        // Eliminamos el vértice que ya no tiene camino a ningún lugar.
                        Vertice vertice = (Vertice) listaCamino.eliminarTope();

                        if(listaCamino.getTope() != null)
                        {// Regresamos a la columna del vértice anterior en la siguiente
                         // posición de la fila que nos quedamos.
                            numFila = vertice.getNumVertice() + 1;
                            numColumna = ((Vertice) listaCamino.getTope()).getNumVertice();
                        }
                        else
                        {// No hay vértices para retroceder; no hay camino.
                            return null;
                        }
                    }
                    else if(esPesoValido((double)aristas.getElemento(numFila, numColumna)))
                    {// Se encontró un vértice que apunta al que está en curso.

                        // Formamos el recorrido de la arista para comprobar que no sea exactamente
                        // igual, y por tanto, se esté ciclando el algoritmo.
                        Arista arista = new Arista();
                        arista.setOrigen((Vertice) vertices.getElemento(numFila));
                        arista.setDestino((Vertice) vertices.getElemento(numColumna));

                        if(aristasRecorridas.buscar(arista) == null)
                        {// No se encontró una arista exáctamente igual.

                            if(tipoCamino == CaminoGrafo.NORMAL || tipoCamino == CaminoGrafo.CERRADO)
                            {// La ruta a generarse no es simple.
                                listaCamino.agregar(vertices.getElemento(numFila));
                                aristasRecorridas.agregar(arista);
                                // Nos movemos en la matriz hacia la columna
                                // del vértice que apunta al que tenemos en curso.
                                numColumna = numFila;
                                numFila = 0;
                            }
                            else if(tipoCamino == CaminoGrafo.SIMPLE)
                            {// La ruta a generarse es simple.
                                Vertice vertice = (Vertice) vertices.getElemento(numFila);
                                if(listaCamino.buscar(vertice) == null || vertice.toString().equals(nombreOrigen))
                                {// El vértice en curso no se repite.
                                    listaCamino.agregar(vertices.getElemento(numFila));
                                    aristasRecorridas.agregar(arista);
                                    // Nos movemos en la matriz hacia la columna
                                    // del vértice que apunta al que tenemos en curso.
                                    numColumna = numFila;
                                    numFila = 0;
                                }
                                else
                                {
                                    numFila++;
                                }
                            }
                        }
                        else
                        {// Se repitió una arista, se evita el ciclado del algoritmo.
                            numFila++;
                        }
                    }
                    else
                    {// Se sigue avanzando en la columna del vértice en curso en busca
                    //  de alguno que apunte hacia él.
                        numFila++;
                    }
                }
            }
            return listaCamino;
        }
        else
        {// Uno, o los dos vértices no existe(n).
            return null;
        }
    }

    /**
     * Determinar si existe un camino válido entre dos
     * vértices proporcionados como argumentos.
     * @param nombreOrigen Vértice donde comienza el camino.
     * @param nombreDestino Vertice donde termina el camino.
     * @return Regresa <b>true</b> si existe un camino válido
     * entre los vértices dados, o <b>false</b> en caso contrario.
     */
    public boolean hayRuta(Object nombreOrigen, Object nombreDestino)
    {
        if(generarCamino(nombreOrigen, nombreDestino, CaminoGrafo.NORMAL) != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Indica si hay un camino válido partiendo de un vértice y
     * llegando hacia éste mismo.
     * @param nombreOrigen Vértice donde comienza el camino.
     * @param nombreDestino Vertice donde termina el camino.
     * la condición de ser camino cerrado, o <b>false</b> en caso
     * contrario.
     * @return Regresa <b>true</b> si la ruta generada cumple con
     * la condición de ser camino cerrado, o <b>false</b> en caso
     * contrario.
     */
    public boolean esCaminoCerrado(Object nombreOrigen, Object nombreDestino)
    {
        if(nombreOrigen.equals(nombreDestino) &&
            generarCamino(nombreOrigen, nombreDestino, CaminoGrafo.CERRADO) != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Indica si los vértices del camino generado entre el origen
     * y el destino son distintos, excepto el primero y el último.
     * @param nombreOrigen Vértice donde comienza el camino.
     * @param nombreDestino Vértice donde termina el camino.
     * @return Regresa <b>true</b> si la ruta generada cumple con
     * la condición de ser camino simple, o <b>false</b> en caso
     * contrario.
     */
    public boolean esCaminoSimple(Object nombreOrigen, Object nombreDestino)
    {
        if(generarCamino(nombreOrigen, nombreDestino, CaminoGrafo.SIMPLE) != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Define el órden en que se tienen que ejecutar los procesos
     * contenidos en los vértices o nodos del grafo.
     * @return Regresa una lista ligada con el órden de ejecución
     * que tienen que seguir los procesos.
     */
    public ListaLigada ordenacionTopologica()
    {
        // Validar que el grafo no tenga ciclos o lazos.
        if(!esPseudografo())
        {// El grafo no tiene lazos.
            // Es la salida del método.
            ListaLigada ordenacionT = new ListaLigada();
            Arreglo incidenciasV = new Arreglo(vertices.getLongitud());
            Arreglo verticesYaAnalizados = new Arreglo(vertices.getLongitud());
            Cola colaAnalizados = new Cola(vertices.getLongitud());

            // 1.- Sacar las incidencias del grafo.
            // Meter los valores por defecto en el arreglo de analizados.
            verticesYaAnalizados.rellenar(0, verticesYaAnalizados.capacidad()); // Lenamos el arreglo con ceros.
            inicializaIncidencias(incidenciasV); // Invocar a inicializar incidencias.

            // 2.- Los vértices con incidencias en cero, se meten en la cola
            // y se marcan como analizados.
            // Invocar al método que mete en la cola los de incidencias en cero y los merca.
            marcarYEncolarIncidencias(verticesYaAnalizados, incidenciasV, colaAnalizados);

            while (!colaAnalizados.vacia())
            {
                // 3.- Sacar el siguiente elemento de la cola y procesarlo.
                Vertice verticeActual = (Vertice) colaAnalizados.eliminar();
                ordenacionT.agregar(verticeActual);

                // 4.- Recalcular incidencias acorde al paso 3.
                recalcularIncidencias(verticeActual, incidenciasV);

                // 5.- Meter en la cola los nodos que obtuvieron incidencias en cero,
                // y que estos no estaban marcados, una vez metidos en la cola se marcan como analizados.
                marcarYEncolarIncidencias(verticesYaAnalizados, incidenciasV, colaAnalizados);
            }
            return ordenacionT;
        }
        else
        {// El grafo tiene lazos, no puede realizarse la ordenación.
            return null;
        }
    }

    /**
     * Calcula el número de aristas que inciden inicialmente sobre cada uno
     * de los vértices del grafo.
     * @param invidenciasV Arreglo paralelo al de los vértices dónde
     *                     en cada posición se indica el número de aristas
     *                     que inciden sobre cada vértice.
     */
    private void inicializaIncidencias(Arreglo incidenciasV)
    {
        // Los distintos posibles.
        for(int nVertD = 0; nVertD < vertices.getLongitud(); nVertD++)
        {
            Vertice verticeD = (Vertice) vertices.getElemento(nVertD);
            // Calculamos el num de incidencias de este vértice.
            int numIncidenciasDestino = numIncidencias(verticeD.getNombre()); // Se inicializa por cada destino.
            // Calculado el número de incidencias, lo meto en el arreglo.
            incidenciasV.agregar(numIncidenciasDestino);
        }
    }

    /***
     * Verifica los vértices que ya no dependen de ningún proceso
     * y lo agrega a la cola de analizados, además los marca para
     * que ya no vuelvan a ser agregados a la cola de analizados.
     * @param verticesYaAnalizados Arreglo de vértices que ya han sido
     *                             marcados como analizados.
     * @param incidenciasV Arreglo de vértices que indica las incidencias
     *                     que tiene cada vértice.
     * @param colaAnalizados Cola que indica los vértices que ya pueden ser
     *                       analizados.
     */
    private void marcarYEncolarIncidencias(Arreglo verticesYaAnalizados, Arreglo incidenciasV,
                                      Cola colaAnalizados)
    {
        // Rcorrer todos los elementos de las incidencias.
        for(int numVertI = 0; numVertI < incidenciasV.getLongitud(); numVertI++)
        {
            int numIncidenciasV = (int) incidenciasV.getElemento(numVertI);
            if(((int) verticesYaAnalizados.getElemento(numVertI)) == 0 &&
            numIncidenciasV == 0)
            {
                // Si el vértice no está ya marcado como analizado
                // y su número de incidencias es cero,
                // marcarlo como analizado y meterlo en la cola.
                colaAnalizados.agregar(vertices.getElemento(numVertI));
                verticesYaAnalizados.setElemento(numVertI, 1);
            }
        }
    }

    /**
     * Actualiza el número de incidencias de los vértices que
     * tienen como origen de la arista a un vértice que ya fue
     * analizado.
     * @param verticeOrigen Vertice ya analizado origen de
     *                      las incidencias.
     * @param incidenciasV Arreglo que contiene el número de incidencias
     *                     de cada vértice en el grafo.
     */
    private void recalcularIncidencias(Vertice verticeOrigen, Arreglo incidenciasV)
    {
        // Recorrer todos los destinos partiendo del vértice origen.
        for(int numVertD = 0; numVertD < incidenciasV.getLongitud(); numVertD++)
        {
            if(aristas.getElemento(verticeOrigen.getNumVertice(), numVertD) != null &&
            esPesoValido((double)aristas.getElemento(verticeOrigen.getNumVertice(), numVertD)))
            {// Ese origen tiene liga con el destino.
                int numIncActualVDestino = (int) incidenciasV.getElemento(numVertD);
                incidenciasV.setElemento(numVertD, numIncActualVDestino - 1);
            }
        }
    }

    /**
     * De un vértice dado, se marcan como visitados los nodos
     * con los que es adyacente y se agregan a la pila de vertices
     * próximos a visitar.
     * @param vertice Nodo o vértice a buscar los nodos con los cuales
     *                es adyacente.
     * @param pilaVertices Pila de vértices próximos a ser visitados.
     * @param verticesMarcados Arreglo de vértices que ya han sido
     *                         visitados.
     */
    private void agregarYMarcarAdyacencias(Vertice vertice, IPila pilaVertices,
                                           Arreglo verticesMarcados)
    {
        if(vertice != null && pilaVertices != null && verticesMarcados != null)
        {// Los argumentos son válidos.

            // Se extrae el índice del vértice para poder validar en la
            // matriz de aristas cuáles son los nodos que tiene como destino.
            int numFila = (int) vertice.getNumVertice();

            // Se iteran todas las posiciones de la fila.
            for(int numColumna = 0; numColumna < vertices.getLongitud(); numColumna++)
            {
                if((esPesoValido((double)aristas.getElemento(numFila, numColumna)) &&
                (int)verticesMarcados.getElemento(numColumna) == 0))
                {// Se encontró un vértice adyacente y que no ha sido marcado.
                    // Se obtiene el vértice que es adyacente.
                    Vertice verticeAdyacente = (Vertice) vertices.getElemento(numColumna);
                    // Se agrega a la pila el vértice.
                     pilaVertices.push(verticeAdyacente);
                    // Se marca el vértice.
                    verticesMarcados.setElemento(numColumna, 1);
                }
            }
        }
    }

    /**
     * De un vértice dado, se marcan como visitados los nodos
     * con los que es adyacente y se agregan a la cola de vertices
     * próximos a visitar.
     * @param vertice Nodo o vértice a buscar los nodos con los cuales
     *                es adyacente.
     * @param colaVertices Cola de vértices próximos a ser visitados.
     * @param verticesMarcados Arreglo de vértices que ya han sido
     *                         visitados.
     */
    private void agregarYMarcarAdyacencias(Vertice vertice, ICola colaVertices,
                                           Arreglo verticesMarcados)
    {
        if(vertice != null && colaVertices != null && verticesMarcados != null &&
        verticesMarcados.getLongitud() >= 0)
        {// Los argumentos son válidos.

            // Se extrae el índice del vértice para poder validar en la
            // matriz de aristas cuáles son los nodos que tiene como destino.
            int numFila = (int) vertice.getNumVertice();

            // Se iteran todas las posiciones de la fila.
            for(int numColumna = 0; numColumna < vertices.getLongitud(); numColumna++)
            {
                if((esPesoValido((double)aristas.getElemento(numFila, numColumna))  &&
                    (int)verticesMarcados.getElemento(numColumna) == 0))
                {// Se encontró un vértice adyacente.
                    // Se obtiene el vértice que es adyacente.
                    Vertice verticeAdyacente = (Vertice) vertices.getElemento(numColumna);
                    // Se agrega a la pila el vértice.
                    colaVertices.agregar(verticeAdyacente);
                    // Se marca el vértice.
                    verticesMarcados.setElemento(numColumna, 1);
                }
            }
        }
    }

    /**
     * Visita y guarda cada uno de los vértices del grafo.
     * @param tipoRecorrido  Tipo de recorrido a realizar
     *                       sobre el grafo.
     * @return Regresa una lista ligada que contiene una o
     * varias listas ligadas con los vértices visitados, o null
     * en caso de que el grafo aún no contenga ningún nodo.
     */
    public ListaLigada recorrer(RecorridoGrafo tipoRecorrido)
    {
        if(!vertices.vacia())
        {// El grafo sí contiene vértices.
            // Lista que guarda los recorridos.
            ListaLigada listaRecorrido = new ListaLigada();
            // Arreglo paralelo al de los vértices del grafo
            // con la posiciones de los nodos ya visitados.
            Arreglo verticesMarcados = new Arreglo(vertices.getLongitud());
            // Se asigna a todas las posiciones del arreglo 0; esto
            // quiere decir que ninguno ha sido visitado hasta el momento.
            verticesMarcados.rellenar(0, vertices.getLongitud());
            IPila pilaVertices = null;
            ICola colaVertices = null;

           if(tipoRecorrido == RecorridoGrafo.DFS)
           {// Se indicó un recorrido en profundidad.
               // Vértices próximos a ser visitados.
               pilaVertices = new Pila(vertices.getLongitud());
               // Se realiza un primer recorrido, si no se indica un vértice
               // específico de inicio siempre se comienza con el primero que fue agregado al grafo.
               listaRecorrido.agregar(recorrerDFS((Vertice) vertices.getElemento(0),
                       pilaVertices, verticesMarcados));
           }
           else if(tipoRecorrido == RecorridoGrafo.BFS)
           {// Se indicó un recorrido en anchura.
               // Vértices próximos a ser visitados.
               colaVertices = new Cola(vertices.getLongitud());
               // Se realiza un primer recorrido, si no se indica un vértice
               // específico de inicio siempre se comienza con el primero que fue agregado al grafo.
               listaRecorrido.agregar(recorrerBFS((Vertice) vertices.getElemento(0),
                       colaVertices, verticesMarcados));
           }
            // Verificamos si aún hay vértices sin marcar o visitar.
            Integer posVerticeSinMarcar = (Integer) verticesMarcados.buscar(0);

            while (posVerticeSinMarcar != null)
            {// Se vuelve a ejecutar el recorrido elegido hasta que no haya
            // más vértices sin marcar.

                if(tipoRecorrido == RecorridoGrafo.DFS)
                {// Se indicó un recorrido en profundidad.
                    // Se agregan vértices que no habían sido marcados.
                    listaRecorrido.agregar(
                    recorrerDFS((Vertice) vertices.getElemento(posVerticeSinMarcar),
                    pilaVertices, verticesMarcados));
                }
                else if(tipoRecorrido == RecorridoGrafo.BFS)
                {// Se indicó un recorrido en anchura.
                    // Se agregan vértices que no habían sido marcados.
                    listaRecorrido.agregar(
                    recorrerBFS((Vertice) vertices.getElemento(posVerticeSinMarcar),
                    colaVertices, verticesMarcados));
                }
                // Se vuelve a verificar si aún hay vértices sin marcar o visitar.
                posVerticeSinMarcar = (Integer) verticesMarcados.buscar(0);
            }
            return listaRecorrido;
        }
        else
        {// El grafo aún no contiene ningún vértice.
            return null;
        }
    }

    /**
     * Visita y guarda en formato de profundidad cada uno de
     * los vértices del grafo que no han sido visitados, partiendo
     * de un vértice específico.
     * @param inicio Vértice de donde parte el recorrido.
     * @param pilaVertices Vértices próximos a visitar.
     * @param verticesMarcados Vértices ya visitados.
     * @return Regresa una lista ligada con los nodos o vértices
     * que fueron visitados.
     */
    private ListaLigada recorrerDFS(Vertice verticeInicio, IPila pilaVertices,
                                    Arreglo verticesMarcados)
    {
         ListaLigada listaRecorrido = new ListaLigada();
         // 1.- Se marca el vértice de partida como visitado.
         verticesMarcados.setElemento(verticeInicio.getNumVertice(), 1);
         // 2.- Se mete en la pila el vértice de partida.
         pilaVertices.push(verticeInicio);

         // 3.- Repetir hasta que la pila esté vacía.
         while (!pilaVertices.vacia())
         {
             // 4.- Quitar el nodo tope de la pila.
             Vertice verticeActual = (Vertice) pilaVertices.pop();
             // 4.1.- Visitar el vértice.
             listaRecorrido.agregar(verticeActual);
             // 5.- Meter en la pila todos los vértices adyacentes a él que no
             // estén marcados y, a continuación marcarlos.
             agregarYMarcarAdyacencias(verticeActual, pilaVertices, verticesMarcados);
         }
         return listaRecorrido;
    }

    /**
     * Visita en formato de anchura y guarda cada uno de
     * los vértices del grafo que no han sido visitados,
     * partiendo de un vértice específico.
     * @param inicio Vértice de donde parte el recorrido.
     * @param colaVertices Vértices próximos a visitar.
     * @param verticesMarcados Vértices ya visitados.
     * @return Regresa una lista ligada con los nodos o vértices
     * que fueron visitados.
     */
    private ListaLigada recorrerBFS(Vertice verticeInicio, ICola colaVertices,
                                    Arreglo verticesMarcados)
    {
        ListaLigada listaRecorrido = new ListaLigada();
        // 1.- Se marca el vértice de partida como visitado.
        verticesMarcados.setElemento(verticeInicio.getNumVertice(), 1);
        // 2.- Se mete en la cola el vértice de partida.
        colaVertices.agregar(verticeInicio);

        // 3.- Repetir hasta que la cola esté vacía.
        while (!colaVertices.vacia())
        {
            // 4.- Quitar el primer nodo de la cola.
            Vertice verticeActual = (Vertice) colaVertices.eliminar();
            // 4.1.- Visitar el vértice.
            listaRecorrido.agregar(verticeActual);
            // 5.- Meter en la cola todos los vértices adyacentes a él que no
            // estén marcados y, a continuación marcarlos.
            agregarYMarcarAdyacencias(verticeActual, colaVertices, verticesMarcados);
        }
        return listaRecorrido;
    }

    /**
     * Busca un elemento en el grafo.
     * @param nombreBuscado Elemento a ser buscado.
     * @return Regresa el elemento encontrado, o null en caso
     * de que no exista en el grafo.
     */
    public Object buscar(Object nombreBuscado)
    {
        if(!vertices.vacia())
        {// El grafo sí contiene vértices.
            // Arreglo paralelo al de los vértices del grafo
            // con la posiciones de los nodos ya visitados.
            Arreglo verticesMarcados = new Arreglo(vertices.getLongitud());
            // Se asigna a todas las posiciones del arreglo 0; esto
            // quiere decir que ninguno ha sido visitado hasta el momento.
            verticesMarcados.rellenar(0, vertices.getLongitud());
            // Vértices próximos a ser visitados.
            ICola colaVertices = new Cola(vertices.getLongitud());
            // Se realiza una primera búsqueda partiendo del primer vértice agregado al grafo.
            Object contenido = buscar(nombreBuscado, (Vertice) vertices.getElemento(0),
            colaVertices, verticesMarcados);
            // Verificamos si aún hay vértices sin marcar o visitar.
            Integer posVerticeSinMarcar = (Integer) verticesMarcados.buscar(0);

            while (posVerticeSinMarcar != null && contenido == null)
            {// Se vuelve a ejecutar el recorrido elegido hasta que no haya
                // más vértices sin marcar o se encuentre el contenido buscado.

                // Se recorre el grafo por anchura en búsqueda del elemento.
                contenido = buscar(nombreBuscado, (Vertice)vertices.getElemento(posVerticeSinMarcar),
                colaVertices, verticesMarcados);

                // Se vuelve a verificar si aún hay vértices sin marcar o visitar.
                posVerticeSinMarcar = (Integer) verticesMarcados.buscar(0);
            }
            return contenido;
        }
        else
        {// El grafo aún no contiene ningún vértice.
            return null;
        }
    }

    /**
     * Busca un elemento en el grafo.
     * @param nombreBuscado Elemento a ser buscado.
     * @param nombreInicio Vértice donde inicia la búsqueda.
     * @return Regresa el elemento encontrado, o null en caso
     * de que no exista en el grafo.
     */
    public Object buscar(Object nombreBuscado, Object nombreInicio)
    {
        if(!vertices.vacia())
        {// El grafo sí contiene vértices.
            // Arreglo paralelo al de los vértices del grafo
            // con la posiciones de los nodos ya visitados.
            Arreglo verticesMarcados = new Arreglo(vertices.getLongitud());
            // Se asigna a todas las posiciones del arreglo 0; esto
            // quiere decir que ninguno ha sido visitado hasta el momento.
            verticesMarcados.rellenar(0, vertices.getLongitud());
            // Vértices próximos a ser visitados.
            ICola colaVertices = new Cola(vertices.getLongitud());
            // Se realiza una primera búsqueda partiendo del vértice dado como parámetro.
            Integer posInicio = (Integer) vertices.buscar(nombreInicio);

            if(posInicio != null)
            {// El vértice de inicio sí existe en el grafo.
                Object contenido = buscar(nombreBuscado, (Vertice) vertices.getElemento(posInicio),
                colaVertices, verticesMarcados);
                // Verificamos si aún hay vértices sin marcar o visitar.
                Integer posVerticeSinMarcar = (Integer) verticesMarcados.buscar(0);

                while (posVerticeSinMarcar != null && contenido == null)
                {// Se vuelve a ejecutar el recorrido elegido hasta que no haya
                    // más vértices sin marcar o se encuentre el contenido buscado.

                    // Se recorre el grafo por anchura en búsqueda del elemento.
                    contenido = buscar(nombreBuscado, (Vertice)vertices.getElemento(posVerticeSinMarcar),
                    colaVertices, verticesMarcados);

                    // Se vuelve a verificar si aún hay vértices sin marcar o visitar.
                    posVerticeSinMarcar = (Integer) verticesMarcados.buscar(0);
                }
                return contenido;
            }
            else
            {// El vértice de inicio no existe en el grafo.
                return null;
            }
        }
        else
        {// El grafo aún no contiene ningún vértice.
            return null;
        }
    }

    /**
     * Busca un elemento en el grafo utilizando el algoritmo
     * BFS.
     * @param nombre Elemento a ser buscado.
     * @param inicio Vértice dónde inicia la búsqueda.
     * @param colaVertices Vértices próximos a visitar.
     * @param verticesMarcados Vértices ya visitados.
     * @return Regresa el elemento encontrado, o null en caso
     * de que no exista en el grafo.
     */
    private Object buscar(Object nombreBuscado, Vertice verticeInicio,
                          ICola colaVertices, Arreglo verticesMarcados)
    {
        // 1.- Se marca el vértice de partida como visitado.
        verticesMarcados.setElemento(verticeInicio.getNumVertice(), 1);
        // 2.- Se mete en la cola el vértice de partida.
        colaVertices.agregar(verticeInicio);

        // 3.- Repetir hasta que la cola esté vacía.
        while (!colaVertices.vacia())
        {
            // 4.- Quitar el primer nodo de la cola.
            Vertice verticeActual = (Vertice) colaVertices.eliminar();
            // 4.1.- Visitar el vértice y verificar si su contenido es lo que
            // se está buscando.
            if(verticeActual.toString().equalsIgnoreCase(nombreBuscado.toString()))
            {// Se encontró el elemento.
                return verticeActual.getNombre();
            }
            // 5.- Meter en la cola todos los vértices adyacentes a él que no
            // estén marcados y, a continuación marcarlos.
            agregarYMarcarAdyacencias(verticeActual, colaVertices, verticesMarcados);
        }
        // No fue encontrado el elemento.
        return null;
    }

    ////////////////////////////////// Métodos de Dijkstra ///////////////////////////////////////////////////

    /**
     * Genera las métricas de cada nodo hasta llegar
     * al destino.
     * @param nodoOrigen Vértice de partida.
     * @param nodoDestino Vértice de destino.
     * @return Regresa la métrica que resultó ser
     * la más óptima para llegar del origen al destino.
     */
    public Double obtenerMetricaOptima(Object nodoOrigen, Object nodoDestino)
    {
        Arreglo etiquetasOptimas = calcularEtiquetasOptimas(nodoOrigen);
        Integer indiceDestino = (Integer) vertices.buscar(nodoDestino);

        etiquetasOptimas.imprimir();
        SalidaEstandar.consola("\n");
        // Por ejemplo obtener la métrica de A -> G:
        if(indiceDestino != null)
        {
            EtiquetaGrafo etiquetaDestino = (EtiquetaGrafo) etiquetasOptimas.
            getElemento(indiceDestino);
            double metricaAalD = etiquetaDestino.getMetricaA();
            return metricaAalD;
        }
        return null;
     }

    /**
     * Genera la ruta más rápida u óptima para ir de un vértice
     * a otro en el grafo.
     * @param nodoOrigen Vértice de inicio de la ruta.
     * @param nodoDestino Vértice destino de la ruta.
     * @return Regresa una lista ligada con los contenidos de
     * los nodos o vértices que tuvieron la ruta más óptima.
     */
     public ListaLigada obtenerRutaOptima(Object nodoOrigen, Object nodoDestino)
     {
         ListaLigada ruta = new ListaLigada();

         // Por ejemplo obtener la ruta de A -> G:
         // obtener indices de vertices de origen y destino.
         Integer indiceOrigen = (Integer) vertices.buscar(nodoOrigen);
         Integer indiceDestino = (Integer) vertices.buscar(nodoDestino);

         if(indiceOrigen != null && indiceDestino != null)
         {// No están si me da null.
             Arreglo etiquetasOptimas = calcularEtiquetasOptimas(nodoOrigen); //Dijkstra.

             // Primero irnos a la etiqueta destino.
             EtiquetaGrafo etiquetaActual = (EtiquetaGrafo) etiquetasOptimas.
             getElemento(indiceDestino);
             // Agregar primero el destino
             ruta.insertarInicio(((Vertice) vertices.getElemento(indiceDestino)).getNombre());
             int nodoActual = indiceDestino;
             while(nodoActual != indiceOrigen)
             {
                 nodoActual = etiquetaActual.getNodoAnterior();
                 etiquetaActual = (EtiquetaGrafo) etiquetasOptimas.getElemento(nodoActual);
                 ruta.insertarInicio(((Vertice) vertices.getElemento(nodoActual)).getNombre());
             }
             return ruta;
         }
         return null;
     }

    /**
     * Genera las etiquetas del recorrido para cada nodo del
     * grafo.
     * @param nodoOrigen Vértice del que parte el camino.
     * @return Regresa un arreglo con las etiquetas de cada
     * nodo generadas.
     */
     private Arreglo calcularEtiquetasOptimas(Object nodoOrigen)
     {
         Arreglo etiquetas = new Arreglo(vertices.getLongitud());
         Arreglo vMarcados = new Arreglo(vertices.getLongitud());
         double infinito = 0.0;

         // 1.- Inicializar las etiquetas partiendo del nodo origen, y marcarlo.
         if(criterio == CriterioOrden.DESCENDENTE)
         {
             infinito = Double.MAX_VALUE;
         }
         else
         {// El criterio: mayor es mejor.
             infinito = Double.MIN_VALUE;
         }
         Integer numVerticeOrigen = (Integer) vertices.buscar(nodoOrigen);
         if(numVerticeOrigen == null)
         {// No existe, salirse del método.
             return etiquetas;
         }
         inicializaEtiquetas(etiquetas, numVerticeOrigen, infinito, 0.0);

         // Rellenar con ceros el tamaño.
         vMarcados.rellenar(0, vMarcados.capacidad());
         // Marcar el origen.
         vMarcados.setElemento(numVerticeOrigen, 1);

         // Desde la iteración 1 hasta la iteración n - 1, es decir,
         // procesará todos los vértices partiendo del nodo origen pero
         // el último ya no se procesa, porque queda solo.
         int nodoActual = numVerticeOrigen; // El primer vértice actual es el origen.
         for(int iter = 1; iter < vertices.getLongitud(); iter++)
         {
             // 2.- Calcular las nuevas métricas acumuladas hacia los vecinos
             // no marcados partiendo del nodo actual, si se mejora, actualiza.
             actualizarMetricasAcumuladas(etiquetas, vMarcados, nodoActual, infinito, iter);

             // 3.- Seleccionar el nodo no marcado con la métrica acumulada más óptima
             // y marcarlo, a la vez se vuelve el nodo actual.
             nodoActual = obtenerVerticeOptimo(vMarcados, etiquetas, infinito);
             vMarcados.setElemento(nodoActual, 1); // Marcar el vértice nuevo actual.
         }
         return etiquetas;
     }

    /**
     * Asigna los valores iniciales para las etiquetas
     * de los nodos del grafo en el algoritmo de Dijkstra.
     * @param etiquetas Arreglo con la capacidad para almacenar
     *                  una etiqueta por nodo.
     * @param nodoOrigen Vértice o nodo del que parte el camino
     *                    a buscar.
     * @param valorMetricaNodos Valor o ponderación mínima y más
     *                          eficiente para generar el camino
     *                          entre los vértices del grafo.
     * @param valorMetricaOrigen Valor o ponderación que posee el
     *                           nodo o vértice de partida.
     */
     private void inicializaEtiquetas(Arreglo etiquetas, int nodoOrigen,
                                double valorMetricaNodos ,double valorMetricaOrigen)
     {
         for(int posE = 0; posE < vertices.getLongitud(); posE++)
         {
             EtiquetaGrafo nuevaEtiqueta = new EtiquetaGrafo();
             nuevaEtiqueta.setIteracion(0);
             nuevaEtiqueta.setMetricaA(valorMetricaNodos);
             nuevaEtiqueta.setNodoAnterior(-1);
             etiquetas.agregar(nuevaEtiqueta);
         }
         EtiquetaGrafo etiquetaOrigen = (EtiquetaGrafo) etiquetas.getElemento(nodoOrigen);
         etiquetaOrigen.setMetricaA(valorMetricaOrigen); // En nuestro caso suele ser 0.
     }

    /**
     * Cambia en caso de ser mejor las metricas de las etiquetas de
     * los nodos vecinos de un nodo de partida en cuestión.
     * @param etiquetas Arreglo de etiquetas de cada vértice o nodo.
     * @param vMarcados Arreglo de vértices que ya han sido procesados.
     * @param nodoActual Nodo del se busca actualizar las métricas de
     *                   sus vértices vecinos.
     * @param infinito Ponderación de máximo valor del grafo en cuestión.
     * @param iter Número de iteración que lleva el proceso.
     */
    public void actualizarMetricasAcumuladas(Arreglo etiquetas, Arreglo vMarcados,
                                        int nodoActual, double infinito, int iter)
    {
       // Hacer ciclo del nodo actual hacia todos los nodos
       // y detectar quien es vecino.
       for(int nodoDestino = 0; nodoDestino < vertices.getLongitud(); nodoDestino++)
       {
           // Los vecinos no marcados.
           double metricaOrigenDestino = (double) aristas.getElemento(nodoActual, nodoDestino);
           double metricaOrigen = ((EtiquetaGrafo) etiquetas.getElemento(nodoActual)).getMetricaA();
           double metricaAcumuladaOrDe = metricaOrigen + metricaOrigenDestino;

           if(metricaOrigenDestino != 0 && metricaOrigenDestino != infinito
           && ((int) vMarcados.getElemento(nodoDestino) == 0))
           {// Nodos adyacentes y no marcados.
               EtiquetaGrafo etiquetaDestino = ((EtiquetaGrafo) etiquetas.getElemento(nodoDestino));
               double metricaDestino = etiquetaDestino.getMetricaA();
               boolean banderaActualizar = false;

               if(criterio == CriterioOrden.DESCENDENTE)
               {// Menor es mejor.
                   if(metricaAcumuladaOrDe < metricaDestino)
                   {// Si es mejor se modifica.
                       banderaActualizar = true;
                   }
               }
               else
               {// Mayor es mejor, es decir ASC.
                   if(metricaAcumuladaOrDe > metricaDestino)
                   {// Si es mejor se modifica.
                       banderaActualizar = true;
                   }
               }
               if(banderaActualizar == true)
               {
                   // Cambiar métrica.
                   etiquetaDestino.setMetricaA(metricaAcumuladaOrDe);
                   // Cambiar nodoAnterior.
                   etiquetaDestino.setNodoAnterior(nodoActual);
                   // Cambiar iteración.
                   etiquetaDestino.setIteracion(iter);
               }
           }
       }
    }

    /**
     * De los vértices que no han sido marcados aún
     * obtiene la posición respecto al arreglo de
     * vértices del que tiene la métrica más pequeña
     * o grande (según se haya establecido el criterio)
     * que tenga el grafo.
     * @param vMarcados Arreglo de vértices marcados y
     *                  no marcados.
     * @param etiquetas Arreglo con las etiquetas que
     *                  tiene cada vértice.
     * @param infinito Infinito (métrica más grande o pequeña)
     *                 definido al criterio ASC o DESC del grafo.
     * @return Regresa el índice del nodo que contiene la métrica
     * de mayor o menor valor.
     */
    public int obtenerVerticeOptimo(Arreglo vMarcados, Arreglo etiquetas,
                                    double infinito)
    {
        // Sacar el más óptimo (ASC, DESC) de un arreglo de etiquetas.
        double optimo = infinito;
        // Si el criterio es ASC, el infinito es le más pequeño.
        int indiceNodoOptimo = -1;
        for(int verticesP = 0; verticesP < etiquetas.getLongitud(); verticesP++)
        {
            if((int) vMarcados.getElemento(verticesP) == 0)
            {// Solo entra si no está marcado.
                EtiquetaGrafo etiquetaProcesada = (EtiquetaGrafo) etiquetas.getElemento(verticesP);
                double metricaAP = etiquetaProcesada.getMetricaA();
                if(criterio == CriterioOrden.DESCENDENTE && metricaAP < optimo)
                {
                    optimo = metricaAP;
                    indiceNodoOptimo = verticesP;
                }
                else if(criterio == CriterioOrden.ASCENDENTE && metricaAP > optimo)
                {
                    optimo = metricaAP;
                    indiceNodoOptimo = verticesP;
                }
            }
        }
        return indiceNodoOptimo;
    }


    /**
     * Indica si el peso o ponderación
     * de la arista es diferente de 0 y
     * del valor máximo de Double (en caso
     * de ser el criterio descendente), o
     * el valor mínimo de Double (en caso
     * de ser el criterio ascendente).
     * @param peso Peso a ser verificado.
     * @return Regresa <b>true</b> si el
     * peso es válido, o <b>false</b> en
     * caso contrario.
     */
    private boolean esPesoValido(double peso)
    {
        if(criterio == null)
        {// No se definio un criterio.
            if(peso != 0.0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(criterio == CriterioOrden.ASCENDENTE)
        {// El criterio es ascendente (entre más grande mejor).
            if(peso != 0.0 && peso != Double.MIN_VALUE)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {// El criterio es descendente (ente más pequeño mejor).
            if(peso != 0.0 && peso != Double.MAX_VALUE)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * Verifica si el grafo es no dirigido.
     * @return Regresa <b>true</b> en caso
     * de serlo, o <b>false</b> en caso contrario.
     */
    public boolean esNoDirigido()
    {
        // Se recorre la matriz de adyacencia del grafo.
        for(int numRenglon = 0; numRenglon < vertices.getLongitud(); numRenglon++)
        {
            for(int numColumna = 0; numColumna < vertices.getLongitud(); numColumna++)
            {
                // 1.- Se omiten los lazos.
                // 2.- Se comprueba la existencia de una arista.
                if(numRenglon != numColumna &&
                esPesoValido((double)aristas.getElemento(numRenglon, numColumna)))
                {
                    // 2.1.- En caso de existir una arista, se comprueba
                    // que también haya una con las posiciones invertidas en la
                    // matriz, esto indicaría que la arista podría ser paralela o
                    // no dirigida.
                    if(esPesoValido((double)aristas.getElemento(numColumna, numRenglon)))
                    {
                        // 3.- Se comprueba que exita el mismo peso entre las dos aristas;
                        // esto indicaria que es no dirigida.
                        if((double)aristas.getElemento(numRenglon, numColumna) !=
                        (double)aristas.getElemento(numColumna, numRenglon))
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
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
            if(!vertices.vacia())
            {// El grafo sí contiene vértices.
                // Lista que guarda los vértices para los cuales
                // hay entre ellos.
                ListaLigada listaVertices = new ListaLigada();
                // Arreglo paralelo al de los vértices del grafo
                // con la posiciones de los nodos ya visitados.
                Arreglo verticesMarcados = new Arreglo(vertices.getLongitud());
                // Se asigna a todas las posiciones del arreglo 0; esto
                // quiere decir que ninguno ha sido visitado hasta el momento.
                verticesMarcados.rellenar(0, vertices.getLongitud());
                // Cola para realizar el recorrido en anchura.
                ICola colaVertices = new Cola(vertices.getLongitud());
                // El vértice de partida es el primero insertado en el grafo.
                Vertice verticeInicio = (Vertice) vertices.getElemento(0);
                // Se realiza un sólo recorrido para comprobar si es conexo.
                listaVertices = recorrerBFS(
                verticeInicio, colaVertices, verticesMarcados);

                if(listaVertices.getSize() == vertices.getLongitud())
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
     * Indica si el grafo es fuertemente conexo, es decir,
     * que exista un camino para cualquier par de vértices.
     * El grafo debe ser <b>dirigido</b>.
     * @return Regresa <b>true</b> en caso de serlo,
     * o <b>false</b> en caso contrario.
     */
    public boolean esFuertementeConexo()
    {
        // Lista que guardará los componentes conexos del grafo.
        ListaLigada listaComponentes = new ListaLigada();
        // Arreglo paralelo al de los vértices del grafo
        // con la posiciones de los nodos que ya están en un componente conexo.
        Arreglo verticesEnComponente = new Arreglo(vertices.getLongitud());
        // Se asigna a todas las posiciones del arreglo 0; esto
        // quiere decir que ninguno ha sido visitado hasta el momento.
        verticesEnComponente.rellenar(0, vertices.getLongitud());
        // Arreglo para poder realizar los recorridos solamente.
        Arreglo vMarcadosTemporal = new Arreglo(vertices.getLongitud());
        vMarcadosTemporal.rellenar(0, vertices.getLongitud());
        // Pila para realizar los recorridos de profundidad
        IPila pilaVertices = new Pila(vertices.getLongitud());
        // Lista que tendrá el conjunto de vértices ascendientes.
        ListaLigada listaAscendientes = new ListaLigada();
        // Lista que tendrá el conjunto de vértices descendientes.
        ListaLigada listaDescendientes = new ListaLigada();
        // Se crea una copia del grafo con las aristas invertidas.
        GrafoMatrizA grafoInvertido = getGrafoInvertido();
        // Vértice del que inician los recorridos.
        Vertice verticeInicio = (Vertice) vertices.getElemento(0);
        // Se realizan los recorridos ascendiente y descendiente.
        listaDescendientes = recorrerDFS(verticeInicio, pilaVertices, vMarcadosTemporal);
        // Se reinicia el arreglo para abarcar todos los vértices que se puedan en el grafo invertido.
        vMarcadosTemporal.setElemento(1,0, vertices.getLongitud());
        listaAscendientes = grafoInvertido.recorrerDFS(verticeInicio, pilaVertices, vMarcadosTemporal);
        //Vertices comunes que son obtenidos de los reocrridos.
        ListaLigada listaVerticesComunes = getVerticesComunes(listaDescendientes, listaAscendientes);
        // Se agrega el primer componente conexo en base a los recorridos.
        agregarYMarcarComponenteConexo(
                listaComponentes,listaVerticesComunes, verticesEnComponente);

        // Se comprueba si aún quedan nodos sin estar en un componente conexo.
        Integer posVerticeNoMarcado = (Integer) verticesEnComponente.buscar(0);

        if(posVerticeNoMarcado == null)
        {// Todos los vértices fueron alcanzados en los dos recorridos;
         // el grafo es fuertemente conexo.
            return true;
        }
        else
        {// No se alcanzaron todos los vértices en los dos recorridos;
         // el grafo no es fuertemente conexo.
            return false;
        }
    }

    /**
     * Obtiene el o los conjuntos de vértices de los
     * cuales existe un camino entre un cada par de nodos.
     * @return Regresa una lista ligada de listas
     * ligadas con el o los conjuntos de vértices
     * obtenidos.
     */
    public ListaLigada componentesConexos()
    {
        if(!vertices.vacia())
        {
            if(esNoDirigido())
            {// El grafo es no dirigido, se realiza
             // su respectivo proceso de obtención de
             // componentes conexos.
                return componentesConexosNoDirigido();
            }
            else
            {// El grafo es dirigido, se realiza
             // su respectivo proceso de obtención de
             // componentes conexos.
                return componentesConexosDirigido();
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Genera los componentes conexos del grafo
     * cuando es dirigido.
     * @return Regresa una lista ligada de listas
     * ligadas con el o los conjuntos de vértices
     * obtenidos.
     */
    private ListaLigada componentesConexosDirigido()
    {
        // Lista que guardará los componentes conexos del grafo.
        ListaLigada listaComponentes = new ListaLigada();
        // Arreglo paralelo al de los vértices del grafo
        // con la posiciones de los nodos que ya están en un componente conexo.
        Arreglo verticesEnComponente = new Arreglo(vertices.getLongitud());
        // Se asigna a todas las posiciones del arreglo 0; esto
        // quiere decir que ninguno ha sido visitado hasta el momento.
        verticesEnComponente.rellenar(0, vertices.getLongitud());
        // Arreglo para poder realizar los recorridos solamente.
        Arreglo vMarcadosTemporal = new Arreglo(vertices.getLongitud());
        vMarcadosTemporal.rellenar(0, vertices.getLongitud());
        // Pila para realizar los recorridos de profundidad
        IPila pilaVertices = new Pila(vertices.getLongitud());
        // Lista que tendrá el conjunto de vértices ascendientes.
        ListaLigada listaAscendientes = new ListaLigada();
        // Lista que tendrá el conjunto de vértices descendientes.
        ListaLigada listaDescendientes = new ListaLigada();
        // Se crea una copia del grafo con las aristas invertidas.
        GrafoMatrizA grafoInvertido = getGrafoInvertido();
        // Vértice del que inician los recorridos.
        Vertice verticeInicio = (Vertice) vertices.getElemento(0);
        // Se realizan los recorridos ascendiente y descendiente.
        listaDescendientes = recorrerDFS(verticeInicio, pilaVertices, vMarcadosTemporal);
        // Se reinicia el arreglo para abarcar todos los vértices que se puedan en el grafo invertido.
        vMarcadosTemporal.setElemento(1,0, vertices.getLongitud());
        listaAscendientes = grafoInvertido.recorrerDFS(verticeInicio, pilaVertices, vMarcadosTemporal);
        //Vertices comunes que son obtenidos de los reocrridos.
        ListaLigada listaVerticesComunes = getVerticesComunes(listaDescendientes, listaAscendientes);
        // Se agrega el primer componente conexo en base a los recorridos.
        agregarYMarcarComponenteConexo(
        listaComponentes,listaVerticesComunes, verticesEnComponente);

        // Se comprueba si aún quedan nodos sin estar en un componente conexo.
        Integer posVerticeNoMarcado = (Integer) verticesEnComponente.buscar(0);

        while (posVerticeNoMarcado != null)
        {// Se repite hasta que todos los vértices estén en un componente.
            verticeInicio = (Vertice) vertices.getElemento(posVerticeNoMarcado);
            // Se realizan los recorridos ascendiente y descendiente.
            vMarcadosTemporal.setElemento(1,0, vertices.getLongitud());
            listaDescendientes = recorrerDFS(verticeInicio, pilaVertices, vMarcadosTemporal);
            vMarcadosTemporal.setElemento(1,0, vertices.getLongitud());
            listaAscendientes = grafoInvertido.recorrerDFS(verticeInicio, pilaVertices, vMarcadosTemporal);
            // Se extraen los vértices comunes.
            listaVerticesComunes = getVerticesComunes(listaDescendientes, listaAscendientes);
            // Se agrega el componente a la lista y se marcan los vértices.
            agregarYMarcarComponenteConexo(
            listaComponentes, listaVerticesComunes, verticesEnComponente);
            // Se comprueba si aún quedan nodos sin estar en un componente conexo.
            posVerticeNoMarcado = (Integer) verticesEnComponente.buscar(0);
        }
        return listaComponentes;
    }

    /**
     * Crea una copia del grafo con las aristas
     * invertidas en los sentidos.
     * @return Regresa el grafo invertido.
     */
    private GrafoMatrizA getGrafoInvertido()
    {
        // Se crea un grafo con el mismo tamaño.
        GrafoMatrizA grafoInvertido = new GrafoMatrizA(vertices.getLongitud());
        // Se copia el criterio al nuevo grafo.
        grafoInvertido.criterio = criterio;
        // Se copian los vértices al nuevo grafo.
        grafoInvertido.vertices.setArreglo(vertices.leerArreglo());
        // Se copian las aristas para el nuevo grafo.
        Matriz copiaAristas = aristas.copiar();
        // Se invierten las aristas trasponiendo la matriz.
        copiaAristas.aplicarTraspuesta();
        // Se asignan las aristas invertidas al nuevo grafo.
        grafoInvertido.aristas = copiaAristas;
        return grafoInvertido;
    }

    /**
     * Genera los componentes conexos del grafo
     * cuando es NO dirigido.
     * @return Regresa una lista ligada de listas
     * ligadas con el o los conjuntos de vértices
     * obtenidos.
     */
    private ListaLigada componentesConexosNoDirigido()
    {
        return recorrer(RecorridoGrafo.BFS);
    }

    /**
     * Agrupa los vértices que se repiten en ambas listas.
     * @param listaUno Primera lista.
     * @param listaDos Segunda lista.
     * @return Regresa una lista ligada con las coincidencias
     * obtenidas.
     */
    private ListaLigada getVerticesComunes(ListaLigada listaUno, ListaLigada listaDos)
    {
        ListaLigada listaVComunes = new ListaLigada();
        int tamListaUno = listaUno.getSize();
        int tamListaDos = listaDos.getSize();

        if(tamListaUno < tamListaDos)
        {// La lista uno tiene menos elementos que la dos.
            listaUno.inicializaIterador();

            while (listaUno.hayNodoSiguiente())
            {// Se busca en base a la lista más pequeña.
                Vertice verticeActual = (Vertice) listaDos.
                buscar(listaUno.obtenerSiguiente());

                if(verticeActual != null)
                {// Se encontró una coincidencia de vértices y no se
                // encuentra en otro componente conexo.
                    listaVComunes.agregar(verticeActual);
                }
            }
        }
        else
        {// La lista uno tiene los mismos o más elementos que la dos.
            listaDos.inicializaIterador();

            while (listaDos.hayNodoSiguiente())
            {// Se busca en base a la lista más pequeña.
                Vertice verticeActual = (Vertice) listaUno.
                buscar(listaDos.obtenerSiguiente());

                if(verticeActual != null)
                {// Se encontró una coincidencia de vértices y no se
                    // encuentra en otro componente conexo.
                    listaVComunes.agregar(verticeActual);
                }
            }
        }
        return listaVComunes;
    }


    /**
     * De una lista de vértices comunes agrega y marca los
     * vértices que aún no forman parte de los componentes conexos
     * del grafo.
     * @param listaComponentes Lista ligada de listas ligadas
     *                         que contiene los componentes
     *                         conexos obtenidos del grafo hasta el
     *                         momento.
     * @param listaComunes Lista de vértices que resultaron comunes en
     *                     los recorridos ascendente y descendiente.
     * @param verticesEnComponente Vertices que ya se encuentran en
     *                             alguno de los componentes conexos
     *                             del grafo.
    */
    private void agregarYMarcarComponenteConexo(ListaLigada listaComponentes,
                                                ListaLigada listaComunes,
                                                Arreglo verticesEnComponente)
    {
        if(!listaComponentes.vacia())
        {// La ya componentes de vértices para verificar que no se repitan.
            // Lista que conformará a los nuevos vértices conexos.
            ListaLigada listaNuevoComponente = new ListaLigada();
            listaComunes.inicializaIterador();
            // Se iteran todos los vértices comunes.
            while(listaComunes.hayNodoSiguiente())
            {
                Vertice verticeActual = (Vertice) listaComunes.obtenerSiguiente();
                // Se verifica que el vértice común no se encuentre
                // ya en un componente conexo.
                if((int)verticesEnComponente.
                getElemento(verticeActual.getNumVertice()) == 0)
                {
                    // Se marca el vértice.
                    verticesEnComponente.setElemento(verticeActual.getNumVertice(), 1);
                    listaNuevoComponente.agregar(verticeActual);
                }
            }
            if(!listaNuevoComponente.vacia())
            {// La lista tiene por lo menos un nodo.
                listaComponentes.agregar(listaNuevoComponente);
            }
        }
        else
        {// No hay aún componentes agregados.
            listaComunes.inicializaIterador();
            // Se iteran todos los vértices comunes.
            while(listaComunes.hayNodoSiguiente())
            {
                Vertice verticeActual = (Vertice) listaComunes.obtenerSiguiente();
                // Se marca el vértice común.
                verticesEnComponente.setElemento(verticeActual.getNumVertice(), 1);
            }
            listaComponentes.agregar(listaComunes);
        }
    }

    /**
     * Calcula la distancia mínima que hay entre
     * todos los vértices del grafo.
     * @return Devuelve una matriz con las distancias obtenidas.
     */
    public Matriz distanciaMinimaVertices()
    {
        if(criterio == CriterioOrden.DESCENDENTE)
        {// Debido a que se busca la distancia y recorrido mínimos
         // el grafo debe tener criterio descendente.

            // Se inicia la matriz con los costos del grafo.
            Matriz distancias = aristas.copiar();
            // Cantidad de vertices.
            int numVertices = vertices.getLongitud();

            for(int posK = 0; posK < numVertices; posK++)
            {
                for(int posI = 0; posI < numVertices; posI++)
                {
                    for(int posJ = 0; posJ < numVertices; posJ++)
                    {
                        // Se define el infinito según el criterio
                        // descendente.
                        double infinito = Double.MAX_VALUE;
                        double distanciaUno = (double)distancias.getElemento(posI, posK);
                        double distanciaDos = (double)distancias.getElemento(posK, posJ);

                        if(distanciaUno != infinito && distanciaDos != infinito)
                        {// Las distancias son válidas.

                            double sumaDistancias = distanciaUno + distanciaDos;

                            if(sumaDistancias < (double)distancias.getElemento(posI, posJ))
                            {// Se encontró un camino más corto.
                                distancias.setElemento(posI, posJ, sumaDistancias);
                            }

                        }
                    }
                }
            }
            return distancias;
        }
        else
        {// El grafo no tiene criterio descendente.
            return null;
        }
    }

    /**
     * Crea una matriz del tamaño de la matriz
     * de adyacencia del grafo con los origenes idénticos
     * a sus destinos.
     * @return Devuelve una matriz con los
     * recorridos inicializados.
     */
    private Matriz inicializarMatrizRecorridos()
    {
        // Matriz paralela a la de las aristas.
        Matriz matrizRecorridos = new Matriz(
        vertices.getLongitud(), vertices.getLongitud());

        // Se asignan los valores iniciales.
        for(int columna = 0; columna < vertices.getLongitud(); columna++)
        {
            // Se copia el vértice que será asignado en todas las
            // filas de la columna.
            Vertice verticeActual = (Vertice)vertices.getElemento(columna);
            for(int fila = 0; fila < vertices.getLongitud(); fila++)
            {
                if(fila != columna)
                {// No es elemento en la diagonal principal.
                    matrizRecorridos.setElemento(fila, columna, verticeActual);
                }
            }
        }
        return matrizRecorridos;
    }

    /**
     * Calcula la distancia mínima que hay entre
     * todos los vértices del grafo y el recorrido que hay
     * para llegar a cada uno.
     * @return Devuelve un arreglo con dos matrices:
     * Una matriz contiene los valores de las distancias
     * mínimas.
     * La otra matriz contiene los recorridos para llegar
     * a cada vértice según estas distancias.
     */
    public Arreglo distanciaMinimaVerticesYRecorridos()
    {
        if(criterio == CriterioOrden.DESCENDENTE)
        {// Debido a que se busca la distancia y recorrido mínimos
         // el grafo debe tener criterio descendente.

            // Matrices que serán devueltas.
            Arreglo matrices = new Arreglo(2);
            // Se inicializa la matriz con los costos del grafo.
            Matriz distancias = aristas.copiar();
            // El origen desde un vértice hacia él mismo es cero.
            //distancias.rellenarDiagonal(0.0);
            // Se inicia otra matriz para almacenar los recorridos.
            Matriz recorridos = inicializarMatrizRecorridos();
            //recorridos.inicializar(null);
            // Cantidad de vertices
            int numVertices = vertices.getLongitud();

            for(int posK = 0; posK < numVertices; posK++)
            {
                for(int posI = 0; posI < numVertices; posI++)
                {
                    for(int posJ = 0; posJ < numVertices; posJ++)
                    {
                        // Se define el infinito según el criterio
                        // descendente.
                        double infinito = Double.MAX_VALUE;
                        double distanciaUno = (double)distancias.getElemento(posI, posK);
                        double distanciaDos = (double)distancias.getElemento(posK, posJ);

                        if(distanciaUno != infinito && distanciaDos != infinito)
                        {// Las distancias son válidas.

                            double sumaDistancias = distanciaUno + distanciaDos;

                            if(sumaDistancias < (double)distancias.getElemento(posI, posJ))
                            {// Se encontró un camino más corto.
                                distancias.setElemento(posI, posJ, sumaDistancias);
                                recorridos.setElemento(posI, posJ,(Vertice)vertices.getElemento(posK));
                            }

                        }
                    }
                }
            }
            matrices.agregar(distancias);
            matrices.agregar(recorridos);
            return matrices;
        }
        else
        {// El grafo no tiene criterio descendente.
            return null;
        }
    }

    /**
     * Guarda los nodos que apuntan hacia el
     * vértice.
     * @param nombre Vértice a obtener sus adyacencias.
     * @return Regresa la lista de nodos que son
     * adyacentes al dado como parámetro.
     */
    public ListaLigada obtenerAdyacencias(Object nombre)
    {
        ListaLigada listaAdyacencias = new ListaLigada();

        // Se recorren todos los vértices.
        for(int index = 0; index < vertices.getLongitud(); index++)
        {
            Vertice verticeOrigen = (Vertice) vertices.getElemento(index);
            Object nombreOrigen = verticeOrigen.getNombre();

            if(esAdyacente(verticeOrigen.getNombre(), nombre))
            {// El vertice actual de la iteración apunta hacia el
             // que se están buscando sus adyacencias.
                listaAdyacencias.agregar(nombreOrigen);
            }
        }
        return listaAdyacencias;
    }
}