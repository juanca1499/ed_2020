package catalogos;

import edlineal.Arreglo;
import edlineal.ListaLigada;
import entradasalida.SalidaEstandar;
import utilidades.TipoImpresion;

/**
 * Esta clase implementa el TDA CentroComputo.
 * Almacena y administra TDA'S Computadora.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class CentroComputo
{
    /** Almacena las computadoras que administra el CentroComputo. */
    private Arreglo arrayComputadoras;

    /**
     * Define el constructor de TDA CentroComputo.
     * @param tamano Cantidad máxima de computadoras
     *               a almacenar.
     */
    public CentroComputo(int tamano)
    {
        arrayComputadoras = new Arreglo(tamano);
    }

    /**
     * Obtiene de las computadoras almacenadas, la que tiene el
     * número dado como parámetro.
     * @param numeroComputadora Clave de computadora a buscar entre las
     *                          almacenadas.
     * @return Regresa la Computadora en caso de haberla encontrado, o null
     * en caso contrario.
     */
    public Computadora getComputadora(int numeroComputadora)
    {
        // Se itera el arreglo de computadoras.
        for(int index = 0; index < arrayComputadoras.getLongitud(); index++)
        {
            Computadora computadora = (Computadora) arrayComputadoras.getElemento(index);
            // Si el número de computadora coincide con el que se está buscando.
            if(computadora.getNumero() == numeroComputadora)
            {
                return computadora;
            }
        }
        // Si no se encontró ninguna Computadora con el número indicado.
        return null;
    }

    /**
     * Agrega una Computadora al Arreglo de computadoras.
     * @param nuevaComputadora Computadora a ser agregada.
     * @return Regresa <b>true</b> en caso de haber podido agregar
     * la Computadora, o <b>false</b> en caso contrario.
     */
    public boolean agregarComputadora(Computadora nuevaComputadora)
    {
        return arrayComputadoras.agregar(nuevaComputadora);
    }

    /**
     * Revisa si existe la Aplicacion en la Computadora dada como parámetro.
     * @param computadora Computadora a revisar sus aplicaciones.
     * @param aplicacion Aplicacion a buscarse en la Computadora.
     * @return Regresa un objeto de tipo Aplicacion en caso de haberla
     * encontrado, o null en caso contrario.
     */
    public Aplicacion buscarAplicacion(Computadora computadora, Aplicacion aplicacion)
    {
        // Se obtiene la lista de aplicaciones instaladas de la Computadora.
        ListaLigada listaApps = computadora.getAplicaciones();
        // Se busca la Aplicacion en la lista de aplicaciones de la Computadora.
        return (Aplicacion) listaApps.buscar(aplicacion);
    }

    /**
     * Elimina de las computadoras almacenadas, la que tiene el número
     * dado como parámetro junto con sus aplicaciones instaladas.
     * @param numeroComputadora Clave de computadora a ser eliminada.
     * @return Regresa la Computadora en caso de haberla podido eliminar,
     * o null en caso contrario.
     */
    public Computadora eliminarComputadora(int numeroComputadora)
    {
        Computadora computadora = getComputadora(numeroComputadora);
        return (Computadora) arrayComputadoras.eliminar(computadora);
    }

    /**
     * Agrega una nueva aplicación a la lista de aplicaciones de la Computadora
     * indicada en el parámetro.
     * @param numeroComputadora Computadora a agregar la aplicación.
     * @param nuevaApp Aplicación a agregarse a la Computadora.
     * @return Regresa <b>true</b> en caso de haber podido agregar la aplicación,
     * o <b>false</b> en caso contrario.
     */
    public boolean agregarAplicacion(int numeroComputadora, Aplicacion nuevaApp)
    {
        // Se obtiene la Computadora a agregarle la Aplicacion.
        Computadora computadora = getComputadora(numeroComputadora);
        // Se agrega la aplicación a la Computadora.
        return computadora.agregarAplicacion(nuevaApp);
    }

    /**
     * Elimina de una Aplicacion de una Computadora.
     * @param numeroComputadora Computadora a eliminar una Aplicacion.
     * @param nombreApp Nombre de la Aplicacion a eliminar.
     * @return Regresa el objeto de la Aplicacion eliminada en
     * caso de haber podido realizar la operación, o null en caso
     * contrario.
     */
    public Aplicacion eliminarAplicacion(int numeroComputadora, String nombreApp)
    {
        // Se obtiene la Computadora a eliminar la Aplicacion.
        Computadora computadora = getComputadora(numeroComputadora);
        // Se elimina la aplicación de la Computadora.
        return computadora.eliminarAplicacion(nombreApp);
    }

    /**
     * Busca entre las computadoras almacenadas, las que tienen
     * la aplicación instalada.
     * @param aplicacion Aplicacion a buscarse entre la lista de
     *                   aplicaciones de cada Computadora.
     * @return Regresa un Arreglo con las computadoras que sí tienen
     * la Aplicacion instalada.
     */
    public Arreglo getComputadorasPorApp(Aplicacion aplicacion)
    {
        // Arreglo de computadoras que tienen la Aplicacion instalada.
        Arreglo ocurrencias = new Arreglo(arrayComputadoras.getLongitud());

        // Se itera el arreglo de computadoras.
        for(int index = 0; index < arrayComputadoras.getLongitud(); index++)
        {
            Computadora computadora = (Computadora) arrayComputadoras.getElemento(index);
            // Se busca en cada computadora si tiene la Aplicacion instalada.
            if(buscarAplicacion(computadora, aplicacion) != null)
            {
                ocurrencias.agregar(computadora);
            }
        }
        return ocurrencias;
    }

    /**
     * Guarda en una lista las Computadoras que cuentan con la
     * RAM suficiente para ejecutar una Aplicacion.
     * @param aplicacion Aplicacion a comparar su compatibilidad
     *                   con las computadoras.
     * @return Regresa la lista de computadoras compatibles
     * con la Aplicacion.
     */
    public ListaLigada getComputadorasCompatibles(Aplicacion aplicacion)
    {
        ListaLigada computadorasCompatibles = new ListaLigada();

        // Se iteran las computadoras.
        for(int index = 0; index < arrayComputadoras.getLongitud(); index++)
        {
            Computadora computadora = (Computadora) arrayComputadoras.getElemento(index);

            // Si la Computadora puede soportar la Aplicacion.
            if(computadora.getRam() >= aplicacion.getRamMinima())
            {
                computadorasCompatibles.agregar(computadora);
            }
        }
        return computadorasCompatibles;
    }

    /**
     * Guarda en una lista las Computadoras que tienen aplicaciones
     * con una cierta cantidad de RAM para ser compatibles.
     * @param ram Cantidad de RAM a buscarse en las aplicaciones
     *            de las computadoras.
     * @return Regresa la lista de computadoras que cuentan con
     * aplicaciones con ese requisito de RAM.
     */
    public ListaLigada getComputadorasAppsRam(int ram)
    {
        ListaLigada listaComputadoras = new ListaLigada();

        // Se iteran las computadoras.
        for(int index = 0; index < arrayComputadoras.getLongitud(); index++)
        {
            Computadora computadora = (Computadora) arrayComputadoras.getElemento(index);
            ListaLigada listaApps = computadora.getAplicaciones();
            listaApps.inicializaIterador();

            // Se iteran las aplicaciones de la computadora.
            while (listaApps.hayNodoSiguiente())
            {
                Aplicacion aplicacion = (Aplicacion) listaApps.obtenerSiguiente();
                // Si la aplicacion pide una cantidad mayor o igual de RAM.
                if(aplicacion.getRamMinima() >= ram)
                {
                    listaComputadoras.agregar(computadora);
                    break;
                }
            }
        }
        return listaComputadoras;
    }

    /**
     * Guarda en una lista las Computadoras que tienen una
     * cierta cantidad de RAM.
     * @param ram Cantidad de RAM a buscarse en las computadoras.
     * @return Regresa la lista de computadoras que cumplen con el
     * requerimiento de RAM.
     */
    public ListaLigada getComputadorasConCantRam(int ram)
    {
        ListaLigada listaComputadoras = new ListaLigada();

        // Se iteran las computadoras.
        for(int index = 0; index < arrayComputadoras.getLongitud(); index++)
        {
            Computadora computadora = (Computadora) arrayComputadoras.getElemento(index);

            // Si la Computadora tiene la misma cantidad de RAM.
            if(computadora.getRam() == ram)
            {
                listaComputadoras.agregar(computadora);
            }
        }
        return listaComputadoras;
    }

    /**
     * Guarda en una lista las Computadoras que tienen una cierta
     * Aplicacion instalada.
     * @param aplicacion Aplicacion a revisar si está instalada en
     *                   cada Computadora.
     * @return Regresa la lista de las computadoras que tenían la app
     * instalada.
     */
    public ListaLigada getComputadorasConApp(Aplicacion aplicacion)
    {
        ListaLigada listaComputadoras = new ListaLigada();

        // Se iteran las computadoras.
        for(int index = 0; index < arrayComputadoras.getLongitud(); index++)
        {
            Computadora computadora = (Computadora) arrayComputadoras.getElemento(index);

            // Si la Computadora tiene la Aplicacion instalada.
            if(buscarAplicacion(computadora, aplicacion) != null)
            {
                listaComputadoras.agregar(computadora);
            }
        }
        return listaComputadoras;
    }

    /**
     * Imprime todas las computadoras almacenadas junto con sus datos
     * de forma detallada.
     */
    public void imprimirDatosTodasComputadoras()
    {
        for(int index = 0; index < arrayComputadoras.getLongitud(); index++)
        {
            Computadora computadora = (Computadora) arrayComputadoras.getElemento(index);
            imprimirDatosComputadora(computadora.getNumero(), TipoImpresion.DETALLADA);
        }
    }

    /**
     * Imprime todos los datos que posee una Computadora.
     * @param numeroComputadora Clave de computadora a imprimir sus datos.
     * @param tipoImpresion Nivel de detalle de la impresión de los datos de la
     *                      Computadora.
     */
    public void imprimirDatosComputadora(int numeroComputadora, TipoImpresion tipoImpresion)
    {
        Computadora computadora = getComputadora(numeroComputadora);

        // Si se encontro la Computadora se imprimen sus datos.
        if(computadora != null)
        {
            SalidaEstandar.consola("\n\n----------------------------------------------------");
            SalidaEstandar.consola(computadora.getDatos());

            // Si la impresión es detallada se imprimen también sus aplicaciones.
            if(tipoImpresion == TipoImpresion.DETALLADA)
            {
                SalidaEstandar.consola("\n\tAplicaciones instaladas:");
                imprimirAplicacionesComputadora(computadora);
            }
            SalidaEstandar.consola("\n----------------------------------------------------");
        }
        // No se encontró la Computadora.
        else
        {
            SalidaEstandar.consola("\nLa computadora indicada no existe");
        }
    }

    /**
     * Imprime los datos de las aplicaciones que posee una Computadora.
     * @param computadora Computadora a imprimir sus aplicaciones.
     */
    public void imprimirAplicacionesComputadora(Computadora computadora)
    {
        // Se extrae la lista de aplicaciones.
        ListaLigada listaApps = computadora.getAplicaciones();
        listaApps.inicializaIterador();

        // Se iteran las aplicaciones.
        while (listaApps.hayNodoSiguiente())
        {
            SalidaEstandar.consola("\n");
            Aplicacion aplicacion = (Aplicacion) listaApps.obtenerSiguiente();
            imprimirDatosAplicacion(aplicacion);
        }
    }

    /**
     * Imprime los datos de la Aplicacion.
     * @param aplicacion Aplicacion a imprimirse sus datos.
     */
    public void imprimirDatosAplicacion(Aplicacion aplicacion)
    {
        SalidaEstandar.consola(aplicacion.getDatos());
        // Se extraen los autores de la Aplicacion.
        ListaLigada listaAutores = aplicacion.getAutores();
        listaAutores.inicializaIterador();

        SalidaEstandar.consola("\n\tAutores de la aplicacion: " + aplicacion.getNombre());
        // Se iteran e imprimen los autores.
        while (listaAutores.hayNodoSiguiente())
        {
            SalidaEstandar.consola("\n");
            SalidaEstandar.consola(""+listaAutores.obtenerSiguiente());
        }
    }

    /**
     * Imprime los datos de las computadoras que son compatibles con la Aplicacion.
     * @param aplicacion Información de la Aplicacion que es compatible.
     */
    public void imprimirComputadorasCompatibles(Aplicacion aplicacion)
    {
        ListaLigada listaCompatibles = getComputadorasCompatibles(aplicacion);
        listaCompatibles.inicializaIterador();
        // Se itera la lista de computadoras compatibles.
        while (listaCompatibles.hayNodoSiguiente())
        {
            Computadora computadora = (Computadora) listaCompatibles.obtenerSiguiente();
            // Se imprimen los datos de la Computadora.
            imprimirDatosComputadora(computadora.getNumero(), TipoImpresion.NO_DETALLADA);
        }
    }

    /**
     * Imprime la información de las computadoras que tienen instaladas
     * aplicaciones con una cierta cantidad de RAM como requerimiento.
     * @param ram RAM requerida de la aplicacion.
     */
    public void imprimirComputadorasAppsRam(int ram)
    {
        ListaLigada listaComputadoras = getComputadorasAppsRam(ram);
        listaComputadoras.inicializaIterador();
        // Se itera la lista de computadoras.
        while (listaComputadoras.hayNodoSiguiente())
        {
            Computadora computadora = (Computadora) listaComputadoras.obtenerSiguiente();
            // Se imprimen los datos de la Computadora.
            imprimirDatosComputadora(computadora.getNumero(), TipoImpresion.NO_DETALLADA);
        }
    }

    /**
     * Imprime las computadoras que tienen una cantidad de RAM específica.
     * @param ram Cantidad de RAM a buscarse en las computadoras.
     */
    public void imprimirComputadorasConCantRam(int ram)
    {
        ListaLigada listaComputadoras = getComputadorasConCantRam(ram);
        listaComputadoras.inicializaIterador();
        // Se itera la lista de computadoras.
        while (listaComputadoras.hayNodoSiguiente())
        {
            Computadora computadora = (Computadora) listaComputadoras.obtenerSiguiente();
            // Se imprimen los datos de la Computadora.
            imprimirDatosComputadora(computadora.getNumero(), TipoImpresion.NO_DETALLADA);
        }
    }

    /**
     * Imprime las computadoras que tienen una cierta Aplicacion instalada.
     * @param aplicacion Aplicacion a comprobar si está instalada en las
     *                   computadoras.
     */
    public void imprimirComputadorasConApp(Aplicacion aplicacion)
    {
        ListaLigada listaComputadoras = getComputadorasConApp(aplicacion);
        listaComputadoras.inicializaIterador();
        // Se itera la lista de computadoras.
        while (listaComputadoras.hayNodoSiguiente())
        {
            Computadora computadora = (Computadora) listaComputadoras.obtenerSiguiente();
            // Se imprimen los datos de la Computadora.
            imprimirDatosComputadora(computadora.getNumero(), TipoImpresion.NO_DETALLADA);
        }
    }
}
