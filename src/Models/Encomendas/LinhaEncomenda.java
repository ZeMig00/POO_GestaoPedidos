package Models.Encomendas;

import java.io.Serializable;


public class LinhaEncomenda implements Serializable {

    private String codProduto;
    private String descricao;
    private double quantidade;
    private double valor;
    
    /**
    * Construtores da clase LinhaEncomenda.
    */
    
    /**
    * Construtor por omissao da classe LinhaEncomenda
    */
    public LinhaEncomenda() {
        this.codProduto = "";
        this.descricao = "";
        this.quantidade = 0;
        this.valor= 0;        
    }
    
    
    /**
    * Construtor parametrizado da clase LinhaEncomenda.
    * Aceita como parametros o valor de cada parametro.
    */
    public LinhaEncomenda(String codProduto,String descricao,double quantidade,double valor) {
        this.codProduto = codProduto;
        this.descricao = descricao;
        this.quantidade =quantidade;
        this.valor=valor;        
    } 
    
    /**
    * Construtor de copia de LinhaEncomenda.
    * Aceita como parametro outra LinhaEncomenda e utiliza os metodos
    * de acesso aos valores das valiaveis
    */
    public LinhaEncomenda(LinhaEncomenda l) {
        this.codProduto = l.getCodProduto();
        this.descricao = l.getDescricao();
        this.quantidade = l.getQuantidade();
        this.valor= l.getValor();        
    }
    
    /**
    * Devolve a string correspondente ao codigo de produto.
    * 
    * @return String do codigo de produto.
    */
    public String getCodProduto(){
        return this.codProduto;
    }
    
    /**
     * Devolve a String correspondente a descricao do produto.
     * 
     * @return String com a descricao.
     */
    public String getDescricao(){
        return this.descricao;
    }
    
    /**
     * Devolve a quantidade do produto.
     * 
     *@return int com a quantidade. 
     */
    public double getQuantidade(){
        return this.quantidade;
    }
    
    /**
     * Devolve o valor do produto.
     * 
     *@return double com o valor. 
     */
    public double getValor(){
        return this.valor;
    }
    
    /**
    * Atualiza o codigo do produto.
    * 
    * @param codProduto novo codigo de produto. 
    */
    public void setCodProduto(String codProduto){
        this.codProduto=codProduto; 
    }
    
    /**
    * Atualiza a descricao.
    * 
    * @param descricao nova descricao. 
    */
    public void setDescricao(String descricao){
        this.descricao=descricao; 
    }
    
    /**
    * Atualiza a quantidade de produto.
    * 
    * @param quantidade nova quantidade. 
    */
    public void setQuantidade(double quantidade){
        this.quantidade=quantidade; 
    }
    
    /**
    * Atualiza o valor do produto.
    * 
    * @param valor novo valor. 
    */
    public void setValor(double valor){
        this.valor=valor; 
    }

    public boolean equals(Object obj) {
        if(obj==this) return true;
        if(obj==null || obj.getClass() != this.getClass()) return false;
        LinhaEncomenda l = (LinhaEncomenda) obj;
        return (l.getCodProduto().equals(this.codProduto) &&
              l.getDescricao().equals(this.descricao) &&
              l.getQuantidade()==this.quantidade &&
              l.getValor()==this.valor);
    }
    
     /**
    * Método que devolve a representação em String de LinhaEncomenda.
    * 
    * @return String com todos os parametros de LinhaEncomenda. 
    */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.codProduto );
        sb.append(",").append(this.descricao);
        sb.append(",").append(this.quantidade);
        sb.append(",").append(this.valor);
        return sb.toString();
    }
    
    /**
   * Método que faz uma cópia do objecto receptor da mensagem.
   * Para tal invoca o construtor de cópia.
   * 
   * @return objecto clone do objecto que recebe a mensagem.
   */
    public LinhaEncomenda clone(){
        return new LinhaEncomenda(this);
    }

    public boolean contemMedicamento(String medicamento){
        return this.codProduto.equals(medicamento);
    }
}

/*
@startuml
class LinhaEncomenda{
--codProduto : String
--descricao : String
--quantidade : double
--valor : double


++LinhaEncomenda()
++LinhaEncomenda(codProduto : String, descricao : String, quantidade : int, valor: double)
++LinhaEncomenda(l : LinhaEncomenda)

++getCodProduto() : String
++getDescricao() : String
++getQuantidade() : double
++getValor() : double
++setCodProduto(codProduto : String) : void
++setDescricao(descricao : String) : void
++setQuantidade(quantidade : double) : void
++setValor(valor : double) : void
++equals(obj : Object) : boolean
++toString() : String
++clone() : LinhaEncomenda
}
@enduml
*/
