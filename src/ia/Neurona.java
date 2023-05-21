package ia;

import edlineal.VectorNum;
import entradasalida.SalidaEstandar;
import matematicas.Matematica;
import utilidades.TipoFuncionActivacion;

/**
 * Esta clase implementa el TDA Neurona.
 * Una neurona es el componente más pequeño de una red
 * neuronal.
 * En esta clase se definen las características y
 * funcionamiento que posee una neurona.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class Neurona
{
    /** Guarda el número de la capa respecto a la red neuronal en que
     * se encuentra la neurona. */
    private int numCapa;
    /** Guarda el número que tiene la neurona respecto a su capa. */
    private int numero;
    /** Guarda el coeficiente a usar en la función de activación. */
    private double bias;

    /**
     * Define el constructor TDA Neurona.
     * En este constructor todos los parámetros
     * son asignados como nulos.
     */
    public Neurona()
    {
        this(0, 0, 0);
    }

    /**
     * Define el constructor TDA Neurona.
     * @param numCapa Número de la capa en la que se encuentra
     *                la neurona.
     * @param numero Número de la neurona respecto a la capa.
     * @param bias Coeficiente sumatorio para la función de
     *             activación.
     */
    public Neurona(int numCapa, int numero, double bias)
    {
        this.numCapa = numCapa;
        this.numero = numero;
        this.bias = bias;
    }

    /**
     * Recupera el número de capa asignado.
     * @return Regresa el campo numCapa.
     */
    public int getNumCapa()
    {
        return numCapa;
    }

    /**
     * Asigna un número de capa a la neurona.
     * @param numCapa Valor a ser asignado.
     */
    public void setNumCapa(int numCapa)
    {
        this.numCapa = numCapa;
    }

    /**
     * Recupera el número de la neurona respecto
     * a su capa.
     * @return Regresa el campo numero.
     */
    public int getNumero()
    {
        return numero;
    }

    /**
     * Asigna un número a la neurona respecto
     * a su capa.
     * @param numero Valor a ser asignado.
     */
    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    /**
     * Recupera el coeficiente a usarse en la función
     * de activación.
     * @return Regresa el campo bias.
     */
    public double getBias()
    {
        return bias;
    }

    /**
     * Asigna un coeficiente a usarse en la función de
     * activación.
     * @param bias Valor a ser asignado.
     */
    public void setBias(double bias)
    {
        this.bias = bias;
    }

    /**
     * Realiza la multiplicación de los datos de entrada
     * con los pesos para llegar a la neurona y suma el bias
     * al resultado.
     * @param datosEntrada Conjunto de datos de entrada.
     * @param pesosDestino Fila de la matriz de pesos de la
     *                     capa que tiene como destino la neurona.
     * @return Regresa el resultado de la operación.
     */
    public double operar(VectorNum datosEntrada, VectorNum pesosDestino)
    {
        // 1.- Multiplicación y suma de los datos de entrada
        // con los pesos de la neurona.
        double sumatoria = pesosDestino.productoPunto(datosEntrada);
        // 2.- Se agrega el bias.
        sumatoria += bias;
        return sumatoria;
    }

    @Override
    public String toString()
    {
        return numCapa + "(" + numero + ")";
    }
}
