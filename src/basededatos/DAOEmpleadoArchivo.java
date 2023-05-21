package basededatos;

import catalogos.Empleado;
import catalogos.NodoIndice;
import edlineal.Lista;
import edlineal.ListaDobleLigada;
import edlineal.ListaEnlazadaIndexada;
import ednolineal.ArbolBinarioBusqueda;
import entradasalida.SalidaEstandar;
import utilidades.ManipuladorString;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Realiza operaciones CRUD sobre un archivo
 * que contiene TDA'S Empleado. El archivo es
 * indexado por medio de un TDA ArbolBinarioBusqueda
 * para aumentar la eficiencia en las operaciones
 * que solicite el cliente.
 * @author Clase de estructura de datos.
 * @author Juan Carlos García Murillo.
 * @version 1.0
 */
public class DAOEmpleadoArchivo implements DAOEmpleado
{
    /** Guarda el archivo que almacena los registros. */
    private RandomAccessFile archivo;
    /** Guarda el ArbolBinarioBusqueda que indexa el archivo. */
    private ArbolBinarioBusqueda arbolArchivo;

    /**
     * Define el constructor de TDA DAOEmpleadoArchivo.
     * @param ruta Dirección donde se encuentra el
     *             archivo a manipular.
     */
    public DAOEmpleadoArchivo(String ruta)
    {
        try
        {
            archivo = new RandomAccessFile(ruta, "rw");
            arbolArchivo = new ArbolBinarioBusqueda();
            indexarArchivo();
        }
        catch (IOException eio)
        {
            SalidaEstandar.consola("Ocurrió un error al leer el archivo.");
        }
    }

    /**
     * Realiza la indexación de los datos contenidos en el archivo
     * creando un TDA ArbolBinarioBusqueda con los id's y direcciones
     * de memoria de cada registro en el archivo.
     */
    private void indexarArchivo()
    {
        try
        {
            String cadena = "";
            // Se ignoran los encabezados.
            archivo.readLine();

            // Se itera el archivo hasta que ya no haya más datos.
            while (true)
            {
                // Se obtiene la dirección del archivo donde está el registro.
                Long direccion = archivo.getFilePointer();
                cadena = archivo.readLine();

                // La lectura de línea es nula, por lo tanto, ya no hay datos.
                if(cadena == null)
                {
                    break;
                }
                // Se obtienen el id del empleado para insertar en el nodo.
                Integer indice = Integer.parseInt(ManipuladorString.subCadena(cadena, '\t'));
                NodoIndice nodo = new NodoIndice(indice, direccion);
                arbolArchivo.insertar(nodo);
            }
        }
        catch (FileNotFoundException fe)
        {
            SalidaEstandar.consola("No se encontró el archivo.");
        }
        catch (IOException eio)
        {
            SalidaEstandar.consola("Error al intentar leer el archivo.");
        }
    }

    @Override
    public boolean agregar(Empleado empleado)
    {
        // El empleado es válido y el id no existía antes en la base de datos.
        if(empleado != null && buscar(empleado.getId()) == null)
        {
            try
            {
                // Se extraen los datos del TDA Empleado y se asignan a
                // un String para guardarse en el mismo formato en que se leen.
                String cadenaRegistro = "";
                cadenaRegistro += "\n";
                cadenaRegistro += empleado.getId() + "\t";
                cadenaRegistro += empleado.getNombre() + "\t";
                cadenaRegistro += empleado.getApellido() + "\t";
                cadenaRegistro += empleado.getSeccional() + "\t";
                cadenaRegistro += empleado.getFacultad() + "\t";
                cadenaRegistro += empleado.getCargo() + "\t";
                cadenaRegistro += empleado.getSalario() + "\t";
                cadenaRegistro += empleado.getFechaComienzo() + "\t";
                cadenaRegistro += empleado.getFechaNacimiento();
                // Se sitúa el puntero en la última posición para la escritura.
                archivo.seek(archivo.length());
                Long direccionMemoria = archivo.length() + 1;
                // Se escribe el registro en el archivo y el index en el árbol.
                archivo.writeBytes(cadenaRegistro);
                arbolArchivo.insertar(new NodoIndice(empleado.getId(), direccionMemoria));
                return true;
            }
            catch (IOException eio)
            {
                SalidaEstandar.consola("Error al leer el archivo");
                return false;
            }
        }
        // El empleado es inválido.
        else
        {
            return false;
        }
     }

