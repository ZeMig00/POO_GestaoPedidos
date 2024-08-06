package Models.Transportadores;

import Models.Sistema.Aleatoridade;
import Models.Utilizador.GPS;
import Models.Sistema.I_PedidosTransportadores;
import Models.Sistema.PedidoLoja;
import Models.Sistema.PedidoTransportadora;

import java.time.LocalDateTime;

public class TransportadoraCertificada extends Transportadora {

    private boolean transportaMedicamentos;

    public TransportadoraCertificada(){
        super();
    }

    public TransportadoraCertificada(String a, String b, GPS g, String c, double r, double p, boolean med) {
        super(a, b, g, c, r, p);
        this.transportaMedicamentos = med;
    }

    private double calculaDistancia(PedidoLoja p){
        return super.getGPS().distancia(p.getGpsLoja()) + p.getGpsLoja().distancia(p.getGpsUtilizador());
    }

    public TransportadoraCertificada(TransportadoraCertificada t){
        super(t);
    }

    public double precoTransporte(PedidoLoja p){
        double distanciaLoja = super.getGPS().distancia(p.getGpsLoja());
        double distanciaUtilizador = p.getGpsLoja().distancia(p.getGpsUtilizador());
        double taxaPeso = this.calculaTaxaPeso(p.getEncomenda().getPeso());
        double taxaPorAceitarMedicamentos = 0.01 + ((p.isTemMedicamentos()) ? 1.01 : 1);

        return (((distanciaLoja + distanciaUtilizador)*super.getPrecoPorKm())*taxaPorAceitarMedicamentos)*taxaPeso;
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

    private double calculaTaxaPeso(double peso){
        double r = 1;
        if(peso>10 && peso<50) r+=0.1;
        if(peso>50) r+=0.4;
        return r;
    }

    public double tempoTransporte(PedidoLoja p){
        double distanciaLoja = super.getGPS().distancia(p.getGpsLoja());
        double distanciaUtilizador = p.getGpsLoja().distancia(p.getGpsUtilizador());
        double tempoMenosPorTransportarMedicamentos = -0.01 + ((p.isTemMedicamentos()) ? 0.96 : 1);
        double taxaEsperaLoja = this.calculaTempoEspera(p.getEstadoEsperaLoja());

        return ((((distanciaLoja + distanciaUtilizador)/super.getVelocidadeMedia())+taxaEsperaLoja)*tempoMenosPorTransportarMedicamentos) * Aleatoridade.geraTaxaAleatoridade();
    }

    public boolean isTransportaMedicamentos() {
        return this.transportaMedicamentos;
    }

    public void setTransportaMedicamentos(boolean transportaMedicamentos) {
        this.transportaMedicamentos = transportaMedicamentos;
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
    public TransportadoraCertificada clone(){
        return new TransportadoraCertificada(this);
    };

    public boolean aceitoTransporteMedicamentos(){
        return transportaMedicamentos;
    }

    public PedidoTransportadora recebeSugestao(PedidoLoja p){
        String tempoPrevisto = String.format("%f horas",this.tempoTransporte(p));
        PedidoTransportadora pt = new PedidoTransportadora(p,super.getCodigo(),super.getNomeEmpresa(),this.precoTransporte(p), LocalDateTime.now(), LocalDateTime.now(), false, tempoPrevisto,super.getClassificacoes(),this.calculaDistancia(p));
        return pt.clone();
    }

    public I_PedidosTransportadores ocupaTransportadora(PedidoLoja p){
        super.setCodEncomendaAtual(p.getCodigoPedido());
        String tempoPrevisto = String.format("%f horas",this.tempoTransporte(p));
        PedidoTransportadora pt = new PedidoTransportadora(p,super.getCodigo(),super.getNomeEmpresa(),this.precoTransporte(p),LocalDateTime.now(),LocalDateTime.now(),false, tempoPrevisto,super.getClassificacoes(),this.calculaDistancia(p));
        super.adicionaHistorico(pt);
        return pt;
    }
}
