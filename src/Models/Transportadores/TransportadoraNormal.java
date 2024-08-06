package Models.Transportadores;

import Models.Sistema.*;
import Models.Utilizador.GPS;

import java.time.LocalDateTime;

public class TransportadoraNormal extends Transportadora {

    public TransportadoraNormal(){
        super();
    }

    public TransportadoraNormal(String a, String b, GPS g, String c, double r, double p) {
        super(a, b, g, c, r, p);
    }

    public TransportadoraNormal(TransportadoraNormal t){
        super(t);
    }

    public double precoTransporte(PedidoLoja p){
        double distanciaLoja = super.getGPS().distancia(p.getGpsLoja());
        double distanciaUtilizador = p.getGpsLoja().distancia(p.getGpsUtilizador());
        double taxaPeso = this.calculaTaxaPeso(p.getEncomenda().getPeso());

        return ((distanciaLoja + distanciaUtilizador)*super.getPrecoPorKm())*taxaPeso;
    }

    private double calculaTempoEspera(String estado){
        double r = 0.28;
        switch (estado){
            case "Muito" : r = 0.5;
            case "Moderado" : r = 0.25;
            case "Pouco" : r = 0.01;
        }
        return r;
    }

    private double calculaDistancia(PedidoLoja p){
        return super.getGPS().distancia(p.getGpsLoja()) + p.getGpsLoja().distancia(p.getGpsUtilizador());
    }

    private double calculaTaxaPeso(double peso){
        double r = 1;
        if(peso>10 && peso<50) r+=0.1;
        if(peso>50) r+=0.4;
        return r;
    }

    public double tempoTransporte(PedidoLoja p){
        double distanciaLoja = super.getGPS().distancia(p.getGpsLoja());
        double distanciaUtilizador = p.getGpsLoja().distancia(p.getGpsUtilizador());
        double taxaEsperaLoja = this.calculaTempoEspera(p.getEstadoEsperaLoja());

        return (((distanciaLoja + distanciaUtilizador)/super.getVelocidadeMedia())+taxaEsperaLoja) * Aleatoridade.geraTaxaAleatoridade();
    }

    public boolean aceitaCaracteristicasEncomenda(PedidoLoja p){

        if(!super.superAceitaCaracteristicasEncomenda(p)) return false;

        return (p.isTemMedicamentos()) ? this.aceitoTransporteMedicamentos() : true;
    }


    /**
     * Cria cópia da Transportadora
     *
     * @return Transportadora correspondente à sua Cópia
     */
    public TransportadoraNormal clone(){
        return new TransportadoraNormal(this);
    };

    public boolean aceitoTransporteMedicamentos(){
        return false;
    }

    public PedidoTransportadora recebeSugestao(PedidoLoja p){
        String tempoPrevisto = String.format("%f horas",this.tempoTransporte(p));
        PedidoTransportadora pt = new PedidoTransportadora(p,super.getCodigo(),super.getNomeEmpresa(),this.precoTransporte(p), LocalDateTime.now(), LocalDateTime.now(), false, tempoPrevisto,super.getClassificacoes(),this.calculaDistancia(p));
        return pt.clone();
    }

    public I_PedidosTransportadores ocupaTransportadora(PedidoLoja p){
        String tempoPrevisto = String.format("%f horas",this.tempoTransporte(p.clone()));
        PedidoTransportadora pt = new PedidoTransportadora(p,super.getCodigo(),super.getNomeEmpresa(),this.precoTransporte(p),LocalDateTime.now(),LocalDateTime.now(),false, tempoPrevisto,super.getClassificacoes(),this.calculaDistancia(p));
        super.adicionaHistorico(pt.clone());
        super.setCodEncomendaAtual(pt.getCodigoPedido());
        System.out.println(this.toString());
        return pt.clone();
    }
}
