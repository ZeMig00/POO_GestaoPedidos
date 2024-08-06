package Models.Transportadores;
import Excepitions.ValorErradoException;
import Models.Encomendas.LinhaEncomenda;
import Models.Sistema.I_PedidosTransportadores;
import Models.Sistema.PedidoCompleto;
import Models.Sistema.PedidoLoja;
import Models.Sistema.PedidoTransportadora;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Transportadora implements Serializable, I_Transportadores {

    /**
     * Variáveis Instância
     */
    private String codEmpresa;
    private String nomeEmpresa;
    private GPS gps;
    private String nif;
    private double raio;
    private double precoPorKm;
    private boolean disponivel;
    private double velocidadeMedia;

    private double classificacoes;
    private double numAvaliacoes;

    private Map<String, PedidoTransportadora> historico;

    private String codEncomendaAtual;

    /**
     * Construtor por omissão de Transportadora
     */
    public Transportadora() {
        this.codEmpresa = "";
        this.nomeEmpresa = "";
        this.gps = new GPS();
        this.nif = "";
        this.raio = 0;
        this.precoPorKm = 0;
        this.disponivel = true;
        this.velocidadeMedia = 0.0;
        this.classificacoes = 0.0;
        this.numAvaliacoes = 0;
        this.historico = new HashMap<>();
        this.codEncomendaAtual = "None";
    }

    /**
     * Construtor Parametrizado de Transportadora
     * Aceita como parâmetros os valores para cada Variável de Instância
     */
    public Transportadora(String a, String b, GPS g, String c, double r, double p) {
        this.codEmpresa = a;
        this.nomeEmpresa = b;
        this.gps = g.clone();
        this.nif = c;
        this.raio = r;
        this.precoPorKm = p;

        this.disponivel = true;
        this.velocidadeMedia = 50;

        this.classificacoes = 0;
        this.numAvaliacoes = 0;

        this.historico = new HashMap<>();

        this.codEncomendaAtual = "None";
    }

    /**
     * Construtor de cópia de uma Transportadora
     * Aceita como parâmetros outra Transportadora e utiliza os métodos
     * de acesso aos valores das Variáveis de Instância
     */
    public Transportadora(Transportadora t) {
        this.codEmpresa = t.getCodigo();
        this.nomeEmpresa = t.getNomeEmpresa();
        this.gps = t.getGPS();
        this.nif = t.getNIF();
        this.raio = t.getRaio();
        this.precoPorKm = t.getPrecoPorKm();

        this.disponivel = t.getDisponivel();
        this.velocidadeMedia = t.getVelocidadeMedia();

        this.classificacoes = t.getClassificacoes();
        this.numAvaliacoes = t.getNumAvaliacoes();

        this.historico = t.getHistorico();
        this.codEncomendaAtual = t.getCodEncomendaAtual();
    }

    /**
     * Devolve valor referente ao Nome Empresa
     *
     * @return String nomeEmpresa
     */
    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

    /**
     * Devolve valor referente ao GPS
     *
     * @return GPS gps
     */
    public GPS getGPS() {
        return this.gps.clone();
    }

    /**
     * Devolve valor referente ao NIF
     *
     * @return String nif
     */
    public String getNIF() {
        return this.nif;
    }

    /**
     * Devolve valor referente ao Raio
     *
     * @return double raio
     */
    public double getRaio() {
        return this.raio;
    }

    /**
     * Devolve valor referente ao PrecoPorKm
     *
     * @return double precoPorKm
     */
    public double getPrecoPorKm() {
        return this.precoPorKm;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return double disponivel
     */
    public boolean getDisponivel() {
        return this.disponivel;
    }

    /**
     * Devolve valor referente a Velocidade Media
     *
     * @return double velocidadeMedia
     */
    public double getVelocidadeMedia() {
        return this.velocidadeMedia;
    }

    /**
     * Devolve valor referente à classificacao
     *
     * @return double classificacao
     */

    public double getClassificacoes() {
        return classificacoes;
    }

    public void setClassificacoes(double classificacoes) {
        this.classificacoes = classificacoes;
    }

    public double getNumAvaliacoes() {
        return numAvaliacoes;
    }

    public void setNumAvaliacoes(double numAvaliacoes) {
        this.numAvaliacoes = numAvaliacoes;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return valor disponivel
     */
    public Map<String, PedidoTransportadora> getHistorico() {
        Map<String, PedidoTransportadora> res = new HashMap<>();
        for(Map.Entry<String, PedidoTransportadora> e: this.historico.entrySet()){
            res.put(e.getKey(), e.getValue().clone());
        }
        return res;
    }

    /**
     * Devolve valor referente ao PedidoLoja
     *
     * @return PedidoLoja disponivel
     */
    public PedidoTransportadora getPedidoTransportadora() {
        return this.historico.get(this.codEncomendaAtual);
    }

    /**
     * Devolve valor referente ao codProd
     *
     * @return String disponivel
     */
    public String getCodEncomendaAtual() {
        return this.codEncomendaAtual;
    }

    /**
     * Atualiza Código da Empresa
     *
     * @param codigo codEmpresa
     */
    public void setCodEmpresa(String codigo) {
        this.codEmpresa = codigo;
    }

    /**
     * Atualiza Nome da Empresa
     *
     * @param nome nomeEmpresa
     */
    public void setNomeEmpresa(String nome) {
        this.nomeEmpresa = nome;
    }

    /**
     * Atualiza GPS
     *
     * @param gps gps
     */
    public void setGPS(GPS gps) {
        this.gps = gps.clone();
    }

    /**
     * Atualiza NIF da Empresa
     *
     * @param nif nif
     */
    public void setNIF(String nif) {
        this.nif = nif;
    }

    /**
     * Atualiza Raio
     *
     * @param raio raio
     */
    public void setRaio(double raio) {
        this.raio = raio;
    }

    /**
     * Atualiza PrecoPorKm
     *
     * @param preco precoPorKm
     */
    public void setPrecoPorKm(double preco) {
        this.precoPorKm = preco;
    }

    /**
     * Atualiza disponivel
     *
     * @param disponivel disponivel
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Atualiza velocidadeMedia
     *
     * @param velocidadeMedia disponivel
     */
    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    /**
     * Atualiza disponivel
     *
     * @param historico disponivel
     */

    public void setHistorico(Map<String, PedidoTransportadora> historico) {
        this.historico = new HashMap<>();
        for(Map.Entry<String, PedidoTransportadora> e: historico.entrySet()){
            this.historico.put(e.getKey(), e.getValue().clone());
        }
    }

    /**
     * Atualiza disponivel
     *
     * @param p disponivel
     */

    public void setCodEncomendaAtual(String p) {
        this.codEncomendaAtual = p;
    }

    /**
     * Verifica se Transportadora está disponível para Transportar Encomenda
     *
     * @return true se estiver disponível
     */
    public boolean aceitaPedido(){
        return this.disponivel;
    }

    /**
     * Verifica se Transportadora está disponível para Transportar Encomenda
     *
     * @return true se estiver disponível
     */
    public boolean alteraEstado(){
        return !this.disponivel;
    }

    /**
     * Calcula Preco de Transporte
     *
     * @return double correspondente a esse valor
     */
    public abstract double precoTransporte(PedidoLoja p);

    /**
     * Calcula Tempo de Transporte
     *
     * @return double correspondente a esse valor
     */
    public abstract double tempoTransporte(PedidoLoja p);

    /**
     * Permite apresentar no ecrã as informações necessárias
     *
     * @return String com infotmação da Classe
     */
    public String toString(){
        return this.codEmpresa +
               this.nomeEmpresa +
               this.gps.toString() +
               this.nif +
               this.raio +
               this.precoPorKm +
               this.disponivel +
               this.velocidadeMedia +
               this.classificacoes +
               this.numAvaliacoes +
               this.historico.toString();
    }

    /**
     * Verifica se 2 Objects são iguais
     *
     * @return true se Object for igual à Transportadora
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Transportadora t = (Transportadora)o;

        return (this.codEmpresa.equals(t.getCodigo()) &&
                this.nomeEmpresa.equals(t.getNomeEmpresa()) &&
                this.gps.equals(t.getGPS()) &&
                this.nif.equals(t.getNIF()) &&
                this.raio == t.getRaio() &&
                this.precoPorKm == t.getPrecoPorKm() &&
                this.disponivel == t.getDisponivel() &&
                this.velocidadeMedia == t.getVelocidadeMedia() &&
                this.classificacoes == t.getClassificacoes() &&
                this.numAvaliacoes == t.getNumAvaliacoes() &&
                this.historico.equals(t.getHistorico()) &&
                this.codEncomendaAtual.equals(t.getCodEncomendaAtual()));
    }

    /**
     * Cria cópia da Transportadora
     *
     * @return Transportadora correspondente à sua Cópia
     */
    public abstract Transportadora clone();

    public abstract boolean aceitoTransporteMedicamentos();

    public List<String> getListaHistorico(){
        return this.historico.values().parallelStream().sorted((p1,p2)->(p1.getDataSubmissaoResposta().compareTo(p2.getDataSubmissaoResposta())==0)?1:p1.getDataSubmissaoResposta().compareTo(p2.getDataSubmissaoResposta()))
                   .map(PedidoTransportadora::toString).collect(Collectors.toList());
    }

    public PedidoCompleto finalizaEntrega(){
        PedidoTransportadora p = this.historico.get(this.codEncomendaAtual).clone();
        this.disponivel = true;
        this.codEncomendaAtual = "None";
        return new PedidoCompleto(p);
    }

    public void mudaEstado(){
       this.disponivel = !this.disponivel;
    }

    public abstract I_PedidosTransportadores ocupaTransportadora(PedidoLoja p);

    public abstract boolean aceitaCaracteristicasEncomenda(PedidoLoja p);

    public boolean superAceitaCaracteristicasEncomenda(PedidoLoja p){

        if(!this.disponivel) return false;

        if(!this.codEncomendaAtual.equals("None")) return false;

        if(this.historico.get(p.getCodigoPedido())!=null) return false;

        GPS loja = p.getGpsLoja();
        if(this.gps.distancia(loja)>this.raio) return false;

        GPS utilizador = p.getGpsUtilizador();
        if(this.gps.distancia(utilizador)>this.raio) return false;

        return true;
    }

    public void avaliaTransportador(int aval)throws ValorErradoException {
        if(aval<0 || aval>5) throw new ValorErradoException("Valor invalido.");

        this.numAvaliacoes++;
        this.classificacoes = ((this.classificacoes+aval)/this.numAvaliacoes);
    }

    /**
     * Funcao responsavel por devolver o codigo da empresa
     * @return codigo da empresa
     */
    public String getCodigo(){
        return this.codEmpresa;
    }

    public double totalFaturado(){
        double fat = 0.0;

        for (PedidoTransportadora p : this.historico.values()){
            fat += p.getEncomenda().getLinhasEncomenda().parallelStream().mapToDouble(LinhaEncomenda::getValor).sum();
        }
        return fat;
    }

    public I_PedidosTransportadores aceitarEncomenda(){
        PedidoTransportadora p = this.historico.get(this.codEncomendaAtual);
        p.setAceite(true);
        this.disponivel = false;
        return p.clone();
    }

    public PedidoLoja rejeitarEncomenda(){
        PedidoTransportadora p = this.historico.get(this.codEncomendaAtual);
        this.codEncomendaAtual = "None";

        return p.getPedidoLoja().clone();
    }

    public void adicionaHistorico(PedidoTransportadora p){
        this.historico.put(p.getCodigoPedido(),p.clone());
    }
}
