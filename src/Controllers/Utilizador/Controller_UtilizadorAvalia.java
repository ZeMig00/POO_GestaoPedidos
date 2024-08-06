package Controllers.Utilizador;

import Controllers.I_Controllers;
import Excepitions.ValorErradoException;
import Models.TrazAqui;
import Views.View_Erro;

import java.util.List;

public class Controller_UtilizadorAvalia implements I_Controllers {

    private TrazAqui sistema;
    private String codUtilizador;

    public Controller_UtilizadorAvalia(String cod, TrazAqui s){
        this.sistema = s;
        this.codUtilizador = cod;
    }

    public void processa(List<String> opcao){
        if (opcao.get(0).equals("Avalia")) {
            try {
                this.sistema.avaliaTransportador(this.codUtilizador,opcao.get(1),Integer.parseInt(opcao.get(2)));
            } catch (ValorErradoException e) {
                View_Erro erro = new View_Erro(e.getMessage());
                erro.run();
            }
        }
    }
}
