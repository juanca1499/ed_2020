package catalogos;

import edlineal.Arreglo;
import edlineal.Matriz3D;
import entradasalida.SalidaEstandar;


public class Empresa
{
    protected Matriz3D ventasAnuales;
    protected Arreglo sucursales;
    protected Arreglo departamentos;
    protected Arreglo meses;
    protected String nombre;

    public Empresa(String nombre, int suc, int dep, int numMeses)
    {
        this.nombre = nombre;
        ventasAnuales = new Matriz3D(suc, dep, numMeses);
        sucursales = new Arreglo(suc);
        departamentos = new Arreglo(dep);
        meses = new Arreglo(numMeses);
    }

    public boolean agregarSucursal(Sucursal sucursalNuevo)
    {
        return sucursales.agregar(sucursalNuevo);
    }

    public boolean agregarDepartamento(String nombreDepto)
    {
        return departamentos.agregar(nombreDepto);
    }

    public boolean agregarMes(String mes)
    {
        return meses.agregar(mes);
    }

    public boolean agregarSumaVenta(double cantidadVenta, String nombreSuc, String nombreDepto, String nombreMes)
    {
        Integer posSuc;
        Integer posDepto;
        Integer posMes;

        posSuc = (Integer) sucursales.buscar(nombreSuc);
        posDepto = (Integer) departamentos.buscar(nombreDepto);
        posMes = (Integer) meses.buscar(nombreMes);

        if(posSuc != null && posDepto != null && posMes != null)
        {
            return ventasAnuales.setElemento(posSuc.intValue(), posDepto.intValue(),
                                             posMes.intValue(), cantidadVenta);
        }
        return false;
    }

    public void imprimirSucursales()
    {
        SalidaEstandar.consola("Las sucursales disponibles son: ");
        SalidaEstandar.consola("\n");
        sucursales.imprimirR();
    }

    public void imprimirDepartamentos()
    {
        SalidaEstandar.consola("Los datos de los departamentos son ");
        SalidaEstandar.consola("\n");
        departamentos.imprimirR();
    }

    public void imprimirMeses()
    {
        SalidaEstandar.consola("Los meses registrados son: ");
        SalidaEstandar.consola("\n");
        meses.imprimirR();
    }

    public void imprimirVentas()
    {
        SalidaEstandar.consola("Las ventas por mes son: ");
        SalidaEstandar.consola("\n");
        ventasAnuales.imprimir();
    }

    public double ventasAcumuladasPorMes(String nombreSuc, String nombreDepto, Arreglo mesesSolicitados)
    {
        double ventasAcumuladas = 0.0;

        for(int mesActualProcesado = 0; mesActualProcesado < mesesSolicitados.getLongitud(); mesActualProcesado++)
        {
            Double ventaCasilla = ventasSucDeptoMes(nombreSuc, nombreDepto,
                    (String) mesesSolicitados.getElemento(mesActualProcesado));

            if(ventaCasilla != null) //Si tiene valor válido.
            {
                ventasAcumuladas += ventaCasilla.doubleValue();
            }
            else
            {
                //Algún valor era inválido de los argumentos o no existía.
            }
        }
        return ventasAcumuladas;
    }

    public Double ventasSucDeptoMes(String nombreSuc, String nombreDepto, String mes)
    {
        Integer indiceSucursal = (Integer) sucursales.buscar(nombreSuc);
        Integer indiceDepto = (Integer) departamentos.buscar(nombreDepto);
        Integer indiceMes = (Integer) meses.buscar(mes);

        if(indiceSucursal == null || indiceDepto == null || indiceMes == null)
        {
            return null;
        }
        else
        {
            return (Double)ventasAnuales.getElemento(indiceSucursal, indiceDepto, indiceMes);
        }
    }
}
