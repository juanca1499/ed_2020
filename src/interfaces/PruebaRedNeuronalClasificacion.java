package interfaces;

import edlineal.*;
import entradasalida.SalidaEstandar;
import entradasalida.archivos.LeerArchivo;
import ia.RedNeuronal;
import ia.RedNeuronalClasificacion;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import utilidades.ManipuladorString;
import utilidades.TipoFuncionActivacion;


/**
 * Esta clase implementa el TDA PruebaRedNeuronalClasificacion.
 * Contiene las pruebas para la ejecución del
 * <b>Proyecto Final</b> de la materia de
 * Estructura de Datos.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class PruebaRedNeuronalClasificacion
{
    public static void main(String[] args)
    {
                            // PARTE DE ENTRENAMIENTO //

        // Extracción de los datos de entrenamiento
        // de los archivos línea por línea.
        Arreglo lineasEntradas = LeerArchivo.leer(
        "resources\\redneuronal\\entradas_entrenamiento.txt", 1728);
        Arreglo lineasTargets = LeerArchivo.leer(
        "resources\\redneuronal\\targets_entrenamiento.txt", 1728);

        Arreglo entradasRed = new Arreglo(1728);
        Arreglo targetsRed = new Arreglo(1728);

        // Extracción de pares de datos.
        for(int index = 0; index < lineasEntradas.getLongitud(); index++)
        {
            // Separación de los datos de entrada y los datos
            // target sobre las líneas extraídas de los archivos
            // para el entrenamiento.
            ListaDobleLigada cadenaEntrada = ManipuladorString.separar(
            (String) lineasEntradas.getElemento(index), ' ');
            ListaDobleLigada cadenaTarget = ManipuladorString.separar(
            (String) lineasTargets.getElemento(index), ' ');

            // Transformación de las Listas Dobles Ligadas con
            // las separaciones a vectores. Cada uno es un
            // par de entradas sobre el conjunto completo de datos
            // para entrenar la red. Estos vectores se agregan
            // a un arreglo para tener los pares de datos listos
            // para el entrenamiento.
            entradasRed.agregar(cadenaEntrada.aVector());
            targetsRed.agregar(cadenaTarget.aVector());
        }

        // Inicialización de la red neuronal.
        RedNeuronal redClasificacion = new RedNeuronalClasificacion(2, 6,
        4, 0.00009);
        redClasificacion.agregarCapa(50, TipoFuncionActivacion.SIGMOIDEAL);

        // Entrenamiento por épocas
        for(int epoca = 0; epoca < 8; epoca++)
        {
            // Extracción de pares de datos.
            for(int index = 0; index < lineasEntradas.getLongitud(); index++)
            {
                // Se extraen los pares de datos (entradas y target) del momento
                // para entrenar la red.
                VectorNum entradasActual = (VectorNum) entradasRed.getElemento(index);
                VectorNum targetsActual = (VectorNum) targetsRed.getElemento(index);

                // Ejecución del entrenamiento
                VectorNum salidasRed = redClasificacion.entrenar(
                entradasActual, targetsActual);
                SalidaEstandar.consola("\nSALIDA DE LA RED: \n");
                salidasRed.imprimir();
            }
        }
                            // PARTE DE PRUEBAS //


        // Extracción de los datos de prueba
        // de los archivos línea por línea.
        Arreglo lineasEntradasPruebas = LeerArchivo.leer(
    "resources\\redneuronal\\entradas_pruebas.txt", 346);
        Arreglo lineasTargetsPruebas = LeerArchivo.leer(
    "resources\\redneuronal\\targets_pruebas.txt", 346);

        // Numeración de las respuestas que da la red y las
        // originales. Los vectores tienen la misma longitud
        // a las salidas de la red, a cada posición se le
        // sumará el número más grande que hay en cada línea
        // del archivo y en cada salida de la red respectivamente.
        VectorNum conteoReales = new VectorNum(4);
        conteoReales.rellenar(0, 4);
        VectorNum conteoRed = new VectorNum(4);
        conteoRed.rellenar(0, 4);


        for(int index = 0; index < lineasEntradasPruebas.getLongitud(); index++)
        {
            // Separación de los datos de entrada y los datos
            // target sobre las líneas extraídas de los archivos
            // para las pruebas.
            ListaDobleLigada cadenaEntrada2 = ManipuladorString.separar(
            (String) lineasEntradasPruebas.getElemento(index), ' ');
            ListaDobleLigada cadenaTargets2 = ManipuladorString.separar(
            (String) lineasTargetsPruebas.getElemento(index), ' ');

            // Transformación de las Listas Dobles Ligadas con
            // las separaciones a vectores. Cada uno es un
            // par de entradas sobre el conjunto completo de datos
            // para probar la red. Estos vectores se agregan
            // a un arreglo para tener los pares de datos listos
            // para las pruebas.
            VectorNum entradasRed2 = cadenaEntrada2.aVector();
            VectorNum targetsRed2 = cadenaTargets2.aVector();

            // Ejecución de una prueba.
            VectorNum salidasRed2 = redClasificacion.operar(entradasRed2);
            SalidaEstandar.consola("\nSALIDA DE LA RED: \n");
            salidasRed2.imprimir();

            int posRespuesta = 0;
            int valorActual = 0;


            // El número más grande es la clasificación real.
            // Se aumenta el conteo de respuestas que deberían
            // ser en esa posición.
            posRespuesta = targetsRed2.posMayor();
            valorActual = (int)conteoReales.getElemento(posRespuesta) + 1;
            conteoReales.setElemento(posRespuesta, valorActual);
            // El número más grande en los resultados de la red
            // es su respuesta ante los datos de entrada. Se
            // aumenta el conteo de respuestas de este tipo
            // dadas por la red.
            posRespuesta = salidasRed2.posMayor();
            valorActual = (int)conteoRed.getElemento(posRespuesta) + 1;
            conteoRed.setElemento(posRespuesta, valorActual);
        }

        int aciertos = 0;

        for(int index = 0; index < conteoReales.getLongitud(); index++)
        {// Iteracioens de las respuestas para contar los aciertos.
            if((int)conteoReales.getElemento(index) != 0 &&
                (int)conteoRed.getElemento(index) != 0)
            {// La posición tiene elementos contabilizados.
                if((int)(conteoReales.getElemento(index)) >=
                (int)conteoRed.getElemento(index))
                {// Los resultados de la red en la posición
                 // son inferiores a los reales.
                    aciertos += (int)conteoRed.getElemento(index);
                }
                else
                {// Los resultados de la red en la posición son
                 // mayores a los reales.
                    aciertos += (int)conteoReales.getElemento(index);
                }
            }
        }
        double porcentajeAciertos = ((double) aciertos / 346) * 100;
        graficar(conteoReales,conteoRed, aciertos, porcentajeAciertos);
    }

    /**
     * Muestra la gráfica con los resultados obtenidos
     * y los esperados de la red.
     * @param datosReales Clasificación real.
     * @param datosRed Clasificación hecha por la red.
     * @param aciertos Cantidad de clasificaciones correctas
     *                 de la red.
     * @param porcentaje Porcentaje de respuestas correctas de
     *                   la red.
     */
    private static void graficar(VectorNum datosReales, VectorNum datosRed,
                                 int aciertos, double porcentaje)
    {
        // Clasificaciones de autos disponibles.
        Arreglo variantes = new Arreglo(4);
        variantes.agregar(1);
        variantes.agregar(2);
        variantes.agregar(3);
        variantes.agregar(4);

        // Grafica con los valores reales.
        CategoryChart graficaReal = new CategoryChartBuilder().width(600).height(600).
        title("Clasificación Real").xAxisTitle("Tipo de auto").
        yAxisTitle("Autos clasificados").build();
        graficaReal.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        graficaReal.getStyler().setHasAnnotations(true);
        graficaReal.addSeries(
    "Real", variantes.aList(), datosReales.aList());

        // Gráfica con los valores dados por la red.
        CategoryChart graficaRed = new CategoryChartBuilder().width(600).height(600).
        title("Clasificación de la Red").xAxisTitle("Tipo de auto").
        yAxisTitle("Autos clasificados").build();
        graficaRed.addSeries(
        "ACIERTOS: " + aciertos + "\nPORCENTAJE: " + porcentaje,
        variantes.aList(), datosRed.aList());
        graficaRed.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        graficaRed.getStyler().setHasAnnotations(true);

        // Series
        Arreglo graficas = new Arreglo(2);
        graficas.agregar(graficaReal);
        graficas.agregar(graficaRed);

        new SwingWrapper(graficas.aList()).displayChartMatrix().
        setTitle("Red neuronal clasificación de automóviles");
    }
}
