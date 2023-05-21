package edlineal;


import entradasalida.SalidaEstandar;
import utilidades.NumeroAleatorio;

/**
 * Esta clase implementa el TDA VectorNum.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class VectorNum extends Arreglo
{

    /**
     * Define el constructor de TDA VectorNum.
     *
     * @param tam Es el tamaño para crear el Arreglo
     */
    public VectorNum(int tam)
    {
        super(tam);
    }

    @Override
    public boolean agregar(Object valor)
    {
        if(valor instanceof Number)
        {
            return super.agregar(valor);
        }
        return false;
    }

    @Override
    public Object eliminar(Object valor)
    {
        if(valor instanceof Number)
        {
            return super.eliminar(valor);
        }
        return null;
    }
    @Override
    public Object buscar(Object valor)
    {
        if(valor instanceof Number)
        {
            return super.buscar(valor);
        }
        return null;
    }

    @Override
    public Arreglo buscarOcurrencias(Object valor)
    {
        if(valor instanceof Number)
        {
            return super.buscarOcurrencias(valor);
        }
        return null;
    }

    @Override
    public boolean setElemento(int pos, Object valor)
    {
        if(valor instanceof Number)
        {
            return super.setElemento(pos, valor);
        }
        return false;
    }

    @Override
    public boolean setElemento(Object valorV, Object valorN, int numOcurrencias)
    {
        if(valorV instanceof Number && valorN instanceof Number)
        {
            return super.setElemento(valorV, valorN, numOcurrencias);
        }
        return false;
    }

    @Override
    public boolean agregarArreglo(Arreglo arreglo2)
    {
        boolean valoresValidos = false;

        for(int contador = 0; contador <= tope; contador++)
        {
            if(arreglo2.getElemento(contador) instanceof Number)
            {
                valoresValidos = true;
            }
            else
            {
                valoresValidos = false;
            }
        }
        if(valoresValidos)
        {
            return super.agregarArreglo(arreglo2);
        }
        return false;
    }

    @Override
    public int contar(Object valor)
    {
        if(valor instanceof Number)
        {
            return super.contar(valor);
        }
        return 0;
    }

    @Override
    public void rellenar(Object valor, int cuantos)
    {
        if(valor instanceof Number)
        {
            super.rellenar(valor, cuantos);
        }
    }

    /**
     * Modifica cada uno de los valores del vector
     * multiplicandólos por un valor dado.
     * @param escalar Valor por el cual se multiplicarán los elementos del vector.
     */
    public void multEscalar(Number escalar)
    {
        for(int contador = 0; contador <= tope; contador++)
        {
            Number multiplicacion = Double.parseDouble(super.getElemento(contador).toString()) *
            Double.parseDouble(escalar.toString());
            super.setElemento(contador, multiplicacion);
        }
    }

    /**
     * Modifica cada uno de los valores del vector
     * dividiéndolos por un valor dado.
     * @param escalar Valor por el cual se dividirán los elementos del vector.
     */
    public void divEscalar(Number escalar)
    {
        for(int contador = 0; contador <= tope; contador++)
        {
            Number division = Double.parseDouble(super.getElemento(contador).toString()) /
            Double.parseDouble(escalar.toString());
            super.setElemento(contador, division);
        }
    }

    /**
     * Modifica cada uno de los valores del vector
     * sumándolos por un valor dado.
     * @param escalar Valor por el cual se sumarán los elementos del vector.
     */
    public void sumaEscalar(Number escalar)
    {
        for(int contador = 0; contador <= tope; contador++)
        {
            Number suma = Double.parseDouble(super.getElemento(contador).toString()) +
            Double.parseDouble(escalar.toString());
            super.setElemento(contador, suma);
        }
    }

    /**
     * Modifica los valores del vector original sumando la posición 1
     * del mismo con la posición 1 del segundo vector, y así sucesivamente.
     * @param vector2 Elementos a ser tomados para realizar las sumas.
     */
    public void sumar(VectorNum vector2)
    {
        Number suma;

        if((super.getLongitud() - 1) == (vector2.getLongitud() - 1))
        {
            for (int contador = 0; contador <= tope; contador++)
            {
                suma =  Double.parseDouble(datos[contador].toString()) +
                Double.parseDouble(vector2.getElemento(contador).toString());
                datos[contador] = suma;
            }
        }
    }

    /**
     * Modifica los valores del vector original restando la posición 1
     * del mismo con la posición 1 del segundo vector, y así sucesivamente.
     * @param vector2 Elementos a ser tomados para realizar las restas.
     */
    public void restar(VectorNum vector2)
    {
        Number resta;

        if((super.getLongitud() - 1) == (vector2.getLongitud() - 1))
        {
            for (int contador = 0; contador <= tope; contador++)
            {
                resta =  Double.parseDouble(datos[contador].toString()) -
                Double.parseDouble(vector2.getElemento(contador).toString());
                datos[contador] = resta;
            }
        }
    }

    /**
     * Realiza una copia del vector con los
     * mismos datos.
     * @return Regresa la copia del Vector.
     */
    @Override
    public VectorNum copiar()
    {
        VectorNum copia = new VectorNum(MAX);

        // Se iteran las posiciones del vector.
        for(int index = 0; index <= tope; index++)
        {
            copia.agregar(datos[index]);
        }
        return copia;
    }

    /**
     * Suma los datos contenidos en el vector.
     * @return Regresa la suma calculada.
     */
    public double sumarDatos()
    {
        double suma = 0.0;

        for(int index = 0; index <= tope; index++)
        {// Se iteran las posiciones del vector.
            suma += Double.parseDouble(datos[index].toString());
        }
        return suma;
    }

    /**
     * Modifica los valores del vector original multiplicando la posición 1
     * del mismo con la posición 1 del segundo vector, y así sucesivamente.
     * @param vector2 Elementos a ser tomados para realizar las multiplicaciones.
     */
    public void multiplicar(VectorNum vector2)
    {
        Double suma;

        if((super.getLongitud() - 1) == (vector2.getLongitud() - 1))
        {
            for (int contador = 0; contador <= tope; contador++)
            {
                suma = Double.parseDouble(datos[contador].toString()) *
                Double.parseDouble(vector2.getElemento(contador).toString());
                datos[contador] = suma;
            }
        }
    }

    /**
     * Obtiene el producto punto entre vector original y el vector dado,
     * dicho producto está definido por la suma de las multiplicaciones
     * de los elementos en los vectores.
     * @param vector2 Vector a ser operado con el vector original para
     *                obtener el resultado.
     * @return Regresa el resultado del procedimiento.
     */
    public double productoPunto(VectorNum vector2)
    {
        double producto = 0;

        if((super.getLongitud() - 1) == (vector2.getLongitud() - 1))
        {
            for(int contador = 0; contador <= tope; contador++)
            {
               producto += Double.parseDouble(datos[contador].toString()) *
               Double.parseDouble(vector2.getElemento(contador).toString());
            }
        }
        return producto;
    }

    /**
     * Obtiene la magnitud del vector, dicho proceso está
     * definido por la raiz cuadrada de la suma de los cuadrados
     * de los elementos del vector.
     * @return Regresa el resultado del procedimiento.
     */
    public double magnitud()
    {
        double resultado = 0;

        for(int contador = 0; contador <= tope; contador++)
        {
            resultado += Math.pow(Double.parseDouble(datos[contador].toString()), 2);
        }
        resultado = Math.sqrt(resultado);
        return resultado;
    }

    /**
     * Obtiene la normaEuclidiana entre el vector original
     * y el dado.
     * @param vector2 Vector a ser operado con el vector original para
     *                obtener el resultado.
     * @return Regresa el resultado del procedimiento.
     */
    public double normaEuclidiana(VectorNum vector2)
    {
       double resultado = 0;
       double potencia;

       if((super.getLongitud() - 1) == (vector2.getLongitud() - 1))
       {
           for(int contador = 0; contador <= tope; contador++)
           {
               potencia = Math.pow(Double.parseDouble(vector2.getElemento(contador).toString()) -
                          Double.parseDouble(datos[contador].toString()), 2);
               resultado += potencia;
           }
       }
       resultado = Math.sqrt(resultado);
       return resultado;
    }

    /**
     * Obtiene la posición del valor mas pequeño contenido en el vector.
     * @param conteoNatural <b>true</b> para que indique la posicion en
     *                      el conteo humano partiendo de 1 o <b>false</b>
     *                      para que indique el conteo partiendo de 0.
     * @return Regresa la posición del menor elemento.
     */
    public int getMenor(boolean conteoNatural)
    {
        int posicion = -1;

        if(!super.vacia())
        {
            posicion = 0;
            Number menor = Double.parseDouble(this.getElemento(0).toString());
            for(int contador = 1; contador <= tope; contador++)
            {
                Number temporal = (Number) datos[contador];
                if(Double.parseDouble(temporal.toString()) < Double.parseDouble(menor.toString()))
                {
                    menor = temporal;
                    posicion = contador;
                }
            }
            if(conteoNatural)
            {
                return posicion + 1;
            }
            else
            {
                return posicion;
            }
        }
        return posicion;
    }

    @Override
    public MatrizNum reDim(int numReng, int numCol)
    {
        MatrizNum matriz = new MatrizNum(numReng, numCol);
        // El Arreglo puede cubrir las dimensiones.
        if((numReng * numCol) <= (tope + 1))
        {
            return reDim(0, 0, 0, matriz);
        }
        // El Arreglo no puede cubrir las dimensiones.
        else
        {
            return matriz;
        }
    }

    private MatrizNum reDim(int index, int reng, int col, MatrizNum matriz)
    {
        // Caso base.
        if(index == (tope + 1))
        {
            return matriz;
        }
        // Caso recursivo.
        else
        {
            // Aún hay columnas válidas.
            if(col < matriz.numColumnas())
            {
                matriz.setElemento(reng, col, datos[index]);
                return reDim(index + 1, reng, col + 1, matriz);
            }
            // Se llegó al final de las columnas.
            else
            {
                matriz.setElemento(reng, col, datos[index]);
                return reDim(index, reng + 1, 0, matriz);
            }
        }
    }

    /**
     * Busca el número más grande en el Vector.
     * @return Regresa el número de mayor
     * denominación en caso de haber,
     * o -1 si el vector está vacío.
     */
    public int posMayor()
    {
        if(!vacia())
        {// El vector tiene elementos.
            int posicion = 0;
            // El número mayor inicial es el primer elemento.
            double mayor = Double.parseDouble(
            datos[0].toString());

            // Se iteran los elementos restantes.
            for(int index = 1; index <= tope; index++)
            {
                if(Double.parseDouble(
                datos[index].toString()) > mayor)
                {// El elemento es más grande al
                 // mayor actual.
                    posicion = index;
                }
            }
            return posicion;
        }
        else
        {
            return -1;
        }
    }
}
