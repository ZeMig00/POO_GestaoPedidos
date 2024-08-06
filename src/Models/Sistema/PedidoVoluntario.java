package Models.Sistema;

import Models.Encomendas.I_Encomendas;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoVoluntario implements Serializable, I_PedidosTransportadores {

    private String codigoPedido;

    private PedidoLoja pedidoLoja;

    //Models.Sistema Atribuiu
    private String codigoVoluntario;
    private String nomeDoVoluntario;
    private double classificacao;
    private double kmPercorridos;
    private LocalDateTime dataSubmissaoResposta;

    //Aceite Models.Voluntario
    private LocalDateTime dataAceite;
    private boolean aceite;

    public PedidoVoluntario(PedidoLoja p, String codigoVoluntario, String nomeDoVoluntario, LocalDateTime dataSubmissaoResposta, LocalDateTime dataAceite, boolean aceite,double classificacao, double km) {
        this.codigoPedido = p.getCodigoPedido();

        this.pedidoLoja = p.clone();

        this.classificacao = classificacao;
        this.kmPercorridos = km;

        this.codigoVoluntario = codigoVoluntario;
        this.nomeDoVoluntario = nomeDoVoluntario;
        this.dataSubmissaoResposta = dataSubmissaoResposta;
        this.dataAceite = dataAceite;
        this.aceite = aceite;
    }

    public PedidoVoluntario(PedidoVoluntario p) {
        this.codigoPedido = p.getCodigoPedido();
        this.codigoPedido = p.getCodigoPedido();
        this.pedidoLoja = p.getPedidoLoja();
        this.classificacao = p.getClassificacao();
        this.kmPercorridos = p.getKMPercorridos();
        this.codigoVoluntario = p.getCodigoTransportador();
        this.nomeDoVoluntario = p.getNome();
        this.dataSubmissaoResposta = p.getDataSubmissaoResposta();
        this.dataAceite = p.getDataAceite();
        this.aceite = p.isAceite();
    }

    public double getPreco(){
        return 0;
    }

    public PedidoLoja getPedidoLoja() {
        return this.pedidoLoja.clone();
    }

    public void setPedidoLoja(PedidoLoja pedidoLoja) {
        this.pedidoLoja = pedidoLoja.clone();
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public double getKMPercorridos() {
        return kmPercorridos;
    }

    public void setKmPercorridos(double kmPercorridos) {
        this.kmPercorridos = kmPercorridos;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public LocalDateTime getDataCriacao() {
        return this.pedidoLoja.getDataCriacao();
    }

    public String getUtilizador() {
        return this.pedidoLoja.getUtilizador();
    }

    public void setUtilizador(String utilizador) {
        this.pedidoLoja.setUtilizador(utilizador);
    }

    public GPS getGpsUtilizador() {
        return this.pedidoLoja.getGpsUtilizador().clone();
    }

    public I_Encomendas getEncomenda() {
        return this.pedidoLoja.getEncomenda().clone();
    }

    public void setEncomenda(I_Encomendas e) {
        this.pedidoLoja.setEncomenda(e.clone());
    }

    public LocalDateTime getDataAceitePelaLoja() {
        return this.pedidoLoja.getDataAceitePelaLoja();
    }

    public String getLoja() {
        return this.pedidoLoja.getLoja();
    }

    public void setLoja(String loja) {
        this.pedidoLoja.setLoja(loja);
    }

    public GPS getGpsLoja() {
        return this.pedidoLoja.getGpsLoja().clone();
    }

    public String getCodigoTransportador() {
        return this.codigoVoluntario;
    }

    public void setCodigoVoluntario(String codigoVoluntario) {
        this.codigoVoluntario = codigoVoluntario;
    }

    public String getNome() {
        return this.nomeDoVoluntario;
    }

    public void setNomeDoVoluntario(String nomeDoVoluntario) {
        this.nomeDoVoluntario = nomeDoVoluntario;
    }

    public LocalDateTime getDataSubmissaoResposta() {
        return this.dataSubmissaoResposta;
    }

    public void setDataSubmissaoResposta(LocalDateTime dataSubmissaoResposta) {
        this.dataSubmissaoResposta = dataSubmissaoResposta;
    }

    public LocalDateTime getDataAceite() {
        return this.dataAceite;
    }

    public void setDataAceite(LocalDateTime dataAceite) {
        this.dataAceite = dataAceite;
    }

    public boolean isAceite() {
        return this.aceite;
    }

    public void setAceite(boolean aceite) {
        this.aceite = aceite;
    }

    public PedidoVoluntario clone(){
        return new PedidoVoluntario(this);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Encomenda: ").append(this.codigoPedido).append(" | ").append(this.pedidoLoja.getEncomenda().toString()).append(" | Aceite: ").append(this.aceite);
        return sb.toString();
    }
}
