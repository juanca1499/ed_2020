package catalogos;

import java.util.Date;

/**
 * Esta clase implementa el TDA Empleeado.
 * Almacena algunos de los datos básicos correspondientes
 * a un empleado.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo
 * @version 1.0
 */
public class Empleado
{
    /** Guarda el identificador único del Empleado. */
    private Integer id;
    /** Guarda el nombre del Empleado. */
    private String nombre;
    /** Guarda el apellido del Empleado. */
    private String apellido;
    /** Guarda el seccional o lugar donde labora el Empleado. */
    private String seccional;
    /** Guarda la factultad en la que labora el Empleado. */
    private String facultad;
    /** Guarda el cargo o tipo de trabajo que desempeña el Empleado. */
    private String cargo;
    /** Guarda el sueldo que posee el Empleado. */
    private String salario;
    /** Guarda la fecha en la que el Empleado comenzó a laborar. */
    private String fechaComienzo;
    /** Guarda la fecha en la que el Empleado nació. */
    private String fechaNacimiento;

    /**
     * Define el constructor de TDA Empleado.
     */
    public Empleado() { }

    /**
     * Asigna un identificador al Empleado.
     * @param id Identificador a ser asignado.
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * Recupera el identificador del Empleado.
     * @return Regresa el id.
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Asigna un nombre al Empleado.
     * @param nombre Valor a ser asignado en el
     *               campo de nombre.
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Recupera el nombre del Empleado.
     * @return Regresa el campo nombre.
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Asigna un apellido al Empleado.
     * @param apellido Valor a ser asignado en el
     *                 campo de apellido.
     */
    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    /**
     * Recupera el apellido del Empleado.
     * @return Regresa el campo apellido.
     */
    public String getApellido()
    {
        return apellido;
    }

    /**
     * Asigna un seccional al Empleado.
     * @param seccional Valor a ser asignado en el
     *                  campo de seccional.
     */
    public void setSeccional(String seccional)
    {
        this.seccional = seccional;
    }

    /**
     * Recupera el seccional del Empleado.
     * @return Regresa el campo seccional.
     */
    public String getSeccional() {
        return seccional;
    }

    /**
     * Asigna una facultad al Empleado.
     * @param facultad Valor a ser asignado en el
     *                 campo de facultad.
     */
    public void setFacultad(String facultad)
    {
        this.facultad = facultad;
    }

    /**
     * Recupera la facultad a la que pertenece el Empleado.
     * @return Regresa el campo facultad.
     */
    public String getFacultad()
    {
        return facultad;
    }

    /**
     * Asigna un cargo al Empleado.
     * @param cargo Valor a ser asignado en el
     *              campo de cargo.
     */
    public void setCargo(String cargo)
    {
        this.cargo = cargo;
    }

    /**
     * Recupera el cargo que posee el Empleado.
     * @return Regresa el campo cargo.
     */
    public String getCargo()
    {
        return cargo;
    }

    /**
     * Asigna un salario al Empleado.
     * @param salario Valor a ser asignado en el
     *                campo de salario.
     */
    public void setSalario(String salario)
    {
        this.salario = salario;
    }

    /**
     * Recupera el salario que posee el Empleado.
     * @return Regresa el campo salario.
     */
    public String getSalario()
    {
        return salario;
    }

    /**
     * Asigna la fecha en la que el
     * Empleado comenzó a laborar.
     * @param fechaComienzo Valor a ser asignado en el
     *                      campo de fechaComienzo.
     */
    public void setFechaComienzo(String fechaComienzo)
    {
        this.fechaComienzo = fechaComienzo;
    }

    /**
     * Recupera la fecha en la que el Empleado comenzó
     * a laborar.
     * @return Regresa el campo fechaComienzo.
     */
    public String getFechaComienzo()
    {
        return fechaComienzo;
    }

    /**
     * Asigna la fecha en la que el Empleado
     * nació.
     * @param fechaNacimiento Valor a ser asignado en el
     *                        campo fechaNacimiento.
     */
    public void setFechaNacimiento(String fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Recupera la fecha en la que el Empleado nació.
     * @return Regresa el campo fechaNacimiento.
     */
    public String getFechaNacimiento()
    {
        return fechaNacimiento;
    }
}
