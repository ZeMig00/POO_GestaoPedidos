package Models.Transportadores;

import Excepitions.ValorErradoException;
import Models.Sistema.I_PedidosTransportadores;
import Models.Sistema.PedidoCompleto;
import Models.Sistema.PedidoLoja;

import java.util.List;

public interface I_Transportadores {

    List<String> getListaHistorico();
    void setVelocidadeMedia(double velocidadeMedia);
    double getVelocidadeMedia();
    void setRaio(double raio);
    double getRaio();
    double getPrecoPorKm();
    void setPrecoPorKm(double preco);

    PedidoCompleto finalizaEntrega();
    void mudaEstado();
    boolean aceitoTransporteMedicamentos();
    I_PedidosTransportadores aceitarEncomenda();
    PedidoLoja rejeitarEncomenda();

    void avaliaTransportador(int aval) throws ValorErradoException;

    boolean aceitaCaracteristicasEncomenda(PedidoLoja p);

    String getCodigo();
    I_Transportadores clone();

    double totalFaturado();
}
