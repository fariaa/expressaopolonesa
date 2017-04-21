package faculdade;

import java.util.*;

public class Si {

    public static void main(String[] args) {
        
        //String teclado = "1+2*3^4/(5-6)+7";
        String caractere = "";
        String teclado = "";
        boolean valida;
        Object inicioFila = null;
        Scanner ler = new Scanner(System.in);
        try
        {    
            teclado = ler.next();   
            Pilha pilha = new Pilha(teclado.length());
            Fila fila = new Fila(teclado.length());
            StringTokenizer quebrador = new StringTokenizer(teclado,"+-*/^()", true);
            Tabela tabela = new Tabela();
            Resolvedor resolvedor = new Resolvedor();
            
            if(tabela.validaExpressao(teclado))
            {
                while(quebrador.hasMoreTokens())
                {
                    valida = true;
                    try
                    {
                        caractere = quebrador.nextToken();
                        double result = Double.parseDouble(caractere);
                        fila.insere(result);

                    }
                    catch(NumberFormatException ex)
                    {
                        if(pilha.vazia())
                        {
                            pilha.guarde(caractere);
                        }else{
                            while(valida)
                            {
                                if(pilha.vazia()){
                                    pilha.guarde(caractere);
                                    valida = false;
                                }
                                else{
                                    if(tabela.validaOperadores(pilha.recupera().toString(), caractere)){
                                        if(pilha.recupera().toString().equals("(") && caractere.equals(")"))
                                        {
                                            pilha.remove();
                                            valida = false;
                                        }else{
                                            fila.insere(pilha.remove());
                                        }
                                    }else{
                                        pilha.guarde(caractere);
                                        valida = false;
                                    }
                                }   
                                
                            }       
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Erro: " + ex.getMessage());
                    }
                    //System.out.println("O Resultado fila: " + fila.toString());
                }
                //passar o que sobrou na pilha para fila
                while(!pilha.vazia())
                {
                    fila.insere(pilha.remove());
                }
                
                while(!fila.vazia())
                {
                    inicioFila = fila.remove();
                    if(resolvedor.operadorExistente(inicioFila))
                    {
                        if(pilha.size() >= 2)
                        {
                            Object v1 = pilha.remove();
                            Object v2 = pilha.remove();
                            String i = inicioFila.toString();
                            double valor1 = Double.parseDouble(v1.toString());
                            double valor2 = Double.parseDouble(v2.toString());
                            Object teste = resolvedor.resolve(valor2, i, valor1);
                           pilha.guarde(teste);
                        }
                    }
                    else
                    {
                        pilha.guarde(inicioFila);
                    }
                }
            }
            else
            {
                System.out.println("Expressão errada, por favor verificar. ");
            }
            System.out.println("O Resultado Pilha: " + pilha.toString());
        }
        catch(Exception ex)
        {
            System.out.println("Erro: " + ex.getMessage());
        }
        
    }
}
