package interfaces;

import catalogos.GaleriaArte;
import entradasalida.SalidaEstandar;
import utilidades.ActividadPintor;
import utilidades.Sexo;
import utilidades.entidades.Pintor;


public class PruebaGaleriaArte
{
    public static void main(String[] args)
    {
        GaleriaArte galeria = new GaleriaArte("Bellas artes",12,20,15);
        galeria.agregarMes("Enero");
        galeria.agregarMes("Febrero");
        galeria.agregarMes("Marzo");
        galeria.agregarMes("Abril");
        galeria.agregarMes("Mayo");
        galeria.agregarMes("Junio");
        galeria.agregarMes("Julio");
        galeria.agregarMes("Agosto");
        galeria.agregarMes("Septiembre");
        galeria.agregarMes("Octubre");
        galeria.agregarMes("Noviembre");
        galeria.agregarMes("Diciembre");
        //
        Pintor pintor1 = new Pintor("Pancho",20, Sexo.MASCULINO);
        Pintor pintor2 = new Pintor("Carlos",20, Sexo.MASCULINO);
        Pintor pintor3 = new Pintor("Alfredo",20, Sexo.MASCULINO);
        Pintor pintor4 = new Pintor("Araceli", 50, Sexo.FEMENINO);

        galeria.agregarPintor(pintor1);
        galeria.agregarPintor(pintor2);
        galeria.agregarPintor(pintor3);
        galeria.agregarPintor(pintor4);

        galeria.asignarActividad(pintor1, ActividadPintor.DESCANSAR);
        galeria.asignarActividad(pintor2, ActividadPintor.INSPIRAR);
        galeria.asignarActividad(pintor3, ActividadPintor.EXPONER);


        //Mismas actividades de un pintor
        Pintor pintorMismasActs = galeria.getPintorMismasActividades();
        SalidaEstandar.consola("Pintor que realizó las mismas actividades todo el año:");
        SalidaEstandar.consola("\nNombre: " + pintorMismasActs.getNombre() + "\nEdad: " + pintor1.getEdad());


        // En que semana viajó un pintor
        galeria.agregarActividad(1, "Enero", pintor3, ActividadPintor.VIAJAR);
        SalidaEstandar.consola("\n\nFechas en que el pintor " + pintor3.getNombre() + " viajó:");
        galeria.imprimirFechaViaje(pintor3);


        //Mes en se pintaron menos obras de arte
        galeria.agregarActividad(1,"Enero", pintor1 , ActividadPintor.PINTAR);
        galeria.agregarActividad(1,"Enero", pintor2 , ActividadPintor.PINTAR);
        galeria.agregarActividad(1,"Marzo", pintor3 , ActividadPintor.PINTAR);
        SalidaEstandar.consola("\n\nMes en que se pintaron menos obras de arte: " + galeria.getMesMenosObrasArte());


        //Pintores que expusieron todos los meses aunque sea una sola vez.
        galeria.asignarActividad(pintor4, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Enero", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Febrero", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Marzo", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Abril", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Mayo", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Junio", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Julio", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Agosto", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Septiembre", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Octubre", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Noviembre", pintor2, ActividadPintor.EXPONER);
        galeria.agregarActividad(15, "Diciembre", pintor2, ActividadPintor.EXPONER);
        SalidaEstandar.consola("\n\nPintores que expusieron todos los meses por lo menos una vez:");
        galeria.imprimirPintoresExpusieronTodosMeses();

        //Semana y mes en el que todos los pintores expusieron.
        GaleriaArte galeria2 = new GaleriaArte("Bellas",12,20,15);
        galeria2.agregarMes("Enero");
        galeria2.agregarMes("Febrero");
        galeria2.agregarMes("Marzo");
        galeria2.agregarMes("Abril");
        galeria2.agregarMes("Mayo");
        galeria2.agregarMes("Junio");
        galeria2.agregarMes("Julio");
        galeria2.agregarMes("Agosto");
        galeria2.agregarMes("Septiembre");
        galeria2.agregarMes("Octubre");
        galeria2.agregarMes("Noviembre");
        galeria2.agregarMes("Diciembre");
        galeria2.agregarPintor(pintor1);
        galeria2.agregarPintor(pintor2);
        galeria2.agregarPintor(pintor3);
        galeria2.agregarActividad(13,"Octubre", pintor1, ActividadPintor.EXPONER);
        galeria2.agregarActividad(11,"Octubre", pintor2, ActividadPintor.EXPONER);
        galeria2.agregarActividad(14, "Octubre", pintor3, ActividadPintor.EXPONER);
        SalidaEstandar.consola("\n\nSemana en la que todos los pintores expusieron:");
        galeria2.imprimirFechaTodosExpusieron();

        //Mes en que se dedicó más tiempo a descansar
        galeria2.agregarActividad(10, "Abril", pintor1, ActividadPintor.DESCANSAR);
        galeria2.agregarActividad(15, "Abril", pintor1, ActividadPintor.DESCANSAR);
        galeria2.agregarActividad(20, "Marzo",pintor1, ActividadPintor.DESCANSAR);
        SalidaEstandar.consola("\n\nEl mes en que el pintor 1 dedicó más tiempo a descansar fue:  " +
        galeria2.mesMayorActividadRepetida(pintor1, ActividadPintor.DESCANSAR));


        //Mes y semana en que no se inspiró nadie.
        SalidaEstandar.consola("\n\nFecha en que nadie se inspiró:");
        galeria2.imprimirFechaNadieHizoActividad(ActividadPintor.INSPIRAR);

        SalidaEstandar.consola("\n\nHistorial:");
       //galeria2.imprimirActividades(pintor1);

    }
}
