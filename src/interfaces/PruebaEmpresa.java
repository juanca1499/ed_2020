package interfaces;

import catalogos.Empresa;
import catalogos.Sucursal;
import edlineal.Arreglo;
import entradasalida.SalidaEstandar;


public class PruebaEmpresa
{
    public static void main(String[] args)
    {
        Empresa empresa1 = new Empresa("ElWalmar", 3, 2, 12);

        Sucursal sucursal1 = new Sucursal("Lomas","Amargura", 41);
        Sucursal sucursal2 = new Sucursal("Limones","Llanto", 233);
        Sucursal sucursal3 = new Sucursal("Patito","Sufrimiento", 90);

        empresa1.agregarSucursal(sucursal1);
        empresa1.agregarSucursal(sucursal2);
        empresa1.agregarSucursal(sucursal3);

        empresa1.agregarDepartamento("Farmacia");
        empresa1.agregarDepartamento("Lacteos");

        empresa1.agregarMes("Enero");
        empresa1.agregarMes("Marzo");
        empresa1.agregarMes("Febrero");

        empresa1.agregarSumaVenta(10435.4,"Limones","Farmacia", "Enero");
        empresa1.agregarSumaVenta(20000.1,"Lomas","Farmacia", "Enero");
        empresa1.agregarSumaVenta(500000,"Lomas","Lacteos", "Enero");
        empresa1.agregarSumaVenta(30000,"Lomas","Lacteos", "Febrero");
        empresa1.agregarSumaVenta(34222.3,"Lomas","Lacteos", "Marzo");
        empresa1.agregarSumaVenta(455223.98,"Limones","Lacteos", "Febrero");
        empresa1.agregarSumaVenta(563232,"Lomas","Farmacia", "Febrero");

        SalidaEstandar.consola("\n");
        empresa1.imprimirSucursales();
        SalidaEstandar.consola("\n");
        empresa1.imprimirDepartamentos();
        SalidaEstandar.consola("\n");
        empresa1.imprimirMeses();
        SalidaEstandar.consola("\n");
        empresa1.imprimirVentas();
        SalidaEstandar.consola("\n");

        Arreglo mesesConsultados = new Arreglo(2);
        mesesConsultados.agregar("Enero");
        mesesConsultados.agregar("Febrero");
        double sumaAcumulada = empresa1.ventasAcumuladasPorMes("Lomas", "Lacteos", mesesConsultados);
        SalidaEstandar.consola("La suma acumulada solicitada es: " + sumaAcumulada);
        SalidaEstandar.consola("\n");
    }
}
