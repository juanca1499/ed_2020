package interfaces;

import basededatos.DAOEmpleado;
import basededatos.DAOEmpleadoArchivo;
import catalogos.Empleado;
import entradasalida.EntradaEstandar;
import entradasalida.SalidaEstandar;

import javax.swing.plaf.SliderUI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;



public class PruebaArbolArchivo
{
    public static void main(String[] args)
    {
        DAOEmpleado daoEmpleado = new DAOEmpleadoArchivo("resources/arboles/base2.txt");


        SalidaEstandar.consola("AGREGANDO REGISTROS AL ARCHIVO:");
        SalidaEstandar.consola("\nEmpleado 1:");
        Empleado empleado = new Empleado();
        empleado.setId(3000);
        empleado.setNombre("Juan Carlos");
        empleado.setApellido("García");
        empleado.setSeccional("Jerez");
        empleado.setFacultad("Administración");
        empleado.setCargo("Docente");
        empleado.setSalario("$10,000,000");
        empleado.setFechaComienzo("9/5/2020");
        empleado.setFechaNacimiento("14/10/1999");
        SalidaEstandar.consola("\nStatus inserción: " + daoEmpleado.agregar(empleado));

        SalidaEstandar.consola("\nEmpleado 2:");
        empleado = new Empleado();
        empleado.setId(3001);
        empleado.setNombre("Esther");
        empleado.setApellido("González");
        empleado.setSeccional("Jerez");
        empleado.setFacultad("Administración");
        empleado.setCargo("Docente");
        empleado.setSalario("$20,000,000");
        empleado.setFechaComienzo("1/5/2020");
        empleado.setFechaNacimiento("18/05/1985");
        SalidaEstandar.consola("\nStatus inserción: " + daoEmpleado.agregar(empleado));

        SalidaEstandar.consola("\nEmpleado 3:");
        empleado = new Empleado();
        empleado.setId(3002);
        empleado.setNombre("Juan");
        empleado.setApellido("González");
        empleado.setSeccional("Jerez");
        empleado.setFacultad("Administración");
        empleado.setCargo("Docente");
        empleado.setSalario("$15,000,000");
        empleado.setFechaComienzo("19/05/1985");
        empleado.setFechaNacimiento("2/5/1975");
        SalidaEstandar.consola("\nStatus inserción: " + daoEmpleado.agregar(empleado));

        SalidaEstandar.consola("\nEmpleado 4 (repetido):");
        empleado = new Empleado();
        empleado.setId(3002);
        empleado.setNombre("Juan");
        empleado.setApellido("González");
        empleado.setSeccional("Jerez");
        empleado.setFacultad("Administración");
        empleado.setCargo("Docente");
        empleado.setSalario("$15,000,000");
        empleado.setFechaComienzo("19/05/1985");
        empleado.setFechaNacimiento("2/5/1975");
        SalidaEstandar.consola("\nStatus inserción: " + daoEmpleado.agregar(empleado));


        SalidaEstandar.consola("\n\n\nELIMINANDO REGISTROS DEL ARCHIVO:");
        SalidaEstandar.consola("\nEliminado 1: 1055");
        empleado = daoEmpleado.eliminar(1055);
        SalidaEstandar.consola("\nid eliminado: " + empleado.getId());

        SalidaEstandar.consola("\nEliminado 2: 1428");
        empleado = daoEmpleado.eliminar(1428);
        SalidaEstandar.consola("\nid eliminado: " + empleado.getId());

        SalidaEstandar.consola("\nEliminado 3: 1676");
        empleado = daoEmpleado.eliminar(1676);
        SalidaEstandar.consola("\nid eliminado: " + empleado.getId());


        SalidaEstandar.consola("\n\n\nBÚSQUEDA DE REGISTROS:");
        empleado = daoEmpleado.buscar(1152);
        SalidaEstandar.consola("\n\nBuscando empleado con id 1152:");
        SalidaEstandar.consola("\nID Empleado: " + empleado.getId());
        SalidaEstandar.consola("\nNombre: " + empleado.getNombre());
        SalidaEstandar.consola("\nApellido: " + empleado.getApellido());
        SalidaEstandar.consola("\nSeccional: " + empleado.getSeccional());
        SalidaEstandar.consola("\nFacultad: " + empleado.getFacultad());
        SalidaEstandar.consola("\nCargo: " + empleado.getCargo());
        SalidaEstandar.consola("\nSalario: " + empleado.getSalario());
        SalidaEstandar.consola("\nFecha Comienzo: " + empleado.getFechaComienzo());
        SalidaEstandar.consola("\nFecha Nacimiento: " + empleado.getFechaNacimiento());

        empleado = daoEmpleado.buscar(3001);
        SalidaEstandar.consola("\n\nBuscando empleado con id 3001:");
        SalidaEstandar.consola("\nID Empleado: " + empleado.getId());
        SalidaEstandar.consola("\nNombre: " + empleado.getNombre());
        SalidaEstandar.consola("\nApellido: " + empleado.getApellido());
        SalidaEstandar.consola("\nSeccional: " + empleado.getSeccional());
        SalidaEstandar.consola("\nFacultad: " + empleado.getFacultad());
        SalidaEstandar.consola("\nCargo: " + empleado.getCargo());
        SalidaEstandar.consola("\nSalario: " + empleado.getSalario());
        SalidaEstandar.consola("\nFecha Comienzo: " + empleado.getFechaComienzo());
        SalidaEstandar.consola("\nFecha Nacimiento: " + empleado.getFechaNacimiento());

        empleado = daoEmpleado.buscar(3002);
        SalidaEstandar.consola("\n\nBuscando empleado con id 3002:");
        SalidaEstandar.consola("\nID Empleado: " + empleado.getId());
        SalidaEstandar.consola("\nNombre: " + empleado.getNombre());
        SalidaEstandar.consola("\nApellido: " + empleado.getApellido());
        SalidaEstandar.consola("\nSeccional: " + empleado.getSeccional());
        SalidaEstandar.consola("\nFacultad: " + empleado.getFacultad());
        SalidaEstandar.consola("\nCargo: " + empleado.getCargo());
        SalidaEstandar.consola("\nSalario: " + empleado.getSalario());
        SalidaEstandar.consola("\nFecha Comienzo: " + empleado.getFechaComienzo());
        SalidaEstandar.consola("\nFecha Nacimiento: " + empleado.getFechaNacimiento());

        empleado = daoEmpleado.buscar(1080);
        SalidaEstandar.consola("\n\nBuscando empleado con id 1080:");
        SalidaEstandar.consola("\nID Empleado: " + empleado.getId());
        SalidaEstandar.consola("\nNombre: " + empleado.getNombre());
        SalidaEstandar.consola("\nApellido: " + empleado.getApellido());
        SalidaEstandar.consola("\nSeccional: " + empleado.getSeccional());
        SalidaEstandar.consola("\nFacultad: " + empleado.getFacultad());
        SalidaEstandar.consola("\nCargo: " + empleado.getCargo());
        SalidaEstandar.consola("\nSalario: " + empleado.getSalario());
        SalidaEstandar.consola("\nFecha Comienzo: " + empleado.getFechaComienzo());
        SalidaEstandar.consola("\nFecha Nacimiento: " + empleado.getFechaNacimiento());
    }
}
