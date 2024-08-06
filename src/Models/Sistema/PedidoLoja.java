package Models.Sistema;

import Models.Encomendas.I_Encomendas;
import Models.Encomendas.LinhaEncomenda;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoLoja implements Serializable {

    private String codigoPedido;

    private PedidoUtilizador pedidoUtilizador;

    private boolean temMedicamentos;

    //LojaCriou
    private LocalDateTime dataAceitePelaLoja;
    private String loja;
    private GPS gpsLoja;
    private String estadoEsperaLoja;

    public PedidoLoja(PedidoUtilizador p, LocalDateTime dataAceitePelaLoja, String loja, GPS gpsLoja, boolean med, String espera) {

        this.codigoPedido = p.getCodigoPedido();

        this.pedidoUtilizador = p.clone();

        this.temMedicamentos = med;

        this.dataAceitePelaLoja = dataAceitePelaLoja;
        this.loja = loja;
        this.gpsLoja = gpsLoja;
        this.estadoEsperaLoja = espera;
    }

    public PedidoLoja(PedidoLoja p) {

        this.codigoPedido = p.getCodigoPedido();
        this.pedidoUtilizador = p.getPedidoUtilizador();
        this.temMedicamentos = p.isTemMedicamentos();
        this.dataAceitePelaLoja = p.getDataAceitePelaLoja();
        this.loja = p.getLoja();
        this.gpsLoja = p.getGpsLoja();
        this.estadoEsperaLoja = p.getEstadoEsperaLoja();
    }

    public PedidoUtilizador getPedidoUtilizador() {
        return this.pedidoUtilizador.clone();
    }

    public void setPedidoUtilizador(PedidoUtilizador pedidoUtilizador) {
        this.pedidoUtilizador = pedidoUtilizador.clone();
    }

    public boolean isTemMedicamentos() {
        return temMedicamentos;
    }

    public void setTemMedicamentos(boolean temMedicamentos) {
        this.temMedicamentos = temMedicamentos;
    }

    public String getEstadoEsperaLoja() {
        return estadoEsperaLoja;
    }

    public void setEstadoEsperaLoja(String estadoEsperaLoja) {
        this.estadoEsperaLoja = estadoEsperaLoja;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public LocalDateTime getDataCriacao() {
        return this.pedidoUtilizador.getDataCriacao();
    }

    public String getUtilizador() {
        return this.pedidoUtilizador.getUtilizador();
    }

    public void setUtilizador(String utilizador) {
        this.pedidoUtilizador.setUtilizador(utilizador);
    }

    public GPS getGpsUtilizador() {
        return this.pedidoUtilizador.getGpsUtilizador().clone();
    }

    public I_Encomendas getEncomenda() {
        return this.pedidoUtilizador.getEncomenda().clone();
    }

    public void setEncomenda(I_Encomendas e) {
        this.pedidoUtilizador.setEncomenda(e.clone());
    }

    public LocalDateTime getDataAceitePelaLoja() {
        return dataAceitePelaLoja;
    }

    public void setDataAceitePelaLoja(LocalDateTime dataAceitePelaLoja) {
        this.dataAceitePelaLoja = dataAceitePelaLoja;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public GPS getGpsLoja() {
        return gpsLoja.clone();
    }

    public void setGpsLoja(GPS gpsLoja) {
        this.gpsLoja = gpsLoja.clone();
    }

    public PedidoLoja clone(){
        return new PedidoLoja(this);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        double preco = this.pedidoUtilizador.getPrecoTotal();
        sb.append("A encomenda: ").append(this.pedidoUtilizador.getEncomenda().toString()).append("| Preço=").append(preco);
        return sb.toString();
    }
}
