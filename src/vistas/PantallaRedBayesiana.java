package vistas;

import catalogos.VariableBayesiana;
import edlineal.Arreglo;
import edlineal.ListaLigada;
import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;
import utilidades.RedBayesiana;

/**
 * Contiene los mensajes y acciones que interaccionan
 * con el usuario para comunicarse y obtener información
 * de la red bayesiana.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0.
 */
public class PantallaRedBayesiana
{
    /** Guarda las opciones que ofrece el menú. */
    private Arreglo opciones;
    /** Guarda la red bayesiana que será manipulada de acuerdo a las peticiones
     * del usuario. */
    private RedBayesiana redBayesiana;

    public PantallaRedBayesiana(RedBayesiana redBayesiana)
    {
        this.redBayesiana = redBayesiana;
        opciones = new Arreglo(3);
        // Se asignan las opciones del menu.
        opciones.agregar("Obtener probabilidad.");
        opciones.agregar("Calcular probabilidad compuesta.");
        opciones.agregar("Salir");
    }

    /**
     * Muestra al usuario en la salida estándar
     * un mensaje de bienvenida.
     */
    public void mensajeInicio()
    {
        SalidaEstandar.consola("Bienvenido al programa de la Red Bayesiana!");
    }

    /**
     * Muestra al usuario en la salida estándar
     * un mensaje de salida.
     */
    public void mensajeSalida()
    {
        SalidaEstandar.consola("\nGracias por visitar a la Red Bayesiana!");
    }

    /**
     * Imprime en la salida estándar las opciones que puede
     * ejecutar el usuario sobre la red bayesiana.
     */
    public void mostrarOpciones()
    {
        SalidaEstandar.consola("\nElija una de las siguientes opciones:");

        // Se iteran e imprimen las opciones
        for(int index = 0; index < opciones.getLongitud(); index++)
        {
            SalidaEstandar.consola("\n[" + (index + 1) + "]---> " +
            opciones.getElemento(index));
        }
        int respuesta = pedirOpcion();

        switch (respuesta)
        {// Se direcciona a la pantalla correspondiente según la opción elegida.
            case 1:
                probabilidad();
                break;
            case 2:
                probabilidadConjunta();
                break;
            case 3:
                mensajeSalida();
                break;
        }
    }

    /**
     * Solicita al usuario por medio de la salida estándares
     * una respuesta a las opciones del menu.
     * @return Regresa la opción dada por el usuario.
     */
    private int pedirOpcion()
    {
        boolean opcionValida = false;
        Integer respuesta = -1;
        do
        {
            SalidaEstandar.consola("\n---> ");
            try
            {
                // Se comprueba que la opcion sea un valor entero.
                respuesta = Integer.parseInt(EntradaEstandar.consolaCadena());
                if(respuesta > 0 && respuesta < (opciones.getLongitud() + 1))
                {// La opción elegida está dentro del rango permitido y es válida.
                    opcionValida = true;
                }
                else
                {
                    SalidaEstandar.consola("\nError!: La opción dada no existe. \n" +
                    "Introduza el valor nuevamente");
                }
            }
            catch (Exception ex)
            {
                SalidaEstandar.consola("\nError!: La opción es inválida! \n" +
                "Introduza el valor nuevamente");
            }
        } while (opcionValida == false);
        return respuesta.intValue();
    }

    /**
     * Solicita al usuario por medio de la salida estándar
     * una de las variables que posee la red bayesiana.
     * @return Regresa la variable dada por el usuario.
     */
    private String pedirVariable()
    {
        boolean variableValida = false;
        String respuesta = "";
        do
        {
            SalidaEstandar.consola("\nIndica la clave de una de las variables: \n");
            redBayesiana.imprimirVariables();
            SalidaEstandar.consola("\n---> ");
            respuesta = EntradaEstandar.consolaCadena();
            if(redBayesiana.existeVariable(respuesta))
            {// La variable sí existe.
                variableValida = true;
            }
            else
            {// La variable no existe.
                SalidaEstandar.consola("Error!: Por favor indica una " +
                "clave de variable existente.");
            }
        }while (variableValida == false);
        return respuesta;
    }

    /**
     * Solicita al usuario por medio de la salida estándar
     * las condiciones para ubicar un caso de probabilidad
     * en una variable.
     * @param clave Identificador de la variable.
     * @return Regresa una lista con las coordenadas del
     * caso de probabilidad a buscarse.
     */
    private ListaLigada pedirCondiciones(String clave)
    {
        // Lista que guardará el caso de probabilidad.
        ListaLigada coordenadas = new ListaLigada();
        // Variables padre o dependencias de la variable en cuestión.
        ListaLigada dependencias = redBayesiana.obtenerDependencias(clave);
        dependencias.inicializaIterador();

        // Se iteran las dependencias.
        while (dependencias.hayNodoSiguiente())
        {
            String nombreVariable = ((VariableBayesiana)dependencias.
            obtenerSiguiente()).getNombre().toString();
            SalidaEstandar.consola("\n¿Ocurrió el siguiente suceso? " +
            "'" + nombreVariable + "'" + " (S/N)");
            SalidaEstandar.consola("\n---> ");
            String respuesta = EntradaEstandar.consolaCadena();

            if(respuesta.equalsIgnoreCase("S"))
            {// La respuesta es afirmativa, se agrega a las coordenadas.
                coordenadas.agregar(1);
            }
            else if(respuesta.equalsIgnoreCase("N"))
            {// La respuesta es una negación, se agrega a las coordenadas.
                coordenadas.agregar(-1);
            }
        }
        return coordenadas;
    }

