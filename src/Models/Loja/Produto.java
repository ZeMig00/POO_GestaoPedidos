package Models.Loja;

import java.io.Serializable;

public class Produto implements Serializable {
    private String codigoProduto;
    private String nomeProduto;

    private double peso;
    private double preco;

    private boolean isMedicamento;

    public Produto() {
        this.codigoProduto = "";
        this.nomeProduto = "";
        this.peso = 0;
        this.preco = 0;
        this.isMedicamento = false;
    }

    public Produto(String codigoProduto, String nomeProduto, double peso, double preco, boolean isMedicamento) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.peso = peso;
        this.preco = preco;
        this.isMedicamento = isMedicamento;
    }

    public Produto(Produto p) {
        this.codigoProduto = p.getCodigoProduto();
        this.nomeProduto = p.getNomeProduto();
        this.peso = p.getPeso();
        this.preco = p.getPreco();
        this.isMedicamento = p.isMedicamento();
    }

    /**
     * Funcao que devolve o codigo do produto
     * @return codigo do produto
     */
    public String getCodigoProduto() {
        return this.codigoProduto;
    }

    /**
     * Funcao que atualiza o codigo do produto
     * @param codigoProduto novo codigo do produto
     */
    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    /**
     * Funcao que devolve o nome do produto
     * @return nome do produto
     */
    public String getNomeProduto() {
        return this.nomeProduto;
    }

    /**
     * Funcao que atualiza o nome do produto
     * @param nomeProduto novo nome do produto
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * Funcao que devolve o peso do produto
     * @return peso do produto
     */
    public double getPeso() {
        return this.peso;
    }

    /**
     * Funcao que atualiza o peso do produto
     * @param peso novo peso do produto
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * funcao que devolve o preco do produto
     * @return preço do produto
     */
    public double getPreco() {
        return this.preco;
    }

    /**
     * funcao que atualiza o preco do produto
     * @param preco novo preço do produto
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Funcao que diz se o produto é um medicamento
     * @return boolean com o facto de ser ou nao medicamento
     */
    public boolean isMedicamento() {
        return this.isMedicamento;
    }

    /**
     * Funcao que atualiza o facto do produto ser ou nao medicamento
     * @param medicamento nova veracidade sobre o produto ser ou nao mediacemnto
     */
    public void setMedicamento(boolean medicamento) {
        this.isMedicamento = medicamento;
    }

    /**
     * Funcao que transforma o produto numa string
     * @return representação em string do produto
     */
    public String toString() {
        return this.nomeProduto+" "+this.preco+" "+this.codigoProduto+" "+this.peso;
    }

    /**
     * Funçao que clona um produto
     * @return clone do produto
     */
    public Produto clone(){
        return new Produto(this);
    }
}
