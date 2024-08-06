package Models.Loja;

import Excepitions.ProdutoNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stock implements Serializable {

    private Map<String,Produto> stock;

    public Stock() {
        this.stock = new HashMap<>();
    }

    public Stock(Map<String, Produto> stock) {
        this.setStock(stock);
    }

    public Stock(Stock s){
        this.stock = s.getStock();
    }

    public Map<String, Produto> getStock() {
        HashMap<String,Produto> res = new HashMap<>();
        for (Map.Entry<String,Produto> s : this.stock.entrySet()){
            res.put(s.getKey(),s.getValue().clone());
        }

        return res;
    }

    public void setStock(Map<String, Produto> stock) {
        this.stock = new HashMap<>();
        for (Map.Entry<String,Produto> s : stock.entrySet()){
            this.stock.put(s.getKey(),s.getValue().clone());
        }
    }

    public Produto getProduto(String codProduto) throws ProdutoNotFoundException{
        try {
            return this.stock.get(codProduto).clone();
        }catch (NullPointerException e){
            throw new ProdutoNotFoundException("Produto inexistente");
        }
    }

    public void addStock(Produto p){
        this.stock.put(p.getCodigoProduto(),p.clone());
    }

    public String removeProduto(String cod){
        String s = "";
        if(this.stock.containsKey(cod)) {
            this.stock.remove(cod);
            s = "Produto removido do Stock com sucesso.";

        }else {
            s = "Produto não existe em Stock.";
        }

        return s;
    }

    public int tamStock(){
        return this.stock.size();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        int i = 1;

        List<Produto> medicamentos = this.stock.values().parallelStream().filter(Produto::isMedicamento).collect(Collectors.toList());
        List<Produto> produtos = this.stock.values().parallelStream().filter(p->!p.isMedicamento()).collect(Collectors.toList());

        if(!medicamentos.isEmpty()){
            sb.append("Medicamentos:\n");
            for (Produto m : medicamentos) {
                sb.append(i + "º -> ").append(m.getCodigoProduto() + " | ").append(m.toString() + "\n");
                i++;
            }

            i=1;
            sb.append("\n");
        }

        if(!produtos.isEmpty()){
            sb.append("Produtos:\n");
            for (Produto p : produtos) {
                sb.append(i + "º -> ").append(p.getCodigoProduto() + " | ").append(p.toString() + "\n");
                i++;
            }
        }

        if(produtos.isEmpty() && medicamentos.isEmpty()){
            sb.append("Não existem produtos disponiveis na loja\n");
        }

        return sb.toString();
    }

    public List<Produto> getMedicamentos(){
        return this.stock.values().stream().filter(Produto::isMedicamento).map(Produto::clone).collect(Collectors.toList());
    }

    public Stock clone(){
        return new Stock(this);
    }

    public List<Produto> getListaProdutos(){
        return this.stock.values().stream().map(Produto::clone).collect(Collectors.toList());
    }
}