    /**
     * Solicita al usuario por medio de la salida estándar
     * los datos necesarios para obtener la probabilidad
     * de una variable.
     */
    public void probabilidad()
    {
        SalidaEstandar.consola("\nCalculando una probabilidad...");
        String  claveVariable = pedirVariable();
        ListaLigada coordenadas = pedirCondiciones(claveVariable);
        // Se pide la última coordenada.
        SalidaEstandar.consola("\n¿Desea calcular la probabilidad " +
        "afirmativa o negativa? A/N");
        SalidaEstandar.consola("\n---> ");
        String respuesta = EntradaEstandar.consolaCadena();

        if(respuesta.equalsIgnoreCase("A"))
        {
            coordenadas.agregar(1);
        }
        else if(respuesta.equalsIgnoreCase("N"))
        {
            coordenadas.agregar(-1);
        }
        // Después de recabar los datos se obtiene la probabilidad.
        double probabilidad = redBayesiana.obtenerProbabilidad(
        claveVariable,coordenadas);
        // Se imprime la probabilidad en formato de porcentaje
        SalidaEstandar.consola("La probabilidad de que pase el " +
        "suceso es del: " + (probabilidad * 100) + "%" );
    }

    /**
     * Solicita al usuario por medio de la salida estándar
     * los datos necesarios para obtener la probabilidad
     * conjunta de una serie de variables.
     */
    public void probabilidadConjunta()
    {
        SalidaEstandar.consola("\nCalculando una probabilidad conjunta...");
        String variablesAcumuladas = "";
        boolean terminar = false;
        // Variables a calcular su probabilidad conjunta.
        ListaLigada variables = new ListaLigada();
        // Lista de listas con las coordenadas para la probabilidad cada variable.
        ListaLigada coordenadas = new ListaLigada();

        do
        {
            SalidaEstandar.consola("\n\nIndique una variable:");
            Object clave = pedirVariable();
            // Se acumulan las variables en la lista.
            variables.agregar(clave);
            variablesAcumuladas += clave + " ";
            SalidaEstandar.consola("\nVariables acumuladas: " + variablesAcumuladas);
            SalidaEstandar.consola("\n¿Desea continuar agregando variables? S/N");
            SalidaEstandar.consola("\n---> ");
            String respuesta = EntradaEstandar.consolaCadena();

            if(respuesta.equalsIgnoreCase("N"))
            {// La respuesta es negativa, ya no se agregan más variables
                terminar = true;
            }
        }while (terminar == false);

        variables.inicializaIterador();
        // Se piden las dependencias de todas las variables.
        while (variables.hayNodoSiguiente())
        {
            String clave = (String) variables.obtenerSiguiente();
            SalidaEstandar.consola("\nCondicion(es) de: " + clave);
            coordenadas.agregar(pedirCondiciones(clave));
        }

        // Se pide la última coordenada que será agregada por igual
        // a todas las listas.
        SalidaEstandar.consola("\n¿Desea calcular la probabilidad " +
        "afirmativa o negativa? A/N");
        SalidaEstandar.consola("\n---> ");
        String respuesta = EntradaEstandar.consolaCadena();

        if(respuesta.equalsIgnoreCase("A"))
        {
            agregarUltimaCondicion(coordenadas, 1);
        }
        else if(respuesta.equalsIgnoreCase("N"))
        {
            agregarUltimaCondicion(coordenadas, -1);
        }

        // Se calcula la probabilidad conjunta.
        double probabilidadConj = redBayesiana.obtenerProbabiliadConjunta(variables, coordenadas);
        // Se imprime la probabilidad en formato de porcentaje
        SalidaEstandar.consola("La probabilidad de que pasen los " +
                "sucesos es del: " + (probabilidadConj * 100) + "%" );
    }

    /**
     * Agrega a todas las listas de coordenadas
     * una última condición para cuando se desea
     * obtener el caso afirmativo o negativo en
     * una probabilidad conjunta.
     * @param coordenadas Lista de listas de coordenadas.
     * @param condicion Condición afirmativa o negativa
     *                  a ser agregada.
     */
    private void agregarUltimaCondicion(ListaLigada coordenadas, int condicion)
    {
        coordenadas.inicializaIterador();
        // Se iteran todos los elementos de la lista.
        while (coordenadas.hayNodoSiguiente())
        {
            // Se agrega a todas las listas la última condición.
            ListaLigada listaActual = (ListaLigada)
            coordenadas.obtenerSiguiente();
            listaActual.agregar(condicion);
        }
    }
}
