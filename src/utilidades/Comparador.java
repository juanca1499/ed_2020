package utilidades;



/**
 * Esta clase implementa el TDA Comparador.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Comparador
{
    /**
     * Realiza la comparación de dos objetos para evaluar si
     * el primero es mayor que el segundo, si es menor  o si
     * son iguales.
     * @param obj1 Objeto uno a ser comparado.
     * @param obj2 Objeto dos a ser comparado.
     * @return Regresa <b>0</b> si los dos objetos son
     * iguales, un valor menor que <b>0</b> si obj1 es menor
     * que obj2, o un número mayor que <b>0</b> si obj1 es
     * mayor que obj2.
     */
    public static int comparar(Object obj1, Object obj2)
    {
        if(obj1 instanceof Number && obj2 instanceof Number)
        {
            double num1 = Double.parseDouble(obj1.toString());
            double num2  = Double.parseDouble(obj2.toString());
            return Double.compare(num1, num2);
        }
        return obj1.toString().compareToIgnoreCase(obj2.toString());
    }
}
