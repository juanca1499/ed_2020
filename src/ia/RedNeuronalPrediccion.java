package ia;

import edlineal.MatrizNum;
import edlineal.VectorNum;
import matematicas.FuncionPerdida;
import utilidades.ConversionMatriz;
import utilidades.TipoFuncionActivacion;

/**
 * Esta clase implementa el TDA RedNeuronalPrediccion.
 * Esta especialización de una red neuronal permite
 * realizar predicciones a partir de un conjunto de
 * datos que recibe. Devuelve un sólo dato resultado
 * de la predicción.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0.
 */
public class RedNeuronalPrediccion extends RedNeuronal
{

    /**
     * Define el constructor TDA RedNeuronalPredicción.
     * @param capacidad Cantidad de capas que contendrá la red.
     * @param numEntradas Cantidad de neuronas que habrá en la capa
     *                    de entrada.
     * @param numSalidas Cantidad de neuronas que habrá en la capa
     *                   de salida.
     * @param coeficienteA Grado de aprendizaje de la red en cada
     *                     iteración.
     */
    public RedNeuronalPrediccion(int capacidad, int numEntradas, int numSalidas,
                                 double coeficienteA)
    {
        super(capacidad, numEntradas, numSalidas, coeficienteA);
    }

    @Override
    public void asignarCapaSalida()
    {
        // Por defecto es la última capa
        // se le asigna la función de activación de salida
        // acorde al tipo de red neuronal.
        int numCapa = capas.getLongitud();
        CapaRedNeuronal capaSalida = new CapaRedNeuronal( (numCapa - 1),
        SALIDAS, TipoFuncionActivacion.LINEAL);
        // Se asigna la capa de salida en la última posición.
        capas.agregar(capaSalida);
        asignarPesosYBias(numCapa);
    }

    @Override
    protected void calcularSensibilidades(VectorNum entradas, VectorNum salidas, VectorNum target)
    {
        // Indice de la capa actual (capa de salida).
        int numCapa = capas.getLongitud() - 1;
        // Cálculo del error de la red, respecto a los
        // resultados esperados.
        double error = calcularError(salidas, target);
        // Cálculo de las sensibilidades de la capa de salida.
        calcularSensibilidades(salidas.copiar(), error);

        // Sensibilidades para las capas ocultas.
        for(int index = capas.getLongitud() - 2; index >= 0; index--)
        {
            calcularSensibilidades(index);
        }
    }

    @Override
    protected double calcularError(VectorNum resultados, VectorNum targets)
    {
        return FuncionPerdida.perdidaLineal(resultados, targets);
    }

    /**
     * Calcula las sensibilidades para las capas ocultas de la red.
     * @param numCapa Número de la capa.
     * @return Regresa un vector con las sensibilidades
     * obtenidas.
    */
    private void calcularSensibilidades(int numCapa)
    {
        CapaRedNeuronal capaActual = obtenerCapa(numCapa);
        // 1.- Se derivan la o las salidas de la capa actual.
        MatrizNum sensibilidadesActual = capaActual.derivarSalidas();
        // 2.- Se multiplican las derivadas de la capa actual por
        // la matriz de pesos traspuesta de la capa anterior
        // (La capa anterior en este contexto es en realidad la
        // que está a la derecha de la actual).
        CapaRedNeuronal capaAnterior = obtenerCapa(numCapa + 1);
        MatrizNum pesosCapaAnterior = (MatrizNum)capaAnterior.
        getMatrizPesos().copiar();
        pesosCapaAnterior.aplicarTraspuesta();
        sensibilidadesActual.multiplicarMatriz(pesosCapaAnterior);
        // 3.- El resultado se multiplica ahora por las sensibilidades
        // de la capa anterior.
        VectorNum sensibilidadesAnterior = capaAnterior.
        getSensibilidades();
        sensibilidadesActual.multiplicar(sensibilidadesAnterior);
        // Las sensibilidades resultantes deben ser vector así que se
        // convierten.
        capaActual.setSensibilidades(sensibilidadesActual.reDim(
        ConversionMatriz.POR_RENGLON));
    }

    /**
     * Calcula las sensibilidades para la capa
     * de salida de la red.
     * @param salidas Valores de salida que obtuvo la capa.
     * @param error Coeficiente de error que generó la red.
     * @return Regresa un vector con las sensibilidades
     * obtenidas.
    */
    private void calcularSensibilidades(VectorNum salidas, double error)
    {
        // 1.- Se derivan la o las salidas de la última capa.
        CapaRedNeuronal capaSalida = obtenerCapa(capas.getLongitud() - 1);
        MatrizNum sensibilidades = capaSalida.derivarSalidas();
        // 2.- Se multiplica la matriz por -2.
        sensibilidades.multiplicarEscalar(-2);
        // 3.- Se multiplica el vector por el coeficiente de error.
        sensibilidades.multiplicarEscalar(error);
        // 4.- Asignación de las sensibilidades obtenidas a la capa de salida.
        capaSalida.setSensibilidades(sensibilidades.reDim(
        ConversionMatriz.POR_RENGLON));
    }
}
