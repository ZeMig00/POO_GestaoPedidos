package Models.Sistema;

import Models.Encomendas.I_Encomendas;
import Models.Encomendas.LinhaEncomenda;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PedidoCompleto implements Serializable {

    private String codigoPedido;
    private I_PedidosTransportadores pedidosTransportadores;

    //Entregue em
    private LocalDateTime entregue;

    //Avaliado
    private boolean avaliado;

    public PedidoCompleto(I_PedidosTransportadores p) {
        this.codigoPedido = p.getCodigoPedido();
        this.pedidosTransportadores = p.clone();
        this.entregue = LocalDateTime.now();
        this.avaliado = false;
    }

    public PedidoCompleto(PedidoCompleto p) {
        this.codigoPedido = p.getCodigoPedido();
        this.pedidosTransportadores = p.getPedidosTransportadores();
        this.entregue = p.getEntregue();
        this.avaliado = p.isAvaliado();
    }

    public I_PedidosTransportadores getPedidosTransportadores() {
        return this.pedidosTransportadores.clone();
    }

    public void setPedidosTransportadores(I_PedidosTransportadores pedidosTransportadores) {
        this.pedidosTransportadores = pedidosTransportadores.clone();
    }

    public double getKmPercorridos() {
        return this.pedidosTransportadores.getKMPercorridos();
    }

    public String getCodigoPedido() {
        return this.codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getUtilizador() {
        return this.pedidosTransportadores.getUtilizador();
    }

    public I_Encomendas getEncomenda() {
        return this.pedidosTransportadores.getEncomenda().clone();
    }

    public String getLoja() {
        return this.pedidosTransportadores.getLoja();
    }

    public String getCodigoTransportadora() {
        return this.pedidosTransportadores.getCodigoTransportador();
    }

    public String getNomeDaTransportadora() {
        return this.pedidosTransportadores.getNome();
    }

    public double getPrecoSugerido() {
        return this.pedidosTransportadores.getPreco();
    }

    public LocalDateTime getEntregue() {
        return this.entregue;
    }

    public void setEntregue(LocalDateTime entregue) {
        this.entregue = entregue;
    }

    public boolean isAvaliado() {
        return this.avaliado;
    }

    public void setAvaliado(boolean avaliado) {
        this.avaliado = avaliado;
    }

    public PedidoCompleto clone(){
        return new PedidoCompleto(this);
    }

    public String calculaTempoTransorte(){
        long res = ChronoUnit.MINUTES.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
        String unidades = "minutos";
        if(res>60){
            res = ChronoUnit.HOURS.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
            unidades = "horas";
            if(res>24){
                res = ChronoUnit.DAYS.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
                unidades = "dias";
                if(res>31){
                    res = ChronoUnit.MONTHS.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
                    unidades = "meses";
                    if(res>366){
                        res = ChronoUnit.YEARS.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
                        unidades = "anos";
                    }
                }
            }
        }
        return res+" "+unidades;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        double preco = this.pedidosTransportadores.getEncomenda().getLinhasEncomenda().parallelStream().mapToDouble(LinhaEncomenda::getValor).sum();
        sb.append("A encomenda: ").append(this.pedidosTransportadores.getEncomenda().toString()).append("| Preço Loja=").append(preco).append(" | Preço Transporte=").append(this.pedidosTransportadores.getPreco()).append(" | Iniciada em ").append(this.pedidosTransportadores.getDataAceite().toString()).append(" | Finalizada em ").append(this.entregue.toString());
        return sb.toString();
    }

}
