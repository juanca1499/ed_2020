package catalogos;

import edlineal.ListaLigada;
import entradasalida.SalidaEstandar;

/**
 * Esta clase implementa el TDA Aplicacion.
 * Almacena los datos correspondientes a una aplicación
 * de computadora.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Aplicacion
{
    /** Guarda el nombre de la Aplicacion. */
    private String nombre;
    /** Guarda la ruta del logo de la Aplicacion. */
    private String logo;
    /** Guarda la lista de autores de la Aplicacion */
    private ListaLigada listaAutores;
    /** Guarda la clave de versión de la Aplicacion */
    private String version;
    /** Guarda la RAM mínima que se debe de tener para ejecutar la Aplicacion en Gb */
    private int ramMinima;

    /**
     * Define el constructor de TDA Aplicacion.
     * @param nombre Nombre que tendrá la Aplicacion.
     * @param logo Ruta del logo de la Aplicacion.
     * @param version Clave de versión de la Aplicacion.
     * @param ramMinima RAM mínima para poder ejecutar la Aplicacion.
     */
    public Aplicacion(String nombre, String logo, String version, int ramMinima)
    {
        this.nombre = nombre;
        this.logo = logo;
        this.version = version;
        this.ramMinima = ramMinima;
        listaAutores = new ListaLigada();
    }

    /**
     * Obtiene el nombre asignado a la Aplicacion.
     * @return Regresa el nombre de la Aplicacion.
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Asigna un nombre a la Aplicacion.
     * @param nombre Valor a ser asignado.
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Obtiene la ruta del logo de la Aplicacion.
     * @return Regresa la ruta del logo.
     */
    public String getLogo()
    {
        return logo;
    }

    /**
     * Asigna la ruta del logo de la Aplicacion.
     * @param rutaLogo Ruta donde está el logo de la Aplicacion.
     */
    public void setLogo(String rutaLogo)
    {
        this.logo = logo;
    }

    /**
     * Obtiene la lista de autores de la Aplicacion.
     * @return Regresa la lista de autores.
     */
    public ListaLigada getAutores()
    {
        return listaAutores;
    }

    /**
     * Agregar un nuevo autor de la Aplicacion.
     * @param autor Nombre del autor.
     * @return Regresa <b>true</b> en caso de haber
     * podido agregar el autor, y <b>false</b> en caso
     * contrario.
     */
    public boolean agregarAutor(String autor)
    {
        return listaAutores.agregar(autor);
    }

    /**
     * Elimina uno de los autores de la Aplicacion.
     * @param autor Nombre del Autor a eliminar.
     * @return Regresa el nombre del autor eliminado
     * caso de haber podido realizar la operación, o null en caso
     * contrario.
     */
    public Object eliminarAutor(String autor)
    {
       return listaAutores.eliminar(autor);
    }

    /**
     * Obtiene la clave de versión de la Aplicacion.
     * @return Regresa la clave de version.
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * Asigna la clave de versión de la Aplicacion.
     * @param version Clave de versión a asignarse.
     */
    public void setVersion(String version)
    {
        this.version = version;
    }

    /**
     * Obtiene la cantidad mínima de RAM para ejecutar la
     * Aplicacion.
     * @return Regresa la RAM requerida en Gb.
     */
    public int getRamMinima()
    {
        return ramMinima;
    }

    /**
     * Aisgna la cantidad mínima de RAM para ejecutar la
     * Aplicacion.
     * @param ramMinima Cantidad de RAM mínima en Gb.
     */
    public void setRamMinima(int ramMinima)
    {
        this.ramMinima = ramMinima;
    }

    /**
     * Obtiene los datos que conforman a la Aplicacion.
     * @return Regresa una cadena con la información.
     */
    public String getDatos()
    {
        return "\nNombre: " + nombre + "\nRuta logo: " + logo +
                "\nVersion: " + version + "\nRAM Mínima para ejecutarse (Gb): " + ramMinima + "\n";

    }

    @Override
    public String toString()
    {
        return nombre;
    }
}
