package utilidades.entidades;

import edlineal.Arreglo;
import edlineal.Matriz;
import utilidades.Sexo;

/**
 * Esta clase define el TDA Pintor.
 * @author Clase de estructura de datos.
 * @version 1.0
 */
public class Pintor
{
    /** Guarda el nombre que recibe el Pintor. */
    private String nombre;
    /** Guarda la edad que posee el Pintor. */
    private int edad;
    /** Guarda el(la) sexo que posee el Pintor(a). */
    private Sexo sexo;
    /** Guarda las actividades que realizó el pintor durante cada semana de cada mes. */
    private Matriz historialActs;

    /**
     * Define el constructor de TDA Pintor.
     * @param nombre Nombre del pintor.
     * @param edad Edad del pintor.
     * @param sexo Sexo del pintor.
     */
    public Pintor(String nombre, int edad, Sexo sexo)
    {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        historialActs = new Matriz(12,20);
        Arreglo actividadesDelDia = new Arreglo(5);
        historialActs.inicializar(actividadesDelDia);
    }

    /**
     * Obtiene el nombre del pintor.
     * @return Regresa el atributo nombre del pintor.
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Obtiene la edad del pintor.
     * @return Regresa el atributo edad del pintor.
     */
    public int getEdad()
    {
        return edad;
    }

    /**
     * Obtiene el sexo del pintor.
     * @return Regresa el sexo del pintor.
     */
    public Sexo getSexo()
    {
        return sexo;
    }
}
