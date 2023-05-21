package utilidades;

import catalogos.VariableBayesiana;
import edlineal.Arreglo;
import edlineal.ListaLigada;
import ednolineal.ArbolProbabilidad;
import ednolineal.GrafoMatrizA;
import entradasalida.SalidaEstandar;

/**
 * Esta clase implementa el TDA RedBayesiana.
 * Modela un fenómeno mediante un conjunto de variables
 * y relaciones de dependencia entre ellas. Dado este
 * modelo, se hace inferencia bayesiana.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0.
 */
public class RedBayesiana
{
    /** Guarda las variables de la red y las relaciones que
     * hay entre ellas. */
    private GrafoMatrizA grafoVariables;
    /** Guarda únicamente las claves de las variables de la red. */
    private Arreglo arrayClaves;

    /**
     * Define el constructor TDA RedBayesiana.
     * @param tamano Cantidad de variables que
     *               contendrá la red.
     */
    public RedBayesiana(int tamano)
    {
        grafoVariables = new GrafoMatrizA(tamano);
        arrayClaves = new Arreglo(tamano);
    }

    /**
     * Inserta una nueva variable en la red.
     * @param valor Variable a ser agregada.
     * @return Regresa <b>true</b> en caso de haber
     * podido agregar la variable, o <b>false</b> en
     * caso contrario.
     */
    public boolean agregarVariable(VariableBayesiana valor)
    {
        // Se agrega la clave al arreglo de claves.
        if(grafoVariables.agregarVertice(valor))
        {// La clave no se repite.
            arrayClaves.agregar(valor.getClave());
            return true;
        }
        else
        {// La clave está repetida.
            return false;
        }
    }

    /**
     * Agrega una dependencia de una variable con otra.
     * @param claveOrigen Identificador de la variable que genera la dependencia.
     * @param claveDestino Identificador de la variable que recibe la dependencia.
     * @return Regresa <b>true</b> en caso de haber
     * podido agregar la dependencia, o <b>false</b> en
     * caso contrario.
     */
    public boolean agregarDependencia(Object claveOrigen,
                                      Object claveDestino)
    {
        if(arrayClaves.buscar(claveOrigen) != null &&
           arrayClaves.buscar(claveDestino) != null)
        {// Las claves existen.
            ArbolProbabilidad arbolProb = ((VariableBayesiana)
            grafoVariables.buscar(claveDestino)).getArbolProbabilidad();
            // Debido a la nueva dependencia, aumentan los casos
            // probabilísticos.
            arbolProb.aumentarCasos();
            // La representación de una dependencia es una arista.
            return grafoVariables.agregarArista(claveOrigen, claveDestino);
        }
        else
        {// Alguna de las clave no existe.
            return false;
        }
    }

    /**
     * Agrega un caso de probabilidad a una de las
     * variables de la red.
     * @param clave Identificador de la variable.
     * @param coordenadas Caso de probabilidad en
     *                    donde será agregado el valor.
     * @param valor Probabilidad del caso.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar la probabilidad, o <b>false</b> en caso contrario.
     */
    public boolean asignarProbabilidad(Object clave, ListaLigada coordenadas,
                                       double valor)
    {
        // Se busca la variable a donde será agregada el caso
        // de probabilidad.
        if(arrayClaves.buscar(clave) != null)
        {// La clave sí existe.
            VariableBayesiana variableB = (VariableBayesiana)
            grafoVariables.buscar(clave);
            ArbolProbabilidad arbolProbabilidad = variableB.getArbolProbabilidad();
            // Se asigna el valor de probabilidad al caso en cuestión.
            return arbolProbabilidad.asignar(coordenadas, valor);
        }
        else
        {// La clave no existe.
            return false;
        }
    }

