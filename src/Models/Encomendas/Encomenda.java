package Models.Encomendas;

import Models.Loja.Produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Encomenda implements Serializable, I_Encomendas {
   private String codEncomenda;
   private String codUtilizador;
   private String codLoja;
   private double peso;
   private List<LinhaEncomenda> linhasEncomenda;
   
   /**
    * Construtores da clase Encomenda
    */
   
   /**
    * Construtor por omissao da clase Encomenda.
    */
   public Encomenda(){
       this.codEncomenda = "";
       this.codUtilizador = "";
       this.codLoja = "";
       this.peso = 0;
       this.linhasEncomenda= new ArrayList<>();
   } 
    
    /**
     * Construtor parametrizado de Encomenda.
     * Aceita como parametros os valores de cada parametro.
     */
   public Encomenda(String codEncomenda,String codUtilizador,String codLoja, double peso, List<LinhaEncomenda> lista){
       this.codEncomenda = codEncomenda;
       this.codUtilizador = codUtilizador;
       this.codLoja = codLoja;
       this.peso = peso;
       this.linhasEncomenda = new ArrayList<>(lista);
   }

    /**
    * Construtor de copia de Encomenda.
    * Aceita como parametro outra Encomenda e utiliza os metodos
    * de acesso aos valores das valiaveis
    */
   public Encomenda(Encomenda ec){
       this.codEncomenda = ec.getCodEncomenda();
       this.codUtilizador = ec.getCodUtilizador();
       this.codLoja = ec.getCodLoja();
       this.peso = ec.getPeso();
       this.linhasEncomenda = ec.getLinhasEncomenda() ;
   }    
   
   /**
    * Devolve a string correspondente ao codigo de encomenda.
    * 
    * @return string do codigo de encomenda.
    */
   public String getCodEncomenda(){
       return this.codEncomenda;
   }
   
   /**
    * Devolve a string correspondente ao codigo do utilizador.
    * 
    * @return string do codigo do utilizador.
    */
   
   public String getCodUtilizador(){
       return this.codUtilizador;
   }
   
   /**
    * Devolve a string correspondente ao codigo da loja.
    * 
    * @return string do codigo da loja.
    */
   public String getCodLoja(){
       return this.codLoja;
   }
   
   /**
    * Devolve o valor do peso da encomenda.
    * 
    * @return valor do peso da encomenda.
    */
   public double getPeso(){
       return this.peso;
   }
   
   /**
    * Devolve a lista das linhas de encomenda.
    * 
    * @return a lista das linhas de encomenda
    */
   public List<LinhaEncomenda> getLinhasEncomenda(){
       List<LinhaEncomenda> res = new ArrayList<>();
       
       for(LinhaEncomenda l: this.linhasEncomenda){
           res.add(l.clone());
        }
       return res;
    }
   
   /**
    * Atualiza o codigo da encomenda
    * 
    * @param codEnc novo codigo de encomenda. 
    */
   public void setCodEncomenda(String codEnc){
       this.codEncomenda=codEnc;
   }
   
   /**
    * Atualiza o codigo do utilizador.
    * 
    * @param codUtilidor novo codigo do Models.Utilizador.
    */   
   public void setCodUtilizador(String codUtilidor){
       this.codUtilizador=codUtilidor;
   }
   
   /**
    * Atualiza o codigo da Models.Loja
    * 
    * @param codLoja novo codigo da Models.Loja.
    */
   public void setCodLoja(String codLoja){
       this.codLoja=codLoja;
   } 
   
   /**
    * Atualiza o valor do peso
    * 
    * @param peso novo valor do peso. 
    */
   public void setPeso(double peso){
       this.peso=peso;
   }
   
   /**
    * Atualiza a lista da encomenda.
    * 
    * @param lc nova lista da encomenda. 
    */
   public void setLinhasEncomenda(List<LinhaEncomenda> lc){
       this.linhasEncomenda = new ArrayList<>();
       for(LinhaEncomenda l: lc){
           this.linhasEncomenda.add(l.clone());
       }
    }
    
   /**
    * Metodo que adiciona uma LinhaEncomenda a lista das linhasEncomenda.
    * 
    * @param l LinhaEncomenda a adicionar.
    */
   public void addLinhaEncomenda(LinhaEncomenda l){
       this.linhasEncomenda.add(l.clone());
    } 
    
   public boolean equals(Object o){
       if (this==o)
            return true;
       
       if(o==null||o.getClass()!=this.getClass())
            return false;
            
       Encomenda ec = (Encomenda) o;
       
       return (this.codUtilizador.equals(ec.getCodUtilizador()) &&
               this.codLoja.equals(ec.getCodLoja()) && 
               this.codEncomenda.equals(ec.getCodEncomenda()) &&
               this.peso==ec.getPeso()) &&
               this.linhasEncomenda.equals(ec.getLinhasEncomenda());
   }

   public boolean contemMedicamentos(List<Produto> medicamentos){
      Iterator<Produto> it = medicamentos.iterator();
      boolean temMedicamento = false;
      while (it.hasNext() && !temMedicamento){
          Produto p = it.next();
          temMedicamento = this.linhasEncomenda.stream().map(LinhaEncomenda::getCodProduto).anyMatch(c->c.equals(p.getCodigoProduto()));
      }
       return temMedicamento;
   }
   
   /**
    * Método que devolve a representação em String de Encomenda.
    * 
    * @return String com todos os parametros de Encomenda. 
    */
    
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.codEncomenda)
          .append(",").append(this.codUtilizador)
          .append(",").append(this.codLoja)
          .append(",").append(this.peso)
          .append(",").append(this.linhasEncomenda.toString());
          
          return sb.toString();
    } 
   
   /**
   * Método que faz uma cópia do objecto receptor da mensagem.
   * Para tal invoca o construtor de cópia.
   * 
   * @return objecto clone do objecto que recebe a mensagem.
   */
   public Encomenda clone(){
       return new Encomenda(this);
   }
   
}
