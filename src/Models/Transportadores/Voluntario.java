package Models.Transportadores;

import Excepitions.ValorErradoException;
import Models.Utilizador.GPS;
import Models.Sistema.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Voluntario implements Serializable, I_Transportadores {

    /**
     * Variáveis Instância
     */
    private String codVoluntario;
    private String nomeVoluntario;
    private GPS cord;
    private double raio;
    private double velocidadeMedia;
    private double classificacoes;
    private double numAvaliacoes;

    private boolean disponivel;

    private String encomendaAtual;

    private Map<String, PedidoVoluntario> historico;

    /**
     * Construtor por omissão de Voluntario.
     */
    public Voluntario(){
        this.codVoluntario = "NotDefine";
        this.nomeVoluntario = "NotDefine";
        this.cord = new GPS();
        this.raio = 0;
        this.disponivel = true;
        this.velocidadeMedia = 0.0;

        this.classificacoes = 0.0;
        this.numAvaliacoes = 0.0;

        this.historico = new HashMap<>();

        this.encomendaAtual = "None";
    }

    /**
     * Construtor parametrizado de Voluntario.
     * Aceita como parâmetros o nome, coordenadas GPS, raio de açao do voluntario.
     */
    public Voluntario(String codVoluntario, String nomeVoluntario, GPS cord, double raio){
        this.codVoluntario = codVoluntario;
        this.nomeVoluntario = nomeVoluntario;
        this.cord = cord.clone();
        this.raio = raio;
        this.disponivel = true;
        this.velocidadeMedia = 50;
        this.classificacoes = 0;
        this.numAvaliacoes = 0;
        this.historico = new HashMap<>();

        this.encomendaAtual = "None";
    }

    /**
     * Construtor de cópia de Voluntario.
     * Aceita como parâmetro outro Voluntario e utiliza os métodos
     * de acesso aos valores das variáveis de instância.
     */
    public Voluntario(Voluntario voluntario){
        this.codVoluntario = voluntario.getCodigo();
        this.nomeVoluntario = voluntario.getNomeVoluntario();
        this.cord = voluntario.getGPS();
        this.raio = voluntario.getRaio();
        this.disponivel = voluntario.getDisponivel();
        this.velocidadeMedia = voluntario.getVelocidadeMedia();
        this.classificacoes = voluntario.getClassificacoes();
        this.numAvaliacoes = voluntario.getNumAvaliacoes();
        this.historico = voluntario.getHistorico();

        this.encomendaAtual = voluntario.getEncomendaAtual();
    }

    public String getEncomendaAtual() {
        return this.encomendaAtual;
    }

    public void setEncomendaAtual(String encomendaAtual) {
        this.encomendaAtual = encomendaAtual;
    }

    /**
     * Devolve o nome do Voluntario.
     *
     * @return nome do Voluntario.
     */
    public String getNomeVoluntario(){
        return this.nomeVoluntario;
    }

    /**
     * Devolve as cordenadas GPS do Voluntario.
     *
     * @return as cordenadas GPS do Voluntario.
     */
    public GPS getGPS(){
        return this.cord.clone();
    }

    /**
     * Devolve o raio de operaçao do Voluntario.
     *
     * @return o raio de operaçao do Voluntario.
     */
    public double getRaio(){
        return this.raio;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return valor disponivel
     */
    public boolean getDisponivel() {
        return this.disponivel;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return valor disponivel
     */
    public double getVelocidadeMedia() {
        return this.velocidadeMedia;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return valor disponivel
     */
    public double getClassificacoes() {
        return classificacoes;
    }

    /**
     * Devolve valor referente ao numAvaliacoes
     *
     * @return double numAvaliacoes
     */
    public double getNumAvaliacoes() {
        return numAvaliacoes;
    }

    /**
     * Devolve valor referente ao historico
     *
     * @return Map<LocalDateTime, PedidoVoluntario> disponivel
     */
    public Map<String, PedidoVoluntario> getHistorico() {
        Map<String, PedidoVoluntario> res = new HashMap<>();

        for(Map.Entry<String, PedidoVoluntario> e: this.historico.entrySet()){
            res.put(e.getKey(), e.getValue().clone());
        }
        return res;
    }

    /**
     * Atualiza Código da Empresa
     *
     * @param codigo codEmpresa
     */
    public void setCodVoluntario(String codigo) {
        this.codVoluntario = codigo;
    }

    /**
     * Atualiza Nome da Empresa
     *
     * @param nome nomeEmpresa
     */
    public void setNomeVoluntario(String nome) {
        this.nomeVoluntario = nome;
    }

    /**
     * Atualiza GPS
     *
     * @param gps gps
     */
    public void setGPS(GPS gps) {
        this.cord = gps.clone();
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
     * Atualiza disponivel
     *
     * @param disponivel disponivel
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Funcao Exigida pela Interface, que nao se aplica a voluntarios;
     * @param preco preco a ignorar
     */
    public void setPrecoPorKm(double preco){
        ;
    }

    /**
     * Retorna o preco medio de um voluntario
     * @return 0 que e o preco medio de um voluntario;
     */
    public double getPrecoPorKm(){
        return 0;
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

    public void setHistorico(Map<String, PedidoVoluntario> historico) {
        this.historico = new HashMap<>();

        for(Map.Entry<String, PedidoVoluntario> e: historico.entrySet()){
            this.historico.put(e.getKey(), e.getValue().clone());
        }
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
     * Permite apresentar no ecrã as informações necessárias
     *
     * @return String com infotmação da Classe
     */
    public String toString(){
        return this.codVoluntario +
               this.nomeVoluntario +
               this.cord.toString() +
               this.raio +
               this.disponivel +
               this.velocidadeMedia +
               this.classificacoes +
               this.numAvaliacoes +
               this.historico.toString();
    }

    /**
     * Verifica se 2 Objects são iguais
     *
     * @return true se Object for igual à Voluntario
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Voluntario t = (Voluntario) o;

        return (this.codVoluntario.equals(t.getCodigo()) &&
                this.nomeVoluntario.equals(t.getNomeVoluntario()) &&
                this.cord.equals(t.getGPS()) &&
                this.raio == t.getRaio() &&
                this.disponivel == t.getDisponivel() &&
                this.velocidadeMedia == t.getVelocidadeMedia() &&
                this.classificacoes == t.getClassificacoes() &&
                this.numAvaliacoes == t.getNumAvaliacoes() &&
                this.historico.equals(t.getHistorico()));
    }

    /**
     * Cria cópia da Voluntario
     *
     * @return Voluntario correspondente à sua Cópia
     */
    public abstract Voluntario clone();

    public abstract boolean aceitoTransporteMedicamentos();

    public List<String> getListaHistorico(){
        return this.historico.values().parallelStream().sorted((p1,p2)->(p1.getDataSubmissaoResposta().compareTo(p2.getDataSubmissaoResposta())==0)?1:p1.getDataSubmissaoResposta().compareTo(p2.getDataSubmissaoResposta()))
                   .map(PedidoVoluntario::toString).collect(Collectors.toList());
    }

    public PedidoCompleto finalizaEntrega(){
        PedidoVoluntario p = this.historico.get(this.encomendaAtual);
        this.disponivel = true;
        this.encomendaAtual = "None";
        return new PedidoCompleto(p);
    }

    public void mudaEstado(){
        this.disponivel = !this.disponivel;
    }

    public I_PedidosTransportadores aceitarEncomenda(){
        this.disponivel = false;
        return null;
    }

    public PedidoLoja rejeitarEncomenda(){
        return null;
    }

    public void ocupaVoluntario(PedidoLoja p){
        this.encomendaAtual = p.getCodigoPedido();
        this.disponivel = false;
        double km = this.cord.distancia(p.getGpsLoja()) + p.getGpsLoja().distancia(p.getGpsUtilizador());
        PedidoVoluntario v = new PedidoVoluntario(p,this.codVoluntario,this.nomeVoluntario,LocalDateTime.now(),LocalDateTime.now(),true,this.classificacoes,km);
        this.historico.put(v.getCodigoPedido(),v.clone());
    }

    public boolean aceitaCaracteristicasEncomenda(PedidoLoja p){

        if(!this.disponivel) return false;

        if(!this.encomendaAtual.equals("None")) return false;

        if(this.historico.get(p.getCodigoPedido())!=null) return false;

        GPS loja = p.getGpsLoja();
        if(this.cord.distancia(loja)>this.raio) return false;

        GPS utilizador = p.getGpsUtilizador();
        if(this.cord.distancia(utilizador)>this.raio) return false;

        return true;
    }

    public void avaliaTransportador(int aval) throws ValorErradoException {
        if(aval<0 || aval>5) throw new ValorErradoException("Valor invalido.");
        this.numAvaliacoes++;
        this.classificacoes = ((this.classificacoes+aval)/this.numAvaliacoes);
    }

    //Ja existe esta funçao mas tem outro nome da interface e para mudar o nome é muito trabalhoso
    //Se tivermos tempo temos de mudar isso se nao esta otimo XD
    public String getCodigo(){
        return this.codVoluntario;
    }

    public double totalFaturado(){
        return 0.0;
    }
}
