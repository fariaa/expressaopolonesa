package faculdade;

public class Resolvedor {
    private static final int TAMANHO_MAXIMO = 7;
    private String[] operador;
    
    public Resolvedor()
    {
        this.operador = new String[this.TAMANHO_MAXIMO];
        this.operador[0] = "(";
        this.operador[1] = "^";
        this.operador[2] = "*";
        this.operador[3] = "/";
        this.operador[4] = "+";
        this.operador[5] = "-";
        this.operador[6] = ")"; 
    }
    
    public double resolve(double valor1, String op, double valor2) throws Exception
    {
        if(op.equals(this.operador[1]))
            return Math.pow(valor1, valor2);
        
        if(op.equals(this.operador[2]))
            return valor1 * valor2;
        
        if(op.equals(this.operador[3]))
            return valor1 / valor2;
        
        if(op.equals(this.operador[4]))
            return valor1 + valor2;
        
        if(op.equals(this.operador[5]))
            return valor1 - valor2;
        else
            throw new Exception("operador invalido");
    }
    
    public boolean operadorExistente(Object operador)
    {
        if(operador.equals(this.operador[1]) || operador.equals(this.operador[2]) || 
           operador.equals(this.operador[3]) || operador.equals(this.operador[4]) ||
           operador.equals(this.operador[5]))
            return true;
        else
            return false;
    }
    
    public String toString ()
    {
        String ret = "";
            
        for(int cont = 0; cont <= this.operador.length; cont++)
        {
            ret += "P"+cont+": " + this.operador[cont];
        }

        return ret;
    }
	
    public boolean equals (Object obj)
    {
        if (obj==null)
            return false;

        if (this==obj)
            return true;

        if (this.getClass()!=obj.getClass())
            return false;

        Resolvedor r = (Resolvedor)obj;

        if (this.operador.length != r.operador.length)
            return false;

        for (int i=0; i <= this.operador.length; i++)
            if (!this.operador[i].equals(r.operador[i]))
                return false;
        return true;
    }

    public int hashCode ()
    {
        int ret = 897;

        for (int i=0; i <= this.operador.length; i++)
            ret = 7*ret + this.operador[i].hashCode();

        return ret;
    }
}
