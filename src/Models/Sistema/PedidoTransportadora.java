package Models.Sistema;

import Models.Encomendas.I_Encomendas;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoTransportadora implements Serializable, I_PedidosTransportadores {

    private String codigoPedido;

    private PedidoLoja pedidoLoja;

    //SistemaAtribuiu
    private String codigoTransportadora;
    private String nomeDaTransportadora;
    private double precoSugerido;
    private String tempoPrevisto;
    private double classificacao;
    private double kmPercorridos;

    private LocalDateTime dataSubmissaoResposta;

    //Aceite pelo Models.Utilizador
    private LocalDateTime dataAceite;
    private boolean aceite;

    public PedidoTransportadora(PedidoLoja p, String codigoTransportadora, String nomeDaTransportadora, double precoSugerido, LocalDateTime dataSubmissaoResposta, LocalDateTime dataAceite, boolean aceite, String tempoPrevisto, double classificacao, double km) {

        this.codigoPedido = p.getCodigoPedido();
        this.pedidoLoja = p.clone();
        this.codigoTransportadora = codigoTransportadora;
        this.nomeDaTransportadora = nomeDaTransportadora;
        this.precoSugerido = precoSugerido;
        this.tempoPrevisto = tempoPrevisto;
        this.classificacao = classificacao;
        this.kmPercorridos = km;
        this.dataSubmissaoResposta = dataSubmissaoResposta;
        this.dataAceite = dataAceite;
        this.aceite = aceite;
    }

    public PedidoTransportadora(PedidoTransportadora p){

        this.codigoPedido = p.getCodigoPedido();
        this.codigoPedido = p.getCodigoPedido();
        this.pedidoLoja = p.getPedidoLoja().clone();
        this.codigoTransportadora = p.getCodigoTransportador();
        this.nomeDaTransportadora = p.getNome();
        this.precoSugerido = p.getPreco();
        this.tempoPrevisto = p.getTempoPrevisto();
        this.kmPercorridos = p.getKMPercorridos();
        this.classificacao = p.getClassificacao();
        this.dataSubmissaoResposta = p.getDataSubmissaoResposta();
        this.dataAceite = p.getDataAceite();
        this.aceite = p.isAceite();
    }

    public void setPedidoLoja(PedidoLoja pedidoLoja) {
        this.pedidoLoja = pedidoLoja.clone();
    }

    public double getKMPercorridos() {
        return kmPercorridos;
    }

    public void setKmPercorridos(double kmPercorridos) {
        this.kmPercorridos = kmPercorridos;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public String getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(String tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public boolean isTemMedicamentos() {
        return this.pedidoLoja.isTemMedicamentos();
    }

    public String getEstadoLoja() {
        return this.pedidoLoja.getEstadoEsperaLoja();
    }

   public String getCodigoPedido() {
        return this.codigoPedido;
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
        return this.codigoTransportadora;
    }

    public void setCodigoTransportadora(String codigoTransportadora) {
        this.codigoTransportadora = codigoTransportadora;
    }

    public String getNome() {
        return this.nomeDaTransportadora;
    }

    public void setNomeDaTransportadora(String nomeDaTransportadora) {
        this.nomeDaTransportadora = nomeDaTransportadora;
    }

    public double getPreco() {
        return this.precoSugerido;
    }

    public void setPrecoSugerido(double precoSugerido) {
        this.precoSugerido = precoSugerido;
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

    public PedidoTransportadora clone(){
        return new PedidoTransportadora(this);
    }

    public PedidoLoja getPedidoLoja(){
        return this.pedidoLoja.clone();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Encomenda: ").append(this.codigoPedido).append(" | ")
                .append(this.pedidoLoja.getEncomenda().toString()).append(" | Aceite: ").append(this.aceite)
                .append(" | Preço=").append(this.precoSugerido);
        return sb.toString();
    }

    public String toStringUtilizador() {
        StringBuffer sb = new StringBuffer();
        sb.append("Encomenda: ").append(this.codigoPedido).append("\n");
        sb.append("Detalhes da encomenda: ").append(this.pedidoLoja.getEncomenda().toString()).append("\n");
        sb.append("Solução: ").append("Trasportadora ").append(this.codigoTransportadora+" | ")
                .append(this.nomeDaTransportadora).append(" | Tempo Previsto: ")
                .append(this.tempoPrevisto).append(" | Classificação: ").append(this.classificacao)
                .append("\n");
        sb.append("Preço = ").append(this.precoSugerido).append(" euros");

        return sb.toString();
    }
}
