package Models.Sistema;

import Models.Encomendas.I_Encomendas;

import java.time.LocalDateTime;

public interface I_PedidosTransportadores {
    String getCodigoPedido();
    String getUtilizador();
    String getLoja();
    String getNome();
    String getCodigoTransportador();
    double getPreco();
    double getKMPercorridos();
    I_Encomendas getEncomenda();
    LocalDateTime getDataAceite();
    I_PedidosTransportadores clone();
    String toString();
}

