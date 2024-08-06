package Models.Sistema;

import Models.Encomendas.I_Encomendas;
import Models.Encomendas.LinhaEncomenda;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoUtilizador implements Serializable {

    private String codigoPedido;

    //UtilizadorCriou
    private LocalDateTime dataCriacao;
    private String utilizador;
    private GPS gpsUtilizador;

    //Encomenda
    private I_Encomendas e;

    public PedidoUtilizador(I_Encomendas e, GPS gpsUtilizador, LocalDateTime t) {

        this.codigoPedido = e.getCodEncomenda();
        this.utilizador = e.getCodUtilizador();
        this.gpsUtilizador = gpsUtilizador.clone();
        this.e = e.clone();

        this.dataCriacao = t;
    }

    public PedidoUtilizador(PedidoUtilizador p) {
        this.codigoPedido = p.getCodigoPedido();
        this.utilizador = p.getUtilizador();
        this.gpsUtilizador = p.getGpsUtilizador();
        this.e = p.getEncomenda();
        this.dataCriacao = p.getDataCriacao();
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public GPS getGpsUtilizador() {
        return gpsUtilizador.clone();
    }

    public void setGpsUtilizador(GPS gpsUtilizador) {
        this.gpsUtilizador = gpsUtilizador.clone();
    }

    public I_Encomendas getEncomenda() {
        return e.clone();
    }

    public void setEncomenda(I_Encomendas e) {
        this.e = e.clone();
    }

    public double getPrecoTotal(){
        return this.e.getLinhasEncomenda().parallelStream().mapToDouble(LinhaEncomenda::getValor).sum();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        double preco = this.e.getLinhasEncomenda().parallelStream().mapToDouble(LinhaEncomenda::getValor).sum();
        sb.append("A encomenda: ").append(this.e.toString()).append(" | Preço=").append(preco);
        return sb.toString();
    }

    public PedidoUtilizador clone(){
        return new PedidoUtilizador(this);
    }
}
