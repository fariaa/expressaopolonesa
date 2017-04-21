package faculdade;

import java.util.StringTokenizer;

public class Tabela {
    
    static final int TAMANHO_MAXIMO = 7;
    private String[] operador;
    private boolean[][] tabela;
    
    public Tabela(){
        this.operador = new String[this.TAMANHO_MAXIMO];
        this.tabela = new boolean[this.TAMANHO_MAXIMO][this.TAMANHO_MAXIMO]; 
        this.carregaValores();  
    }
    
    public boolean validaExpressao(String expressao) throws Exception
    {
        if(expressao.equals(null))
            throw new Exception("expressao errada");
        
        StringTokenizer quebrador = new StringTokenizer(expressao,"+-*/^()", true);
        String elemento = "";
        boolean abreP = false, fechaP = false;
        while(quebrador.hasMoreTokens())
        {
            elemento = quebrador.nextToken();
            if(elemento.equals("("))
                abreP = true;
            if(elemento.equals(")"))
                fechaP = true;
        }  
        
        if(abreP && fechaP || abreP == false && fechaP == false)
            return true;
        
        return false;
    }
    
    public boolean validaOperadores(String operador1, String operador2) throws Exception
    {
        int linha = -1;
        int coluna = -1;
        
        if(this.operador[6].equals(operador2))
            return true;
        
        for(int cont = 0; cont < this.TAMANHO_MAXIMO; cont++)
        {
            if(this.operador[cont].equals(operador1))
                linha = cont;
            
            if(this.operador[cont].equals(operador2))
                coluna = cont;
        }
        if(linha == -1 || coluna == -1)
            throw new Exception("operador invalido");
        
        return this.tabela[linha][coluna];
    }
    
    private void carregaValores()
    {
        this.operador[0] = "(";
        this.operador[1] = "^";
        this.operador[2] = "*";
        this.operador[3] = "/";
        this.operador[4] = "+";
        this.operador[5] = "-";
        this.operador[6] = ")";  
        
        for (int l = 0; l <= this.TAMANHO_MAXIMO; l++){
            for (int c = 0; c < this.TAMANHO_MAXIMO; c++){
                if(l == 1 && c == 1)
                    this.tabela[l][c] = true;
                
                if(c == 2 && l > 0 && l <= 3)
                    this.tabela[l][c] = true;
                
                if(c == 3 && l > 0 && l <= 3)
                    this.tabela[l][c] = true;
                
                if(c == 4 && l > 0 && l <= 5)
                    this.tabela[l][c] = true;
                
                if(c == 5 && l > 0 && l <= 5)
                    this.tabela[l][c] = true;
                
                if(c == 6 && l == 0 && l <= 5)
                    this.tabela[l][c] = true;
            }
        }
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

        Tabela t = (Tabela)obj;

        if (this.operador.length != t.operador.length)
            return false;

        for (int i=0; i <= this.operador.length; i++)
            if (!this.operador[i].equals(t.operador[i]))
                return false;
        return true;
    }

    public int hashCode ()
    {
        int ret = 897;

        for (int i=0; i <= this.operador.length; i++)
            ret = 7*ret + this.operador[i].hashCode();
        
        for (int l=0; l <= this.operador.length; l++)
        {
            for (int c=0; c <= this.operador.length; c++)
            {
                ret = 7*ret + new Boolean(this.tabela[l][c]).hashCode();
            }
        }

        return ret;
    }
    
}
