package catalogos;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import edlineal.Arreglo;
import edlineal.Matriz3D;
import entradasalida.SalidaEstandar;
import utilidades.ActividadPintor;
import utilidades.entidades.Pintor;

import javax.rmi.ssl.SslRMIClientSocketFactory;


/**
 * Esta clase implementa el TDA GaleriaArte.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class GaleriaArte
{
    /** Guarda el nombre que recibe la galería. */
    private String nombre;
    /** Guarda las actividades que realizaron los pintores durante cada semana de cada mes. */
    private Matriz3D historialPintores;
    /** Arreglo paralelo para indicar los pintores **/
    private Arreglo pintores;
    /** Arreglo paralelo para indicar los meses **/
    private Arreglo meses;
    /** Guarda los días que se están tomando de cada mes. */
    private int diasPMes;

    /**
     * Define el constructor de TDA GaleriaArte.
     * @param nombre Nombre de que tendrá la galería.
     * @param numMeses Meses de los que se llevará registro.
     * @param diasPMes Cantidad de los días de un mes que se llevará registro.
     * @param numPintores Número de pintores de los cuales se guardarán sus actividades.
     */
    public GaleriaArte(String nombre, int numMeses, int diasPMes, int numPintores)
    {
        this.nombre = nombre;
        this.diasPMes = diasPMes;
        historialPintores = new Matriz3D(numPintores, numMeses, diasPMes);
        pintores = new Arreglo(numPintores);
        meses = new Arreglo(numMeses);
    }

    /**
     * Agrega un arreglo de actividades a cada posición de la matriz
     * del historial de actividades.
     */
    private void inicializarActividades(int posPintor)
    {
        for(int numMes = 0; numMes < meses.capacidad(); numMes++)
        {
            for(int numDia = 0; numDia < diasPMes; numDia++)
            {
                historialPintores.setElemento(posPintor, numMes, numDia, new Arreglo(5));
            }
        }
    }

    /**
     * Añade un nuevo pintor para monitorear sus actividades.
     * @param pintor Pintor a ser agregado.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar el pintor con éxito, o <b>false</b> en caso
     * contrario.
     */
    public boolean agregarPintor(Pintor pintor)
    {
        boolean pintorAgregado = false;
        pintorAgregado = pintores.agregar(pintor);
        this.inicializarActividades((Integer) pintores.getLongitud() - 1);
        return pintorAgregado;
    }

    /**
     * Añade un nuevo mes que se tomará en cuenta para
     * registrar las actividades del pintor.
     * @param nombre Nombre del mes a ser agregado.
     * @return Regresa <b>true</b> en caso de haber podido
     * agregar el mes con éxito, o <b>false</b> en caso
     * contrario.
     */
    public boolean agregarMes(String nombre)
    {
        return meses.agregar(nombre);
    }

    /**
     * Guarda en el mes y día indicado, la actividad que realizó el pintor.
     * @param dia Día en que se hizo la actividad.
     * @param mes Mes en que se hizo la actividad.
     * @param pintor Pintor al que será asignada la actividad.
     * @param actividad Actividad realizada ese día y mes.
     * @return Regresa <b>true</b> en caso de haber podido guardar la
     * actividad exitosamente, o <b>false</b> en caso contrario.
     */
    public boolean agregarActividad(int dia, String mes, Pintor pintor, ActividadPintor actividad)
    {
        //Se extraen las posiciones en los arreglos paralelos del pintor y del mes.
        Integer posMes = (Integer) meses.buscar(mes);
        Integer posPintor = (Integer) pintores.buscar(pintor);
        boolean actividadAgregada = false;

        //Se comprueba que el pintor, el mes, y el día realmente existan.
        if(posMes != null && posPintor != null && dia > 0 && dia <= 20)
        {
            //Se extrae el arreglo de actividades que tiene el pintor.
            Arreglo arregloActs = (Arreglo) historialPintores.getElemento(
                    posPintor, posMes, (dia-1));
            //Se agrega la nueva actividad que realizó el pintor al arreglo de actividades.
            actividadAgregada = arregloActs.agregar(actividad);
            //Se comprueba que la actividad haya sido agregada con éxito al arreglo de actividades.
            if(actividadAgregada)
            {
                //Se agrega el arreglo de actividades que realizó el pintor ese día y mes.
                actividadAgregada = historialPintores.setElemento(posPintor, posMes, (dia-1), arregloActs);
                return actividadAgregada;
            }
        }
        return actividadAgregada;
    }

    /**
     * Asigna una misma actividad realizada al pintor indicado
     * durante todos los días de todos los meses.
     * @param pintor Pintor a asignarse la actividad.
     * @param actividad Actividad a ser asignada al pintor.
     * @return Regresa <b>true</b> en caso de haber podido asignar
     * todas las actividades, o <b>false</b> en caso contrario.
     */
    public boolean asignarActividad(Pintor pintor, ActividadPintor actividad)
    {
        //Se busca la posicion del pintor en el arreglo.
        Integer posPintor = (Integer) pintores.buscar(pintor);
        boolean actividadAsignada = false;

        //Se comprueba que el pintor haya sido encontrado en el arreglo.
        if(posPintor != null)
        {
            //Se iteran meses y dias del pintor para asignarle la actividad.
            for (int mes = 0; mes < meses.capacidad(); mes++)
            {
                for (int dia = 0; dia < diasPMes; dia++)
                {
                    //Extrae el arreglo de actividades por dia para agregarle la actividad indicada.
                    Arreglo acts = (Arreglo) historialPintores.getElemento(posPintor, mes, dia);
                    actividadAsignada = acts.agregar(actividad);

                    if(actividadAsignada)
                    {
                        //En caso de haber podido agregar la actividad ese día, actualiza
                        //la lista de actividades hechas por el pintor ese día.
                        historialPintores.setElemento(posPintor, mes, dia, acts);
                    }
                }
            }
        }
        return actividadAsignada;
    }

    /**
     * Obtiene el pintor que realizó todos los días del año la misma actividad.
     * @return Regresa el pintor que cumplió dicha condición, o regresa null en
     * caso de no haber encontrado ninguno.
     */
    public Pintor getPintorMismasActividades()
    {
        Pintor objectPintor = null;
        boolean mismasActividades;

        //Se realiza la iteración de las actividades que realizó cada día cada pintor.
        for(int pintor = 0; pintor < pintores.getLongitud(); pintor++)
        {
            //Se obtiene la actividad que realizó el pintor el primer día de todos para comparar que
            //sea igual con las de los demás días del año.
            Arreglo acts = (Arreglo) historialPintores.getElemento(pintor,0,0);
            ActividadPintor actividadRepetida = (ActividadPintor) acts.getElemento(0);
            mismasActividades = false;

            if(actividadRepetida != null)
            {
                //Se iteran todos los días del año para comprobar que se haya hecho la misma actividad.
                for (int mes = 0; mes < meses.capacidad(); mes++)
                {
                    for (int dia = 1; dia < diasPMes; dia++)
                    {
                        //Se extrae la actividad del día en curso para compar si es similar a la común.
                        acts = (Arreglo) historialPintores.getElemento(pintor, mes, dia);
                        int ocurrencias = acts.contar(actividadRepetida);

                        //Se comprueba que la misma actividad se haya realizado siempre en el año.
                        if (ocurrencias == acts.getLongitud())
                        {
                            mismasActividades = true;
                        }
                        else
                        {
                            break;
                        }
                    }
                    if(!mismasActividades)
                    {
                        break;
                    }
                }
            }
            //Si al terminar el año el pintor realizó la misma actividad, entonces
            //se retorna al pintor.
            if(mismasActividades)
            {
                return (Pintor) pintores.getElemento(pintor);
            }
        }
        return null;
    }

    /**
     * Realiza la impresión del número de la semana del mes,
     * y el mes en que el pintor dado como parámetro realizó
     * la actividad de viajar.
     * @param pintor Pintor a comprobar en que fecha viajó.
     */
    public void imprimirFechaViaje(Pintor pintor)
    {
        //Se obtiene la posición en la matriz que posee el pintor.
        Integer posPintor = (Integer) pintores.buscar(pintor);

        if(posPintor != null) {

            //Se iteran todos los días del año para ver la fecha
            //en que el pintor viajó
            for (int numMes = 0; numMes < meses.capacidad(); numMes++)
            {
                for (int numDia = 0; numDia < diasPMes; numDia++)
                {
                    //Se extrae el arreglo de actividades hechas en la fecha que indican los contadores.
                    Arreglo acts = (Arreglo) historialPintores.getElemento(posPintor, numMes, numDia);
                    //Se itera el arreglo de actividades del día.
                    for (int posActividad = 0; posActividad < acts.getLongitud(); posActividad++)
                    {
                        ActividadPintor actividad = (ActividadPintor) acts.getElemento(posActividad);
                        //Se comprueba si la actividad del pintor fue viajar.
                        if (acts.getElemento(posActividad) == ActividadPintor.VIAJAR)
                        {
                            //Se calcula el número de semana en que el pintor viajó.
                            int semana = (int) (numDia + 1) / 5;
                            int modulo = (numDia + 1) % 5;
                            if (modulo > 0)
                            {
                                semana++;
                            }
                            //En caso de sí haber viajado, se imprime la fecha en que realizó el viaje.
                            SalidaEstandar.consola("\nEl pintor " + pintor.getNombre() + " viajó " +
                                    "la semana: " + semana + " del mes: " + (numMes + 1));
                        }
                    }
                }
            }
        }
    }

    /**
     * Obtiene el nombre del mes en que se realizó menor cantidad de veces
     * la actividad de pintar.
     * @return Regresa el número mes en que menos de realizó la actividad de pintar.
     */
    public int getMesMenosObrasArte()
    {
        int numMesConMenosPinturas = 0;
        int menorCantidadPinturas = 0;

        //Se itera cada día de las actividades de cada pintor de cada mes.
        //Se toma al mes como el ciclo más superficial para hacer el conteo entre todos los pintores de ese mismo mes.
        for(int numMes = 0; numMes < meses.capacidad(); numMes++)
        {
            int cantObrasPintadas = -1;
            for(int numPintor = 0; numPintor < pintores.getLongitud(); numPintor++)
            {
                for(int numDia = 0; numDia < diasPMes; numDia++)
                {
                    //Se extraen las actividades realizadas cada día.
                    Arreglo acts = (Arreglo) historialPintores.getElemento(numPintor, numMes, numDia);
                    //Se cuentan las obras pintadas en ese día.
                    cantObrasPintadas += acts.contar(ActividadPintor.PINTAR);
                }
            }
            //Se evalua si el mes tuvo menos obras de arte pintadas que los demás en caso afirmativo se guarda el
            //número del mes.
            if(cantObrasPintadas < menorCantidadPinturas)
            {
                numMesConMenosPinturas = numMes;
                menorCantidadPinturas = cantObrasPintadas;
            }
        }
        return numMesConMenosPinturas + 1;
    }

    /**
     * Realiza la impresión del nombre y el sexo de los pintores
     * que hayan expuesto por lo menos una vez todos los meses del año.
     */
    public void imprimirPintoresExpusieronTodosMeses()
    {
        //Se iteran todos los pintores en sus actividades realizadas cada día,
        //para saber si expuso por lo menos una vez cada día.
        for(int numPintor = 0; numPintor < pintores.getLongitud(); numPintor++)
        {
            int vecesExpuestasPMes = 0;
            for(int numMes = 0; numMes < meses.capacidad(); numMes++)
            {
                int vecesExpuestasPDia = 0;
                for(int numDia = 0; numDia < diasPMes; numDia++)
                {
                    //Se extraen las actividades hechas por el pintor cada día.
                    Arreglo acts = (Arreglo) historialPintores.getElemento(numPintor, numMes, numDia);
                    //Se comprueba si el día en curso el pintor expuso y en caso afirmativo se contea.
                    if(acts.contar(ActividadPintor.EXPONER) > 0)
                    {
                        vecesExpuestasPDia++;
                        break;
                    }
                }
                //Se comprueba si el pintor expuso por lo menos una vez durante el mes.
                if(vecesExpuestasPDia > 0)
                {
                    vecesExpuestasPMes++;
                }
            }
            //Al terminar el mes se evalua si el pintor expuso por lo menos una vez al mes.
            if(vecesExpuestasPMes >= 12)
            {
                //En caso de sí haberlo hecho se imprimen sus datos.
                Pintor pintor = (Pintor)pintores.getElemento(numPintor);
                SalidaEstandar.consola("\nNombre: " + pintor.getNombre() + " Sexo: " +
                                        pintor.getSexo().getNombre());
            }
        }
    }

    /**
     * Realiza la impresión del mes y la semana en que todos
     * los pintores expusieron.
     */
    public void imprimirFechaTodosExpusieron()
    {
        //Se iteran los datos partiendo del mes, la semana, el día y al final
        //los pintores porque nos interesa saber en que mes y semana todos expusieron.
        for(int numMes = 0; numMes < meses.capacidad(); numMes++)
        {
            int conteoExposPorSemana = 0;
            int semanaActual = 1;

            for(int numDia = 0; numDia < diasPMes; numDia++)
            {
                Arreglo pintoresEvaluados = new Arreglo(pintores.getLongitud());
                for(int numPintor = 0; numPintor < pintores.getLongitud(); numPintor++)
                {
                    //Se extraen las actividades hechas por el pintor en el día.
                    Arreglo acts = (Arreglo) historialPintores.getElemento(numPintor, numMes, numDia);
                    Pintor pintorActual = (Pintor) pintores.getElemento(numPintor);
                    //Se evalua si el pintor expuso ese día y si no era un pintor del que ya se había contado.
                    if(pintoresEvaluados.buscar(pintorActual) == null && acts.contar(ActividadPintor.EXPONER) > 0)
                    {
                        pintoresEvaluados.agregar(pintorActual);
                        conteoExposPorSemana++;
                    }
                }
                //Se aumenta el número de semana cada cinco días.
                if((numDia + 1) % 5 == 0)
                {
                    semanaActual++;
                    if(conteoExposPorSemana >= pintores.getLongitud())
                    {
                        SalidaEstandar.consola("\nEn el mes: " + (numMes + 1) + " y la semana: " +
                                (semanaActual - 1) + " expusieron todos los pintores." );
                        return;
                    }
                    conteoExposPorSemana = 0;
                }
            }
        }
    }


    /**
     * Obtiene el mes en el que el pintor dedicó más tiempo a la actividad
     * dada como parámetro.
     * @param pintor Pintor a analizar sus actividades.
     * @param actividad Actividad a analizar cuantas veces fue repetida.
     * @return Regresa el número del mes en que más veces se realizó la actividad
     */
    public int mesMayorActividadRepetida(Pintor pintor, ActividadPintor actividad)
    {
        int mesMasRepetido = 0;
        int numRepeticionesGeneral = 0;
        Integer posPintor = (Integer) pintores.buscar(pintor);

        if(posPintor != null)
        {
            //Se iteran los meses y días de las actividades del pintor
            for (int numMes = 0; numMes < meses.capacidad(); numMes++)
            {
                int repeticionesEnElMes = 0;
                for (int numDia = 0; numDia < diasPMes; numDia++)
                {
                    //Se extraen las actividades que realizó el pintor en ese día.
                    Arreglo acts = (Arreglo) historialPintores.getElemento(posPintor, numMes, numDia);
                    //Se contean las veces que descansó en el día.
                    repeticionesEnElMes += acts.contar(actividad);
                }
                if(repeticionesEnElMes > numRepeticionesGeneral)
                {
                    mesMasRepetido = numMes;
                }
            }
        }
        if(mesMasRepetido != 0)
        {
            return mesMasRepetido + 1;
        }
        return mesMasRepetido;
    }

    /**
     * Realiza la impresión del mes y la semana en que ningún pintor realizó
     * la actividad dada como parámetro.
     * @param actividad Actividad a comparar cuando nunca se hizo.
     */
    public void imprimirFechaNadieHizoActividad(ActividadPintor actividad)
    {
        //Se iteran los datos partiendo del mes, la semana, el día y al final
        //los pintores porque nos interesa saber en que mes y semana todos expusieron.
        for(int numMes = 0; numMes < meses.capacidad(); numMes++)
        {
            int conteoExposPorSemana = 0;
            int semanaActual = 1;

            for(int numDia = 0; numDia < diasPMes; numDia++)
            {
                Arreglo pintoresEvaluados = new Arreglo(pintores.getLongitud());
                for(int numPintor = 0; numPintor < pintores.getLongitud(); numPintor++)
                {
                    //Se extraen las actividades hechas por el pintor en el día.
                    Arreglo acts = (Arreglo) historialPintores.getElemento(numPintor, numMes, numDia);
                    Pintor pintorActual = (Pintor) pintores.getElemento(numPintor);
                    //Se evalua si el pintor expuso ese día y si no era un pintor del que ya se había contado.
                    if(pintoresEvaluados.buscar(pintorActual) == null && acts.contar(ActividadPintor.EXPONER) > 0)
                    {
                        pintoresEvaluados.agregar(pintorActual);
                        conteoExposPorSemana++;
                    }
                }
                //Se aumenta el número de semana cada cinco días.
                if((numDia + 1) % 5 == 0)
                {
                    semanaActual++;
                    if(conteoExposPorSemana == 0)
                    {
                        SalidaEstandar.consola("\nEn el mes: " + (numMes + 1) + " y la semana: " +
                                (semanaActual - 1) + " nadie se inspiró." );
                        return;
                    }
                    conteoExposPorSemana = 0;
                }
            }
        }
    }

    /**
     * Realiza la impresión de las actividades realizadas
     * por el pintor en cada en cada día de la semana, de
     * cada mes.
     * @param pintor Pintor a consultar sus actividades.
     */
    public void imprimirActividades(Pintor pintor)
    {
        //Se extrae la posicion del pintor en la matriz.
        Integer posPintor = (Integer) pintores.buscar(pintor);

        //Se iteran los meses y los días correspondientes al pintor.
        for(int numMes = 0; numMes < meses.capacidad(); numMes++)
        {
            for(int numDia = 0; numDia < diasPMes; numDia++)
            {
                //Se obtienen las actividades realizadas por el pintor ese día.
                Arreglo acts = (Arreglo) historialPintores.getElemento(posPintor, numMes, numDia);

                for(int posAct = 0; posAct < acts.getLongitud(); posAct++)
                {
                    //Se imprimen las actividades que realizó el pintor ese día y mes.
                    ActividadPintor actividadPintor = (ActividadPintor) acts.getElemento(posAct);
                    SalidaEstandar.consola("\nMes: " + (numMes + 1) + " Día: " + (numDia + 1) +
                    " Actividad: " + actividadPintor.getNombre());
                }
            }
        }
    }
}
