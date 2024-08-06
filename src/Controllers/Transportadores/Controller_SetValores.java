package Controllers.Transportadores;

import Controllers.I_Controllers;
import Models.TrazAqui;
import Views.I_View;
import Views.Transportadores.View_EncomendasParaRecolha;
import Views.View_Erro;
import Views.View_Historico;

import java.util.List;

public class Controller_SetValores implements I_Controllers {
    private TrazAqui sistema;
    private String codigoTransportador;

    public Controller_SetValores(TrazAqui s, String cod){
        this.sistema = s;
        this.codigoTransportador = cod;
    }

    public void processa(List<String> opcao){

        switch (opcao.get(0)){
            case "MudarPreco" : {
                double val = Double.parseDouble(opcao.get(1));
                if(val>=0){
                    this.sistema.mudaPrecoMedio(codigoTransportador,val);
                    View_Erro view = new View_Erro("Valor alterado com sucesso");
                    view.run();
                }
                else{
                    View_Erro view = new View_Erro("Valor invalido");
                    view.run();
                }

                break;
            }

            case "MudarVelocidade" : {
                double val = Double.parseDouble(opcao.get(1));
                if(val>0){
                    this.sistema.mudaVelocidadeMedia(codigoTransportador,val);
                    View_Erro view = new View_Erro("Valor alterado com sucesso");
                    view.run();
                }
                else{
                    View_Erro view = new View_Erro("Valor invalido");
                    view.run();
                }
                break;
            }

            case "MudarRaio" : {
                double val = Double.parseDouble(opcao.get(1));
                if(val>0){
                    this.sistema.mudaRaioAcao(codigoTransportador,val);
                    View_Erro view = new View_Erro("Valor alterado com sucesso");
                    view.run();
                }
                else{
                    View_Erro view = new View_Erro("Valor invalido");
                    view.run();
                }
                break;
            }
        }
    }
}