    @Override
    public Empleado eliminar(Integer id)
    {
        // El ID es válido.
        if(id != null)
        {
            NodoIndice nodoEliminar = (NodoIndice) arbolArchivo.buscar(id);
            // El Empleado a eliminar sí se encuentra en el archivo.
            if(nodoEliminar != null)
            {
                Empleado empleado = buscar(nodoEliminar.getIndice());
                try
                {
                    // Sirve para identificar si se necesita hacer al archivo
                    // un readline() extra para poder alcanzar el siguiente registro.
                    boolean saltarResiduo = false;
                    int contadorLineas = 0;
                    // Se calcula el tamaño a reducir del archivo.
                    Long dirActual = nodoEliminar.getDireccion();
                    archivo.seek(dirActual);
                    archivo.readLine();
                    Long reduccion = archivo.getFilePointer() - dirActual;

                    while (true)
                    {
                        archivo.seek(dirActual);
                        archivo.readLine();

                        if(saltarResiduo == true)
                        {
                            // Saltar el residuo para alcanzar el registro de la
                            // siguiente línea.
                            archivo.readLine();
                            saltarResiduo = false;
                        }
                        Long dirSiguiente = archivo.getFilePointer();

                        if(dirSiguiente.longValue() == archivo.length())
                        {
                            // Se llegó al final del archivo, ya no hay más
                            // que recorrer.
                            break;
                        }
                        // Se copia el registro siguiente para ser recorrido.
                        String regSiguiente = archivo.readLine();
                        Integer idRegistro = Integer.valueOf(
                                ManipuladorString.subCadena(regSiguiente, '\t'));
                        // El registro copiado ocupa el lugar del registro anterior.
                        archivo.seek(dirActual);
                        archivo.writeBytes(regSiguiente + "\n");
                        // Se asigna al nodo del registro su nueva dirección.
                        NodoIndice nodoActual = (NodoIndice) arbolArchivo.buscar(idRegistro);
                        nodoActual.setDireccion(dirActual);
                        dirActual = archivo.getFilePointer();
                        contadorLineas++;

                        if(archivo.getFilePointer() < dirSiguiente)
                        {
                            // El puntero del archivo no alcanzó el siguiente
                            // registro, por tanto se hará un readline() extra.
                            saltarResiduo = true;
                        }
                    }
                    if(contadorLineas == 0)
                    {
                        // El contador de líneas es cero, significa que
                        // la última línea fue borrada.
                        reduccion++;
                    }
                    // Eliminación del índice en el árbol y reducción del archivo.
                    archivo.setLength(archivo.length() - reduccion);
                    arbolArchivo.eliminar(nodoEliminar);
                    return empleado;
                }
                catch (IOException eio)
                {
                    SalidaEstandar.consola("Ocurrió un error de acceso al archivo.");
                    return null;
                }
            }
            // El empleado no se encuentra en el archivo.
            else
            {
                return null;
            }
        }
        // El ID es inválido.
        else
        {
            return null;
        }
    }

    @Override
    public Empleado buscar(Integer id)
    {
        // El ID es válido.
        if(id != null)
        {
            // Se crea la instancia del empleado a crear.
            Empleado empleado = new Empleado();
            NodoIndice nodo = (NodoIndice) arbolArchivo.buscar(id);

            // El empleado con el id solicitado fue encontrado.
            if(nodo != null)
            {
                Long dirMemoria = nodo.getDireccion();
                // Se procede a extraer los datos del archivo y asignarlos al objeto.
                try
                {
                    // Se extrae la información del Empleado.
                    archivo.seek(dirMemoria);

                    String cadenaRegistro = archivo.readLine();
                    // Se separa la cadena de texto en los atributos del Empleado.
                    ListaDobleLigada listaDatos = ManipuladorString.separar(cadenaRegistro, '\t');
                    listaDatos.inicializarIteradorPrincipio();
                    // Se asigna cada uno de los atributos obtenidos al Empleado.
                    empleado.setId(Integer.valueOf((String) listaDatos.obtenerSiguiente()));
                    empleado.setNombre((String) listaDatos.obtenerSiguiente());
                    empleado.setApellido((String) listaDatos.obtenerSiguiente());
                    empleado.setSeccional((String) listaDatos.obtenerSiguiente());
                    empleado.setFacultad((String) listaDatos.obtenerSiguiente());
                    empleado.setCargo((String) listaDatos.obtenerSiguiente());
                    empleado.setSalario((String) listaDatos.obtenerSiguiente());
                    empleado.setFechaComienzo((String) listaDatos.obtenerSiguiente());
                    empleado.setFechaNacimiento((String) listaDatos.obtenerSiguiente());
                }
                catch (IOException eio)
                {
                    SalidaEstandar.consola("Error al leer el archivo");
                    return null;
                }
                return empleado;
            }
            // El empleado con el id solicitado no fue encontrado.
            else
            {
                return null;
            }
        }
        // El ID es inválido
        else
        {
            return null;
        }
    }
}