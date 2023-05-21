package interfaces;


import catalogos.VariableBayesiana;
import edlineal.ListaLigada;
import entradasalida.SalidaEstandar;
import utilidades.RedBayesiana;
import vistas.PantallaRedBayesiana;

/**
 * Contiene las pruebas del <b>Examen 3</b>
 * sobre la creación y manipulación de una
 * red bayesiana.
 * @author Juan Carlos García Murillo.
 * @author Clase de Estructura de Datos.
 * @version 1.0.
 */
public class PruebaRedBayesiana
{
    public static void main(String[] args)
    {
        // AGREGAR VARIABLES A LA RED BAYESIANA.
        RedBayesiana redBayesiana = new RedBayesiana(7);
        redBayesiana.agregarVariable(new VariableBayesiana(
        "U","Entrar a la universidad"));
        redBayesiana.agregarVariable(new VariableBayesiana(
        "F", "Tener condición física"));
        redBayesiana.agregarVariable(new VariableBayesiana(
        "E","Estudiar"));
        redBayesiana.agregarVariable(new VariableBayesiana(
        "A", "Poner atención en clase"));
        redBayesiana.agregarVariable(new VariableBayesiana(
        "S", "Estar en la selección de futbol"));
        redBayesiana.agregarVariable(new VariableBayesiana(
        "G", "Graduacion"));
        redBayesiana.agregarVariable(new VariableBayesiana(
        "T", "Titulacion"));

        // AGREGANDO DEPENDENCIAS A LAS VARIABLES.
        redBayesiana.agregarDependencia("U", "E");
        redBayesiana.agregarDependencia("U", "A");
        redBayesiana.agregarDependencia("U","S");
        redBayesiana.agregarDependencia("F","S");
        redBayesiana.agregarDependencia("E","G");
        redBayesiana.agregarDependencia("A","G");
        redBayesiana.agregarDependencia("G","T");


        // ASIGNACIÓN DE LOS CASOS DE PROBABILIDAD

        ListaLigada coordenadas = new ListaLigada();
        coordenadas.agregar(-1);
        redBayesiana.asignarProbabilidad("U", coordenadas, 0.4);
        redBayesiana.asignarProbabilidad("F", coordenadas, 0.6);

        coordenadas.vaciar();
        coordenadas.agregar(1);
        redBayesiana.asignarProbabilidad("U", coordenadas, 0.6);
        redBayesiana.asignarProbabilidad("F", coordenadas, 0.4);

        coordenadas.vaciar();
        coordenadas.agregar(-1);
        coordenadas.agregar(-1);
        redBayesiana.asignarProbabilidad("E", coordenadas,1.0);
        redBayesiana.asignarProbabilidad("A", coordenadas, 1.0);
        redBayesiana.asignarProbabilidad("T", coordenadas, 1.0);

        coordenadas.vaciar();
        coordenadas.agregar(-1);
        coordenadas.agregar(1);
        redBayesiana.asignarProbabilidad("E", coordenadas,0.0);
        redBayesiana.asignarProbabilidad("A", coordenadas, 0.0);
        redBayesiana.asignarProbabilidad("T", coordenadas, 0.0);

        coordenadas.vaciar();
        coordenadas.agregar(1);
        coordenadas.agregar(-1);
        redBayesiana.asignarProbabilidad("E", coordenadas,0.2);
        redBayesiana.asignarProbabilidad("A", coordenadas, 0.25);
        redBayesiana.asignarProbabilidad("T", coordenadas, 0.5);

        coordenadas.vaciar();
        coordenadas.agregar(1);
        coordenadas.agregar(1);
        redBayesiana.asignarProbabilidad("E", coordenadas,0.8);
        redBayesiana.asignarProbabilidad("A", coordenadas, 0.75);
        redBayesiana.asignarProbabilidad("T", coordenadas, 0.5);

        coordenadas.vaciar();
        coordenadas.agregar(-1);
        coordenadas.agregar(-1);
        coordenadas.agregar(-1);
        redBayesiana.asignarProbabilidad("S", coordenadas, 1.0);
        redBayesiana.asignarProbabilidad("G", coordenadas, 0.9);

        coordenadas.vaciar();
        coordenadas.agregar(-1);
        coordenadas.agregar(-1);
        coordenadas.agregar(1);
        redBayesiana.asignarProbabilidad("S", coordenadas, 0);
        redBayesiana.asignarProbabilidad("G", coordenadas, 0.1);

        coordenadas.vaciar();
        coordenadas.agregar(-1);
        coordenadas.agregar(1);
        coordenadas.agregar(-1);
        redBayesiana.asignarProbabilidad("S", coordenadas, 0.65);
        redBayesiana.asignarProbabilidad("G", coordenadas, 0.6);

        coordenadas.vaciar();
        coordenadas.agregar(-1);
        coordenadas.agregar(1);
        coordenadas.agregar(1);
        redBayesiana.asignarProbabilidad("S", coordenadas, 0.35);
        redBayesiana.asignarProbabilidad("G", coordenadas, 0.4);

        coordenadas.vaciar();
        coordenadas.agregar(1);
        coordenadas.agregar(-1);
        coordenadas.agregar(-1);
        redBayesiana.asignarProbabilidad("S", coordenadas, 0.8);
        redBayesiana.asignarProbabilidad("G", coordenadas, 0.4);

        coordenadas.vaciar();
        coordenadas.agregar(1);
        coordenadas.agregar(-1);
        coordenadas.agregar(1);
        redBayesiana.asignarProbabilidad("S", coordenadas, 0.2);
        redBayesiana.asignarProbabilidad("G", coordenadas, 0.6);

        coordenadas.vaciar();
        coordenadas.agregar(1);
        coordenadas.agregar(1);
        coordenadas.agregar(-1);
        redBayesiana.asignarProbabilidad("S", coordenadas, 0.3);
        redBayesiana.asignarProbabilidad("G", coordenadas, 0.2);

        coordenadas.vaciar();
        coordenadas.agregar(1);
        coordenadas.agregar(1);
        coordenadas.agregar(1);
        redBayesiana.asignarProbabilidad("S", coordenadas, 0.7);
        redBayesiana.asignarProbabilidad("G", coordenadas, 0.8);


        // EJECUCION DE LA VISTA QUE INTERACTÚA CON EL USUARIO.
        PantallaRedBayesiana pantallaRed = new PantallaRedBayesiana(redBayesiana);
        pantallaRed.mensajeInicio();
        pantallaRed.mostrarOpciones();
    }
}
