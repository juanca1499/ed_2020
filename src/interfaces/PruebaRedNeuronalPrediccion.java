package interfaces;

import edlineal.*;
import entradasalida.SalidaEstandar;
import entradasalida.archivos.LeerArchivo;
import ia.*;
import matematicas.FuncionPerdida;
import matematicas.Matematica;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import utilidades.Casteador;
import utilidades.ManipuladorString;
import utilidades.NumeroAleatorio;
import utilidades.TipoFuncionActivacion;

/**
 * Esta clase implementa el TDA PruebaRedNeuronalPrediccion.
 * Contiene las pruebas para la ejecución de la red
 * de prueba para el <b>Proyecto Final</b> de la
 * materia de Estructura de Datos.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class PruebaRedNeuronalPrediccion
{
    public static void main(String[] args)
    {
        // Inicialización de la red.
        RedNeuronal redNeuronal = new RedNeuronalPrediccion(
        2,1,1,0.1);
        redNeuronal.agregarCapa(2, TipoFuncionActivacion.SIGMOIDEAL);
        NumeroAleatorio random = new NumeroAleatorio();

        // Vectores que contendrán los valores de entrenamiento de la red.
        VectorNum entradas = new VectorNum(1);
        VectorNum target = new VectorNum(1);

                    //  ETAPA DE ENTRENAMIENTO   ///
        for(int epoca = 0; epoca < 10000; epoca++)
        {
            for(double variable = -2; variable <= 2; variable+= 0.1)
            {
                // Valor de entrenamiento aleatorio entre -2 y 2.
                double entrada = random.generarDouble(-2, 2);
                // Cálculo del resultado esperado.
                double resultado = Math.sin((Math.PI * entrada) / 4) + 1;
                entradas.setElemento(0,entrada);
                target.setElemento(0, resultado);
                // Ejecución del entrenamiento.
                VectorNum salidas = redNeuronal.entrenar(entradas, target);
                SalidaEstandar.consola("\n\nEntrada de la red: " + entrada);
                SalidaEstandar.consola("\nSalida de la red:\n");
                salidas.imprimir();
                SalidaEstandar.consola("\nResultado esperado: " +  resultado);
            }
        }

                            // PRUEBAS PARA LA RED YA ENTRENADA //
        // Arreglo para graficar los resultados de la red.
        Arreglo resultados = new Arreglo(40);

        for(double index = -2; index <= 2; index+= 0.1)
        {
            // Cálculo del resultado esperado.
            double resultado = Math.sin((Math.PI * index) / 4) + 1;
            entradas.setElemento(0,index);
            VectorNum salidas = redNeuronal.operar(entradas);
            // Se guarda el resultado de la red en la iteración.
            resultados.agregar(salidas.getElemento(0));
        }

        // Arreglos para graficar.
        double[] xDominio = new double[40];
        double[] yRangoSeno = new double[40];
        double[] yRedNeuronal = Casteador.convertirDoublePrimitivo(resultados);
        int contador = 0;

        // Cálculo de los valores de 'x' y 'y' para la función a aproximar.
        for(double index = -2; index <= 2; index+= 0.1)
        {
            xDominio[contador] = index;
            yRangoSeno[contador] = Math.sin((Math.PI * index) / 4) + 1;
            contador++;
        }

        // Gráfica de la función a aproximar y la función generada por la red.
        XYChart grafica = QuickChart.getChart(
        "Función de aproximaciones usando redes neuronales", "X", "Y",
        "Salida de la red (aproximación)", xDominio, yRedNeuronal);
        grafica.addSeries("Función seno original", xDominio, yRangoSeno);
        grafica.setXAxisTitle("Valores de entrada de las muestras de prueba");
        grafica.setYAxisTitle("Valores de función objetivo y función aproximada");
        new SwingWrapper(grafica).displayChart().setTitle("Red Neuronal Predicción");
    }
}
