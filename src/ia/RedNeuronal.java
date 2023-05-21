package ia;

import edlineal.Arreglo;
import edlineal.MatrizNum;
import edlineal.VectorNum;
import utilidades.TipoFuncionActivacion;

/**
 * Esta clase implementa el TDA RedNeuronal.
 * Define los comportamientos y atributos que
 * un tipo específico de red neuronal debe
 * implementar y tener.
 * Comprende un conjunto de capas de neuronas
 * que pueden lograr predecir o clasificar
 * datos.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public abstract class RedNeuronal
{
    /** Guarda las capas que contiene la red. */
    protected Arreglo capas;
    /** Guarda el rango con el que aprende la red. */
    protected double coeficienteAprendizaje;
    /** Guarda las últimas entradas que recibió la red. */
    protected VectorNum entradas;
    /** Guarda la cantidad de datos que puede recibir la red como entrada. */
    protected final int ENTRADAS;
    /** Guarda la cantidad de datos que la red da como salida. */
    protected final int SALIDAS;

    /**
     * Define el constructor TDA RedNeuronal.
     * @param capacidad Cantidad de capas que contendrá la red.
     * @param numEntradas Cantidad de neuronas que habrá en la capa
     *                    de entrada.
     * @param numSalidas Cantidad de neuronas que habrá en la capa
     *                   de salida.
     * @param coeficienteA Grado de aprendizaje de la red en cada
     *                     iteración.
     */
    public RedNeuronal(int capacidad, int numEntradas, int numSalidas,
                       double coeficienteA)
    {
        ENTRADAS = numEntradas;
        SALIDAS = numSalidas;
        // El tamaño del arreglo está definido por la capacidad dada.
        capas = new Arreglo(capacidad);
        this.coeficienteAprendizaje = coeficienteA;
    }

    /**
     * Inicializa los pesos y bias de las neuronas de cada
     * capa de la red.
     * @param numCapa Capa a inicializar sus pesos y bias.
     */
    protected void asignarPesosYBias(int numCapa)
    {
        CapaRedNeuronal capaActual = obtenerCapa(numCapa);

        if(numCapa == 0)
        {// Asignación de la primera capa oculta.
            capaActual.inicializarPesos(ENTRADAS);
            capaActual.generarBias();
        }
        else
        {// Asignación de una de las capas ocultas o la de salida.
            CapaRedNeuronal capaAnterior = obtenerCapa(numCapa - 1);
            capaActual.inicializarPesos(capaAnterior.getLongitud());
            capaActual.generarBias();
        }
    }

    /**
     * Agrega una capa oculta de neuronas a la
     * red.
     * @param numNeuronas Cantidad de neuronas
     *                    que tendrá la capa.
     * @param funcion Función a asignar a las neuronas
     *                de la capa.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar la capa, o <b>false</b> en caso contrario.
     */
    public boolean agregarCapa(int numNeuronas, TipoFuncionActivacion funcion)
    {
        if(!capas.llena())
        {// Aún hay espacio.
            // Se obtiene la posición a asignar a la capa.
            int numCapa = capas.getLongitud();
            CapaRedNeuronal nuevaCapa = new CapaRedNeuronal(numCapa,
            numNeuronas, funcion);
            if(capas.agregar(nuevaCapa))
            {// La capa pudo ser asignada.
                asignarPesosYBias(numCapa);
                return true;
            }
            else
            {// Ocurrió un error al asignar la capa
                return false;
            }
        }
        else
        {// Ya no hay espacio.
            return false;
        }
    }

    /**
     * Asigna la capa de salida de la red neuronal
     * según el tipo de red implementada.
     */
    public abstract void asignarCapaSalida();

    /**
     * Ejecuta la red neuronal para realizar un
     * cálculo.
     * @param entradas Valores de entrada para
     *                 la red.
     * @return Regresa el o los resultados obtenidos.
     */
    public VectorNum operar(VectorNum entradas)
    {
        if(capas.getElemento(capas.capacidad() - 1) == null)
        {// La capa de salida no ha sido asignada.
            asignarCapaSalida();
        }
        this.entradas = entradas;
        return forward(entradas);
    }

    /**
     * Genera un cálculo con la red neuronal
     * para mejorar sus resultados.
     * @param entradas Vector con los valores de entrada.
     * @param target Vector con los resultados esperados
     *               de la red.
     * @return Regresa un vector con el o los resultados
     * de la red.
    */
    public VectorNum entrenar(VectorNum entradas, VectorNum target)
    {
        if(capas.getElemento(capas.capacidad() - 1) == null)
        {// La capa de salida no ha sido asignada.
            asignarCapaSalida();
        }
        VectorNum salidas = new VectorNum(SALIDAS);
        this.entradas = entradas;
        salidas = forward(entradas, 0);
        calcularSensibilidades(entradas, salidas, target);
        backward(capas.getLongitud() - 1);
        return salidas;
    }

    /**
     * Realiza un cálculo con las neuronas de la red.
     * @param entradas Matriz con los valores de entrada
     *                 de la red.
     * @return Regresa el resultado del cálculo.
     */
    private VectorNum forward(VectorNum entradas)
    {
        return forward(entradas,0);
    }

    /**
     * Recorre recursivamente las capas de la red
     * realizando el cálculo de valores.
     * @param numCapa Número de la capa.
     * @param entradas Vector con los valores de
     *                 entrada de la capa.
     * @return Regresa el resultado que arroja
     * cada capa.
     */
    private VectorNum forward(VectorNum entradas ,int numCapa)
    {
        if(numCapa == capas.getLongitud())
        {// Caso base.
            return entradas;
        }
        else
        {// Caso recursivo.
            CapaRedNeuronal capaActual = obtenerCapa(numCapa);
            entradas = capaActual.operar(entradas);
            return forward(entradas, ++numCapa);
        }
    }

    /**
     * Calcula las sensibilidades de las
     * capas de la red.
     * @param entradas Datos ingresados en la capa de entrada.
     * @param salidas Resultados obtenidos de la red.
     * @param target Datos esperados de la red.
     */
    protected abstract void calcularSensibilidades(VectorNum entradas, VectorNum salidas,
                                                   VectorNum target);
    /**
     * Actualiza los pesos y bias de la red
     * para obtener mejores resultados en
     * operaciones posteriores.
     * @param numCapa Índice de la capa que
     *                se actualiza en el momento.
     */
    protected void backward(int numCapa)
    {
        if(numCapa >= 0)
        {// Caso recursivo

            if(numCapa == 0)
            {// Caso para la priemra capa oculta.
                CapaRedNeuronal capaActual = obtenerCapa(numCapa);
                capaActual.actualizarPesos(entradas, coeficienteAprendizaje);
                capaActual.actualizarBias(coeficienteAprendizaje);
                backward(--numCapa);
            }
            else
            {// Caso para las demás capas.
                CapaRedNeuronal capaActual = obtenerCapa(numCapa);
                CapaRedNeuronal capaAnterior = obtenerCapa(numCapa - 1);
                capaActual.actualizarPesos(capaAnterior.getUltimasSalidas(),
                coeficienteAprendizaje);
                capaActual.actualizarBias(coeficienteAprendizaje);
                backward(--numCapa);
            }
        }
        // Caso base escondido.
    }

    /**
     * Aplica la función de pérdida o de error.
     * @param resultados Datos de salida generados
     *                   por la red.
     * @param targets Datos esperados de la red.
     * @return Regresa el escalar del error obtenido.
     */
    protected abstract double calcularError(VectorNum resultados, VectorNum targets);

    /**
     * Recupera una de las capas de la red.
     * @param numCapa Número de la capa.
     * @return Regresa la capa encontrada, o null
     * en caso contrario.
     */
    protected CapaRedNeuronal obtenerCapa(int numCapa)
    {
        return (CapaRedNeuronal)capas.getElemento(numCapa);
    }
}
