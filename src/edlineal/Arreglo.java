/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edlineal;

import entradasalida.SalidaEstandar;
import utilidades.Separador;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase implementa el TDA Arreglo.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Arreglo implements Lista
{
    /** Guarda el contenido de los elementos del Arreglo. */
    protected Object datos[];
    /** Guarda el tamaño del Arreglo. */
    protected int MAX;
    /** Guarda el límite de elementos insertados. */
    protected int tope;

    /**
     * Define el constructor de TDA Arreglo.
     * @param tam Es el tamaño para crear el Arreglo.
     */
    public Arreglo(int tam)
    {
        MAX = tam;
        datos = new Object[MAX];
        tope = -1;
    }

    @Override
    public boolean llena()
    {
        if(tope == (MAX - 1))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean vacia()
    {
        if(tope == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean agregar(Object valor)
    {
        if(!llena())
        {
            tope++;
            datos[tope] = valor;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void imprimirR()
    {
        for(int contador = tope; contador >= 0; contador--)
        {
            SalidaEstandar.consola(datos[contador]+"\n");
        }
    }

    /**
     * Muestra los elementos del Arreglo en la salida estándar
     * de manera inversa en forma recurisva.
     */
    public void imprimirRRecursivo()
    {
        // Sí hay elementos en el Arreglo.
        if(!vacia())
        {
            imprimirRRecursivo(tope);
        }
    }

    /**
     * Función real que muestra los elementos del Arreglo
     * en la salida estándar de forma recurisva.
     * @param contador Posición de los datos que se está imprimiendo.
     */
    private void imprimirRRecursivo(int contador)
    {
        // Caso recursivo.
        if(contador >= 0)
        {
            SalidaEstandar.consola(datos[contador]+"\n");
            imprimirRRecursivo(contador - 1);
        }
        //Caso base escondido.
    }

    @Override
    public Object buscar(Object valor)
    {
        int pos = 0;
        while(pos <= tope &&
                !valor.toString().equalsIgnoreCase(datos[pos].toString()))
        {
            pos++;
        }
        if(pos <= tope)
        {
            return pos;
        }
        else
        {
            return null;
        }
    }

    /**
     * Localiza la posición de valor de forma recursiva.
     * @param valor Es el elemento a encontrar.
     * @return Regresa la posicion si lo encuentra, null en caso contrario.
     */
    public Object buscarR(Object valor)
    {
        // Sí hay elementos en el Arreglo.
        if(!vacia() && valor != null)
        {
            return buscarR(valor, 0);
        }
        // Está vacío.
        else
        {
            return null;
        }
    }

    /**
     * Función real que localiza la posición de valor de forma recursiva.
     * @param valor Es el elemento a encontrar.
     * @param pos Posición de los datos que se está comparando.
     * @return Regresa la posicion si lo encuentra, null en caso contrario.
     */
    private Object buscarR(Object valor, int pos)
    {
        // Caso base.
        if(pos > tope)
        {
            return null;
        }
        // Caso recursivo.
        else
        {
            // Caso base2.
            if(valor.toString().equalsIgnoreCase(datos[pos].toString()))
            {
                return pos;
            }
            // Caso recursivo.
            else
            {
                return buscarR(valor, pos + 1);
            }
        }
    }

    @Override
    public Object eliminar(Object valor)
    {
        Integer pos = (Integer)buscar(valor);

        if(pos != null)
        {
            Object contenido = datos[pos];
            tope -= 1;
            for(int mov = pos; mov <= tope; mov++)
            {
                datos[mov] = datos[mov + 1];
            }
            return contenido;
        }
        else
        {
            return null;
        }
    }

    /**
     * Suprime el elemento de forma recursiva.
     * @param valor Es el elemento a borrar.
     * @return Regresa el objeto de la posicion encontrada o null si no
     * se encuentra.
     */
    public Object eliminarR(Object valor)
    {
        Integer pos = (Integer)buscarR(valor);

        // Sí se encontró el elemento a eliminar.
        if(pos != null)
        {
            Object contenido = datos[pos];
            tope -= 1;
            reacomodarAtrasR(pos);
            return contenido;
        }
        // No se encontró.
        else
        {
            return null;
        }
    }

    /**
     * Recorre los elementos del Arreglo cuando se elimina
     * un elemento.
     * @param pos Posición del Arreglo que se está recorriendo.
     */
    private void reacomodarAtrasR(int pos)
    {
        // Caso recursivo.
        if(pos <= tope)
        {
            datos[pos] = datos[pos + 1];
            reacomodarAtrasR(pos + 1);
        }
        // Caso base oculto.
    }

    /**
     * Valida que la posición dada esté dentro de los
     * límites de datos.
     * @param valor Posición en el arreglo a ser comprobada.
     * @return Regresa <b>true</b> si el valor es un índice
     * de datos, <b>false</b> en caso contrario.
     */
    protected boolean posicionValida(int valor)
    {
        if (valor >= 0 && valor < MAX)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Extrae de datos el valor contenido en la posición dada.
     * @param pos Posición del elemento.
     * @return Regresa el Object contenido en la posición indicada.
     */
    public Object getElemento(int pos)
    {
        if(posicionValida(pos))
        {
            return datos[pos];
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean setElemento(Object valorV, Object valorN, int numOcurrencias)
    {
        boolean ocurrencia = false;
        int contador = 0;

        while (contador <= tope && numOcurrencias > 0)
        {
            if(datos[contador].toString().equalsIgnoreCase(valorV.toString()))
            {
                datos[contador] = valorN;
                numOcurrencias--;
                ocurrencia = true;
            }
            contador++;
        }
        return ocurrencia;
    }

    /**
     * Recorre el Arreglo buscando elementos equivalentes a valorV de forma recursiva,
     * cuando encuentra uno equivalente lo cambia por valorN. Este
     * proceso se repite la cantidad de veces indicada.
     * @param valorV Es el elemento a ser reemplazado.
     * @param valorN Es el elemento a asignar en el lugar de valorV.
     * @param numOcurrencias Cantidad de veces que se buscara hacer el remplazo.
     * @return Regresa <b>true</b> si realizó por lo menos una modificación,
     * <b>false</b> en caso contrario.
     */
    public boolean setElementoR(Object valorV, Object valorN, int numOcurrencias)
    {
        // Los parámetros son válidos.
        if(valorV != null && valorN != null && numOcurrencias > 0)
        {
            int cantCambios = setElementoR(valorV, valorN, 0,numOcurrencias, 0);

            // Se realizó por lo menos un cambio.
            if(cantCambios > 0  )
            {
                return true;
            }
            // No se realizó ningún cambio.
            else
            {
                return false;
            }
        }
        // Uno de los parámetros es inválido.
        else
        {
            return false;
        }
    }

    /**
     * Modifica en el Arreglo el elemento que sea equivalente a ValorV y
     * lo cambia por ValorN.
     * @param valorV Es el elemento a ser reemplazado.
     * @param valorN Es el elemento a asignar en el lugar de valorV.
     * @param pos Posición del Arreglo que se está comparando con ValorV.
     * @param cuantos Cantidad de elementos que serán reemplazados.
     * @param conteo Cantidad de veces que se buscara hacer el remplazo.
     * @return Regresa el número de modificaciones que fueron realizadas.
     */
    private int setElementoR(Object valorV, Object valorN, int pos, int cuantos, int conteo)
    {
        // Caso base.
        if(pos > tope || conteo == cuantos)
        {
            return conteo;
        }
        // Casos recursivos.
        else
        {
            // Es igual al que se va a cambiar.
            if(datos[pos].toString().equalsIgnoreCase(valorV.toString()))
            {
                datos[pos] = valorN;
                return setElementoR(valorV, valorN, pos + 1, cuantos,conteo + 1);
            }
            // No es igual al que se va a cambiar.
            else
            {
                return setElementoR(valorV, valorN, pos + 1, cuantos, conteo);
            }
        }
    }

    /**
     * Asigna al arreglo el valor en la posición indicada.
     * @param pos Posición a realizar la asignación.
     * @param valor Elemento a ser asignado.
     * @return Regresa <b>true</b> en caso de haber realizado la
     * asignación, <b>false</b> en caso contrario.
     */
    public boolean setElemento(int pos, Object valor)
    {
        if(posicionValida(pos))
        {
            datos[pos] = valor;

            if(pos >= getLongitud())
            {
                tope++;
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Arreglo buscarOcurrencias(Object valor)
    {
        int longitud = contar(valor);
        Arreglo nuevoArreglo = new Arreglo(longitud);

        for (int contador = 0; contador <= tope; contador++)
        {
            if(datos[contador].toString().equalsIgnoreCase(valor.toString()))
            {
                nuevoArreglo.agregar(contador);
            }
        }
        return nuevoArreglo;
    }

    /**
     * Busca en la lista los elementos equivalentes a valor de forma recurisva
     * y guarda las posiciones de estos en un objeto Arreglo.
     * @param valor Elemento a comparar para buscar ocurrencias.
     * @return Objeto de tipo Arreglo con las posiciones de
     * las ocurrencias encontradas.
     */
    public Arreglo buscarOcurrenciasR(Object valor)
    {
        // El valor es válido.
        if(valor != null)
        {
            Arreglo ocurrencias = new Arreglo(contarR(valor));
            agregarOcurrenciasR(valor, 0, ocurrencias);
            return ocurrencias;
        }
        // El valor es inválido.
        else
        {
            return null;
        }
    }

    /**
     * Funcion real que busca en la lista los elementos equivalentes a
     * valor de forma recurisva y guarda las posiciones de estos en un objeto Arreglo.
     * @param valor Elemento a comparar para buscar ocurrencias.
     * @param pos Posición en curso de los datos.
     * @param ocurrencias Arreglo que va almacenando las posiciones de los
     *                    elementos iguales.
     */
    private void agregarOcurrenciasR(Object valor, int pos, Arreglo ocurrencias)
    {
        // Caso recursivo.
        if(pos <= tope)
        {
            // El elemento es igual al valor.
            if(datos[pos].toString().equalsIgnoreCase(valor.toString()))
            {
                ocurrencias.agregar(pos);
            }
            agregarOcurrenciasR(valor, pos + 1, ocurrencias);
        }
        // Caso base oculto.

    }

    /**
     * Remueve el elemento del arreglo contenido en la
     * posición dada.
     * @param pos Indice del elemento en el arreglo a eliminar.
     * @return Regresa el objeto de la posición encontrada o
     * null si no lo encuentra.
     */
    public Object eliminar(int pos)
    {
        if(posicionValida(pos))
        {
            return eliminar(datos[pos]);
        }
        return null;
    }

    /**
     * Remueve el elemento del arreglo contenido en la
     * posición dada de forma recursiva.
     * @param pos Indice del elemento en el arreglo a eliminar.
     * @return Regresa el objeto de la posición encontrada o
     * null si no lo encuentra.
     */
    public Object eliminarR(int pos)
    {
        // La posición del arreglo es válida.
        if(posicionValida(pos))
        {
            Object contenido = datos[pos];
            tope -= 1;
            reacomodarAtrasR(pos);
            return contenido;
        }
        // La posción es inválida.
        else
        {
            return null;
        }
    }

    @Override
    public void vaciar()
    {
        for(int contador = 0; contador <= tope; contador++)
        {
            datos[contador] = null;
        }
        tope = -1;
    }

    /**
     * Elimina todos los elementos del Arreglo de forma
     * recursiva.
     */
    public void vaciarR()
    {
        vaciarR(0);
        tope = -1;
    }

    /**
     * Función real que elimina todos los elementos del
     * Arreglo de forma recursiva.
     * @param pos Posición de los datos que se está vaciando.
     */
    private void vaciarR(int pos)
    {
        // Caso recursivo.
        if(pos <= tope)
        {
            datos[pos] = null;
            vaciarR(pos + 1);
        }
        //Caso base oculto.
    }

    @Override
    public boolean agregarArreglo(Arreglo arreglo2)
    {
        boolean elementoAgregado = false;
        int indexNuevo = 0;

        while (!this.llena() && arreglo2.getElemento(indexNuevo) != null)
        {
            this.agregar(arreglo2.getElemento(indexNuevo));
            elementoAgregado = true;
            indexNuevo++;
        }
        return elementoAgregado;
    }

    /**
     * Agrega al final del arreglo original de forma recursiva
     * la totalidad o la mayor cantidad de elementos que se puedan del
     * segundo arreglo, en función de los espacios vacíos
     * en el arreglo original.
     * @param arreglo2 Elementos a agregarse al arreglo original.
     * @return Regresa <b>true</b> si por lo menos se agrego un
     * elemento al arreglo original, <b>false</b> en caso contrario.
     */
    public boolean agregarArregloR(Arreglo arreglo2)
    {
        // El arreglo es válido.
        if(arreglo2 != null)
        {
            int agregados = agregarArregloR(arreglo2, 0);

            // Se agregó por lo menos un elemento.
            if(agregados > 0)
            {
                return true;
            }
            // No se agregó ningún elemento.
            else
            {
                return false;
            }
        }
        // El arreglo es inválido.
        else
        {
            return false;
        }
    }

    /**
     * Función real que agrega al final del arreglo original de forma recursiva
     * la totalidad o la mayor cantidad de elementos que se puedan del
     * segundo arreglo, en función de los espacios vacíos
     * en el arreglo original.
     * @param arreglo2 Elementos a agregarse al arreglo original.
     * @param pos Posición del arreglo2 que se está intentando agregar.
     * @return Regresa la cantidad de elementos que fueron agregados
     * al arreglo original.
     */
    private int agregarArregloR(Arreglo arreglo2, int pos)
    {
        // Caso base.
        if(llena() || arreglo2.getElemento(pos) == null)
        {
            return 0;
        }
        // Caso recursivo.
        else
        {
            agregar(arreglo2.getElemento(pos));
            return 1 + agregarArregloR(arreglo2, pos + 1);
        }
    }

    @Override
    public void invertir()
    {
        Object[] invertido = new Object[MAX];
        int contadorOrg = 0;

        for(int contadorInv = tope; contadorInv >= 0; contadorInv--)
        {
            invertido[contadorInv] = datos[contadorOrg];
            contadorOrg++;
        }
        datos = invertido;
    }

    /**
     * Invierte de manera recursiva la posición de los
     * elementos contenidos en la lista.
     */
    public void invertirR()
    {
        Object[] invertido = new Object[MAX];
        datos = invertirR(0, tope, invertido);
    }

    /**
     * Función real que invierte de manera recursiva la
     * posición de los elementos contenidos en la lista.
     * @param posInvertida Posición del arreglo invertido que se está
     *                     manipulando.
     * @param posOriginal Posición del arreglo origianl que se está
     *                    manipulando.
     * @param invertido Arreglo con los datos del arreglo original invertidos.
     */
    private Object[] invertirR(int posOriginal, int posInvertida, Object[] invertido)
    {
        // Caso base.
        if(posInvertida < 0)
        {
            return invertido;
        }
        // Caso recursivo.
        else
        {
            invertido[posInvertida] = datos[posOriginal];
            return invertirR(posOriginal + 1, posInvertida - 1, invertido);
        }
    }

    @Override
    public int contar(Object valor)
    {
        int ocurrencias = 0;

        for(int contador = 0; contador <= tope; contador++)
        {
            if(datos[contador].toString().equalsIgnoreCase(valor.toString()))
            {
                ocurrencias++;
            }
        }
        return ocurrencias;
    }

    /**
     * Enumera la cantidad de elementos en la lista que
     * son equivalentes a un valor dado de forma recursiva.
     * @param valor Objeto a ser comparado con los elementos
     * del Arreglo.
     * @return Regresa la cantidad de coincidencias con
     *  el objeto encontradas.
     */
    public int contarR(Object valor)
    {
        // El valor es válido.
        if(valor != null)
        {
            return contarR(valor, 0);
        }
        // El valor es inválido.
        else
        {
            return 0;
        }
    }

    /**
     * Función real que enumera la cantidad de elementos en la lista que
     * son equivalentes a un valor dado de forma recursiva.
     * @param valor Objeto a ser comparado con los elementos
     * del Arreglo.
     * @param pos Posición del arreglo que se está comparando.
     * @return Regresa la cantidad de coincidencias con
     *  el objeto encontradas.
     */
    private int contarR(Object valor, int pos)
    {
        // Caso base.
        if(pos > tope)
        {
            return 0;
        }
        // Caso recursivo.
        else
        {
            // Se encontró una coincidencia.
            if(datos[pos].toString().equalsIgnoreCase(valor.toString()))
            {
                return 1 + contarR(valor, pos + 1);
            }
            // El valor no coincide con el elemento actual.
            else
            {
                return 0 + contarR(valor, pos + 1);
            }
        }
    }

    @Override
    public Object eliminarTope()
    {
        if(!vacia())
        {
            Object elemento = datos[tope];
            datos[tope] = null;
            tope--;
            return elemento;
        }
        return null;
    }

    @Override
    public void imprimir()
    {
        for(int contador = 0; contador <= tope; contador++)
        {
            SalidaEstandar.consola(datos[contador]+"\n");
        }
    }


    /**
     * Realiza la impresión de los elementos de la lista
     * partiendo de la primera posición a la última de
     * forma recursiva.
     */
    public void imprimirRecursivo()
    {
        imprimirRecursivo(0);
    }

    /**
     * Función real que realiza la impresión de los
     * elementos de la lista partiendo de la primera
     * posición a la última.
     * @param pos Posición de los datos que se está imprimiendo.
     */
    private void imprimirRecursivo(int pos)
    {
        // Caso recursivo.
        if(pos <= tope)
        {
            SalidaEstandar.consola(datos[pos] + "\n");
            imprimirRecursivo(pos + 1);
        }
        //Caso base oculto.
    }

    @Override
    public void rellenar(Object valor, int cuantos)
    {
        for(int contador = 0; contador < cuantos; contador++)
        {
            this.agregar(valor);
        }
    }

    /**
     * Agrega al arreglo el valor dado la cantidad
     * de veces que se indique de forma recursiva.
     * @param valor Elemento a insertarse.
     * @param cuantos Cantidad de veces que será insertado el elemento.
     */
    public void rellenarR(Object valor, int cuantos)
    {
        // Los parámetros son válidos.
        if(valor != null && cuantos > 0)
        {
            rellenarRecursivo(valor, cuantos);
        }
    }

    /**
     * Función real que agrega al arreglo el valor dado la cantidad
     * de veces que se indique de forma recursiva.
     * @param valor Elemento a insertarse.
     * @param cuantos Cantidad de veces que será insertado el elemento.
     */
    private void rellenarRecursivo(Object valor, int cuantos)
    {
        // Caso recursivo.
        if(cuantos > 0)
        {
            agregar(valor);
            rellenarRecursivo(valor, cuantos - 1);
        }
        // Caso base escondido.
    }

    /**
     * Obtiene el número máximo de elementos a almacenar.
     * @return Regresa la cantidad de elementos máximos en el TDA.
     */
    public int capacidad()
    {
        return MAX;
    }

    /**
     * Obtiene el número de elementos del arreglo.
     * @return Regresa la cantidad de elementos almacenados.
     */
    public int getLongitud()
    {
        return tope + 1;
    }

    /**
     * Agrega al arreglo el objeto dado en la posición indicada y
     * recorre los elementos a partir de la posición a una adelante.
     * @param pos Posición del arreglo a agregar el objeto.
     * @param valor Objeto a ser agregado al arreglo.
     * @return Regresa <b>true</b> si pudo agregarse el elemento,
     * o <b>false</b> en caso contrario.
     */
    public boolean insertar(int pos, Object valor)
    {
        if(posicionValida(pos) && !llena())
        {
            tope++;
            for(int index = tope; index >= pos; index--)
            {
                datos[index] = datos[index - 1];
            }
            datos[pos] = valor;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Agrega al arreglo el objeto dado en la posición indicada y
     * recorre los elementos a partir de la posición a una adelante
     * de forma recursiva.
     * @param pos Posición del arreglo a agregar el objeto.
     * @param valor Objeto a ser agregado al arreglo.
     * @return Regresa <b>true</b> si pudo agregarse el elemento,
     * o <b>false</b> en caso contrario.
     */
    public boolean insertarR(int pos, Object valor)
    {
        if(posicionValida(pos) && !llena())
        {
            tope++;
            reacomodarAdelanteR(tope, pos);
            datos[pos] = valor;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Recorre los elementos del Arreglo cuando se elimina
     * un elemento.
     * @param pos Posición del Arreglo que se está recorriendo.
     * @param fin Posición de donde se comenzarán a recorrer los elementos.
     */
    private void reacomodarAdelanteR(int pos, int fin)
    {
        // Caso recursivo.
        if(pos >= fin)
        {
            datos[pos] = datos[pos - 1];
            reacomodarAdelanteR(pos - 1, fin);
        }
        // Caso base oculto.
    }

    /**
     * Sustituye los elementos de Arreglo actual con los
     * del nuevo Arreglo.
     * @param arreglo2 Elementos a sustituir a los actuales de Arreglo.
     * @return Regresa <b>true</b> en caso de haber podido sustituir
     * los elementos, o <b>false</b> en caso contrario.
     */
    public boolean copiarArreglo(Arreglo arreglo2)
    {
        if(arreglo2.capacidad() == this.capacidad())
        {
            this.vaciar();
            tope = -1;

            for(int contador = 0; contador < MAX; contador++)
            {
                if(arreglo2.getElemento(contador) != null)
                {
                    datos[contador] = arreglo2.getElemento(contador);
                    tope++;
                }
                else
                {
                    break;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Sustituye los elementos de Arreglo actual con los
     * del nuevo Arreglo de forma recursiva.
     * @param arreglo2 Elementos a sustituir a los actuales de Arreglo.
     * @return Regresa <b>true</b> en caso de haber podido sustituir
     * los elementos, o <b>false</b> en caso contrario.
     */
    public boolean copiarArregloR(Arreglo arreglo2)
    {
        // El Arreglo puede ser copiado.
        if(arreglo2 != null && capacidad() == arreglo2.capacidad())
        {
            tope = -1;
            vaciarR();
            copiarArregloR(arreglo2, 0);
            return true;
        }
        // El Arreglo contiene valores inválidos.
        else
        {
            return false;
        }
    }

    /**
     * Función real que sustituye los elementos de Arreglo actual con los
     * del nuevo Arreglo de forma recursiva.
     * @param arreglo2 Elementos a sustituir a los actuales de Arreglo.
     * @param pos Posición del arreglo original en que se está agregando
     *            un elemento del arreglo2.
     */
    private void copiarArregloR(Arreglo arreglo2, int pos)
    {
        // Caso recursivo.
        if(pos < MAX && pos < arreglo2.getLongitud())
        {
            datos[pos] = arreglo2.getElemento(pos);
            tope++;
            copiarArregloR(arreglo2, pos + 1);
        }
        // Caso base oculto.
    }

    /**
     * Sobreescribe el atributo <b>datos</b> de la
     * clase con el arreglo pasado como parámetro.
     * @param arreglo2 Arreglo a ser almacenado.
     */
    public void setArreglo(Object[] arreglo2)
    {
        this.MAX = arreglo2.length;
        datos = new Object[MAX];
        this.tope = MAX - 1;

        for(int contador = 0; contador <= tope; contador++)
        {
            datos[contador] = arreglo2[contador];
        }
    }

    /**
     * Sobreescribe de forma recursiva el atributo
     * <b>datos</b> de la clase con el arreglo pasado
     * como parámetro.
     * @param arreglo2 Arreglo a ser almacenado.
     */
    public void setArregloR(Object[] arreglo2)
    {
        // El arreglo es válido.
        if(arreglo2 != null)
        {
            MAX = arreglo2.length;
            datos = new Object[MAX];
            tope = MAX - 1;
            setArregloR(arreglo2, 0);
        }
    }

    /**
     * Función real que sobreescribe de forma recursiva
     * el atributo <b>datos</b> de la clase con el arreglo
     * pasado como parámetro.
     * @param arreglo2 Arreglo a ser almacenado.
     * @param pos Posición actual del nuevo arreglo que se
     *            está asignando.
     */
    private void setArregloR(Object[] arreglo2, int pos)
    {
        // Caso recursivo.
        if(pos <= tope)
        {
            datos[pos] = arreglo2[pos];
            setArregloR(arreglo2, pos + 1);
        }
        // Caso base oculto.
    }

    /**
     * Obtiene una copia de los objetos contenidos
     * en la variable <b>datos</b> de la clase.
     * @return Regresa una copia de <b>datos</b>.
     */
    public Object[] leerArreglo()
    {
        Object[] temporal = new Object[MAX];

        for(int contador = 0; contador <= tope; contador++)
        {
            temporal[contador] = datos[contador];
        }
        return temporal;
    }

    /**
     * Obtiene una copia de los objetos contenidos
     * en la variable <b>datos</b> de la clase
     * en forma recursiva.
     * @return Regresa una copia de <b>datos</b>.
     */
    public Object[] leerArregloR()
    {
        Object[] temporal = new Object[MAX];
        leerArregloR(temporal, 0);
        return temporal;
    }

    /**
     * Función real que crea una copia de los objetos
     * contenidos en la variable <b>datos</b> de la clase
     * en forma recursiva.
     * @param copia Arreglo al que se copiarán los elementos
     *              de la variable datos.
     * @param pos Posición actual que se está copiando.
     */
    private void leerArregloR(Object[] copia, int pos)
    {
        // Caso recursivo.
        if(pos <= tope)
        {
            copia[pos] = datos[pos];
            leerArregloR(copia, pos + 1);
        }
        // Caso base oculto.
    }

    @Override
    public Object getTope()
    {
        if(!vacia())
        {
            return datos[tope];
        }
        return null;
    }

    /**
     * Divide los datos guardados en varios arreglos donde cada
     * uno contiene una columna de elementos.
     * @param separador Criterio de separación de los elementos del
     *                  Arrelo.
     * @param cantColumnas Número de columnas a ser separadas.
     * @return Regresa un Arreglo que en su interior contiene más
     * arreglos con los datos separados.
     */
    public Arreglo separarPorColumna(char separador, int cantColumnas)
    {
        Arreglo arraySeparaciones = new Arreglo(cantColumnas);

        // El Arreglo tiene elementos y las columnas son válidas.
        if(!vacia() && cantColumnas > 0)
        {
            // Se agrega la cantidad de arreglos que contendrán la separación.
            for(int contador = 0; contador < cantColumnas; contador++)
            {
                arraySeparaciones.agregar(new Arreglo(getLongitud()));
            }

            // Se recorren los datos del Arreglo.
            for(int posicion = 0; posicion < getLongitud(); posicion++)
            {
                String cadena = (String)datos[posicion].toString();
                // Se obtienen las separaciones de cada elemento del Arreglo.
                ListaDobleLigada separacionesCadena = Separador.separar(cadena, separador);

                // Las cantidad de separaciones es igual a las columnas indicadas.
                if(separacionesCadena.getSize() == cantColumnas)
                {
                    // Se agrega cada separación a cada Arreglo.
                    for(int index = separacionesCadena.getSize() - 1; index >= 0; index--)
                    {
                        Arreglo arrayTemp = (Arreglo)arraySeparaciones.getElemento(index);
                        arrayTemp.agregar(separacionesCadena.eliminarTope());
                    }
                }
            }
            return arraySeparaciones;
        }
        // No hay elementos o la columna es inválida.
        else
        {
            return arraySeparaciones;
        }
    }

    /**
     * Recupera los datos contenidos en el Arreglo para transformarlos
     * a una ListaDobleLigada.
     * @return Regresa la ListaDobleLigada con los elementos del Arreglo.
     */
    public ListaDobleLigada aListaDobleLigada()
    {
        ListaDobleLigada listaDatos = new ListaDobleLigada();

        // Se recorren los datos para agregarlos a la Lista.
        for(int pos = 0; pos < getLongitud(); pos++)
        {
            listaDatos.agregar(datos[pos]);
        }
        return listaDatos;
    }

    /**
     * Recupera los datos contenidos en el Arreglo para transformarlos
     * a una listaLigada de forma recursiva.
     * @return Regresa la ListaLigada con los elementos del Arreglo.
     */
    public ListaLigada arregloAListaRR()
    {
        ListaLigada listaDatos = new ListaLigada();
        arregloAListaRR(listaDatos, 0);
        return listaDatos;
    }

    /**
     * Función real que recupera los datos contenidos en el Arreglo para transformarlos
     * a una listaLigada de forma recursiva.
     * @param lista Lista a la que se le agregan los datos del Arreglo.
     * @param pos Posición del Arreglo de donde se está copiando el
     *            elemento.
     */
    private void arregloAListaRR(ListaLigada lista, int pos)
    {
        // Caso recursivo.
        if(pos <= tope)
        {
            lista.agregar(datos[pos]);
            arregloAListaRR(lista, pos + 1);
        }
        // Caso base oculto.
    }

    /**
     * Convierte el arreglo a una Matriz con las dimensiones
     * específicadas.
     * @param numReng Número de renglones que tendrá la Matriz.
     * @param numCol Número de columnas que tendrá la Matriz.
     * @return Regresa una Matriz con los elementos del arreglo
     * con las dimensiones específicadas si dichas dimensiones
     * pueden ser cubiertas por los elementos del arreglo, en
     * caso contrario regresa una Matriz vacía.
     */
    public Matriz reDim(int numReng, int numCol)
    {
        Matriz matriz = new Matriz(numReng, numCol);
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

    /**
     * Función real que convierte el arreglo a una Matriz con
     * las dimensiones específicadas de forma recursiva.
     * @param index Índice del arreglo de donde se está copiando
     *              el elemento.
     * @param reng Renglón al cual se le está agregando el elemento
     *             a la Matriz.
     * @param col Columna a la cual se le está agregando el elemento
     *            a la Matriz.
     * @param matriz Matriz que está siendo llenada con los datos del
     *               arreglo.
     * @return Regresa una copia de los elementos del arreglo contenidos
     * en la Matriz.
     */
    private Matriz reDim(int index, int reng, int col, Matriz matriz)
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
     * Crea una copia de los datos y los
     * asigna a una implementación de la
     * interfaz List de Java.
     * @return Regresa una List con los
     * datos copiados.
     */
    public List aList()
    {
        List resultado = new ArrayList();

        // Se iteran los elementos del arreglo.
        for(int index = 0; index <= tope; index++)
        {
            // Se agrega cada elemento a la nueva
            // lista.
            resultado.add(datos[index]);
        }
        return resultado;
    }

    /**
     * Realiza una copia del arreglo con los
     * mismos datos.
     * @return Regresa la copia del arreglo.
     */
    public Arreglo copiar()
    {
        Arreglo copia = new Arreglo(MAX);

        // Se iteran las posiciones del vector.
        for(int index = 0; index <= tope; index++)
        {
            copia.agregar(datos[index]);
        }
        return copia;
    }

}