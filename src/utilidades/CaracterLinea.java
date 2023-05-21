package utilidades;

public class CaracterLinea
{

    private int linea;
    private int posCaracter;
    private String expresion;

    public CaracterLinea(int linea, int posCaracter, String expresion)
    {
        this.linea = linea;
        this.posCaracter = posCaracter;
        this.expresion = expresion;
    }

    public int getLinea()
    {
        return linea;
    }

    public int getCaracter()
    {
        return posCaracter;
    }

    public String getExpresion()
    {
        return expresion;
    }
}
