package basededatos;

import catalogos.Empleado;

/**
 * Define los métodos para relizar operaciones
 * CRUD sobre un medio de almacenamiento que contenga
 * TDA's Empleado.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public interface DAOEmpleado
{
    /**
     * Inserta un nuevo registro en el
     * medio de almacenamiento de Empleados.
     * @param empleado Elemento a ser insertado.
     * @return Regresa <b>true</b> en caso de
     * haber podido insertar con éxito el nuevo
     * registro, o <b>false</b> en caso contrario.
     */
    boolean agregar(Empleado empleado);

    /**
     * Suprime un registro contenido en el
     * medio de almacenamiento de Empleados.
     * @param id Identificador del registro
     *           a eliminar.
     * @return Regresa un TDA Empleado con la
     * información del registro eliminado en caso
     * de haberse podido realizar la operación, o
     * null en caso contrario.
     */
    Empleado eliminar(Integer id);

    /**
     * Busca un registro contenido en el medio de
     * almacenamiento de Empleados.
     * @param id Identificador del registro
     *           a ser buscado.
     * @return Regresa un TDA Empleado con la información
     * del registro en caso de haberse encontrado, o null
     * en caso contrario.
     */
    Empleado buscar(Integer id);
}

