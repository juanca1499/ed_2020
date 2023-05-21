package ia;

import edlineal.Arreglo;
import edlineal.MatrizNum;
import edlineal.VectorNum;
import matematicas.FuncionLineal;
import matematicas.FuncionSigmod;
import matematicas.FuncionSoftMax;
import utilidades.NumeroAleatorio;
import utilidades.TipoFuncionActivacion;


/**
 * Esta clase implementa el TDA CapaRedNeuronal.
 * Una capa es un componente de una red neuronal que
 * contiene una o muchas neuronas.
 * En esta clase se definen las características y
 * funcionamiento que posee una capa de una red
 * neuronal.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class CapaRedNeuronal
{
    /** Guarda las neuronas que posee la capa. */
    private Arreglo neuronas;
    /** Guarda el número de capa que se ocupa en la red neuronal. */
    private int numCapa;
    /** Guarda la cantidad de datos que recibe la capa. */
    private int numEntradas;
    /** Guarda los costos que conlleva llegar a cada una de las neuronas. */
    private MatrizNum matrizPesos;
    /** Guarda los últimos resultados que obtuvo la capa. */
    private VectorNum ultimasSalidas;
    /** Guarda las últimas sensibilidades calculadas para la capa. */
    private VectorNum sensibilidades;
    /** Guarda el tipo de función de activación que tienen las neuronas de la capa. */
    private TipoFuncionActivacion funcionCapa;

    /**
     * Define el constructor TDA CapaRedNeuronal.
     * @param numCapa Número de capa respecto a la red neuronal
     *                entera.
     * @param numNeuronas Cantidad de neuronas que poseerá la capa.
     * @param funcion Función de activación a asignarse para todas
     *                las neuronas.
     */
    public CapaRedNeuronal(int numCapa, int numNeuronas,
                           TipoFuncionActivacion funcion)
    {
        this.numCapa = numCapa;
        neuronas = new Arreglo(numNeuronas);
        this.funcionCapa = funcion;
        inicializarNeuronas();
    }

    /**
     * Crea los objetos de las neuronas según
     * la cantidad especificada en el constructor
     */
    private void inicializarNeuronas()
    {
        NumeroAleatorio random = new NumeroAleatorio();

        // Se itera el arreglo de neuronas.
        for(int index = 0; index < neuronas.capacidad(); index++)
        {
            Neurona neuronaActual = new Neurona();
            neuronaActual.setNumCapa(numCapa);
            neuronaActual.setNumero(index);
            // Se asigna un primer bias en un rango de -1 a 1.
            neuronaActual.setBias(random.generarDouble(-1, 1));
            // Se agrega el objeto inicializado al arreglo.
            neuronas.setElemento(index, neuronaActual);
        }
    }

    /**
     * Rellena la matriz de pesos con valores
     * iniciales que van desde -1 a 1.
     * @param numEntradas Cantidad de datos que recibe
     *                    la capa de neuronas.
     */
    public void inicializarPesos(int numEntradas)
    {
        NumeroAleatorio random = new NumeroAleatorio();
        // Se crea la matriz de pesos con relación a las neuronas
        // de la capa y la cantidad de entradas.
        this.numEntradas = numEntradas;
        matrizPesos = new MatrizNum(neuronas.getLongitud(), numEntradas);

        // Se iteran las filas.
        for(int fila = 0; fila < matrizPesos.numRenglones(); fila++)
        {
            // Se iteran las columnas.
            for(int columna = 0; columna < matrizPesos.numColumnas(); columna++)
            {
                // Asignación de número aleatorio.
                matrizPesos.setElemento(fila, columna,
                random.generarDouble(-1, 1));
            }
        }
    }

    /**
     * Asigna como bias un número aleatorio entre -1 y 1
     * a cada una de las neuronas de la capa.
     */
    public void generarBias()
    {
        NumeroAleatorio random = new NumeroAleatorio();
        // Se iteran las neuronas.
        for(int index = 0; index < neuronas.getLongitud(); index++)
        {
            // Cálculo del bias aleatorio entre -1 y 1.
            double bias = random.generarDouble(-1, 1);
            // Se extrae una neurona por iteración.
            Neurona neuronaActual = (Neurona) neuronas.getElemento(index);
            neuronaActual.setBias(bias);
        }
    }

    /**
     * Envia a cada una de las neuronas de la capa los
     * datos necesarios para que genere una salda.
     * @param datosEntrada Matriz de valores de entrada de la
     *                     neurona.
     * @return Regresa una vector con los resultados que obtuvieron
     * las neuronas.
     */
    public VectorNum operar(VectorNum datosEntrada)
    {
         // Aquí se guardarán los resultados de las neuronas.
         VectorNum datosSalida = new VectorNum(neuronas.getLongitud());

        // Se iteran las neuronas.
       for(int index = 0; index < neuronas.getLongitud(); index++)
        {
            // Se extrae una neurona por iteración.
            Neurona neuronaActual = obtenerNeurona(index);
            // Se envía una copia de los valores de entrada y
            // una copia de los pesos de la matriz que están relacionados
            // con la neurona actual.
            double salida = neuronaActual.operar(datosEntrada.copiar(),
            (VectorNum) matrizPesos.copiarRenglon(index));
            datosSalida.setElemento(index, salida);
        }
        // Se aplica la función de activación de la capa.
        datosSalida = funcionActivacion(datosSalida);
        // Se guardan los resultados o salidas de la capa.
        ultimasSalidas = datosSalida;
        return datosSalida;
    }

    /**
     * Aplica la función de activación correspondiente
     * a la que tiene definida la capa.
     * @param valores Calculos de las funciones de red
     *                más el bias de la neuronas.
     * @return Regresa un vector con los resultados.
     */
    private VectorNum funcionActivacion(VectorNum valores)
    {
        if(funcionCapa == TipoFuncionActivacion.LINEAL)
        {
            return FuncionLineal.calcular(valores);
        }
        else if(funcionCapa == TipoFuncionActivacion.SIGMOIDEAL)
        {
            return FuncionSigmod.calcular(valores);
        }
        else if(funcionCapa == TipoFuncionActivacion.SOFTMAX)
        {
            return FuncionSoftMax.calcular(valores);
        }
        else
        {
            return null;
        }
    }

    /**
     * Calcula las derivadas de las últimas salidas
     * de la capa según la función de activación
     * que se tiene.
     * @return Regresa una matriz con la diagonal
     * principal rellena de los resultados de las
     * derivadas y las demás posiciones en ceros.
     */
    public MatrizNum derivarSalidas()
    {
        if(funcionCapa == TipoFuncionActivacion.LINEAL)
        {
            return FuncionLineal.calcularDerivadas(ultimasSalidas);
        }
        else if(funcionCapa == TipoFuncionActivacion.SIGMOIDEAL)
        {
            return FuncionSigmod.calcularDerivadas(ultimasSalidas);
        }
        else
        {
            return null;
        }
    }

    /**
     * Calcula las derivadas para las funciones que requieren
     de los valores esperados de la red de las últimas salidas
     * de la capa según la función de activación
     * que se tiene.
     * @param targets Vector con los resultados
     *                esperados de la red.
     * @return Regresa una matriz con la diagonal
     * principal rellena de los resultados de las
     * derivadas y las demás posiciones en ceros.
     */
    public MatrizNum derivarSalidas(VectorNum targets)
    {
        return FuncionSoftMax.calcularDerivadas(ultimasSalidas, targets);
    }

    /**
     * Recupera los bias de cada una de las neuronas
     * de la capa.
     * @return Regresa un vector con los bias.
     */
    public VectorNum getBias()
    {
        VectorNum biases = new VectorNum(neuronas.getLongitud());

        // Se recorren las neuronas de la capa.
        for(int index = 0; index < neuronas.getLongitud(); index++)
        {
            Neurona neuronaActual = obtenerNeurona(index);
            biases.agregar(neuronaActual.getBias());
        }
        return biases;
    }

    /**
     * Cambia el bias de cada neurona a un valor
     * nuevo.
     * @param biases Valores a ser asignados.
     */
    public void asignarBias(VectorNum biases)
    {
        // Se recorren las neuronas de la capa.
        for(int index = 0; index < neuronas.getLongitud(); index++)
        {
            Neurona neuronaActual = obtenerNeurona(index);
            // Sustitución del antiguo bias por el nuevo.
            neuronaActual.setBias((double)biases.getElemento(index));
        }
    }

    /**
     * Ajusta los pesos de la capa para obtener mejores
     * resultados en próximas operaciones.
     * @param entradas Últimos datos que recibió la capa
     *                 como entrada.
     * @param coeficienteA Coeficiente dado
     *                     sobre el que la red neuronal
     *                     aprende.
     */
    public void actualizarPesos(VectorNum entradas ,double coeficienteA)
    {
        MatrizNum pesosActual = matrizPesos;
        VectorNum sensibilidadesActual = sensibilidades;
        // En esta este contexto la capa anterior es la que está
        // a la izquierda de la capa actual.
        // 1.- Convertir el vector de sensibilidades de la capa actual.
        // y el vector de salidas de la capa anterior
        // (entradas de la capa actual) a matriz. El vector de las salidas
        // de la capa anterior se transforma a matriz respecto a las columnas
        // para quedar traspuesta.
        MatrizNum matrizSalidasAnterior = entradas.reDim(
        1, entradas.capacidad());
        // 2.- El vector de sensibilidades de la capa actual se transforma
        // a matriz en base a sus renglones.
        MatrizNum matrizSensibilidadesActual = sensibilidadesActual.reDim(
        sensibilidadesActual.capacidad(), 1);
        // 3.- Multiplicar las sensibilidades por el coeficiente de aprendizaje.
        matrizSensibilidadesActual.multiplicarEscalar(coeficienteA);
        // 4.- Multiplicar la matriz de sensibilidades de la capa actual con las
        // salidas que obtuvo la capa anterior.
        matrizSensibilidadesActual.multiplicarMatriz(matrizSalidasAnterior);
        // 4.- Restar a la matriz de pesos que tiene actualmente la capa
        // con el resultado de las operaciones anteriores.
        pesosActual.restarMatriz(matrizSensibilidadesActual);
    }

    /**
     * Reajusta los bias de las neuronas de la capa
     * para mejorar los resultados en próximas operaciones.
     * @param coeficienteA Coeficiente dado
     *                               sobre el que la red neuronal
     *                               aprende.
     */
    public void actualizarBias(double coeficienteA)
    {
        // Se obtienen los bias actuales de las neuronas de la capa.
        VectorNum biases = getBias();
        // Se obtienen las últimas sensibilidades asignadas a la capa.
        VectorNum sensibilidades = this.sensibilidades.copiar();
        // 1.- Se multiplican las últimas sensibilidades por el coeficiente de
        // aprendizaje.
        sensibilidades.multEscalar(coeficienteA);
        // 2.- Se restan los bias actuales con el resultado de la multiplicación.
        biases.restar(sensibilidades);
        // 3.- Asignación de los nuevos bias a la capa.
        asignarBias(biases);
    }

    /**
     * Recupera el número de la capa.
     * @return Regresa el campo numCapa.
     */
    public int getNumCapa()
    {
        return numCapa;
    }

    /**
     * Asigna un número a la capa.
     * @param numCapa Valor a ser asignado.
     */
    public void setNumCapa(int numCapa)
    {
        this.numCapa = numCapa;
    }

    /**
     * Recupera la cantidad de neuronas que
     * hay en la capa.
     * @return Regresa la longitud del arreglo
     * de neuronas.
     */
    public int getLongitud()
    {
        return neuronas.getLongitud();
    }

    /**
     * Recupera los pesos que contiene la capa.
     * @return Regresa el campo matrizPesos.
     */
    public MatrizNum getMatrizPesos()
    {
        return matrizPesos;
    }

    /**
     * Asigna una matriz de pesos a la capa.
     * @param matriz Valor a ser asignado.
     */
    public void setMatrizPesos(MatrizNum matriz)
    {
        this.matrizPesos = matriz;
    }

    /**
     * Recupera el vector de las últimas salidas
     * que generó la capa.
     * @return Regresa el vector de salidas.
     */
    public VectorNum getUltimasSalidas()
    {
        return ultimasSalidas;
    }

    /**
     * Recupera el vector de las últimas sensibilidades
     * calculadas para la capa.
     * @return Regresa el vector de sensibilidades.
     */
    public VectorNum getSensibilidades()
    {
        return sensibilidades;
    }

    /**
     * Asigna el vector de sensibilidades calculado.
     * @param sensibilidades Valor a ser asignado.
     */
    public void setSensibilidades(VectorNum sensibilidades)
    {
        this.sensibilidades = sensibilidades;
    }

    /**
     * Recupera la función de activación que tienen
     * asignadas las neuronas de la capa.
     * @return Regresa la función de activación.
     */
    public TipoFuncionActivacion getFuncionCapa()
    {
        return funcionCapa;
    }

    /**
     * Recupera una de las neuronas contenidas en la
     * capa.
     * @param numNeurona Posición de la neurona en la capa.
     * @return Regresa la neurona en caso de haberla encontrado,
     * o null en caso contrario.
     */
    private Neurona obtenerNeurona(int numNeurona)
    {
        return (Neurona) neuronas.getElemento(numNeurona);
    }
}