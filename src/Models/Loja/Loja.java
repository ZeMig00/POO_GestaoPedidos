package Models.Loja;

import Excepitions.EncomendaNotFoundException;
import Excepitions.ProdutoNotFoundException;
import Models.Encomendas.LinhaEncomenda;
import Models.Utilizador.GPS;
import Models.Sistema.I_PedidosTransportadores;
import Models.Sistema.PedidoLoja;
import Models.Sistema.PedidoUtilizador;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Loja implements Serializable {

    private String codigoLoja;
    private String nomeLoja;
    private GPS cord;

    private Stock stockLoja;
    private List<PedidoUtilizador> listaEspera;

    private Map<String, I_PedidosTransportadores> historico;

    public Loja(){
        this.codigoLoja = "";
        this.nomeLoja = "";
        this.cord = new GPS(0,0);

        this.stockLoja = new Stock();
        this.listaEspera = new ArrayList<>();
        this.historico = new HashMap<>();
    }

    public Loja(String codLoja, String nomeLoja, GPS gps){
        this.codigoLoja = codLoja;
        this.nomeLoja = nomeLoja;
        this.cord = gps.clone();

        this.stockLoja = new Stock();
        this.listaEspera = new ArrayList<>();
        this.historico = new HashMap<>();
    }

    public Loja(Loja l){
        this.codigoLoja = l.getCodigoLoja();
        this.nomeLoja = l.getNomeLoja();
        this.cord = l.getCord();

        this.stockLoja = l.getStockLoja();
        this.listaEspera = l.getListaEspera();
        this.historico = l.getHistorico();
    }

    /**
     * Funçao que retorna o codigo da loja
     * @return codigo da loja
     */
    public String getCodigoLoja() {
        return codigoLoja;
    }

    /**
     * Funçao que atualisa o codigo da loja
     * @param codigoLoja novo codigo para esta loja.
     */
    public void setCodigoLoja(String codigoLoja) {
        this.codigoLoja = codigoLoja;
    }

    /**
     * Funçao que dá o nome da loja
     * @return nome da loja.
     */
    public String getNomeLoja() {
        return nomeLoja;
    }

    /**
     * Funçao que atualiza o nome da loja
     * @param nomeLoja novo nome para a loja
     */
    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    /**
     * Funçao que devolve uma copia das coredenadas da loja
     * @return coordenadas da loja
     */
    public GPS getCord() {
        return cord.clone();
    }

    /**
     * Função que atualiza as coordenadas da loja
     * @param cord novas coordenadas pra a loja
     */
    public void setCord(GPS cord) {
        this.cord = cord.clone();
    }

    /**
     * Funçao que retorna uma copia do stock da loja
     * @return stock da loja
     */
    public Stock getStockLoja() {
        return stockLoja.clone();
    }

    /**
     * Função que atualiza o stock da loja
     * @param stockLoja novo stock para a loja
     */
    public void setStockLoja(Stock stockLoja) {
        this.stockLoja = stockLoja.clone();
    }

    /**
     * Função que devolve a lista dos pedidos em espera da loja
     * @return lista de pedidos em espera
     */
    public List<PedidoUtilizador> getListaEspera() {
        List<PedidoUtilizador> r = new ArrayList<>();
        for (PedidoUtilizador p : this.listaEspera){
            r.add(p.clone());
        }
        return r;
    }

    /**
     * Função que Atualiza a lista dos pedidos em espera da loja
     * @param listaEspera nova lista de pedidos em espera.
     */
    public void setListaEspera(List<PedidoUtilizador> listaEspera) {
        this.listaEspera = new ArrayList<>();

        for (PedidoUtilizador p : listaEspera){
            this.listaEspera.add(p.clone());
        }
    }

    /**
     * Funçao que devolve o historico da loja.
     * @return historico da loja.
     */
    public Map<String, I_PedidosTransportadores> getHistorico() {
        Map<String, I_PedidosTransportadores> res = new HashMap<>();

        for (Map.Entry<String, I_PedidosTransportadores> h : this.historico.entrySet()){
            res.put(h.getKey(),h.getValue().clone());
        }
        return res;
    }

    /**
     * Funçao que atualiza o historico da loja.
     * @param historico novo historico da loja.
     */
    public void setHistorico(Map<String, I_PedidosTransportadores> historico) {
        this.historico = new HashMap<>();

        for (Map.Entry<String, I_PedidosTransportadores> h : historico.entrySet()){
            this.historico.put(h.getKey(),h.getValue().clone());
        }
    }

    /**
     * Funçao que devolve o pedido feito pela loja baseado no pedido de utilizador
     * Funçao que vai devolver um pedido de entrega de uma encomenda que tem disponivel
     * @param p encomenda que pretende declarar como pronta para entregar
     * @return pedido da loja para alguem ir entregar essa encomenda.
     */
    public PedidoLoja pedeEntregaEncomenda(PedidoUtilizador p){
        boolean temMedicamentos = false;
        List<Produto> medicamentosLoja = this.stockLoja.getMedicamentos();
        Iterator<Produto> it = medicamentosLoja.iterator();

        while (!temMedicamentos && it.hasNext()){
            Produto prod = it.next();
            temMedicamentos = p.getEncomenda().getLinhasEncomenda().stream().anyMatch(l->l.contemMedicamento(prod.getCodigoProduto()));
        }

        return new PedidoLoja(p, LocalDateTime.now(),this.codigoLoja,this.cord.clone(),temMedicamentos,"None");
    }

    /**
     * Funçao que devolve esta loja em formato String
     * @return String que representa a loja
     */
    public String toString(){
        return this.codigoLoja+" "+this.nomeLoja;
    }

    /**
     * Funçao que replica esta loja
     * @return copia da loja
     */
    public Loja clone(){
        return new Loja(this);
    }

    /**
     * Funçao que retorna a lista dos produtos que estão em stock
     * @return lista dos produtos em stock
     */
    public List<Produto> getProdutos(){
        return this.stockLoja.getListaProdutos();
    }

    /**
     * Funçao que adiciona um pedido de um utilizador a lista de espera
     * @param p pedido a adicionar.
     */
    public void addPedidoUtilizador(PedidoUtilizador p){
        this.listaEspera.add(p.clone());
    }

    /**
     * Funçao que retorna o clone do produto caso exista
     * @param codProd codigo de produto a obter
     * @return produto pedido
     * @throws ProdutoNotFoundException caso o produto nao exista
     */
    public Produto getProduto(String codProd) throws ProdutoNotFoundException{
        return this.stockLoja.getProduto(codProd);
    }

    /**
     * Funçao que aciciona um produto ao stock
     * @param p produto a adicionar
     */
    public void adicionaProdutoStock(Produto p){
        this.stockLoja.addStock(p.clone());
    }

    /**
     * Funcao que remove um produto do stock
     * @param codProd codigo do produto a remover
     * @throws ProdutoNotFoundException caso o produto nao exista
     */
    public void removeProdutoStock(String codProd) throws ProdutoNotFoundException {
        try {
            this.stockLoja.removeProduto(codProd);
        }catch (NullPointerException e){
            throw new ProdutoNotFoundException();
        }
    }

    /**
     * Funçã oque retorna a lista do historico da loja
     * @return lista do historico em string
     */
    public List<String> getListaHistorico(){
        List<String> l = new ArrayList<>();

        for (I_PedidosTransportadores p : this.historico.values()){
            l.add(p.toString());
        }
        return this.historico.values().parallelStream().sorted((p1,p2)->(p1.getDataAceite().compareTo(p2.getDataAceite())==0)?1:p1.getDataAceite().compareTo(p2.getDataAceite()))
                   .map(I_PedidosTransportadores::toString).collect(Collectors.toList());
    }

    /**
     * Funçao que verifica se um pedido do utilizador tem medicamentos
     * @param p pedido do utilizadro
     * @return boolean com o resultado do pedido ter ou nao medicamentos
     */
    public boolean temMedicamentos(PedidoUtilizador p){
        boolean temMedicamentos = false;
        List<Produto> medicamentosLoja = this.stockLoja.getMedicamentos();
        Iterator<Produto> it = medicamentosLoja.iterator();

        while (!temMedicamentos && it.hasNext()){
            Produto prod = it.next();
            temMedicamentos = p.getEncomenda().getLinhasEncomenda().stream().anyMatch(l->l.contemMedicamento(prod.getCodigoProduto()));
        }

        return temMedicamentos;
    }

    /**
     * Funçao que adiciona ao historico
     * @param p pedido que foi aceite por todas as entidades necessarias e que esta atribuido desta forma
     * a algum transportador
     */
    public void adicionaHistorico(I_PedidosTransportadores p){
        this.historico.put(p.getCodigoPedido(),p.clone());
    }

    /**
     * Funçao que devolve um pedido que estava em espera em formato de aceite pela loja
     * Funçao que vai a lista de espera e aceita um pedido devolvendo o pedido da loja
     * @param pos posiçao da lista de espera a retirar o pedido
     * @return pedido da loja para alguem vir buscar a encomenda
     * @throws EncomendaNotFoundException caso a posicao dada nao exista
     */
    public PedidoLoja getPedidoUtilizador(int pos) throws EncomendaNotFoundException {
       try {
           PedidoUtilizador p = this.listaEspera.get(pos).clone();
           boolean temMedicamentos = this.temMedicamentos(p);
           return new PedidoLoja(p,LocalDateTime.now(),this.codigoLoja,this.cord.clone(),temMedicamentos,"None");
       }catch (NullPointerException | IndexOutOfBoundsException e){
           throw new EncomendaNotFoundException();
       }
    }

    /**
     * Funçao que remove um pedido da lista de espera
     * @param pos posicao a retira o pedido
     */
    public void removeListaEspera(int pos){
        PedidoUtilizador p = this.listaEspera.get(pos).clone();
        this.listaEspera.remove(pos);
    }

    /**
     * Funcao que retorna a lista de string dos pedidos na fila de espera
     * @return lista com os pedidos em formato string dos pedidos na fila de espera
     */
    public List<String> getListPedidos(){
        return this.listaEspera.parallelStream().map(PedidoUtilizador::clone).map(PedidoUtilizador::toString).collect(Collectors.toList());
    }

    /**
     * Funçao que devolve o total faturado por uma loja
     * @return total faturado pela loja
     */
    public double totalFaturado(){
        double fat = 0.0;

        for (I_PedidosTransportadores p : this.historico.values()){
            fat += p.getEncomenda().getLinhasEncomenda().parallelStream().mapToDouble(LinhaEncomenda::getValor).sum();
        }

        return fat;
    }
}
