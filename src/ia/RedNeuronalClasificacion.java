package ia;

import edlineal.MatrizNum;
import edlineal.VectorNum;
import matematicas.FuncionPerdida;
import utilidades.ConversionMatriz;
import utilidades.TipoFuncionActivacion;

/**
 * Esta clase implementa el TDA RedNeuronalClasificacion.
 * Esta especialización de una red neuronal permite
 * realizar clasificaciones a partir de un conjunto de
 * datos que recibe. Devuelve múltiples datos resultado
 * de la predicción.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
*/
public class RedNeuronalClasificacion extends RedNeuronal
{

    /**
     * Define el constructor TDA RedNeuronalClasificacion.
     * @param capacidad Cantidad de capas que contendrá la red.
     * @param numEntradas Cantidad de neuronas que habrá en la capa
     *                    de entrada.
     * @param numSalidas Cantidad de neuronas que habrá en la capa
     *                   de salida.
     * @param coeficienteA Grado de aprendizaje de la red en cada
     *                     iteración.
     */
    public RedNeuronalClasificacion(int capacidad, int numEntradas, int numSalidas,
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
        SALIDAS, TipoFuncionActivacion.SOFTMAX);
        // Se asigna la capa de salida en la última posición.
        capas.setElemento(numCapa,capaSalida);
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
        calcularSensibilidades(target);

        // Sensibilidades para las capas ocultas.
        for(int index = capas.getLongitud() - 2; index >= 0; index--)
        {
            calcularSensibilidades(index);
        }
    }

    @Override
    protected double calcularError(VectorNum resultados, VectorNum targets)
    {
        return FuncionPerdida.entropiaCruzada(resultados, targets);
    }

    /**
     * Calcula las sensibilidades para las capas ocultas de la red.
     * @param numCapa Número de la capa.
     *                esperados de la red.
     */
    private void calcularSensibilidades(int numCapa)
    {
        CapaRedNeuronal capaActual = obtenerCapa(numCapa);
        // 1.- Se derivan la o las salidas de la capa actual.
        MatrizNum sensibilidadesActual = capaActual.derivarSalidas();
        // 2.- Se multiplican las derivadas de las salidas por
        // la matriz de pesos de la capa anterior traspuesta.
        // (La capa anterior en este contexto es en realidad la
        // que está a la derecha de la actual).
        CapaRedNeuronal capaAnterior = obtenerCapa(numCapa + 1);
        MatrizNum pesosCapaAnterior = (MatrizNum)capaAnterior.
        getMatrizPesos().copiar();
        pesosCapaAnterior.aplicarTraspuesta();
        sensibilidadesActual.multiplicarMatriz(pesosCapaAnterior);
        // 3.- Finalmente, se multiplica el resultado por las sensibilidades
        // también de la capa anterior.
        VectorNum sensibilidadesAnterior = capaAnterior.
        getSensibilidades();
        sensibilidadesActual.multiplicar(sensibilidadesAnterior);
        // El resultado deber ser un vector, así que se transforma la matriz.
        capaActual.setSensibilidades(sensibilidadesActual.reDim(
        ConversionMatriz.POR_RENGLON));
    }

    /**
     * Calcula las sensibilidades para la capa
     * de salida de la red.
     * @param target Valores de salida esperados
     *                de la capa.
     */
    private void calcularSensibilidades(VectorNum target)
    {
        CapaRedNeuronal capaSalida = obtenerCapa(capas.getLongitud() - 1);
        // Las sensibilidades de la capa de salida son equivalentes
        // a las derivadas de la misma capa.
        capaSalida.setSensibilidades(capaSalida.derivarSalidas(target).
        extraerDiagonalPrincipal());
    }
}
