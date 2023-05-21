package catalogos;

import edlineal.ListaLigada;

/**
 * Esta clase implementa el TDA Computadora.
 * Almacena los datos correspondientes a la Computadora.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Computadora
{
    /** Guarda el número asignado a la Computadora */
    private int numero;
    /** Guarda la cantidad de RAM que posee la Computadora */
    private int ram;
    /** Guarda el disco duro que posee la Computadora */
    private String discoDuro;
    /** Guarda el procesaor que posee la Computadora */
    private String procesador;
    /** Guarda la marca que posee la Computadora */
    private String marca;
    /** Guarda la lista de aplicaciones que tiene instalada la Computadora */
    private ListaLigada listaAplicaciones;

    /**
     * Define el constructor de TDA Computadora.
     * @param numero Número de la computadora.
     * @param ram RAM que contiene la Computadora.
     * @param discoDuro Modelo de disco duro que tiene la Computadora.
     * @param procesador Modelo del procesador que tiene la Computadora.
     * @param marca Marca que posee la Computadora.
     */
    public Computadora(int numero, int ram, String discoDuro, String procesador, String marca)
    {
        this.numero = numero;
        this.ram = ram;
        this.discoDuro = discoDuro;
        this.procesador = procesador;
        this.marca = marca;
        this.listaAplicaciones = new ListaLigada();
    }

    /**
     * Obtiene el número asignado a la Computadora.
     * @return Regresa el número de la Computadora.
     */
    public int getNumero()
    {
        return numero;
    }

    /**
     * Asigna un número a la Computadora.
     * @param numero Valor a ser asignado.
     */
    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    /**
     * Obtiene la cantidad de RAM que tiene la Computadora.
     * @return Regresa la cantidad de RAM que posee.
     */
    public int getRam()
    {
        return ram;
    }

    /**
     * Asigna una cantidad de RAM a la Computadora.
     * @param ram Cantidad de RAM.
     */
    public void setRam(int ram)
    {
        this.ram = ram;
    }

    /**
     * Obtiene el nombre del modelo del disco duro que
     * posee la Computadora.
     * @return Regresa el modelo del disco duro.
     */
    public String getDiscoDuro()
    {
        return discoDuro;
    }

    /**
     * Asigna un modelo de disco duro a la Computadora.
     * @param discoDuro Modelo a ser asignado.
     */
    public void setDiscoDuro(String discoDuro)
    {
        this.discoDuro = discoDuro;
    }

    /**
     * Obtiene el modelo del procesador que posee la Computadora.
     * @return Regresa el modelo del procesador.
     */
    public String getProcesador()
    {
        return procesador;
    }

    /**
     * Asigna un modelo de procesador a la Computadora.
     * @param procesador Modelo a ser asignado.
     */
    public void setProcesador(String procesador)
    {
        this.procesador = procesador;
    }

    /**
     * Obtiene la marca de la Computadora.
     * @return Regresa el nombre de la marca.
     */
    public String getMarca()
    {
        return marca;
    }

    /**
     * Asigna un nombre de marca a la Computadora.
     * @param marca Nombre de marca a ser asignado.
     */
    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    /**
     * Obtiene la lista de aplicaciones que tiene
     * instaladas la Computadora.
     * @return Regresa la lista de aplicaciones.
     */
    public ListaLigada getAplicaciones()
    {
        return listaAplicaciones;
    }

    /**
     * Agrega una nueva aplicación instalada a la
     * Computadora.
     * @param nuevaApp Aplicacion a ser agregada.
     * @return Regresa <b>true</b> en caso de haber
     * podido agregar la aplicación, o <b>false</b> en
     * caso contrario.
     */
    public boolean agregarAplicacion(Aplicacion nuevaApp)
    {
        return listaAplicaciones.agregar(nuevaApp);
    }

    /**
     * Elimina una de las aplicaciones instaladas que tiene
     * la Computadora
     * @param nombre Aplicación a eliminar.
     * @return Regresa el objeto de la aplicación eliminada en
     * caso de haber podido realizar la operación, o null en caso
     * contrario.
     */
    public Aplicacion eliminarAplicacion(String nombre)
    {
        return (Aplicacion) listaAplicaciones.eliminar(
                listaAplicaciones.buscar(nombre));
    }

    /**
     * Obtiene los datos que conforman a la Computadora.
     * @return Regresa una cadena con la información.
     */
    public String getDatos()
    {
        return "\nNumero: " + numero + "\nCantidad de RAM(Gb): " + ram +
                "\nModelo disco duro: " + discoDuro + "\nModelo procesador: " + procesador +
                "\nMarca: " + marca + "\n";
    }

    @Override
    public String toString()
    {
        return ""+numero;
    }
}