    /**
     * Busca un valor de probabilidad de la variable.
     * @param clave Identificador de la variable.
     * @param coordenadas Caso de probabilidad en
     *                    donde será buscado el valor.
     * @return Regresa el valor de probabilidad si fue encontrado,
     * o <b>Double.NaN</b> en caso contrario.
     */
    public double obtenerProbabilidad(Object clave, ListaLigada coordenadas)
    {
        if(arrayClaves.buscar(clave) != null)
        {// La clave sí existe.
            // Se busca la variable a donde será agregada el caso
            // de probabilidad.
            VariableBayesiana variableB = (VariableBayesiana)
            grafoVariables.buscar(clave);
            ArbolProbabilidad arbolProbabilidad = variableB.getArbolProbabilidad();
            // Se regresa el valor de probabilidad.
            return arbolProbabilidad.buscar(coordenadas);
        }
        else
        {// La clave no existe.
            return Double.NaN;
        }
    }

    /**
     * Calcula la probabilidad de que dos variables o
     * eventos sucedan simultaneamente.
     * @param variables Lista con las claves de las variables
     *                  a calcular su probabilidad conjunta.
     * @param coordenadas Lista de listas con las coordenadas
     *                    de cada una de la variables para
     *                    ubicar su respectivo caso de probabilidad.
     * @return Regresa la probabilidad conjunta de las variables
     * dadas en caso de haberla podido calcular, o <b>Double.NaN</b>
     * en caso contrario.
     */
    public double obtenerProbabiliadConjunta(ListaLigada variables, ListaLigada coordenadas)
    {
        if((variables.getSize() != 0 && coordenadas.getSize() != 0) &&
           variables.getSize() == coordenadas.getSize())
        {// Las listas tienen la misma cantidad de datos.
            double probConjunta = 1;
            variables.inicializaIterador();
            coordenadas.inicializaIterador();

            // Se iteran las listas, pero sólo se comprueba la existencia
            // de nodo en una porque tienen la misma longitud.
            while (variables.hayNodoSiguiente())
            {
                VariableBayesiana variableActual = (VariableBayesiana)
                grafoVariables.buscar(variables.obtenerSiguiente());
                if(variableActual != null)
                {// La variable sí fue encontrada.
                    // Se extrae el árbol de probabilidades de la variable en curso.
                    ArbolProbabilidad arbolActual = variableActual.getArbolProbabilidad();
                    // Se obtiene el caso de probabilidad de la variable en curso.
                    double probIndividual = arbolActual.buscar(
                    (ListaLigada)coordenadas.obtenerSiguiente());
                    if(probIndividual != Double.NaN)
                    {// El caso de probabilidad fue encontrado.
                        probConjunta = probConjunta * probIndividual;
                    }
                    else
                    {// No fue encontrado el caso de probabilidad.
                        return Double.NaN;
                    }
                }
                else
                {// La variable actual no existe.
                    return Double.NaN;
                }
            }
            return probConjunta;
        }
        else
        {// No se puede calcular la probabilidad conjunta porque
         // no hay la misma cantidad de variables y coordenadas.
            return Double.NaN;
        }
    }

    /**
     * Busca las variables de las que depende la variable.
     * @param clave Identificador de la variable a
     *              buscar sus dependencias.
     * @return Regresa una lista con las variables de las
     * que depende.
     */
    public ListaLigada obtenerDependencias(Object clave)
    {
        return grafoVariables.obtenerAdyacencias(clave);
    }

    /**
     * Muestra en la salida estandar las variables
     * que existen en la red.
     */
    public void imprimirVariables()
    {
        SalidaEstandar.consola("Clave\t\tNombre completo");

        // Se itera el arreglo de calves.
        for(int index = 0; index < arrayClaves.getLongitud(); index++)
        {
            // Se extrae la variable acorde a la clave actual.
            VariableBayesiana variableActual = (VariableBayesiana)
            grafoVariables.buscar(arrayClaves.getElemento(index));
            // Se imprime la clave y nombre completo de las variables.
            SalidaEstandar.consola("\n" + variableActual.getClave()
            + "\t\t\t" + variableActual.getNombre());
        }
    }

    /**
     * Comprueba la existencia de una variable en la
     * red.
     * @param clave Identificador de la variable.
     * @return Regresa <b>true</b> en caso de que sí
     * exista la variable, o <b>false</b> en caso
     * contrario.
     */
    public boolean existeVariable(Object clave)
    {
        if(arrayClaves.buscar(clave) != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
