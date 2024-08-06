package Controllers.Utilizador;

import Controllers.I_Controllers;
import Models.TrazAqui;

import java.util.List;

public class Controller_UtilizadorAceitaPendentes implements I_Controllers {

    private TrazAqui sistema;
    private String codUtilizador;

    public Controller_UtilizadorAceitaPendentes(TrazAqui s, String cod){
        this.sistema = s;
        this.codUtilizador = cod;
    }

    public void processa(List<String> opcao){
        switch (opcao.get(0)){
            case "Aceita" :{
                this.sistema.aceitaTransportadoraPendente(this.codUtilizador,opcao.get(1));
                break;
            }
            case "Rejeita" :{
                this.sistema.rejeitaTransportadoraPendente(this.codUtilizador,opcao.get(1));
                break;
            }
        }
    }
}
